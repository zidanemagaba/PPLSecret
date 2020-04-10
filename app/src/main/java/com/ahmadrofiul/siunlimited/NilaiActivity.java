package com.ahmadrofiul.siunlimited;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class NilaiActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;
    private int jumlah;
    ListView listView;
    private String [] matkul, kode, sks, nilai, tampil, detail1, detail2;
    private AlertDialog.Builder builder;

    private BarChart barChart;

    LineChartView lineChartView;
    String[] axisData = {"1", "2", "3", "4", "5", "6", "7"};
    float[] yAxisData = {3.30f, 3.20f, 2.61f, 2.71f, 3.36f, 3.09f, 2.30f};
    float down = 2.3f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nilai);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading ...");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

        /*jumlah = 9999;
        matkul= new String[jumlah];
        kode = new String[jumlah];
        sks = new String[jumlah];
        nilai = new String[jumlah];
        for (int i=0;i<jumlah;i++){ matkul[i]= " ";kode[i]= " ";sks[i]= " ";nilai[i]= " "; }*/

        builder = new AlertDialog.Builder(NilaiActivity.this);
        getData();

        /*barChart = (BarChart) findViewById(R.id.ipk);
        ArrayList<BarEntry> ipk = new ArrayList<>();

        ipk.add(new BarEntry(3.30f, 0));
        ipk.add(new BarEntry(3.20f, 1));
        ipk.add(new BarEntry(2.61f, 2));
        ipk.add(new BarEntry(2.71f, 3));
        BarDataSet dataSet1 = new BarDataSet(ipk, "Ips");
        dataSet1.setColor(ColorTemplate.JOYFUL_COLORS[0]);

        ArrayList<String> semester = new ArrayList<>();
        semester.add("1");
        semester.add("2");
        semester.add("3");
        semester.add("4");

        BarData barData = new BarData(semester,dataSet1);
        barChart.setData(barData);

        /////==========================================================================================

        lineChartView = findViewById(R.id.chart);

        List yAxisValues = new ArrayList();
        List axisValues = new ArrayList();

        Line line = new Line(yAxisValues).setColor(Color.parseColor("#9C27B0"));

        for (int i = 0; i < axisData.length; i++) {
            axisValues.add(i, new AxisValue(i).setLabel(axisData[i]));
        }
        for (int i = 0; i < yAxisData.length; i++) {
            yAxisValues.add(new PointValue(i, yAxisData[i]));
        }

        List lines = new ArrayList();
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);

        Axis axis = new Axis();
        axis.setValues(axisValues);
        axis.setTextSize(8);
        axis.setTextColor(Color.parseColor("#03A9F4"));
        data.setAxisXBottom(axis);

        Axis yAxis = new Axis();
        yAxis.setName("Indeks Prestasi Kumulatif");
        yAxis.setTextColor(Color.parseColor("#03A9F4"));
        yAxis.setTextSize(8);
        data.setAxisYLeft(yAxis);

        lineChartView.setLineChartData(data);
        Viewport viewport = new Viewport(lineChartView.getMaximumViewport());
        viewport.top = 4.1f;
        viewport.bottom = down-0.2f;
        lineChartView.setMaximumViewport(viewport);
        lineChartView.setCurrentViewport(viewport);*/
    }

    private void getData(){
        SharedPreferences share = getSharedPreferences("mahasiswa", MODE_PRIVATE);
        String nim = share.getString("nim","");
        String login = share.getString("login","");
        final StringRequest request = new StringRequest(Request.Method.GET, UtilsActivity.URL_NILAI+nim+"&login="+login,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            iniData(response);
                            mProgressDialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(NilaiActivity.this, "Eror "+e,Toast.LENGTH_LONG).show();
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

            String status = jsonObject.getString("status");

            if(status.equals("0")){
                AlertDialog.Builder builder = new AlertDialog.Builder(NilaiActivity.this);
                builder.setTitle("Perhatian");
                builder.setMessage("Maaf, daftar nilai belum tersedia");
                builder.setCancelable(false);
                builder.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        //startActivity(new Intent(NilaiActivity.this, LoginActivity.class));
                        finish();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }else if(status.equals("2")){
                AlertDialog.Builder builder = new AlertDialog.Builder(NilaiActivity.this);
                builder.setTitle("Perhatian");
                builder.setMessage("Maaf, menu ini sedang Maintenance");
                //builder.setCancelable(false);
                builder.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        finish();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }else{
                JSONArray jsonArray = jsonObject.getJSONArray("result");
                jumlah = jsonArray.length();
                //Toast.makeText(StokActivity.this,""+jumlah,Toast.LENGTH_SHORT).show();

                matkul= new String[jumlah+2];
                kode = new String[jumlah+2];
                sks = new String[jumlah+2];
                nilai = new String[jumlah+2];

                //looping utk array
                for(int i=0; i<jsonArray.length(); i++){
                    //get json berdasarkan banyaknya data (index i)
                    JSONObject data = jsonArray.getJSONObject(i);

                    kode[i]= data.getString("kelompok");
                    matkul[i]= data.getString("matkul");
                    sks[i]= data.getString("sks");
                    nilai[i]= data.getString("nilai");
                }

                matkul[jumlah] = jsonObject.getString("nilai");
                matkul[jumlah+1] = jsonObject.getString("ipk");

                tampilData(jumlah+2);
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void tampilData(int j){

        /*for(int i=0;i<jumlah;i++){
            matkul[i]="PEMROGRAMAN PERANGKAT BERGERAK"; kode[i]="N201702"; sks[i]="3"; nilai[i]="A";
        }*/

        listView = (ListView) findViewById(R.id.listView);
        tampil = new String[j];

        for(int i=0; i<(j-2);i++){

            /*if(i<9){
                tampil[i]=(i+1)+".\t\t"+kode[i]+" \t "+nilai[i];
            }else if(i<99){
                tampil[i]=(i+1)+".\t"+kode[i]+" \t "+nilai[i];
            }else {
                tampil[i]=(i+1)+".\t"+kode[i]+" \t "+nilai[i];
            }*/

            tampil[i]=matkul[i]+" ("+nilai[i]+")";
        }

        /*tampil[jumlah] = matkul[jumlah];
        tampil[jumlah+1] = matkul[jumlah+1];*/


        detail1 = matkul[jumlah].split("; ");
        //tampil[jumlah] = detail1[0]+" "+detail1[1]+" "+detail1[2]+" "+detail1[3]+" "+detail1[4]+" "+detail1[5]+" "+detail1[6]+" "+detail1[7];
        tampil[jumlah] = "DETAIL REKAP NILAI";
        kode[jumlah] = "DETAIL REKAP NILAI";
        sks[jumlah] = "Nilai A\t\t=\t\t"+nilai(detail1[0])+"\nNilai AB\t=\t\t"+nilai(detail1[1])+"\nNilai B\t\t=\t\t"+nilai(detail1[2])+"\nNilai BC\t=\t\t"+nilai(detail1[3])+"\nNilai C\t\t=\t\t"+nilai(detail1[4])+"\nNilai D\t\t=\t\t"+nilai(detail1[5])+"\nNilai E\t\t=\t\t"+nilai(detail1[6])+"\nKosong\t=\t\t"+nilai(detail1[7]);



        detail1 = matkul[jumlah+1].split("; ");
        //tampil[jumlah+1] = detail1[0]+" "+detail1[1];
        tampil[jumlah+1] = "DETAIL JUMLAH SKS";
        kode[jumlah+1] = "DETAIL JUMLAH SKS";
        sks[jumlah+1] = "IPK\t\t\t=\t\t"+nilai(detail1[0])+"\nSKS\t\t\t=\t\t"+nilai(detail1[1]);


        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, tampil));
        listView.setSelected(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView arg0, View arg1, final int arg2, long arg3) {
                if(arg2<jumlah){
                    final String tampil = //"id\t\t\t\t\t\t= "+a_id[arg2]+"\n"+
                            "Kode\t\t=  "+kode[arg2]+"\n"+
                            "SKS\t\t\t=  "+sks[arg2]+"\n"+
                            "Nilai\t\t=  "+nilai[arg2];

                    //final CharSequence[] dialogitem = {"Lihat Data"};
                    builder = new AlertDialog.Builder(NilaiActivity.this);
                    builder.setTitle(""+matkul[arg2]);
                    builder.setMessage(tampil);
                    builder.setCancelable(true);
                    builder.setPositiveButton("Kembali", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    builder.create().show();
                }else {
                    //final CharSequence[] dialogitem = {"Lihat Data"};
                    builder = new AlertDialog.Builder(NilaiActivity.this);
                    builder.setTitle(""+kode[arg2]);
                    builder.setMessage(""+sks[arg2]);
                    builder.setCancelable(false);
                    builder.setPositiveButton("Kembali", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    builder.create().show();
                }

            }});
        ((ArrayAdapter)listView .getAdapter()).notifyDataSetInvalidated();

    }

    public static String nilai(String n) {
        String aja;
        String [] detail = n.split("=");
        aja = detail[1];

        return aja;
    }
}
