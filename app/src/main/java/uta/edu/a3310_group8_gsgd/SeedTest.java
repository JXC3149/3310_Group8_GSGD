package uta.edu.a3310_group8_gsgd;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;

import uta.edu.a3310_group8_gsgd.databinding.FragmentSeedTestBinding;


public class SeedTest extends Fragment {
    private FragmentSeedTestBinding binding;
    Button btn_logout;
    Button btn_test_image;
    Button btn_open_gallery;
    TextView txt_result;
    Bitmap bitmap;
    ImageView imageView;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSeedTestBinding.inflate(inflater, container, false);

        btn_logout = binding.btnLogout;
        btn_test_image = binding.btnTestImage;
        btn_open_gallery = binding.btnOpenGallery;
        imageView = binding.testImageView;

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

        btn_test_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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