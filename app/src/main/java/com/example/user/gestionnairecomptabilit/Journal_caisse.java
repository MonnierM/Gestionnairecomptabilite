package com.example.user.gestionnairecomptabilit;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.Date;

public class Journal_caisse extends AppCompatActivity
    {
    private Button btn_return, btn_valider, btn_journalcaisse;
    private EditText et_date1, et_date2, et_date3, et_date4, et_date5, et_date6, et_date7, et_date8, et_date9, et_date10;
    private EditText et_des1, et_des2, et_des3, et_des4, et_des5, et_des6, et_des7, et_des8, et_des9, et_des10;
    private EditText et_debit1, et_debit2, et_debit3, et_debit4, et_debit5, et_debit6, et_debit7, et_debit8, et_debit9, et_debit10;
    private EditText et_credit1, et_credit2, et_credit3, et_credit4, et_credit5, et_credit6, et_credit7, et_credit8, et_credit9, et_credit10;
    private String date1C, date2C, date3C, date4C, date5C, date6C, date7C, date8C, date9C, date10C;
    private String des1C, des2C, des3C, des4C, des5C, des6C, des7C, des8C, des9C, des10C;
    private String debit1C, debit2C, debit3C, debit4C, debit5C, debit6C, debit7C, debit8C, debit9C, debit10C;
    private String credit1C, credit2C, credit3C, credit4C, credit5C, credit6C, credit7C, credit8C, credit9C, credit10C;
    private String result = ""; String j = "1";
    private static int success_count, line_count;
    private TextView tv_date;

    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_journal_caisse );
        success_count = 0;
        line_count = 0;
        btn_return = findViewById( R.id.btn_return );
        btn_valider = findViewById( R.id.btn_valider );
        btn_journalcaisse = findViewById( R.id.btn_journalcaisse);
        tv_date = findViewById( R.id.tv_annee );
        et_date1 = findViewById( R.id.caisse_date1 );
        et_date2 = findViewById( R.id.caisse_date2 );
        et_date3 = findViewById( R.id.caisse_date3 );
        et_date4 = findViewById( R.id.caisse_date4 );
        et_date5 = findViewById( R.id.caisse_date5 );
        et_date6 = findViewById( R.id.caisse_date6 );
        et_date7 = findViewById( R.id.caisse_date7 );
        et_date8 = findViewById( R.id.caisse_date8 );
        et_date9 = findViewById( R.id.caisse_date9 );
        et_date10 = findViewById( R.id.caisse_date10 );
        et_des1 = findViewById( R.id.caisse_des1 );
        et_des2 = findViewById( R.id.caisse_des2 );
        et_des3 = findViewById( R.id.caisse_des3 );
        et_des4 = findViewById( R.id.caisse_des4 );
        et_des5 = findViewById( R.id.caisse_des5 );
        et_des6 = findViewById( R.id.caisse_des6 );
        et_des7 = findViewById( R.id.caisse_des7 );
        et_des8 = findViewById( R.id.caisse_des8 );
        et_des9 = findViewById( R.id.caisse_des9 );
        et_des10 = findViewById( R.id.caisse_des10 );
        et_debit1 = findViewById( R.id.caisse_debit1 );
        et_debit2 = findViewById( R.id.caisse_debit2 );
        et_debit3 = findViewById( R.id.caisse_debit3 );
        et_debit4 = findViewById( R.id.caisse_debit4 );
        et_debit5 = findViewById( R.id.caisse_debit5 );
        et_debit6 = findViewById( R.id.caisse_debit6 );
        et_debit7 = findViewById( R.id.caisse_debit7 );
        et_debit8 = findViewById( R.id.caisse_debit8 );
        et_debit9 = findViewById( R.id.caisse_debit9 );
        et_debit10 = findViewById( R.id.caisse_debit10 );
        et_credit1 = findViewById( R.id.caisse_credit1 );
        et_credit2 = findViewById( R.id.caisse_credit2 );
        et_credit3 = findViewById( R.id.caisse_credit3 );
        et_credit4 = findViewById( R.id.caisse_credit4 );
        et_credit5 = findViewById( R.id.caisse_credit5 );
        et_credit6 = findViewById( R.id.caisse_credit6 );
        et_credit7 = findViewById( R.id.caisse_credit7 );
        et_credit8 = findViewById( R.id.caisse_credit8 );
        et_credit9 = findViewById( R.id.caisse_credit9 );
        et_credit10 = findViewById( R.id.caisse_credit10 );

        Date now = new Date();

        DateFormat dateformatter = DateFormat.getDateInstance(DateFormat.SHORT);
        String formattedDate = dateformatter.format(now);

        tv_date.setText(formattedDate  );

        btn_valider.setOnClickListener( new View.OnClickListener()
            {
            @Override
            public void onClick(View v)
                {
                String type = "register";
                date1C = et_date1.getText().toString();
                des1C = et_des1.getText().toString();
                debit1C = et_debit1.getText().toString();
                credit1C = et_credit1.getText().toString();
                date2C = et_date2.getText().toString();
                des2C = et_des2.getText().toString();
                credit2C = et_credit2.getText().toString();
                debit2C = et_debit2.getText().toString();
                date3C = et_date3.getText().toString();
                date4C = et_date4.getText().toString();
                date5C = et_date5.getText().toString();
                date6C = et_date6.getText().toString();
                date7C = et_date7.getText().toString();
                date8C = et_date8.getText().toString();
                date9C = et_date9.getText().toString();
                date10C = et_date10.getText().toString();
                des1C = et_des1.getText().toString();
                des2C = et_des2.getText().toString();
                des3C = et_des3.getText().toString();
                des4C = et_des4.getText().toString();
                des5C = et_des5.getText().toString();
                des6C = et_des6.getText().toString();
                des7C = et_des7.getText().toString();
                des8C = et_des8.getText().toString();
                des9C = et_des9.getText().toString();
                des10C = et_des10.getText().toString();
                debit1C = et_debit1.getText().toString();
                debit2C = et_debit2.getText().toString();
                debit3C = et_debit3.getText().toString();
                debit4C = et_debit4.getText().toString();
                debit5C = et_debit5.getText().toString();
                debit6C = et_debit6.getText().toString();
                debit7C = et_debit7.getText().toString();
                debit8C = et_debit8.getText().toString();
                debit9C = et_debit9.getText().toString();
                debit10C = et_debit10.getText().toString();
                credit1C = et_credit1.getText().toString();
                credit2C = et_credit2.getText().toString();
                credit3C = et_credit3.getText().toString();
                credit4C = et_credit4.getText().toString();
                credit5C = et_credit5.getText().toString();
                credit6C = et_credit6.getText().toString();
                credit7C = et_credit7.getText().toString();
                credit8C = et_credit8.getText().toString();
                credit9C = et_credit9.getText().toString();
                credit10C = et_credit10.getText().toString();

                if (des1C.isEmpty()||date1C.isEmpty())
                    {
                    }
                else
                    {
                    line_count += 1;
                    new Register_row().execute( type, date1C, des1C, credit1C, debit1C, j );
                    }
                if (des2C.isEmpty()||date2C.isEmpty())
                    {
                    }
                else
                    {
                    line_count += 1;
                    new Register_row().execute( type, date2C, des2C, credit2C, debit2C, j  );
                    }
                if (des3C.isEmpty()||date3C.isEmpty())
                    {
                    }
                else
                    {
                    line_count += 1;
                    new Register_row().execute( type, date3C, des3C, credit3C, debit3C, j  );
                    }
                if (des4C.isEmpty()||date4C.isEmpty())
                    {
                    }
                else
                    {
                    line_count += 1;
                    new Register_row().execute( type, date4C, des4C, credit4C, debit4C, j  );
                    }
                if (des5C.isEmpty()||date5C.isEmpty())
                    {
                    }
                else
                    {
                    line_count += 1;
                    new Register_row().execute( type, date5C, des5C, credit5C, debit5C, j  );
                    }
                if (des6C.isEmpty()||date6C.isEmpty())
                    {
                    }
                else
                    {
                    line_count += 1;
                    new Register_row().execute( type, date6C, des6C, credit6C, debit6C, j  );
                    }
                if (des7C.isEmpty()||date7C.isEmpty())
                    {
                    }
                else
                    {
                    line_count += 1;
                    new Register_row().execute( type, date7C, des7C, credit7C, debit7C, j  );
                    }
                if (des8C.isEmpty()||date8C.isEmpty())
                    {
                    }
                else
                    {
                    line_count += 1;
                    new Register_row().execute( type, date8C, des8C, credit8C, debit8C, j  );
                    }
                if (des9C.isEmpty()||date9C.isEmpty())
                    {
                    }
                else
                    {
                    line_count += 1;
                    new Register_row().execute( type, date9C, des9C, credit9C, debit9C, j  );
                    }
                if (des10C.isEmpty()||date10C.isEmpty())
                    {
                    }
                else
                    {
                    line_count += 1;
                    new Register_row().execute( type, date10C, des10C, credit10C, debit10C,j );
                    }
                Log.d( "Michel", String.valueOf( success_count ) );


                new AlertDialog.Builder( Journal_caisse.this ).setIcon( R.drawable.ok ).setTitle("Enregistrement..." ).
                setMessage(line_count + " lignes ont été enregistrées avec succès! \n Effectuer une nouvelle saisie?")
                        .setPositiveButton( "Nouvelle saisie", new DialogInterface.OnClickListener()
                            {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                                {
                                et_date1.setText( "" );
                                et_date2.setText( "" );
                                et_date3.setText( "" );
                                et_date4.setText( "" );
                                et_date5.setText( "" );
                                et_date6.setText( "" );
                                et_date7.setText( "" );
                                et_date8.setText( "" );
                                et_date9.setText( "" );
                                et_date10.setText( "" );
                                et_des1.setText( "" );
                                et_des2.setText( "" );
                                et_des3.setText( "" );
                                et_des4.setText( "" );
                                et_des5.setText( "" );
                                et_des6.setText( "" );
                                et_des7.setText( "" );
                                et_des8.setText( "" );
                                et_des9.setText( "" );
                                et_des10.setText( "" );
                                et_debit1.setText( "" );
                                et_debit2.setText( "" );
                                et_debit3.setText( "" );
                                et_debit4.setText( "" );
                                et_debit5.setText( "" );
                                et_debit6.setText( "" );
                                et_debit7.setText( "" );
                                et_debit8.setText( "" );
                                et_debit9.setText( "" );
                                et_debit10.setText( "" );
                                et_credit1.setText( "" );
                                et_credit2.setText( "" );
                                et_credit3.setText( "" );
                                et_credit4.setText( "" );
                                et_credit5.setText( "" );
                                et_credit6.setText( "" );
                                et_credit7.setText( "" );
                                et_credit8.setText( "" );
                                et_credit9.setText( "" );
                                et_credit10.setText( "" );;
                                }
                            } ).setNegativeButton( "Retour à la page précédente", new DialogInterface.OnClickListener()
                            {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                                {
                                Intent assosActivity = new Intent (Journal_caisse.this, Assos.class);
                                startActivity( assosActivity);
                                }
                            } ).show();
                }
            } );


        btn_return.setOnClickListener( new View.OnClickListener()
            {
            @Override
            public void onClick(View v)
                {
                new AlertDialog.Builder( Journal_caisse.this ).setIcon( R.drawable.attention2 ).setTitle( "ATTENTION" ).setMessage
                        ( "Les informations saisies ne sont pas sauvegardés. Retourner à la page précédente?" ).setPositiveButton
                        ( "OUI", new DialogInterface.OnClickListener()
                            {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                                {
                                Intent assos = new Intent( Journal_caisse.this, Assos.class );
                                startActivity( assos );
                                }
                            } ).setNegativeButton( "non", new DialogInterface.OnClickListener()
                    {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                        {
                        }
                    } ).show();
                }
            } );
        btn_journalcaisse.setOnClickListener( new View.OnClickListener(){

        @Override
        public void onClick(View v)
            {
            Intent journal = new Intent (Journal_caisse.this, Journal.class);
            startActivity( journal );
            }
        }  );
        }

    public class Register_row extends AsyncTask<String, Void, String>
        {
        AlertDialog alertDialog;

        @Override
        protected String doInBackground(String... params)
            {
            String type = params[0];
            String date1C = params[1];
            String des1C = params[2];
            String credit1C = params[3];
            String debit1C = params[4];
            String id = params[5];

            String register_url = "https://albi-projects.000webhostapp.com/postCaisseWH.php";
            if (type.equals( "register" ))
                {
                try
                    {
                    URL url = new URL( register_url );
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod( "POST" );
                    httpURLConnection.setDoOutput( true );
                    httpURLConnection.setDoInput( true );
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter( (new OutputStreamWriter( outputStream, "UTF-8" )) );
                    String post_data = URLEncoder.encode( "date", "UTF-8" ) + "=" + URLEncoder.encode( date1C, "UTF-8" ) + "&" +
                            URLEncoder.encode( "description", "UTF-8" ) + "=" + URLEncoder.encode( des1C, "UTF-8" ) + "&" +
                            URLEncoder.encode( "credit", "UTF-8" ) + "=" + URLEncoder.encode( credit1C, "UTF-8" ) + "&" +
                            URLEncoder.encode( "debit", "UTF-8" ) + "=" + URLEncoder.encode( debit1C, "UTF-8" )+ "&" +
                            URLEncoder.encode( "id", "UTF-8" ) + "=" + URLEncoder.encode( id, "UTF-8" );
                    bufferedWriter.write( post_data );
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( inputStream, "UTF-8" ) );
                    String result = "";
                    String line = "";

                    while ((line = bufferedReader.readLine()) != null)
                        {
                        result += line;
                        }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;
                    }
                catch (MalformedURLException e)
                    {
                    e.printStackTrace();
                    }
                catch (IOException e)
                    {
                    e.printStackTrace();
                    }
                }
            return null;
            }
        @Override
        protected synchronized void onPostExecute(String result)
            {
            Log.d("MICHEL",result);
            if (result.contains( "Transactions enregistrées avec succès!" ))
                {
                success_count+=1;
                }
            }
        }
    }