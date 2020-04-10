package com.ahmadrofiul.siunlimited;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginnfcActivity extends AppCompatActivity {

    private NfcAdapter adapter = null;
    private PendingIntent pendingIntent = null;

    private TextView currentTagView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginnfc);

        /*currentTagView = (TextView) findViewById(R.id.currentTagView);
        currentTagView.setText("Loading...");

        adapter = NfcAdapter.getDefaultAdapter(this);*/
    }

    /*@Override
    public void onResume() {
        try{
            super.onResume();
            if (!adapter.isEnabled()) {
                UtilsActivity.showNfcSettingsDialog(this);
                return;
            }

            if (pendingIntent == null) {
                pendingIntent = PendingIntent.getActivity(this, 0,
                        new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
                currentTagView.setText("Tempelkan Kartu pada HP");
            }
            adapter.enableForegroundDispatch(this, pendingIntent, null, null);


        }catch (Exception e){
            Toast.makeText(LoginnfcActivity.this,"NFC is Trouble",Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void onPause() {
        try{
            super.onPause();
            adapter.disableForegroundDispatch(this);
        }catch (Exception e){
            Toast.makeText(LoginnfcActivity.this,"NFC is Trouble",Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    @Override
    public void onNewIntent(Intent intent) {
        Log.d("onNewIntent", "Discovered tag with intent " + intent);

        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        final String tagId = UtilsActivity.bytesToHex(tag.getId());


        currentTagView.setText("Kode NFCnya = " + tagId +
                "\nDi scan = " + UtilsActivity.now());
    }*/

}

