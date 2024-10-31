package br.com.mobileospro;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ServiceScheduleActivity extends AppCompatActivity {

    private EditText etClientName, etVehicleModel, etServiceDate;
    private Button btnScheduleService;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_schedule);

        etClientName = findViewById(R.id.etClientName);
        etVehicleModel = findViewById(R.id.etVehicleModel);
        etServiceDate = findViewById(R.id.etServiceDate);
        btnScheduleService = findViewById(R.id.btnScheduleService);

        db = DatabaseHelper.getInstance(this);

        // Botão para agendar serviço
        btnScheduleService.setOnClickListener(v -> {
            String clientName = etClientName.getText().toString();
            String vehicleModel = etVehicleModel.getText().toString();
            String serviceDate = etServiceDate.getText().toString();

            if (!clientName.isEmpty() && !vehicleModel.isEmpty() && !serviceDate.isEmpty()) {
                long result = db.addServiceSchedule(clientName, vehicleModel, serviceDate);
                if (result != -1) {
                    Toast.makeText(ServiceScheduleActivity.this, "Serviço agendado com sucesso", Toast.LENGTH_SHORT).show();
                    etClientName.setText("");
                    etVehicleModel.setText("");
                    etServiceDate.setText("");
                } else {
                    Toast.makeText(ServiceScheduleActivity.this, "Erro ao agendar serviço", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(ServiceScheduleActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

