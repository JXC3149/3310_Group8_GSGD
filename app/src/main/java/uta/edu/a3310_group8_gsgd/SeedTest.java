package uta.edu.a3310_group8_gsgd;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import uta.edu.a3310_group8_gsgd.databinding.FragmentSeedTestBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SeedTest#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SeedTest extends Fragment {
    private FragmentSeedTestBinding binding;
    Button btn_logout;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSeedTestBinding.inflate(inflater, container, false);

        btn_logout = binding.btnLogout;

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                NavHostFragment.findNavController(SeedTest.this)
                        .navigate(R.id.action_seedTest_to_Welcome);
                Toast.makeText(getActivity(), "Logout Successful!",
                        Toast.LENGTH_SHORT).show();
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