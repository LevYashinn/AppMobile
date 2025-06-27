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

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button back = findViewById(R.id.btnRegister1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText objemail1 = findViewById(R.id.txtemail1);
                String txtemail1 = objemail1.getText().toString().trim();

                EditText objpass1 = findViewById(R.id.txtpass1);
                String txtpass1 = objpass1.getText().toString().trim();

                if (txtemail1.isEmpty() || txtpass1.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Đăng ký thất bại: Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
                } else {
                    Intent it = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(it);
                    Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
