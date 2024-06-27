<?php
// Include file koneksi.php untuk menghubungkan ke database
include "koneksi.php";

// Ambil id_user dari request (misalnya dari parameter URL atau body request)
$id_user = isset($_POST['id_user']) ? $_POST['id_user'] : 0;

// Validasi id_user
if ($id_user == 0) {
    echo json_encode(array('status' => 'error', 'message' => 'id_user tidak ditemukan'));
    exit;
}

// Query untuk mengambil data pemesanan barang beserta informasi nama barang dan gambar dari tabel_pemesanan dan tabel_barang
$query = "SELECT p.id_pemesanan, b.nama_barang, b.merk, b.tipe, b.gambar, p.nama_penerima, p.jumlah_pesanan, p.alamat, p.nomor_hp, p.total_bayar, p.tanggal_pemesanan
          FROM tabel_pemesanan p
          INNER JOIN tabel_barang b ON p.id_barang = b.id_barang
          WHERE p.id_user = ?
          ORDER BY p.tanggal_pemesanan DESC";

// Persiapan statement
$stmt = $koneksi->prepare($query);
$stmt->bind_param("i", $id_user);
$stmt->execute();
$result = $stmt->get_result();

// Cek jika terdapat hasil dari query
if ($result) {
    // Array untuk menyimpan hasil query
    $response = array();

    // Mengambil data dan memasukkannya ke dalam array response
    while ($row = $result->fetch_assoc()) {
        // Ubah path gambar jika diperlukan
        $row['gambar'] = '/images/' . $row['gambar']; // Sesuaikan dengan path tempat gambar disimpan

        $response[] = $row;
    }

    // Mengirimkan response dalam format JSON
    echo json_encode(array('status' => 'success', 'data' => $response));
} else {
    // Jika query tidak berhasil dieksekusi
    echo json_encode(array('status' => 'error', 'message' => 'Gagal mengambil data pemesanan: ' . $koneksi->error));
}

// Menutup koneksi database
$stmt->close();
$koneksi->close();
