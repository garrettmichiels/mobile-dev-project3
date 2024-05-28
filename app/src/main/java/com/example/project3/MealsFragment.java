package com.example.project3;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

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

        MyAdapter adapter = new MyAdapter(getActivity(), meals, calories);
        adapter.notifyDataSetChanged();

        RecyclerView mealsRV = view.findViewById(R.id.mealsRecyclerView);
        mealsRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mealsRV.setAdapter(adapter);

        //Add button functionality
//        FloatingActionButton addButton = view.findViewById(R.id.fab);
//        addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //add blank meal with 0 cals to meal
//
//                //Toast that they should edit the meal to add cals and change name
//            }
//        });


        return view;
    }
}