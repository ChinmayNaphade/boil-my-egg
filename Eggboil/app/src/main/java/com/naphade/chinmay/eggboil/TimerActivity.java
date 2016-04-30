package com.naphade.chinmay.eggboil;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.mikhaellopez.circularfillableloaders.CircularFillableLoaders;
import com.naphade.chinmay.eggboil.utils.MillisToTime;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TimerActivity extends AppCompatActivity {
    private static final String TAG = "TimerActivity";
    public static boolean active = false;

    @Inject
    BoilTimeCalculator calculator;

    long millis;

    @Bind(R.id.textswitcher)
    TextSwitcher textSwitcher;

    @Bind(R.id.circularFillableLoaders)
    CircularFillableLoaders circularFillableLoaders;

    CountDownTimer countDownTimer;
    boolean timerRunning = false;

    NotificationManager notificationManager;
    NotificationCompat.Builder mBuilder;
    NotificationCompat.Builder mBuilder1;


    @Bind(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        active = true;
        setContentView(R.layout.activity_timer);
        ButterKnife.bind(this);
        ((App) getApplication()).getAppComponent().inject(this);

        circularFillableLoaders.setAmplitudeRatio(0.08f);
        circularFillableLoaders.setBorderWidth(0f);

        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);

        textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {

            public View makeView() {
                TextView myText = new TextView(TimerActivity.this);
                myText.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
                myText.setTextSize(36);
                return myText;
            }
        });
        textSwitcher.setInAnimation(in);
        textSwitcher.setOutAnimation(out);

        // TODO: 29/04/16 revert back to original
        //millis = calculator.getMillis();
        millis = 5000;
        textSwitcher.setText(MillisToTime.getTime(millis));

        //Define Notification Manager
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        Intent intent = new Intent(this, TimerActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.food)
                .setContentTitle(getResources().getString(R.string.app_name))
                .setContentText("Timer running")
                .setPriority(0)
                .setOngoing(true)
                .setContentIntent(pIntent)
                .setColor(ContextCompat.getColor(this, R.color.colorPrimary));

        mBuilder1 = (NotificationCompat.Builder) new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.food)
                .setContentTitle(getResources().getString(R.string.app_name))
                .setContentText("Eggs are boiled")
                .setPriority(2)
                .setColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .setCategory(NotificationCompat.CATEGORY_ALARM)
                .setContentIntent(pIntent)
                .setSound(soundUri);

        countDownTimer = new CountDownTimer(millis, 1000) {

            public void onTick(long millisUntilFinished) {
                textSwitcher.setText(MillisToTime.getTime(millisUntilFinished));
                float progress = millisUntilFinished / (float) millis * 100;
                circularFillableLoaders.setProgress(100 - (int) progress);
            }

            public void onFinish() {
                textSwitcher.setText("00:00");
                notificationManager.cancel(0);
                if (active) {

                } else {
                    notificationManager.notify(1, mBuilder1.build());
                }
            }
        };


    }


    // TODO: 29/04/16 reset code for the progress bar

    @OnClick(R.id.button)
    public void startTimer(View view) {
        if (timerRunning) {
            textSwitcher.setText(MillisToTime.getTime(millis));
            countDownTimer.cancel();
            button.setText("Start Timer");
            notificationManager.cancel(0);
            timerRunning = false;
        } else {
            countDownTimer.start();
            button.setText("Reset Timer");
            timerRunning = true;
            notificationManager.notify(0, mBuilder.build());
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        active = false;
    }

}
