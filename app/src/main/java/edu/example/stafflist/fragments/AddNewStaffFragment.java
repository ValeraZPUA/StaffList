package edu.example.stafflist.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import edu.example.stafflist.R;

public class AddNewStaffFragment extends Fragment {

    public AddNewStaffFragment() {
        // Required empty public constructor
    }

    private EditText etName, etNumber, etAge;
    private RadioButton rbMale, rbFemale;
    private Button btnAdd;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_new_staff, container, false);
        etName = view.findViewById(R.id.etName);
        etNumber = view.findViewById(R.id.etNumber);
        etAge = view.findViewById(R.id.etAge);
        btnAdd = view.findViewById(R.id.btnAdd);
        rbMale = view.findViewById(R.id.rbMale);
        rbFemale = view.findViewById(R.id.rbFemale);

        return view;
    }


}
