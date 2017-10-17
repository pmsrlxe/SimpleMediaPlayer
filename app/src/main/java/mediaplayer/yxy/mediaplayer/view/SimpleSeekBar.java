package mediaplayer.yxy.mediaplayer.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.List;

//支持增加多个listener
public class SimpleSeekBar extends android.support.v7.widget.AppCompatSeekBar {
    private List<OnSeekBarChangeListener> seekBarChangeListenerList = new ArrayList<>();

    public SimpleSeekBar(Context context) {
        super(context);
        init();
    }

    public SimpleSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SimpleSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        super.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                for (OnSeekBarChangeListener l : seekBarChangeListenerList) {
                    l.onProgressChanged(seekBar, progress, fromUser);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                for (OnSeekBarChangeListener l : seekBarChangeListenerList) {
                    l.onStartTrackingTouch(seekBar);
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                for (OnSeekBarChangeListener l : seekBarChangeListenerList) {
                    l.onStopTrackingTouch(seekBar);
                }
            }
        });
    }

    @Override
    public void setOnSeekBarChangeListener(OnSeekBarChangeListener l) {
        seekBarChangeListenerList.add(l);
    }

    public void addOnSeekBarChangeListener(OnSeekBarChangeListener l) {
        seekBarChangeListenerList.add(l);
    }

    public void removeOnSeekBarChangeListener(OnSeekBarChangeListener l) {
        seekBarChangeListenerList.remove(l);
    }
}
