package com.example.profilemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class TeamList extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_list);
        DatabaseAccess dbAccess = new DatabaseAccess();
        ListView teamList = findViewById(R.id.teamListView);

        dbAccess.setListener(new DatabaseAccess.Listener() {
            @Override
            public void entryLists(ArrayList<String> entries) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(TeamList.this,android.R.layout.simple_list_item_1,entries);
                teamList.setAdapter(adapter);

            }
        });

        Button backButton = findViewById(R.id.buttonBack);
        backButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent  = new Intent(TeamList.this,MainActivity.class);
                startActivity(intent);
            }
        });
        dbAccess.entryList();
    }
}