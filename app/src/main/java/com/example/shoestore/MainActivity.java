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
                String txtemail = objemail.getText().toString();

                EditText objPass = findViewById(R.id.txtpass);
                String txtpass = objPass.getText().toString();

                if(txtemail.equals("phuc") && txtpass.equals("123"))
                {
                    Intent it = new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(it);
                    Toast.makeText(getApplicationContext(),"login thanh cong",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"login fail",Toast.LENGTH_LONG).show();
                }
            }
        });
        Button btnNext1 = findViewById(R.id.btnRegister);
        btnNext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(it);
            }
        });
    }
}
