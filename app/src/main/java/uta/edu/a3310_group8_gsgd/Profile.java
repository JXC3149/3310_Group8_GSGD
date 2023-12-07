package uta.edu.a3310_group8_gsgd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import uta.edu.a3310_group8_gsgd.databinding.FragmentProfileBinding;
import uta.edu.a3310_group8_gsgd.databinding.HomeBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile extends Fragment {
    private FragmentProfileBinding binding;
    Button btn_logout;
    Button btn_home;
    TextView textView;
    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        if (user == null) {
            NavHostFragment.findNavController(Profile.this)
                    .navigate(R.id.action_profile_to_Welcome);
        }
        else {

            //get email maybe
        }

        btn_logout = binding.btnLogout;
        btn_home = binding.btnHome;
        textView = binding.textView;



        textView.setText(user.getEmail());

        btn_logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseAuth.getInstance().signOut();
                    Toast.makeText(getActivity(), "Logout Successful!",
                            Toast.LENGTH_SHORT).show();
                    NavHostFragment.findNavController(Profile.this)
                            .navigate(R.id.action_profile_to_Welcome);

                }
            });
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Home!",
                        Toast.LENGTH_SHORT).show();
                NavHostFragment.findNavController(Profile.this)
                        .navigate(R.id.action_profile_to_home2);

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