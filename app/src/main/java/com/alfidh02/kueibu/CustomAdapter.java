package com.alfidh02.kueibu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList cake_id,cake_deadline,cake_type,cake_quantity,cake_note;

    Animation translateAnim;

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
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.cakeIdText.setText(String.valueOf(cake_id.get(position)));
        holder.cakeDeadlineText.setText(String.valueOf(cake_deadline.get(position)));
        holder.cakeQuantityText.setText(String.valueOf(cake_quantity.get(position)));
        holder.cakeTypeText.setText(String.valueOf(cake_type.get(position)));
        holder.cakeNoteText.setText(String.valueOf(cake_note.get(position)));

        holder.rowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id",String.valueOf(cake_id.get(position)));
                intent.putExtra("deadline",String.valueOf(cake_deadline.get(position)));
                intent.putExtra("quantity",String.valueOf(cake_quantity.get(position)));
                intent.putExtra("type",String.valueOf(cake_type.get(position)));
                intent.putExtra("note",String.valueOf(cake_note.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cake_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout rowLayout;
        TextView cakeIdText,cakeDeadlineText,cakeQuantityText,cakeTypeText,cakeNoteText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cakeIdText = itemView.findViewById(R.id.cakeIdText);
            cakeDeadlineText = itemView.findViewById(R.id.cakeDeadlineText);
            cakeQuantityText = itemView.findViewById(R.id.cakeQuantityText);
            cakeTypeText = itemView.findViewById(R.id.cakeTypeText);
            cakeNoteText = itemView.findViewById(R.id.cakeNoteText);
            rowLayout = itemView.findViewById(R.id.rowLayout);
//            Animate recycler view
            translateAnim = AnimationUtils.loadAnimation(context,R.anim.translate_anim);
            rowLayout.setAnimation(translateAnim);
        }
    }
}
