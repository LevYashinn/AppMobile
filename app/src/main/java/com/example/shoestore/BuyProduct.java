package com.example.shoestore;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BuyProduct extends AppCompatActivity {

    private int quantity = 1; // Default quantity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buy_product);

        // Handle window insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        ImageView productImage = findViewById(R.id.productImage);
        TextView productName = findViewById(R.id.productName);
        TextView productPrice = findViewById(R.id.productPrice);
        TextView productDesc = findViewById(R.id.productDesc);
        Spinner spinnerColor = findViewById(R.id.spinnerColor);
        Button btnDecrease = findViewById(R.id.btnDecrease);
        TextView quantityText = findViewById(R.id.quantityText);
        Button btnIncrease = findViewById(R.id.btnIncrease);
        Button btnAddToCart = findViewById(R.id.btnAddToCart);
        Button btnBack = findViewById(R.id.btnBack);

        // Set up color spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.colors, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColor.setAdapter(adapter);

        // Get data from intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String price = intent.getStringExtra("price");
        int imageResId = intent.getIntExtra("image", R.drawable.jordan_air1);

        // Set product details
        productImage.setImageResource(imageResId);
        productName.setText(name);
        productPrice.setText(price);

        // Set product-specific description
        switch (name) {
            case "Jordan Air 1":
                productDesc.setText("Jordan Air Force 1 được làm từ da thật và da tổng hợp cao cấp, đế cao su bền bỉ với lớp đệm Air-Sole mang lại sự êm ái tối ưu.");
                break;
            case "Adidas Samba":
                productDesc.setText("Adidas Samba sử dụng chất liệu da lộn mềm mại, đế cao su đặc chống trượt và lót trong êm ái, phù hợp cho cả trong nhà và ngoài trời.");
                break;
            case "Nike Air Force 1":
                productDesc.setText("Nike Air Force 1 được chế tác từ da cao cấp, đế ngoài cao su chắc chắn cùng công nghệ đệm Air-Sole mang lại sự thoải mái và độ bền vượt trội.");
                break;
            case "Nike Air Jordan 1 Retro":
                productDesc.setText("Nike Air Jordan 1 Retro sử dụng chất liệu da cao cấp kết hợp da lộn mềm mại, đế cao su bền chắc và lớp lót trong êm ái, mang đến phong cách cổ điển cùng cảm giác thoải mái tối ưu.");
                break;
            case "Adidas Campus":
                productDesc.setText("Adidas Campus được làm từ chất liệu da lộn cao cấp, đế cao su bền bỉ và lớp lót trong mềm mại, mang lại sự thoải mái và phong cách cổ điển đặc trưng.");
                break;
            case "Nike Blazer Mid":
                productDesc.setText("Nike Blazer Mid được làm từ da tổng hợp và da thật cao cấp, đế cao su chắc chắn cùng phần cổ giày cao ôm sát, mang lại sự thoải mái và hỗ trợ tối ưu.");
                break;
            default:
                productDesc.setText("Sản phẩm mang phong cách thể thao cổ điển, thiết kế tinh tế và chất liệu cao cấp cho trải nghiệm êm ái và bền bỉ mỗi ngày.");
                break;
        }

        // Set initial quantity
        quantityText.setText(String.valueOf(quantity));

        // Quantity controls
        btnDecrease.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                quantityText.setText(String.valueOf(quantity));
            }
        });

        btnIncrease.setOnClickListener(v -> {
            quantity++;
            quantityText.setText(String.valueOf(quantity));
        });

        // Add to cart button
        btnAddToCart.setOnClickListener(v -> {
            String selectedColor = spinnerColor.getSelectedItem() != null ? spinnerColor.getSelectedItem().toString() : "N/A";
            CartItem item = new CartItem(name, price, imageResId, quantity, selectedColor);
            Cart.addItem(item);
            Toast.makeText(this, "Đã thêm " + quantity + " " + name + " vào giỏ hàng", Toast.LENGTH_SHORT).show();
            // Optionally, navigate to CartActivity
            Intent cartIntent = new Intent(this, CartActivity.class);
            startActivity(cartIntent);
        });

        // Back button
        btnBack.setOnClickListener(v -> finish());

        // Action Bar back button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}