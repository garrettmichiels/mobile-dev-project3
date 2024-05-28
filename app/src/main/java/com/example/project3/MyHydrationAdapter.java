package com.example.project3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyHydrationAdapter extends RecyclerView.Adapter<MyHydrationAdapter.MyViewHolder> {
    Context context;
    ArrayList<String> hydration;
    ArrayList<String> ml;

    public MyHydrationAdapter(Context context, ArrayList<String> hydration, ArrayList<String> ml) {
        this.context = context;
        this.hydration = hydration;
        this.ml = ml;
    }

    @NonNull
    @Override
    public MyHydrationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.meal_layout, parent, false);
        MyHydrationAdapter.MyViewHolder myViewHolder = new MyHydrationAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHydrationAdapter.MyViewHolder holder, int position) {
        holder.hydration.setText(hydration.get(position));
        holder.ml.setText(ml.get(position)+"ml");
    }

    @Override
    public int getItemCount() {
        if (hydration == null) {
            return 0;
        }
        return hydration.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView hydration, ml;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            hydration = itemView.findViewById(R.id.mealName);
            ml = itemView.findViewById(R.id.calorieCount);
        }
    }
}
