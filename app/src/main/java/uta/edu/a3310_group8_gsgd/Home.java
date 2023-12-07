package uta.edu.a3310_group8_gsgd;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.navigation.ui.AppBarConfiguration;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import uta.edu.a3310_group8_gsgd.databinding.HomeBinding;
import uta.edu.a3310_group8_gsgd.ml.MobilenetV110224Quant;
import uta.edu.a3310_group8_gsgd.ui.login.LoginFragment;



public class Home extends Fragment{
    private HomeBinding binding;
    FirebaseAuth auth;
    Button btn_logout;
    Button btn_test_seed;
    Button btn_open_gallery;
    Button btn_pro;
    Button btn_seedTest;
    FirebaseUser user;

    TextView txt_result;
    Bitmap bitmap;
    ImageView imageView;

    public static final int INPUT_SIZE = 224;
    public static final int IMAGE_MEAN = 128;
    public static final float IMAGE_STD = 128.0f;
    public static final String INPUT_NAME = "input";
    public static final String OUTPUT_NAME = "final_result";
    public static final String MODEL_FILE = "file:///android_asset/rounded_graph.pb";
    public static final String LABEL_FILE = "file:///android_asset/retrained_labels.txt";


    public Executor executor = Executors.newSingleThreadExecutor();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = HomeBinding.inflate(inflater, container, false);

        auth = FirebaseAuth.getInstance();
        btn_logout = binding.btnLogout;
        btn_test_seed = binding.btnTestSeed; //why does this not want the underscores????
        btn_open_gallery = binding.btnOpenGallery;
        txt_result = binding.testOutput;
        imageView = binding.imageView;
        btn_pro = binding.button;
        btn_seedTest = binding.btnTestSeed;
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
                Toast.makeText(getContext(), "Welcome, " + user.getEmail() + "!\nNeed a change?",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btn_test_seed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(Home.this)
                        .navigate(R.id.action_home2_to_Seed_Test);
                /*

                try {
                    MobilenetV110224Quant model = MobilenetV110224Quant.newInstance(getActivity());

                    // Creates inputs for reference.
                    TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.UINT8);

                    bitmap = Bitmap.createScaledBitmap(bitmap,224, 224, true);
                    inputFeature0.loadBuffer(TensorImage.fromBitmap(bitmap).getBuffer());

                    // Runs model inference and gets result.
                    MobilenetV110224Quant.Outputs outputs = model.process(inputFeature0);
                    TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

                    txt_result.setText(outputFeature0.getFloatArray()[1000]+"");

                    // Releases model resources if no longer used.
                    model.close();
                } catch (IOException e) {
                    // TODO Handle the exception
                }

                 */
            }
        });

        btn_open_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 5);
            }
        });

        return binding.getRoot();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 5) {
            if (data != null) {
                Uri uri = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getActivity().getContentResolver(), uri);
                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
