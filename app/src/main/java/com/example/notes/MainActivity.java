package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter adapter;
    List<Data> data;
    Data value;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.notesList);
        fab=findViewById(R.id.fab);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        data = new ArrayList<>();
        adapter = new Adapter(this, data);
        recyclerView.setAdapter(adapter);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
               LayoutInflater inflater=getLayoutInflater();
              View view= inflater.inflate(R.layout.fab_dialog,null);
               builder.setView(view);
              final AlertDialog alertDialog=builder.create();
              alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.show();

                final EditText title=(EditText)view.findViewById(R.id.title_text);
                final EditText content=(EditText)view.findViewById(R.id.content_text);
                Button addNote=(Button) view.findViewById(R.id.add);

                addNote.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (title.getText().toString().isEmpty()&&content.getText().toString().isEmpty())
                        {
                            title.setError("Required");
                            content.setError("Required");
                        }
                        else
                        {
                            value=new Data(title.getText().toString(),content.getText().toString());
                            data.add(value);
                            alertDialog.dismiss();
                            adapter.notifyDataSetChanged();

                        }
                    }
                });

            }
        });


    }


}
