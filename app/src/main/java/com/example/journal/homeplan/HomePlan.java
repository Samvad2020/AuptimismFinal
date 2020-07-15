package com.example.journal.homeplan;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.journal.R;
import com.example.journal.SideNavBar.ContactUsActivity;
import com.example.journal.SideNavBar.DrawerItemCustomAdapter;
import com.example.journal.IEP.IEPActivity;
import com.example.journal.MyProfile.MyProfileActivity;
import com.example.journal.journaling.MainActivity;
import com.example.journal.model.DataModel;
import com.example.journal.ui.home.HomeFragment;
import com.example.journal.ui.journal.JournalFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

public class HomePlan extends AppCompatActivity implements HomePlanFragment.onSomeActivitySelected, JournalFragment.onStartJournaling, HomeFragment.onMenuList {
    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    ActionBarDrawerToggle mDrawerToggle;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_plan);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);
        mTitle = mDrawerTitle = getTitle();
        mNavigationDrawerItemTitles= getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        DataModel[] drawerItem = new DataModel[3];

        drawerItem[0] = new DataModel(R.drawable.profile, "My Profile");
        drawerItem[1] = new DataModel(R.drawable.profile, "IEP");
        drawerItem[2] = new DataModel(R.drawable.profile, "Contact Us");
        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.list_view_item_new, drawerItem);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

    }

    @Override
    public void someActivitySelected(int num, String type) {
        Intent intent=new Intent(HomePlan.this,HomePlanActivityDetailedInfo.class);
        intent.putExtra("type",type);
        intent.putExtra("pos",num);
        startActivity(intent);
    }



    @Override
    public void someJournalingSelected(boolean check) {
        if(check){
            Intent intent=new Intent(HomePlan.this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Yes", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void openMenu(boolean open) {
        if(open){
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

    }

    private void selectItem(int position) {


        switch (position) {
            case 0:
                Intent intent=new Intent(this, MyProfileActivity.class);
                startActivity(intent);
                break;
            case 1:
                Intent intent1=new Intent(this, IEPActivity.class);
                startActivity(intent1);
                break;
            case 2:
                Intent intent2=new Intent(this, ContactUsActivity.class);
                startActivity(intent2);
                break;

            default:
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }


}
