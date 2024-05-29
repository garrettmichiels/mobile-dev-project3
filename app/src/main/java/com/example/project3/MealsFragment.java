package com.example.project3;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MealsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MealsFragment extends Fragment {

    public interface MyFragmentComInterface{
        void addMeal(String meal, int calories);
    }
    MyFragmentComInterface activityCommunicator;

    View view;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters
    private String username;
    private ArrayList<String> meals;
    private ArrayList<String> calories;
    private MyMealAdapter adapter;

    public MealsFragment() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment MealsFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static MealsFragment newInstance(String param1, String param2) {
//        MealsFragment fragment = new MealsFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }


//    this.getactivity().getsharedpref
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            username = bundle.getString("USERNAME");
            Log.d("USERNAME", username);
            meals = bundle.getStringArrayList("meals");
            Log.d("MEALS SIZE", meals.size() + "");
            calories = bundle.getStringArrayList("calories");
            Log.d("CALORIES SIZE", calories.size() + "");
            adapter = new MyMealAdapter(getActivity(), meals, calories);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (getArguments() != null) {

            Bundle bundle = getArguments();
            username = bundle.getString("USERNAME");
            Log.d("USERNAME", username);
            meals = bundle.getStringArrayList("meals");
            Log.d("MEALS SIZE", meals.size() + "");
            calories = bundle.getStringArrayList("calories");
            Log.d("CALORIES SIZE", calories.size() + "");
        }


        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_meals, container, false);

        RecyclerView mealsRV = view.findViewById(R.id.mealsRecyclerView);
        mealsRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mealsRV.setAdapter(adapter);

        //Add button functionality
        FloatingActionButton addButton = view.findViewById(R.id.fab);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogForAdd(adapter);

            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        if (direction == ItemTouchHelper.LEFT) {
                            int position = viewHolder.getAdapterPosition();
                            meals.remove(position);
                            calories.remove(position);
                            adapter.notifyDataSetChanged();
                        }
                        else if (direction == ItemTouchHelper.RIGHT) {
                            int position = viewHolder.getAdapterPosition();
                            //Start dialog
                            openDialog(position, adapter);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
        itemTouchHelper.attachToRecyclerView(mealsRV);
        return view;
    }
    public void openDialog(int position, MyMealAdapter adapter) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog dialog = builder.create();
        View view = getLayoutInflater().inflate(R.layout.edit_meal_layout, null);

        EditText mealName = view.findViewById(R.id.mealNameText);
        mealName.setText(meals.get(position));
        EditText mealCals = view.findViewById(R.id.mealCaloriesText);
        mealCals.setText(calories.get(position));

        builder.setView(view);
        builder.setMessage("Edit how much you ate in calories").setTitle("Edit a meal");

        builder.setPositiveButton("EDIT", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                meals.set(position, mealName.getText().toString());
                calories.set(position, mealCals.getText().toString());
                adapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "Meal edited", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        builder.show();
    }

    public void openDialogForAdd(MyMealAdapter adapter) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog dialog = builder.create();
        View view = getLayoutInflater().inflate(R.layout.edit_meal_layout, null);

        EditText mealName = view.findViewById(R.id.mealNameText);
        EditText mealCals = view.findViewById(R.id.mealCaloriesText);

        builder.setView(view);
        builder.setMessage("Add how much you ate in calories").setTitle("Add a meal");

        builder.setPositiveButton("EDIT", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                meals.add(mealName.getText().toString());
                calories.add(mealCals.getText().toString());
                adapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "Meal Added", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        builder.show();
    }
}