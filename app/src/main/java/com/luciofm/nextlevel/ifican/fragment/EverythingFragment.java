package com.luciofm.nextlevel.ifican.fragment;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luciofm.nextlevel.ifican.R;
import com.luciofm.nextlevel.ifican.activity.MainActivity;
import com.luciofm.nextlevel.ifican.anim.XFractionProperty;
import com.luciofm.nextlevel.ifican.anim.YFractionProperty;
import com.luciofm.nextlevel.ifican.util.Utils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import pl.droidsonroids.gif.GifImageView;

/**
 * A simple {@link android.app.Fragment} subclass.
 */
public class EverythingFragment extends BaseFragment {

    @InjectView(R.id.text1)
    TextView text1;
    @InjectView(R.id.text2)
    TextView text2;
    @InjectView(R.id.gif1)
    GifImageView gif1;

    public EverythingFragment() {
    }


    @Override
    public int getLayout() {
        return R.layout.fragment_everything;
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

        Utils.stopGif(gif1);
        Utils.resetGif(gif1);

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

    @Override
    public void onNextPressed() {
        switch (++currentStep) {
            case 2:
                text1.setVisibility(View.GONE);
                gif1.setVisibility(View.VISIBLE);
                Utils.startGifDelayed(gif1);
                break;
            case 3:
                Utils.stopGif(gif1);
                gif1.setVisibility(View.GONE);
                text2.setVisibility(View.VISIBLE);
                break;
            default:
                ((MainActivity) getActivity()).nextFragment();
        }
    }

    @OnClick(R.id.container)
    public void onClick() {
        onNextPressed();
    }
}
