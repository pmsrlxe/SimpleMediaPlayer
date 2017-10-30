package simple.media.player.view;

/**
 * 触摸的时候，展示的音量view
 * Created by rty on 30/10/2017.
 */

public interface TouchVolumeView extends TouchableView {
    /**
     * @param percent 0f-1.0f
     */
    void show(float percent);

    void dismiss();
}
