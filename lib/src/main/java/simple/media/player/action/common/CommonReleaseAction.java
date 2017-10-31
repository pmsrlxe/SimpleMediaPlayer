package simple.media.player.action.common;


import simple.media.player.action.BaseMediaPlayerAction;
import simple.media.player.data.MediaPlayerState;
import simple.media.player.player.RealMediaPlayer;
import simple.media.player.player.SimpleMediaPlayer;
import simple.media.player.utils.Task;

public class CommonReleaseAction extends BaseMediaPlayerAction {

    public CommonReleaseAction(SimpleMediaPlayer mediaPlayer, RealMediaPlayer realMediaPlayer, MediaPlayerState changeToState) {
        super(mediaPlayer, realMediaPlayer, changeToState);
    }


    @Override
    public void perform() {
        super.perform();
        Task.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    //release 这个方法有网络操作，至少华为p10有。
                    if (realMediaPlayer != null) {
                        //如果从来没有reset或者操作过，可能是null
                        realMediaPlayer.doRelease();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    Task.post(new Runnable() {
                        @Override
                        public void run() {
                            simpleMediaPlayer.setMediaPlayerStateFromAction(MediaPlayerState.Released);
                            notifyActionFinish();
                        }
                    });
                }
            }
        });
    }

}
