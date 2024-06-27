<?php
include "koneksi.php";

$id_barang = $_POST['id_barang'];
$nama_barang = $_POST['nama_barang'];
$merk = $_POST['merk'];
$tipe = $_POST['tipe'];
$keterangan = $_POST['keterangan']; // Variabel keterangan
$harga = $_POST['harga'];
$jumlah = $_POST['jumlah'];
$gambar = $_FILES['gambar']; // Mengambil data gambar dari $_FILES
$path = "/images/" . $gambar['name']; // Menyimpan path gambar

// Membuat folder jika belum ada
$folder_path = __DIR__ . "/images/";
if (!is_dir($folder_path)) {
    mkdir($folder_path, 0777, true);
}

// Memindahkan file gambar ke direktori /images/
move_uploaded_file($gambar['tmp_name'], $folder_path . $gambar['name']);

$query = "INSERT INTO tabel_barang (id_barang, nama_barang, merk, tipe, keterangan, harga, jumlah, gambar, path) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
$stmt = $koneksi->prepare($query);
$stmt->bind_param("ssssssdss", $id_barang, $nama_barang, $merk, $tipe, $keterangan, $harga, $jumlah, $gambar['name'], $path);

$response = array(); // Inisialisasi array untuk menyimpan response

if ($stmt->execute()) {
    $response['status'] = 'data_tersimpan';
    echo "<script>
            alert('User registered successfully');
            window.location.href='../admin/tampilBarang.php';
          </script>";
} else {
    $response['status'] = 'gagal';
    $response['error'] = $stmt->error;
}

echo json_encode($response);

$stmt->close();
$koneksi->close();
