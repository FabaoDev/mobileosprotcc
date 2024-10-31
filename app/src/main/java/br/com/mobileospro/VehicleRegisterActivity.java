package br.com.mobileospro;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class VehicleRegisterActivity extends AppCompatActivity {

    private EditText etOwnerName, etOwnerMail, etOwnerCpf, etVehicleModel, etVehiclePlate, etVehicleYear;
    private Button btnAddVehicle;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_vehicle);

        etOwnerName = findViewById(R.id.etOwnerName);
        etOwnerMail = findViewById(R.id.etOwnerMail);
        etOwnerCpf = findViewById(R.id.etOwnerCpf);
        etVehicleModel = findViewById(R.id.etVehicleModel);
        etVehiclePlate = findViewById(R.id.etVehiclePlate);
        etVehicleYear = findViewById(R.id.etVehicleYear);
        btnAddVehicle = findViewById(R.id.btnAddVehicle);

        db = DatabaseHelper.getInstance(this);

        btnAddVehicle.setOnClickListener(v -> {
            String ownerName = etOwnerName.getText().toString();
            String ownerMail = etOwnerMail.getText().toString();
            String ownerCpf = etOwnerCpf.getText().toString();
            String vehicleModel = etVehicleModel.getText().toString();
            String vehiclePlate = etVehiclePlate.getText().toString();
            String vehicleYear = etVehicleYear.getText().toString();

            if (!ownerName.isEmpty() && !vehicleModel.isEmpty() && !vehiclePlate.isEmpty() && !vehicleYear.isEmpty()) {
                long result = db.addVehicle(ownerName, ownerMail, ownerCpf, vehicleModel, vehiclePlate, vehicleYear);
                if (result != -1) {
                    Toast.makeText(VehicleRegisterActivity.this, "Veículo cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                    clearFields();
                } else {
                    Toast.makeText(VehicleRegisterActivity.this, "Erro ao cadastrar veículo", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(VehicleRegisterActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clearFields() {
        etOwnerName.setText("");
        etOwnerMail.setText("");
        etOwnerCpf.setText("");
        etVehicleModel.setText("");
        etVehiclePlate.setText("");
        etVehicleYear.setText("");
    }
}

