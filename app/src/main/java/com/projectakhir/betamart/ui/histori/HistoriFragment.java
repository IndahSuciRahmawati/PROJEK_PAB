package com.projectakhir.betamart.ui.histori;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.projectakhir.betamart.AdapterPesanan;
import com.projectakhir.betamart.ApiClient;
import com.projectakhir.betamart.ApiService;
import com.projectakhir.betamart.Data.GetPesananResponse;
import com.projectakhir.betamart.Data.PesananItem;
import com.projectakhir.betamart.databinding.FragmentHistoriBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoriFragment extends Fragment {

    private FragmentHistoriBinding binding;

    private AdapterPesanan adapter;
    private List<PesananItem> pemesananList;

    // Nama file SharedPreferences
    private static final String SHARED_PREF_NAME = "bookmart_shared_pref";
    // Key untuk menyimpan ID pengguna di SharedPreferences
    private static final String KEY_USER_ID = "user_id";

    private int id_user = 0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHistoriBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Inisialisasi RecyclerView dan Adapter
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        pemesananList = new ArrayList<>();
        adapter = new AdapterPesanan(getContext(), pemesananList);
        binding.recyclerView.setAdapter(adapter);

        // Ambil ID user dari SharedPreferences
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        id_user = sharedPreferences.getInt(KEY_USER_ID, 0);

        // Ambil data pesanan dari server
        getDataPesanan(id_user);

        return root;
    }

    private void getDataPesanan(int id_user) {
        ApiService apiService = ApiClient.getApiService();
        Call<GetPesananResponse> call = apiService.getPesananBarang(String.valueOf(id_user));
        call.enqueue(new Callback<GetPesananResponse>() {
            @Override
            public void onResponse(Call<GetPesananResponse> call, Response<GetPesananResponse> response) {
                if (response.isSuccessful()) {
                    GetPesananResponse getPesananResponse = response.body();
                    if (getPesananResponse != null && getPesananResponse.getStatus().equals("success")) {
                        // Update data di RecyclerView
                        pemesananList.clear();
                        pemesananList.addAll(getPesananResponse.getData());
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getContext(), "Tidak ada data pesanan", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Gagal mengambil data pesanan", Toast.LENGTH_SHORT).show();
                    Log.e("HistoriFragment", "Response not successful: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<GetPesananResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Data Gagal Diambil", Toast.LENGTH_SHORT).show();
                Log.e("HistoriFragment", "Error fetching data: " + t.getMessage());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
