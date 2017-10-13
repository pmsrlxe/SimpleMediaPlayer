package mediaplayer.yxy.mediaplayer.action.common;

import mediaplayer.yxy.mediaplayer.SimpleMediaPlayer;
import mediaplayer.yxy.mediaplayer.action.BaseMediaPlayerAction;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerError;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerInfo;
import mediaplayer.yxy.mediaplayer.data.MediaPlayerState;

public class CommonReleaseAction extends BaseMediaPlayerAction {

    public CommonReleaseAction(SimpleMediaPlayer mediaPlayer, MediaPlayerState changeToState) {
        super(mediaPlayer, changeToState);
    }

    @Override
    public void onPrepared(SimpleMediaPlayer simpleMediaPlayer) {

    }

    @Override
    public boolean onInfo(SimpleMediaPlayer mediaPlayer, MediaPlayerInfo info) {
        return false;
    }

    @Override
    public boolean onError(SimpleMediaPlayer mediaPlayer, MediaPlayerError error) {
        return false;
    }

    @Override
    public void onSeekComplete(SimpleMediaPlayer mediaPlayer) {

    }

    @Override
    public void onBufferingUpdate(SimpleMediaPlayer mediaPlayer, int percent) {

    }

    @Override
    public void onCompletion(SimpleMediaPlayer mediaPlayer) {

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
}
