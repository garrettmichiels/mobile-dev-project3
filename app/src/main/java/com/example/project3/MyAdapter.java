package com.example.project3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    String[] meals;
    String[] calories;

    public MyAdapter(Context context, String[] meals, String[] calories) {
        this.context = context;
        this.meals = meals;
        this.calories = calories;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.meal_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        holder.meal.setText(meals[position]);
        holder.calories.setText(calories[position]);
    }

    @Override
    public int getItemCount() {
        return meals.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView meal, calories;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            meal = itemView.findViewById(R.id.mealName);
            calories = itemView.findViewById(R.id.calorieCount);
        }
    }
}
