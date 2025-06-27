package com.example.shoestore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        // Handle window insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Jordan Air 1 buttons
        Button btnDetailJordan = findViewById(R.id.btndetail_jordan);
        btnDetailJordan.setOnClickListener(v -> openDetail("Jordan Air 1", "5.000.000₫", R.drawable.jordan_air1));

        Button btnBuyJordan = findViewById(R.id.btnbuy_jordan);
        btnBuyJordan.setOnClickListener(v -> {
            Intent it = new Intent(getApplicationContext(), BuyProduct.class);
            it.putExtra("name", "Jordan Air 1");
            it.putExtra("price", "5.000.000₫");
            it.putExtra("image", R.drawable.jordan_air1);
            startActivity(it);
        });

        // Adidas Samba buttons
        Button btnDetailSamba = findViewById(R.id.btndetail_samba);
        btnDetailSamba.setOnClickListener(v -> openDetail("Adidas Samba", "2.500.000₫", R.drawable.adidas_samba));

        Button btnBuySamba = findViewById(R.id.btnbuy_samba);
        btnBuySamba.setOnClickListener(v -> {
            Intent it = new Intent(getApplicationContext(), BuyProduct.class);
            it.putExtra("name", "Adidas Samba");
            it.putExtra("price", "2.500.000₫");
            it.putExtra("image", R.drawable.adidas_samba);
            startActivity(it);
        });

        // Nike Air Force 1 buttons
        Button btnDetailNikeAirForce = findViewById(R.id.btndetail_nike_air_force);
        btnDetailNikeAirForce.setOnClickListener(v -> openDetail("Nike Air Force 1", "3.800.000₫", R.drawable.nike_air_force1));

        Button btnBuyNikeAirForce = findViewById(R.id.btnbuy_nike_air_force);
        btnBuyNikeAirForce.setOnClickListener(v -> {
            Intent it = new Intent(getApplicationContext(), BuyProduct.class);
            it.putExtra("name", "Nike Air Force 1");
            it.putExtra("price", "3.800.000₫");
            it.putExtra("image", R.drawable.nike_air_force1);
            startActivity(it);
        });

        // Nike Air Jordan 1 Retro buttons
        Button btnDetailJordanRetro = findViewById(R.id.btndetail_jordan_retro);
        btnDetailJordanRetro.setOnClickListener(v -> openDetail("Nike Air Jordan 1 Retro", "4.200.000₫", R.drawable.nike_air_jordan));

        Button btnBuyJordanRetro = findViewById(R.id.btnbuy_jordan_retro);
        btnBuyJordanRetro.setOnClickListener(v -> {
            Intent it = new Intent(getApplicationContext(), BuyProduct.class);
            it.putExtra("name", "Nike Air Jordan 1 Retro");
            it.putExtra("price", "4.200.000₫");
            it.putExtra("image", R.drawable.nike_air_jordan);
            startActivity(it);
        });

        // Adidas Campus buttons
        Button btnDetailCampus = findViewById(R.id.btndetail_campus);
        btnDetailCampus.setOnClickListener(v -> openDetail("Adidas Campus", "3.833.000₫", R.drawable.adidas_samba));

        Button btnBuyCampus = findViewById(R.id.btnbuy_campus);
        btnBuyCampus.setOnClickListener(v -> {
            Intent it = new Intent(getApplicationContext(), BuyProduct.class);
            it.putExtra("name", "Adidas Campus");
            it.putExtra("price", "3.833.000₫");
            it.putExtra("image", R.drawable.adidas_samba);
            startActivity(it);
        });

        // Nike Blazer Mid buttons
        Button btnDetailBlazer = findViewById(R.id.btndetail_blazer);
        btnDetailBlazer.setOnClickListener(v -> openDetail("Nike Blazer Mid", "3.050.000₫", R.drawable.nike_blazer_mid));

        Button btnBuyBlazer = findViewById(R.id.btnbuy_blazer);
        btnBuyBlazer.setOnClickListener(v -> {
            Intent it = new Intent(getApplicationContext(), BuyProduct.class);
            it.putExtra("name", "Nike Blazer Mid");
            it.putExtra("price", "3.050.000₫");
            it.putExtra("image", R.drawable.nike_blazer_mid);
            startActivity(it);
        });

        // Cart navigation button
        ImageView navCart = findViewById(R.id.nav_cart);
        navCart.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CartActivity.class);
            startActivity(intent);
        });
    }

    private void openDetail(String name, String price, int imageResId) {
        Intent intent = new Intent(HomeActivity.this, ProductDetail.class);
        intent.putExtra("name", name);
        intent.putExtra("price", price);
        intent.putExtra("image", imageResId);
        startActivity(intent);
    }
}