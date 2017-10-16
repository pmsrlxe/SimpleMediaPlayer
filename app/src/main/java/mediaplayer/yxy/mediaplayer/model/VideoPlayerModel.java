package mediaplayer.yxy.mediaplayer.model;

import java.util.Map;

public class VideoPlayerModel {
    private String url;
    private Map<String, String> headData;
    private int seekToMs;

    public VideoPlayerModel(String url, Map<String, String> headData) {
        this.url = url;
        this.headData = headData;
    }

    public int getSeekToMs() {
        return seekToMs;
    }

    public void setSeekToMs(int seekToMs) {
        this.seekToMs = seekToMs;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setHeadData(Map<String, String> headData) {
        this.headData = headData;
    }

    public Map<String, String> getHeadData() {
        return headData;
    }

    public String getUrl() {
        return url;
    }
}
