package com.projectakhir.betamart.ui.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.projectakhir.betamart.ApiClient;
import com.projectakhir.betamart.ApiService;
import com.projectakhir.betamart.Data.RegisterResponse;
import com.projectakhir.betamart.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterActivity extends AppCompatActivity {

    EditText username, email, password;
    Button registerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.usernameEditText);
        password = findViewById(R.id.passwordEditText);
        email = findViewById(R.id.emailEditText);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(v -> {
            String inputUsername = username.getText().toString();
            String inputPassword = password.getText().toString();
            String inputEmail = email.getText().toString();

            ApiService apiService = ApiClient.getApiService();
            Call<RegisterResponse> call = apiService.registerUser(inputUsername, inputEmail, inputPassword);
            call.enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                    RegisterResponse registerResponse = response.body();
                    if (registerResponse.getStatus().equals("success")){
                        Toast.makeText(RegisterActivity.this, "Register Berhasil", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "Register Gagal", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RegisterResponse> call, Throwable t) {
                    Toast.makeText(RegisterActivity.this, "Error "+t, Toast.LENGTH_SHORT).show();
                    Log.e("RegisterActivity", " "+t);
                }
            });
        });
    }
}