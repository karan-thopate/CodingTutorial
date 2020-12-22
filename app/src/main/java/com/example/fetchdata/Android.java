package com.example.fetchdata;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.fetchdata.utility.OnListViewClick;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;

public class Android extends AppCompatActivity implements OnListViewClick{

    ImageView fab;
    ListView Android_list;
    ArrayList<Url> urlsList = new ArrayList<>();
    ArrayList<Integer> arrayListCount = new ArrayList<>();
    DatabaseReference databaseAndroid =  FirebaseDatabase.getInstance().getReference().child(AddAndroidUrl.DATBASE_NAME);
    CAdapter cAdapter;
    EditText searchAndroid;
    private OnListViewClick onListViewClick = this;
    protected void onStart() {

        super.onStart();

    }

    void onStartMethod(){
        databaseAndroid.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    Url url = noteDataSnapshot.getValue(Url.class);
                    url.setCount(0);
                    arrayListCount.add(0);
                    urlsList.add(url);
                }

                cAdapter=new CAdapter(urlsList,Android.this, onListViewClick);

                Android_list.setAdapter(cAdapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         fab = findViewById(R.id.addAndroid);
         Android_list=findViewById(R.id.list_Android);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent intent = new Intent(Android.this, AddAndroidUrl.class);
                startActivityForResult(intent, 102);
            }
        });
        searchAndroid = findViewById(R.id.searchAndroid);
        searchAndroid.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence cs, int start, int count, int after) {
                int textlength = cs.length();
                ArrayList<Url> tempArrayList = new ArrayList<Url>();
                for (Url c : urlsList) {
                    if (textlength <= c.getUserTopic().length()) {
                        if (c.getUserTopic().toLowerCase().contains(cs.toString().toLowerCase())) {
                            tempArrayList.add(c);
                        }
                    }
                }
                cAdapter = new CAdapter(tempArrayList, Android.this, onListViewClick);
                Android_list.setAdapter(cAdapter);

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        onStartMethod();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 102){
            checkDataAndUpdate();
        }
    }



    private void checkDataAndUpdate() {
        databaseAndroid.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                urlsList.clear();

                int i = 0;
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    if (arrayListCount.get(i)==null){
                        arrayListCount.add(0);
                    }
                    i++;
                    Url url = noteDataSnapshot.getValue(Url.class);
                    urlsList.add(url);
                }



                /*               cAdapter=new CAdapter(urlsList,c.this);*/

                //             c_list.setAdapter(cAdapter);
                cAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onLisViewClick(View view, int position) {
        int cnt = arrayListCount.get(position);
        cnt=cnt+1;
        if (cnt >2){
            arrayListCount.set(position,0);
            try {


                AlertDialog.Builder builder = new AlertDialog.Builder(Android.this);
                View layout = null;
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                layout = inflater.inflate(R.layout.rating, null);
                final RatingBar ratingBar = (RatingBar) layout.findViewById(R.id.ratingBar);
                builder.setTitle("Rate Us");
                builder.setMessage("Thank you for rating us , it will help us to provide you the best service .");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Float value = ratingBar.getRating();
                        Toast.makeText(Android.this, "Rating is : " + value, Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("No,Thanks", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setCancelable(false);
                builder.setView(layout);
                builder.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
            Toast.makeText(this, "Count"+cnt, Toast.LENGTH_SHORT).show();
        }else{
            arrayListCount.set(position,cnt);
            Toast.makeText(Android.this, "Count"+cnt, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlsList.get(position).getUserUrl()));
            startActivity(intent);
        }
    }
}
