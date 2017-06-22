package com.xi.liuliu.topnews.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.event.HomeFragmentVisibleEvent;
import com.xi.liuliu.topnews.event.MineFragmentVisibleEvent;
import com.xi.liuliu.topnews.fragment.HomeFragment;
import com.xi.liuliu.topnews.fragment.MineFragment;

import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mMineTextView;
    private TextView mHomeTextView;
    private HomeFragment mHomeFragment;
    private MineFragment mMineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        setContentView(R.layout.activity_main);
        init();
        setDefaultFragment();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onClick(View v) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        switch (v.getId()) {
            case R.id.home_textView:
                if (mMineFragment != null) {
                    transaction.hide(mMineFragment);
                    EventBus.getDefault().post(new MineFragmentVisibleEvent(false));
                }
                if (mHomeFragment != null) {
                    transaction.show(mHomeFragment);
                    EventBus.getDefault().post(new HomeFragmentVisibleEvent(true));
                }
                break;
            case R.id.mine_textView:
                if (mHomeFragment != null) {
                    transaction.hide(mHomeFragment);
                    EventBus.getDefault().post(new HomeFragmentVisibleEvent(false));
                }
                if (mMineFragment == null) {
                    mMineFragment = new MineFragment();
                    transaction.add(R.id.flt_fragment, mMineFragment);
                    EventBus.getDefault().post(new MineFragmentVisibleEvent(true));
                } else {
                    transaction.show(mMineFragment);
                    EventBus.getDefault().post(new MineFragmentVisibleEvent(true));
                }
                break;
            default:
        }
        transaction.commit();

    }

    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mHomeFragment = new HomeFragment();
        mHomeFragment.setActivity(this);
        transaction.add(R.id.flt_fragment, mHomeFragment);
        transaction.commit();
        EventBus.getDefault().post(new HomeFragmentVisibleEvent(true));
    }

    private void init() {
        mMineTextView = (TextView) findViewById(R.id.mine_textView);
        mHomeTextView = (TextView) findViewById(R.id.home_textView);
        mHomeTextView.setOnClickListener(this);
        mMineTextView.setOnClickListener(this);
    }

    private void setIconState(TextView textView, boolean isSelected) {
        //设置字体
        if (isSelected) {
            textView.setTextColor(getResources().getColor(R.color.red_light));
        } else {
            textView.setTextColor(getResources().getColor(R.color.darker_gray));
        }
        //设置图片
        if (textView.getId() == R.id.home_textView) {
            if (isSelected) {
                Drawable country = getResources().getDrawable(R.drawable.home_index_selected_icon);
                country.setBounds(0, 0, country.getMinimumWidth(), country.getMinimumHeight());
                mHomeTextView.setCompoundDrawables(null, country, null, null);
            } else {
                Drawable country = getResources().getDrawable(R.drawable.home_index_icon);
                country.setBounds(0, 0, country.getMinimumWidth(), country.getMinimumHeight());
                mHomeTextView.setCompoundDrawables(null, country, null, null);
            }
        }
        if (textView.getId() == R.id.mine_textView) {
            if (isSelected) {
                Drawable country = getResources().getDrawable(R.drawable.mine_index_selected_icon);
                country.setBounds(0, 0, country.getMinimumWidth(), country.getMinimumHeight());
                mMineTextView.setCompoundDrawables(null, country, null, null);
            } else {
                Drawable country = getResources().getDrawable(R.drawable.mine_index_icon);
                country.setBounds(0, 0, country.getMinimumWidth(), country.getMinimumHeight());
                mMineTextView.setCompoundDrawables(null, country, null, null);
            }
        }
    }

    public void onEventMainThread(HomeFragmentVisibleEvent event) {
        if (event != null) {
            if (event.getFragmentVisibility()) {
                setIconState(mHomeTextView, true);
            } else {
                setIconState(mHomeTextView, false);
            }
        }
    }

    public void onEventMainThread(MineFragmentVisibleEvent event) {
        if (event != null) {
            if (event.getFragmentVisibility()) {
                setIconState(mMineTextView, true);
            } else {
                setIconState(mMineTextView, false);
            }
        }
    }

}
