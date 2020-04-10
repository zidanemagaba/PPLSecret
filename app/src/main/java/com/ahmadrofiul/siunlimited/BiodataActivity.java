package com.ahmadrofiul.siunlimited;

import android.content.SharedPreferences;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

public class BiodataActivity extends AppCompatActivity {

    private String [] biodata, nama;
    //private TextView texview;
    private ListView simpleList;
    private ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biodata);

        SharedPreferences share = getSharedPreferences("mahasiswa", MODE_PRIVATE);
        String nim = share.getString("nim","");
        String[] n;
        n = nim.split("");


        imageview=(ImageView)findViewById(R.id.foto);
        //imageview.setImageResource(R.drawable.foto);
        Glide.with(BiodataActivity.this)
             .load("http://mahasiswa.dinus.ac.id/images/foto/"+n[1]+"/"+n[1]+n[2]+n[3]+"/"+n[5]+n[6]+n[7]+n[8]+"/"+nim+".jpg")
             .placeholder(R.drawable.foto)
             .error(R.drawable.foto)
             .into(imageview);

        //Toast.makeText(BiodataActivity.this,"foto/"+n[1]+"/"+n[1]+n[2]+n[3]+"/"+n[5]+n[6]+n[7]+n[8]+"/"+nim+".jpg",Toast.LENGTH_LONG).show();

        nama = new String [25];
        biodata = new String [25];

        nama[0]="Nomor Induk Mahasiswa";    biodata[0] = getIntent().getStringExtra("nim");
        nama[1]="Nama Lengkap";             biodata[1] = getIntent().getStringExtra("nama");
        nama[2]="Jalur Pendaftaran";        biodata[2] = getIntent().getStringExtra("jalur");
        nama[3]="Status Mahasiswa";         biodata[3] = getIntent().getStringExtra("status");
        nama[4]="Tempat Tanggal Lahir";     biodata[4] = getIntent().getStringExtra("ttl");
        nama[5]="Provinsi Asal";            biodata[5] = getIntent().getStringExtra("prov_a");
        nama[6]="Alamat Asal";              biodata[6] = getIntent().getStringExtra("alamat_a");
        nama[7]="Kodepos Asal";             biodata[7] = getIntent().getStringExtra("kodepos_a");
        nama[8]="Telepon Asal";             biodata[8] = getIntent().getStringExtra("telepon_a");
        nama[9]="Alamat Semarang";          biodata[9] = getIntent().getStringExtra("alamat_s");
        nama[10]="Kodepos Semarang";        biodata[10] = getIntent().getStringExtra("kodepos_s");
        nama[11]="Telepon Semarang";        biodata[11] = getIntent().getStringExtra("telepon_s");
        nama[12]="Hp Mahasiswa";            biodata[12] = getIntent().getStringExtra("hp_mhs");
        nama[13]="Email Mahasiswa";         biodata[13] = getIntent().getStringExtra("email_mhs");
        nama[14]="Email Lain";              biodata[14] = getIntent().getStringExtra("email_lain");
        nama[15]="Warga Negara";            biodata[15] = getIntent().getStringExtra("warga");
        nama[16]="Agama";                   biodata[16] = getIntent().getStringExtra("agama");
        nama[17]="Golongan Darah";          biodata[17] = getIntent().getStringExtra("goldar");
        nama[18]="Status Marital";          biodata[18] = getIntent().getStringExtra("marital");
        nama[19]="Kelas";                   biodata[19] = getIntent().getStringExtra("kelas");
        nama[20]="Dosen Wali";              biodata[20] = getIntent().getStringExtra("waldos");
        nama[21]="Alamat Orang Tua";        biodata[21] = getIntent().getStringExtra("alamat_o");
        nama[22]="TTL Orang Tua";           biodata[22] = getIntent().getStringExtra("ttl_o");
        nama[23]="Provinsi";                biodata[23] = getIntent().getStringExtra("prov");
        nama[24]="Telepon Orang Tua";       biodata[24] = getIntent().getStringExtra("telepon_o");

        /*biodata[0]="A11.2016.01234";
        biodata[1]="INI NAMA KAMU";
        biodata[2]="PMDK III";
        biodata[3]="Aktif Keuangan";
        biodata[4]="SEMARANG, 30 Februari 1990";
        biodata[5]=" - ";
        biodata[6]="JL KEDUNG WUNI BARAT 2 No.36B SEMARANG";
        biodata[7]="50123";
        biodata[8]="(024)6701234";
        biodata[9]="JL KEDUNG WUNI BARAT 2 No.36B SEMARANG";
        biodata[10]="50123";
        biodata[11]="-";
        biodata[12]="081234567897";
        biodata[13]="111201601234@mhs.dinus.ac.id";
        biodata[14]="abcdefghijkl123@gmail.com";
        biodata[15]="WNI";
        biodata[16]="Islam";
        biodata[17]="Tidak tahu";
        biodata[18]="Belum Menikah";
        biodata[19]="Malam";
        biodata[20]="Dosen Walimu, S.E, M.Kom";
        biodata[21]="JL KEDUNG WUNI BARAT 2 No.36B SEMARANG";
        biodata[22]="Surabaya,30 Februari 1969";
        biodata[23]="-";
        biodata[24]="081234567897";*/

        simpleList = (ListView) findViewById(R.id.listView);
        CustomBiodata customAdapter = new CustomBiodata(getApplicationContext(), nama,biodata);
        simpleList.setAdapter(customAdapter);

        /*texview = (TextView)findViewById(R.id.data);
        texview.setText("NIM\t\t= "+biodata[0]+"\n"+
                        "Nama\t\t= "+biodata[1]+"\n"+
                        "Jalur\t\t= "+biodata[2]+"\n"+
                        "Status\t\t= "+biodata[3]+"\n"+
                        "TTL\t\t= "+biodata[4]+"\n"+
                        "Prov. & Kota\t\t= "+biodata[5]+"\n"+
                        "Alamat Asal\t\t= "+biodata[6]+"\n"+
                        "Kode Pos Asal\t\t= "+biodata[7]+"\n"+
                        "Telepon Asal\t\t= "+biodata[8]+"\n"+
                        "Prov. & Kota\t\t= "+biodata[9]+"\n"+
                        "Alamat SMG\t\t= "+biodata[10]+"\n"+
                        "Kode Pos SMG\t\t= "+biodata[11]+"\n"+
                        "Telepon SMG\t\t= "+biodata[12]+"\n"+
                        "No Hp Mhs\t\t= "+biodata[13]+"\n"+
                        "Email Lain\t\t= "+biodata[14]+"\n"+
                        "Warga Negara\t\t= "+biodata[15]+"\n"+
                        "Agama\t\t= "+biodata[16]+"\n"+
                        "Golongan Darah\t\t= "+biodata[17]+"\n"+
                        "Status marital\t\t= "+biodata[18]+"\n"+
                        "Kelas kuliah\t\t= "+biodata[19]+"\n"+
                        "Dosen Wali\t\t= "+biodata[20]+"\n"+
                        "Alamat Ortu\t\t= "+biodata[21]+"\n"+
                        "TTL Ortu\t\t= "+biodata[22]+"\n"+
                        "Kota Ortu\t\t= "+biodata[23]+"\n"+
                        "Telepon Ortu\t\t= "+biodata[24]+"\n");*/

    }
}
