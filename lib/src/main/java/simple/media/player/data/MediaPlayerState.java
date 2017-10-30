package simple.media.player.data;

public enum MediaPlayerState {
    Init,          //最开始的状态
    Reset,         //刚初始化的
    Preparing,     //准备中
    Prepared,      //数据准备完毕了
    Playing,       //播放中
    Stopped,       //停止了
    Paused,        //暂停了
    Error,         //出错了
    Complete,      //播放完毕
    Seeking,       //seek中
    Released;      //释放了

    /**
     * 是否是有数据的状态
     * prepared之后，release之前的状态
     */
    public boolean isHasDataState() {
        return this == Prepared || this == Paused
                || this == Playing || this == Complete
                || this == Seeking;
    }
}
