package edu.example.stafflist.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import edu.example.stafflist.R;
import edu.example.stafflist.model.Constans;
import edu.example.stafflist.model.Staff;


public class CreateNewStaffFragment extends Fragment implements View.OnTouchListener {

    private EditText etName, etAge, etNumber;
    private RadioGroup rgGender;
    private RadioButton rbGender;
    private Button btnAdd;

    private Gson gson;

    public CreateNewStaffFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gson = new Gson();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_create_new_staff, container, false);
        etName = view.findViewById(R.id.etName);
        etAge = view.findViewById(R.id.etAge);
        etNumber = view.findViewById(R.id.etNumber);
        rgGender = view.findViewById(R.id.rgGender);
        btnAdd = view.findViewById(R.id.btnAdd);

        etName.setOnTouchListener(this);
        etAge.setOnTouchListener(this);
        etNumber.setOnTouchListener(this);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedRb = rgGender.getCheckedRadioButtonId();
                rbGender = view.findViewById(selectedRb);
                validationData();
            }
        });

        return view;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case (R.id.etName):
                etName.setText("");
                break;
            case (R.id.etAge):
                etAge.setText("");
                break;
            case (R.id.etNumber):
                etNumber.setText("");
                break;
        }
        return false;
    }

    private void validationData() {
        if(etName.getText().toString().trim().length()==0 ||
           etAge.getText().toString().trim().length()==0 ||
           etNumber.getText().toString().trim().length()==0 ||
           rgGender.getCheckedRadioButtonId()==-1) {
            Toast.makeText(getActivity(),"Enter correct data", Toast.LENGTH_LONG).show();
        } else {
            Staff staff = new Staff(etName.getText().toString(),etAge.getText().toString(),etNumber.getText().toString(),rbGender.getText().toString());
            String gsonStr = gson.toJson(staff);
            Intent intent = new Intent(getActivity(), CreateNewStaffFragment.class);
            intent.putExtra(Constans.OtherConst.TAG, gsonStr);
            getTargetFragment().onActivityResult(getTargetRequestCode(), Constans.OtherConst.RESULT_CODE, intent);
            getFragmentManager().popBackStack();
        }
    }
}
