package mediaplayer.yxy.mediaplayer.data;

import android.content.Context;
import android.view.SurfaceView;

import java.util.Map;

public class MediaParams {
    private final Context context;
    private String url;
    private Map<String, String> mapHeadData;
    private SurfaceView surfaceView;
    private int seekToPercent;

    public MediaParams(Context context, String url, Map<String, String> mapHeadData, SurfaceView surfaceView) {
        this.url = url;
        this.context = context;
        this.mapHeadData = mapHeadData;
        this.surfaceView = surfaceView;
    }

    public MediaParams(MediaParams mediaParams) {
        this(mediaParams.context, mediaParams.url, mediaParams.mapHeadData, mediaParams.surfaceView);
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

    public void setSeekToPercent(int seekPercent) {
        this.seekToPercent = seekPercent;
    }

    public int getSeekToPercent() {
        return seekToPercent;
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
