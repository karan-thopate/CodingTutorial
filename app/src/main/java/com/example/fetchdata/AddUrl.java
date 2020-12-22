package com.example.fetchdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.data.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddUrl extends AppCompatActivity {

    EditText UserUrl,Topic;
    Button addurl;

    public static final String DATBASE_NAME="URL";
    DatabaseReference database =  FirebaseDatabase.getInstance().getReference().child(DATBASE_NAME);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_url);

    UserUrl=findViewById(R.id.UserUrl);
    Topic=findViewById(R.id.UserSub);



    addurl=findViewById(R.id.btnurl);
    addurl.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Url url=new Url();
            url.setUrlId(database.child(DATBASE_NAME).push().getKey());
            url.setUserUrl(UserUrl.getText().toString());
            url.setUserTopic(Topic.getText().toString());

            Toast.makeText(AddUrl.this, "Added Sucesslly", Toast.LENGTH_SHORT).show();
            database.child(url.getUrlId()).setValue(url);

        }
    });

    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_OK,intent);
        finish();
        super.onBackPressed();
    }
}
