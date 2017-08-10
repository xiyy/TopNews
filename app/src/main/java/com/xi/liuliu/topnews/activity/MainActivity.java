package com.xi.liuliu.topnews.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.constants.Constants;
import com.xi.liuliu.topnews.event.LiveFragmentVisibleEvent;
import com.xi.liuliu.topnews.event.LoginResultEvent;
import com.xi.liuliu.topnews.fragment.HomeFragment;
import com.xi.liuliu.topnews.fragment.LiveFragment;
import com.xi.liuliu.topnews.fragment.MineFragment;
import com.xi.liuliu.topnews.utils.SharedPrefUtil;

import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity {
    private static final int HOME_FRAGMENT = 0;
    private static final int LIVE_FRAGMENT = 1;
    private static final int MINE_FRAGMENT = 2;
    private HomeFragment mHomeFragment;
    private LiveFragment mLiveFrament;
    private MineFragment mMineFragment;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private RadioGroup mRadioGroup;
    private RadioButton mHomeRadioBtn;
    private RadioButton mLiveRadioBtn;
    private RadioButton mMineRadioBtn;
    private boolean isLoggedIn;
    private Toast mExitToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        setContentView(R.layout.activity_main);
        init();
    }


    private void init() {
        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group_main_activity);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_button_home_activity_main:
                        showFragment(HOME_FRAGMENT);
                        break;
                    case R.id.radio_button_live_activity_main:
                        showFragment(LIVE_FRAGMENT);
                        EventBus.getDefault().post(new LiveFragmentVisibleEvent(true));
                        break;
                    case R.id.radio_button_mine_activity_main:
                        showFragment(MINE_FRAGMENT);
                        break;
                }
            }
        });
        mHomeRadioBtn = (RadioButton) findViewById(R.id.radio_button_home_activity_main);
        mLiveRadioBtn = (RadioButton) findViewById(R.id.radio_button_live_activity_main);
        mMineRadioBtn = (RadioButton) findViewById(R.id.radio_button_mine_activity_main);
        isLoggedIn = SharedPrefUtil.getInstance(this).getBoolean(Constants.LOGIN_SP_KEY);
        if (isLoggedIn) {
            setLogin();
        }
        showFragment(HOME_FRAGMENT);
    }


    private void showFragment(int which) {
        mFragmentManager = getFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        hideFragment(mFragmentTransaction);
        switch (which) {
            case HOME_FRAGMENT:
                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                    mHomeFragment.setActivity(this);
                    mFragmentTransaction.add(R.id.flt_fragment, mHomeFragment);
                } else {
                    mFragmentTransaction.show(mHomeFragment);
                }
                break;
            case LIVE_FRAGMENT:
                if (mLiveFrament == null) {
                    mLiveFrament = new LiveFragment();
                    mFragmentTransaction.add(R.id.flt_fragment, mLiveFrament);
                } else {
                    mFragmentTransaction.show(mLiveFrament);
                }
                break;
            case MINE_FRAGMENT:
                if (mMineFragment == null) {
                    mMineFragment = new MineFragment();
                    mFragmentTransaction.add(R.id.flt_fragment, mMineFragment);
                } else {
                    mFragmentTransaction.show(mMineFragment);
                }
                break;
        }
        mFragmentTransaction.commit();
    }

    private void hideFragment(FragmentTransaction fragmentTransaction) {
        if (fragmentTransaction != null) {
            if (mHomeFragment != null) {
                fragmentTransaction.hide(mHomeFragment);
            }
            if (mLiveFrament != null) {
                fragmentTransaction.hide(mLiveFrament);
            }
            if (mMineFragment != null) {
                fragmentTransaction.hide(mMineFragment);
            }
        }
    }

    public void onEventMainThread(LoginResultEvent event) {
        isLoggedIn = event.getLoginResult();
        if (event != null) {
            if (isLoggedIn) {
                setLogin();
            } else {
                setLogout();
            }
        }
    }

    private void setLogin() {
        Drawable loginDrawable = getResources().getDrawable(R.drawable.selector_main_tab_item_login);
        loginDrawable.setBounds(0, 0, loginDrawable.getMinimumWidth(), loginDrawable.getMinimumHeight());
        mMineRadioBtn.setCompoundDrawables(null, loginDrawable, null, null);
        mMineRadioBtn.setText(R.string.mine_index);
        ColorStateList color = getResources().getColorStateList(R.color.main_tab_item_color);
        mMineRadioBtn.setTextColor(color);
    }

    private void setLogout() {
        Drawable notLoginDrawable = getResources().getDrawable(R.drawable.selector_main_tab_item_mine_not_login);
        notLoginDrawable.setBounds(0, 0, notLoginDrawable.getMinimumWidth(), notLoginDrawable.getMinimumHeight());
        mMineRadioBtn.setCompoundDrawables(null, notLoginDrawable, null, null);
        mMineRadioBtn.setText(R.string.mine_index_not_login);
        ColorStateList color = getResources().getColorStateList(R.color.main_tab_item_color);
        mMineRadioBtn.setTextColor(color);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mExitToast == null) {
                mExitToast = Toast.makeText(getApplicationContext(), R.string.main_activity_exit_toast, Toast.LENGTH_SHORT);
                mExitToast.getView().getBackground().setAlpha(200);//设置背景透明度
                mExitToast.setGravity(Gravity.CENTER, 0, 0);
                mExitToast.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mExitToast != null) {
                            mExitToast.cancel();
                        }
                        mExitToast = null;
                    }
                }, 2000);
                return true;
            } else {
                return super.onKeyDown(keyCode, event);
            }
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
}
