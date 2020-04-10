package com.ahmadrofiul.siunlimited;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

public class KebijakanActivity extends AppCompatActivity {

    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kebijakan);

        TextView tt=(TextView)findViewById(R.id.textView);
        tt.setText("\nSiadin Unlimited All IN ONE Mobile Aplication \n" +
                "\nSIUnlimited hadir dalam bentuk aplikasi android " +
                "untuk membantu mahasiswa dalam memantau kegiatan akademik " +
                "di UDINUS Semarang\n\n" +
                "Aplikasi ini berisi fitur-fitur :\n" +
                "1.\tMelihat Jadwal Kuliah\n" +
                "2.\tMelihat Kartu Rencana Studi\n" +
                "3.\tMelihat Hasil Studi\n" +
                "4.\tMelihat Rekap Nilai\n" +
                "5.\tMelihat Kartu Ujian\n" +
                "6.\tMelihat Studi Semester Antara\n" +
                "7.\tMelihat Rekap Presensi\n" +
                "8.\tMengecek Tagihan Pembayaran\n" +
                "9.\tMelihat Riwayat Keuangan\n" +
                "10.\tMengecek jadwal dosen wali\n" +
                "11.\tMelihat Teman sekelas\n" +
                "12.\tMengecek informasi \n" +
                "13.\tMelihat Rekap Presensi\n" +
                "14.\tMelihat Kalender Akademik\n" +
                "\nOtentikasi aplikasi ini menggunakan Nim dan Password yang " +
                "terintregasi dengan Siadin (Dinus.ac.id), Aplikasi ini menjamin " +
                "keamanan data, karena verifikasi akun tetap melalui portal 'Dinus.ac.id > student'" +
                "\n\n\n" +
                "Fitur mendatang :\n" +
                "- Notifikasi Perkuliahan\n" +
                "- Kuliah Online\n" +
                "- KRSan\n" +
                "- Login dengan kartu E-money/KTP\n" +
                "- IOS Platform\n");

        Button b = (Button)findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(KebijakanActivity.this);
                builder.setTitle("Perhatian");
                builder.setMessage("Apakah anda telah membaca dan menyetujui?");
                //builder.setCancelable(false);
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        SharedPreferences share = getSharedPreferences("mahasiswa", MODE_PRIVATE);
                        SharedPreferences.Editor save = share.edit();
                        save.putString("setuju", "1");
                        save.apply();

                        startActivity(new Intent(KebijakanActivity.this, LoginActivity.class));
                        finish();
                        //dialogInterface.dismiss();
                    }
                });
                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        startActivity(new Intent(KebijakanActivity.this, LoginActivity.class));
                        finish();
                        //dialogInterface.dismiss();

                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

    }
}
