package com.example.myshake;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements SensorEventListener {
	
	
	private float mLastX, mLastY, mLastZ;
	private boolean mInitialized;
	private SensorManager mSensorManager; 
	private Sensor mAccelerometer;
	private final float NOISE = (float) 2.0;
	
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInitialized = false;
       
        setContentView(R.layout.activity_main);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onResume() {
    	super.onResume();
    	mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    	}
    
    protected void onPause() {
    	super.onPause();
    	mSensorManager.unregisterListener(this);
    	}
	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		//System.out.println("oo");
		 TextView tvx=(TextView) findViewById(R.id.x_axis);
		 TextView tvy=(TextView) findViewById(R.id.y_axis);
		 TextView tvz=(TextView) findViewById(R.id.z_axis);
		 
		 ImageView ivlr = (ImageView) findViewById(R.id.image_left_right);
		 ImageView ivtd = (ImageView) findViewById(R.id.image_top_down);

		
		 float az = event.values[0];
		 float pit = event.values[1];
		 float roll = event.values[2];
		 
		 tvx.setText(Float.toString(az));
		 tvy.setText(Float.toString(pit));
		 tvz.setText(Float.toString(roll));
		 
		 if(pit>0){
			 ivtd.setImageResource(R.drawable.up);
		 }
		 else if(pit<0){
			 ivtd.setImageResource(R.drawable.down);
		 }
		 if(roll>0){
			 ivlr.setImageResource(R.drawable.left);
		 }
		 else if(roll<0){
			 ivlr.setImageResource(R.drawable.right);
		 }

	}


	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
}
