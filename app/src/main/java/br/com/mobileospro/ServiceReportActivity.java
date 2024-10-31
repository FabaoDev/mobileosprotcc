package br.com.mobileospro;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ServiceReportActivity extends AppCompatActivity {

    private ListView lvServiceReport;
    private DatabaseHelper db;
    private ArrayList<String> serviceReportList;
    private ArrayAdapter<String> serviceReportAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_report);

        lvServiceReport = findViewById(R.id.lvServiceReport);
        db = DatabaseHelper.getInstance(this);
        serviceReportList = new ArrayList<>();
        serviceReportAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, serviceReportList);
        lvServiceReport.setAdapter(serviceReportAdapter);

        loadServiceReport();
    }

    private void loadServiceReport() {
        serviceReportList.clear();
        Cursor cursor = db.getAllServiceOrders();  // Método que busca todas as OSs do banco de dados
        if (cursor.moveToFirst()) {
            do {
                String osDetails = "OS #" + cursor.getInt(cursor.getColumnIndex("id")) +
                        " - Cliente: " + cursor.getString(cursor.getColumnIndex("client_name")) +
                        " - Status: " + cursor.getString(cursor.getColumnIndex("status"));
                serviceReportList.add(osDetails);
            } while (cursor.moveToNext());
        }
        serviceReportAdapter.notifyDataSetChanged();
    }
}

