package mediaplayer.yxy.mediaplayer.data;

import mediaplayer.yxy.mediaplayer.listener.OnPlayingBufferListener;

public class MediaPlayerInfo {
    //MEDIA_PLAYER_STATE_ERROR        = 0,      0
    //MEDIA_PLAYER_IDLE               = 1 << 0, 1
    //MEDIA_PLAYER_INITIALIZED        = 1 << 1, 2
    //MEDIA_PLAYER_PREPARING          = 1 << 2, 4
    //MEDIA_PLAYER_PREPARED           = 1 << 3, 8
    //MEDIA_PLAYER_STARTED            = 1 << 4, 16
    //MEDIA_PLAYER_PAUSED             = 1 << 5, 32
    //MEDIA_PLAYER_STOPPED            = 1 << 6, 64
    //MEDIA_PLAYER_PLAYBACK_COMPLETE  = 1 << 7  128

 /* Do not change these values without updating their counterparts
     * in include/media/mediaplayer.h!
     */
    /**
     * Unspecified media player info.
     *
     * @see android.media.MediaPlayer.OnInfoListener
     */
    public static final int MEDIA_INFO_UNKNOWN = 1;

    /**
     * The player was started because it was used as the next player for another
     * player, which just completed playback.
     *
     * @hide
     * @see android.media.MediaPlayer.OnInfoListener
     */
    public static final int MEDIA_INFO_STARTED_AS_NEXT = 2;

    /**
     * The player just pushed the very first video frame for rendering.
     *
     * @see android.media.MediaPlayer.OnInfoListener
     */
    public static final int MEDIA_INFO_VIDEO_RENDERING_START = 3;

    /**
     * The video is too complex for the decoder: it can't decode frames fast
     * enough. Possibly only the audio plays fine at this stage.
     *
     * @see android.media.MediaPlayer.OnInfoListener
     */
    public static final int MEDIA_INFO_VIDEO_TRACK_LAGGING = 700;

    /**
     * MediaPlayer is temporarily pausing playback internally in order to
     * buffer more data.
     *
     * @see android.media.MediaPlayer.OnInfoListener
     */
    public static final int MEDIA_INFO_BUFFERING_START = 701;

    /**
     * MediaPlayer is resuming playback after filling buffers.
     *
     * @see android.media.MediaPlayer.OnInfoListener
     */
    public static final int MEDIA_INFO_BUFFERING_END = 702;

    /**
     * Estimated network bandwidth information (kbps) is available; currently this event fires
     * simultaneously as {@link #MEDIA_INFO_BUFFERING_START} and {@link #MEDIA_INFO_BUFFERING_END}
     * when playing network files.
     *
     * @hide
     * @see android.media.MediaPlayer.OnInfoListener
     */
    public static final int MEDIA_INFO_NETWORK_BANDWIDTH = 703;

    /**
     * Bad interleaving means that a media has been improperly interleaved or
     * not interleaved at all, e.g has all the video samples first then all the
     * audio ones. Video is playing but a lot of disk seeks may be happening.
     *
     * @see android.media.MediaPlayer.OnInfoListener
     */
    public static final int MEDIA_INFO_BAD_INTERLEAVING = 800;

    /**
     * The media cannot be seeked (e.g live stream)
     *
     * @see android.media.MediaPlayer.OnInfoListener
     */
    public static final int MEDIA_INFO_NOT_SEEKABLE = 801;

    /**
     * A new set of metadata is available.
     *
     * @see android.media.MediaPlayer.OnInfoListener
     */
    public static final int MEDIA_INFO_METADATA_UPDATE = 802;

    /**
     * A new set of external-only metadata is available.  Used by
     * JAVA framework to avoid triggering track scanning.
     *
     * @hide
     */
    public static final int MEDIA_INFO_EXTERNAL_METADATA_UPDATE = 803;

    /**
     * Failed to handle timed text track properly.
     *
     * @see android.media.MediaPlayer.OnInfoListener
     * <p>
     * {@hide}
     */
    public static final int MEDIA_INFO_TIMED_TEXT_ERROR = 900;

    /**
     * Subtitle track was not supported by the media framework.
     *
     * @see android.media.MediaPlayer.OnInfoListener
     */
    public static final int MEDIA_INFO_UNSUPPORTED_SUBTITLE = 901;

    /**
     * Reading the subtitle track takes too long.
     *
     * @see android.media.MediaPlayer.OnInfoListener
     */
    public static final int MEDIA_INFO_SUBTITLE_TIMED_OUT = 902;

    private int what;
    private int extra;

    public MediaPlayerInfo(int what, int extra) {
        this.what = what;
        this.extra = extra;
    }


    public void callback(OnPlayingBufferListener bufferStateListener) {
        if (bufferStateListener == null) {
            return;
        }
        if (what == MEDIA_INFO_BUFFERING_START) {
            bufferStateListener.onPauseForBuffer();
        }
        if (what == MEDIA_INFO_BUFFERING_END) {
            bufferStateListener.onPlayingFromPause();
        }
    }

    @Override
    public String toString() {
        return "what:" + what + ",extra:" + extra;
    }
}
