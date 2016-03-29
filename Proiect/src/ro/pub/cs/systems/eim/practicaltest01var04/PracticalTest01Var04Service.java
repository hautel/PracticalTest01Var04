package ro.pub.cs.systems.eim.practicaltest01var04;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class PracticalTest01Var04Service extends Service{

	private ProcessingThread myThread;
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId){
		Log.d("OK", "a intrat in serv");
		int goodNumber = intent.getIntExtra("GoodNumber", -1);
		
		if(goodNumber!=-1){
			myThread = new ProcessingThread(this, goodNumber);
			myThread.start();
			return 1;
		}
		return 1;
	}
	
	@Override
	public void onDestroy(){
		myThread.stopThread();
	}

}
