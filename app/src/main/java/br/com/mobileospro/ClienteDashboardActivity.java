package br.com.mobileospro;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ClienteDashboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_dashboard);

        Button manageVehicles = findViewById(R.id.btnManageVehicles);
        Button checkServiceStatus = findViewById(R.id.btnCheckServiceStatus);
        Button rateService = findViewById(R.id.btnRateService);

        manageVehicles.setOnClickListener(v -> {
            Intent intent = new Intent(ClienteDashboardActivity.this, AddVehicles.class);
            startActivity(intent);
        });

        checkServiceStatus.setOnClickListener(v -> {
            Intent intent = new Intent(ClienteDashboardActivity.this, RatingReportActivity.class);
            startActivity(intent);
        });

        rateService.setOnClickListener(v -> {
            Intent intent = new Intent(ClienteDashboardActivity.this, ReportsActivity.class);
            startActivity(intent);
        });
    }
}

