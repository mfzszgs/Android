package com.suweiyue.bmi;

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //listen for the button clicks
        findView();
        setListener();
    }
    
    private Button button_clac;
    private EditText filed_height;
    private EditText filed_weight;
    private TextView view_result;
    private TextView view_suggest;
    private void findView(){
    	button_clac=(Button)findViewById(R.id.button);
    	filed_height = (EditText)findViewById(R.id.height);
		filed_weight = (EditText)findViewById(R.id.weight);
		view_result = (TextView)findViewById(R.id.view_result);
		view_suggest=(TextView)findViewById(R.id.suggest);
    }
    
    private void setListener(){
    	button_clac.setOnClickListener(calcBMI);
    }
    
	private OnClickListener button_positive = new OnClickListener(){
		public void onClick(DialogInterface dialoginterface,int i){
		}
	};
    private Button.OnClickListener calcBMI=new Button.OnClickListener(){
		public void onClick(View v) {
			// TODO Auto-generated method stub
			DecimalFormat nf= new DecimalFormat("0.00");
		try{
			double height = Double.parseDouble(filed_height.getText().toString())/100;
			double weight = Double.parseDouble(filed_weight.getText().toString());
			double BMI=weight/height/height;
			view_result.setText("YourBMI is "+nf.format(BMI));
			if (BMI>25)
				view_suggest.setText("heavy");
			else if(BMI<20)
				view_suggest.setText("light");
			else
				view_suggest.setText("average");
			openOptionsDialog();
		
		}catch(Exception obj)
		{
			Toast.makeText(MainActivity.this,R.string.input_error, Toast.LENGTH_SHORT).show();
		}
		}
	};

	private void openOptionsDialog() {
		// TODO Auto-generated method stub
		new AlertDialog.Builder(MainActivity.this)

		.setTitle(R.string.about_title)
		.setMessage(R.string.about_msg)
		.setPositiveButton("х╥хо", button_positive)
		.show();
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
}
