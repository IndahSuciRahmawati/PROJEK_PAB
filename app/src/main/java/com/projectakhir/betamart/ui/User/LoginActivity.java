package com.projectakhir.betamart.ui.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.projectakhir.betamart.ApiClient;
import com.projectakhir.betamart.ApiService;
import com.projectakhir.betamart.Data.LoginResponse;
import com.projectakhir.betamart.MainActivity;
import com.projectakhir.betamart.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    TextView register;
    Button login;

    // Nama file SharedPreferences
    private static final String SHARED_PREF_NAME = "bookmart_shared_pref";
    // Key untuk menyimpan ID pengguna di SharedPreferences
    private static final String KEY_USER_ID = "user_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.usernameEditText);
        password = findViewById(R.id.passwordEditText);
        register = findViewById(R.id.registerTextView);
        login = findViewById(R.id.loginButton);

        register.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });

        login.setOnClickListener(v -> {
            String inputUsername = username.getText().toString();
            String inputPassword = password.getText().toString();

            ApiService apiService = ApiClient.getApiService();
            Call<LoginResponse> call = apiService.loginUser(inputUsername, inputPassword);
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    LoginResponse loginResponse = response.body();
                    if (loginResponse.getStatus().equals("success")){

                        // Simpan ID pengguna ke SharedPreferences
                        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt(KEY_USER_ID, loginResponse.getId()); // Simpan ID pengguna
                        editor.apply();

                        Toast.makeText(LoginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                    Log.e("LoginActivity", " "+t);
                }
            });
        });
    }
}