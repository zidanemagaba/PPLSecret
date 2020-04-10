package com.ahmadrofiul.siunlimited;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DownloadActivity extends AppCompatActivity {

    private String [] cetak, dicetak;
    private int jumlah;
    private AlertDialog.Builder builder;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);


        builder = new AlertDialog.Builder(DownloadActivity.this);
        listView = (ListView) findViewById(R.id.listView);

        jumlah = 7;
        cetak = new String[jumlah];         dicetak = new String[jumlah];
        cetak[0] = "Cetak Biodata";         dicetak[0] = "pdf_dataindukmhs.html";
        cetak[1] = "Cetak KRS";             dicetak[1] = "pdf_krs_reguler.html";
        cetak[2] = "Cetak Daftar Nilai";    dicetak[2] = "transkrip_cetak_transkrip.html";
        cetak[3] = "Cetak KRS Lalu";        dicetak[3] = "pdf_khs_lalu2_20182.html";
        cetak[4] = "Cetak Kartu Ujian";     dicetak[4] = "pdf_uts_reg.html"; ///UNTUK UAS
        //cetak[4] = "Cetak Kartu Ujian";     dicetak[4] = "pdf_uas_reg.html"; ///UNTUK UAS
        cetak[5] = "Cetak Tagihan";         dicetak[5] = "pdf_tagihan.html";
        cetak[6] = "Cetak E-Tiket KTM";     dicetak[6] = "pdf_cetak_tiketktm.html";




        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, cetak));
        listView.setSelected(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView arg0, View arg1, final int arg2, long arg3) {
                    final String tampil = "Apakah anda ingin mendownload? ";

                    //final CharSequence[] dialogitem = {"Lihat Data"};
                    builder = new AlertDialog.Builder(DownloadActivity.this);
                    builder.setTitle(""+cetak[arg2]);
                    builder.setMessage(tampil);
                    builder.setCancelable(false);
                    builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(DownloadActivity.this, Download2Activity.class);
                            i.putExtra("link", dicetak[arg2]);
                            startActivity(i);
                            //dialog.dismiss();

                        }
                    });
                    builder.setNegativeButton("Kembali", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });


                    builder.create().show();


            }});
        ((ArrayAdapter)listView .getAdapter()).notifyDataSetInvalidated();






    }
}
