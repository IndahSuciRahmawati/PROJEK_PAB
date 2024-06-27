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
import com.projectakhir.betamart.Data.DataItem;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class AdapterBarang extends RecyclerView.Adapter<AdapterBarang.ViewHolder> {
    private List<DataItem> dataItems;
    private Context context;
    private OnItemClickData onItemClickData;

    public void SetOnItemClickData(OnItemClickData onItemClickData) {
        this.onItemClickData = onItemClickData;
    }

    public AdapterBarang(Context context, List<DataItem> list) {
        this.context = context;
        this.dataItems = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataItem dataItem = dataItems.get(position);
        Glide.with(context)
                .load(BASE_URL+dataItem.getPath())
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error_image)
                .into(holder.imgBuku);

        holder.tvNamaBuku.setText(dataItem.getNamaBarang());
        int format = Integer.parseInt(dataItem.getHarga());
        holder.tvHarga.setText(formatUang(format));
        holder.tvJumlah.setText("Stok: "+dataItem.getJumlah());

        holder.itemView.setOnClickListener(v -> {
            onItemClickData.OnItemClicked(dataItems.get(holder.getAdapterPosition()));
        });

    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgBuku;
        TextView tvNamaBuku, tvHarga, tvJumlah;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBuku = itemView.findViewById(R.id.barangImageView);
            tvNamaBuku = itemView.findViewById(R.id.barangNameTextView);
            tvHarga = itemView.findViewById(R.id.priceTextView);
            tvJumlah = itemView.findViewById(R.id.stockTextView);
        }
    }
    public interface OnItemClickData{
        void OnItemClicked(DataItem dataItem);
    }

    private String formatUang(int number) {
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }
}
