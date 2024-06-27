package com.projectakhir.betamart;

import static com.projectakhir.betamart.ApiClient.BASE_URL;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.projectakhir.betamart.Data.PesananItem;

import java.util.List;

public class AdapterPesanan extends RecyclerView.Adapter<AdapterPesanan.PemesananViewHolder> {

    private Context context;
    private List<PesananItem> pemesananList;

    public AdapterPesanan(Context context, List<PesananItem> pemesananList) {
        this.context = context;
        this.pemesananList = pemesananList;
    }

    @NonNull
    @Override
    public PemesananViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pemesanan, parent, false);
        return new PemesananViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PemesananViewHolder holder, int position) {
        PesananItem pemesanan = pemesananList.get(position);

        holder.namaBarangTextView.setText(pemesanan.getNamaBarang());
        holder.merkTextView.setText("Merk: " + pemesanan.getMerk());
        holder.tipeTextView.setText("Tipe: " + pemesanan.getTipe());
        holder.jumlahPesananTextView.setText("Jumlah: " + pemesanan.getJumlahPesanan());
        holder.alamatTextView.setText("Alamat: " + pemesanan.getAlamat());
        holder.nomorHpTextView.setText("Nomor HP: " + pemesanan.getNomorHp());
        holder.totalBayarTextView.setText("Total Bayar: " + pemesanan.getTotalBayar());
        holder.tanggalPemesananTextView.setText("Tanggal Pemesanan: " + pemesanan.getTanggalPemesanan());

        Glide.with(context)
                .load(BASE_URL+pemesanan.getGambar())
                .into(holder.gambarBarangImageView);
    }

    @Override
    public int getItemCount() {
        return pemesananList.size();
    }

    public static class PemesananViewHolder extends RecyclerView.ViewHolder {

        TextView namaBarangTextView, merkTextView, tipeTextView, jumlahPesananTextView,
                alamatTextView, nomorHpTextView, totalBayarTextView, tanggalPemesananTextView;
        ImageView gambarBarangImageView;

        public PemesananViewHolder(@NonNull View itemView) {
            super(itemView);

            namaBarangTextView = itemView.findViewById(R.id.nama_barang);
            merkTextView = itemView.findViewById(R.id.merk);
            tipeTextView = itemView.findViewById(R.id.tipe);
            jumlahPesananTextView = itemView.findViewById(R.id.jumlah_pesanan);
            alamatTextView = itemView.findViewById(R.id.alamat);
            nomorHpTextView = itemView.findViewById(R.id.nomor_hp);
            totalBayarTextView = itemView.findViewById(R.id.total_bayar);
            tanggalPemesananTextView = itemView.findViewById(R.id.tanggal_pemesanan);
            gambarBarangImageView = itemView.findViewById(R.id.image_barang);
        }
    }
}
