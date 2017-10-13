package mediaplayer.yxy.mediaplayer.data;

import android.view.SurfaceView;

import java.util.Map;

public class MediaParams {
    private String url;
    private Map<String, String> mapHeadData;
    private SurfaceView surfaceView;

    public MediaParams(String url, Map<String, String> mapHeadData, SurfaceView surfaceView) {
        this.url = url;
        this.mapHeadData = mapHeadData;
        this.surfaceView = surfaceView;
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
