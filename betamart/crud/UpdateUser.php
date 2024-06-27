<?php
header('Content-Type: application/json'); // Atur header untuk mengirimkan response dalam format JSON

include "koneksi.php";

// Ambil data dari request POST
$id = $_POST['id']; // ID user yang akan di-update
$username = $_POST['username'];
$password = $_POST['password'];
$email = $_POST['email'];
$role = '2'; // Role user

// Query untuk memperbarui data user dalam tabel users
$query = "UPDATE users SET username = ?, password = ?, email = ?, role = ? WHERE id = ?";
$stmt = $koneksi->prepare($query);
$stmt->bind_param("ssssi", $username, $password, $email, $role, $id);

$response = array(); // Inisialisasi array untuk menyimpan response

if ($stmt->execute()) {
    // Jika query berhasil dieksekusi
    $response['status'] = 'success';
    $response['message'] = 'User updated successfully';
    echo json_encode($response); // Mengirimkan response dalam format JSON
} else {
    // Jika query gagal dieksekusi
    $response['status'] = 'error';
    $response['message'] = 'Failed to update user';
    echo json_encode($response); // Mengirimkan response dalam format JSON
}

$stmt->close();
$koneksi->close();
