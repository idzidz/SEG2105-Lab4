package com.example.profilemanager;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class DatabaseAccess {

    public interface Listener{
        void entryLists(ArrayList<String> entries);
    }
    FirebaseDatabase masterTable;
    DatabaseReference tableReference;
    private Listener listener = null;
    public void setListener(Listener listener){
        this.listener = listener;
    }


    public void createEntry(String teamName,String postalCode){
        masterTable = FirebaseDatabase.getInstance();
        tableReference = masterTable.getReference("teams");
        tableReference.child("teams").child(teamName).setValue(postalCode);
    }

    public void entryList(){
        ArrayList<String> entries = new ArrayList<String>();
        masterTable = FirebaseDatabase.getInstance();
        tableReference = masterTable.getReference("teams/teams");
        tableReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()){
                    String entry = postSnapshot.getKey()+" - "+postSnapshot.getValue();
                    entries.add(entry);
                }
                listener.entryLists(entries);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
