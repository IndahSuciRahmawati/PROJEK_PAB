<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Input Barang</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
    <div class="container mt-4">
        <h2 class="mb-4">Input Barang</h2>
        <form action="../crud/Tambah.php" method="POST" enctype="multipart/form-data">
            <div class="form-group">
                <label for="id_barang">ID Barang:</label>
                <input type="text" class="form-control" id="id_barang" name="id_barang" required>
            </div>

            <div class="form-group">
                <label for="nama_barang">Nama Barang:</label>
                <input type="text" class="form-control" id="nama_barang" name="nama_barang" required>
            </div>

            <div class="form-group">
                <label for="merk">Merk:</label>
                <input type="text" class="form-control" id="merk" name="merk" required>
            </div>

            <div class="form-group">
                <label for="tipe">Tipe:</label>
                <input type="text" class="form-control" id="tipe" name="tipe" required>
            </div>

            <div class="form-group">
                <label for="keterangan">Keterangan:</label>
                <input type="text" class="form-control" id="keterangan" name="keterangan" required>
            </div>

            <div class="form-group">
                <label for="harga">Harga:</label>
                <input type="text" class="form-control" id="harga" name="harga" required>
            </div>

            <div class="form-group">
                <label for="jumlah">Jumlah:</label>
                <input type="text" class="form-control" id="jumlah" name="jumlah" required>
            </div>

            <div class="form-group">
                <label for="gambar">Gambar:</label>
                <input type="file" class="form-control-file" id="gambar" name="gambar" required accept="image/*">
            </div>

            <button type="submit" class="btn btn-primary">Simpan</button>
        </form>
        <br>

        <!-- Navigasi ke halaman tampil barang dan pembelian -->
        <div class="d-flex justify-content-between mt-3 mb-3">
            <a href="pembelian.php" class="btn btn-info">Pembelian Barang</a>
            <a href="tampilBarang.php" class="btn btn-success">Tampil Barang</a>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>

</html>