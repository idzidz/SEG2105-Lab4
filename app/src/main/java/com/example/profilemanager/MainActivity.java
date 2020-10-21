package com.example.profilemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button map,create,list;
    EditText teamName, teamPostal;
    ImageView teamIcon;
    DatabaseAccess dbAccess;
    int resOut;

    public void OnOpenInGoogleMaps(EditText postalCode){
        //Intent string: the postal code entered by user
        Uri gmmIntentUri = Uri.parse("http://maps.google.co.in/maps?q="+postalCode.getText());

        //Creating intent and setting action to view
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

        //Setting intent to Google Maps package
        mapIntent.setPackage("com.google.android.apps.maps");

        //Start activity using given intent
        startActivity(mapIntent);
    }

    public void OnSetAvatarButton(View view){
        //Setting up separate activity which allows user to pick their icon.
        //getApplicationContext essentially extends ProfileActivity(?)
        Intent intent = new Intent(this, SetIcon.class);
        startActivityForResult (intent, 0);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) return;
        //Getting the Avatar Image we show to our users
        ImageView avatarImage = (ImageView) findViewById(R.id.logo);
        //Figuring out the correct image
        String drawableName = "ic_logo_00";
        switch (data.getIntExtra("imageID", R.id.image0)) {
            case R.id.image5:
                drawableName = "ic_logo_01";
                break;
            case R.id.image4:
                drawableName = "ic_logo_02";
                break;
            case R.id.image3:
                drawableName = "ic_logo_03";
                break;
            case R.id.image2:
                drawableName = "ic_logo_04";
                break;
            case R.id.image1:
                drawableName = "ic_logo_05";
                break;
            default:
                drawableName = "ic_logo_00";
                break;
        }
        int resID = getResources().getIdentifier(drawableName, "drawable", getPackageName());
        resOut = resID;
        avatarImage.setImageResource(resID);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbAccess = new DatabaseAccess();

        map = (Button) findViewById(R.id.buttonMap);
        teamPostal = (EditText) findViewById(R.id.teamPostal);
        teamName = (EditText) findViewById(R.id.teamName);
        teamIcon = (ImageView) findViewById(R.id.logo);
        create = (Button) findViewById(R.id.buttonCreate);
        list = (Button) findViewById(R.id.buttonList);

        map.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V){
                OnOpenInGoogleMaps(teamPostal);
            }
        });

        teamIcon.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V){
                OnSetAvatarButton(teamIcon);
            }
        });
        create.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){dbAccess.createEntry(teamName.getText().toString(),teamPostal.getText().toString(),resOut);
                Toast.makeText(MainActivity.this,"Team Added",Toast.LENGTH_SHORT).show();
            }
        });
        list.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,TeamList.class);
                startActivity(intent);
            }
        });

    }
}