package com.watchdroid;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Tronix on 26-03-2017.
 */

public class Background extends Service {
    private Timer mTimer;
    TextView textView;
    ImageView imageView;
    Bitmap mbitmap;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mTimer = new Timer();
        mTimer.schedule(timerTask, 2000, 2 * 1000);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            notifiy();
        }
    };

    @Override
    public void onDestroy() {
        try {
            mTimer.cancel();
            timerTask.cancel();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Intent intent = new Intent("com.watchdroid");
        intent.putExtra("try","try hard");
        sendBroadcast(intent);
    }

    public void notifiy(){

        IntentFilter intentfilter = new IntentFilter();
        intentfilter.addAction("Background");

        Intent myintent = new Intent(Intent.ACTION_VIEW, Uri.parse(""));
        PendingIntent pendingintent = PendingIntent.getActivity(getBaseContext(), 0, myintent, Intent.FLAG_ACTIVITY_NEW_TASK);
        Context context = getApplicationContext();

    }

    public void screenShot(View view) {
        mbitmap = getBitmapOFRootView(captureScreenShot);
        imageView.setImageBitmap(mbitmap);
        createImage(mbitmap);
    }

    public Bitmap getBitmapOFRootView(View v) {
        View rootview = v.getRootView();
        rootview.setDrawingCacheEnabled(true);
        Bitmap bitmap1 = rootview.getDrawingCache();
        return bitmap1;
    }

    public void createImage(Bitmap bmp) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 40, bytes);
        File file = new File(Environment.getExternalStorageDirectory() +
                "/capturedscreenandroid.jpg");
        try {
            file.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(bytes.toByteArray());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}