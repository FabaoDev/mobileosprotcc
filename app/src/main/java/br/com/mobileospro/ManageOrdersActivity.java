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

public class ManageOrdersActivity extends AppCompatActivity {

    private EditText etOrderClient, etOrderVehicle, etOrderDescription, etOrderServices, etOrderEstimate;
    private Button btnAddOrder;
    private ListView lvOrders;
    private DatabaseHelper db;
    private ArrayAdapter<String> orderAdapter;
    private ArrayList<String> orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_orders);

        etOrderClient = findViewById(R.id.etOrderClient);
        etOrderVehicle = findViewById(R.id.etOrderVehicle);
        etOrderDescription = findViewById(R.id.etOrderDescription);
        etOrderServices = findViewById(R.id.etOrderServices);
        etOrderEstimate = findViewById(R.id.etOrderEstimate);
        btnAddOrder = findViewById(R.id.btnAddOrder);
        lvOrders = findViewById(R.id.lvOrders);

        db = DatabaseHelper.getInstance(this);
        orderList = new ArrayList<>();
        orderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, orderList);
        lvOrders.setAdapter(orderAdapter);

        // Botão para adicionar uma OS
        btnAddOrder.setOnClickListener(v -> {
            String client = etOrderClient.getText().toString();
            String vehicle = etOrderVehicle.getText().toString();
            String description = etOrderDescription.getText().toString();
            String services = etOrderServices.getText().toString();
            String estimate = etOrderEstimate.getText().toString();

            if (!client.isEmpty() && !vehicle.isEmpty() && !description.isEmpty() && !services.isEmpty() && !estimate.isEmpty()) {
                long result = db.addOrder(client, vehicle, description, services, estimate);
                if (result != -1) {
                    Toast.makeText(ManageOrdersActivity.this, "Ordem de Serviço adicionada com sucesso", Toast.LENGTH_SHORT).show();
                    etOrderClient.setText("");
                    etOrderVehicle.setText("");
                    etOrderDescription.setText("");
                    etOrderServices.setText("");
                    etOrderEstimate.setText("");
                    loadOrders();
                } else {
                    Toast.makeText(ManageOrdersActivity.this, "Erro ao adicionar OS", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(ManageOrdersActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            }
        });

        // Carregar OSs na ListView
        loadOrders();
    }

    private void loadOrders() {
        orderList.clear();
        Cursor cursor = db.getAllOrders();
        if (cursor.moveToFirst()) {
            do {
                String client = cursor.getString(cursor.getColumnIndex("COLUMN_ORDER_CLIENT"));
                String vehicle = cursor.getString(cursor.getColumnIndex("COLUMN_ORDER_VEHICLE"));
                String description = cursor.getString(cursor.getColumnIndex("COLUMN_ORDER_DESCRIPTION"));
                String services = cursor.getString(cursor.getColumnIndex("COLUMN_ORDER_SERVICES"));
                String estimate = cursor.getString(cursor.getColumnIndex("COLUMN_ORDER_ESTIMATE"));
                orderList.add(client + " - " + vehicle + ": " + description + " - Serviços: " + services + " - Preço: " + estimate);
            } while (cursor.moveToNext());
        }
        cursor.close();
        orderAdapter.notifyDataSetChanged();
    }
}
