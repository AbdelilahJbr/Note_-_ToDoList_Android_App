package com.jabrinet.projetnotebook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class ResultActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mRecyclerView = (RecyclerView) findViewById(R.id.result_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        String key = getIntent().getStringExtra("key");
        String where = getIntent().getStringExtra("where");
        if (where.equals("notes")){
            NoteDAO noteDao = NoteDataBase.getDatabase(this).NoteDAO();
            List<Note> dataset = noteDao.searchedNotes(key);
            mAdapter = new NotesAdapter(dataset);
        }

        else if (where.equals("listes")){
            ListeDAO listeDao = NoteDataBase.getDatabase(this).ListeDAO();
            List<Liste> dataset = listeDao.searchedListes(key);
            mAdapter = new ListesAdapter(dataset);
        }


        mRecyclerView.setAdapter(mAdapter);
    }
}
