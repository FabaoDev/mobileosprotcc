package br.com.mobileospro;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ReportsActivity extends AppCompatActivity {

    private Button btnServiceReport, btnStockReport, btnRatingReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        btnServiceReport = findViewById(R.id.btnServiceReport);
        btnStockReport = findViewById(R.id.btnStockReport);
        btnRatingReport = findViewById(R.id.btnRatingReport);

        btnServiceReport.setOnClickListener(v -> {
            Intent intent = new Intent(ReportsActivity.this, ServiceReportActivity.class);
            startActivity(intent);
        });

        btnStockReport.setOnClickListener(v -> {
            Intent intent = new Intent(ReportsActivity.this, StockReportActivity.class);
            startActivity(intent);
        });

        btnRatingReport.setOnClickListener(v -> {
            Intent intent = new Intent(ReportsActivity.this, RatingReportActivity.class);
            startActivity(intent);
        });
    }
}

