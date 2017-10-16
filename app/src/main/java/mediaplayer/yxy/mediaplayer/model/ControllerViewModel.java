package mediaplayer.yxy.mediaplayer.model;

public class ControllerViewModel {
    private ToolBarVisibleModel toolBarVisibleModel;
    private ControllerDurationModel controllerDurationModel;

    public ControllerViewModel(ToolBarVisibleModel toolBarVisibleModel, ControllerDurationModel controllerDurationModel) {
        this.toolBarVisibleModel = toolBarVisibleModel;
        this.controllerDurationModel = controllerDurationModel;
    }

    public ControllerViewModel() {
    }

    public void setToolBarVisibleModel(ToolBarVisibleModel toolBarVisibleModel) {
        this.toolBarVisibleModel = toolBarVisibleModel;
    }

    public void setControllerDurationModel(ControllerDurationModel controllerDurationModel) {
        this.controllerDurationModel = controllerDurationModel;
    }

    public ToolBarVisibleModel getToolBarVisibleModel() {
        return toolBarVisibleModel;
    }

    public ControllerDurationModel getControllerDurationModel() {
        return controllerDurationModel;
    }
}
