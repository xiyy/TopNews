package com.xi.liuliu.topnews.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.activity.BrokeNewsActivity;
import com.xi.liuliu.topnews.activity.FavorHistoryActivity;
import com.xi.liuliu.topnews.activity.FeedbackActivity;
import com.xi.liuliu.topnews.activity.SettingsActivity;
import com.xi.liuliu.topnews.constants.Constants;
import com.xi.liuliu.topnews.dialog.LoginDialog;
import com.xi.liuliu.topnews.event.LoginResultEvent;
import com.xi.liuliu.topnews.utils.SharedPrefUtil;

import de.greenrobot.event.EventBus;

/**
 * Created by liuliu on 2017/6/19.
 */

public class MineFragment extends Fragment implements View.OnClickListener {
    private TextView mMyFavourite;
    private TextView mReadHistory;
    private TextView mReadMode;
    private RelativeLayout mFeedback;
    private LinearLayout mHeaderLogin;
    private RelativeLayout mPhoneLogin;
    private RelativeLayout mWeixinLogin;
    private RelativeLayout mQQLogin;
    private RelativeLayout mWeiboLogin;
    private TextView mMoreLoginWays;
    private RelativeLayout mHeaderUserinfo;
    private TextView mHeaderUserInfoPhone;
    private RelativeLayout mSettingsRlt;
    private RelativeLayout mBrokeNewsRlt;
    private boolean isDayReadMode = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine_layout, container, false);
        mMyFavourite = (TextView) view.findViewById(R.id.mine_favorite);
        mMyFavourite.setOnClickListener(this);
        mReadHistory = (TextView) view.findViewById(R.id.mine_history);
        mReadHistory.setOnClickListener(this);
        mReadMode = (TextView) view.findViewById(R.id.mine_night_mode);
        mReadMode.setOnClickListener(this);
        mFeedback = (RelativeLayout) view.findViewById(R.id.mine_feedback);
        mFeedback.setOnClickListener(this);
        mHeaderLogin = (LinearLayout) view.findViewById(R.id.header_login_rtl);
        mPhoneLogin = (RelativeLayout) mHeaderLogin.findViewById(R.id.header_fragment_mine_login_phone);
        mPhoneLogin.setOnClickListener(this);
        mWeixinLogin = (RelativeLayout) mHeaderLogin.findViewById(R.id.header_fragment_mine_login_weixin);
        mWeixinLogin.setOnClickListener(this);
        mQQLogin = (RelativeLayout) mHeaderLogin.findViewById(R.id.header_fragment_mine_login_qq);
        mQQLogin.setOnClickListener(this);
        mWeiboLogin = (RelativeLayout) mHeaderLogin.findViewById(R.id.header_fragment_mine_login_weibo);
        mWeiboLogin.setOnClickListener(this);
        mMoreLoginWays = (TextView) mHeaderLogin.findViewById(R.id.header_fragment_mine_login_more_ways);
        mMoreLoginWays.setOnClickListener(this);
        mHeaderUserinfo = (RelativeLayout) view.findViewById(R.id.header_user_into_rtl);
        mHeaderUserInfoPhone = (TextView) mHeaderUserinfo.findViewById(R.id.user_nick_name);
        mSettingsRlt = (RelativeLayout) view.findViewById(R.id.mine_app_settings);
        mSettingsRlt.setOnClickListener(this);
        mBrokeNewsRlt = (RelativeLayout) view.findViewById(R.id.mine_broke_news);
        mBrokeNewsRlt.setOnClickListener(this);
        if (SharedPrefUtil.getInstance(getActivity()).getBoolean(Constants.LOGIN_SP_KEY)) {
            String phoneNumber = SharedPrefUtil.getInstance(getActivity()).getString(Constants.USER_PHONE_NUMBER_SP_KEY);
            mHeaderLogin.setVisibility(View.GONE);
            mHeaderUserinfo.setVisibility(View.VISIBLE);
            mHeaderUserInfoPhone.setText("手机用户 " + phoneNumber);
        }
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_favorite:
                Intent favoriteIntent = new Intent(getActivity(), FavorHistoryActivity.class);
                favoriteIntent.putExtra("viewPager_current_item", 0);
                startActivity(favoriteIntent);
                getActivity().overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
                break;
            case R.id.mine_history:
                Intent historyIntent = new Intent(getActivity(), FavorHistoryActivity.class);
                historyIntent.putExtra("viewPager_current_item", 1);
                startActivity(historyIntent);
                getActivity().overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
                break;
            case R.id.mine_night_mode:
                changReadMode();
                break;
            case R.id.mine_feedback:
                Intent feedbackIntent = new Intent(getActivity(), FeedbackActivity.class);
                startActivity(feedbackIntent);
                getActivity().overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
                break;
            case R.id.header_fragment_mine_login_phone:
                new LoginDialog(v.getContext()).show();
                break;
            case R.id.header_fragment_mine_login_more_ways:
                new LoginDialog(v.getContext()).show();
                break;
            case R.id.mine_app_settings:
                Intent settingsIntent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(settingsIntent);
                getActivity().overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
                break;
            case R.id.mine_broke_news:
                Intent brokeNewsIntent = new Intent(getActivity(), BrokeNewsActivity.class);
                startActivity(brokeNewsIntent);
                getActivity().overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
                break;

        }
    }

    private void changReadMode() {
        if (isDayReadMode) {
            mReadMode.setText(R.string.mine_day_mode);
            Drawable day = getResources().getDrawable(R.drawable.mine_day_mode);
            day.setBounds(0, 0, day.getMinimumWidth(), day.getMinimumHeight());
            mReadMode.setCompoundDrawables(null, day, null, null);
            isDayReadMode = false;
        } else {
            mReadMode.setText(R.string.mine_night_mode);
            Drawable night = getResources().getDrawable(R.drawable.mine_night_mode);
            night.setBounds(0, 0, night.getMinimumWidth(), night.getMinimumHeight());
            mReadMode.setCompoundDrawables(null, night, null, null);
            isDayReadMode = true;
        }
    }

    public void onEventMainThread(LoginResultEvent event) {
        if (event != null) {
            if (event.getLoginResult()) {
                String phoneNumber = SharedPrefUtil.getInstance(getActivity()).getString(Constants.USER_PHONE_NUMBER_SP_KEY);
                mHeaderLogin.setVisibility(View.GONE);
                mHeaderUserinfo.setVisibility(View.VISIBLE);
                mHeaderUserInfoPhone.setText("手机用户 " + phoneNumber);
            } else {
                mHeaderLogin.setVisibility(View.VISIBLE);
                mHeaderUserinfo.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
