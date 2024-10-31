package br.com.mobileospro;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import android.text.TextWatcher;
import android.text.Editable;
import android.content.Intent;

public class ManageClientsActivity extends AppCompatActivity {

    private EditText etSearch;
    private ListView lvItems;
    private Button btnAddNewItem;
    private ArrayAdapter<String> clientAdapter;
    private ArrayList<String> clientList;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_clients);

        etSearch = findViewById(R.id.etSearch);
        lvItems = findViewById(R.id.lvItems);
        btnAddNewItem = findViewById(R.id.btnAddNewItem);

        db = DatabaseHelper.getInstance(this);
        clientList = new ArrayList<>();
        clientAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, clientList);
        lvItems.setAdapter(clientAdapter);

        // Buscar clientes
        etSearch.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loadClients(s.toString());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void afterTextChanged(Editable s) {}
        });

        // Adicionar novo cliente
        btnAddNewItem.setOnClickListener(v -> {
            Intent intent = new Intent(ManageClientsActivity.this, AddClient.class);
            startActivity(intent);
        });

        // Carregar a lista inicial de clientes
        loadClients("");
    }

    private void loadClients(String search) {
        clientList.clear();
        Cursor cursor = db.searchClients(search);  // Método para buscar clientes no banco
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String cpf = cursor.getString(cursor.getColumnIndex("cpf"));
                String email = cursor.getString(cursor.getColumnIndex("email"));
                clientList.add(name + " - CPF: " + cpf + " - Email: " + email);
            } while (cursor.moveToNext());
        }
        cursor.close();
        clientAdapter.notifyDataSetChanged();
    }
}
