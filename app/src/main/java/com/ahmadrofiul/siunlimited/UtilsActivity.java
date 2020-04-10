package com.ahmadrofiul.siunlimited;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UtilsActivity {

    public static final String DOMAIN = "https://zidanejmagaba.com/SiadinBaru"; ///Domain Zidan
    //public static final String DOMAIN = "http://192.168.43.53/siadin1"; ///Domain KUUU

    ///-------- DIPERHATIKAN KETIKA MAINTENANCE --------///
    public static final String VERSION = "12.0"; ///VERSI CUK
    //public static final String URL_LOGIN = ""+DOMAIN+"/ajson_maintenance.php?nim=";
    //public static final String URL_JAJAL = ""+DOMAIN+"/ajson_json.php?";


    public static final String URL_LOGIN = ""+DOMAIN+"/ajson_login.php?nim=";
    public static final String URL_KRS = ""+DOMAIN+"/json_krs.php?nim=";
    public static final String URL_KHS = ""+DOMAIN+"/json_khs.php?nim=";
    public static final String URL_NILAI = ""+DOMAIN+"/json_nilai.php?nim=";
    public static final String URL_UJIAN = ""+DOMAIN+"/json_ujian.php?nim=";
    public static final String URL_SA = ""+DOMAIN+"/json_sa.php?nim=";
    public static final String URL_UJIANSA = ""+DOMAIN+"/json_ujiansa.php?nim=";
    public static final String URL_REMIDI = ""+DOMAIN+"/json_remidi.php?nim=";


    public static final String URL_BIODATA = ""+DOMAIN+"/json_biodata.php?nim=";
    public static final String URL_DOSEN = ""+DOMAIN+"/json_dosen.php?nim=";
    public static final String URL_TAGIHAN = ""+DOMAIN+"/json_tagihan.php?nim=";
    public static final String URL_KEUANGAN = ""+DOMAIN+"/json_keuangan.php?nim=";
    public static final String URL_KULINO = ""+DOMAIN+"/online.php?nim=";
    public static final String URL_ABSENSI = ""+DOMAIN+"/json_absen.php?nim=";
    public static final String URL_KULIAH = ""+DOMAIN+"/json_kuliah.php?nim=";
    public static final String URL_SEKELAS = ""+DOMAIN+"/json_kelas.php?nim=";
    public static final String URL_IPK = ""+DOMAIN+"/json_ipk.php?nim=";
    public static final String URL_REPOSITORY = ""+DOMAIN+"/json_file.php?nim=";
    public static final String URL_CETAK = ""+DOMAIN+"/pdf.php?";


    public final static String now() {
        Calendar ci = Calendar.getInstance();

        int j=0;

        SimpleDateFormat df;

        df = new SimpleDateFormat("EEEE;HH.mm;MM;yyyy");

        if(j==1){ df = new SimpleDateFormat("EEEE, dd/MM/yyyy HH:mm:ss"); }

        //SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm:ss");
        return df.format(ci.getTime());
    }

    public static String hash(String n, String p) {
        Random r = new Random();
        int nn = r.nextInt(n.length());
        int pp = r.nextInt(p.length());
        String[] n1 = n.split("");
        String[] p1 = p.split("");
        String aja;
        aja = md(md(n+""+md(p1[pp]))+""+md(p+""+md(n1[nn])));

        return aja;
    }

    public static String md(String password) {
        String result = "";
        MessageDigest messageDigest;

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes("UTF8"));
            byte message[] = messageDigest.digest();

            for (int i = 0; i < message.length; i++) {
                result += Integer.toHexString((0x000000ff & message[i]) | 0xffffff00).substring(6);
            }

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UtilsActivity.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UtilsActivity.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public static void showNfcSettingsDialog(final Activity app) {
        new AlertDialog.Builder(app)
                .setTitle("NFC is disabled")
                .setMessage("You must enable NFC to use this app.")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        app.startActivity(new Intent(android.provider.Settings.ACTION_NFC_SETTINGS));
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        app.finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public final static String bytesToHex(byte[] bytes) {
        if (bytes == null) return null;

        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
    public final static String bytesToHexAndString(byte[] bytes) {
        if (bytes == null) return null;

        return bytesToHex(bytes) + " (" + new String(bytes) + ")";
    }



}
