package com.dl.ms.mspro1.main;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.app.dl.uilibrary.tab.CustomTabView;
import com.app.dl.uilibrary.tab.Tab;
import com.dl.ms.mspro1.R;
import com.dl.ms.mspro1.main.fragment.MyFragment;
import com.dl.ms.mspro1.main.fragment.Tab1Fragment;
import com.dl.ms.mspro1.main.fragment.Tab2Fragment;
import com.dl.ms.mspro1.main.fragment.Tab3Fragment;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity implements
        CustomTabView.OnTabCheckListener {

    private Tab1Fragment mTab1Fragment;
    private Tab2Fragment mTab2Fragment;
    private Tab3Fragment mTab3Fragment;
    private MyFragment mTab4Fragment;

    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lc_test);
        initView();
        initFragment();
    }

    private void initView() {
        CustomTabView mCustomTabView = findViewById(R.id.tabView);

        Tab tab1 = new Tab().setText("首页")
                .setNormalIcon(R.drawable.tab_1)
                .setPressedIcon(R.drawable.tab_1_sel);
        mCustomTabView.addTab(tab1);

        Tab tab2 = new Tab().setText("赛事")
                .setNormalIcon(R.drawable.tab_2)
                .setPressedIcon(R.drawable.tab_2_sel);
        mCustomTabView.addTab(tab2);

        Tab tab3 = new Tab().setText("赛车")
                .setNormalIcon(R.drawable.tab_3)
                .setPressedIcon(R.drawable.tab_3_sel);
        mCustomTabView.addTab(tab3);

        Tab tab4 = new Tab().setText("我的")
                .setNormalIcon(R.drawable.tab_4)
                .setPressedIcon(R.drawable.tab_4_sel);
        mCustomTabView.addTab(tab4);

        mCustomTabView.setCurrentItem(0);
        mCustomTabView.setOnTabCheckListener(this);
    }

    private void initFragment() {
        mTab1Fragment = new Tab1Fragment();
        addFragment(mTab1Fragment);
        showFragment(mTab1Fragment);
    }

    private void showFragment(Fragment fragment) {
        for (Fragment frag: mFragments) {
            //隐藏其它fragment
            if (frag != fragment) {
                getSupportFragmentManager().beginTransaction().hide(frag).commit();
            }
        }
        getSupportFragmentManager().beginTransaction().show(fragment).commit();
    }

    private void addFragment(Fragment fragment) {
        //判断该fragment是否添加，没有添加则添加
        if (!fragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().add(R.id.fl_content, fragment).commit();
            addToList(fragment);
        }
    }

    private void addToList(Fragment fragment) {
        if (fragment != null) {
            mFragments.add(fragment);
        }
    }

    @Override
    public void onTabSelected(View v, int position) {
        switch (position) {
            case 0:
                if (mTab1Fragment == null) {
                    mTab1Fragment = new Tab1Fragment();
                }
                addFragment(mTab1Fragment);
                showFragment(mTab1Fragment);
                break;
            case 1:
                if (mTab2Fragment == null) {
                    mTab2Fragment = new Tab2Fragment();
                }
                addFragment(mTab2Fragment);
                showFragment(mTab2Fragment);
                break;
            case 2:
                if (mTab3Fragment == null) {
                    mTab3Fragment = new Tab3Fragment();
                }
                addFragment(mTab3Fragment);
                showFragment(mTab3Fragment);
                break;
            case 3:
                if (mTab4Fragment == null) {
                    mTab4Fragment = new MyFragment();
                }
                addFragment(mTab4Fragment);
                showFragment(mTab4Fragment);
                break;
        }
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (mTab1Fragment == null && fragment instanceof Tab1Fragment) {
            mTab1Fragment = (Tab1Fragment) fragment;
            mFragments.add(mTab1Fragment);
        } else if (mTab2Fragment == null && fragment instanceof Tab2Fragment) {
            mTab2Fragment = (Tab2Fragment) fragment;
            mFragments.add(mTab2Fragment);
        } else if (mTab3Fragment == null && fragment instanceof Tab3Fragment) {
            mTab3Fragment = (Tab3Fragment) fragment;
            mFragments.add(mTab3Fragment);
        } else if (mTab4Fragment == null && fragment instanceof MyFragment) {
            mTab4Fragment = (MyFragment) fragment;
            mFragments.add(mTab4Fragment);
        }
    }

    public static final String TAG = "dlTest";

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.e(TAG, "onSaveInstanceState: ");
        super.onSaveInstanceState(outState);
    }

}
