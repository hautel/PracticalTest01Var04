package ro.pub.cs.systems.eim.practicaltest01var04;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class PracticalTest01Var03ChooseNumber extends Activity {

	private final static int SecondaryActivityReqCode = 1;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var03_choose_number);
        
        
        
        
        Button playBtn = (Button)findViewById(R.id.play);
        if(playBtn != null){
        	playBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					TextView numberText = (TextView)findViewById(R.id.number);
					
					String value = numberText.getText().toString();
					
					if(value.isEmpty()){
						return;
					}
					int intVal = Integer.parseInt(value);
					
					Intent intent = new Intent(getApplicationContext(),PracticalTest01Var02PlayActivity.class);
					intent.putExtra("number", intVal);
					startActivityForResult(intent, SecondaryActivityReqCode);
					
					Intent servIntent = new Intent(getApplicationContext(),PracticalTest01Var04Service.class);
					servIntent.putExtra("GoodNumber", intVal);
					getApplicationContext().startService(servIntent);
					
				}
			});
        }
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.practical_test01_var03_choose_number, menu);
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
    
    
}
