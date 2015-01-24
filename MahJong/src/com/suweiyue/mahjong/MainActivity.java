package com.suweiyue.mahjong;


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
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// listen for the button clicks
		findView();
		setListener();

	}

	private Button button_north;
	private Button button_south;
	private Button button_east;
	private Button button_west;
	private TextView textview_north;
	private TextView textview_south;
	private TextView textview_east;
	private TextView textview_west;
	private RadioGroup radiogroup;

	// private NumberPicker numberpicker1;
	// private int score;

	private void findView() {
		button_north = (Button) findViewById(R.id.button_north);
		button_south = (Button) findViewById(R.id.button_south);
		button_east = (Button) findViewById(R.id.button_east);
		button_west = (Button) findViewById(R.id.button_west);
		textview_north = (TextView) findViewById(R.id.textview_north);
		textview_south = (TextView) findViewById(R.id.textview_south);
		textview_east = (TextView) findViewById(R.id.textview_east);
		textview_west = (TextView) findViewById(R.id.textview_west);
		
		radiogroup = (RadioGroup) findViewById(R.id.radiogroup);
		// numberpicker1 = (NumberPicker) findViewById(R.id.numberpicker1);
	}

	private void setListener() {
		button_north.setOnClickListener(clicklistener_north);
		button_south.setOnClickListener(clicklistener_south);
		button_east.setOnClickListener(clicklistener_east);
		button_west.setOnClickListener(clicklistener_west);
	}

	// private void setNumberPicker() {
	// numberpicker1.setMinValue(1);
	// numberpicker1.setMaxValue(50);
	// numberpicker1.setValue(score);
	// numberpicker1.setOnValueChangedListener(new OnValueChangeListener() {
	// @Override
	// public void onValueChange(NumberPicker picker, int oldVal,
	// int newVal) {
	// score = newVal;
	// // showSelectedPrice();
	// }
	// });
	// }

	// set the button listener

	private Button.OnClickListener clicklistener_north = new Button.OnClickListener() {
		@Override
		public void onClick(View v) {
			try {
				openOptionsDialog(v,textview_north);

			} catch (Exception obj) {
				Toast.makeText(MainActivity.this, R.string.input_error,
						Toast.LENGTH_SHORT).show();
			}
		}
	};
	private Button.OnClickListener clicklistener_south = new Button.OnClickListener() {
		public void onClick(View v) {
			try {
				openOptionsDialog(v,textview_south);

			} catch (Exception obj) {
				Toast.makeText(MainActivity.this, R.string.input_error,
						Toast.LENGTH_SHORT).show();
			}
		}
	};
	private Button.OnClickListener clicklistener_west = new Button.OnClickListener() {
		public void onClick(View v) {
			try {
				openOptionsDialog(v,textview_west);

			} catch (Exception obj) {
				Toast.makeText(MainActivity.this, R.string.input_error,
						Toast.LENGTH_SHORT).show();
			}
		}
	};
	private Button.OnClickListener clicklistener_east = new Button.OnClickListener() {
		public void onClick(View v) {
			try {
				openOptionsDialog(v,textview_east);

			} catch (Exception obj) {
				Toast.makeText(MainActivity.this, R.string.input_error,
						Toast.LENGTH_SHORT).show();
			}
		}
	};

	public void openOptionsDialog(View v,TextView textview) {
		// 装载/res/layout/login.xml界面布局

		TableLayout loginForm = (TableLayout) getLayoutInflater().inflate(
				R.layout.dialog, null);

		new AlertDialog.Builder(this)
		// 设置对话框的标题
				.setTitle("从选定 者处获取")
				// 设置对话框显示的View对象
				.setView(loginForm)
				// 为对话框设置一个“确定”按钮
				.setPositiveButton("OK", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						EditText edittext_score;
						edittext_score = (EditText) findViewById(R.id.score);
						edittext_score.getText();
						// 此处可执行登录处理
//						try{
//							edittext_score.getText();
////							int score=Integer.parseInt(edittext_score.getText().toString());
//						}catch(Exception obj)
//						{
//							Toast.makeText(MainActivity.this,R.string.input_error, Toast.LENGTH_SHORT).show();
//						}
//						radiogroup
//								.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//									@Override
//									public void onCheckedChanged(
//											RadioGroup group, int checkedId) {
										// TODO Auto-generated method stub
//										textview.setText(Integer
//												.parseInt(textview
//														.getText()
//														.toString())
//												+ score);
//										switch (checkedId) {
//										case R.id.radiobutton_north:
//											textview_north.setText(Integer
//													.parseInt(textview_north
//															.getText()
//															.toString())
//													- score);
//											break;
//										case R.id.radiobutton_south:
//											textview_south.setText(Integer
//													.parseInt(textview_south
//															.getText()
//															.toString())
//													- score);
//											break;
//										case R.id.radiobutton_east:
//											textview_east.setText(Integer
//													.parseInt(textview_east
//															.getText()
//															.toString())
//													- score);
//											break;
//										case R.id.radiobutton_west:
//											textview_west.setText(Integer
//													.parseInt(textview_west
//															.getText()
//															.toString())
//													- score);
//											break;
//										default: ;
//										}
//									}

//								});

					}
				})
				// 为对话框设置一个“取消”按钮
				.setNegativeButton("Cancel", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// 取消登录，不做任何事情。
					}
				})
				// 创建、并显示对话框
				.create().show();

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
