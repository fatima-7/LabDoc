package com.example.esport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TableRow;


import androidx.appcompat.app.AppCompatActivity;

public class CricketDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cricketdetail_activity);

        TableRow row = (TableRow)findViewById( R.id.currentMatch );
        row.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                Intent intent = new Intent(v.getContext(), Match_Detail_Activity.class);
                v.getContext().startActivity(intent);}
            }



         );

        TableRow row2 = (TableRow)findViewById( R.id.upcommingMatch );
        row2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                Intent intent = new Intent(v.getContext(), Match_Detail_Activity.class);
                v.getContext().startActivity(intent);}
        }
        );
    }



}