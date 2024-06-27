<?php
include 'koneksi.php';

if (isset($_GET['id'])) {
    $id_barang = $_GET['id'];
    $delete = "DELETE FROM tabel_barang WHERE id_barang = ?";

    $stmt = $koneksi->prepare($delete);
    $stmt->bind_param("s", $id_barang);

    if ($stmt->execute()) {
        echo json_encode(array(
            'status' => 'Data Berhasil Dihapus'
        ));
        echo "<script>
            alert('User registered successfully');
            window.location.href='../admin/tampilBarang.php';
          </script>";
    } else {
        echo json_encode(array(
            'status' => 'Data Gagal Dihapus'
        ));
        echo "<script>
            alert('User registered successfully');
            window.location.href='../admin/tampilBarang.php';
          </script>";
    }

    $stmt->close();
    $koneksi->close();
} else {
    echo json_encode(array(
        'status' => 'No ID Provided'
    ));
}
