package mediaplayer.yxy.mediaplayer.view;

import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * 控制条，暂停，开始等操作按钮
 */
public interface ControllerView {

    View getControlPanel();

    View getSurfaceContainer();

    SeekBar getSeekBar();

    View getCenterStartView();

    View getCenterPauseView();

    TextView getCurrentTimeTextView();

    TextView getDurationTimeTextView();

}
