package br.com.mobileospro;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;

public class AddClient extends AppCompatActivity {

    private EditText etClientName, etClientAddress, etClientPhone, etOwnerMail, etOwnerCpf, etPassword;
    private Spinner spnUserType;
    private Button btnAddClient;
    private DatabaseHelper db;
    private String userType; // Tipo de usuário logado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_client);

        etClientName = findViewById(R.id.etClientName);
        etClientAddress = findViewById(R.id.etClientAddress);
        etClientPhone = findViewById(R.id.etClientPhone);
        etOwnerCpf = findViewById(R.id.etOwnerCpf);
        etOwnerMail = findViewById(R.id.etOwnerMail);
        etPassword = findViewById(R.id.etPassword);
        btnAddClient = findViewById(R.id.btnAddClient);
        spnUserType = findViewById(R.id.spnUserType);

        db = DatabaseHelper.getInstance(this);

        // Obtendo o tipo de usuário logado (admin, funcionário, cliente) e definindo valor padrão caso esteja nulo
        userType = getIntent().getStringExtra("user_type");
        if (userType == null) {
            userType = "cliente"; // valor padrão caso não seja passado
        }

        // Exibir o Spinner apenas se o usuário for um administrador
        if (userType.equals("admin")) {
            spnUserType.setVisibility(View.VISIBLE);

            // Configurar o Spinner com as opções de tipos de usuário
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.user_types, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnUserType.setAdapter(adapter);
        } else {
            spnUserType.setVisibility(View.GONE);
        }

        btnAddClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addClient();
            }
        });
    }

    private void addClient() {
        String name = etClientName.getText().toString();
        String address = etClientAddress.getText().toString();
        String phone = etClientPhone.getText().toString();
        String cpf = etOwnerCpf.getText().toString();
        String mail = etOwnerMail.getText().toString();
        String password = etPassword.getText().toString();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(address) || TextUtils.isEmpty(phone) ||
                TextUtils.isEmpty(cpf) || TextUtils.isEmpty(mail) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verificar se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            Toast.makeText(this, "CPF inválido. Insira exatamente 11 dígitos.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verificar se o CPF ou email já existem
        if (db.isCpfOrEmailExistente(cpf, mail)) {
            Toast.makeText(this, "CPF ou Email já existente. Tente outro.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Define o tipo de usuário para o cadastro (selecionado pelo admin ou padrão para cliente)
        String selectedUserType = userType.equals("admin") ? spnUserType.getSelectedItem().toString() : "cliente";

        long isInserted = db.addUser(name, address, phone, cpf, mail, password, selectedUserType);
        if (isInserted != -1) {
            Toast.makeText(this, "Cliente cadastrado com sucesso", Toast.LENGTH_SHORT).show();
            finish(); // Fecha a activity após o cadastro
        } else {
            Toast.makeText(this, "Erro ao cadastrar cliente", Toast.LENGTH_SHORT).show();
        }
    }
}
