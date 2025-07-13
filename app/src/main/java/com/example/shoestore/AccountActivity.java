package com.example.shoestore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class AccountActivity extends AppCompatActivity {

    private TextView tvUsername, tvEmail;
    private Button btnEditProfile, btnChangePassword, btnViewOrders, btnLogout;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_account);

        // Handle window insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        tvUsername = findViewById(R.id.tv_username);
        tvEmail = findViewById(R.id.tv_email);
        btnEditProfile = findViewById(R.id.btn_edit_profile);
        btnChangePassword = findViewById(R.id.btn_change_password);
        btnViewOrders = findViewById(R.id.btn_view_orders);
        btnLogout = findViewById(R.id.btn_logout);

        // Initialize Volley request queue
        requestQueue = Volley.newRequestQueue(this);

        // Call API to fetch user data (giả sử lấy user với ID = 1)
        fetchUserData(1);

        // Button listeners
        btnEditProfile.setOnClickListener(v -> {
            Toast.makeText(this, "Chỉnh sửa hồ sơ", Toast.LENGTH_SHORT).show();
            // Thêm Intent đến màn hình chỉnh sửa hồ sơ nếu cần
        });

        btnChangePassword.setOnClickListener(v -> {
            Toast.makeText(this, "Đổi mật khẩu", Toast.LENGTH_SHORT).show();
            // Thêm Intent đến màn hình đổi mật khẩu nếu cần
        });

        btnViewOrders.setOnClickListener(v -> {
            Toast.makeText(this, "Xem đơn hàng", Toast.LENGTH_SHORT).show();
            // Thêm Intent đến màn hình xem đơn hàng nếu cần
        });

        btnLogout.setOnClickListener(v -> {
            Toast.makeText(this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AccountActivity.this, MainActivity.class); // Thay bằng MainActivity
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }

    private void fetchUserData(int userId) {
        String url = "https://fakestoreapi.com/users/" + userId;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Lấy thông tin từ JSON
                            JSONObject nameObj = response.getJSONObject("name");
                            String fullName = nameObj.getString("firstname") + " " + nameObj.getString("lastname");
                            String email = response.getString("email");

                            // Hiển thị thông tin lên TextView
                            tvUsername.setText(fullName);
                            tvEmail.setText(email);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(AccountActivity.this, "Lỗi khi phân tích dữ liệu", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AccountActivity.this, "Lỗi khi gọi API: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // Thêm request vào queue
        requestQueue.add(jsonObjectRequest);
    }
}