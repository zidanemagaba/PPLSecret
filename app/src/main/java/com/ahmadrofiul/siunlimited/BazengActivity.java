package com.ahmadrofiul.siunlimited;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;


public class BazengActivity extends AppCompatActivity {

    /*int count=0;
    TextView t;
    Context context = this;
    private NotificationManager mNotifyManager;*/


    private NotificationManager mNotifyManager;
    private static final int NOTIFICATION_ID = 1;
    private static final String NOTIFICATION_GUIDE_URL = "https://developer.android.com/design/patterns/notifications.html";
    private static final String ACTION_UPDATE_NOTIFICATION = "com.example.android.notifyme.ACTION_UPDATE_NOTIFICATION";
    private static final String ACTION_CANCEL_NOTIFICATION = "com.example.android.notifyme.ACTION_CANCEL_NOTIFICATION";

    private Button mNotifyButton;
    private Button mUpdateButton;
    private Button mCancelButton;


    Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bazeng);

        /*mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Timer myTimer = new Timer();
        t = (TextView)findViewById(R.id.textView);
        //Set the schedule function and rate
        myTimer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        //Called at every 1000 milliseconds (1 second)
                        t = (TextView)findViewById(R.id.textView);
                        if(count ==0){
                            t.setText("Holaaa");
                            count =1;
                        }else {
                            t.setText("Loading...");
                            count =0;
                        }


                        Log.i("MainActivity", "Repeated task");
                    }
                }, 0, 500);


        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateNotification();
                Toast.makeText(BazengActivity.this,"Bazeng",Toast.LENGTH_SHORT).show();

            }
        });*/





    }









}
