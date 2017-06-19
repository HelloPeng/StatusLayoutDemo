package com.lvzp.statuslayoutdemo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lvzp.statuslayoutdemo.state.StateHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int argument = getArguments().getInt("argument");
        StateHelper helper = new StateHelper(this);
        if (argument == 1) {
            helper.showProgressView();
        } else if (argument == 2) {
            helper.showEmptyView();
        } else if (argument == 3) {
            helper.showErrorView();
        }

    }
}
