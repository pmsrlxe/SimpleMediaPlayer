package simple.media.player.listener;

import simple.media.player.data.MediaPlayerError;

/**
 * 出现错误了
 * Created by rty on 30/10/2017.
 */

public interface OnErrorListener {
    void onError(MediaPlayerError error);
}
