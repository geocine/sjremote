package com.geocine.sjremote;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.geocine.sjremote.http.SJRemoteRestClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;


public class MainActivity extends ActionBarActivity {

    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

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

    public void takePhoto(View view){
        SJRemoteRestClient.takePhoto(context);
    }

    public void switchPhotoMode(View view){
        SJRemoteRestClient.switchMode(context,SJRemoteRestClient.PHOTO_MODE);
    }

    public void switchVideoMode(View view){
        SJRemoteRestClient.switchMode(context,SJRemoteRestClient.VIDEO_MODE);
    }

    public void recordVideo(View view){
        SJRemoteRestClient.toggleRecord(context,true);
    }

    public void stopRecordVideo(View view){
        SJRemoteRestClient.toggleRecord(context,false);
    }
}
