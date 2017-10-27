package simple.media.player.data;


import android.content.Context;
import android.view.SurfaceView;

import java.util.Map;

public class MediaParams {
    private Context context;
    private String url;
    private Map<String, String> mapHeadData;
    private SurfaceView surfaceView;

    //0-100
    private int seekToPercent;//这2个互相冲突，设置了那么second就=0，无效了
    private int seekToMs;//这2个互相冲突，设置了那么percent就=0，无效了

    public MediaParams(Context context,
                       String url,
                       Map<String, String> mapHeadData,
                       SurfaceView surfaceView) {
        this.url = url;
        this.context = context;
        this.mapHeadData = mapHeadData;
        this.surfaceView = surfaceView;
    }

    public MediaParams(MediaParams mediaParams) {
        this(mediaParams.context,
                mediaParams.url,
                mediaParams.mapHeadData,
                mediaParams.surfaceView);
        this.seekToPercent = mediaParams.getSeekToPercent();
        this.seekToMs = mediaParams.getSeekToMs();
    }

    public Context getContext() {
        return context;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setMapHeadData(Map<String, String> mapHeadData) {
        this.mapHeadData = mapHeadData;
    }

    public void setSurfaceView(SurfaceView surfaceView) {
        this.surfaceView = surfaceView;
    }

    //0-100
    public void setSeekToPercent(int seekPercent) {
        this.seekToPercent = seekPercent;
        seekToMs = 0;
    }

    //0-100
    public int getSeekToPercent() {
        return seekToPercent;
    }

    public void setSeekToMs(int seekToMs) {
        this.seekToMs = seekToMs;
        seekToPercent = 0;
    }

    public int getSeekToMs() {
        return seekToMs;
    }

    public String getUrl() {
        return url;
    }

    public Map<String, String> getMapHeadData() {
        return mapHeadData;
    }

    public SurfaceView getSurfaceView() {
        return surfaceView;
    }
}
