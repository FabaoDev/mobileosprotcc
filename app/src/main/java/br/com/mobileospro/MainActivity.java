package br.com.mobileospro;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnManageClients, btnManageEmployee, btnManageVehicles, btnManageOrders, btnManageStock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnManageClients = findViewById(R.id.btnManageClients);
        btnManageEmployee = findViewById(R.id.btnManageEmployee);
        btnManageVehicles = findViewById(R.id.btnManageVehicles);
        btnManageOrders = findViewById(R.id.btnManageOrders);
        btnManageStock = findViewById(R.id.btnManageStock);

        // Navegar para a tela de Gerenciamento de Clientes
        btnManageClients.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddClient.class);
            startActivity(intent);
        });

        // Navegar para a tela de Gerenciamento de Empregados
        btnManageEmployee.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEmployee.class);
            startActivity(intent);
        });

        // Navegar para a tela de Gerenciamento de Veículos
        btnManageVehicles.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddVehicles.class);
            startActivity(intent);
        });

        // Navegar para a tela de Gerenciamento de Ordens de Serviço
        btnManageOrders.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ManageOrdersActivity.class);
            startActivity(intent);
        });

        // Navegar para a tela de Gerenciamento de Estoque
        btnManageStock.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ManageStockActivity.class);
            startActivity(intent);
        });
    }
}
