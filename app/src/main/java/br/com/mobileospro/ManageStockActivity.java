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

public class ManageStockActivity extends AppCompatActivity {

    private EditText etPartName, etPartQuantity;
    private Button btnAddPart;
    private ListView lvParts;
    private DatabaseHelper db;
    private ArrayAdapter<String> partAdapter;
    private ArrayList<String> partList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_stock);

        etPartName = findViewById(R.id.etPartName);
        etPartQuantity = findViewById(R.id.etPartQuantity);
        btnAddPart = findViewById(R.id.btnAddPart);
        lvParts = findViewById(R.id.lvParts);

        db = DatabaseHelper.getInstance(this);
        partList = new ArrayList<>();
        partAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, partList);
        lvParts.setAdapter(partAdapter);

        // Botão para adicionar peça ao estoque
        btnAddPart.setOnClickListener(v -> {
            String partName = etPartName.getText().toString();
            String partQuantity = etPartQuantity.getText().toString();

            if (!partName.isEmpty() && !partQuantity.isEmpty()) {
                long result = db.addPart(partName, partQuantity); // Chama o método addPart
                if (result != -1) {
                    Toast.makeText(ManageStockActivity.this, "Peça adicionada com sucesso", Toast.LENGTH_SHORT).show();
                    etPartName.setText("");
                    etPartQuantity.setText("");
                    loadParts();
                } else {
                    Toast.makeText(ManageStockActivity.this, "Erro ao adicionar peça", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(ManageStockActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            }
        });

        // Carregar peças no ListView
        loadParts();
    }

    private void loadParts() {
        partList.clear();
        Cursor cursor = db.getAllParts(); // Chama o método getAllParts
        if (cursor.moveToFirst()) {
            do {
                String partName = cursor.getString(cursor.getColumnIndex("COLUMN_PART_NAME"));
                String partQuantity = cursor.getString(cursor.getColumnIndex("COLUMN_PART_QUANTITY"));
                partList.add(partName + " - Quantidade: " + partQuantity);
            } while (cursor.moveToNext());
        }
        cursor.close(); // Sempre feche o cursor!
        partAdapter.notifyDataSetChanged();
    }
}

