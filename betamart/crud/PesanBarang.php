<?php
// Include file koneksi.php untuk menghubungkan ke database
include "koneksi.php";

// Ambil data dari request POST
$id_barang = $_POST['id_barang'];
$nama_penerima = $_POST['nama_penerima'];
$jumlah_pesanan = $_POST['jumlah_pesanan'];
$alamat = $_POST['alamat'];
$nomor_hp = $_POST['nomor_hp'];
$id_user = $_POST['id_user']; // Ambil id_user dari data yang dikirim

// Validasi input
if (empty($id_barang) || empty($nama_penerima) || empty($jumlah_pesanan) || empty($alamat) || empty($nomor_hp) || empty($id_user)) {
    echo json_encode(array('status' => 'error', 'message' => 'Semua field harus diisi'));
    exit;
}

// Query untuk menghitung total bayar berdasarkan id_barang dan jumlah_pesanan
$query_harga_barang = "SELECT harga FROM tabel_barang WHERE id_barang = ?";
$stmt_harga = $koneksi->prepare($query_harga_barang);
$stmt_harga->bind_param("i", $id_barang);
$stmt_harga->execute();
$result_harga_barang = $stmt_harga->get_result();

if ($result_harga_barang->num_rows > 0) {
    $row_harga_barang = $result_harga_barang->fetch_assoc();
    $harga_barang = $row_harga_barang['harga'];
    $total_bayar = $harga_barang * $jumlah_pesanan;
} else {
    echo json_encode(array('status' => 'error', 'message' => 'ID Barang tidak ditemukan'));
    exit;
}

// Query untuk memasukkan data pemesanan ke tabel_pemesanan
$query = "INSERT INTO tabel_pemesanan (id_barang, nama_penerima, jumlah_pesanan, alamat, nomor_hp, id_user, total_bayar, tanggal_pemesanan)
          VALUES (?, ?, ?, ?, ?, ?, ?, NOW())";
$stmt = $koneksi->prepare($query);
$stmt->bind_param("isissid", $id_barang, $nama_penerima, $jumlah_pesanan, $alamat, $nomor_hp, $id_user, $total_bayar);

// Eksekusi query untuk memasukkan data pemesanan
if ($stmt->execute()) {
    echo json_encode(array('status' => 'success', 'message' => 'Pemesanan berhasil'));
} else {
    echo json_encode(array('status' => 'error', 'message' => 'Gagal melakukan pemesanan: ' . $stmt->error));
}

// Menutup statement dan koneksi database
$stmt->close();
$koneksi->close();
