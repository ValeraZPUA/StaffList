package edu.example.stafflist.fragments;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.example.stafflist.R;
import edu.example.stafflist.model.Constans;
import edu.example.stafflist.model.Staff;
import edu.example.stafflist.model.StaffDBHelper;
import edu.example.stafflist.model.StaffData;


public class ListFragment extends Fragment {

    private Button btnCreate;
    private RecyclerView staffRecyclerView;
    private StaffAdapter staffAdapter;
    private CreateNewStaffFragment createNewStaffFragment;
    private List<Staff> staff;

    private Cursor cursor;
    private SQLiteDatabase database;

    public ListFragment() {

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StaffDBHelper dbHelper = new StaffDBHelper(getActivity());
        StaffData staffData = new StaffData(dbHelper);
        staff = staffData.create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_list, container, false);

        staffRecyclerView = view.findViewById(R.id.rvStaffList);
        staffRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        btnCreate = view.findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewStaffFragment = new CreateNewStaffFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.fragment_container, createNewStaffFragment);
                ft.commit();
            }
        });

        staffAdapter = new StaffAdapter(staff);
        staffRecyclerView.setAdapter(staffAdapter);

        //updateUI();

        return view;
    }

    private void updateUI() {

        staffAdapter = new StaffAdapter(staff);
        staffRecyclerView.setAdapter(staffAdapter);

    }

    private class StaffHolder extends RecyclerView.ViewHolder {

        private TextView tvName, tvAge, tvPhoneNumber, tvGender;
        private Staff sStaff;

        public StaffHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_staff, parent, false));

            tvName = itemView.findViewById(R.id.tvName);
            tvAge = itemView.findViewById(R.id.tvAge);
            tvPhoneNumber = itemView.findViewById(R.id.tvNumber);
            tvGender = itemView.findViewById(R.id.tvGender);
        }

        public void bind(Staff staff) {

            sStaff = staff;
            Log.d("tag", "bind " + sStaff.getName());
            tvName.setText(staff.getName());
            tvAge.setText(String.valueOf(staff.getAge()));
            tvPhoneNumber.setText(staff.getPhoneNumber());
            tvGender.setText(staff.getGender());
            Log.d("tag", "bind end" );
        }
    }

    private class StaffAdapter extends RecyclerView.Adapter<StaffHolder> {

        private List<Staff> sStaff;

        public StaffAdapter(List<Staff> staff) {
           sStaff = staff;
        }

        @NonNull
        @Override
        public StaffHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            Log.d("tag", "onCreateViewHolder");
            return new StaffHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(@NonNull StaffHolder holder, int position) {
            Log.d("tag", "onBindViewHolder");
            Staff staff = sStaff.get(position);
            Log.d("tag", "onBindViewHolder position " + position);
            Log.d("tag", "onBindViewHolder staff " + staff.getName());
            holder.bind(staff);
        }

        @Override
        public int getItemCount() {
            int size = staff.size();
            Log.d("tag", String.valueOf(size));
            return size;
        }
    }
}
