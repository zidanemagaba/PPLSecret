package com.ahmadrofiul.siunlimited;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView textView,textView2;
    int[] sampleImages = {R.drawable.z1,R.drawable.z2,R.drawable.z3,R.drawable.z4,R.drawable.z5,R.drawable.z6,R.drawable.z7,R.drawable.z8,R.drawable.z9,R.drawable.z10,R.drawable.z11,R.drawable.z12,R.drawable.z13};
    CarouselView carouselView;
    String [] matkul, kelompok, hari_1, jam_1, sampai_1, ruang_1, hari_2, jam_2, sampai_2, ruang_2, hari_3, jam_3, sampai_3, ruang_3, absen;
    int jumlah, temp=0, ada=0, jadwal=0, count =0;
    String judul, waktu;


    private AlertDialog.Builder builder;
    private LayoutInflater inflater;
    private View dialogView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ///=========================================================================================

        textView =(TextView)findViewById(R.id.sekarang);
        textView2 =(TextView)findViewById(R.id.sekarang2);

        getData();

        /*Timer myTimer = new Timer();
        //Set the schedule function and rate
        myTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //Called at every 1000 milliseconds (1 second)
                if(count==0){
                    textView.setText("Loading.");
                    textView2.setText("");
                    count =1;
                }else if(count==1){
                    textView.setText("Loading..");
                    textView2.setText("");
                    count =2;
                }else if(count==2){
                    textView.setText("Loading...");
                    textView2.setText("");
                    count =3;
                }else if(count==3){
                    textView.setText("Loading....");
                    textView2.setText("");
                    count =0;
                }
            }
        }, 0, 600);*/

        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);

        jumlah=99;

        matkul = new String[jumlah];
        kelompok = new String[jumlah];
        hari_1 = new String[jumlah];
        jam_1 = new String[jumlah];
        sampai_1 = new String[jumlah];
        ruang_1 = new String[jumlah];
        hari_2 = new String[jumlah];
        jam_2 = new String[jumlah];
        sampai_2 = new String[jumlah];
        ruang_2 = new String[jumlah];
        hari_3 = new String[jumlah];
        jam_3 = new String[jumlah];
        sampai_3 = new String[jumlah];
        ruang_3 = new String[jumlah];
        absen = new String[jumlah];


        WebView web = (WebView) findViewById(R.id.web_view);
        //web.getSettings().setBuiltInZoomControls(true);
        web.getSettings().setJavaScriptEnabled(true);
        web.setWebViewClient(new WebViewClient());
        web.loadUrl("http://zidanejmagaba.com/SiadinBaru/cuaca.php");

        ImageView krs = (ImageView)findViewById(R.id.krs);
        krs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DataKrs.class));
            }
        });
        ImageView khs = (ImageView)findViewById(R.id.khs);
        khs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DataKhs.class));
            }
        });
        ImageView nilai = (ImageView)findViewById(R.id.nilai);
        nilai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NilaiActivity.class));
            }
        });
        ImageView ujian = (ImageView)findViewById(R.id.ujian);
        ujian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DataUjian.class));
                //startActivity(new Intent(MainActivity.this, UjianActivity.class));
            }
        });
        ImageView sa = (ImageView)findViewById(R.id.sa);
        sa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DataSA.class));
            }
        });
        ImageView ujiansa = (ImageView)findViewById(R.id.ujiansa);
        ujiansa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DataUjianSA.class));
            }
        });
        ImageView remidi = (ImageView)findViewById(R.id.remidi);
        remidi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DataRemidi.class));
            }
        });

        ///=========================================================================================
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        SharedPreferences share = getSharedPreferences("mahasiswa", MODE_PRIVATE);
        String namaa = share.getString("nama","");
        String nimm = share.getString("nim","");
        String studii = share.getString("studi","");
        String[] n = nimm.split("");

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerview = navigationView.getHeaderView(0);
        /*ImageView imageview=(ImageView)headerview.findViewById(R.id.foto);
        Glide.with(MainActivity.this)
                .load("http://mahasiswa.dinus.ac.id/images/foto/"+n[1]+"/"+n[1]+n[2]+n[3]+"/"+n[5]+n[6]+n[7]+n[8]+"/"+nimm+".jpg")
                .placeholder(R.drawable.foto)
                .error(R.drawable.foto)
                .into(imageview);*/

        TextView nama = (TextView) headerview.findViewById(R.id.nama);
        nama.setText(namaa);
        TextView nim = (TextView) headerview.findViewById(R.id.nim);
        nim.setText(nimm);
        TextView studi = (TextView) headerview.findViewById(R.id.studi);
        studi.setText(studii);
        navigationView.setNavigationItemSelectedListener(this);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_maps) {
            displayMap();
            return true;

        } else if (id == R.id.action_wa) {
            String url = "https://api.whatsapp.com/send?phone=6282135567713";
            Uri webpage = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(intent);
            return true;

        } else if (id == R.id.send_rating) {
            String url = "https://play.google.com/store/apps/details?id=com.ahmadrofiul.siunlimited";
            Uri webpage = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(intent);
            return true;

        } else if (id == R.id.action_about) {
            //Toast.makeText(MainActivity.this,"Maaf Menu ini sedang Maintenance",Toast.LENGTH_LONG).show();
            return true;

        }   else if (id == R.id.action_update) {
            //startActivity(new Intent(MainActivity.this, UpdateActivity.class));
            String url = ""+UtilsActivity.DOMAIN+"/update.php?versi="+UtilsActivity.VERSION+"";
            Uri webpage = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(intent);
            return true;

        }   else if (id == R.id.action_report) {
            //startActivity(new Intent(MainActivity.this, BazengActivity.class));
            Toast.makeText(MainActivity.this,"Silahkan Chat WA admin Kami",Toast.LENGTH_LONG).show();
            return true;

        } else if (id == R.id.action_logout) {
            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit();
            editor.putBoolean("login", false);
            editor.apply();

            SharedPreferences share = getSharedPreferences("mahasiswa", MODE_PRIVATE);
            SharedPreferences.Editor save = share.edit();
            save.putString("nim", "");
            save.putString("pass", "");
            save.apply();

            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
            //return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.about_me) {
            startActivity(new Intent(MainActivity.this, DataBiodata.class));
            // Handle the camera action
        } else if (id == R.id.assigment) {
            //startActivity(new Intent(MainActivity.this, TugasActivity.class));
            Toast.makeText(MainActivity.this,"Maaf Menu ini sedang Maintenance",Toast.LENGTH_LONG).show();
        } else if (id == R.id.kuliah_online) {
            startActivity(new Intent(MainActivity.this, KulinoActivity.class));

        } else if (id == R.id.jadwal_waldos) {
            startActivity(new Intent(MainActivity.this, WaldosActivity.class));

        } else if (id == R.id.info_akademik) {
            startActivity(new Intent(MainActivity.this, InfoActivity.class));

        } else if (id == R.id.kalender_akademik) {
            startActivity(new Intent(MainActivity.this, InfoFragment.class));

        } else if (id == R.id.tagihan_keuangan) {
            startActivity(new Intent(MainActivity.this, KeuanganFragment.class));

        } else if (id == R.id.riwayat_keuangan) {
            startActivity(new Intent(MainActivity.this, DataKeuangan.class));

        } else if (id == R.id.download) {
            startActivity(new Intent(MainActivity.this, DownloadActivity.class));

        } else if (id == R.id.absensi) {
            Intent i = new Intent(MainActivity.this, MatkulActivity.class);
            i.putExtra("pilihan", "1");
            startActivity(i);

        } else if (id == R.id.repository) {
            Intent i = new Intent(MainActivity.this, RepositoryActivity.class);
            startActivity(i);

        } else if (id == R.id.teman_sekelas) {
            Intent i = new Intent(MainActivity.this, MatkulActivity.class);
            i.putExtra("pilihan", "2");
            startActivity(i);

        /*} else if (id == R.id.ipk) {
            Intent i = new Intent(MainActivity.this, IPKActivity.class);
            startActivity(i);*/

        } else if (id == R.id.logout) {
            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit();
            editor.putBoolean("login", false);
            editor.apply();

            SharedPreferences share = getSharedPreferences("mahasiswa", MODE_PRIVATE);
            SharedPreferences.Editor save = share.edit();
            save.putString("nim", "");
            save.putString("pass", "");
            save.apply();

            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void displayMap() {
        Uri gmmIntentUri = Uri.parse("google.navigation:q=-6.982635,110.409200");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }


    private void getData(){
        SharedPreferences share = getSharedPreferences("mahasiswa", MODE_PRIVATE);
        String nim = share.getString("nim","");
        String login = share.getString("login","");
        final StringRequest request = new StringRequest(Request.Method.GET, UtilsActivity.URL_KULIAH+nim+"&login="+login,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            iniData(response);
                            //mProgressDialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(MainActivity.this, "Eror "+e,Toast.LENGTH_LONG).show();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void iniData(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            // ini utk mengambil attribute array yg ada di json (yaitu attribute data)

            String statuss = jsonObject.getString("status");
            waktu = jsonObject.getString("time");

            if(statuss.equals("1")){ /// WAKTU HABIS
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Waktu Habis");
                builder.setMessage("Silahkan Mulai Kembali Aplikasi Ini");
                builder.setCancelable(false);
                builder.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        startActivity(new Intent(MainActivity.this, Login2Activity.class));
                        finish();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }else if(statuss.equals("2")){ /// MAINTENANCE
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Perhatian");
                builder.setMessage("Maaf Aplikasi ini sedang Maintenance :) ");
                builder.setCancelable(false);
                builder.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {

                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit();
                        editor.putBoolean("login", false);
                        editor.apply();

                        SharedPreferences share = getSharedPreferences("mahasiswa", MODE_PRIVATE);
                        SharedPreferences.Editor save = share.edit();
                        save.putString("nim", "");
                        save.putString("pass", "");
                        save.apply();

                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        finish();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }else{

                JSONArray jsonArray = jsonObject.getJSONArray("result");
                String popup = jsonObject.getString("popup");
                jumlah = jsonArray.length();
                //Toast.makeText(MainActivity.this,""+jumlah,Toast.LENGTH_LONG).show();

                //looping utk array
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject data = jsonArray.getJSONObject(i);

                    matkul[i] = data.getString("matkul");
                    kelompok[i] = data.getString("kelompok");

                    hari_1[i] = data.getString("hari_1");
                    jam_1[i] = data.getString("jam_1");
                    sampai_1[i] = data.getString("sampai_1");
                    ruang_1[i] = data.getString("ruang_1");

                    hari_2[i] = data.getString("hari_2");
                    jam_2[i] = data.getString("jam_2");
                    sampai_2[i] = data.getString("sampai_2");
                    ruang_2[i] = data.getString("ruang_2");

                    hari_3[i] = data.getString("hari_3");
                    jam_3[i] = data.getString("jam_3");
                    sampai_3[i] = data.getString("sampai_3");
                    ruang_3[i] = data.getString("ruang_3");

                    absen[i] = data.getString("absen");

                }

                SharedPreferences share = getSharedPreferences("mahasiswa", MODE_PRIVATE);
                String nim = share.getString("nim","");
                String login = share.getString("login","");

                inflater = getLayoutInflater();
                dialogView = inflater.inflate(R.layout.item_popup, null);
                WebView pop = (WebView) dialogView.findViewById(R.id.web_popup);
                //web.getSettings().setBuiltInZoomControls(true);
                pop.getSettings().setJavaScriptEnabled(true);
                pop.setWebViewClient(new WebViewClient());
                pop.loadUrl(UtilsActivity.DOMAIN+"/info.php?login="+login);

                builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(dialogView);
                builder.setCancelable(true);
                builder.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                if(popup.equals("1")){
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }
                    }, 750);

                }
                getMatkul();
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getMatkul(){
        //String sekarang = UtilsActivity.now();
        String sekarang = waktu;

        String[] waktu = sekarang.split(";");
        String hari = waktu[0];

        ada = 0;

        int jmin = 86400;
        //Toast.makeText(MainActivity.this, "("+waktu[1]+")-{"+convertJam(waktu[1]+3600)+"}",Toast.LENGTH_LONG).show();
        for (int i=0; i<jumlah;i++){
            if((hari_1[i].equals(hari))){
                if ((convertJam(jam_1[i])<=jmin)&&(convertJam(jam_1[i])>=(convertJam(waktu[1])-1800))) {
                    jmin = convertJam(jam_1[i]);
                    temp = i;
                    ada = 1;
                    jadwal = 1;
                }
            }
            if((hari_2[i].equals(hari))){
                if ((convertJam(jam_2[i])<=jmin)&&(convertJam(jam_2[i])>=(convertJam(waktu[1])-1800))) {
                    jmin = convertJam(jam_2[i]);
                    temp = i;
                    ada = 1;
                    jadwal = 2;
                }
            }
            if ((hari_3[i].equals(hari))){
                if((convertJam(jam_3[i])<=jmin)&&(convertJam(jam_3[i])>=(convertJam(waktu[1])-1800))) {
                    jmin = convertJam(jam_3[i]);
                    temp = i;
                    ada = 1;
                    jadwal = 3;
                }
            }
        }
        count = 99;
        if(ada==0){
            count = 99;
            textView.setText("Tidak Ada kelas hari ini");
            //textView2.setText("");
        }else if(ada==1) {// ADA KELAS
            if (jadwal == 1){
                textView.setText(matkul[temp]);
                textView2.setText(hari_1[temp]+" "+jam_1[temp]+"-"+sampai_1[temp]+" / "+ruang_1[temp]+" ("+kelompok[temp]+" )");
                judul = (matkul[temp]);
                //konteks = (hari_1[temp]+" "+jam_1[temp]+"-"+sampai_1[temp]+" / "+ruang_1[temp]+" ("+kelompok[temp]+" )");
                //waktuu = convertJam(jam_1[temp]);
            }else if (jadwal == 2){
                textView.setText(matkul[temp]);
                textView2.setText(hari_2[temp]+" "+jam_2[temp]+"-"+sampai_2[temp]+" / "+ruang_2[temp]+" ("+kelompok[temp]+" )");
                judul = (matkul[temp]);
                //konteks = (hari_2[temp]+" "+jam_2[temp]+"-"+sampai_2[temp]+" / "+ruang_2[temp]+" ("+kelompok[temp]+" )");
                //waktuu = convertJam(jam_2[temp]);
            }else if (jadwal == 3){
                textView.setText(matkul[temp]);
                textView2.setText(hari_3[temp]+" "+jam_3[temp]+"-"+sampai_3[temp]+" / "+ruang_3[temp]+" ("+kelompok[temp]+" )");
                judul = (matkul[temp]);
                //konteks = (hari_3[temp]+" "+jam_3[temp]+"-"+sampai_3[temp]+" / "+ruang_3[temp]+" ("+kelompok[temp]+" )");
                //waktuu = convertJam(jam_3[temp]);
            }
        }
    }


    public void lihatkelas(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.item_kelas, null);

        TextView t_matkul = (TextView) dialogView.findViewById(R.id.matkul);
        TextView t_kelompok = (TextView) dialogView.findViewById(R.id.kelompok);
        TextView t_jadwal = (TextView) dialogView.findViewById(R.id.jadwal);
        TextView t_absen = (TextView) dialogView.findViewById(R.id.persen);

        if(ada==1) {
            ProgressBar progress = (ProgressBar) dialogView.findViewById(R.id.absen);

            String[] absenss = absen[temp].split("\\.");
            int absennn = 0;
            absennn = Integer.parseInt(absenss[0]);


            if (absennn >= 75) {
                //t_absen.setTextColor(Color.rgb(25, 175, 75)); // HIJAU
                t_absen.setTextColor(getResources().getColor(R.color.absen_hijau));
                progress.getProgressDrawable().setColorFilter(ContextCompat.getColor(this, R.color.absen_hijau), PorterDuff.Mode.MULTIPLY);
            } else if (absennn >= 50) {
                //t_absen.setTextColor(Color.rgb(255, 150, 50)); // KUNING
                t_absen.setTextColor(getResources().getColor(R.color.absen_kuning));
                progress.getProgressDrawable().setColorFilter(ContextCompat.getColor(this, R.color.absen_kuning), PorterDuff.Mode.MULTIPLY);
            } else {
                //t_absen.setTextColor(Color.rgb(200, 30, 30)); // MERAH
                t_absen.setTextColor(getResources().getColor(R.color.absen_merah));
                progress.getProgressDrawable().setColorFilter(ContextCompat.getColor(this, R.color.absen_merah), PorterDuff.Mode.MULTIPLY);
            }

            progress.setMax(100);
            progress.setProgress(absennn);
            progress.setBackgroundColor(Color.WHITE);

            if (jadwal == 1) {
                t_matkul.setText(matkul[temp]);
                t_kelompok.setText("Kelompok " + kelompok[temp]);
                t_jadwal.setText(hari_1[temp] + " " + jam_1[temp] + "-" + sampai_1[temp] + " (" + ruang_1[temp] + " )");
                t_absen.setText(absen[temp]);
            } else if (jadwal == 2) {
                t_matkul.setText(matkul[temp]);
                t_kelompok.setText("Kelompok " + kelompok[temp]);
                t_jadwal.setText(hari_2[temp] + " " + jam_2[temp] + "-" + sampai_2[temp] + " (" + ruang_2[temp] + " )");
                t_absen.setText(absen[temp]);
            } else if (jadwal == 3) {
                t_matkul.setText(matkul[temp]);
                t_kelompok.setText("Kelompok " + kelompok[temp]);
                t_jadwal.setText(hari_3[temp] + " " + jam_3[temp] + "-" + sampai_3[temp] + " (" + ruang_3[temp] + " )");
                t_absen.setText(absen[temp]);
            }

            //builder.setMessage("Maaf Aplikasi ini sedang Maintenance :) ");
            builder.setView(dialogView);
            builder.setCancelable(true);
            /*builder.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int j) {
                    dialog.dismiss();
                }
            });*/
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }


    public int convertJam(String waktu){
        int jam, menit;
        int hasil = 0;
        String[] jamm = waktu.split("\\.");
        jam = Integer.parseInt(jamm[0]);
        menit = Integer.parseInt(jamm[1]);
        hasil = (3600*jam)+(60*menit);

        return hasil;
    }

}
