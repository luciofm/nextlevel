package com.luciofm.nextlevel.ifican.fragment;



import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.luciofm.nextlevel.ifican.R;
import com.luciofm.nextlevel.ifican.anim.XFractionProperty;
import com.luciofm.nextlevel.ifican.util.IOUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * A simple {@link android.app.Fragment} subclass.
 *
 */
public class AnimationListCodeFragment extends BaseFragment {

    @InjectView(R.id.container2)
    View container;
    @InjectView(R.id.image)
    ImageView image;
    @InjectView(R.id.text1)
    TextView text1;
    @InjectView(R.id.text2)
    TextView text2;

    private int currentStep;

    public AnimationListCodeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, v);

        text2.setText(Html.fromHtml(IOUtils.readFile(getActivity(), "source/list.xml.html")));

        ((AnimationDrawable) image.getDrawable()).start();

        currentStep = 1;
        return v;
    }

    @Override
    public void onNextPressed() {
        switch (++currentStep) {
            case 2:
                container.setVisibility(View.VISIBLE);
                text1.animate().scaleX(0.6f).scaleY(0.6f);
                break;
            case 3:
                text2.setVisibility(View.VISIBLE);
                image.animate().scaleX(0.6f).scaleY(0.6f);
                break;
            default:
                super.onNextPressed();
        }
    }

    @OnClick(R.id.container)
    public void onClick() {
        onNextPressed();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_animation_list_code;
    }

    @Override
    public String getMessage() {
        return "";
    }

    @Override
    public Animator onCreateAnimator(int transit, boolean enter, int nextAnim) {
        if (transit == 0) {
            return null;
        }

        //Target will be filled in by the framework
        return enter ? ObjectAnimator.ofFloat(null, new XFractionProperty(), 1f, 0f)
                       : ObjectAnimator.ofFloat(null, new XFractionProperty(), 0f, -1f);
    }

}
