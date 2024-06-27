<?php
header('Content-Type: application/json'); // Atur header untuk mengirimkan response dalam format JSON

include "koneksi.php";

// Cek apakah parameter ID tersedia dalam request POST
if (isset($_POST['id'])) {
    $id = $_POST['id'];

    // Query untuk mengambil data user berdasarkan ID
    $query = "SELECT id, username, email, password, role FROM users WHERE id = ?";
    $stmt = $koneksi->prepare($query);
    $stmt->bind_param("i", $id);

    $response = array(); // Inisialisasi array untuk menyimpan response

    if ($stmt->execute()) {
        $result = $stmt->get_result();

        if ($result->num_rows > 0) {
            $user = $result->fetch_assoc();
            $response['status'] = 'success';
            $response['data'] = $user;
        } else {
            // Jika tidak ditemukan user dengan ID tersebut
            $response['status'] = 'error';
            $response['message'] = 'User not found';
        }
    } else {
        // Jika query gagal dieksekusi
        $response['status'] = 'error';
        $response['message'] = 'Failed to get user';
    }

    echo json_encode($response); // Mengirimkan response dalam format JSON
    $stmt->close();
} else {
    // Jika parameter ID tidak tersedia dalam request
    $response['status'] = 'error';
    $response['message'] = 'ID parameter is missing';
    echo json_encode($response); // Mengirimkan response dalam format JSON
}

$koneksi->close();
