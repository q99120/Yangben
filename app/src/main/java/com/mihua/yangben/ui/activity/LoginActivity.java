package com.mihua.yangben.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.mihua.yangben.R;
import com.mihua.yangben.ui.dialog.LoadingDialog;
import com.mihua.yangben.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.login)
    Button login;
    @BindView(R.id.userName)
    EditText userName;
    @BindView(R.id.password)
    EditText password;
    private LoadingDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        ButterKnife.bind(this);

        initProject();
        SPUtils.putBoolean(this, "isDiag", false);
        password.setTransformationMethod(PasswordTransformationMethod.getInstance());


    }

    private void initProject() {
        SPUtils.putString(LoginActivity.this, "password", "12345");
        SPUtils.putString(LoginActivity.this, "userName", "test01");
        userName.setText("test01");
        password.setText("12345");

        String userInfo = "{\"msg\":\"success\",\"code\":0,\"user\":{\"userName\":\"test01\",\"name\":\"ll\",\"policeNo\":\"001\",\"duties\":\"测试\",\"deptName\":\"测试\"}}";
        SPUtils.putString(LoginActivity.this, "userInfo", userInfo);


        String videolist = "{\"msg\":\"success\",\"code\":0,\"videoList\":[[{\"videoId\":14,\"url\":\"/upload/video/1556156310304.mp4\",\"name\":\"测试视频\"},{\"videoId\":15,\"url\":\"/upload/video/1556250295649.mp4\",\"name\":\"test\"},{\"videoId\":16,\"url\":\"/upload/video/1556259474459.mp4\",\"name\":\"动漫测试视频\"}]]}";
        SPUtils.putString(LoginActivity.this, "videolist", videolist);


        Map<String, String> map = new HashMap<>();
        map.put("hcl", "452");
        map.put("fourfuran", "452");
        map.put("asas", "452");
        map.put("assa", "452");
        map.put("dsds", "452");
        map.put("asd", "452");
        map.put("zx", "452");
        map.put("asd", "452");
        Gson gson = new Gson();
        String abc = gson.toJson(map);
        Log.e("22222222222", "initProject: " + abc);

        String fourfuran = "{\"hcl\":\"452\",\"fywd\":\"20\",\"fysj\":\"20\",\"jbzs1\":\"23\",\"jbsj1\":\"22\",\"naoh\":\"54\",\"pbshcy\":\"554\",\"jbzs2\":\"22\",\"jbsj2\":\"22\",\"ch3oh\":\"45\",\"hhzy1\":\"22\",\"hhsj1\":\"22\",\"hhh2o\":\"54\",\"hhzy2\":\"22\",\"hhsj2\":\"22\",\"syyby\":\"22\",\"syzy\":\"22\",\"sysj\":\"22\",\"sycs\":\"22\",\"jhh2o\":\"45\",\"jhzy\":\"22\",\"jhsj\":\"22\",\"c4h8o2\":\"54\",\"xtzy\":\"22\",\"xtsj\":\"22\",\"nsyywd\":\"22\",\"nscqwd\":\"222\",\"nssj\":\"22\",\"type\":0}";
        SPUtils.putString(this, "fourfuran", fourfuran);
        String malachitegreen = "{\"c2h3n\":\"123\",\"jbzs\":\"45\",\"jbsj\":\"1\",\"hhc2h3n\":\"123\",\"hhzy\":\"23\",\"hhsj\":\"1\",\"syyby\":\"454\",\"syzy\":\"54\",\"sysj\":\"1\",\"sycs\":\"54\",\"nsfywd\":\"40\",\"nscqwd\":\"40\",\"nssj\":\"1\",\"ybrl\":\"22\",\"type\":1}";
        SPUtils.putString(this, "malachitegreen", malachitegreen);
        String leanmeat = "{\"ch3cooh\":\"123\",\"fywd\":\"25\",\"fysj\":\"34\",\"jbzs\":\"4\",\"jbsj\":\"76\",\"hhch3oh\":\"123\",\"hhzy1\":\"123\",\"hhsj1\":\"123\",\"hhh2o\":\"78\",\"hhzy2\":\"234\",\"hhsj2\":\"234\",\"hclo4\":\"234\",\"hhzy3\":\"234\",\"hhsj3\":\"234\",\"syyby\":\"234\",\"syzy\":\"234\",\"sysj\":\"234\",\"sycs\":\"23\",\"jhch3oh\":\"234\",\"jhzy1\":\"234\",\"jhsj1\":\"234\",\"ch3ohh2o\":\"234\",\"jhzy2\":\"234\",\"jhsj2\":\"52\",\"ahch3oh\":\"134\",\"xtzy\":\"234\",\"xtsj\":\"23\",\"type\":2}";
        SPUtils.putString(this, "leanmeat", leanmeat);
    }


    @OnClick(R.id.login)
    public void onViewClicked() {
        //本地登录不经过网络
        loginss();
    }

    private void loginss() {

        if (mDialog == null) {
            mDialog = new LoadingDialog(this);
        }
        mDialog.setTvTip("正在登录中");
        mDialog.show();

        mhand.sendEmptyMessageDelayed(1, 2000);

        //  }
    }

    private Handler mhand = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            startLogin();
        }
    };

    private void startLogin() {
        String passwords = SPUtils.getString(LoginActivity.this, "password", "");
        String userNames = SPUtils.getString(LoginActivity.this, "userName", "");
        if (!TextUtils.isEmpty(userNames)) {
            if (userNames.equals(userName.getText().toString().trim())) {

                if (passwords.equals(password.getText().toString().trim())) {
                    mDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    finish();

                } else {
                    mDialog.dismiss();
                    Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT).show();
                }

            } else {
                mDialog.dismiss();
                Toast.makeText(this, "用户名错误", Toast.LENGTH_SHORT).show();
            }

        } else {
            mDialog.dismiss();
            Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            // 点击EditText的事件，忽略它。
            return !(event.getX() > left) || !(event.getX() < right)
                    || !(event.getY() > top) || !(event.getY() < bottom);
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     *
     * @param token
     */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (im != null) {
                im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }
}
