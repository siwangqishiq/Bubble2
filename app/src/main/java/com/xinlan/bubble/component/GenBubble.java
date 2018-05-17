package com.xinlan.bubble.component;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;

import com.xinlan.bubble.R;
import com.xinlan.utils.Common;
import com.xinlan.view.MainView;

public class GenBubble {
	public static final int STATUS_WAIT = 0;
	public static final int STATUS_RELOAD = 1;
	public static final int STATUS_READY = 2;
	public static final int STATUS_FIRING = 3;
	public static final int STATUS_CANLOAD = 4;
	public static final int STATUS_ISFIRING=5;

	private MainView context;
	private float x, y;
	private float bubbleGenSpeed = 1f;
	public int status;

	private Bubble mBubble;
	private Paint mPaint;
	public static int[] colors = { Color.BLACK, Color.BLUE, Color.LTGRAY,
			Color.GREEN, Color.RED, Color.YELLOW };
	private int waitDelay = 40;

	private float absSpeed = 25f;
	private float mBubble_dx, mBubble_dy;

	public GenBubble(MainView context) {
		this.context = context;
		status = STATUS_WAIT;
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		x = MainView.screenW / 2;
		y = -Bubble.RADIUS;
	}

	public void genOneBubble() {
		
	}

	public void draw(Canvas canvas) {
		if (mBubble != null) {
			mBubble.draw(canvas);
		}
	}

	public void logic() {
		switch (status) {
		case STATUS_WAIT:// 等待
			waitDelay--;
			if (waitDelay == 0) {
				status = STATUS_CANLOAD;
			}
			break;
		case STATUS_CANLOAD:// 创建新泡泡
			mBubble = new Bubble(context.imageData,x, y, genColor());
			status = STATUS_RELOAD;
			context.soundPlayer.playSound(R.raw.load_bubble);
			break;
		case STATUS_RELOAD:// 装填新泡泡
			mBubble.y += bubbleGenSpeed;
			if (mBubble.y >= mBubble.radius) {
				mBubble.y = mBubble.radius;
				status = STATUS_READY;
			}
			break;
		case STATUS_FIRING:// 发射泡泡
			context.getGroupBubbles().setTempBubble(mBubble);
			status=STATUS_ISFIRING;
			break;
		case STATUS_ISFIRING:
			break;
		case STATUS_READY:
			break;
		}// end switch
	}

	public static int genColor() {
		return colors[Common.genRand(0, colors.length)];
	}

	public boolean onTouchEvent(MotionEvent event) {
		float touch_x = event.getX();
		float touch_y = event.getY();
		if (STATUS_READY == status) {
			switch(MotionEventCompat.getActionMasked(event)){
			case MotionEvent.ACTION_DOWN:
				context.arrow.isShow=true;
				context.arrow.resetDegrees(touch_x-mBubble.x, touch_y-mBubble.y, mBubble.x);
				break;
			case MotionEvent.ACTION_MOVE:
				context.arrow.isShow=true;
				context.arrow.resetDegrees(touch_x-mBubble.x, touch_y-mBubble.y, mBubble.x);
				break;
			case MotionEvent.ACTION_UP:
				calculateVector(touch_x, touch_y);
				status = STATUS_FIRING;
				context.arrow.isShow=false;
				break;
			}//end switch
		}
		return true;
	}

	private void calculateVector(float touch_x, float touch_y) {
		float vector_x = touch_x - mBubble.x;
		float vector_y = touch_y - mBubble.y;
		float vector_len = (float) Math
				.sqrt(vector_x * vector_x + vector_y * vector_y);// 计算单位向量
		mBubble_dx = absSpeed * (vector_x / vector_len);
		mBubble_dy = absSpeed * (vector_y / vector_len);
		mBubble.dx = mBubble_dx;
		mBubble.dy = mBubble_dy;
	}

}// end class
