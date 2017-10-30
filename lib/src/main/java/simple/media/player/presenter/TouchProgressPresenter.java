package simple.media.player.presenter;

import android.content.Context;

import simple.media.player.helper.ViewTouchProgressHelper;
import simple.media.player.listener.OnTouchProgressChange;
import simple.media.player.model.TouchProgressModel;
import simple.media.player.utils.Utils;
import simple.media.player.view.TouchProgressView;

/**
 * 触摸的时候，展示的进度条presenter
 * Created by rty on 30/10/2017.
 */

public class TouchProgressPresenter {
    private final TouchProgressView view;
    private final ViewTouchProgressHelper viewTouchProgressHelper;
    private long downPositionMs;
    private long resultCurrent;

    public TouchProgressPresenter(Context context, TouchProgressView view) {
        this.view = view;
        viewTouchProgressHelper = new ViewTouchProgressHelper(true, view);
    }

    public void bind(final TouchProgressModel model) {
        viewTouchProgressHelper.setOnTouchProgressChange(new OnTouchProgressChange() {
            @Override
            public void onProgressChange(float percent, boolean increase) {
                long total = model.getSimpleMediaPlayer().getRuntimeInfo().getDurationInMs();
                long gap = (long) (total * percent);
                resultCurrent = downPositionMs + gap;

                view.show(resultCurrent * 1.0f / total, increase ? 1 : -1,
                        Utils.stringForTime(resultCurrent), Utils.stringForTime(total));
            }

            @Override
            public void onTouchDown() {
                downPositionMs = model.getSimpleMediaPlayer().getRuntimeInfo()
                        .getCurrentPositionInMs();
            }

            @Override
            public void onTouchUp() {
                view.dismiss();
                long total = model.getSimpleMediaPlayer().getRuntimeInfo().getDurationInMs();
                //seekTo
                if (resultCurrent != downPositionMs) {
                    model.getSimpleMediaPlayer().seekToPercent((int) (resultCurrent * 1.0f / total * 100));
                }
            }
        });
    }


}
