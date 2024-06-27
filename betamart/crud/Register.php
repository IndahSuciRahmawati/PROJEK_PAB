<?php
header('Content-Type: application/json'); // Atur header untuk mengirimkan response dalam format JSON

include "koneksi.php";

// Ambil data dari request POST
$username = $_POST['username'];
$password = $_POST['password'];
$email = $_POST['email'];
$role = "2"; // Atur role sesuai kebutuhan aplikasi Anda

// Query untuk memasukkan data user baru ke dalam tabel users
$query = "INSERT INTO users (username, password, email, role) VALUES (?, ?, ?, ?)";
$stmt = $koneksi->prepare($query);
$stmt->bind_param("ssss", $username, $password, $email, $role);

$response = array(); // Inisialisasi array untuk menyimpan response

if ($stmt->execute()) {
    // Jika query berhasil dieksekusi
    $response['status'] = 'success';
    $response['message'] = 'User registered successfully';
    echo json_encode($response); // Mengirimkan response dalam format JSON
} else {
    // Jika query gagal dieksekusi
    $response['status'] = 'error';
    $response['message'] = 'Failed to register user';
    echo json_encode($response); // Mengirimkan response dalam format JSON
}

$stmt->close();
$koneksi->close();
