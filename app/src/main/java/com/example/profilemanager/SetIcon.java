package com.example.profilemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SetIcon extends AppCompatActivity {
    ImageView team0, team1, team2, team3, team4, team5;
    ImageView selectedTeam;

    public void SetTeamIcon(ImageView selection){
        //Return intent for our MainActivity
        Intent returnIntent = new Intent();

        //Selected image
        ImageView selectedImage = (ImageView) selection;

        //Returning ID of selected image
        returnIntent.putExtra("imageID", selectedImage.getId());
        setResult(RESULT_OK, returnIntent);

        //Closing this activity and returning to MainActivity
        finish();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        team0 = (ImageView) findViewById(R.id.image0);
        team1 = (ImageView) findViewById(R.id.image1);
        team2 = (ImageView) findViewById(R.id.image2);
        team3 = (ImageView) findViewById(R.id.image3);
        team4 = (ImageView) findViewById(R.id.image4);
        team5 = (ImageView) findViewById(R.id.image5);

        team0.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                SetTeamIcon(team0);
            }
        });

        team1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                SetTeamIcon(team1);
            }
        });

        team2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                SetTeamIcon(team2);
            }
        });

        team3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                SetTeamIcon(team3);
            }
        });

        team4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                SetTeamIcon(team4);
            }
        });

        team5.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                SetTeamIcon(team5);
            }
        });

    }
}