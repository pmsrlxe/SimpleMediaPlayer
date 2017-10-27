package simple.media.player.data;

public enum MediaPlayerState {
    Init(false),          //最开始的状态
    Reset(false),         //刚初始化的
    Preparing(true),      //准备中
    Prepared(false),      //数据准备完毕了
    Playing(false),       //播放中
    Stopped(false),       //停止了
    Paused(false),        //暂停了
    Error(false),         //出错了
    Complete(false),      //播放完毕
    @Deprecated
    //TODO 注意：这个指的是：播放的时候缓冲中的状态，同时会卡住播放，和播放器状态串行的，不是预加载的缓冲。
            PlayBuffering(false),
    Seeking(true),        //seek中
    SeekComplete(true),   //seek结束
    Released(false);      //释放了

    //是否是临时状态，临时状态都是临时状态，不能表明现在MediaPlayer的最后状态
    //非临时状态表明了临时状态之后的播放器的行为

    //例子：
    //1、preparing这个是临时状态，如果再此之前的非临时状态是reset
    //那么临时状态消失之后，播放器的行为要成为不播放的（因为reset没有播放）

    //2、Seeking临时状态，如果之前是pause，那么seeking完毕，还是需要pause的（虽然目前播放器自动支持）

    private boolean tempState;

    MediaPlayerState(boolean tempState) {
        this.tempState = tempState;
    }

    public boolean isTempState() {
        return tempState;
    }


    public boolean hasDataState() {
        return this == Prepared || this == Paused
                || this == Playing || this == Complete
                || this == Seeking || this == SeekComplete;
    }
}
