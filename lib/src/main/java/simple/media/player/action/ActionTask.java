package simple.media.player.action;

import android.util.Log;

import java.util.concurrent.ArrayBlockingQueue;

import simple.media.player.action.common.CommonReleaseAction;
import simple.media.player.player.SimpleMediaPlayer;
import simple.media.player.utils.Task;

/**
 * 动作队列，让所有播放器的action
 * 顺序放起来
 * Created by rty on 31/10/2017.
 */

public class ActionTask {
    private boolean run = true;
    private final ArrayBlockingQueue<MediaPlayerAction> queue = new ArrayBlockingQueue<>(30);
    private MediaPlayerAction lastAction;
    private Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (run) {
                try {
                    final MediaPlayerAction currentAction = queue.take();
                    if (lastAction != null) {
                        final MediaPlayerAction temp = lastAction;
                        Task.post(new Runnable() {
                            @Override
                            public void run() {
                                temp.onRelease();
                            }
                        });
                    }
                    lastAction = currentAction;
                    currentAction.setActionListener(new ActionListener() {
                        @Override
                        public void onActionFinish() {
                            synchronized (currentAction) {
                                currentAction.notify();
                            }
                            Log.e(SimpleMediaPlayer.TAG, "TaskNotify:" + currentAction.getClass().getSimpleName());
                        }
                    });
                    Task.post(new Runnable() {
                        @Override
                        public void run() {
                            currentAction.perform();
                        }
                    });
                    Log.e(SimpleMediaPlayer.TAG, "TaskWait:" + currentAction.getClass().getSimpleName());
                    synchronized (currentAction) {
                        currentAction.wait();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    });

    public ActionTask() {
        thread.start();
    }

    public void enqueue(MediaPlayerAction action) {
        if (action == null) {
            return;
        }
        synchronized (queue) {
            queue.offer(action);
            Log.e(SimpleMediaPlayer.TAG, "TaskAddAction:" + action.getClass().getSimpleName());
        }
    }


    public void release() {
        run = false;
        thread.interrupt();
        //找到release action
        synchronized (queue) {
            for (MediaPlayerAction action : queue) {
                if (action instanceof CommonReleaseAction) {
                    action.perform();
                    Log.e(SimpleMediaPlayer.TAG, "TaskReleaseAction:" + action.getClass().getSimpleName());
                    break;
                }
            }
            queue.clear();
        }
    }


}
