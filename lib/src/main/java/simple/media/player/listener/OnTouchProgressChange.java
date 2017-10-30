package simple.media.player.listener;

/**
 * 触摸时候，根据view的宽高计算触摸滑动的百分比
 * Created by rty on 30/10/2017.
 */

public interface OnTouchProgressChange {
    /**
     * 目前触摸滑动的百分比
     *
     * @param percent  0f-0.1f
     * @param increase 是否增加方向
     *                 true增加方向，false减少方向（向右，或者向上为增加方向，否则就是减少方向）
     */
    void onProgressChange(float percent, boolean increase);

    /**
     * 手指刚触摸view
     */
    void onTouchDown();

    /**
     * 手指离开view
     */
    void onTouchUp();
}
