package com.example.project3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyMealAdapter extends RecyclerView.Adapter<MyMealAdapter.MyViewHolder> {

    Context context;
    ArrayList<String> meals;
    ArrayList<String> calories;

    public MyMealAdapter(Context context, ArrayList<String> meals, ArrayList<String> calories) {
        this.context = context;
        this.meals = meals;
        this.calories = calories;
    }

    @NonNull
    @Override
    public MyMealAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.meal_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyMealAdapter.MyViewHolder holder, int position) {
        holder.meal.setText(meals.get(position));
        holder.calories.setText(calories.get(position)+"cals");
    }

    @Override
    public int getItemCount() {
        if (meals == null) {
            return 0;
        }
        return meals.size();
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
