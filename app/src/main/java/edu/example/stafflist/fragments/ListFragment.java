package edu.example.stafflist.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import java.util.List;
import edu.example.stafflist.R;
import edu.example.stafflist.model.Constans;
import edu.example.stafflist.model.Staff;
import edu.example.stafflist.StaffDBHelper;
import edu.example.stafflist.StaffData;


public class ListFragment extends Fragment {

    private Button btnCreate;
    private RecyclerView staffRecyclerView;
    private StaffAdapter staffAdapter;
    private CreateNewStaffFragment createNewStaffFragment;
    private List<Staff> staff;
    private Gson gson;
    private StaffData staffData;

    public ListFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gson = new Gson();
        StaffDBHelper dbHelper = new StaffDBHelper(getActivity());
        staffData = new StaffData(dbHelper);
        if(staff==null) {
            staff = staffData.create();
        }
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
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                createNewStaffFragment = new CreateNewStaffFragment();
                createNewStaffFragment.setTargetFragment(ListFragment.this, Constans.OtherConst.REQUEST_CODE);
                ft.addToBackStack(createNewStaffFragment.getClass().getName());
                ft.replace(R.id.fragment_container, createNewStaffFragment);
                ft.commit();
            }
        });

        staffAdapter = new StaffAdapter(staff);
        staffRecyclerView.setAdapter(staffAdapter);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Constans.OtherConst.RESULT_CODE) {
            if (requestCode==Constans.OtherConst.REQUEST_CODE){
                String gsonNewStaff =data.getStringExtra(Constans.OtherConst.TAG);
                addStaff(gsonNewStaff);
            }
        }
    }

    public void addStaff(String gsonNewStaff) {
        Staff newStaff = gson.fromJson(gsonNewStaff, Staff.class);
        staff.add(newStaff);
        staffData.addToDB(newStaff);
    }

    private class StaffHolder extends RecyclerView.ViewHolder {

        private TextView tvName, tvAge, tvPhoneNumber, tvGender;
        private Staff sStaff;

        public StaffHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_staff, parent, false));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(getActivity(), sStaff.getId().toString(),Toast.LENGTH_SHORT).show();
                }
            });

            tvName = itemView.findViewById(R.id.tvName);
            tvAge = itemView.findViewById(R.id.tvAge);
            tvPhoneNumber = itemView.findViewById(R.id.tvNumber);
            tvGender = itemView.findViewById(R.id.tvGender);
        }

        public void bind(Staff staff) {

            sStaff = staff;
            tvName.setText(staff.getName());
            tvAge.setText(String.valueOf(staff.getAge()));
            tvPhoneNumber.setText(staff.getPhoneNumber());
            tvGender.setText(staff.getGender());
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
            return new StaffHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(@NonNull StaffHolder holder, int position) {
            Staff staff = sStaff.get(position);
            holder.bind(staff);
        }

        @Override
        public int getItemCount() {
            int size = staff.size();
            return size;
        }
    }


}
