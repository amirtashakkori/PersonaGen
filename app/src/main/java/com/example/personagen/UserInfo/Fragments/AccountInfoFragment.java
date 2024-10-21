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

public class AccountInfoFragment extends Fragment {

    View view;
    Bundle bundle;

    TextView userNameTv, passwordTv, dorTv;

    public void cast(){
        userNameTv = view.findViewById(R.id.userNameTv);
        passwordTv = view.findViewById(R.id.passwordTv);
        dorTv = view.findViewById(R.id.dorTv);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_account_info, container, false);
        cast();

        bundle = getArguments();

        if (bundle != null){
            User user = bundle.getParcelable("user");
            if (user != null){
                userNameTv.setText(user.getLogin().getUsername());
                setUpTextViewCopy(userNameTv);

                passwordTv.setText(user.getLogin().getPassword());
                setUpTextViewCopy(passwordTv);
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