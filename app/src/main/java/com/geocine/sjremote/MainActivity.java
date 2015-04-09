package com.geocine.sjremote;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.geocine.sjremote.http.SJRemoteRestClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;


public class MainActivity extends ActionBarActivity {

    Context context;
    Boolean isVideoMode = false;
    Boolean isRecording = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        final ImageView actionButton = (ImageView)findViewById(R.id.actionButton);

        switchVideoMode();
        isVideoMode = true;

        actionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                  if(isVideoMode){
                      if(isRecording){
                          stopRecordVideo();
                          isRecording = false;
                          actionButton.setImageResource(R.drawable.record_start);
                      } else {
                          recordVideo();
                          isRecording = true;
                          actionButton.setImageResource(R.drawable.record_stop);
                      }
                  } else {
                      takePhoto();
                  }
            }
        });

        actionButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(!isVideoMode) {
                        actionButton.setImageResource(R.drawable.capture_pressed);
                    }
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if(!isVideoMode) {
                        actionButton.setImageResource(R.drawable.capture);
                    }
                }
                return false;
            }
        });

        final ImageView photoModeButton = (ImageView)findViewById(R.id.photoModeButton);

        photoModeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switchPhotoMode();
                isVideoMode = false;
                actionButton.setImageResource(R.drawable.capture);
            }
        });

        photoModeButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    photoModeButton.setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_ATOP);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    photoModeButton.setColorFilter(Color.argb(0, 0, 0, 0));
                }
                return false;
            }
        });

        final ImageView videoModeButton = (ImageView)findViewById(R.id.videoModeButton);

        videoModeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switchVideoMode();
                isVideoMode = true;
                actionButton.setImageResource(R.drawable.record_start);
            }
        });

        videoModeButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    videoModeButton.setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_ATOP);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    videoModeButton.setColorFilter(Color.argb(0, 0, 0, 0));
                }
                return false;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void takePhoto(){
        SJRemoteRestClient.takePhoto(context);
    }

    public void switchPhotoMode(){
        SJRemoteRestClient.switchMode(context,SJRemoteRestClient.PHOTO_MODE);
    }

    public void switchVideoMode(){
        SJRemoteRestClient.switchMode(context,SJRemoteRestClient.VIDEO_MODE);
    }

    public void recordVideo(){
        SJRemoteRestClient.toggleRecord(context,true);
    }

    public void stopRecordVideo(){
        SJRemoteRestClient.toggleRecord(context,false);
    }
}
