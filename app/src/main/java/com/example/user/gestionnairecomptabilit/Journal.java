package com.example.user.gestionnairecomptabilit;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Journal extends AppCompatActivity
    {

    private RecyclerView journal_recycler;
    private TextView periode, titre, tv_result;
    private RecyclerViewAdapter mAdapter;
    private Button retour;
    private ArrayList <Row> ligne = new ArrayList<Row>();
    private String type;
    private Double resultatCredit=0.0, resultatDebit=0.0, total=0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_journal );

        journal_recycler = findViewById( R.id.journal_recycler );
        periode = findViewById( R.id.tv_periode );
        titre = findViewById( R.id.titre );
        retour = findViewById( R.id.btn_retour );
        tv_result = findViewById( R.id.tv_result );

        retour.setOnClickListener( new View.OnClickListener()
            {
            @Override
            public void onClick(View v)
                {
                Intent assos = new Intent(Journal.this, Assos.class);
                startActivity(assos);
                }
            } );

        Date now = new Date();

        DateFormat dateformatter = DateFormat.getDateInstance(DateFormat.SHORT);
        String formattedDate = dateformatter.format(now);
        periode.setText("01/01/2018 au " +formattedDate);
        Intent journal = getIntent();
        Bundle extras = journal.getExtras();
        type = extras.getString( "type" );
        titre.setText( type );
        Log.d( "AFPA", String.valueOf( type ) );

        new GetRows().execute();

    }

    public class GetRows extends AsyncTask<String, String, String>
        {

        @Override
        protected String doInBackground(String... strings)
            {
            String result = "";
            String dataDisplay_URL = "https://albi-projects.000webhostapp.com/dao_getInfos.php?type="+type;
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
                Log.d( "JSON2", result );
                } catch (Exception e)
                {
                Log.d( "ERROR", "Here is an exception: " + e.getMessage() );
                }

            return result;
            }

        @Override
        protected void onPostExecute(String result)
            {
            try
                {
                final JSONArray jsonArray = new JSONArray( result );
               for (int i = 0; i < jsonArray.length(); i++)
                    {
                    Row row = new Row(jsonArray.getJSONObject( i ).getString( "date" ), jsonArray.getJSONObject( i ).getString( "description" ),
                            jsonArray.getJSONObject( i ).getString( "debit" ), jsonArray.getJSONObject( i ).getString( "credit" ));
                    ligne.add(row);

                     resultatCredit += Double.valueOf( jsonArray.getJSONObject( i ).getString( "credit" ) );
                     resultatDebit += Double.valueOf( jsonArray.getJSONObject( i ).getString( "debit" ) );
                     Log.d( "MICHEL", String.valueOf( row ) );
                    }
                total = resultatCredit - resultatDebit;
                tv_result.setText(String.valueOf(total) + "€");
                Log.d("obj:",String.valueOf( ligne.size()));
                mAdapter = new RecyclerViewAdapter(ligne);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                journal_recycler.setLayoutManager(mLayoutManager);
                journal_recycler.setItemAnimator(new DefaultItemAnimator());
                journal_recycler.setAdapter(mAdapter);
                Log.d( "LIGNE", String.valueOf(ligne));

                Log.d( "JSON3", String.valueOf( result ) );
                }
            catch(JSONException e)
                {
               Toast.makeText( getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
                }
            }
        }
    }
