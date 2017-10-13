package mediaplayer.yxy.mediaplayer.model;

import java.util.Map;

public class VideoPlayerModel {
    private String url;
    private Map<String, String> headData;

    public VideoPlayerModel(String url, Map<String, String> headData) {
        this.url = url;
        this.headData = headData;
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
