package com.lyun.lawyer.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.lyun.lawyer.BuildConfig;
import com.lyun.lawyer.model.TranslationOrderModel;

import java.util.Timer;
import java.util.TimerTask;

public class TranslationOrderService extends Service {

    //心跳包时间间隔 s
    public final int HEART_BEAT_INTERVAL = 60;

    private TranslationOrder mTranslationOrder;

    public TranslationOrderService() {
        mTimer = new Timer();
        mTimer.schedule(mOrderTimerTask, 1000, 1000);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String orderId = intent.getStringExtra(TranslationOrder.ORDER_ID);
        String userId = intent.getStringExtra(TranslationOrder.USER_ID);
        String translatorId = intent.getStringExtra(TranslationOrder.TRANSLATOR_ID);
        startNewOrder(new TranslationOrder(orderId, System.currentTimeMillis(), userId, translatorId));
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    protected void startNewOrder(TranslationOrder translationOrder) {

        mTranslationOrder = translationOrder;

        Intent intent = new Intent();
        //设置intent的动作为，可以任意定义
        intent.setAction(Action.START);
        intent.putExtra(TranslationOrder.ORDER_ID, mTranslationOrder.getOrderId());
        intent.putExtra(TranslationOrder.TRANSLATOR_ID, mTranslationOrder.getTranslatorId());
        intent.putExtra(TranslationOrder.USER_ID, mTranslationOrder.getUserId());
        //发送无序广播
        sendBroadcast(intent);

        heartBeat();
    }

    protected void heartBeat() {
        new TranslationOrderModel().heartBeat(mTranslationOrder.getOrderId())
                .subscribe(result -> {

                }, throwable -> {

                });
    }

    public Timer mTimer;
    public TimerTask mOrderTimerTask = new TimerTask() {
        @Override
        public void run() {
            mTranslationOrder.setServicedTime(System.currentTimeMillis() - mTranslationOrder.getStartTime());

            Intent intent = new Intent();
            //设置intent的动作为，可以任意定义
            intent.setAction(Action.STATUS_CHANGE);
            intent.putExtra(TranslationOrder.ORDER_ID, mTranslationOrder.getOrderId());
            intent.putExtra(TranslationOrder.SERVICED_TIME, mTranslationOrder.getServicedTime());
            //发送无序广播
            sendBroadcast(intent);

            if (((int) mTranslationOrder.getServicedTime() / 1000) % HEART_BEAT_INTERVAL == 0) {
                heartBeat();
            }
        }
    };

    public class Action {
        public static final String START = BuildConfig.APPLICATION_ID + ".translation.order.START";
        public static final String STATUS_CHANGE = BuildConfig.APPLICATION_ID + ".translation.order.STATUS_CHANGE";
        public static final String FINISH = BuildConfig.APPLICATION_ID + ".translation.order.FINISH";
    }

}
