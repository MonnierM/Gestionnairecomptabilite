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

public class Journal_compte extends AppCompatActivity
    {

    private Button btn_return, btn_valider, btn_journal;
    private EditText et_date1, et_date2, et_date3, et_date4, et_date5, et_date6, et_date7, et_date8, et_date9, et_date10;
    private EditText et_des1, et_des2, et_des3, et_des4, et_des5, et_des6, et_des7, et_des8, et_des9, et_des10;
    private EditText et_debit1, et_debit2, et_debit3, et_debit4, et_debit5, et_debit6, et_debit7, et_debit8, et_debit9, et_debit10;
    private EditText et_credit1, et_credit2, et_credit3, et_credit4, et_credit5, et_credit6, et_credit7, et_credit8, et_credit9, et_credit10;
    private String date1Cpte, date2Cpte, date3Cpte, date4Cpte, date5Cpte, date6Cpte, date7Cpte, date8Cpte, date9Cpte, date10Cpte;
    private String des1Cpte, des2Cpte, des3Cpte, des4Cpte, des5Cpte, des6Cpte, des7Cpte, des8Cpte, des9Cpte, des10Cpte;
    private String debit1Cpte, debit2Cpte, debit3Cpte, debit4Cpte, debit5Cpte, debit6Cpte, debit7Cpte, debit8Cpte, debit9Cpte, debit10Cpte;
    private String credit1Cpte, credit2Cpte, credit3Cpte, credit4Cpte, credit5Cpte, credit6Cpte, credit7Cpte, credit8Cpte, credit9Cpte, credit10Cpte;
    private String result = ""; String j= "1";
    private int success_count, line_count;
    private TextView tv_date;
    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_journal_compte );

        btn_return = findViewById( R.id.btn_return );
        success_count = 0;
        line_count = 0;
        btn_return = findViewById( R.id.btn_return );
        btn_valider = findViewById( R.id.btn_valider );
        btn_journal = findViewById( R.id.btn_journalcompte );
        tv_date = findViewById( R.id.tv_date );
        et_date1 = findViewById( R.id.compte_date1 );
        et_date2 = findViewById( R.id.compte_date2 );
        et_date3 = findViewById( R.id.compte_date3 );
        et_date4 = findViewById( R.id.compte_date4 );
        et_date5 = findViewById( R.id.compte_date5 );
        et_date6 = findViewById( R.id.compte_date6 );
        et_date7 = findViewById( R.id.compte_date7 );
        et_date8 = findViewById( R.id.compte_date8 );
        et_date9 = findViewById( R.id.compte_date9 );
        et_date10 = findViewById( R.id.compte_date10 );
        et_des1 = findViewById( R.id.compte_des1 );
        et_des2 = findViewById( R.id.compte_des2 );
        et_des3 = findViewById( R.id.compte_des3 );
        et_des4 = findViewById( R.id.compte_des4 );
        et_des5 = findViewById( R.id.compte_des5 );
        et_des6 = findViewById( R.id.compte_des6 );
        et_des7 = findViewById( R.id.compte_des7 );
        et_des8 = findViewById( R.id.compte_des8 );
        et_des9 = findViewById( R.id.compte_des9 );
        et_des10 = findViewById( R.id.compte_des10 );
        et_debit1 = findViewById( R.id.compte_debit1 );
        et_debit2 = findViewById( R.id.compte_debit2 );
        et_debit3 = findViewById( R.id.compte_debit3 );
        et_debit4 = findViewById( R.id.compte_debit4 );
        et_debit5 = findViewById( R.id.compte_debit5 );
        et_debit6 = findViewById( R.id.compte_debit6 );
        et_debit7 = findViewById( R.id.compte_debit7 );
        et_debit8 = findViewById( R.id.compte_debit8 );
        et_debit9 = findViewById( R.id.compte_debit9 );
        et_debit10 = findViewById( R.id.compte_debit10 );
        et_credit1 = findViewById( R.id.compte_credit1 );
        et_credit2 = findViewById( R.id.compte_credit2 );
        et_credit3 = findViewById( R.id.compte_credit3 );
        et_credit4 = findViewById( R.id.compte_credit4 );
        et_credit5 = findViewById( R.id.compte_credit5 );
        et_credit6 = findViewById( R.id.compte_credit6 );
        et_credit7 = findViewById( R.id.compte_credit7 );
        et_credit8 = findViewById( R.id.compte_credit8 );
        et_credit9 = findViewById( R.id.compte_credit9 );
        et_credit10 = findViewById( R.id.compte_credit10 );

        Date now = new Date();

        DateFormat dateformatter = DateFormat.getDateInstance(DateFormat.SHORT);
        String formattedDate = dateformatter.format(now);

        tv_date.setText(formattedDate);

        btn_valider.setOnClickListener(new View.OnClickListener() {

        @Override
               public void onClick(View v)
               {
               String type = "register";
               date1Cpte = et_date1.getText().toString();
               des1Cpte = et_des1.getText().toString();
               debit1Cpte = et_debit1.getText().toString();
               credit1Cpte = et_credit1.getText().toString();
               date2Cpte = et_date2.getText().toString();
               des2Cpte = et_des2.getText().toString();
               credit2Cpte = et_credit2.getText().toString();
               debit2Cpte = et_debit2.getText().toString();
               date3Cpte = et_date3.getText().toString();
               date4Cpte = et_date4.getText().toString();
               date5Cpte = et_date5.getText().toString();
               date6Cpte = et_date6.getText().toString();
               date7Cpte = et_date7.getText().toString();
               date8Cpte = et_date8.getText().toString();
               date9Cpte = et_date9.getText().toString();
               date10Cpte = et_date10.getText().toString();
               des1Cpte = et_des1.getText().toString();
               des2Cpte = et_des2.getText().toString();
               des3Cpte = et_des3.getText().toString();
               des4Cpte = et_des4.getText().toString();
               des5Cpte = et_des5.getText().toString();
               des6Cpte = et_des6.getText().toString();
               des7Cpte = et_des7.getText().toString();
               des8Cpte = et_des8.getText().toString();
               des9Cpte = et_des9.getText().toString();
               des10Cpte = et_des10.getText().toString();
               debit1Cpte = et_debit1.getText().toString();
               debit2Cpte = et_debit2.getText().toString();
               debit3Cpte = et_debit3.getText().toString();
               debit4Cpte = et_debit4.getText().toString();
               debit5Cpte = et_debit5.getText().toString();
               debit6Cpte = et_debit6.getText().toString();
               debit7Cpte = et_debit7.getText().toString();
               debit8Cpte = et_debit8.getText().toString();
               debit9Cpte = et_debit9.getText().toString();
               debit10Cpte = et_debit10.getText().toString();
               credit1Cpte = et_credit1.getText().toString();
               credit2Cpte = et_credit2.getText().toString();
               credit3Cpte = et_credit3.getText().toString();
               credit4Cpte = et_credit4.getText().toString();
               credit5Cpte = et_credit5.getText().toString();
               credit6Cpte = et_credit6.getText().toString();
               credit7Cpte = et_credit7.getText().toString();
               credit8Cpte = et_credit8.getText().toString();
               credit9Cpte = et_credit9.getText().toString();
               credit10Cpte = et_credit10.getText().toString();

               if (des1Cpte.isEmpty()||date1Cpte.isEmpty())
                   {
                   }
               else
                   {
                   line_count += 1;
                   new Journal_compte.Register_row_cpte().execute( type, date1Cpte, des1Cpte, credit1Cpte, debit1Cpte, j );
                   }
               if (des2Cpte.isEmpty()||date2Cpte.isEmpty())
                   {
                   }
               else
                   {
                   line_count += 1;
                   new Journal_compte.Register_row_cpte().execute( type, date2Cpte, des2Cpte, credit2Cpte, debit2Cpte, j );
                   }
               if (des3Cpte.isEmpty()||date3Cpte.isEmpty())
                   {
                   }
               else
                   {
                   line_count += 1;
                   new Journal_compte.Register_row_cpte().execute( type, date3Cpte, des3Cpte, credit3Cpte, debit3Cpte, j );
                   }
               if (des4Cpte.isEmpty()||date4Cpte.isEmpty())
                   {
                   }
               else
                   {
                   line_count += 1;
                   new Journal_compte.Register_row_cpte().execute( type, date4Cpte, des4Cpte, credit4Cpte, debit4Cpte, j );
                   }
               if (des5Cpte.isEmpty()||date5Cpte.isEmpty())
                   {
                   }
               else
                   {
                   line_count += 1;
                   new Journal_compte.Register_row_cpte().execute( type, date5Cpte, des5Cpte, credit5Cpte, debit5Cpte, j );
                   }
               if (des6Cpte.isEmpty()||date6Cpte.isEmpty())
                   {
                   }
               else
                   {
                   line_count += 1;
                   new Journal_compte.Register_row_cpte().execute( type, date6Cpte, des6Cpte, credit6Cpte, debit6Cpte, j );
                   }
               if (des7Cpte.isEmpty()||date7Cpte.isEmpty())
                   {
                   }
               else
                   {
                   line_count += 1;
                   new Journal_compte.Register_row_cpte().execute( type, date7Cpte, des7Cpte, credit7Cpte, debit7Cpte, j );
                   }
               if (des8Cpte.isEmpty()||date8Cpte.isEmpty())
                   {
                   }
               else
                   {
                   line_count += 1;
                   new Journal_compte.Register_row_cpte().execute( type, date8Cpte, des8Cpte, credit8Cpte, debit8Cpte, j );
                   }
               if (des9Cpte.isEmpty()||date9Cpte.isEmpty())
                   {
                   }
               else
                   {
                   line_count += 1;
                   new Journal_compte.Register_row_cpte().execute( type, date9Cpte, des9Cpte, credit9Cpte, debit9Cpte, j );
                   }
               if (des10Cpte.isEmpty()||date10Cpte.isEmpty())
                   {
                   }
               else
                   {
                   line_count += 1;
                   new Journal_compte.Register_row_cpte().execute( type, date10Cpte, des10Cpte, credit10Cpte, debit10Cpte, j );
                   }
               Log.d( "Michel", String.valueOf( success_count ) );


               new AlertDialog.Builder( Journal_compte.this ).setIcon( R.drawable.ok ).setTitle("Enregistrement..." ).
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
                       Intent assosActivity = new Intent (Journal_compte.this, Assos.class);
                       startActivity( assosActivity);
                       }
                   } ).show();
               }
           }
        );

        btn_journal.setOnClickListener( new View.OnClickListener(){

        @Override
        public void onClick(View v)
            {
            Intent journal = new Intent (Journal_compte.this, Journal.class);
            startActivity( journal );
            }
        }  );
        btn_return.setOnClickListener( new View.OnClickListener()
            {
            @Override
            public void onClick(View v)
                {
                new AlertDialog.Builder( Journal_compte.this ).setIcon( R.drawable.attention2 ).setTitle( "ATTENTION" ).setMessage
                        ( "Les informations saisies ne sont pas sauvegardés. Retourner à la page précédente?" ).setPositiveButton
                        ( "OUI", new DialogInterface.OnClickListener()
                            {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                                {
                                Intent assos = new Intent( Journal_compte.this, Assos.class );
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
        }

    public class Register_row_cpte extends AsyncTask<String, Void, String>
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

            String register_url = "https://albi-projects.000webhostapp.com/postCpteWH.php";
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
