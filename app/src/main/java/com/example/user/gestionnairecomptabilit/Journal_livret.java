package com.example.user.gestionnairecomptabilit;

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

public class Journal_livret extends AppCompatActivity
    {
    private Button btn_return, btn_valider, btn_journallivret;
    private EditText et_date1, et_date2, et_date3, et_date4, et_date5, et_date6, et_date7, et_date8, et_date9, et_date10;
    private EditText et_des1, et_des2, et_des3, et_des4, et_des5, et_des6, et_des7, et_des8, et_des9, et_des10;
    private EditText et_debit1, et_debit2, et_debit3, et_debit4, et_debit5, et_debit6, et_debit7, et_debit8, et_debit9, et_debit10;
    private EditText et_credit1, et_credit2, et_credit3, et_credit4, et_credit5, et_credit6, et_credit7, et_credit8, et_credit9, et_credit10;
    private String date1Livret, date2Livret, date3Livret, date4Livret, date5Livret, date6Livret, date7Livret, date8Livret, date9Livret, date10Livret;
    private String des1Livret, des2Livret, des3Livret, des4Livret, des5Livret, des6Livret, des7Livret, des8Livret, des9Livret, des10Livret;
    private String debit1Livret, debit2Livret, debit3Livret, debit4Livret, debit5Livret, debit6Livret, debit7Livret, debit8Livret, debit9Livret, debit10Livret;
    private String credit1Livret, credit2Livret, credit3Livret, credit4Livret, credit5Livret, credit6Livret, credit7Livret, credit8Livret, credit9Livret, credit10Livret;
    private String result = ""; String j= "1";
    private int success_count, line_count;
    private TextView tv_date;

    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_journal_livret );


        success_count = 0;
        line_count = 0;
        btn_return = findViewById( R.id.btn_return );
        btn_valider = findViewById( R.id.btn_valider );
        btn_journallivret = findViewById( R.id.btn_journallivret );
        tv_date = findViewById( R.id.tv_date );
        et_date1 = findViewById( R.id.livret_date1 );
        et_date2 = findViewById( R.id.livret_date2 );
        et_date3 = findViewById( R.id.livret_date3 );
        et_date4 = findViewById( R.id.livret_date4 );
        et_date5 = findViewById( R.id.livret_date5 );
        et_date6 = findViewById( R.id.livret_date6 );
        et_date7 = findViewById( R.id.livret_date7 );
        et_date8 = findViewById( R.id.livret_date8 );
        et_date9 = findViewById( R.id.livret_date9 );
        et_date10 = findViewById( R.id.livret_date10 );
        et_des1 = findViewById( R.id.livret_des1 );
        et_des2 = findViewById( R.id.livret_des2 );
        et_des3 = findViewById( R.id.livret_des3 );
        et_des4 = findViewById( R.id.livret_des4 );
        et_des5 = findViewById( R.id.livret_des5 );
        et_des6 = findViewById( R.id.livret_des6 );
        et_des7 = findViewById( R.id.livret_des7 );
        et_des8 = findViewById( R.id.livret_des8 );
        et_des9 = findViewById( R.id.livret_des9 );
        et_des10 = findViewById( R.id.livret_des10 );
        et_debit1 = findViewById( R.id.livret_debit1 );
        et_debit2 = findViewById( R.id.livret_debit2 );
        et_debit3 = findViewById( R.id.livret_debit3 );
        et_debit4 = findViewById( R.id.livret_debit4 );
        et_debit5 = findViewById( R.id.livret_debit5 );
        et_debit6 = findViewById( R.id.livret_debit6 );
        et_debit7 = findViewById( R.id.livret_debit7 );
        et_debit8 = findViewById( R.id.livret_debit8 );
        et_debit9 = findViewById( R.id.livret_debit9 );
        et_debit10 = findViewById( R.id.livret_debit10 );
        et_credit1 = findViewById( R.id.livret_credit1 );
        et_credit2 = findViewById( R.id.livret_credit2 );
        et_credit3 = findViewById( R.id.livret_credit3 );
        et_credit4 = findViewById( R.id.livret_credit4 );
        et_credit5 = findViewById( R.id.livret_credit5 );
        et_credit6 = findViewById( R.id.livret_credit6 );
        et_credit7 = findViewById( R.id.livret_credit7 );
        et_credit8 = findViewById( R.id.livret_credit8 );
        et_credit9 = findViewById( R.id.livret_credit9 );
        et_credit10 = findViewById( R.id.livret_credit10 );

        Date now = new Date();

        DateFormat dateformatter = DateFormat.getDateInstance(DateFormat.SHORT);
        String formattedDate = dateformatter.format(now);

        tv_date.setText(formattedDate);

        btn_valider.setOnClickListener( new View.OnClickListener()
            {
            @Override
            public void onClick(View v)
                {

                String type = "register";
                date1Livret = et_date1.getText().toString();
                des1Livret = et_des1.getText().toString();
                debit1Livret = et_debit1.getText().toString();
                credit1Livret = et_credit1.getText().toString();
                date2Livret = et_date2.getText().toString();
                des2Livret = et_des2.getText().toString();
                credit2Livret = et_credit2.getText().toString();
                debit2Livret = et_debit2.getText().toString();
                date3Livret = et_date3.getText().toString();
                date4Livret = et_date4.getText().toString();
                date5Livret = et_date5.getText().toString();
                date6Livret = et_date6.getText().toString();
                date7Livret = et_date7.getText().toString();
                date8Livret = et_date8.getText().toString();
                date9Livret = et_date9.getText().toString();
                date10Livret = et_date10.getText().toString();
                des1Livret = et_des1.getText().toString();
                des2Livret = et_des2.getText().toString();
                des3Livret = et_des3.getText().toString();
                des4Livret = et_des4.getText().toString();
                des5Livret = et_des5.getText().toString();
                des6Livret = et_des6.getText().toString();
                des7Livret = et_des7.getText().toString();
                des8Livret = et_des8.getText().toString();
                des9Livret = et_des9.getText().toString();
                des10Livret = et_des10.getText().toString();
                debit1Livret = et_debit1.getText().toString();
                debit2Livret = et_debit2.getText().toString();
                debit3Livret = et_debit3.getText().toString();
                debit4Livret = et_debit4.getText().toString();
                debit5Livret = et_debit5.getText().toString();
                debit6Livret = et_debit6.getText().toString();
                debit7Livret = et_debit7.getText().toString();
                debit8Livret = et_debit8.getText().toString();
                debit9Livret = et_debit9.getText().toString();
                debit10Livret = et_debit10.getText().toString();
                credit1Livret = et_credit1.getText().toString();
                credit2Livret = et_credit2.getText().toString();
                credit3Livret = et_credit3.getText().toString();
                credit4Livret = et_credit4.getText().toString();
                credit5Livret = et_credit5.getText().toString();
                credit6Livret = et_credit6.getText().toString();
                credit7Livret = et_credit7.getText().toString();
                credit8Livret = et_credit8.getText().toString();
                credit9Livret = et_credit9.getText().toString();
                credit10Livret = et_credit10.getText().toString();

                if (des1Livret.isEmpty()||date1Livret.isEmpty())
                    {
                    }
                else
                    {
                    line_count += 1;
                    new Journal_livret.Register_row_livret().execute( type, date1Livret, des1Livret, credit1Livret, debit1Livret, j );
                    }
                if (des2Livret.isEmpty()||date2Livret.isEmpty())
                    {
                    }
                else
                    {
                    line_count += 1;
                    new Journal_livret.Register_row_livret().execute( type, date2Livret, des2Livret, credit2Livret, debit2Livret, j );
                    }
                if (des3Livret.isEmpty()||date3Livret.isEmpty())
                    {
                    }
                else
                    {
                    line_count += 1;
                    new Journal_livret.Register_row_livret().execute( type, date3Livret, des3Livret, credit3Livret, debit3Livret, j );
                    }
                if (des4Livret.isEmpty()||date4Livret.isEmpty())
                    {
                    }
                else
                    {
                    line_count += 1;
                    new Journal_livret.Register_row_livret().execute( type, date4Livret, des4Livret, credit4Livret, debit4Livret, j );
                    }
                if (des5Livret.isEmpty()||date5Livret.isEmpty())
                    {
                    }
                else
                    {
                    line_count += 1;
                    new Journal_livret.Register_row_livret().execute( type, date5Livret, des5Livret, credit5Livret, debit5Livret, j );
                    }
                if (des6Livret.isEmpty()||date6Livret.isEmpty())
                    {
                    }
                else
                    {
                    line_count += 1;
                    new Journal_livret.Register_row_livret().execute( type, date6Livret, des6Livret, credit6Livret, debit6Livret, j );
                    }
                if (des7Livret.isEmpty()||date7Livret.isEmpty())
                    {
                    }
                else
                    {
                    line_count += 1;
                    new Journal_livret.Register_row_livret().execute( type, date7Livret, des7Livret, credit7Livret, debit7Livret, j );
                    }
                if (des8Livret.isEmpty()||date8Livret.isEmpty())
                    {
                    }
                else
                    {
                    line_count += 1;
                    new Journal_livret.Register_row_livret().execute( type, date8Livret, des8Livret, credit8Livret, debit8Livret, j );
                    }
                if (des9Livret.isEmpty()||date9Livret.isEmpty())
                    {
                    }
                else
                    {
                    line_count += 1;
                    new Journal_livret.Register_row_livret().execute( type, date9Livret, des9Livret, credit9Livret, debit9Livret, j );
                    }
                if (des10Livret.isEmpty()||date10Livret.isEmpty())
                    {
                    }
                else
                    {
                    line_count += 1;
                    new Journal_livret.Register_row_livret().execute( type, date10Livret, des10Livret, credit10Livret, debit10Livret , j);
                    }
                Log.d( "Michel", String.valueOf( success_count ) );


                new AlertDialog.Builder( Journal_livret.this ).setIcon( R.drawable.ok ).setTitle("Enregistrement..." ).
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
                        Intent assosActivity = new Intent (Journal_livret.this, Assos.class);
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
                new AlertDialog.Builder( Journal_livret.this ).setIcon( R.drawable.attention2 ).setTitle( "ATTENTION" ).setMessage
                        ( "Les informations saisies ne sont pas sauvegardés. Retourner à la page précédente?" ).setPositiveButton
                        ( "OUI", new DialogInterface.OnClickListener()
                            {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                                {
                                Intent assos = new Intent( Journal_livret.this, Assos.class );
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
        btn_journallivret.setOnClickListener( new View.OnClickListener(){

        @Override
        public void onClick(View v)
            {
            Intent journal = new Intent (Journal_livret.this, Journal.class);
            startActivity( journal );
            }
        }  );
        }
    public class Register_row_livret extends AsyncTask<String, Void, String>
        {
        AlertDialog alertDialog;

        @Override
        protected synchronized String doInBackground(String... params)
            {
            String type = params[0];
            String date1C = params[1];
            String des1C = params[2];
            String credit1C = params[3];
            String debit1C = params[4];
            String id = params[5];

            String register_url = "https://albi-projects.000webhostapp.com/postLivretWH.php";
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
