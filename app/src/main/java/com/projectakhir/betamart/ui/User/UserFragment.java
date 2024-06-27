package com.projectakhir.betamart.ui.User;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.projectakhir.betamart.ApiClient;
import com.projectakhir.betamart.ApiService;
import com.projectakhir.betamart.Data.DataUser;
import com.projectakhir.betamart.Data.GetUserResponse;
import com.projectakhir.betamart.Data.UpdateUserResponse;
import com.projectakhir.betamart.R;
import com.projectakhir.betamart.databinding.FragmentUserBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserFragment extends Fragment {

    private FragmentUserBinding binding;

    private static final String SHARED_PREF_NAME = "bookmart_shared_pref";
    private static final String KEY_USER_ID = "user_id";
    private int id_user = 0;

    private String username, email, password;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentUserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        SharedPreferences sharedPreferences = getContext().getSharedPreferences(SHARED_PREF_NAME, getContext().MODE_PRIVATE);
        id_user = sharedPreferences.getInt(KEY_USER_ID, 0);

        binding.Logout.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            getActivity().finish();
        });

        GetUser(id_user);

        binding.updateData.setOnClickListener(v -> {
            showAddUserDialog();
        });

        return root;
    }

    private void GetUser(int id_user) {
        ApiService apiService = ApiClient.getApiService();
        Call<GetUserResponse> call = apiService.getUserById(id_user);
        call.enqueue(new Callback<GetUserResponse>() {
            @Override
            public void onResponse(Call<GetUserResponse> call, Response<GetUserResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    GetUserResponse getUserResponse = response.body();
                    if ("success".equals(getUserResponse.getStatus())) {
                        DataUser dataUser = getUserResponse.getData();
                        if (dataUser != null) {
                            username = dataUser.getUsername();
                            email = dataUser.getEmail();
                            password = dataUser.getPassword(); // Pastikan password juga diambil

                            // Pastikan binding sudah diinisialisasi
                            if (binding != null) {
                                binding.username.setText(username);
                                binding.email.setText(email);
                            }
                        }
                    } else {
                        // Tangani jika status bukan success
                        Log.e("GetUser", "Failed to get user: ");
                    }
                } else {
                    // Tangani jika response tidak berhasil
                    Log.e("GetUser", "Response unsuccessful or body is null");
                }
            }

            @Override
            public void onFailure(Call<GetUserResponse> call, Throwable t) {
                // Tangani kegagalan jaringan
                Log.e("GetUser", "Network request failed", t);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void showAddUserDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Update User");

        View view = getLayoutInflater().inflate(R.layout.update_user, null);
        final EditText editTextId = view.findViewById(R.id.editTextId);
        final EditText editTextUsername = view.findViewById(R.id.editTextUsername);
        final EditText editTextEmail = view.findViewById(R.id.editTextEmail);
        final EditText editTextPassword = view.findViewById(R.id.editTextPassword);

        editTextUsername.setText(username);
        editTextEmail.setText(email);
        editTextPassword.setText(password);
        editTextId.setText(String.valueOf(id_user));

        builder.setView(view);
        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String id = editTextId.getText().toString().trim();
                String name = editTextUsername.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                // Validasi input
                if (id.isEmpty() || name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                    return;
                }

                updateUser(Integer.parseInt(id), name, password, email);
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.create().show();
    }

    private void updateUser(int id, String name, String password, String email) {
        ApiService apiService = ApiClient.getApiService();
        Call<UpdateUserResponse> call = apiService.updateUser(id, name, password, email, "2");
        call.enqueue(new Callback<UpdateUserResponse>() {
            @Override
            public void onResponse(Call<UpdateUserResponse> call, Response<UpdateUserResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    UpdateUserResponse updateUserResponse = response.body();
                    if ("success".equals(updateUserResponse.getStatus())) {
                        GetUser(id_user);
                        Toast.makeText(getContext(), "User Berhasil Diupdate", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Gagal Update: " + updateUserResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Gagal Update", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UpdateUserResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
