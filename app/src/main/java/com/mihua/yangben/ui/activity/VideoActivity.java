package com.mihua.yangben.ui.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.danikula.videocache.CacheListener;
import com.danikula.videocache.HttpProxyCacheServer;
import com.mihua.yangben.R;
import com.xiao.nicevideoplayer.NiceVideoPlayer;
import com.xiao.nicevideoplayer.NiceVideoPlayerManager;
import com.xiao.nicevideoplayer.TxVideoPlayerController;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.mihua.yangben.app.ManageApplication.getProxy;


public class VideoActivity extends AppCompatActivity {
    @BindView(R.id.back)
    LinearLayout back;
    private RelativeLayout rl_video_container;
    private VideoView videoview;
    private String mVideoUrl = "http://play.g3proxy.lecloud.com/vod/v2/MjUxLzE2LzgvbGV0di11dHMvMTQvdmVyXzAwXzIyLTExMDc2NDEzODctYXZjLTE5OTgxOS1hYWMtNDgwMDAtNTI2MTEwLTE3MDg3NjEzLWY1OGY2YzM1NjkwZTA2ZGFmYjg2MTVlYzc5MjEyZjU4LTE0OTg1NTc2ODY4MjMubXA0?b=259&mmsid=65565355&tm=1499247143&key=f0eadb4f30c404d49ff8ebad673d3742&platid=3&splatid=345&playid=0&tss=no&vtype=21&cvid=2026135183914&payff=0&pip=08cc52f8b09acd3eff8bf31688ddeced&format=0&sign=mb&dname=mobile&expect=1&tag=mobile&xformat=super";
    private TextView id_tv_title;
    private NiceVideoPlayer mNiceVideoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);
        init();

    }

    private void init() {
        String url = getIntent().getStringExtra("url");
        mVideoUrl = url;
        mNiceVideoPlayer = findViewById(R.id.nice_video_player);


    }


    @Override
    protected void onStart() {
        super.onStart();
        //视频缓存的设置
        HttpProxyCacheServer proxy = getProxy(this);
        String url = getIntent().getStringExtra("url");
        if (url == null) {
            return;
        }
        mVideoUrl = url;
        String proxyUrl = proxy.getProxyUrl(mVideoUrl);
        proxy.registerCacheListener(new Chache(), mVideoUrl);
        mNiceVideoPlayer.setPlayerType(NiceVideoPlayer.TYPE_IJK); // or NiceVideoPlayer.TYPE_NATIVE   NiceVideoPlayer.TYPE_IJK
        mNiceVideoPlayer.setUp(proxyUrl, null);
        TxVideoPlayerController controller = new TxVideoPlayerController(this);
        controller.setTitle("");

        controller.setImage(R.mipmap.ic_launcher);
        mNiceVideoPlayer.setController(controller);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 在 onStop 时释放掉播放器
        NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
        finish();
    }

    private HttpProxyCacheServer newProxy() {
        return new HttpProxyCacheServer.Builder(this)
                .maxCacheSize(1024 * 1024 * 1024)       // 设置缓存最大限制
                .maxCacheFilesCount(20)
                .build();
    }


    private class Chache implements CacheListener {
        @Override
        public void onCacheAvailable(File cacheFile, String url, int percentsAvailable) {
            Log.e("文件的缓存", percentsAvailable + "");
            if (percentsAvailable == 100) {
                Toast.makeText(VideoActivity.this, "视频缓存完成", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
