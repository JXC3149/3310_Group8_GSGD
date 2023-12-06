package uta.edu.a3310_group8_gsgd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import uta.edu.a3310_group8_gsgd.databinding.HomeBinding;

public class Home extends Fragment{
    private HomeBinding binding;
    FirebaseAuth auth;
    Button btn_logout;
    Button btn_pro;
    Button btn_seedTest;
    FirebaseUser user;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = HomeBinding.inflate(inflater, container, false);

        auth = FirebaseAuth.getInstance();
        btn_logout = binding.btnLogout;
        btn_pro = binding.button;
        btn_seedTest = binding.button3;
        user = auth.getCurrentUser();
        if (user == null) {
            NavHostFragment.findNavController(Home.this)
                    .navigate(R.id.action_home2_to_Welcome);
        }
        else {
            //get email maybe
        }

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                NavHostFragment.findNavController(Home.this)
                        .navigate(R.id.action_home2_to_Welcome);
                Toast.makeText(getActivity(), "Logout Successful!",
                        Toast.LENGTH_SHORT).show();
            }
        });
        btn_pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                FirebaseAuth.getInstance().signOut();
*/
                NavHostFragment.findNavController(Home.this)
                        .navigate(R.id.action_home2_to_profile);
            }
        });
        btn_seedTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                FirebaseAuth.getInstance().signOut();
*/
                NavHostFragment.findNavController(Home.this)
                        .navigate(R.id.action_home2_to_Seed_Test);
            }
        });

        return binding.getRoot();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
