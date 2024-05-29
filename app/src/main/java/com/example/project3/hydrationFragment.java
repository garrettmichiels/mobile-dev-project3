package com.example.project3;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
    private MyHydrationAdapter adapter;


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
            adapter = new MyHydrationAdapter(getActivity(), hydration, ml);
            adapter.notifyDataSetChanged();
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

        RecyclerView hydrationRV = view.findViewById(R.id.hydrationRecyclerView);
        hydrationRV.setLayoutManager(new LinearLayoutManager(getContext()));
        hydrationRV.setAdapter(adapter);

        FloatingActionButton addButton = view.findViewById(R.id.fab);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogForAdd(adapter);
                adapter.notifyDataSetChanged();
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
                            hydration.remove(position);
                            ml.remove(position);
                            adapter.notifyDataSetChanged();
                        }
                        else if (direction == ItemTouchHelper.RIGHT) {
                            int position = viewHolder.getAdapterPosition();
                            //Start dialog
                            openDialogForEdit(position, adapter);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
        itemTouchHelper.attachToRecyclerView(hydrationRV);
        return view;
    }

    public void openDialogForEdit(int position, MyHydrationAdapter adapter) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog dialog = builder.create();
        View view = getLayoutInflater().inflate(R.layout.edit_hydration_layout, null);

        EditText hydrationAmount = view.findViewById(R.id.hydrationAmountText);
        hydrationAmount.setText(ml.get(position));

        builder.setView(view);
        builder.setMessage("Change how much you drank in ml").setTitle("Edit a hydration event");

        builder.setPositiveButton("EDIT", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                hydration.set(position, LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
                ml.set(position, hydrationAmount.getText().toString());
                adapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "Hydration edited", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        builder.show();
    }

    public void openDialogForAdd(MyHydrationAdapter adapter) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog dialog = builder.create();
        View view = getLayoutInflater().inflate(R.layout.edit_hydration_layout, null);

        EditText hydrationAmount = view.findViewById(R.id.hydrationAmountText);

        builder.setView(view);
        builder.setMessage("Add how much you drank in ml").setTitle("Add a hydration event");

        builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                hydration.add(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
                ml.add(hydrationAmount.getText().toString());
                adapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "Hydration edited", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        builder.show();
    }
}