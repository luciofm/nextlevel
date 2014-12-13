package com.luciofm.nextlevel.ifican.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;

import com.luciofm.nextlevel.ifican.IfICan;
import com.luciofm.nextlevel.ifican.R;
import com.luciofm.nextlevel.ifican.fragment.ActivityLTransitionsCodeFragment;
import com.luciofm.nextlevel.ifican.fragment.ActivityTransitionsCodeFragment;
import com.luciofm.nextlevel.ifican.fragment.AnimationListCodeFragment;
import com.luciofm.nextlevel.ifican.fragment.BaseFragment;
import com.luciofm.nextlevel.ifican.fragment.CallAtentionFragment;
import com.luciofm.nextlevel.ifican.fragment.CodeFragment;
import com.luciofm.nextlevel.ifican.fragment.CustomTransitionsCodeFragment;
import com.luciofm.nextlevel.ifican.fragment.EverythingFragment;
import com.luciofm.nextlevel.ifican.fragment.FeedbackFragment;
import com.luciofm.nextlevel.ifican.fragment.GuideUserFragment;
import com.luciofm.nextlevel.ifican.fragment.HelloFragment;
import com.luciofm.nextlevel.ifican.fragment.NextLevelFragment;
import com.luciofm.nextlevel.ifican.fragment.LayoutTransitionsCodeFragment;
import com.luciofm.nextlevel.ifican.fragment.MinSdkFragment;
import com.luciofm.nextlevel.ifican.fragment.MorphingButtonCodeFragment;
import com.luciofm.nextlevel.ifican.fragment.MyselfFragment;
import com.luciofm.nextlevel.ifican.fragment.QuestionsFragment;
import com.luciofm.nextlevel.ifican.fragment.SmoothStateChangeFragment;
import com.luciofm.nextlevel.ifican.fragment.TheEndFragment;
import com.luciofm.nextlevel.ifican.fragment.TouchFeedbackCodeFragment;
import com.luciofm.nextlevel.ifican.fragment.WhatFragment;
import com.luciofm.nextlevel.ifican.fragment.WhatMakesSenseFragment;
import com.luciofm.nextlevel.ifican.fragment.WorkFragment;
import com.luciofm.nextlevel.ifican.util.MessageEvent;
import com.luciofm.nextlevel.ifican.util.RemoteEvent;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import butterknife.ButterKnife;


public class MainActivity extends BaseActivity {

    int index = 0;

    ArrayList<Class<? extends BaseFragment>> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        ButterKnife.inject(this);

        fragments.add(HelloFragment.class);
        fragments.add(MyselfFragment.class);
        fragments.add(WorkFragment.class);
        fragments.add(NextLevelFragment.class);
        fragments.add(WhatFragment.class);
        fragments.add(EverythingFragment.class);
        fragments.add(WhatMakesSenseFragment.class);
        fragments.add(SmoothStateChangeFragment.class);
        fragments.add(CallAtentionFragment.class);
        fragments.add(GuideUserFragment.class);
        fragments.add(FeedbackFragment.class);
        fragments.add(CodeFragment.class);

        fragments.add(AnimationListCodeFragment.class);
        fragments.add(LayoutTransitionsCodeFragment.class);
        fragments.add(TouchFeedbackCodeFragment.class);

        fragments.add(ActivityTransitionsCodeFragment.class);
        fragments.add(ActivityLTransitionsCodeFragment.class);

        fragments.add(MorphingButtonCodeFragment.class);
        fragments.add(CustomTransitionsCodeFragment.class);

        fragments.add(QuestionsFragment.class);

        fragments.add(TheEndFragment.class);
        fragments.add(MinSdkFragment.class);

        /*fragments.add(IfICanFragment.class);
        fragments.add(WhyFragment.class);
        fragments.add(SoftTransitionsFragment.class);
        fragments.add(TransitionsExampleFragment.class);
        fragments.add(ContextFragment.class);
        fragments.add(AtentionFragment.class);
        fragments.add(FeedbackFragment.class);
        fragments.add(CodeFragment.class);
        fragments.add(LayoutTransitionsCodeFragment.class);
        fragments.add(TouchFeedbackCodeFragment.class);
        fragments.add(RevealCodeFragment.class);
        fragments.add(AnimationListCodeFragment.class);
        fragments.add(ObjectAnimatorCodeFragment.class);
        fragments.add(ViewPropertyAnimatorCodeFragment.class);
        fragments.add(ActivityTransitionsCodeFragment.class);
        fragments.add(ActivityLTransitionsCodeFragment.class);
        fragments.add(MorphingButtonCodeFragment.class);
        fragments.add(QuestionsFragment.class);
        fragments.add(TheEndFragment.class);
        fragments.add(MinSdkFragment.class);*/

        if (savedInstanceState == null)
            nextFragment(false);

        getFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                index = getFragmentManager().getBackStackEntryCount() + 1;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        IfICan.getBusInstance().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        IfICan.getBusInstance().unregister(this);
    }

    public void nextFragment() {
        nextFragment(null);
    }

    public void nextFragment(boolean backstack) {
        nextFragment(backstack, null);
    }

    public void nextFragment(Bundle args) {
        nextFragment(true, args);
    }

    public void nextFragment(boolean backStack, Bundle args) {
        if (index == fragments.size())
            return;
        Class<? extends BaseFragment> clazz = fragments.get(index++);
        try {
            BaseFragment f = clazz.newInstance();
            if (args != null)
                f.setArguments(args);
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentContainer, f, "current")
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            if (backStack)
                ft.addToBackStack(null);
            ft.commit();

            String message = f.getMessage();
            if (message != null)
                IfICan.getBusInstance().post(new MessageEvent(message));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("INDEX", index);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        index = savedInstanceState.getInt("INDEX", 0);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d("IFICAN", "onKeyDown: " + keyCode + " - event: " + event.getScanCode());
        BaseFragment fragment = (BaseFragment) getFragmentManager().findFragmentByTag("current");
        int scanCode = event.getScanCode();
        switch (scanCode) {
            case IfICan.BUTTON_NEXT:
            case 28:
            case 229:
            case 0x74:
                fragment.onNextPressed();
                return true;
            case IfICan.BUTTON_PREV:
            case 0x79:
            case 57:
                fragment.onPrevPressed();
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Subscribe
    public void onRemoteEvent(RemoteEvent event) {
        BaseFragment fragment = (BaseFragment) getFragmentManager().findFragmentByTag("current");
        if (event.getType() == RemoteEvent.EVENT_BACK)
            fragment.onPrevPressed();
        else if (event.getType() == RemoteEvent.EVENT_ADVANCE)
            fragment.onNextPressed();
    }
}
