package simple.media.player.view;

/**
 * 触摸的时候，展示的进度条view
 * Created by rty on 30/10/2017.
 */

public interface TouchProgressView extends TouchableView {
    /**
     * @param pc        0f-1.0f
     * @param direction -1,0,1 小于0快退，0无动作，大于0快进
     */
    void show(float pc, int direction, String timeCurrent, String timeTotal);

    void dismiss();
}
