package simple.media.player.view;

import android.view.View;
import android.widget.TextView;

import simple.media.player.view.impl.SimpleSeekBar;

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
