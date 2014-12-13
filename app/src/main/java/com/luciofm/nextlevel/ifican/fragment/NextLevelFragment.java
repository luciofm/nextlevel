package com.luciofm.nextlevel.ifican.fragment;



import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.luciofm.nextlevel.ifican.R;
import com.luciofm.nextlevel.ifican.activity.MainActivity;
import com.luciofm.nextlevel.ifican.anim.YFractionProperty;
import com.luciofm.nextlevel.ifican.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class NextLevelFragment extends BaseFragment {

    @InjectView(R.id.text2)
    TextView text2;
    @InjectView(R.id.container)
    LinearLayout container;

    int currentStep;

    public NextLevelFragment() {
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_nextlevel;
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, parent, savedInstanceState);
        ButterKnife.inject(this, v);
        currentStep = 1;

        Drawable background = getResources().getDrawable(R.drawable.running);
        //background.setTint(getResources().getColor(R.color.pink_light));
        ColorFilter filter = new LightingColorFilter(getResources().getColor(R.color.soft_pink), 0);
        background.setColorFilter(filter);
        background.setAlpha(60);
        container.setBackground(background);

        return v;
    }

    @Override
    public void onNextPressed() {
        // Simple animateLayoutChanges transition...
        if (++currentStep == 2)
            text2.setVisibility(View.VISIBLE);
        else {
            ((MainActivity) getActivity()).nextFragment();
        }
    }

    @OnClick(R.id.container)
    public void onClick() {
        onNextPressed();
    }

    @Override
    public Animator onCreateAnimator(int transit, boolean enter, int nextAnim) {
        if (transit == 0) {
            return null;
        }

        //Target will be filled in by the framework
        return enter ? ObjectAnimator.ofFloat(null, new YFractionProperty(), 1f, -0f) :
                ObjectAnimator.ofFloat(null, new YFractionProperty(), 0f, -1f);
    }
}
