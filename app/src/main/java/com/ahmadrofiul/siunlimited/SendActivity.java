package com.ahmadrofiul.siunlimited;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(SendActivity.this);
        builder.setTitle("Perhatian");
        builder.setMessage("Maaf Menu ini sedang Maintenance");
        builder.setCancelable(false);
        builder.setPositiveButton("Kembali", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        /*TextView textview=(TextView)findViewById(R.id.send);
        //String hola = UtilsActivity.hash("kampret","A11.2016.12345");
        //String hola = UtilsActivity.hash("A11.2016.12345","kampret");
        String hola = UtilsActivity.now();
        textview.setText(hola);
        Toast.makeText(SendActivity.this,hola,Toast.LENGTH_LONG).show();*/

    }
}
