package mediaplayer.yxy.mediaplayer.listener;

public interface MediaDurationListener {
    void onUpdate(int currentMs, int durationMs, int percent);
}
