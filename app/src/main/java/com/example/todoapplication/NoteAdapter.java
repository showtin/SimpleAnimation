package com.example.todoapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.net.CacheRequest;
import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String> note_id, note_title, note_description, note_date;


    NoteAdapter(Context context, ArrayList note_id, ArrayList note_title, ArrayList note_description,
                ArrayList note_date) {
        this.context = context;
        this.note_id = note_id;
        this.note_title = note_title;
        this.note_description = note_description;
        this.note_date = note_date;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.note_temp, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.note_title_txt.setText(String.valueOf(note_title.get(position)));
        holder.note_date_txt.setText(String.valueOf(note_date.get(position)));
        holder.cardView.setAnimation(AnimationUtils.loadAnimation(holder.cardView.getContext(), R.anim.alpha_in));

    }


    @Override
    public int getItemCount() {
        return note_id.size();

    }

    //
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView note_title_txt, note_date_txt;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            note_title_txt = itemView.findViewById(R.id.title_txt);
            note_date_txt = itemView.findViewById(R.id.date_txt);
            cardView = itemView.findViewById(R.id.cardView);
        }


    }
}
