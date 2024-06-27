<?php
include 'koneksi.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $id_barang = $_POST['id_barang'];
    $nama_barang = $_POST['nama_barang'];
    $merk = $_POST['merk'];
    $tipe = $_POST['tipe'];
    $keterangan = $_POST['keterangan']; // This variable was missing
    $harga = $_POST['harga'];
    $jumlah = $_POST['jumlah'];
    $gambar = $_FILES['gambar']; // Mengambil data gambar dari $_FILES

    // Jika ada file gambar yang diunggah
    if ($gambar['name']) {
        $folder_path = __DIR__ . "/images/";
        $path = "/images/" . $gambar['name']; // Menyimpan path gambar

        // Membuat folder jika belum ada
        if (!is_dir($folder_path)) {
            mkdir($folder_path, 0777, true);
        }

        // Mengambil path gambar lama dari database
        $query = "SELECT path FROM tabel_barang WHERE id_barang = ?";
        $stmt = $koneksi->prepare($query);
        $stmt->bind_param("s", $id_barang);
        $stmt->execute();
        $result = $stmt->get_result();
        $row = $result->fetch_assoc();
        $old_path = __DIR__ . $row['path'];

        // Memindahkan file gambar ke direktori /images/
        if (move_uploaded_file($gambar['tmp_name'], $folder_path . $gambar['name'])) {
            // Menghapus gambar lama jika gambar baru berhasil diunggah
            if (file_exists($old_path)) {
                unlink($old_path);
            }
        } else {
            echo json_encode(array(
                'status' => 'gagal',
                'message' => 'Gagal mengunggah gambar'
            ));
            exit();
        }
    } else {
        // Jika tidak ada file gambar yang diunggah, gunakan path lama
        $query = "SELECT path FROM tabel_barang WHERE id_barang = ?";
        $stmt = $koneksi->prepare($query);
        $stmt->bind_param("s", $id_barang);
        $stmt->execute();
        $result = $stmt->get_result();
        $row = $result->fetch_assoc();
        $path = $row['path'];
    }

    // Update data barang setelah memindahkan atau menggunakan path gambar baru/lama
    $update = "UPDATE tabel_barang SET nama_barang = ?, merk = ?, tipe = ?, keterangan = ?, harga = ?, jumlah = ?, path = ? WHERE id_barang = ?";
    $stmt = $koneksi->prepare($update);
    $stmt->bind_param("sssssiss", $nama_barang, $merk, $tipe, $keterangan, $harga, $jumlah, $path, $id_barang);

    if ($stmt->execute()) {
        echo "<script>
                alert('Data berhasil diupdate');
                window.location.href='../admin/tampilBarang.php';
              </script>";
        echo json_encode(array(
            'status' => 'data_diupdate'
        ));
    } else {
        echo json_encode(array(
            'status' => 'gagal',
            'error' => $stmt->error
        ));
    }

    $stmt->close();
    $koneksi->close();
} else {
    echo json_encode(array(
        'status' => 'invalid_request_method'
    ));
}
