package com.example.friendster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.friendster.adapter.ProfileViewPagerAdapter;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {


    private ProfileViewPagerAdapter mProfileViewPagerAdapter;

    @BindView(R.id.profile_cover)
    ImageView profileCover;
    @BindView(R.id.profile_image)
    CircleImageView profileImage;
    @BindView(R.id.profile_option_btn)
    Button profileOptionBtn;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.ViewPager_profile)
    ViewPager ViewPagerProfile;

    String uid="0";
    private  int current_state=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //for hiding status bar

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        uid=getIntent().getStringExtra("uid");

        mProfileViewPagerAdapter=new ProfileViewPagerAdapter(getSupportFragmentManager(),1);

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.arrow_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ProfileActivity.this,MainActivity.class));

            }
        });

        ViewPagerProfile.setAdapter(mProfileViewPagerAdapter);

        if (FirebaseAuth.getInstance().getCurrentUser().getUid().equalsIgnoreCase(uid))
        {
            //UID is matched we are going to load our own profile
            current_state=5;
            profileOptionBtn.setText("Edit Profile");

        }
        else
        {
            //load others profile here
        }


    }
}
