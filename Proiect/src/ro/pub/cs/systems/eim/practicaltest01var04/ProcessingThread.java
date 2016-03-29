package ro.pub.cs.systems.eim.practicaltest01var04;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ProcessingThread extends Thread{

	private int goodNumber;
	private Context context;
	private boolean isRunning;
	
	public ProcessingThread(Context context, int goodNumber){
		this.goodNumber = goodNumber;
		this.context = context;
	}
	
	@Override
	public void run(){
		while(isRunning){
			sendMessage();
			sleep();
		}
	}
	
	private void sendMessage(){
		Intent intent = new Intent();
		intent.setAction("ro.pub.cs.systems.eim.action");
		intent.putExtra("message", goodNumber);
		Log.d("OK", "a trimis");
		context.sendBroadcast(intent);
	}
	
	private void sleep(){
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void stopThread(){
		isRunning = false;
	}
	
}
