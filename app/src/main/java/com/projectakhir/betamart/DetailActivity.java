package com.projectakhir.betamart;

import static com.projectakhir.betamart.ApiClient.BASE_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.projectakhir.betamart.Data.DataItem;

import java.text.NumberFormat;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    private ImageView imgDetail;
    private TextView tvNamaBuku, tvHarga, tvStok, tvDeskripsi, tvPengarang, tvPenerbit;
    private Button btnPesan;

    private DataItem buku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Inisialisasi views
        imgDetail = findViewById(R.id.img_detail);
        tvNamaBuku = findViewById(R.id.tv_nama_buku);
        tvHarga = findViewById(R.id.tv_harga);
        tvStok = findViewById(R.id.tv_stok);
        tvDeskripsi = findViewById(R.id.tv_deskripsi);
        tvPenerbit = findViewById(R.id.tv_merk);
        tvPengarang = findViewById(R.id.tv_tipe);
        btnPesan = findViewById(R.id.btn_pesan);

        // Mendapatkan data buku dari Intent
        if (getIntent().hasExtra("data")) {
            buku = getIntent().getParcelableExtra("data");
            int harga = Integer.parseInt(buku.getHarga());
            if (buku != null) {
                // Menampilkan data buku ke UI
                Glide.with(this)
                        .load(BASE_URL+buku.getPath())
                        .placeholder(R.drawable.placeholder_image)
                        .error(R.drawable.error_image)
                        .into(imgDetail);

                tvNamaBuku.setText(buku.getNamaBarang());
                tvHarga.setText(formatUang(harga));
                tvStok.setText("Stok: " + buku.getJumlah());
                tvDeskripsi.setText(buku.getKeterangan());
                tvPengarang.setText("Merk: "+buku.getMerk());
                tvPenerbit.setText("Tipe: "+buku.getTipe());
            }
        }

        // Listener untuk tombol pesan
        btnPesan.setOnClickListener(v -> {
            // Intent untuk memulai Activity PemesananBuku
            Intent intent = new Intent(DetailActivity.this, PemesananActivity.class);
            // Mengirim data buku ke activity pemesanan buku
            intent.putExtra("BUKU", buku);
            startActivity(intent);
        });
    }

    private String formatUang(int number) {
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }
}