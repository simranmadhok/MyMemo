package com.example.sndtcsi.mymemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder>
{

    protected List<Note> notesList;
    protected Context context;

    public NotesAdapter(List<Note> notesList, Context context)
    {
        this.notesList = notesList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row_layout, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i)
    {
        Note note = notesList.get(i);
        myViewHolder.dot.setText(Html.fromHtml("&#8226;"));
        myViewHolder.title.setText(note.getTitle());
        myViewHolder.description.setText(note.getDescription());
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView title, description, dot;

        public MyViewHolder(View view)
        {
            super(view);
            dot = (TextView) view.findViewById(R.id.dotTextView);
            title = (TextView) view.findViewById(R.id.titleTextView);
            description = (TextView) view.findViewById(R.id.descriptionTextView);
        }
    }

}
