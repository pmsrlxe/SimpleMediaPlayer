package mediaplayer.yxy.mediaplayer.view;

import android.view.View;
import android.widget.TextView;

/**
 * 控制条，暂停，开始等操作按钮
 */
public interface ControllerView {

    View getControlPanel();

    View getSurfaceContainer();

    SimpleSeekBar getSeekBar();

    View getCenterPlayView();

    View getCenterPauseView();

    TextView getCurrentTimeTextView();

    TextView getDurationTimeTextView();

    View getControlPanelPlayView();

    View getControlPanelPauseView();
}
