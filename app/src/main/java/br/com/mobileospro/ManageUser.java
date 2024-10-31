package br.com.mobileospro;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ManageUser extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_user);

        // Botões ou opções para gerenciar clientes, funcionários, veículos, etc.
        Button addClients = findViewById(R.id.btnAddClients);
        Button manageClients = findViewById(R.id.btnManageClients);
        Button addFuncionarios = findViewById(R.id.btnAddFuncionarios);
        Button manageFuncionarios = findViewById(R.id.btnManageFuncionarios);

        // Área dos redirecionamentos para gerenciamentos
        addClients.setOnClickListener(v -> {
            Intent intent = new Intent(ManageUser.this, AddClient.class);
            startActivity(intent);
        });
        manageClients.setOnClickListener(v -> {
            Intent intent = new Intent(ManageUser.this, AddEmployee.class);
            startActivity(intent);
        });
        addFuncionarios.setOnClickListener(v -> {
            Intent intent = new Intent(ManageUser.this, AddVehicles.class);
            startActivity(intent);
        });
        manageFuncionarios.setOnClickListener(v -> {
            Intent intent = new Intent(ManageUser.this, AddVehicles.class);
            startActivity(intent);
        });
    }
}

