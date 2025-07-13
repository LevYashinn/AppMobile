package com.example.shoestore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    private EditText searchEditText;

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

        // Initialize search view
        searchEditText = findViewById(R.id.search_bar).findViewById(android.R.id.edit); // Lấy EditText bên trong CardView
        searchEditText.setOnEditorActionListener((v, actionId, event) -> {
            performSearch();
            return true;
        });

        // Sản phẩm nổi bật: Jordan Air 1
        Button btnBuyJordan = findViewById(R.id.btnbuy_jordan);
        btnBuyJordan.setOnClickListener(v -> {
            Intent it = new Intent(getApplicationContext(), BuyProduct.class);
            it.putExtra("name", "Jordan Air 1");
            it.putExtra("price", "5.000.000₫");
            it.putExtra("image", R.drawable.jordan_air1);
            startActivity(it);
        });

        Button btnDetailJordan = findViewById(R.id.btndetail_jordan);
        btnDetailJordan.setOnClickListener(v -> openDetail("Jordan Air 1", "5.000.000₫", R.drawable.jordan_air1));

        // Sản phẩm nổi bật: Adidas Samba
        Button btnBuySamba = findViewById(R.id.btnbuy_samba);
        btnBuySamba.setOnClickListener(v -> {
            Intent it = new Intent(getApplicationContext(), BuyProduct.class);
            it.putExtra("name", "Adidas Samba");
            it.putExtra("price", "2.500.000₫");
            it.putExtra("image", R.drawable.adidas_samba);
            startActivity(it);
        });

        Button btnDetailSamba = findViewById(R.id.btndetail_samba);
        btnDetailSamba.setOnClickListener(v -> openDetail("Adidas Samba", "2.500.000₫", R.drawable.adidas_samba));

        // Sản phẩm nổi bật: Nike Air Force 1
        Button btnBuyNikeAirForce = findViewById(R.id.btnbuy_nike_air_force);
        btnBuyNikeAirForce.setOnClickListener(v -> {
            Intent it = new Intent(getApplicationContext(), BuyProduct.class);
            it.putExtra("name", "Nike Air Force 1");
            it.putExtra("price", "3.800.000₫");
            it.putExtra("image", R.drawable.nike_air_force1);
            startActivity(it);
        });

        Button btnDetailNikeAirForce = findViewById(R.id.btndetail_nike_air_force);
        btnDetailNikeAirForce.setOnClickListener(v -> openDetail("Nike Air Force 1", "3.800.000₫", R.drawable.nike_air_force1));

        // Sản phẩm nổi bật: Nike Air Jordan 1 Retro
        Button btnBuyJordanRetro = findViewById(R.id.btnbuy_jordan_retro);
        btnBuyJordanRetro.setOnClickListener(v -> {
            Intent it = new Intent(getApplicationContext(), BuyProduct.class);
            it.putExtra("name", "Nike Air Jordan 1 Retro");
            it.putExtra("price", "4.200.000₫");
            it.putExtra("image", R.drawable.nike_air_jordan);
            startActivity(it);
        });

        Button btnDetailJordanRetro = findViewById(R.id.btndetail_jordan_retro);
        btnDetailJordanRetro.setOnClickListener(v -> openDetail("Nike Air Jordan 1 Retro", "4.200.000₫", R.drawable.nike_air_jordan));

        // Sản phẩm nổi bật: Adidas Campus
        Button btnBuyCampus = findViewById(R.id.btnbuy_campus);
        btnBuyCampus.setOnClickListener(v -> {
            Intent it = new Intent(getApplicationContext(), BuyProduct.class);
            it.putExtra("name", "Adidas Campus");
            it.putExtra("price", "3.833.000₫");
            it.putExtra("image", R.drawable.adidas_samba);
            startActivity(it);
        });

        Button btnDetailCampus = findViewById(R.id.btndetail_campus);
        btnDetailCampus.setOnClickListener(v -> openDetail("Adidas Campus", "3.833.000₫", R.drawable.adidas_samba));

        // Sản phẩm nổi bật: Nike Blazer Mid
        Button btnBuyBlazer = findViewById(R.id.btnbuy_blazer);
        btnBuyBlazer.setOnClickListener(v -> {
            Intent it = new Intent(getApplicationContext(), BuyProduct.class);
            it.putExtra("name", "Nike Blazer Mid");
            it.putExtra("price", "3.050.000₫");
            it.putExtra("image", R.drawable.nike_blazer_mid);
            startActivity(it);
        });

        Button btnDetailBlazer = findViewById(R.id.btndetail_blazer);
        btnDetailBlazer.setOnClickListener(v -> openDetail("Nike Blazer Mid", "3.050.000₫", R.drawable.nike_blazer_mid));

        // Thanh điều hướng dưới
        ImageView navCart = findViewById(R.id.nav_cart);
        navCart.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CartActivity.class);
            startActivity(intent);
        });

        // Các nút khác trong bottom_nav (giả định hành động tạm thời)
        ImageView navHome = findViewById(R.id.nav_home);
        navHome.setOnClickListener(v -> Toast.makeText(this, "Trang chủ", Toast.LENGTH_SHORT).show());

        ImageView navBrands = findViewById(R.id.nav_brands);
        navBrands.setOnClickListener(v -> Toast.makeText(this, "Nhãn hiệu", Toast.LENGTH_SHORT).show());

        ImageView navHistory = findViewById(R.id.nav_history);
        navHistory.setOnClickListener(v -> Toast.makeText(this, "Lịch sử", Toast.LENGTH_SHORT).show());

        ImageView navAccount = findViewById(R.id.nav_account);
        navAccount.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, AccountActivity.class); // Điều hướng đến AccountActivity
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

    private void performSearch() {
        String query = searchEditText.getText().toString().trim().toLowerCase();
        if (query.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập từ khóa tìm kiếm!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Danh sách sản phẩm cố định dựa trên các CardView
        String[][] products = {
                {"Jordan Air 1", "5.000.000₫", String.valueOf(R.drawable.jordan_air1)},
                {"Adidas Samba", "2.500.000₫", String.valueOf(R.drawable.adidas_samba)},
                {"Nike Air Force 1", "3.800.000₫", String.valueOf(R.drawable.nike_air_force1)},
                {"Nike Air Jordan 1 Retro", "4.200.000₫", String.valueOf(R.drawable.nike_air_jordan)},
                {"Adidas Campus", "3.833.000₫", String.valueOf(R.drawable.adidas_samba)},
                {"Nike Blazer Mid", "3.050.000₫", String.valueOf(R.drawable.nike_blazer_mid)}
        };

        boolean found = false;
        for (String[] product : products) {
            if (product[0].toLowerCase().contains(query)) {
                openDetail(product[0], product[1], Integer.parseInt(product[2]));
                found = true;
                break;
            }
        }

        if (!found) {
            Toast.makeText(this, "Không tìm thấy sản phẩm!", Toast.LENGTH_SHORT).show();
        }
    }
}