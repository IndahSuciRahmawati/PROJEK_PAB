package com.projectakhir.betamart.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.projectakhir.betamart.Data.DataItem;
import com.projectakhir.betamart.Data.GetBarangResponse;
import com.projectakhir.betamart.DetailActivity;
import com.projectakhir.betamart.databinding.FragmentHomeBinding;
import com.projectakhir.betamart.AdapterBarang;
import com.projectakhir.betamart.ApiClient;
import com.projectakhir.betamart.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    AdapterBarang adapterBarang;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.rvBarang.setLayoutManager(new GridLayoutManager(getContext(), 2));
        getData();


        return root;
    }

    private void getData() {
        ApiService apiService = ApiClient.getApiService();
        Call<GetBarangResponse> call = apiService.getDataBarang();
        call.enqueue(new Callback<GetBarangResponse>() {
            @Override
            public void onResponse(Call<GetBarangResponse> call, Response<GetBarangResponse> response) {
                GetBarangResponse barangResponse = response.body();
                if (barangResponse != null && barangResponse.getStatus().equals("success")) {
                    List<DataItem> dataItemList = barangResponse.getData();
                    adapterBarang = new AdapterBarang(getContext(), dataItemList);
                    binding.rvBarang.setAdapter(adapterBarang);

                    adapterBarang.SetOnItemClickData(data -> {
                        Intent intent = new Intent(getContext(), DetailActivity.class);
                        intent.putExtra("data", data);
                        startActivity(intent);
                    });
                    Toast.makeText(getContext(), "Berhasil", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("MainActivity", "Response tidak sukses atau data kosong");
                }
            }

            @Override
            public void onFailure(Call<GetBarangResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Gagal " + t, Toast.LENGTH_SHORT).show();
                Log.e("MainActivity", " " + t);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}