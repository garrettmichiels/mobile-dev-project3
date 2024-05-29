package com.example.project3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MySummaryAdapter extends RecyclerView.Adapter<MySummaryAdapter.MyViewHolder> {

    Context context;
    ArrayList<String> dates;
    ArrayList<String> calories;
    ArrayList<String> hydrationAmount;

    public MySummaryAdapter(Context context, ArrayList<String> dates, ArrayList<String> calories, ArrayList<String> hydrationAmount) {
        this.context = context;
        this.dates = dates;
        this.calories = calories;
        this.hydrationAmount = hydrationAmount;
    }

    @NonNull
    @Override
    public MySummaryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.summary_layout, parent, false);
        MySummaryAdapter.MyViewHolder myViewHolder = new MySummaryAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MySummaryAdapter.MyViewHolder holder, int position) {
        holder.date.setText(dates.get(position));
        holder.calories.setText(calories.get(position));
        holder.hydration.setText(hydrationAmount.get(position));
    }

    @Override
    public int getItemCount() {
        return dates.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date, calories, hydration;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.dateText);
            calories = itemView.findViewById(R.id.caloriesText);
            hydration = itemView.findViewById(R.id.hydrationText);
        }
    }
}
