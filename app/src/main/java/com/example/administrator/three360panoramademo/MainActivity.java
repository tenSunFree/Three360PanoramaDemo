package com.example.administrator.three360panoramademo;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.vr.sdk.widgets.common.VrWidgetView;
import com.google.vr.sdk.widgets.pano.VrPanoramaEventListener;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;

public class MainActivity extends AppCompatActivity {

    private VrPanoramaView mainVrPanoramaView;
    private VrPanoramaView.Options mainVrPanoramaViewOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initVrPaNormalView();
    }

    /**
     * 初始化VR图片
     */
    private void initVrPaNormalView() {
        mainVrPanoramaView = (VrPanoramaView) findViewById(R.id.mainVrPanoramaView);                 // 布局查找控件
        mainVrPanoramaViewOptions = new VrPanoramaView.Options();

        mainVrPanoramaViewOptions.inputType = VrPanoramaView.Options.TYPE_STEREO_OVER_UNDER;
        mainVrPanoramaView.setFullscreenButtonEnabled(false);                                       // 隐藏全屏模式按钮
        mainVrPanoramaView.setDisplayMode(VrWidgetView.DisplayMode.FULLSCREEN_MONO);             // 直接讓VrPanoramaView 全屏顯示, FULLSCREEN_MONO 全屏模式, FULLSCREEN_STEREO CardBoard 纸盒
        mainVrPanoramaView.setInfoButtonEnabled(false);                                             // 隱藏左下角的信息按钮
        mainVrPanoramaView.setStereoModeButtonEnabled(false);                                       // 隐藏立体模型的按钮
        mainVrPanoramaView.setEventListener(new ActivityEventListener()); //设置监听

        /** 加载本地的图片源 */
        mainVrPanoramaView.loadImageFromBitmap(
                BitmapFactory.decodeResource(getResources(), R.drawable.game), mainVrPanoramaViewOptions);
    }

    private class ActivityEventListener extends VrPanoramaEventListener {
        @Override
        public void onLoadSuccess() {                                                               // 全景圖加载成功
        }


        @Override
        public void onLoadError(String errorMessage) {                                              // 全景圖加载失败
        }

        @Override
        public void onClick() {                                                                     // 当我们点击了VrPanoramaView时候触发, super.onClick();
        }

        @Override
        public void onDisplayModeChanged(int newDisplayMode) {                                      // 改变显示模式时候出发, 全屏模式和纸板模式
            super.onDisplayModeChanged(newDisplayMode);
            finish();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        mainVrPanoramaView.pauseRendering();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mainVrPanoramaView != null) {
            mainVrPanoramaView.resumeRendering();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        /** 退出界面停止显示 */
        if (mainVrPanoramaView != null) {
            mainVrPanoramaView.shutdown();
        }
    }
}
