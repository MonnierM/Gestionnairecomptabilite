package com.example.user.gestionnairecomptabilit;

import android.app.Activity;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Login extends AppCompatActivity
    {

    private EditText nomUtilisateur, mot_de_passe;
    private Button login;
    Animation frombottom, fromtop;
    private ImageView lock;

    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        nomUtilisateur = findViewById( R.id.etNomUtilisateur );
        mot_de_passe = findViewById( R.id.etMDP );
        login = findViewById( R.id.btnLogin );
        lock = findViewById( R.id.picLogin );

        frombottom = AnimationUtils.loadAnimation( this, R.anim.frombottom );
        fromtop = AnimationUtils.loadAnimation( this, R.anim.fromtop );
        login.setAnimation( frombottom );
        lock.setAnimation( fromtop );
        }

    public static boolean isConnectedInternet(Activity activity)
        {
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService( Context.CONNECTIVITY_SERVICE );
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null)
            {
            NetworkInfo.State networkState = networkInfo.getState();
            if (networkState.compareTo( NetworkInfo.State.CONNECTED ) == 0)
                {
                return true;
                } else return false;
            } else return false;
        }

    public Boolean isOnline()
        {
        try
            {
            Process p1 = java.lang.Runtime.getRuntime().exec( "ping -c 1 www.google.com" );
            int returnVal = p1.waitFor();
            boolean reachable = (returnVal == 0);
            return reachable;
            } catch (Exception e)
            {
            e.printStackTrace();
            }
        return false;
        }


    public void OnLogin(View view)
        {
        String username = nomUtilisateur.getText().toString();
        String password = mot_de_passe.getText().toString();
        String type = "login";

        if (nomUtilisateur.getText().toString().isEmpty() || mot_de_passe.getText().toString().isEmpty())
            {
            new AlertDialog.Builder( this ).setIcon( R.drawable.f ).setTitle( "Tous les champs doivent être remplis" )
                    .setNegativeButton( "recommencer", new DialogInterface.OnClickListener()
                        {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                            {
                            }
                        } ).show();
            } else
            {
            if (Login.isConnectedInternet( Login.this ))
                {
                if (isOnline())
                    {
                    new BackgroundWorker().execute( type, username, password );
                    }
                else
                    {
                    new AlertDialog.Builder( this ).setIcon( R.drawable.nointernet ).setTitle( "Votre connexion internet rencontre quelques problèmes, veuillez verifier votre connexion" )
                            .setNegativeButton( "OK", new DialogInterface.OnClickListener()
                                {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                    {
                                    }
                                } ).show();
                    }
                }
            else
                {
                new AlertDialog.Builder( this ).setIcon( R.drawable.nointernet ).setTitle( "Une connexion internet requise pour continuer" )
                        .setNegativeButton( "OK", new DialogInterface.OnClickListener()
                            {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                                {
                                }
                            } ).show();
                }
            }
        }


    public class BackgroundWorker extends AsyncTask<String, Void, String>
        {
        AlertDialog alertDialog;

        @Override
        protected String doInBackground(String... params)
            {
            String nom_util = params[1];
            String mdp_util = params[2];
            String type = params[0];
            String login_url = "https://albi-projects.000webhostapp.com/LoginWH.php";
            if (type.equals( "login" ))
                {
                try
                    {
                    URL url = new URL( login_url );
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod( "POST" );
                    httpURLConnection.setDoOutput( true );
                    httpURLConnection.setDoInput( true );
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter( (new OutputStreamWriter( outputStream, "UTF-8" )) );
                    String post_data = URLEncoder.encode( "nom", "UTF-8" ) + "=" + URLEncoder.encode( nom_util, "UTF-8" ) + "&" +
                            URLEncoder.encode( "mdp", "UTF-8" ) + "=" + URLEncoder.encode( mdp_util, "UTF-8" );
                    bufferedWriter.write( post_data );
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( inputStream, "iso-8859-1" ) );
                    String result = "";
                    String line = null;

                    while ((line = bufferedReader.readLine()) != null)
                        {
                        result += line;
                        }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;
                    } catch (MalformedURLException e)
                    {
                    e.printStackTrace();
                    } catch (IOException e)
                    {
                    e.printStackTrace();
                    }
                }
            return null;
            }

        @Override
        protected void onPreExecute()
            {
            }

        @Override
        protected void onPostExecute(String result)
            {
            Context context;
            String utilisateur = String.valueOf( nomUtilisateur.getText() );
            if (result.contains( "Bienvenue" ))
                {
                new AlertDialog.Builder( Login.this ).setIcon( R.drawable.emoticon )
                        .setTitle( result + " Mr." + utilisateur ).setNegativeButton( "Suivant", new DialogInterface.OnClickListener()
                    {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                        {
                        Intent assos = new Intent( Login.this, Assos.class );
                        startActivity( assos );
                        }
                    } ).show();
                } else
                {
                new AlertDialog.Builder( Login.this ).setIcon( R.drawable.sensinterdit ).setTitle( result )
                        .setNegativeButton( "recommencer la saisie", new DialogInterface.OnClickListener()
                            {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                                {
                                }
                            } ).show();
                }
            }

        @Override
        protected void onProgressUpdate(Void... values)
            {
            super.onProgressUpdate( values );
            }
        }
    }
