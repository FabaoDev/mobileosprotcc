package br.com.mobileospro;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class FuncionarioDashboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funcionario_dashboard);

        Button manageClients = findViewById(R.id.btnManageClients);
        Button manageServiceOrders = findViewById(R.id.btnManageOs);
        Button manageVehicle = findViewById(R.id.btnManageVehicles);

        manageClients.setOnClickListener(v -> {
            Intent intent = new Intent(FuncionarioDashboardActivity.this, AddClient.class);
            startActivity(intent);
        });

        manageServiceOrders.setOnClickListener(v -> {
            Intent intent = new Intent(FuncionarioDashboardActivity.this, ServiceReportActivity.class);
            startActivity(intent);
        });

        manageVehicle.setOnClickListener(v -> {
            Intent intent = new Intent(FuncionarioDashboardActivity.this, AddVehicles.class);
            startActivity(intent);
        });
    }
}

