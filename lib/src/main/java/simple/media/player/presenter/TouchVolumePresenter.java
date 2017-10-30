package simple.media.player.presenter;

import android.content.Context;
import android.media.AudioManager;

import simple.media.player.helper.ViewTouchProgressHelper;
import simple.media.player.listener.OnTouchProgressChange;
import simple.media.player.model.TouchVolumeModel;
import simple.media.player.view.TouchVolumeView;

import static android.media.AudioManager.STREAM_MUSIC;

/**
 * 触摸的时候，展示的声音大小presenter
 * Created by rty on 30/10/2017.
 */

public class TouchVolumePresenter {
    private final TouchVolumeView view;
    private ViewTouchProgressHelper viewTouchProgressHelper;
    private final AudioManager audioManager;
    private float downVolumePc;
    private OnTouchProgressChange onTouchProgressChange = new OnTouchProgressChange() {
        @Override
        public void onProgressChange(float percent, boolean increase) {
            float result = downVolumePc + percent;
            view.show(result);
            //更新音量
            audioManager.setStreamVolume(STREAM_MUSIC,
                    (int) (result * audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)), 0);
        }

        @Override
        public void onTouchDown() {
            downVolumePc = audioManager.getStreamVolume(STREAM_MUSIC) * 1.0f
                    / audioManager.getStreamMaxVolume(STREAM_MUSIC);
        }

        @Override
        public void onTouchUp() {
            view.dismiss();
        }
    };


    public TouchVolumePresenter(Context context, TouchVolumeView view) {
        this.view = view;
        viewTouchProgressHelper = new ViewTouchProgressHelper(false, view);
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }


    public void bind(TouchVolumeModel model) {
        viewTouchProgressHelper.setOnTouchProgressChange(onTouchProgressChange);
    }

    public void release() {
        viewTouchProgressHelper.setOnTouchProgressChange(null);
    }

}
