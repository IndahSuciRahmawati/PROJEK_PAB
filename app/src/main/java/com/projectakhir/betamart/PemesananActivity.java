package com.projectakhir.betamart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.projectakhir.betamart.Data.DataItem;
import com.projectakhir.betamart.Data.InputPesananResponse;

import java.text.NumberFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PemesananActivity extends AppCompatActivity {

    private TextView tvNamaBuku, tvJumlahPesanan, tvTotalPembayaran, tvquantity;
    private EditText etNamaPenerima, etAlamat, etNomorHp;
    private Button btnMinus, btnPlus, btnPesan;

    private DataItem buku;
    private int quantity = 0;

    private int hargaPerBuku, total;

    private static final String SHARED_PREF_NAME = "bookmart_shared_pref";
    // Key untuk menyimpan ID pengguna di SharedPreferences
    private static final String KEY_USER_ID = "user_id";

    private int id_user = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        id_user = sharedPreferences.getInt(KEY_USER_ID, 0);

        // Inisialisasi views
        tvNamaBuku = findViewById(R.id.tv_nama_buku);
        tvJumlahPesanan = findViewById(R.id.tv_jumlah_pesanan);
        tvTotalPembayaran = findViewById(R.id.tv_total_pembayaran);
        etNamaPenerima = findViewById(R.id.et_nama_penerima);
        etAlamat = findViewById(R.id.et_alamat);
        etNomorHp = findViewById(R.id.et_nomor_hp);
        btnMinus = findViewById(R.id.btn_minus);
        btnPlus = findViewById(R.id.btn_plus);
        btnPesan = findViewById(R.id.btn_pesan);
        tvquantity = findViewById(R.id.tv_quantity);

        // Mendapatkan data buku dari Intent
        if (getIntent().hasExtra("BUKU")) {
            buku = getIntent().getParcelableExtra("BUKU");
            if (buku != null) {
                // Menampilkan data buku ke UI
                tvNamaBuku.setText(buku.getNamaBarang());
                updateTotalPembayaran();

                // Listener untuk tombol plus dan minus
                btnMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        decrementQuantity();
                    }
                });

                btnPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        incrementQuantity();
                    }
                });

                // Listener untuk tombol pesan
                btnPesan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pesanBuku();
                    }
                });
            }
        }
    }

    // Method untuk mengurangi jumlah pesanan
    public void decrementQuantity() {
        if (quantity > 0) {
            quantity--;
            tvJumlahPesanan.setText("Jumlah Pesanan: " + quantity);
            tvquantity.setText(String.valueOf(quantity));
            updateTotalPembayaran();
        }
    }

    // Method untuk menambah jumlah pesanan
    public void incrementQuantity() {
        quantity++;
        tvJumlahPesanan.setText("Jumlah Pesanan: " + quantity);
        tvquantity.setText(String.valueOf(quantity));
        updateTotalPembayaran();
    }

    // Method untuk menghitung dan menampilkan total pembayaran
    private void updateTotalPembayaran() {
        hargaPerBuku = Integer.parseInt(buku.getHarga());
        total = quantity * hargaPerBuku;
        tvTotalPembayaran.setText("Total Pembayaran: Rp " + formatUang(total));

    }

    // Method untuk menangani proses pesanan buku
    public void pesanBuku() {
        String namapenerima = etNamaPenerima.getText().toString();
        String alamat = etAlamat.getText().toString();
        String hp = etNomorHp.getText().toString();

        ApiService apiService = ApiClient.getApiService();
        Call<InputPesananResponse> call = apiService.pesanBarang(buku.getIdBarang(), namapenerima, quantity, alamat, hp, id_user);
        call.enqueue(new Callback<InputPesananResponse>() {
            
            @Override
            public void onResponse(Call<InputPesananResponse> call, Response<InputPesananResponse> response) {
                Toast.makeText(PemesananActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                InputPesananResponse inputPesananResponse = response.body();
                if (inputPesananResponse.getStatus().equals("success")){
                    Toast.makeText(PemesananActivity.this, "Pemesanan Berhasil", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PemesananActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<InputPesananResponse> call, Throwable t) {
                Toast.makeText(PemesananActivity.this, "Pesanan Gagal Dibuat", Toast.LENGTH_SHORT).show();
                Log.e("PesananActivity", " "+t);
            }
        });
    }

    private String formatUang(int number) {
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }
}
