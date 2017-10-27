package simple.media.player.action.common;


import simple.media.player.action.BaseMediaPlayerAction;
import simple.media.player.data.MediaPlayerError;
import simple.media.player.data.MediaPlayerState;
import simple.media.player.data.sys.MediaPlayerInfo;
import simple.media.player.player.SimpleMediaPlayer;
import simple.media.player.player.sys.SysMediaPlayerImpl;

public class CommonReleaseAction extends BaseMediaPlayerAction {

    public CommonReleaseAction(SimpleMediaPlayer mediaPlayer, MediaPlayerState changeToState) {
        super(mediaPlayer, changeToState);
    }

    @Override
    public void onPrepared(SysMediaPlayerImpl simpleMediaPlayer) {

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
                            getSimpleMediaPlayer().setMediaPlayerStateFromAction(MediaPlayerState.Released);
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
