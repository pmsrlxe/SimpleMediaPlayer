package simple.media.player.view;

/**
 * 触摸的时候，展示的进度条view
 * Created by rty on 30/10/2017.
 */

public interface TouchProgressView extends TouchableView {
    /**
     * @param pc 0f-1.0f
     */
    void show(float pc, boolean increase, String timeCurrent, String timeTotal);

    void dismiss();
}
