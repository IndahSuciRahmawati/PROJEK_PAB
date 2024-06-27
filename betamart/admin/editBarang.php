<?php
include '../crud/koneksi.php';

if (isset($_GET['id'])) {
    $id_barang = $_GET['id'];
    $query = "SELECT * FROM tabel_barang WHERE id_barang = ?";

    $stmt = $koneksi->prepare($query);
    $stmt->bind_param("s", $id_barang);
    $stmt->execute();
    $result = $stmt->get_result();

    if ($result->num_rows > 0) {
        $row = $result->fetch_assoc();
?>

        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Edit Barang</title>
            <!-- Bootstrap CSS -->
            <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        </head>

        <body>
            <div class="container mt-4">
                <h2 class="mb-4">Edit Barang</h2>
                <form action="../crud/UpdateBarang.php" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="id_barang" value="<?php echo $row['id_barang']; ?>">
                    <div class="form-group">
                        <label for="nama_barang">Nama Barang:</label>
                        <input type="text" class="form-control" id="nama_barang" name="nama_barang" value="<?php echo $row['nama_barang']; ?>" required>
                    </div>
                    <div class="form-group">
                        <label for="merk">Merk:</label>
                        <input type="text" class="form-control" id="merk" name="merk" value="<?php echo $row['merk']; ?>" required>
                    </div>
                    <div class="form-group">
                        <label for="tipe">Tipe:</label>
                        <input type="text" class="form-control" id="tipe" name="tipe" value="<?php echo $row['tipe']; ?>" required>
                    </div>
                    <div class="form-group">
                        <label for="harga">Harga:</label>
                        <input type="text" class="form-control" id="harga" name="harga" value="<?php echo $row['harga']; ?>" required>
                    </div>
                    <div class="form-group">
                        <label for="jumlah">Jumlah:</label>
                        <input type="text" class="form-control" id="jumlah" name="jumlah" value="<?php echo $row['jumlah']; ?>" required>
                    </div>
                    <div class="form-group">
                        <label for="path">Gambar Baru:</label>
                        <input type="file" class="form-control-file" id="path" name="gambar">
                    </div>
                    <div class="form-group">
                        <label for="gambar">Gambar Saat Ini:</label><br>
                        <img src="<?php echo '../crud' . $row['path']; ?>" alt="<?php echo $row['nama_barang']; ?>" class="img-fluid" style="max-width: 100px; max-height: 100px;">
                    </div>
                    <button type="submit" class="btn btn-primary mb-3">Update</button>
                </form>
            </div>

            <!-- Bootstrap JS and dependencies -->
            <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        </body>

        </html>

<?php
    } else {
        echo "Barang tidak ditemukan.";
    }

    $stmt->close();
    $koneksi->close();
} else {
    echo "ID Barang tidak diberikan.";
}
?>