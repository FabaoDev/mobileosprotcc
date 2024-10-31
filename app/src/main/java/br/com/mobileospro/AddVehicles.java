package br.com.mobileospro;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddVehicles extends AppCompatActivity {

    private EditText etOwnerName, etOwnerMail, etOwnerCpf, etModel, etPlate, etYear;
    private Button btnAddVehicle;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_vehicle);

        etOwnerName = findViewById(R.id.etOwnerName);
        etOwnerMail = findViewById(R.id.etOwnerMail);
        etOwnerCpf = findViewById(R.id.etOwnerCpf);
        etModel = findViewById(R.id.etVehicleModel);
        etPlate = findViewById(R.id.etVehiclePlate);
        etYear = findViewById(R.id.etVehicleYear);
        btnAddVehicle = findViewById(R.id.btnAddVehicle);

        db = DatabaseHelper.getInstance(this);

        // Botão para adicionar um veículo
        btnAddVehicle.setOnClickListener(v -> {
            String ownerName = etOwnerName.getText().toString();
            String ownerMail = etOwnerMail.getText().toString();
            String ownerCpf = etOwnerCpf.getText().toString();
            String model = etModel.getText().toString();
            String plate = etPlate.getText().toString();
            String year = etYear.getText().toString();

            if (!ownerName.isEmpty() && !model.isEmpty() && !plate.isEmpty() && !year.isEmpty()) {
                long result = db.addVehicle(ownerName, ownerMail, ownerCpf, model, plate, year); // Chama o método addVehicle
                if (result != -1) {
                    Toast.makeText(AddVehicles.this, "Veículo adicionado com sucesso", Toast.LENGTH_SHORT).show();
                    etOwnerName.setText("");
                    etOwnerMail.setText("");
                    etOwnerCpf.setText("");
                    etModel.setText("");
                    etPlate.setText("");
                    etYear.setText("");
                } else {
                    Toast.makeText(AddVehicles.this, "Erro ao adicionar veículo", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(AddVehicles.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}