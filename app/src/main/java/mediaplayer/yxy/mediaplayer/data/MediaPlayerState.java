package mediaplayer.yxy.mediaplayer.data;

public enum MediaPlayerState {
    Init, //最开始的状态
    Reset, //刚初始化的
    Preparing, //准备中
    Prepared, //数据准备完毕了
    Started,  //播放中
    Stopped, //停止了
    Paused, //暂停了
    Error, //出错了
    Complete, //播放完毕
    Buffering, //缓冲中
    Seeking, //seek中
    SeekComplete,    //seek结束
    Released  //释放了
}
