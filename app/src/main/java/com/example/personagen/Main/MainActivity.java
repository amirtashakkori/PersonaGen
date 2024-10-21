package com.example.personagen.Main;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personagen.Data.Api.ApiServiceProvider;
import com.example.personagen.Data.DataBase.AppDataBase;
import com.example.personagen.Data.Model.User;
import com.example.personagen.Data.UserRepository;
import com.example.personagen.Factory;
import com.example.personagen.GenerateUser.GenerateUserActivity;
import com.example.personagen.GenerateUser.GenerateViewModel;
import com.example.personagen.NetworkUtils;
import com.example.personagen.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements UserAdapter.changeListener {

    RecyclerView userRv;
    FloatingActionButton addBtn;
    ImageView emptyStateImg;

    MainViewModel viewModel;
    UserAdapter adapter;

    public void cast(){
        userRv = findViewById(R.id.userRv);
        addBtn = findViewById(R.id.addBtn);
        emptyStateImg = findViewById(R.id.emptyStateImg);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        cast();

        viewModel = new ViewModelProvider(this, new Factory(new UserRepository(
                ApiServiceProvider.getApiService(), AppDataBase.getInstance(MainActivity.this).getDao())))
                .get(MainViewModel.class);

        if (!NetworkUtils.isNetworkAvailable(this)){
            showNoInternetDialog(this, getResources().getString(R.string.not_internet_load_image));
        }

        viewModel.getUsers().observe(this, users -> {
            if (!users.isEmpty()){
                emptyStateVisibility(false);
                userRv.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));
                adapter = new UserAdapter(MainActivity.this, users, this);
                userRv.setAdapter(adapter);
            } else {
                emptyStateVisibility(true);
            }
        });

        addBtn.setOnClickListener(view -> {
            if(NetworkUtils.isNetworkAvailable(MainActivity.this)){
                Intent intent = new Intent(MainActivity.this, GenerateUserActivity.class);
                startActivity(intent);
            } else {
                showNoInternetDialog(MainActivity.this, getResources().getString(R.string.no_internet_user_generating));
                Toast.makeText(this, "No internet", Toast.LENGTH_SHORT).show();
            }

        });
    }

    public static void showNoInternetDialog(Context context, String message) {
        new AlertDialog.Builder(context)
                .setTitle("No Internet Connection")
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss(); // Closes the dialog
                    }
                })
                .setCancelable(false)
                .show();
    }


    public void emptyStateVisibility(Boolean visible){
        if (visible){
            emptyStateImg.setVisibility(View.VISIBLE);
            userRv.setVisibility(View.GONE);
        } else {
            emptyStateImg.setVisibility(View.GONE);
            userRv.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDelete(User user) {
        viewModel.deleteUser(user);
        viewModel.getUsers();
    }


}