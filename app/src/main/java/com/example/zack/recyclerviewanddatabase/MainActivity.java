package com.example.zack.recyclerviewanddatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityTag";
    RecyclerView rvActorList;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.ItemAnimator itemAnimator;
    private List<Actor> actorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHelper db = new DatabaseHelper(this);
  //      if(db.getAllActors().size()<0)
  //      {
        SetActors();
        Log.d(TAG, "Actors are loading into the database");
  //      }

//        DatabaseHelper db = new DatabaseHelper(this);
        Actor a = db.readActor(0);
        Log.d(TAG, "onCreate: " + a.getName() + a.getGender() + a.getAge() + a.getHeight());


    }

    private void SetActors() {
        actorList = new ArrayList<>();
        actorList.add(new Actor("Bill Clinton", 65, "Male", 167f));
        actorList.add(new Actor("Tom Cruise", 50, "Male", 142f));
        actorList.add(new Actor("Jennifer Aniston", 30, "Female", 150f));


        DatabaseHelper db = new DatabaseHelper(this);
        for (int i = 0; i <actorList.size() ; i++) {
            Actor actor = actorList.get(i);
            db.createActor(actor.getName(), actor.getAge(), actor.getGender(), actor.getHeight());
        }

        }
}
