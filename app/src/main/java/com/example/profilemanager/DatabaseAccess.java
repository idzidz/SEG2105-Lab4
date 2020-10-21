package com.example.profilemanager;

import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class DatabaseAccess {

    public interface Listener{
        void entryLists(ArrayList<Team> entries);
    }
    FirebaseDatabase masterTable;
    DatabaseReference tableReference;
    private Listener listener = null;
    public void setListener(Listener listener){
        this.listener = listener;
    }


    public void createEntry(String teamName, String postalCode, int img){
        Team team = new Team();
        team.setImg(img);
        team.setName(teamName);
        team.setPostCode(postalCode);
        masterTable = FirebaseDatabase.getInstance();
        tableReference = masterTable.getReference("teams");
        tableReference.child(teamName).setValue(team);
    }

    public void entryList(){
        ArrayList<Team> entries = new ArrayList<Team>();
        masterTable = FirebaseDatabase.getInstance();
        tableReference = masterTable.getReference("teams");
        tableReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()){
                    Team team = postSnapshot.getValue(Team.class);
                    entries.add(team);
                }
                listener.entryLists(entries);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
