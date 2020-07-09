package com.example.user.gestionnairecomptabilit;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.net.http.HttpResponseCache;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpGetHC4;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

public class Assos extends AppCompatActivity
    {
    private String nom_assos,raison_sociale,president,adresse;
    private String telephone,mail,nSiret,chiffreA_annuel,membres,caisse, compte, livret, depenses, recettes;
    private TextView etNom_as, etNom_P, etRS, etAdresse, etTel, etMail, etSiret, etCA, etMembres, etCaisse, etCompte, etLivret;
    private String type;
    private Button btnCaisse;
    private Button btnLivret;
    private Button btnCompte;

    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_assos );

        etNom_as = findViewById( R.id.etNom_as );
        etNom_P = findViewById( R.id.etNom_P );
        etRS = findViewById( R.id.etRS );
        etAdresse = findViewById( R.id.etAdresse );
        etTel = findViewById( R.id.etTel );
        etMail = findViewById( R.id.etMail );
        etSiret = findViewById( R.id.etSiret );
        etCA = findViewById( R.id.etCA );
        etMembres = findViewById( R.id.etMembres );
        etCaisse = findViewById( R.id.etCaisse );
        etCompte = findViewById( R.id.etCompte );
        etLivret = findViewById( R.id.etLivret );
        btnCaisse = findViewById( R.id.btn_caisse);
        btnCompte = findViewById( R.id.btn_compte );
        btnLivret =   findViewById( R.id.btn_livret );

        btnCaisse.setOnClickListener( new View.OnClickListener()
            {
            @Override
            public void onClick(View v)
                {
                new AlertDialog.Builder( Assos.this ).setIcon( R.drawable.f)
                        .setTitle( "Journal de caisse").setMessage( "Que voulez-vous faire?" ).setPositiveButton( "Enregistrer de nouvelles transactions", new DialogInterface.OnClickListener()
                    {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                        {
                        Intent caisseActivity = new Intent (Assos.this, Journal_caisse.class);
                        startActivity( caisseActivity );
                        }
                    } ).setNegativeButton( "Voir le journal de la caisse", new DialogInterface.OnClickListener()
                    {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                        {
                        type = "caisse";
                        Intent journal = new Intent (Assos.this, Journal.class);
                        journal.putExtra( "type", type  );

                        startActivity( journal );
                        }
                    } )
                        .show();
                }
            } );

        btnLivret.setOnClickListener( new View.OnClickListener()
            {
            @Override
            public void onClick(View v)
                {
                new AlertDialog.Builder( Assos.this ).setIcon( R.drawable.f)
                        .setTitle( "Journal du livret").setMessage( "Que voulez-vous faire?" ).setPositiveButton( "Enregistrer de nouvelles transactions", new DialogInterface.OnClickListener()
                    {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                        {
                        Intent livretActivity = new Intent (Assos.this, Journal_livret.class);
                        startActivity( livretActivity );
                        }
                    } ).setNegativeButton( "Voir le journal du livret", new DialogInterface.OnClickListener()
                    {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                        {
                        type = "livret";
                        Intent journal = new Intent (Assos.this, Journal.class);
                        journal.putExtra( "type", type );
                        startActivity( journal );
                        }
                    } )
                        .show();
                }
            } );

        btnCompte.setOnClickListener( new View.OnClickListener()
            {
            @Override
            public void onClick(View v)
                {
                new AlertDialog.Builder( Assos.this ).setIcon( R.drawable.f)
                        .setTitle( "Journal du compte courant").setMessage( "Que voulez-vous faire?" ).setPositiveButton( "Enregistrer de nouvelles transactions", new DialogInterface.OnClickListener()
                    {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                        {
                        Intent compteActvity = new Intent (Assos.this, Journal_compte.class);
                        startActivity( compteActvity );
                        }
                    } ).setNegativeButton( "Voir le journal du compte courant", new DialogInterface.OnClickListener()
                    {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                        {
                        type = "compte";
                        Intent journal = new Intent (Assos.this, Journal.class);
                        journal.putExtra( "type", type );
                        startActivity( journal );
                        }
                    } )
                        .show();
                }
            } );

        new InfoAssos().execute();
        }

    public class InfoAssos extends AsyncTask<String, String, String>
        {

        @Override
        protected String doInBackground(String... strings)
            {
            String result = "";
            String dataDisplay_URL = "https://albi-projects.000webhostapp.com/getInfoWH.php";
            try
                {
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI( new URI( dataDisplay_URL ) );
                HttpResponse responce = client.execute( request );
                BufferedReader reader = new BufferedReader( new InputStreamReader( responce.getEntity().getContent() ) );
                StringBuffer stringBuffer = new StringBuffer( "" );
                String line = "";

                while ((line = reader.readLine()) != null)
                    {
                    stringBuffer.append( line );
                    break;
                    }
                reader.close();

                result = stringBuffer.toString();
                Log.d( "JSON", result );
                } catch (Exception e)
                {
                Log.d( "JSONERROR", "Here is an exception: " + e.getMessage() );
                }

            return result;
            }

        @Override
        protected void onPostExecute(String result)
            {
            Log.d( "JSON", result );

            try
                {
                Toast.makeText( getApplicationContext(), "Informations récupérées avec succès", Toast.LENGTH_LONG ).show();
                final JSONArray jsonArray = new JSONArray( result );
                nom_assos = jsonArray.getJSONObject( 0 ).getString( "nom_assos" );
                raison_sociale = jsonArray.getJSONObject( 0 ).getString( "raison_sociale" );
                president = jsonArray.getJSONObject( 0 ).getString( "nom_president" );
                adresse = jsonArray.getJSONObject( 0 ).getString( "adresse" );
                telephone = jsonArray.getJSONObject( 0 ).getString( "telephone" );
                mail = jsonArray.getJSONObject( 0 ).getString( "mail" );
                nSiret = jsonArray.getJSONObject( 0 ).getString( "num_siret" );
                chiffreA_annuel = jsonArray.getJSONObject( 0 ).getString( "CA_annuel" );
                membres = jsonArray.getJSONObject( 0 ).getString( "nombre_de_membres" );
                caisse = jsonArray.getJSONObject( 0 ).getString( "reste_caisse" );
                compte = jsonArray.getJSONObject( 0 ).getString( "reste_compte" );
                livret = jsonArray.getJSONObject( 0 ).getString( "reste_livret" );

                etNom_as.setText("Association " +nom_assos );
                etNom_P.setText("Président: " +president );
                etRS.setText( raison_sociale );
                etAdresse.setText( adresse );
                etTel.setText( "Tel: "+telephone );
                etMail.setText( "Mail: "+mail );
                etSiret.setText( nSiret );
                etCA.setText( chiffreA_annuel +"€" );
                etMembres.setText( membres );
                etCaisse.setText( caisse +"€");
                etCompte.setText( compte +"€");
                etLivret.setText( livret +"€");
                }
            catch (JSONException e)
                {
                Toast.makeText( getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
                }

            }
        }

    public String getNom_assos()
        {
        return nom_assos;
        }

    public void setNom_assos(String nom_assos)
        {
        this.nom_assos = nom_assos;
        }

    public String getRaison_sociale()
        {
        return raison_sociale;
        }

    public void setRaison_sociale(String raison_sociale)
        {
        this.raison_sociale = raison_sociale;
        }

    public String getPresident()
        {
        return president;
        }

    public void setPresident(String president)
        {
        this.president = president;
        }

    public String getAdresse()
        {
        return adresse;
        }

    public void setAdresse(String adresse)
        {
        this.adresse = adresse;
        }

    public String getTelephone()
        {
        return telephone;
        }

    public void setTelephone(String telephone)
        {
        this.telephone = telephone;
        }

    public String getMail()
        {
        return mail;
        }

    public void setMail(String mail)
        {
        this.mail = mail;
        }
    public String getnSiret()
        {
        return nSiret;
        }

    public void setnSiret(String nSiret)
        {
        this.nSiret = nSiret;
        }

    public String getChiffreA_annuel()
        {
        return chiffreA_annuel;
        }

    public void setChiffreA_annuel(String chiffreA_annuel)
        {
        this.chiffreA_annuel = chiffreA_annuel;
        }

    public String getMembres()
        {
        return membres;
        }

    public void setMembres(String membres)
        {
        this.membres = membres;
        }

    public String getCaisse()
        {
        return caisse;
        }

    public void setCaisse(String caisse)
        {
        this.caisse = caisse;
        }

    public String getCompte()
        {
        return compte;
        }

    public void setCompte(String compte)
        {
        this.compte = compte;
        }

    public String getLivret()
        {
        return livret;
        }

    public void setLivret(String livret)
        {
        this.livret = livret;
        }
    }
