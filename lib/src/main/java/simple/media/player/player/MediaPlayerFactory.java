package simple.media.player.player;

import simple.media.player.player.exo.ExoMediaPlayerImpl;
import simple.media.player.player.sys.SysMediaPlayerImpl;
import simple.media.player.utils.Utils;

/**
 * SimpleMediaPlayer工厂
 */
public final class MediaPlayerFactory {

    private MediaPlayerFactory() {
    }

    public static SimpleMediaPlayer getMediaPlayer() {
        if (Utils.isSupportExo()) {
            return new ExoMediaPlayerImpl();
        } else {
            return new SysMediaPlayerImpl();
        }
    }

}
