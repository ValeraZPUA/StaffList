package edu.example.stafflist.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import edu.example.stafflist.R;


public class ListFragment extends Fragment {

    private Button btnCreate;
    private CreateNewStaffFragment createNewStaffFragment;

    public ListFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_list, container, false);
        btnCreate = view.findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewStaffFragment = new CreateNewStaffFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, createNewStaffFragment);
                ft.commit();
            }
        });

        return view;
    }
}
