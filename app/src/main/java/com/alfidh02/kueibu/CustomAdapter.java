package com.alfidh02.kueibu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList cake_id,cake_deadline,cake_type,cake_quantity,cake_note;

    public CustomAdapter(Context context, ArrayList cake_id, ArrayList cake_deadline, ArrayList cake_type, ArrayList cake_quantity, ArrayList cake_note) {
        this.context = context;
        this.cake_id = cake_id;
        this.cake_deadline = cake_deadline;
        this.cake_type = cake_type;
        this.cake_quantity = cake_quantity;
        this.cake_note = cake_note;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.data_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.cakeIdText.setText(String.valueOf(cake_id.get(position)));
        holder.cakeDeadlineText.setText(String.valueOf(cake_deadline.get(position)));
        holder.cakeQuantityText.setText(String.valueOf(cake_quantity.get(position)));
        holder.cakeTypeText.setText(String.valueOf(cake_type.get(position)));
        holder.cakeNoteText.setText(String.valueOf(cake_note.get(position)));
    }

    @Override
    public int getItemCount() {
        return cake_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView cakeIdText,cakeDeadlineText,cakeQuantityText,cakeTypeText,cakeNoteText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cakeIdText = itemView.findViewById(R.id.cakeIdText);
            cakeDeadlineText = itemView.findViewById(R.id.cakeDeadlineText);
            cakeQuantityText = itemView.findViewById(R.id.cakeQuantityText);
            cakeTypeText = itemView.findViewById(R.id.cakeTypeText);
            cakeNoteText = itemView.findViewById(R.id.cakeNoteText);
        }
    }
}