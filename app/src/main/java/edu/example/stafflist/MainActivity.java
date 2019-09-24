package edu.example.stafflist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import edu.example.stafflist.fragments.CreateNewStaffFragment;
import edu.example.stafflist.fragments.ListFragment;

public class MainActivity extends AppCompatActivity {

    private ListFragment staffListFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        staffListFragment = new ListFragment();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment_container,staffListFragment);
        ft.commit();


    }


}
