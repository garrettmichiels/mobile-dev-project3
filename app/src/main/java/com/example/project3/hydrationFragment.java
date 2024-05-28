package com.example.project3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalTime;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link hydrationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class hydrationFragment extends Fragment {
    View view;
    private String username;
    private ArrayList<String> hydration, ml;


    public hydrationFragment() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment hydrationFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static hydrationFragment newInstance(String param1, String param2) {
//        hydrationFragment fragment = new hydrationFragment();
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
            Bundle bundle = getArguments();
            username = bundle.getString("USERNAME");
            Log.d("USERNAME", username);
            hydration = bundle.getStringArrayList("hydration");
            Log.d("MEALS SIZE", hydration.size() + "");
            ml = bundle.getStringArrayList("ml");
            Log.d("CALORIES SIZE", ml.size() + "");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            username = bundle.getString("USERNAME");
            Log.d("USERNAME", username);
            hydration = bundle.getStringArrayList("hydration");
            Log.d("MEALS SIZE", hydration.size() + "");
            ml = bundle.getStringArrayList("ml");
            Log.d("CALORIES SIZE", ml.size() + "");
        }


        view = inflater.inflate(R.layout.fragment_hydration, container, false);


        MyHydrationAdapter adapter = new MyHydrationAdapter(getActivity(), hydration, ml);
        adapter.notifyDataSetChanged();

        RecyclerView hydrationRV = view.findViewById(R.id.hydrationRecyclerView);
        hydrationRV.setLayoutManager(new LinearLayoutManager(getContext()));
        hydrationRV.setAdapter(adapter);

        FloatingActionButton addButton = view.findViewById(R.id.fab);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Add how much you drank in ml").setTitle("Add a hydration event");

                builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });


                AlertDialog dialog = builder.create();

//                FrameLayout fl = findViewById(android.R.id.custom);
//                fl.addView(myView, new LayoutParams(MATCH_PARENT, WRAP_CONTENT));
                hydration.add(LocalTime.now().toString());
                ml.add("0");
                adapter.notifyDataSetChanged();
            }
        });
        return view;
    }
}