package ro.pub.cs.systems.eim.practicaltest01var04;

import java.util.Random;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01Var02PlayActivity extends Activity {

	private int GoodNumber=-1;
	private int RandNumber = -1;
	private int Score = 0;
	
	private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
	private class MessageBroadcastReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			RandNumber = intent.getIntExtra("message",-1);
			TextView number = (TextView)findViewById(R.id.textView3);
			number.setText(String.valueOf(RandNumber));
		}
	}
	private IntentFilter intentFilter = new IntentFilter();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practical_test01_var02_play);
		
		intentFilter.addAction("ro.pub.cs.systems.eim.action");
		
		if(savedInstanceState!=null){
			if(savedInstanceState.containsKey("RandNumber")){
				TextView randView = (TextView)findViewById(R.id.textView3);
				RandNumber = savedInstanceState.getInt("RandNumber");
				randView.setText(String.valueOf(savedInstanceState.getInt("RandNumber")));
			}
			if(savedInstanceState.containsKey("Score")){
				TextView randView = (TextView)findViewById(R.id.textView4);
				Score = savedInstanceState.getInt("Score");
				randView.setText(String.valueOf(savedInstanceState.getInt("Score")));
			}
		}
		
		Intent intent = getIntent();
		if(intent!=null){
			GoodNumber = intent.getIntExtra("number", -1);
		}
		
		Button generateBtn = (Button)findViewById(R.id.generate);
		if(generateBtn!=null){
			generateBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					TextView number = (TextView)findViewById(R.id.textView3);
					if(number==null) return;
					
					Random rand = new Random();
					int randomNum = rand.nextInt(10);
					RandNumber = randomNum;
					number.setText(String.valueOf(randomNum));
				}
			});
		}
		
		Button checkBTN = (Button)findViewById(R.id.check);
		if(checkBTN!=null){
			checkBTN.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(RandNumber == GoodNumber){
						TextView score = (TextView)findViewById(R.id.textView4);
						if(score== null)return;
						Score++;
						score.setText(String.valueOf(Score));
						
					}
					
				}
			});
		}
		
		Button backBTN = (Button)findViewById(R.id.back);
		if(backBTN!= null){
			backBTN.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					finish();
				}
			});
			
			
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practical_test01_var02_play, menu);
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
	
	@Override
	protected void onSaveInstanceState(Bundle bundle){
		bundle.putInt("RandNumber", RandNumber);
		bundle.putInt("Score", Score);
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle bundle){
		if(bundle.containsKey("RandNumber")){
			TextView randView = (TextView)findViewById(R.id.textView3);
			RandNumber = bundle.getInt("RandNumber");
			randView.setText(String.valueOf(bundle.getInt("RandNumber")));
		}
		if(bundle.containsKey("Score")){
			TextView randView = (TextView)findViewById(R.id.textView4);
			Score = bundle.getInt("Score");
			randView.setText(String.valueOf(bundle.getInt("Score")));
		}
	}
	
	@Override
    protected void onDestroy(){
    	Intent intent = new Intent(this, PracticalTest01Var04Service.class);
    	stopService(intent);
    	super.onDestroy();
    }
}
