package com.projectakhir.betamart.Data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class PesananItem implements Parcelable {

    @SerializedName("id_pemesanan")
    private int idPemesanan;

    @SerializedName("nama_barang")
    private String namaBarang;

    @SerializedName("merk")
    private String merk;

    @SerializedName("tipe")
    private String tipe;

    @SerializedName("gambar")
    private String gambar;

    @SerializedName("nama_penerima")
    private String namaPenerima;

    @SerializedName("jumlah_pesanan")
    private int jumlahPesanan;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("nomor_hp")
    private String nomorHp;

    @SerializedName("total_bayar")
    private int totalBayar;

    @SerializedName("tanggal_pemesanan")
    private String tanggalPemesanan;

    public PesananItem(String gambar) {
        this.gambar = gambar;
    }

    protected PesananItem(Parcel in) {
        idPemesanan = in.readInt();
        namaBarang = in.readString();
        merk = in.readString();
        tipe = in.readString();
        gambar = in.readString();
        namaPenerima = in.readString();
        jumlahPesanan = in.readInt();
        alamat = in.readString();
        nomorHp = in.readString();
        totalBayar = in.readInt();
        tanggalPemesanan = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idPemesanan);
        dest.writeString(namaBarang);
        dest.writeString(merk);
        dest.writeString(tipe);
        dest.writeString(gambar);
        dest.writeString(namaPenerima);
        dest.writeInt(jumlahPesanan);
        dest.writeString(alamat);
        dest.writeString(nomorHp);
        dest.writeInt(totalBayar);
        dest.writeString(tanggalPemesanan);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PesananItem> CREATOR = new Creator<PesananItem>() {
        @Override
        public PesananItem createFromParcel(Parcel in) {
            return new PesananItem(in);
        }

        @Override
        public PesananItem[] newArray(int size) {
            return new PesananItem[size];
        }
    };

    // Buat getter dan setter sesuai kebutuhan
    public int getIdPemesanan() {
        return idPemesanan;
    }

    public void setIdPemesanan(int idPemesanan) {
        this.idPemesanan = idPemesanan;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getNamaPenerima() {
        return namaPenerima;
    }

    public void setNamaPenerima(String namaPenerima) {
        this.namaPenerima = namaPenerima;
    }

    public int getJumlahPesanan() {
        return jumlahPesanan;
    }

    public void setJumlahPesanan(int jumlahPesanan) {
        this.jumlahPesanan = jumlahPesanan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNomorHp() {
        return nomorHp;
    }

    public void setNomorHp(String nomorHp) {
        this.nomorHp = nomorHp;
    }

    public int getTotalBayar() {
        return totalBayar;
    }

    public void setTotalBayar(int totalBayar) {
        this.totalBayar = totalBayar;
    }

    public String getTanggalPemesanan() {
        return tanggalPemesanan;
    }

    public void setTanggalPemesanan(String tanggalPemesanan) {
        this.tanggalPemesanan = tanggalPemesanan;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }
}
