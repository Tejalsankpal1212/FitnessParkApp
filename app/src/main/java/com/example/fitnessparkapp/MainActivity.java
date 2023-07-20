package com.example.fitnessparkapp;

import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.navigation.NavigationView;
import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.navigationView);
        toolbar=findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle actionBarDrawerToggle= new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.OpenDrawer,R.string.CloseDrawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        LoadFragment(new HomeFragment());

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {

               int id=item.getItemId();

               if (id==R.id.homeMenu)
               {
                    LoadFragment(new HomeFragment());
               }
               else if (id==R.id.profileMenu)
               {
                   LoadFragment(new ProfileFragment());
               }
               else if(id==R.id.classMenu)
               {
                   LoadFragment(new ClassFragment());
               }
               else if (id==R.id.buymembershipMenu)
               {
                   LoadFragment(new BuyMembershipFragment());
               }
               else if (id==R.id.schedulesMenu)
               {
                   LoadFragment(new MySchedulesFragment());
               }
               else if (id==R.id.mymembershipMenu)
               {
                   LoadFragment(new MyMembershipsFragment());
               }
               else if (id==R.id.cartMenu)
               {
                   LoadFragment(new MyCartFragment());
               }
               else if (id==R.id.aboutMenu)
               {
                   LoadFragment(new AboutFragment());
               }
               else if (id==R.id.logoutMenu)
               {

               }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private void LoadFragment(Fragment fragment)
    {
       FragmentManager fm= getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.add(R.id.container,fragment);
        ft.commit();
    }
}