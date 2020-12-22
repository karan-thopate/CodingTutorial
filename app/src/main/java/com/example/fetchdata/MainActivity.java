package com.example.fetchdata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.os.Build.VERSION_CODES.M;
import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {

    EditText username,password;
    TextView newlogin;
    Button button;
     EditText fname;
    EditText lname;
    FirebaseAuth mAuth;
    //firebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    public static final  String first="";
    public static final  String last="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("Users");

        username = findViewById(R.id.editText2);
        password = findViewById(R.id.editText3);
        newlogin = findViewById(R.id.textView);
        button = findViewById(R.id.button);


        mAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mAuth.getCurrentUser();
                if (mFirebaseUser != null) {
                    Toast.makeText(MainActivity.this, "you are logged in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(MainActivity.this, "Please Login", Toast.LENGTH_SHORT).show();
                }
            }
        };


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = username.getText().toString();
                final String pwd = password.getText().toString();

                if (email.isEmpty()) {
                    username.setError("please enter email id");
                    username.requestFocus();
                } else if (pwd.isEmpty()) {
                    password.setError("please enter your password");
                    password.requestFocus();
                }
                else if (email.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Fields Are Empty", LENGTH_SHORT).show();
                } else if (!(email.isEmpty() && pwd.isEmpty())) {

                    mAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "LogIn error, Please Login Again", Toast.LENGTH_SHORT).show();
                            } else {

                                    Intent intToHome = new Intent(MainActivity.this, HomeActivity.class);
//                                    intToHome.putExtra(first, ffname);
//
//                                    intToHome.putExtra(last, llname);
                                    startActivity(intToHome);
                                }
                            }

                    });
                }
            }
        });

        newlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewLogin.class);
                startActivity(intent);
            }
        });

    }



    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }


}
