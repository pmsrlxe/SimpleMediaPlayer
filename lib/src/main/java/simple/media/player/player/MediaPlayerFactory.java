package simple.media.player.player;

import android.content.Context;

import simple.media.player.player.exo.ExoMediaPlayerImpl;
import simple.media.player.player.sys.SysMediaPlayerImpl;
import simple.media.player.utils.Utils;

/**
 * SimpleMediaPlayer工厂
 */
public final class MediaPlayerFactory {

    private MediaPlayerFactory() {
    }

    public static SimpleMediaPlayer getMediaPlayer(Context context) {
        SimpleMediaPlayer player;
        if (Utils.isSupportExo()) {
            player = new ExoMediaPlayerImpl(context);
        } else {
            player = new SysMediaPlayerImpl(context);
        }
        player.initIfNeed();
        return player;
    }

}
