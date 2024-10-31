package br.com.mobileospro;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminDashboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        // Botões ou opções para gerenciar clientes, funcionários, veículos, etc.
        Button manageClients = findViewById(R.id.btnManageClients);
        Button manageEmployees = findViewById(R.id.btnManageEmployees);
        Button manageVehicle = findViewById(R.id.btnManageVehicles);
        Button manageStock = findViewById(R.id.btnManageStock);
        Button manageOs = findViewById(R.id.btnManageOs);
        Button manageStatus = findViewById(R.id.btnManageStatus);
        Button manageRating = findViewById(R.id.btnManageRating);

        // Área dos redirecionamentos para gerenciamentos
        manageClients.setOnClickListener(v -> {
            Intent intent = new Intent(AdminDashboardActivity.this, AddClient.class);
            startActivity(intent);
        });
        manageEmployees.setOnClickListener(v -> {
            Intent intent = new Intent(AdminDashboardActivity.this, AddEmployee.class);
            startActivity(intent);
        });
        manageVehicle.setOnClickListener(v -> {
            Intent intent = new Intent(AdminDashboardActivity.this, AddVehicles.class);
            startActivity(intent);
        });

        // Adicionar outras funcionalidades conforme o necessário
    }
}

