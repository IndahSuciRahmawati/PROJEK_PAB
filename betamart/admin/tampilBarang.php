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
        <h2 class="mb-4">Data Barang</h2>

        <?php
        // Include file koneksi.php
        include "../crud/koneksi.php";

        // Query untuk mengambil data dari tabel_barang
        $query = "SELECT * FROM tabel_barang";
        $result = $koneksi->query($query);

        if ($result->num_rows > 0) {
            // Jika terdapat data, tampilkan dalam tabel
            echo "<div class='table-responsive'>
                    <table class='table table-bordered table-hover'>
                        <thead class='thead-dark'>
                            <tr>
                                <th>ID Barang</th>
                                <th>Nama Barang</th>
                                <th>Merk</th>
                                <th>Tipe</th>
                                <th>Keterangan</th>
                                <th>Harga</th>
                                <th>Jumlah</th>
                                <th>Gambar</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>";

            // Ambil setiap baris data dan tampilkan dalam tabel
            while ($row = $result->fetch_assoc()) {
                echo "<tr>
                        <td>" . $row['id_barang'] . "</td>
                        <td>" . $row['nama_barang'] . "</td>
                        <td>" . $row['merk'] . "</td>
                        <td>" . $row['tipe'] . "</td>
                        <td>" . $row['keterangan'] . "</td>
                        <td>" . $row['harga'] . "</td>
                        <td>" . $row['jumlah'] . "</td>
                        <td><img src='../crud" . $row['path'] . "' alt='" . $row['nama_barang'] . "' class='img-fluid' style='max-width: 100px; max-height: 100px;'></td>
                        <td>
                            <div class='d-flex justify-content-between'>
                                <a href='editBarang.php?id=" . $row['id_barang'] . "' class='btn btn-primary btn-sm'>Edit</a>
                                <a href='../crud/Delete.php?id=" . $row['id_barang'] . "' class='btn btn-danger btn-sm' onclick=\"return confirm('Are you sure you want to delete this item?');\">Delete</a>
                            </div>
                        </td>
                    </tr>";
            }

            echo "</tbody>
                </table>
                </div>";
        } else {
            // Jika tidak ada data, tampilkan pesan kosong
            echo "<div class='alert alert-warning' role='alert'>Tidak ada data barang.</div>";
        }

        // Tutup koneksi database
        $koneksi->close();
        ?>

        <!-- Navigasi ke halaman input barang dan pembelian -->
        <div class="d-flex justify-content-between mt-3 mb-3">
            <a href="inputBarang.php" class="btn btn-success">Input Barang</a>
            <a href="pembelian.php" class="btn btn-info">Pembelian Barang</a>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>

</html>