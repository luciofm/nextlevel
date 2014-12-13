package com.luciofm.nextlevel.ifican.fragment;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luciofm.nextlevel.ifican.R;
import com.luciofm.nextlevel.ifican.anim.XFractionProperty;
import com.luciofm.nextlevel.ifican.anim.YFractionProperty;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class WhatFragment extends BaseFragment {

    @InjectView(R.id.text1)
    TextView text1;

    public WhatFragment() {
    }


    @Override
    public int getLayout() {
        return R.layout.fragment_what;
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup parent, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, parent, savedInstanceState);
        ButterKnife.inject(this, v);
        currentStep = 1;

        return v;
    }

    @Override
    public Animator onCreateAnimator(int transit, boolean enter, int nextAnim) {
        if (transit == 0) {
            return null;
        }

        //Target will be filled in by the framework
        return enter ? ObjectAnimator.ofFloat(null, new YFractionProperty(), 1f, -0f) :
                ObjectAnimator.ofFloat(null, new XFractionProperty(), 0f, -1f);
    }

    @OnClick(R.id.container)
    public void onClick() {
        onNextPressed();
    }
}
