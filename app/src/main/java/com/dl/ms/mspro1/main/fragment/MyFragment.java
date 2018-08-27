package com.dl.ms.mspro1.main.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dl.ms.mspro1.R;


public class MyFragment extends Fragment implements View.OnClickListener{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bundle_mine_main_fragment_04,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.tv_collect).setOnClickListener(this);
        view.findViewById(R.id.tv_setting).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        /*int i = v.getId();
        if (i == R.id.tv_collect) {
            startActivity(
                    new Intent(getActivity(), MyCollectActivity.class));

        } else if (i == R.id.tv_setting) {
            startActivity(
                    new Intent(getActivity(), SettingActivity.class));

        }*/
    }

}
