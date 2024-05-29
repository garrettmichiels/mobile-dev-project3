package com.example.project3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;


public class summaryFragment extends Fragment {
    View view;
    String username;
    int height;
    int weight;

    ArrayList<String> dates = new ArrayList<>();

    ArrayList<String> calories = new ArrayList<>();
    ArrayList<String> hydration = new ArrayList<>();


    public summaryFragment() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment summaryFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static summaryFragment newInstance(String param1, String param2) {
//        summaryFragment fragment = new summaryFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_summary, container, false);

        if (getArguments() != null) {
            Bundle bundle = getArguments();
            username = bundle.getString("USERNAME");
            height = bundle.getInt("height");
            weight = bundle.getInt("weight");
            TextView name = view.findViewById(R.id.usernameText);
            name.setText(username);
            TextView heightTV = view.findViewById(R.id.userHeightText);
            heightTV.setText("Height: " + height);
            TextView weightTV = view.findViewById(R.id.userWeightText);
            weightTV.setText("Weight: " + weight);
            TextView BMI_TV = view.findViewById(R.id.userBMIText);
            BMI_TV.setText("BMI: " + calculateBMI(height, weight));
        }


        //Create sample data for summary
        dates.addAll(Arrays.asList("May 14", "May 15", "May 16"));
        calories.addAll(Arrays.asList("2100cals", "2200cals", "2000cals"));
        hydration.addAll(Arrays.asList("1000ml", "1100ml", "1200ml"));
        MySummaryAdapter adapter = new MySummaryAdapter(getActivity(), dates, calories, hydration);
        adapter.notifyDataSetChanged();

        RecyclerView summaryRV = view.findViewById(R.id.summaryRecyclerView);
        summaryRV.setLayoutManager(new LinearLayoutManager(getContext()));
        summaryRV.setAdapter(adapter);

        return view;
    }

    public float calculateBMI(int height, int weight){
        return (float) (weight * 703) / (height * height);
    }
}