package br.com.mobileospro;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddEmployee extends AppCompatActivity {

    private EditText etEmployeeName, etEmployeePosition, etEmployeePhone;
    private Button btnAddEmployee;
    private DatabaseHelper db;
    private String userType; // Variável para armazenar o tipo de usuário

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_employee);

        // Obter o tipo de usuário da Intent
        userType = getIntent().getStringExtra("admin"); //admin, funcionario, ou cliente

        // Verificar se o usuário é administrador
        if (!userType.equals("admin")) {
            Toast.makeText(this, "Acesso negado! Apenas administradores podem adicionar funcionários.", Toast.LENGTH_SHORT).show();
            finish();  // Finalizar a Activity se não for administrador
            return;
        }

        // Inicializar os componentes da UI
        etEmployeeName = findViewById(R.id.etEmployeeName);
        etEmployeePosition = findViewById(R.id.etEmployeePosition);
        etEmployeePhone = findViewById(R.id.etEmployeePhone);
        btnAddEmployee = findViewById(R.id.btnAddEmployee);
        db = DatabaseHelper.getInstance(this);

        btnAddEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEmployee();
            }
        });
    }

    private void addEmployee() {
        String name = etEmployeeName.getText().toString();
        String position = etEmployeePosition.getText().toString();
        String phone = etEmployeePhone.getText().toString();

        // Validação dos campos
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(position) || TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        } else {
            // Adicionar o funcionário no banco de dados
            long isInserted = db.addEmployee(name, position, phone);
            if (isInserted != -1) { // Verifica se a inserção foi bem-sucedida
                Toast.makeText(this, "Funcionário cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                finish(); // Fecha a activity após o cadastro
            } else {
                Toast.makeText(this, "Erro ao cadastrar funcionário", Toast.LENGTH_SHORT).show();
            }
        }
    }
}