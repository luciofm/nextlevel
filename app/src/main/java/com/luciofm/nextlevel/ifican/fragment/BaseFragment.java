package com.luciofm.nextlevel.ifican.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luciofm.nextlevel.ifican.activity.MainActivity;

import butterknife.ButterKnife;

/**
 * Created by luciofm on 5/23/14.
 */
public abstract class BaseFragment extends Fragment {

    protected int currentStep = 1;

    public abstract int getLayout();

    public abstract String getMessage();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        currentStep = 1;
        return inflater.inflate(getLayout(), parent, false);
    }

    @Override
    public void onDestroyView() {
        ButterKnife.reset(this);
        super.onDestroyView();
    }

    public void onNextPressed() {
        ((MainActivity) getActivity()).nextFragment();
    }

    public void onPrevPressed() {
        getActivity().onBackPressed();
    }
}
