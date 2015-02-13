package github.mfzszgs.pinball.view;

import github.mfzszgs.pinball.object.GameConf;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View {

	Paint paint = new Paint();
	// 当前x坐标
	public float currentX = 0f;
	// ballX和ballY代表小球的座标
	public int ballX;
	public int ballY;
	// racketX代表球拍的水平位置
	public int racketX;
	// 小球纵向的运行速度
	public int ySpeed;
	// 小球横向的运行速度
	public int xSpeed;
	// 游戏是否结束的旗标
	public boolean isLose;
	// 游戏的配置信息
	private GameConf conf;

	public GameView(Context context, GameConf conf) {
		super(context);
		this.conf = conf;

		currentX = 0f;
		// 指定游戏开始时球拍X坐标，球的X，Y坐标
		racketX = conf.getTableWidth() / 2 - 2 * conf.getSize();
		ballX = conf.getTableWidth() / 2;
		ballY = conf.getSize();
		// 初始化小球运动速度
		xSpeed = conf.getSize();
		ySpeed = conf.getSize();
		isLose = false;
		setFocusable(true);
	}

	// 重写View的onDraw方法，实现绘画
	public void onDraw(Canvas canvas) {
		paint.setStyle(Paint.Style.FILL);
		// 设置去锯齿
		paint.setAntiAlias(true);
		// 如果游戏已经结束
		if (isLose) {
			paint.setColor(Color.RED);
			paint.setTextSize(40);
			canvas.drawText("游戏已结束" + " h=" + conf.getTableHeight() + " w="
					+ conf.getTableWidth(), 50, 200, paint);
		}
		// 如果游戏还未结束
		else {
			// 设置颜色，并绘制小球
			canvas.drawBitmap(conf.getBitmap(), ballX, ballY, paint);
			// 设置颜色，并绘制球 拍
			for (int i = 0; i < 4; i++)
				canvas.drawBitmap(conf.getBitmap(),
						racketX + i * conf.getSize(), conf.getRacketY(), paint);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (currentX > event.getX()) {
			racketX -= 10;
		} else if (currentX < event.getX()) {
			racketX += 10;
			;
		}
		invalidate();
		currentX = event.getX();
		return true;
	}

}
