<?php

include 'koneksi.php';

$select = "SELECT * FROM tabel_barang";

$result = mysqli_query($koneksi, $select);

if ($result) {
    $data = array();

    while ($row = mysqli_fetch_assoc($result)) {
        $data[] = $row;
    }

    echo json_encode(array(
        'status' => 'success',
        'data' => $data
    ));
} else {
    echo json_encode(array(
        'status' => 'failed',
        'message' => 'Failed to get data'
    ));
}

mysqli_close($koneksi);
