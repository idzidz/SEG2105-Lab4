package com.example.profilemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button map;
    EditText teamName, teamPostal;
    ImageView teamIcon;

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

    public void OnSetAvatarButton(ImageView teamIcon){
        //Setting up separate activity which allows user to pick their icon.
        //getApplicationContext essentially extends ProfileActivity(?)
        Intent intent = new Intent(getApplicationContext(), SetIcon.class);
        startActivityForResult (intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_CANCELED) return;
        //Getting the Avatar Image we show to our users
        ImageView avatarImage = (ImageView) findViewById(R.id.logo);
        //Figuring out the correct image
        String drawableName = "ic_logo_00";
        switch (data.getIntExtra("imageID",R.id.image0)) {
            case R.id.image0:
                drawableName = "ic_logo_00";
                break;
            case R.id.image1:
                drawableName = "ic_logo_01";
                break;
            case R.id.image2:
                drawableName = "ic_logo_02";
                break;
            case R.id.image3:
                drawableName = "ic_logo_03";
                break;
            case R.id.image4:
                drawableName = "ic_logo_04";
                break;
            case R.id.image5:
                drawableName = "ic_logo_05";
                break;
            default:
                drawableName = "ic_logo_00";
                break;
        }
        int resID = getResources().getIdentifier(drawableName, "drawable", getPackageName());
        avatarImage.setImageResource(resID);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        map = (Button) findViewById(R.id.buttonMap);
        teamPostal = (EditText) findViewById(R.id.teamPostal);
        teamIcon = (ImageView) findViewById(R.id.logo);



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

    }
}