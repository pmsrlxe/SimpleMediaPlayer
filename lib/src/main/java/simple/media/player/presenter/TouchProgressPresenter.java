package simple.media.player.presenter;

import simple.media.player.model.TouchProgressModel;
import simple.media.player.view.TouchProgressView;

/**
 * 触摸的时候，展示的进度条presenter
 * Created by rty on 30/10/2017.
 */

public class TouchProgressPresenter {
    private final TouchProgressView view;

    public TouchProgressPresenter(TouchProgressView view) {
        this.view = view;
    }

    public void bind(TouchProgressModel model) {
    }


}
