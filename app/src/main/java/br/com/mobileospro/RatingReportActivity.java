package br.com.mobileospro;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class RatingReportActivity extends AppCompatActivity {

    private ListView lvRatingReport;
    private DatabaseHelper db;
    private ArrayList<String> ratingReportList;
    private ArrayAdapter<String> ratingReportAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_report);

        lvRatingReport = findViewById(R.id.lvRatingReport);
        db = DatabaseHelper.getInstance(this);
        ratingReportList = new ArrayList<>();
        ratingReportAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ratingReportList);
        lvRatingReport.setAdapter(ratingReportAdapter);

        loadRatingReport();
    }

    private void loadRatingReport() {
        ratingReportList.clear();
        Cursor cursor = db.getAllServiceRatings();  // Método que busca todas as avaliações do banco de dados
        if (cursor.moveToFirst()) {
            do {
                String ratingDetails = "Cliente: " + cursor.getString(cursor.getColumnIndex("client_name")) +
                        " - Nota: " + cursor.getFloat(cursor.getColumnIndex("rating")) +
                        " - Comentários: " + cursor.getString(cursor.getColumnIndex("comments"));
                ratingReportList.add(ratingDetails);
            } while (cursor.moveToNext());
        }
        ratingReportAdapter.notifyDataSetChanged();
    }
}

