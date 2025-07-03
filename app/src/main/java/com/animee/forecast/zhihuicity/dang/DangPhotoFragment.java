package com.animee.forecast.zhihuicity.dang;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.animee.forecast.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DangPhotoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DangPhotoFragment extends Fragment {
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    private ImageView ivPreview;
    private Button btnCapture, btnSubmit;
    private Bitmap capturedImage;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DangPhotoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment DangPhotoFragment.
     */
    // TODO: Rename and change types and number of parameters

    public static DangPhotoFragment newInstance() {
        return new DangPhotoFragment();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dang_photo, container, false);

        ivPreview = view.findViewById(R.id.iv_preview);
        btnCapture = view.findViewById(R.id.btn_capture);
        btnSubmit = view.findViewById(R.id.btn_submit);

        // 初始状态提交按钮不可见
        btnSubmit.setVisibility(View.GONE);

        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPhoto();
            }
        });

        return view;
    }
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK) {
            Bundle extras = data.getExtras();
            capturedImage = (Bitmap) extras.get("data");
            ivPreview.setImageBitmap(capturedImage);
            btnSubmit.setVisibility(View.VISIBLE);
        }
    }

    private void submitPhoto() {
        if (capturedImage != null) {
            // 这里可以添加实际提交逻辑，如保存到服务器等

            // 重置界面
            ivPreview.setImageResource(android.R.color.transparent);
            btnSubmit.setVisibility(View.GONE);
            capturedImage = null;

            Toast.makeText(getActivity(), "照片提交成功！", Toast.LENGTH_SHORT).show();
        }
    }
}