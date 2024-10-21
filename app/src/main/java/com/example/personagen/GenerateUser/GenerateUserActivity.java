package com.example.personagen.GenerateUser;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.personagen.Data.Api.ApiServiceProvider;
import com.example.personagen.Data.DataBase.AppDataBase;
import com.example.personagen.Data.Model.User;
import com.example.personagen.Data.UserRepository;
import com.example.personagen.Factory;
import com.example.personagen.R;
import com.example.personagen.UserInfo.UserInfoActivity;

public class GenerateUserActivity extends AppCompatActivity {

    GenerateViewModel viewModel;

    AutoCompleteTextView genderSpinner, natSpinner;
    AppCompatButton generateBtn;
    ImageView backBtn;

    public void cast(){
        genderSpinner = findViewById(R.id.genderSpinner);
        natSpinner = findViewById(R.id.natSpinner);
        generateBtn = findViewById(R.id.generateBtn);
        backBtn = findViewById(R.id.backBtn);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        cast();

        setNationalitySpinner();
        setGenderSpinner();

        viewModel = new ViewModelProvider(this, new Factory(new UserRepository(
                ApiServiceProvider.getApiService(), AppDataBase.getInstance(GenerateUserActivity.this).getDao())))
                .get(GenerateViewModel.class);


        generateBtn.setOnClickListener(view -> {
            generateBtnClicked();
        });

        backBtn.setOnClickListener(view1 -> {
            finish();
        });
    }

    public void setGenderSpinner(){
        String[] gender_spinner_items = getResources().getStringArray(R.array.gender);
        ArrayAdapter<String> genderSpinnerAdapter = new ArrayAdapter<>(this, R.layout.item_spinner, R.id.spinnerTv, gender_spinner_items);
        genderSpinner.setAdapter(genderSpinnerAdapter);
        genderSpinner.setText(gender_spinner_items[0], false);
    }

    public void setNationalitySpinner(){
        String[] nat_spinner_items = getResources().getStringArray(R.array.nationalities);
        ArrayAdapter<String> nationalitiesAdapter = new ArrayAdapter<>(this, R.layout.item_spinner, R.id.spinnerTv, nat_spinner_items);
        natSpinner.setAdapter(nationalitiesAdapter);
        natSpinner.setText(nat_spinner_items[0], false);
    }

    public String getNat(String nat){
        if (nat.equals("United State"))
            return "us";
        else if (nat.equals("United Kingdom")) {
            return "gb";
        }
        else if (nat.equals("Australia")) {
            return "au";
        }
        else if (nat.equals("Canada")) {
            return "ca";
        }
        else if (nat.equals("Switzerland")) {
            return "ch";
        }
        else if (nat.equals("Germany")) {
            return "de";
        }
        else if (nat.equals("Spain")) {
            return "es";
        }
        else if (nat.equals("France")) {
            return "fr";
        }
        else if (nat.equals("Turkey")) {
            return "tr";
        }
        else if (nat.equals("Ukraine")) {
            return "ua";
        }
        else{
            return "nl";
        }
    }

    public void generateBtnClicked(){
        String gender = genderSpinner.getText().toString().toLowerCase();
        String nat = getNat(natSpinner.getText().toString());

        viewModel.generateUser(gender, nat);
        viewModel.getUser().observe(GenerateUserActivity.this, user -> {
            if (user != null){
                Intent intent = new Intent(GenerateUserActivity.this, UserInfoActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            } else {
                viewModel.getError().observe(GenerateUserActivity.this, s -> {
                    Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
                    Log.i("generateError", s);
                });
            }
        });
    }

}