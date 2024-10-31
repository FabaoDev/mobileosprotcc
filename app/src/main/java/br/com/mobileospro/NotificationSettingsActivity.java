package br.com.mobileospro;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NotificationSettingsActivity extends AppCompatActivity {

    private CheckBox cbOrderStatusNotification, cbReminderNotification;
    private Button btnSaveNotificationSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications_settings);

        cbOrderStatusNotification = findViewById(R.id.cbOrderStatusNotification);
        cbReminderNotification = findViewById(R.id.cbReminderNotification);
        btnSaveNotificationSettings = findViewById(R.id.btnSaveNotificationSettings);

        btnSaveNotificationSettings.setOnClickListener(v -> {
            // Salvar preferências de notificações (de forma simples, usando SharedPreferences)
            boolean orderStatusNotification = cbOrderStatusNotification.isChecked();
            boolean reminderNotification = cbReminderNotification.isChecked();

            // Aqui, você poderia salvar essas preferências em SharedPreferences
            // e configurar seu sistema para enviar notificações baseado nisso
            Toast.makeText(NotificationSettingsActivity.this, "Configurações salvas", Toast.LENGTH_SHORT).show();

            // Para enviar notificações, é necessário configurar o NotificationManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationManager notificationManager = getSystemService(NotificationManager.class);
                NotificationChannel channel = new NotificationChannel("OSProChannel", "Mobile OS Pro Notifications", NotificationManager.IMPORTANCE_DEFAULT);
                notificationManager.createNotificationChannel(channel);
            }
        });
    }
}

