package com.example.personagen.UserInfo;

import android.annotation.SuppressLint;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.personagen.Data.Api.ApiServiceProvider;
import com.example.personagen.Data.DataBase.AppDataBase;
import com.example.personagen.Data.Model.User;
import com.example.personagen.Data.UserRepository;
import com.example.personagen.Factory;
import com.example.personagen.R;
import com.example.personagen.UserInfo.Fragments.AccountInfoFragment;
import com.example.personagen.UserInfo.Fragments.CommunicationFragment;
import com.example.personagen.UserInfo.Fragments.LocationFragment;
import com.example.personagen.UserInfo.Fragments.PersonalInfoFragment;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class UserInfoActivity extends AppCompatActivity {

    UserInfoViewModel viewModel;
    User user;
    Bundle bundle;

    ImageView userImg;
    TextView userNameTv;
    CardView backBtn, deleteBtn;
    TabLayout infoTb;
    FrameLayout container;

    public void cast(){
        userImg = findViewById(R.id.userImg);
        userNameTv = findViewById(R.id.userNameTv);
        backBtn = findViewById(R.id.backBtn);
        deleteBtn = findViewById(R.id.deleteBtn);
        infoTb = findViewById(R.id.infoTb);
        container = findViewById(R.id.container);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        cast();

        viewModel = new ViewModelProvider(this, new Factory(new UserRepository(ApiServiceProvider.getApiService(), AppDataBase.getInstance(UserInfoActivity.this).getDao()))).get(UserInfoViewModel.class);

        boolean deleteBtnVisibility = getIntent().getBooleanExtra("deleteBtnVisibility", false);
        if (deleteBtnVisibility){
            deleteBtn.setVisibility(View.VISIBLE);
        }

        user = getIntent().getParcelableExtra("user");
        if (user != null){
            Picasso.get().load(user.getPicture().getLarge()).into(userImg);
            String name = user.getName().getFirst() + " " + user.getName().getLast();
            userNameTv.setText(name);

            deleteBtn.setOnClickListener(view -> {
                viewModel.deleteUser(user);
                Toast.makeText(this, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
                finish();
            });
        }

        setTabs();
        loadFragment(new PersonalInfoFragment());

        backBtn.setOnClickListener(view -> {
            finish();
        });

    }

    public void setTabs(){
        TabLayout.Tab tab1 = infoTb.newTab().setIcon(R.drawable.ic_ui_user);
        TabLayout.Tab tab2 = infoTb.newTab().setIcon(R.drawable.ic_ui_call);
        TabLayout.Tab tab3 = infoTb.newTab().setIcon(R.drawable.ic_ui_email);
        TabLayout.Tab tab4 = infoTb.newTab().setIcon(R.drawable.ic_ui_location);

        infoTb.addTab(tab1);
        infoTb.addTab(tab2);
        infoTb.addTab(tab3);
        infoTb.addTab(tab4);
        infoTb.selectTab(tab1);

        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(android.R.attr.colorAccent, typedValue, true);
        int colorAccent = typedValue.data;

        Objects.requireNonNull(tab1.getIcon())
                .setColorFilter(colorAccent, PorterDuff.Mode.SRC_IN);

        infoTb.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Objects.requireNonNull(tab.getIcon())
                        .setColorFilter(colorAccent, PorterDuff.Mode.SRC_IN);

                switch (tab.getPosition()){
                    case 0 :
                        loadFragment(new PersonalInfoFragment());
                        break;
                    case 1:
                        loadFragment(new CommunicationFragment());
                        break;
                    case 2:
                        loadFragment(new AccountInfoFragment());
                        break;
                    case 3:
                        loadFragment(new LocationFragment());
                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Objects.requireNonNull(tab.getIcon()).setColorFilter(ContextCompat.getColor(UserInfoActivity.this, R.color.white), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void loadFragment(Fragment fragment){
        @SuppressLint("CommitTransaction")
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        bundle = new Bundle();
        bundle.putParcelable("user", user);
        fragment.setArguments(bundle);
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }

}