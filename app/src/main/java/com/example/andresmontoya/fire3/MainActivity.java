package com.example.andresmontoya.fire3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;


public class MainActivity extends AppCompatActivity{

    TextView textView;
    Button btnFoggy;
    Button btnSunny;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onStart() {
        super.onStart();
        textView = (TextView) findViewById(R.id.textView3);
        btnFoggy = (Button) findViewById(R.id.btnFoggy);
        btnSunny = (Button) findViewById(R.id.btnSunny);
        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("messages");
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<Map<String, String>> genericTypeIndicator = new GenericTypeIndicator<Map<String, String>>() {};
                Map<String, String> map = dataSnapshot.getValue(genericTypeIndicator );
                //Map<String, String> map = dataSnapshot.getValue(Map.class);
                String hello = map.get("hello");
                String world = map.get("world");
                String bitch = map.get("bitch");
                textView.setText(("VALUES"+ hello +" "+world+" "+bitch));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        /*btnSunny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.setValue("Sunny");
            }
        });


        btnFoggy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.setValue("Foggy");
            }
        });*/
    }
    @Override
    public void onStop() {
        super.onStop();
    }



}
