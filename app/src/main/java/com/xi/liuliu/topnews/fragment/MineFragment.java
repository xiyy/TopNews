package com.xi.liuliu.topnews.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.activity.FavorHistoryActivity;
import com.xi.liuliu.topnews.activity.FeedbackActivity;
import com.xi.liuliu.topnews.dialog.LoginDialog;

/**
 * Created by liuliu on 2017/6/19.
 */

public class MineFragment extends Fragment implements View.OnClickListener {
    private TextView mMyFavourite;
    private TextView mReadHistory;
    private RelativeLayout mFeedback;
    private LinearLayout mLoginLl;
    private RelativeLayout mPhoneLogin;
    private RelativeLayout mWeixinLogin;
    private RelativeLayout mQQLogin;
    private RelativeLayout mWeiboLogin;
    private TextView mMoreLoginWays;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine_layout, container, false);
        mMyFavourite = (TextView) view.findViewById(R.id.mine_favorite);
        mMyFavourite.setOnClickListener(this);
        mReadHistory = (TextView) view.findViewById(R.id.mine_history);
        mReadHistory.setOnClickListener(this);
        mFeedback = (RelativeLayout) view.findViewById(R.id.mine_feedback);
        mFeedback.setOnClickListener(this);
        mLoginLl = (LinearLayout) view.findViewById(R.id.header_rtl);
        mPhoneLogin = (RelativeLayout) mLoginLl.findViewById(R.id.header_fragment_mine_login_phone);
        mPhoneLogin.setOnClickListener(this);
        mWeixinLogin = (RelativeLayout) mLoginLl.findViewById(R.id.header_fragment_mine_login_weixin);
        mWeixinLogin.setOnClickListener(this);
        mQQLogin = (RelativeLayout) mLoginLl.findViewById(R.id.header_fragment_mine_login_qq);
        mQQLogin.setOnClickListener(this);
        mWeiboLogin = (RelativeLayout) mLoginLl.findViewById(R.id.header_fragment_mine_login_weibo);
        mWeiboLogin.setOnClickListener(this);
        mMoreLoginWays = (TextView) mLoginLl.findViewById(R.id.header_fragment_mine_login_more_ways);
        mMoreLoginWays.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_favorite:
                Intent favoriteIntent = new Intent(getActivity(), FavorHistoryActivity.class);
                favoriteIntent.putExtra("viewPager_current_item", 0);
                startActivity(favoriteIntent);
                break;
            case R.id.mine_history:
                Intent historyIntent = new Intent(getActivity(), FavorHistoryActivity.class);
                historyIntent.putExtra("viewPager_current_item", 1);
                startActivity(historyIntent);
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

        }
    }
}
