package com.example.shoestore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnNext = findViewById(R.id.btnLogin);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText objemail = findViewById(R.id.txtemail);
                String txtemail = objemail.getText().toString().trim();

                EditText objPass = findViewById(R.id.txtpass);
                String txtpass = objPass.getText().toString().trim();

                if (txtemail.isEmpty() || txtpass.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập tên người dùng và mật khẩu", Toast.LENGTH_LONG).show();
                    return;
                }

                // Tạo yêu cầu đăng nhập
                LoginRequest loginRequest = new LoginRequest(txtemail, txtpass);
                ApiService apiService = RetrofitClient.getApiService();

                // Gọi API
                apiService.login(loginRequest).enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful() && response.body() != null && response.body().getToken() != null) {
                            // Đăng nhập thành công
                            Intent it = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(it);
                            Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                        } else {
                            // Đăng nhập thất bại
                            Toast.makeText(getApplicationContext(), "Đăng nhập thất bại: Tên người dùng hoặc mật khẩu không đúng", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Lỗi: " + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        Button btnNext1 = findViewById(R.id.btnRegister);
        btnNext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(it);
            }
        });
    }
}
