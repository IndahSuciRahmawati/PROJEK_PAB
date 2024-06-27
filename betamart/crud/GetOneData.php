<?php
include "koneksi.php";

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    if (isset($_POST['id'])) {
        $id = $_POST['id'];

        $query = "SELECT * FROM tabel_barang WHERE id_barang = ?";
        $stmt = $koneksi->prepare($query);

        if ($stmt === false) {
            echo json_encode(array(
                'status' => 'error',
                'message' => 'Failed to prepare statement'
            ));
            exit();
        }

        $stmt->bind_param("i", $id);
        $stmt->execute();
        $result = $stmt->get_result();

        if ($result->num_rows > 0) {
            $data = $result->fetch_assoc();
            echo json_encode(array(
                'status' => 'success',
                'data' => $data
            ));
        } else {
            echo json_encode(array(
                'status' => 'data_not_found'
            ));
        }

        $stmt->close();
    } else {
        echo json_encode(array(
            'status' => 'error',
            'message' => 'ID not provided'
        ));
    }
} else {
    echo json_encode(array(
        'status' => 'error',
        'message' => 'Invalid request method'
    ));
}

$koneksi->close();
