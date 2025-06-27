package com.example.shoestore;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.text.NumberFormat;
import java.util.Locale;

public class CheckoutActivity extends AppCompatActivity {

    private CartAdapter adapter; // Declare adapter as instance variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_checkout);

        // Handle window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set up Action Bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        // Initialize views
        RecyclerView recyclerView = findViewById(R.id.recyclerViewCheckout);
        TextView totalPrice = findViewById(R.id.totalPrice);
        EditText editName = findViewById(R.id.editName);
        EditText editAddress = findViewById(R.id.editAddress);
        EditText editPhone = findViewById(R.id.editPhone);
        Spinner spinnerPayment = findViewById(R.id.spinnerPayment);
        Button btnConfirmOrder = findViewById(R.id.btnConfirmOrder);

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CartAdapter(Cart.getCartItems(), this::handleItemRemoval);
        recyclerView.setAdapter(adapter);

        // Set up payment method spinner
        ArrayAdapter<CharSequence> paymentAdapter = ArrayAdapter.createFromResource(this,
                R.array.payment_methods, android.R.layout.simple_spinner_item);
        paymentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPayment.setAdapter(paymentAdapter);

        // Calculate and display total price
        updateTotalPrice(totalPrice);

        // Confirm order button
        btnConfirmOrder.setOnClickListener(v -> {
            String name = editName.getText().toString().trim();
            String address = editAddress.getText().toString().trim();
            String phone = editPhone.getText().toString().trim();
            String paymentMethod = spinnerPayment.getSelectedItem() != null ? spinnerPayment.getSelectedItem().toString() : "";

            if (name.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin giao hàng", Toast.LENGTH_SHORT).show();
                return;
            }

            if (Cart.getCartItems().isEmpty()) {
                Toast.makeText(this, "Giỏ hàng trống", Toast.LENGTH_SHORT).show();
                return;
            }

            // Simulate order confirmation
            Cart.clearCart();
            adapter.notifyDataSetChanged();
            updateTotalPrice(totalPrice);
            Toast.makeText(this, "Đơn hàng đã được xác nhận!", Toast.LENGTH_LONG).show();
            finish(); // Return to previous screen
        });
    }

    private void handleItemRemoval(int position) {
        Cart.removeItem(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position, adapter.getItemCount());
        TextView totalPrice = findViewById(R.id.totalPrice);
        updateTotalPrice(totalPrice);
        Toast.makeText(this, "Đã xóa sản phẩm khỏi giỏ hàng", Toast.LENGTH_SHORT).show();
    }

    private void updateTotalPrice(TextView totalPriceView) {
        double total = 0;
        for (CartItem item : Cart.getCartItems()) {
            String priceStr = item.getPrice().replace("₫", "").replace(".", "").trim();
            try {
                double price = Double.parseDouble(priceStr);
                total += price * item.getQuantity();
            } catch (NumberFormatException e) {
                // Handle invalid price format
            }
        }
        NumberFormat formatter = NumberFormat.getInstance(new Locale("vi", "VN"));
        totalPriceView.setText("Tổng: " + formatter.format(total) + "₫");
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