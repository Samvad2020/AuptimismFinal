package com.example.journal.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.journal.homeplan.HomePlanFragment;
import com.example.journal.homeplan.LibraryFragment;
import com.example.journal.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public interface onMenuList {
        public void openMenu(boolean open);

    }

    onMenuList someEventListener;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            someEventListener = (onMenuList) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement onSomeEventListener");
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container1,new HomePlanFragment());
        fragmentTransaction.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        final FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
        final BottomNavigationView bottomNavigationView=getView().findViewById(R.id.navigation2);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.navigation_home_plan){
                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container1,new HomePlanFragment());
                    fragmentTransaction.commit();

                }
//                else if(item.getItemId()==R.id.navigation_library){
//                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//                    fragmentTransaction.replace(R.id.fragment_container1,new LibraryFragment());
//                    fragmentTransaction.commit();
//                }
                return false;
            }
        });
        ImageView img_open_menu=getView().findViewById(R.id.menuOpenIcon);
        img_open_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                someEventListener.openMenu(true);
            }
        });

    }
}
