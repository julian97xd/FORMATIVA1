package com.example.formativa1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText editText1,editText2;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.SEND_SMS)){
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.SEND_SMS},1);
        }else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS}, 1);
            }

            }else {

        }

        button=(Button)findViewById(R.id.Enviar);
        editText1=(EditText)findViewById(R.id.Telefono);
        editText2=(EditText)findViewById(R.id.Mensaje);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  numero=editText1.getText().toString();
                String sms=editText2.getText().toString();

                try{
                    SmsManager smsManager=SmsManager.getDefault();
                    smsManager.sendTextMessage(numero,null,sms,null,null);
                    Toast.makeText(MainActivity.this, "Enviado", Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
                }

            }
        });
     }
     @Override
    public void onRequestPermissionsResult (int requesCode,String[] perimissions,int[] grantResults){
        switch (requesCode){
            case 1:{
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.SEND_SMS)==PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this,"Permiso otorgado",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this,"Permiso denegado",Toast.LENGTH_SHORT).show();
                    }
                }return;
            }
        }
     }
    }

