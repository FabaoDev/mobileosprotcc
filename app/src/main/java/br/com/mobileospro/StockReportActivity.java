package br.com.mobileospro;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class StockReportActivity extends AppCompatActivity {

    private ListView lvStockReport;
    private DatabaseHelper db;
    private ArrayList<String> stockReportList;
    private ArrayAdapter<String> stockReportAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_report);

        lvStockReport = findViewById(R.id.lvStockReport);
        db = DatabaseHelper.getInstance(this);
        stockReportList = new ArrayList<>();
        stockReportAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stockReportList);
        lvStockReport.setAdapter(stockReportAdapter);

        loadStockReport();
    }

    private void loadStockReport() {
        stockReportList.clear();
        Cursor cursor = db.getAllParts();  // Método que busca todas as peças do banco de dados
        if (cursor.moveToFirst()) {
            do {
                String stockDetails = "Peça: " + cursor.getString(cursor.getColumnIndex("item_name")) +
                        " - Quantidade: " + cursor.getInt(cursor.getColumnIndex("quantity"));
                stockReportList.add(stockDetails);
            } while (cursor.moveToNext());
        }
        stockReportAdapter.notifyDataSetChanged();
    }
}

