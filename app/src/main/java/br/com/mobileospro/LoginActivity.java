package br.com.mobileospro;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private Button btnLogin;
    private TextView tvRegister;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);

        db = DatabaseHelper.getInstance(this);

        // Ação do botão de login
        btnLogin.setOnClickListener(v -> {
            String emailInput = email.getText().toString();
            String passwordInput = password.getText().toString();
            String userType = db.checkUser(emailInput, passwordInput);

            if (userType != null) {
                Toast.makeText(LoginActivity.this, "Login bem-sucedido", Toast.LENGTH_SHORT).show();
                // Redirecionar conforme o tipo de usuário
                Intent intent;
                if (userType.equals("admin")) {
                    intent = new Intent(LoginActivity.this, AdminDashboardActivity.class);
                } else if (userType.equals("funcionario")) {
                    intent = new Intent(LoginActivity.this, FuncionarioDashboardActivity.class);
                } else {
                    intent = new Intent(LoginActivity.this, ClienteDashboardActivity.class);
                }
                startActivity(intent);
                finish(); // Finaliza a activity de login para não voltar a ela
            } else {
                Toast.makeText(LoginActivity.this, "Credenciais inválidas", Toast.LENGTH_SHORT).show();
            }
        });

        // Redirecionar para a tela de cadastro
        tvRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, AddClient.class);
            startActivity(intent);
        });
    }
}