package com.example.fetchdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCoreJavaUrl extends AppCompatActivity {

    EditText UserUrl,Topic;
    Button addurl;
    public static final String DATBASE_NAME="CoreJava_URL";
    DatabaseReference databaseCjava =  FirebaseDatabase.getInstance().getReference().child(DATBASE_NAME);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_core_java_url);

        UserUrl=findViewById(R.id.UserUrlCjava);
        Topic=findViewById(R.id.UserSubCjava);



        addurl=findViewById(R.id.btnurlCjava);
        addurl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Url url=new Url();
                url.setUrlId(databaseCjava.child(DATBASE_NAME).push().getKey());
                url.setUserUrl(UserUrl.getText().toString());
                url.setUserTopic(Topic.getText().toString());

                Toast.makeText(AddCoreJavaUrl.this, "Added Sucesslly", Toast.LENGTH_SHORT).show();
                databaseCjava.child(url.getUrlId()).setValue(url);
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
        super.onBackPressed();
    }
}

