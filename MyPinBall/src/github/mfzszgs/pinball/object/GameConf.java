package github.mfzszgs.pinball.object;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

public class GameConf {

	// 桌面的宽度
	private int tableWidth;
	// 桌面的高度
	private int tableHeight;
	// 每个格子的大小
	private int size;
	// 更新周期
	private int period;
	// 球拍的垂直位置
	private int racketY;
	// 下面定义球拍的高度和宽度
	private int racketHeight;
	private int racketWidth;
	// 小球的大小
	private int ballSize;
	// 游戏使用的格子图
	private Bitmap bitmap;

	// 使用屏幕大小来初始化游戏配置
	public GameConf(int tableWidth, int tableHeight) {
		//设置桌面大小
		this.tableHeight = tableHeight;
		this.tableWidth = tableWidth;
		// 设置格子大小
		size = tableWidth / 18;
		// 　设置球拍高宽
		racketHeight = size;
		racketWidth = 4 * size;
		ballSize = size;
		racketY = tableHeight - size;
		period = 400;

	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(BitmapDrawable bitmapdrawable) {
		bitmap = Bitmap.createScaledBitmap(bitmapdrawable.getBitmap(), size,
				size, true);
	}

	public int getTableWidth() {
		return tableWidth;
	}

	public void setTableWidth(int tableWidth) {
		this.tableWidth = tableWidth;
	}

	public int getTableHeight() {
		return tableHeight;
	}

	public void setTableHeight(int tableHeight) {
		this.tableHeight = tableHeight;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public int getRacketY() {
		return racketY;
	}

	public void setRacketY(int racketY) {
		this.racketY = racketY;
	}

	public int getRacketHeight() {
		return racketHeight;
	}

	public void setRacketHeight(int racketHeight) {
		this.racketHeight = racketHeight;
	}

	public int getRacketWidth() {
		return racketWidth;
	}

	public void setRacketWidth(int racketWidth) {
		this.racketWidth = racketWidth;
	}

	public int getBallSize() {
		return ballSize;
	}

	public void setBallSize(int ballSize) {
		this.ballSize = ballSize;
	}

}
