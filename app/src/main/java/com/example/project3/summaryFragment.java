package com.example.project3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link summaryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class summaryFragment extends Fragment {
    View view;
    String username;
    int height;
    int weight;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public summaryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment summaryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static summaryFragment newInstance(String param1, String param2) {
        summaryFragment fragment = new summaryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
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

        return view;
    }

    public float calculateBMI(int height, int weight){
        return (float) (weight * 703) / (height * height);
    }
}