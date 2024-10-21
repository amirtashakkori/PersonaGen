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

public class LocationFragment extends Fragment {

    View view;
    Bundle bundle;

    TextView countryTv, stateTv, cityTv, nationalityTv, addressTv, postcodeTv;

    public void cast(){
        countryTv = view.findViewById(R.id.countryTv);
        stateTv = view.findViewById(R.id.stateTv);
        cityTv = view.findViewById(R.id.cityTv);
        nationalityTv = view.findViewById(R.id.nationalityTv);
        addressTv = view.findViewById(R.id.addressTv);
        postcodeTv = view.findViewById(R.id.postCodeTv);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_location, container, false);
        cast();

        bundle = getArguments();

        if (bundle != null){
            User user = bundle.getParcelable("user");
            if (user != null){
                countryTv.setText(user.getLocation().getCountry());
                setUpTextViewCopy(countryTv);

                stateTv.setText(user.getLocation().getState());
                setUpTextViewCopy(stateTv);

                cityTv.setText(user.getLocation().getCity());
                setUpTextViewCopy(cityTv);

                String address = user.getLocation().getStreet().getNumber() + " " + user.getLocation().getStreet().getName();
                addressTv.setText(address);
                setUpTextViewCopy(addressTv);

                postcodeTv.setText(user.getLocation().getPostcode());
                setUpTextViewCopy(postcodeTv);
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