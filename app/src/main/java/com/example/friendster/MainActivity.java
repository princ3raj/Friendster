package com.example.friendster;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.friendster.Fragments.FriendsFragment;
import com.example.friendster.Fragments.NewsFeedFragment;
import com.example.friendster.Fragments.NotificationFragment;
import com.example.friendster.utils.BottomNavigationViewHelper;
import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private NewsFeedFragment mNewsFeedFragment;
    private NotificationFragment mNotificationFragment;
    private FriendsFragment mFriendsFragment;

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.search)
    ImageView search;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.framelayout)
    FrameLayout framelayout;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

       setSupportActionBar(toolbar);
       Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        bottomNavigation.inflateMenu(R.menu.bottom_navigation_main);
        bottomNavigation.setItemBackgroundResource(R.color.colorPrimary);
        bottomNavigation.setItemTextColor(ContextCompat.getColorStateList(bottomNavigation.getContext(),R.color.nav_item_colors));
        bottomNavigation.setItemIconTintList(ContextCompat.getColorStateList(bottomNavigation.getContext(),R.color.nav_item_colors));
        BottomNavigationViewHelper.removeShiftMode(bottomNavigation);

        mFriendsFragment=new FriendsFragment();
        mNewsFeedFragment=new NewsFeedFragment();
        mNotificationFragment=new NotificationFragment();

        setFragment(mNewsFeedFragment);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId())
                {
                    case R.id.newsfeed_fragment:

                        setFragment(mNewsFeedFragment);

                        break;

                    case R.id.profile_fragment:

                        Intent intent=new Intent(MainActivity.this,ProfileActivity.class);

                        startActivity(intent.putExtra("uid", Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()));


                        break;

                    case R.id.profile_friends:

                        setFragment(mFriendsFragment);

                        break;

                    case R.id.profile_notification:

                        setFragment(mNotificationFragment);

                        break;


                }
                return true;
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,UploadActivity.class));
            }
        });



    }

public void setFragment(Fragment fragment)
{

    FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
    fragmentTransaction.replace(R.id.framelayout,fragment);
    fragmentTransaction.commit();




}

}