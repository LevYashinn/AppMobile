package com.example.shoestore;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProductDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

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
        RatingBar productRating = findViewById(R.id.productRating);
        TextView productSpecs = findViewById(R.id.productSpecs);
        TextView productDescription = findViewById(R.id.productDescription);
        Button btnAddToCart = findViewById(R.id.btnAddToCart);
        Button btnBuyNow = findViewById(R.id.btnBuyNow);
        Button btnBack = findViewById(R.id.btnBack);

        // Get data from intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String price = intent.getStringExtra("price");
        int imageResId = intent.getIntExtra("image", R.drawable.jordan_air1);

        // Set common data to views
        productImage.setImageResource(imageResId);
        productName.setText(name);
        productPrice.setText(price);
        productRating.setRating(4.5f); // Default rating, can be customized per product

        // Set product-specific specs and description
        switch (name) {
            case "Jordan Air 1":
                productSpecs.setText("• Thông số: Chất liệu da thật và da tổng hợp cao cấp, đế cao su bền bỉ, chiều cao đế ~3cm, form chuẩn US/EU, size từ 36 đến 45.");
                productDescription.setText("Jordan Air Force 1 mang phong cách thể thao cổ điển, thiết kế tinh tế và chất liệu cao cấp cho trải nghiệm êm ái và bền bỉ mỗi ngày.");
                break;
            case "Adidas Samba":
                productSpecs.setText("• Thông số: Chất liệu da lộn mềm mại, đế cao su đặc chống trượt, form chuẩn US/EU, size từ 36 đến 44.");
                productDescription.setText("Adidas Samba sử dụng chất liệu da lộn mềm mại, đế cao su đặc chống trượt và lót trong êm ái, phù hợp cho cả trong nhà và ngoài trời.");
                break;
            case "Nike Air Force 1":
                productSpecs.setText("• Thông số: Chất liệu da cao cấp, đế cao su chắc chắn, công nghệ đệm Air-Sole, form chuẩn US/EU, size từ 36 đến 45.");
                productDescription.setText("Nike Air Force 1 được chế tác từ da cao cấp, đế ngoài cao su chắc chắn cùng công nghệ đệm Air-Sole mang lại sự thoải mái và độ bền vượt trội.");
                break;
            case "Nike Air Jordan 1 Retro":
                productSpecs.setText("• Thông số: Chất liệu da cao cấp kết hợp da lộn, đế cao su bền chắc, form chuẩn US/EU, size từ 36 đến 45.");
                productDescription.setText("Nike Air Jordan 1 Retro sử dụng chất liệu da cao cấp kết hợp da lộn mềm mại, đế cao su bền chắc và lớp lót trong êm ái, mang đến phong cách cổ điển cùng cảm giác thoải mái tối ưu.");
                break;
            case "Adidas Campus":
                productSpecs.setText("• Thông số: Chất liệu da lộn cao cấp, đế cao su bền bỉ, form chuẩn US/EU, size từ 36 đến 44.");
                productDescription.setText("Adidas Campus được làm từ chất liệu da lộn cao cấp, đế cao su bền bỉ và lớp lót trong mềm mại, mang lại sự thoải mái và phong cách cổ điển đặc trưng.");
                break;
            case "Nike Blazer Mid":
                productSpecs.setText("• Thông số: Chất liệu da tổng hợp và da thật cao cấp, đế cao su chắc chắn, phần cổ giày cao, form chuẩn US/EU, size từ 36 đến 45.");
                productDescription.setText("Nike Blazer Mid được làm từ da tổng hợp và da thật cao cấp, đế cao su chắc chắn cùng phần cổ giày cao ôm sát, mang lại sự thoải mái và hỗ trợ tối ưu.");
                break;
            default:
                productSpecs.setText("• Thông số: Chất liệu cao cấp, đế bền bỉ, form chuẩn US/EU, size từ 36 đến 45.");
                productDescription.setText("Sản phẩm mang phong cách thể thao cổ điển, thiết kế tinh tế và chất liệu cao cấp cho trải nghiệm êm ái và bền bỉ mỗi ngày.");
                break;
        }

        // Button click listeners
        btnAddToCart.setOnClickListener(v -> {
            // TODO: Implement add to cart functionality
            // Example: Add product to a cart database or list
        });

        btnBuyNow.setOnClickListener(v -> {
            Intent buyIntent = new Intent(ProductDetail.this, BuyProduct.class);
            buyIntent.putExtra("name", name);
            buyIntent.putExtra("price", price);
            buyIntent.putExtra("image", imageResId);
            startActivity(buyIntent);
        });

        btnBack.setOnClickListener(v -> finish());
    }
}