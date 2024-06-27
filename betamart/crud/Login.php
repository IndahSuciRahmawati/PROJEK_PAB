<?php
header('Content-Type: application/json'); // Atur header untuk mengirimkan response dalam format JSON

include "koneksi.php";

// Ambil data dari request POST
$username = $_POST['username'];
$password = $_POST['password'];

// Query untuk melakukan pengecekan login berdasarkan username dan password
$query = "SELECT * FROM users WHERE username = ? AND password = ?";
$stmt = $koneksi->prepare($query);
$stmt->bind_param("ss", $username, $password);
$stmt->execute();
$result = $stmt->get_result();

$response = array(); // Inisialisasi array untuk menyimpan response

if ($result->num_rows > 0) {
    // Jika login berhasil
    $data = $result->fetch_assoc();

    $response['status'] = 'success';
    $response['id'] = $data['id'];
    $response['username'] = $data['username'];
    $response['role'] = $data['role'];
    echo json_encode($response);

    exit();
} else {
    // Jika login gagal
    $response['status'] = 'error';
    $response['message'] = 'Username or password is incorrect';

    // Tidak perlu menggunakan alert dan redirect di sini karena kita ingin respons JSON
    // Menggunakan exit untuk menghentikan eksekusi lebih lanjut
    echo json_encode($response);
    exit();
}

$stmt->close();
$koneksi->close();
