package github.mfzszgs.pinball;

import github.mfzszgs.pinball.object.GameConf;
import github.mfzszgs.pinball.view.GameView;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

public class MyPinBall extends Activity {
	// 游戏的初始配置信息，不改变的参数
	private GameConf conf;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 去掉窗口标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 全屏显示
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// 获取窗口管理器
		WindowManager windowManager = getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		DisplayMetrics metrics = new DisplayMetrics();
		display.getMetrics(metrics);

		// 获得屏幕宽和高,用屏幕大小来初始化配置
		conf = new GameConf(metrics.widthPixels, metrics.heightPixels);
		// 设置bitmap
		BitmapDrawable bitmapdrawable = (BitmapDrawable) getResources()
				.getDrawable(R.drawable.size);
		conf.setBitmap(bitmapdrawable);

		// 创建GameView组件
		final GameView gameView = new GameView(this, conf);
		gameView.setBackgroundColor(Color.rgb(255, 255, 255));
		setContentView(gameView);

		final Handler handler = new Handler() {
			public void handleMessage(Message msg) {
				if (msg.what == 0x123) {
					gameView.invalidate();
				}
			}
		};

		final Timer timer = new Timer();
		timer.schedule(new TimerTask() // ①
				{

					@Override
					public void run() {
						// 如果小球碰到左边边框
						if (gameView.ballX < conf.getBallSize()
								|| gameView.ballX >= conf.getTableWidth()
										- conf.getBallSize()) {
							gameView.xSpeed = -gameView.xSpeed;
						}
						// 如果小球高度超出了球拍位置，且横向不在球拍范围之内，游戏结束。
						if (gameView.ballY >= conf.getRacketY()
								- conf.getBallSize()
								&& (gameView.ballX < gameView.racketX || gameView.ballX > gameView.racketX
										+ conf.getRacketWidth())) {
							timer.cancel();
							// 设置游戏是否结束的旗标为true。
							gameView.isLose = true;
						}
						// 如果小球位于球拍之内，且到达球拍位置，小球反弹
						else if (gameView.ballY < conf.getBallSize()
								|| (gameView.ballY >= conf.getRacketY()
										- conf.getBallSize()
										&& gameView.ballX > gameView.racketX && gameView.ballX <= gameView.racketX
										+ conf.getRacketWidth())) {
							gameView.ySpeed = -gameView.ySpeed;
						}
						// 小球座标增加
						gameView.ballY += gameView.ySpeed;
						gameView.ballX += gameView.xSpeed;
						// 发送消息，通知系统重绘组件
						handler.sendEmptyMessage(0x123);
					}
				}, 0, conf.getPeriod());
	}
}
