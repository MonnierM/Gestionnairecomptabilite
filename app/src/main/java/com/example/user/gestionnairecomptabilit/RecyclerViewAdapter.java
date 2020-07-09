package com.example.user.gestionnairecomptabilit;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>
    {


    // Provide a reference to the views for each data item
// Complex data items may need more than one view per item, and
// you provide access to all the views for a data item in a view holder

    private ArrayList<Row> maLigne;

    public static class MyViewHolder extends RecyclerView.ViewHolder
        {

        public TextView tv_date, tv_description, tv_debit, tv_credit;

        public MyViewHolder(View view)
            {
            super( view );
            tv_date = view.findViewById( R.id.tv_date );
            tv_description = view.findViewById( R.id.tv_description );
            tv_debit = view.findViewById( R.id.tv_debit );
            tv_credit = view.findViewById( R.id.tv_credit );

            }
        }
    public RecyclerViewAdapter(ArrayList<Row> ligne)
        {
        maLigne = ligne;
        Log.d("P", String.valueOf( maLigne ));
        }
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
        View v = LayoutInflater.from( parent.getContext() ).inflate( R.layout.recyclerview_row, parent, false );

        MyViewHolder vh = new MyViewHolder( v );
        return vh;
        }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
        {
        Row row = maLigne.get( position );

        holder.tv_date.setText( maLigne.get( position ).getDate() );
        holder.tv_description.setText( maLigne.get( position ).getDescription() );
        holder.tv_debit.setText( maLigne.get( position ).getDebit() );
        holder.tv_credit.setText( maLigne.get( position ).getCredit() );
        }


    @Override
    public int getItemCount()
        {
        return maLigne.size();
        }
    }
