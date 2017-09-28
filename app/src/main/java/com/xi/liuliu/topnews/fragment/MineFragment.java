package com.xi.liuliu.topnews.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.activity.BrokeNewsActivity;
import com.xi.liuliu.topnews.activity.FavorHistoryActivity;
import com.xi.liuliu.topnews.activity.FeedbackActivity;
import com.xi.liuliu.topnews.activity.MainActivity;
import com.xi.liuliu.topnews.activity.SettingsActivity;
import com.xi.liuliu.topnews.activity.UserInfoActivity;
import com.xi.liuliu.topnews.bean.Address;
import com.xi.liuliu.topnews.constants.Constants;
import com.xi.liuliu.topnews.dialog.LoginDialog;
import com.xi.liuliu.topnews.event.InputContentEvent;
import com.xi.liuliu.topnews.event.LoginEvent;
import com.xi.liuliu.topnews.event.LogoutEvent;
import com.xi.liuliu.topnews.event.QQLoginEvent;
import com.xi.liuliu.topnews.event.WeiboLoginEvent;
import com.xi.liuliu.topnews.utils.SharedPrefUtil;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;

/**
 * Created by liuliu on 2017/6/19.
 */

public class MineFragment extends Fragment implements View.OnClickListener {
    private TextView mMyFavourite;
    private TextView mReadHistory;
    private RelativeLayout mFeedback;
    private LinearLayout mHeaderLogin;
    private RelativeLayout mPhoneLogin;
    private RelativeLayout mWeixinLogin;
    private RelativeLayout mQQLogin;
    private RelativeLayout mWeiboLogin;
    private TextView mMoreLoginWays;
    private RelativeLayout mHeaderUserinfo;
    private TextView mUserNickName;
    private ImageView mUserPortrait;
    private RelativeLayout mSettingsRlt;
    private RelativeLayout mBrokeNewsRlt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        mMyFavourite = (TextView) view.findViewById(R.id.mine_favorite);
        mMyFavourite.setOnClickListener(this);
        mReadHistory = (TextView) view.findViewById(R.id.mine_history);
        mReadHistory.setOnClickListener(this);
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
        mHeaderUserinfo.setOnClickListener(this);
        mUserNickName = (TextView) mHeaderUserinfo.findViewById(R.id.user_nick_name);
        mUserPortrait = (ImageView) mHeaderUserinfo.findViewById(R.id.head_portrait);
        mSettingsRlt = (RelativeLayout) view.findViewById(R.id.mine_app_settings);
        mSettingsRlt.setOnClickListener(this);
        mBrokeNewsRlt = (RelativeLayout) view.findViewById(R.id.mine_broke_news);
        mBrokeNewsRlt.setOnClickListener(this);
        checkLoginState();
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
            case R.id.mine_feedback:
                Intent feedbackIntent = new Intent(getActivity(), FeedbackActivity.class);
                startActivity(feedbackIntent);
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
                break;
            case R.id.mine_broke_news:
                Intent brokeNewsIntent = new Intent(getActivity(), BrokeNewsActivity.class);
                ArrayList<Address> addressList = ((MainActivity) getActivity()).getAddressList();
                brokeNewsIntent.putParcelableArrayListExtra("addressList", addressList);
                startActivity(brokeNewsIntent);
                break;
            case R.id.header_fragment_mine_login_weibo:
                weiboLogin();
                break;
            case R.id.header_fragment_mine_login_qq:
                qqLogin();
                break;
            case R.id.header_user_into_rtl:
                Intent intent = new Intent(getActivity(), UserInfoActivity.class);
                startActivity(intent);
                break;

        }
    }

    public void onEventMainThread(LogoutEvent event) {
        if (event != null) {
            mHeaderLogin.setVisibility(View.VISIBLE);
            mHeaderUserinfo.setVisibility(View.GONE);
        }
    }

    public void onEventMainThread(LoginEvent event) {
        if (event != null) {
            mHeaderLogin.setVisibility(View.GONE);
            mHeaderUserinfo.setVisibility(View.VISIBLE);
            //曾经设置过用户名，显示设置过的用户名；否则，显示微博昵称/手机号码
            String userName = SharedPrefUtil.getInstance(getActivity()).getString(Constants.USER_NAME_SP_KEY);
            if (!TextUtils.isEmpty(userName)) {
                mUserNickName.setText(userName);
            } else {
                mUserNickName.setText(event.getName());
            }
            //设置用户头像
            if (event.getLoginType() == LoginEvent.LOGIN_PHONE) {
                mUserPortrait.setImageResource(R.drawable.default_head_portrait);
            } else {
                Glide.with(getActivity()).load(event.getPortraitUrl()).transition(DrawableTransitionOptions.withCrossFade()).into(mUserPortrait);
            }
        }
    }


    /**
     * 更改用户名后，MineFragment顶部布局要显示最新的用户名
     *
     * @param event
     */
    public void onEventMainThread(InputContentEvent event) {
        if (event != null && event.getInputFrom() == InputContentEvent.INPUT_USER_NAME) {
            mUserNickName.setText(event.getInputContent());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    private void weiboLogin() {
        EventBus.getDefault().post(new WeiboLoginEvent());
    }

    private void qqLogin() {
        EventBus.getDefault().post(new QQLoginEvent());
    }

    private void checkLoginState() {
        if (SharedPrefUtil.getInstance(getActivity()).getBoolean(Constants.LOGIN_SP_KEY)) {
            mHeaderLogin.setVisibility(View.GONE);
            mHeaderUserinfo.setVisibility(View.VISIBLE);
            int loginType = SharedPrefUtil.getInstance(getActivity()).getInt(Constants.LOGIN_TYPE_SP_KEY);
            String userName = SharedPrefUtil.getInstance(getActivity()).getString(Constants.USER_NAME_SP_KEY);
            if (!TextUtils.isEmpty(userName)) {
                mUserNickName.setText(userName);
            }
            switch (loginType) {
                case LoginEvent.LOGIN_WEIBO:
                    String portraitUrl = SharedPrefUtil.getInstance(getActivity()).getString(Constants.WEI_BO_Portrait_URL);
                    Glide.with(getActivity()).load(portraitUrl).transition(DrawableTransitionOptions.withCrossFade()).into(mUserPortrait);
                    break;
                case LoginEvent.LOGIN_PHONE:
                    mUserPortrait.setImageResource(R.drawable.default_head_portrait);
                    break;
                case LoginEvent.LOGIN_QQ:
                    break;
                case LoginEvent.LOGIN_WEIXIN:
                    break;
            }
        }
    }
}
