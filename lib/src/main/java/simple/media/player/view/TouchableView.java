package simple.media.player.view;

import android.view.View;

/**
 * 可以设置TouchListener的view
 * Created by rty on 30/10/2017.
 */

public interface TouchableView {
    void setOnTouchListener(View.OnTouchListener onTouchListener);

    View.OnTouchListener getOnTouchListener();
}
