package com.ahmadrofiul.siunlimited;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class TugasActivity extends AppCompatActivity {

    private Button add;
    private ListView listView;
    private Spinner spinner;
    private ArrayList<String> a_matkul = new ArrayList<String>();
    String [] matkul, deadline, tugas, tampil;
    private LayoutInflater inflater;
    private View dialogView;
    private EditText txt_tugas;
    private TextView txt_tanggal;
    private SimpleDateFormat dateFormatter;
    private AlertDialog.Builder builder, builder2;
    private int jumlah=100;
    DatabaseHandler dbcenter, dbhelper;
    protected Cursor cursor;
    ProgressDialog mProgressDialog;
    String matkull,trash;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tugas);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading ...");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

        builder = new AlertDialog.Builder(TugasActivity.this);
        builder2 = new AlertDialog.Builder(TugasActivity.this);


        getData();
        listView = (ListView) findViewById(R.id.tugas);

        final ArrayAdapter<String> a1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, a_matkul);
        a_matkul.add("--- PILIH MATA KULIAH ---");
        /*a_matkul.add("DASAR PEMROGRAMAN");
        a_matkul.add("ALGORITMA PEMPROGRAMAN");
        a_matkul.add("PENDIDIKAN PANCASILA");
        a_matkul.add("PEMROGRAMAN WEB LANJUT");
        a_matkul.add("PENGOLAHAN CITRA DIGITAL");
        a_matkul.add("KALKULUS");*/


        //dateFormatter = new SimpleDateFormat("EEEE, dd-MM-yyyy", Locale.US);
        dateFormatter = new SimpleDateFormat("EEEE, dd-MM-yyyy");



        add = (Button)findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder = new AlertDialog.Builder(TugasActivity.this);
                builder.setTitle("Tambah Tugas");
                //builder.setCancelable(false);
                inflater = getLayoutInflater();
                dialogView = inflater.inflate(R.layout.form_tugas, null);
                builder.setView(dialogView);
                //builder.setCancelable(false);
                spinner = (Spinner)dialogView.findViewById(R.id.matkul);
                txt_tugas = (EditText) dialogView.findViewById(R.id.tugas);
                txt_tanggal= (TextView) dialogView.findViewById(R.id.kalender);
                txt_tanggal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showDateDialog();
                    }
                });
                spinner.setAdapter(a1);

                builder.setPositiveButton("Tambah", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String mtk = spinner.getSelectedItem().toString();
                        final String tgs = txt_tugas.getText().toString();
                        final String tgl = txt_tanggal.getText().toString(); ;

                        if((spinner.getSelectedItem().toString().equals("--- PILIH MATA KULIAH ---"))||(txt_tanggal.getText().toString().equals("Klik untuk tanggal"))||(txt_tugas.getText().toString().equals(""))){
                            Toast.makeText(TugasActivity.this, "Maaf form masih kosong",Toast.LENGTH_SHORT).show();
                        }else {
                            SQLiteDatabase db = dbhelper.getWritableDatabase();
                            db.execSQL("insert into data (matkul, deadline, tugas) values('" +
                                    spinner.getSelectedItem().toString() + "','" +
                                    txt_tanggal.getText().toString() + "','" +
                                    txt_tugas.getText().toString() + "')");

                            dialog.dismiss();
                            //Toast.makeText(TugasActivity.this,mtk+"\n"+tgs+"\n"+tgl,Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(TugasActivity.this, TugasActivity.class));
                            finish();
                        }

                    }
                });


                builder.create().show();

            }
        });

        dbcenter = new DatabaseHandler(this);
        dbhelper = new DatabaseHandler(this);


        tampilData();


    }

    private void tampilData(){

        /*tampil = new String[banyak];
        matkul= new String[banyak];
        deadline = new String[banyak];
        tugas = new String[banyak];

        for(int i=0;i<banyak;i++){
            matkul[i]="PEMROGRAMAN PERANGKAT BERGERAK"; deadline[i]="Senin 20 Agustus 2019"; tugas[i]="Kerja Kelompokkk";
        }*/


        //======================================================================================================






        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM data",null);
        tampil = new String[cursor.getCount()];
        matkul= new String[cursor.getCount()];
        deadline = new String[cursor.getCount()];
        tugas = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            matkul[cc] = cursor.getString(0).toString();
            deadline[cc] = cursor.getString(1).toString();
            tugas[cc] = cursor.getString(2).toString();

        }







        //=========================================================================================================

        for(int i=0; i<cursor.getCount();i++){
            if(i<9){
                tampil[i]=(i+1)+".\t\t"+deadline[i];
            }else if(i<99){
                tampil[i]=(i+1)+".\t"+deadline[i];
            }else{
                tampil[i]=(i+1)+".\t"+deadline[i];
            }
        }

        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, tampil));
        listView.setSelected(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView arg0, View arg1, final int arg2, long arg3) {
                String pesan =
                        "Deadline  :  "+deadline[arg2]+"\n\n"+
                        "Tugasnya :\n"+tugas[arg2];
                builder = new AlertDialog.Builder(TugasActivity.this);
                builder.setTitle(""+matkul[arg2]);
                builder.setMessage(pesan);
                //builder.setCancelable(false);
                builder.setPositiveButton("Kembali", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Hapus", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        builder2 = new AlertDialog.Builder(TugasActivity.this);
                        builder2.setTitle("PERHATIAN");
                        builder2.setMessage("Apakah ingin menghapus tugas?");
                        builder2.setCancelable(false);
                        builder2.setPositiveButton("Ya", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                db.execSQL("delete from data where matkul = '"+matkul[arg2]+"' and deadline = '"+deadline[arg2]+"' and tugas = '"+tugas[arg2]+"' ");
                                startActivity(new Intent(TugasActivity.this, TugasActivity.class));
                                finish();
                            }
                        });
                        builder2.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();

                            }
                        });

                        builder2.create().show();

                    }
                });
                builder.create().show();
            }});
        ((ArrayAdapter)listView .getAdapter()).notifyDataSetInvalidated();

    }

    private void showDateDialog(){
        Calendar newCalendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                txt_tanggal.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
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
                            mProgressDialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(TugasActivity.this, "Eror "+e,Toast.LENGTH_LONG).show();
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

            if(statuss.equals("1")){
                AlertDialog.Builder builder = new AlertDialog.Builder(TugasActivity.this);
                builder.setTitle("Waktu Habis");
                builder.setMessage("Silahkan Mulai Kembali Aplikasi Ini");
                builder.setCancelable(false);
                builder.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        startActivity(new Intent(TugasActivity.this, LoginActivity.class));
                        finish();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }else {

                JSONArray jsonArray = jsonObject.getJSONArray("result");
                jumlah = jsonArray.length();
                //Toast.makeText(getActivity(),""+jumlah,Toast.LENGTH_LONG).show();


                //looping utk array
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject data = jsonArray.getJSONObject(i);

                    matkull = data.getString("matkul");
                    trash = data.getString("kelompok");
                    trash = data.getString("hari_1");
                    trash = data.getString("jam_1");
                    trash = data.getString("sampai_1");
                    trash = data.getString("ruang_1");

                    trash = data.getString("hari_2");
                    trash = data.getString("jam_2");
                    trash = data.getString("sampai_2");
                    trash = data.getString("ruang_2");

                    trash = data.getString("hari_3");
                    trash = data.getString("jam_3");
                    trash = data.getString("sampai_3");
                    trash = data.getString("ruang_3");

                    trash = data.getString("absen");
                    a_matkul.add(matkull);

                }

                //Toast.makeText(getActivity(), matkul[1],Toast.LENGTH_LONG).show();
                //textView.setText(spp_skr+" = "+sks_skr+" = "+poliklinik_skr+" = "+kewajiban+" = "+status+" = "+dicatat);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
