package com.example.personagen.UserInfo.Fragments;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.personagen.Data.Model.User;
import com.example.personagen.R;

import java.util.Objects;


public class PersonalInfoFragment extends Fragment {

    View view;

    TextView userNameTv, userLastNameTv, userGenderTv, userAgeTv, dobTv;

    public void cast(){
        userNameTv = view.findViewById(R.id.userNameTv);
        userLastNameTv = view.findViewById(R.id.userLastNameTv);
        userGenderTv = view.findViewById(R.id.userGenderTv);
        userAgeTv = view.findViewById(R.id.userAgeTv);
        dobTv = view.findViewById(R.id.dobTv);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_personal_info, container, false);
        cast();
        Bundle bundle = getArguments();

        if (bundle != null) {
            User user = bundle.getParcelable("user");
            if (user != null) {
                userNameTv.setText(user.getName().getFirst());
                setUpTextViewCopy(userNameTv);
                
                userLastNameTv.setText(user.getName().getLast());
                setUpTextViewCopy(userLastNameTv);
                
                userGenderTv.setText(user.getGender());
                setUpTextViewCopy(userGenderTv);

                userAgeTv.setText(String.valueOf(user.getDob().getAge()));
                setUpTextViewCopy(userAgeTv);

                dobTv.setText(user.getDob().getDate().substring(0,9));
                setUpTextViewCopy(dobTv);
            }
        }

        return view;
    }

    private void setUpTextViewCopy(TextView textView){
        textView.setOnClickListener(view1 -> {
            String text = textView.getText().toString();
            ClipboardManager clipboard = (ClipboardManager) requireActivity().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("copied text", text);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(getContext(), "Copied!", Toast.LENGTH_SHORT).show();
        });
    }

}