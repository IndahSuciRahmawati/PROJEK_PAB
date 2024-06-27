package com.projectakhir.betamart.Data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DataItem implements Parcelable {

	@SerializedName("path")
	private String path;

	@SerializedName("merk")
	private String merk;

	@SerializedName("keterangan")
	private String keterangan;

	@SerializedName("harga")
	private String harga;

	@SerializedName("jumlah")
	private String jumlah;

	@SerializedName("id_barang")
	private String idBarang;

	@SerializedName("nama_barang")
	private String namaBarang;

	@SerializedName("tipe")
	private String tipe;

	@SerializedName("gambar")
	private String gambar;

	protected DataItem(Parcel in) {
		path = in.readString();
		merk = in.readString();
		keterangan = in.readString();
		harga = in.readString();
		jumlah = in.readString();
		idBarang = in.readString();
		namaBarang = in.readString();
		tipe = in.readString();
		gambar = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(path);
		dest.writeString(merk);
		dest.writeString(keterangan);
		dest.writeString(harga);
		dest.writeString(jumlah);
		dest.writeString(idBarang);
		dest.writeString(namaBarang);
		dest.writeString(tipe);
		dest.writeString(gambar);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<DataItem> CREATOR = new Creator<DataItem>() {
		@Override
		public DataItem createFromParcel(Parcel in) {
			return new DataItem(in);
		}

		@Override
		public DataItem[] newArray(int size) {
			return new DataItem[size];
		}
	};

	public String getPath(){
		return path;
	}

	public String getMerk(){
		return merk;
	}

	public String getKeterangan(){
		return keterangan;
	}

	public String getHarga(){
		return harga;
	}

	public String getJumlah(){
		return jumlah;
	}

	public String getIdBarang(){
		return idBarang;
	}

	public String getNamaBarang(){
		return namaBarang;
	}

	public String getTipe(){
		return tipe;
	}

	public String getGambar(){
		return gambar;
	}
}