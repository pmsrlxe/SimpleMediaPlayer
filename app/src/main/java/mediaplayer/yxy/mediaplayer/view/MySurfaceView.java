package mediaplayer.yxy.mediaplayer.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.widget.Toast;

/**
 *
 * Created by yxy on 17/5/15.
 */

public class MySurfaceView extends SurfaceView {
    public MySurfaceView(Context context) {
        super(context);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        try {
            super.onWindowVisibilityChanged(visibility);
        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(getContext(), "发生异常，请重试！", Toast.LENGTH_LONG).show();
        }
    }
}
