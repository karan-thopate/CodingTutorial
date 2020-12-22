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

public class AddAndroidUrl extends AppCompatActivity {

    EditText UserUrl,Topic;
    Button addurl;
    public static final String DATBASE_NAME="Android_URL";
    DatabaseReference databaseAndroid =  FirebaseDatabase.getInstance().getReference().child(DATBASE_NAME);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_android_url);

        UserUrl=findViewById(R.id.UserUrlAndroid);
        Topic=findViewById(R.id.UserSubAndroid);



        addurl=findViewById(R.id.btnurlAndroid);
        addurl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Url url=new Url();
                url.setUrlId(databaseAndroid.child(DATBASE_NAME).push().getKey());
                url.setUserUrl(UserUrl.getText().toString());
                url.setUserTopic(Topic.getText().toString());

                Toast.makeText(AddAndroidUrl.this, "Added Sucesslly", Toast.LENGTH_SHORT).show();
                databaseAndroid.child(url.getUrlId()).setValue(url);
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
