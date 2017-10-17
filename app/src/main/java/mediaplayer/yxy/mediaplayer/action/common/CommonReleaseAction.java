package mediaplayer.yxy.mediaplayer.action.common;

import mediaplayer.yxy.mediaplayer.media.SimpleMediaPlayerImpl;
import mediaplayer.yxy.mediaplayer.action.BaseMediaPlayerAction;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerError;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerInfo;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public class CommonReleaseAction extends BaseMediaPlayerAction {

    public CommonReleaseAction(SimpleMediaPlayerImpl mediaPlayer, MediaPlayerState changeToState) {
        super(mediaPlayer, changeToState);
    }

    @Override
    public void onPrepared(SimpleMediaPlayerImpl simpleMediaPlayer) {

    }

    @Override
    public boolean onInfo(SimpleMediaPlayerImpl mediaPlayer, MediaPlayerInfo info) {
        return false;
    }

    @Override
    public boolean onError(SimpleMediaPlayerImpl mediaPlayer, MediaPlayerError error) {
        return false;
    }

    @Override
    public void onSeekComplete(SimpleMediaPlayerImpl mediaPlayer) {

    }

    @Override
    public void onBufferingUpdate(SimpleMediaPlayerImpl mediaPlayer, int percent) {

    }

    @Override
    public void onCompletion(SimpleMediaPlayerImpl mediaPlayer) {

    }

    @Override
    public void perform() {
        super.perform();
        submit(new Runnable() {
            @Override
            public void run() {
                try {
                    //release 这个方法有网络操作，至少华为p10有。
                    if (getRealMediaPlayer() != null) {
                        //如果从来没有reset或者操作过，可能是null
                        getRealMediaPlayer().release();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    post(new Runnable() {
                        @Override
                        public void run() {
                            getSimpleMediaPlayer().setMediaPlayerState(MediaPlayerState.Released);
                        }
                    });
                }
            }
        });
    }

    @Override
    public int getDuration() {
        return 0;
    }

    @Override
    public int getCurrentPosition() {
        return 0;
    }
}
