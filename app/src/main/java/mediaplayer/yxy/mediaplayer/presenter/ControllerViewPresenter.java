package mediaplayer.yxy.mediaplayer.presenter;


import mediaplayer.yxy.mediaplayer.model.ControllerViewModel;
import mediaplayer.yxy.mediaplayer.view.ControllerView;

public class ControllerViewPresenter {
    private final ControllerView view;
    private ControllerDurationPresenter durationPresenter;
    private ControllerVisiblePresenter visiblePresenter;

    public ControllerViewPresenter(ControllerView view) {
        this.view = view;
        durationPresenter = new ControllerDurationPresenter(view);
        visiblePresenter = new ControllerVisiblePresenter(view);
    }

    public void bind(final ControllerViewModel model) {
        durationPresenter.bind(model.getControllerDurationModel());
        visiblePresenter.bind(model.getToolBarVisibleModel());
    }

    public void unbind() {
        durationPresenter.unbind();
        visiblePresenter.unbind();
    }
}
