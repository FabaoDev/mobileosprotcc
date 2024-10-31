package br.com.mobileospro;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddUser  extends AppCompatActivity {

    private EditText etName, etEmail, etPassword;
    private Button btnRegister;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        // Inicialização dos componentes da interface
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);

        // Inicialização do banco de dados
        db = DatabaseHelper.getInstance(this);

        // Configuração do botão de registro
        btnRegister.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();

            // Validação dos campos
            if (validateInput(name, email, password)) {
                long result = db.addClient(name, email, password);  // Adiciona o usuário ao banco de dados
                if (result != -1) {
                    Toast.makeText(AddUser .this, "Usuário registrado com sucesso", Toast.LENGTH_SHORT).show();
                    // Redireciona para a tela de login após o registro bem-sucedido
                    Intent intent = new Intent(AddUser .this, LoginActivity.class);
                    startActivity(intent);
                    finish(); // Fecha a tela de cadastro
                } else {
                    // Mensagem de erro se o e-mail já estiver cadastrado
                    Toast.makeText(AddUser .this, "E-mail já cadastrado. Tente outro.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(AddUser .this, "Preencha todos os campos corretamente", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Método para validar os campos
    private boolean validateInput(String name, String email, String password) {
        if (name.isEmpty()) {
            etName.setError("Nome é obrigatório");
            return false;
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Insira um e-mail válido");
            return false;
        }

        if (password.isEmpty() || password.length() < 6) {
            etPassword.setError("A senha deve ter no mínimo 6 caracteres");
            return false;
        }

        return true;
    }
}