package com.xi.liuliu.topnews.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.constants.Constants;
import com.xi.liuliu.topnews.event.HomeFragmentVisibleEvent;
import com.xi.liuliu.topnews.event.LiveFragmentVisibleEvent;
import com.xi.liuliu.topnews.event.LoginResultEvent;
import com.xi.liuliu.topnews.event.MineFragmentVisibleEvent;
import com.xi.liuliu.topnews.fragment.HomeFragment;
import com.xi.liuliu.topnews.fragment.LiveFragment;
import com.xi.liuliu.topnews.fragment.MineFragment;
import com.xi.liuliu.topnews.utils.SharedPrefUtil;

import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mMineTextView;
    private TextView mHomeTextView;
    private TextView mLiveTextView;
    private HomeFragment mHomeFragment;
    private MineFragment mMineFragment;
    private LiveFragment mLiveFrament;
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
                if (mLiveFrament != null) {
                    transaction.hide(mLiveFrament);
                    EventBus.getDefault().post(new LiveFragmentVisibleEvent(false));
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
                if (mLiveFrament != null) {
                    transaction.hide(mLiveFrament);
                    EventBus.getDefault().post(new LiveFragmentVisibleEvent(false));
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
            case R.id.live_textView:
                if (mHomeFragment != null) {
                    transaction.hide(mHomeFragment);
                    EventBus.getDefault().post(new HomeFragmentVisibleEvent(false));
                }
                if (mMineFragment != null) {
                    transaction.hide(mMineFragment);
                    EventBus.getDefault().post(new MineFragmentVisibleEvent(false));
                }
                if (mLiveFrament == null) {
                    mLiveFrament = new LiveFragment();
                    transaction.add(R.id.flt_fragment, mLiveFrament);
                    EventBus.getDefault().post(new LiveFragmentVisibleEvent(true));
                } else {
                    transaction.show(mLiveFrament);
                    EventBus.getDefault().post(new LiveFragmentVisibleEvent(true));
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
        mLiveTextView = (TextView) findViewById(R.id.live_textView);
        mHomeTextView.setOnClickListener(this);
        mMineTextView.setOnClickListener(this);
        mLiveTextView.setOnClickListener(this);
        isLoggedIn = SharedPrefUtil.getInstance(this).getBoolean(Constants.LOGIN_SP_KEY);
        if (isLoggedIn) {
            Drawable country = getResources().getDrawable(R.drawable.mine_index_icon);
            country.setBounds(0, 0, country.getMinimumWidth(), country.getMinimumHeight());
            mMineTextView.setCompoundDrawables(null, country, null, null);
            mMineTextView.setText(R.string.mine_index);
        }

    }

    private void setHomeIcon(boolean isSelected) {
        if (isSelected) {
            mHomeTextView.setTextColor(getResources().getColor(R.color.red_light));
            Drawable country = getResources().getDrawable(R.drawable.home_index_selected_icon);
            country.setBounds(0, 0, country.getMinimumWidth(), country.getMinimumHeight());
            mHomeTextView.setCompoundDrawables(null, country, null, null);
        } else {
            mHomeTextView.setTextColor(getResources().getColor(R.color.darker_gray));
            Drawable country = getResources().getDrawable(R.drawable.home_index_icon);
            country.setBounds(0, 0, country.getMinimumWidth(), country.getMinimumHeight());
            mHomeTextView.setCompoundDrawables(null, country, null, null);
        }
    }

    private void setMineIcon(boolean isSelected, boolean loggedIn) {
        if (loggedIn) {
            mMineTextView.setText(R.string.mine_index);
            if (isSelected) {
                mMineTextView.setTextColor(getResources().getColor(R.color.red_light));
                Drawable country = getResources().getDrawable(R.drawable.mine_index_selected_icon);
                country.setBounds(0, 0, country.getMinimumWidth(), country.getMinimumHeight());
                mMineTextView.setCompoundDrawables(null, country, null, null);
            } else {
                mMineTextView.setTextColor(getResources().getColor(R.color.darker_gray));
                Drawable country = getResources().getDrawable(R.drawable.mine_index_icon);
                country.setBounds(0, 0, country.getMinimumWidth(), country.getMinimumHeight());
                mMineTextView.setCompoundDrawables(null, country, null, null);
            }
        } else {
            mMineTextView.setText(R.string.mine_index_not_login);
            if (isSelected) {
                mMineTextView.setTextColor(getResources().getColor(R.color.red_light));
                Drawable country = getResources().getDrawable(R.drawable.mine_index_not_login_selected_icon);
                country.setBounds(0, 0, country.getMinimumWidth(), country.getMinimumHeight());
                mMineTextView.setCompoundDrawables(null, country, null, null);
            } else {
                mMineTextView.setTextColor(getResources().getColor(R.color.darker_gray));
                Drawable country = getResources().getDrawable(R.drawable.mine_index_not_login_icon);
                country.setBounds(0, 0, country.getMinimumWidth(), country.getMinimumHeight());
                mMineTextView.setCompoundDrawables(null, country, null, null);
            }
        }
    }

    private void setLiveIcon(boolean isSelected) {
        if (isSelected) {
            mLiveTextView.setTextColor(getResources().getColor(R.color.red_light));
            Drawable country = getResources().getDrawable(R.drawable.live_index_selected_icon);
            country.setBounds(0, 0, country.getMinimumWidth(), country.getMinimumHeight());
            mLiveTextView.setCompoundDrawables(null, country, null, null);
        } else {
            mLiveTextView.setTextColor(getResources().getColor(R.color.darker_gray));
            Drawable country = getResources().getDrawable(R.drawable.live_index_un_selected_icon);
            country.setBounds(0, 0, country.getMinimumWidth(), country.getMinimumHeight());
            mLiveTextView.setCompoundDrawables(null, country, null, null);
        }
    }

    public void onEventMainThread(HomeFragmentVisibleEvent event) {
        if (event != null) {
            setHomeIcon(event.getFragmentVisibility());
        }
    }

    public void onEventMainThread(MineFragmentVisibleEvent event) {
        if (event != null) {
            setMineIcon(event.getFragmentVisibility(), isLoggedIn);
        }
    }

    public void onEventMainThread(LiveFragmentVisibleEvent event) {
        if (event != null) {
            setLiveIcon(event.getFragmentVisibility());
        }
    }

    public void onEventMainThread(LoginResultEvent event) {
        isLoggedIn = event.getLoginResult();
        if (event != null) {
            setMineIcon(true, event.getLoginResult());
        }
    }
}
