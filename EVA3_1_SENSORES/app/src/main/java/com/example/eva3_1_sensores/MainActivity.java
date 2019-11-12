package com.example.eva3_1_sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lstSensor;
    TextView txtInfo;
    String[] aSensores;
    List<Sensor> lstSensores; //Loque devuelve el sensor manager
    SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstSensor = findViewById(R.id.lstSensor);
        txtInfo = findViewById(R.id.txtInfo);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        lstSensores = sensorManager.getSensorList(Sensor.TYPE_ALL);
        aSensores = new String[lstSensores.size()];
        int i = 0;
        for (Sensor sensor : lstSensores){
            aSensores[i] = sensor.getName();
            i++;
        }

        lstSensor.setAdapter(new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                aSensores
        ));

        lstSensor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Sensor sensor = lstSensores.get(i);
                txtInfo.setText("Name: "+sensor.getName()+ "\nMax range: "+ sensor.getMaximumRange()+" \n"+
                        "delay: "+sensor.getMaxDelay() +" \n"+
                        "Power: "+sensor.getPower()+"\n"+
                        "Resolution: "+sensor.getResolution());
            }
        });
    }
}
