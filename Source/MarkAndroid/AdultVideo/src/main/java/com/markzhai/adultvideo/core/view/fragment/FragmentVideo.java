package com.markzhai.adultvideo.core.view.fragment;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.markzhai.adultvideo.R;
import com.markzhai.adultvideo.core.App;
import com.markzhai.adultvideo.core.model.empflix.EmpflixVideoModel;
import com.markzhai.library.framework.BaseFragment;
import com.markzhai.library.utils.NLog;
import com.markzhai.library.utils.TimeUtils;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import roboguice.inject.InjectView;

/**
 * Created by marktlzhai on 2015/3/31.
 */
public class FragmentVideo extends BaseFragment implements SurfaceHolder.Callback {

    public static final String VIDEO_INFO = "VIDEO_INFO";

    @InjectView(R.id.video_view)
    private SurfaceView videoView;

    private MediaPlayer mediaPlayer;

    @InjectView(R.id.video_duration_seek)
    private SeekBar videoSeek;

    private EmpflixVideoModel videoData;

    private Timer updateSeekTimer;

    @InjectView(R.id.played_duration)
    private TextView playedDuration;
    @InjectView(R.id.total_duration)
    private TextView totalDuration;

    private long startLoadTime;

    private boolean isBufferedSuccess = false;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_video;
    }

    @Override
    public void init() {
        videoData = (EmpflixVideoModel) getArguments().getSerializable(VIDEO_INFO);

        videoSeek.setMax(videoData.getDurationMilliSecond());
        videoSeek.setProgress(0);
        playedDuration.setText("0:00");
        totalDuration.setText(videoData.videoDuration);
        videoSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                playedDuration.setText(TimeUtils.getMM_SS(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.seekTo(progress);
                }
            }
        });

        updateSeekTimer = new Timer();

        videoView.getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        showLoadingDialog(getString(R.string.buffering), false);
        play();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        NLog.e("surface destoryed.");
        stop();
    }

    private void play() {
        try {
            startLoadTime = System.currentTimeMillis();

            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(getBaseActivity(), Uri.parse(videoData.videoURL));
            mediaPlayer.setDisplay(videoView.getHolder());
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(MediaPlayer mp, int percent) {
                    int buffProgress = (int) Math.floor((double) percent / 100d * videoData.getDurationMilliSecond());
                    NLog.e("buffered :(" + buffProgress + "/" + videoData.getDurationMilliSecond() + ")");
                    videoSeek.setSecondaryProgress(buffProgress);
                }
            });
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    int loadCostTime = new Long(System.currentTimeMillis() - startLoadTime).intValue();

                    isBufferedSuccess = true;

                    NLog.e("media prepared.");
                    hideLoadingDialog();
                    mediaPlayer.start();
                    updateSeekTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                                NLog.e("played :(" + mediaPlayer.getCurrentPosition() + "/" + videoData.getDurationMilliSecond() + ")");
                                videoSeek.setProgress(mediaPlayer.getCurrentPosition());
                            }
                        }
                    }, 1000, 1000);
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    NLog.e("media completion.");
                }
            });
            mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    NLog.e("media error.");
                    return false;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (!isBufferedSuccess) {
        }
        isBufferedSuccess = false;

        if (updateSeekTimer != null) {
            updateSeekTimer.cancel();
            updateSeekTimer = null;
        }

        stop();
    }
}
