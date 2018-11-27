package cs4322.project.telemedicineapp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

import cs4322.project.telemedicineapp.model.Patient;
import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;
import io.agora.rtc.video.VideoCanvas;
import io.agora.rtc.video.VideoEncoderConfiguration;

public class VideoChatViewActivity extends AppCompatActivity {

    private static String roomNameTXT = "";
    private static final String LOG_TAG = VideoChatViewActivity.class.getSimpleName();

    private static final int PERMISSION_REQ_ID = 22;
    private static final String[] REQUESTED_PERMISSIONS = {Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA};

    private RtcEngine mRtcEngine;
    private final IRtcEngineEventHandler mRtcEventHandler = new IRtcEngineEventHandler() {
        @Override
        public void onFirstRemoteVideoDecoded(final int uid, int width, int height, int elapsed) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setupRemoteVideo(uid);
                }
            });
        }

        @Override
        public void onUserOffline(int uid, int reason) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    onRemoteUserLeft();
                }
            });
        }

        @Override
        public void onUserMuteVideo(final int uid, final boolean muted) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    onRemoteUserVideoMuted(uid, muted);
                    Log.i("OnUserMuteVideo", "User has muted their video");
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_chat_view);

        Intent homeIntent = getIntent();
        roomNameTXT = homeIntent.getStringExtra("RoomName");

        if (checkSelfPermission(REQUESTED_PERMISSIONS[0], PERMISSION_REQ_ID) &&
                checkSelfPermission(REQUESTED_PERMISSIONS[1], PERMISSION_REQ_ID) && roomNameTXT.length() > 0) {
            Log.i("IF", "I WIN");
            initAgoraEngineAndJoinChannel(roomNameTXT);
        }
    }

    private void initAgoraEngineAndJoinChannel(String roomName) {
        initializeAgoraEngine();
        setupVideoProfile();
        setupLocalVideo();
        System.out.println("ROOM NAME: " + roomName);
        if (roomName != null) {
            joinChannel(roomName);
        } else {
            System.out.println("SOMETHING WENT WRONG");
        }
    }

    public boolean checkSelfPermission(String permission, int requestCode) {
        Log.i(LOG_TAG, "checkSelfPermission " + permission + " " + requestCode);
        if (ContextCompat.checkSelfPermission(this,
                permission)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    REQUESTED_PERMISSIONS,
                    requestCode);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        Log.i(LOG_TAG, "onRequestPermissionsResult " + grantResults[0] + " " + requestCode);

        switch (requestCode) {
            case PERMISSION_REQ_ID: {
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED || grantResults[1] != PackageManager.PERMISSION_GRANTED) {
                    showLongToast("Need permissions " + Manifest.permission.RECORD_AUDIO + "/" + Manifest.permission.CAMERA);
                    finish();
                    break;
                }

                initAgoraEngineAndJoinChannel(roomNameTXT);
                break;
            }
        }
    }

    public final void showLongToast(final String msg) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        leaveChannel();
        RtcEngine.destroy();
        mRtcEngine = null;
    }

    public void onLocalVideoMuteClicked(View view) {
        Button muteLocalVideoBtn = (Button) view;
        if (muteLocalVideoBtn.isSelected()) {
            muteLocalVideoBtn.setSelected(false);
            Toast.makeText(getApplicationContext(), "Video has been un-muted.", Toast.LENGTH_SHORT).show();
        } else {
            muteLocalVideoBtn.setSelected(true);
            Toast.makeText(getApplicationContext(), "Video has been muted.", Toast.LENGTH_SHORT).show();
        }

        mRtcEngine.muteLocalVideoStream(muteLocalVideoBtn.isSelected());

        FrameLayout container = (FrameLayout) findViewById(R.id.local_video_view_container);
        SurfaceView surfaceView = (SurfaceView) container.getChildAt(0);
        surfaceView.setZOrderMediaOverlay(!muteLocalVideoBtn.isSelected());
        surfaceView.setVisibility(muteLocalVideoBtn.isSelected() ? View.GONE : View.VISIBLE);
    }

    public void onLocalAudioMuteClicked(View view) {
        Button muteLocalAudioBtn = (Button) view;
        if (muteLocalAudioBtn.isSelected()) {
            muteLocalAudioBtn.setSelected(false);
            Toast.makeText(getApplicationContext(), "Audio has been un-muted.", Toast.LENGTH_SHORT).show();
        } else {
            muteLocalAudioBtn.setSelected(true);
            Toast.makeText(getApplicationContext(), "Audio has been muted.", Toast.LENGTH_SHORT).show();
        }

        mRtcEngine.muteLocalAudioStream(muteLocalAudioBtn.isSelected());
    }

    public void onSwitchCameraClicked(View view) {
        mRtcEngine.switchCamera();
    }

    public void onEndCallClicked(View view) {
        finish();
    }

    private void initializeAgoraEngine() {
        try {
            mRtcEngine = RtcEngine.create(getBaseContext(), getString(R.string.private_app_id), mRtcEventHandler);
        } catch (Exception e) {
            Log.e(LOG_TAG, Log.getStackTraceString(e));

            throw new RuntimeException("NEED TO check rtc sdk init fatal error\n" + Log.getStackTraceString(e));
        }
    }

    private void setupVideoProfile() {
        mRtcEngine.enableVideo();

        mRtcEngine.setVideoEncoderConfiguration(new VideoEncoderConfiguration(VideoEncoderConfiguration.VD_640x360, VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_15,
                VideoEncoderConfiguration.STANDARD_BITRATE,
                VideoEncoderConfiguration.ORIENTATION_MODE.ORIENTATION_MODE_FIXED_PORTRAIT));
    }

    private void setupLocalVideo() {
        FrameLayout container = (FrameLayout) findViewById(R.id.local_video_view_container);
        SurfaceView surfaceView = RtcEngine.CreateRendererView(getBaseContext());
        surfaceView.setZOrderMediaOverlay(true);
        container.addView(surfaceView);
        mRtcEngine.setupLocalVideo(new VideoCanvas(surfaceView, VideoCanvas.RENDER_MODE_FIT, 0));
    }

    private void joinChannel(String roomName) {
        mRtcEngine.joinChannel(null, roomName, "This is the video channel for doctors and patients to communicate via 1to1 connection", 0);
    }

    private void setupRemoteVideo(int uid) {
        FrameLayout container = (FrameLayout) findViewById(R.id.remote_video_view_container);

        if (container.getChildCount() >= 1) {
            return;
        }

        SurfaceView surfaceView = RtcEngine.CreateRendererView(getBaseContext());
        container.addView(surfaceView);
        mRtcEngine.setupRemoteVideo(new VideoCanvas(surfaceView, VideoCanvas.RENDER_MODE_FIT, uid));

        surfaceView.setTag(uid);
    }

    private void leaveChannel() {
        mRtcEngine.leaveChannel();
    }

    private void onRemoteUserLeft() {
        FrameLayout container = (FrameLayout) findViewById(R.id.remote_video_view_container);
        container.removeAllViews();
    }

    private void onRemoteUserVideoMuted(int uid, boolean muted) {
        FrameLayout container = (FrameLayout) findViewById(R.id.remote_video_view_container);

        SurfaceView surfaceView = (SurfaceView) container.getChildAt(0);

        Object tag = surfaceView.getTag();
        if (tag != null && (Integer) tag == uid) {
            surfaceView.setVisibility(muted ? View.GONE : View.VISIBLE);
        }
    }
}