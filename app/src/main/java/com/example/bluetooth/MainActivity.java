package com.example.bluetooth;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button ON,OFF;
    BluetoothAdapter adapter;
    final static int req = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ON = (Button)findViewById(R.id.buttonOn);
        OFF = (Button)findViewById(R.id.buttonOff);

        adapter = BluetoothAdapter.getDefaultAdapter();



        ON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter==null){
                    Toast.makeText(MainActivity.this, "Your Device Does Not Support Blutooth", Toast.LENGTH_SHORT).show();
                }else {
                    if (!adapter.isEnabled()){
                        Toast.makeText(MainActivity.this, "Turning On Blutoth", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(i,req);
                    }
                }

            }
        });
        OFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter.disable()){
                    Toast.makeText(MainActivity.this, "Blutooth OFF", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==req){
            if (resultCode==RESULT_OK){
                Toast.makeText(this, "Bluetooth ON", Toast.LENGTH_SHORT).show();
            }else if (requestCode==RESULT_CANCELED){
                Toast.makeText(this, "Canclled", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
