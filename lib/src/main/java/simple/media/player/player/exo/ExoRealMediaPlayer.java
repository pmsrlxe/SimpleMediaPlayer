package simple.media.player.player.exo;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.io.IOException;

import simple.media.player.data.MediaParams;
import simple.media.player.player.RealMediaPlayer;

/**
 * Exo real接口实例
 * Created by rty on 27/10/2017.
 */

public class ExoRealMediaPlayer extends SimpleExoPlayer implements RealMediaPlayer {
    private static final String TAG = "ExoRealMediaPlayer";
    private final Context context;

    protected ExoRealMediaPlayer(Context context, RenderersFactory renderersFactory,
                                 TrackSelector trackSelector,
                                 LoadControl loadControl) {
        super(renderersFactory, trackSelector, loadControl);
        this.context = context;
    }


    @Override
    public void doSeekTo(int msec) throws IllegalStateException {
        super.seekTo(msec);
        Log.d(TAG, "doSeekTo(" + msec + "):" + ExoRealMediaPlayer.this);
    }

    @Override
    public void doPrepare(MediaParams params) throws IOException, IllegalStateException {
        // Measures bandwidth during playback. Can be null if not required.
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
// Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context,
                Util.getUserAgent(context, "yourApplicationName"), bandwidthMeter);
// Produces Extractor instances for parsing the media data.
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
// This is the MediaSource representing the media to be played.
        MediaSource videoSource = new ExtractorMediaSource(Uri.parse(params.getUrl()),
                dataSourceFactory, extractorsFactory, null, null);
        super.prepare(videoSource);
        Log.d(TAG, "doPrepare:" + this);
    }

    @Override
    public void doStop() throws IllegalStateException {
        super.stop();
        Log.d(TAG, "doStop:" + this);
    }

    @Override
    public void doStart() throws IllegalStateException {
        Log.d(TAG, "doStart:" + this);
        super.setPlayWhenReady(true);
    }

    @Override
    public void doPause() throws IllegalStateException {
        super.stop();
        Log.d(TAG, "doPause:" + this);
    }

    @Override
    public long doGetCurrentPositionMs() throws Throwable {
        Log.d(TAG, "doGetCurrentPositionMs:" + this);
        return super.getCurrentPosition();
    }

    @Override
    public long doGetDurationMs() throws Throwable {
        Log.d(TAG, "doGetDurationMs:" + this);
        return super.getDuration();
    }

    @Override
    public void doRelease() {
        super.release();
        Log.d(TAG, "doRelease:" + this);
    }

    @Override
    public void doReset() throws Throwable {
        doRelease();

        Log.d(TAG, "doResetAndSetSource:" + this);
    }

}
