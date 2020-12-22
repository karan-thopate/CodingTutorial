package com.example.fetchdata;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    Button btnLogout;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    TextView fname,lname;
    String first,last;
    TextView c,cpp,java,corejava,php,android;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnLogout = findViewById(R.id.logout);
      //  listView = findViewById(R.id.names);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intToMain = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intToMain);
            }
        });

//        fname=findViewById(R.id.ffname);
//        lname=findViewById(R.id.llname);

//        Intent intent = getIntent();
//
//        first=intent.getStringExtra(MainActivity.first);
//        fname.setText(first);
//        last=intent.getStringExtra(MainActivity.last);
//        lname.setText(last);


        c=findViewById(R.id.c_langage);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(HomeActivity.this,c.class);
                startActivity(intent1);
            }
        });

        cpp=findViewById(R.id.cpp_language);
        cpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(HomeActivity.this,Cpp.class);
                startActivity(intent1);
            }
        });

        java=findViewById(R.id.java);
        java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(HomeActivity.this,Java.class);
                startActivity(intent2);
            }
        });

        corejava=findViewById(R.id.corejava);
        corejava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3=new Intent(HomeActivity.this,CoreJava.class);
                startActivity(intent3);
            }
        });

        php=findViewById(R.id.php);
        php.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4=new Intent(HomeActivity.this,Php.class);
                startActivity(intent4);
            }
        });

        android=findViewById(R.id.android);
        android.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5=new Intent(HomeActivity.this,Android.class);
                startActivity(intent5);
            }
        });
    }
}