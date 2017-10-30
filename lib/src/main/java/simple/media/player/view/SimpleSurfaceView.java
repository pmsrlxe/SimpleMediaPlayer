package simple.media.player.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * 用来包裹下，以防自定义
 */
public class SimpleSurfaceView extends SurfaceView {
    public SimpleSurfaceView(Context context) {
        super(context);
    }

    public SimpleSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SimpleSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
