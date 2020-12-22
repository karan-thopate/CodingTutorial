package com.example.fetchdata;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewLogin extends AppCompatActivity {
    EditText new_username,new_password;
    Button button1,button2;
    FirebaseAuth mAuth;
    EditText firstname,lastname;
    DatabaseReference database =  FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_login);

        new_username=findViewById(R.id.editText);
        new_password=findViewById(R.id.editText4);

        button1=findViewById(R.id.button2);
        button2=findViewById(R.id.signup);
        mAuth = FirebaseAuth.getInstance();





//        database.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
//                    Users users = noteDataSnapshot.getValue(Users.class);
//
//
//                    Toast.makeText(NewLogin.this, "add seccusessfully", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });








        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                firstname.getText().toString().trim();
//                Users users=new Users();
//                users.setFirstname(database.child("Users").push().getKey());
//                users.setFirstname(firstname.getText().toString());
//                database.child("Users").child(users.getFirstname()).setValue(users);
//
//                lastname.getText().toString().trim();
//                users.setLastname(database.child("Users").push().getKey());
//                users.setLastname(lastname.getText().toString());
//                database.child("Users").child(users.getLastname()).setValue(users);

                String email=new_username.getText().toString();
                final String pwd=new_password.getText().toString();
                if (email.isEmpty())
                {
                    new_username.setError("please enter email id");
                    new_username.requestFocus();
                }
                else if (pwd.isEmpty())
                {
                    new_password.setError("please enter your password");
                    new_password.requestFocus();
                }
                else if (email.isEmpty() && pwd.isEmpty())
                {
                    Toast.makeText(NewLogin.this, "Fields Are Empty", Toast.LENGTH_SHORT).show();
                }
                else if (!(email.isEmpty() && pwd.isEmpty()))
                {

                        mAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(NewLogin.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (isValidPassword(pwd)){
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(NewLogin.this, "SignUp Unsuccessful , Please Try Again OR Enter the Strong Password", Toast.LENGTH_SHORT).show();
                                    } else {
                                        startActivity(new Intent(NewLogin.this, MainActivity.class));
                                    }
                                }
                                else {
                                    Toast.makeText(NewLogin.this, "Enter Strong Password        [like inclding Capital,small latter special character and number", Toast.LENGTH_SHORT).show();
                                }
                            }

                        });
                }
                else
                {
                    Toast.makeText(NewLogin.this, "Error Occured!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intsignup=new Intent(NewLogin.this,MainActivity.class);
                startActivity(intsignup);
            }
        });
    }
    public boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }


}
