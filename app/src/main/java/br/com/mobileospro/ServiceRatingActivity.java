package br.com.mobileospro;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ServiceRatingActivity extends AppCompatActivity {

    private EditText etClientNameRating, etRatingComments;
    private RatingBar ratingBar;
    private Button btnSubmitRating;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_rating);

        etClientNameRating = findViewById(R.id.etClientNameRating);
        etRatingComments = findViewById(R.id.etRatingComments);
        ratingBar = findViewById(R.id.ratingBar);
        btnSubmitRating = findViewById(R.id.btnSubmitRating);

        db = DatabaseHelper.getInstance(this);

        btnSubmitRating.setOnClickListener(v -> {
            String clientName = etClientNameRating.getText().toString();
            String comments = etRatingComments.getText().toString();
            float rating = ratingBar.getRating();

            if (!clientName.isEmpty() && rating > 0) {
                long result = db.addServiceRating(clientName, rating, comments);
                if (result != -1) {
                    Toast.makeText(ServiceRatingActivity.this, "Avaliação enviada com sucesso", Toast.LENGTH_SHORT).show();
                    etClientNameRating.setText("");
                    etRatingComments.setText("");
                    ratingBar.setRating(0);
                } else {
                    Toast.makeText(ServiceRatingActivity.this, "Erro ao enviar avaliação", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(ServiceRatingActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

