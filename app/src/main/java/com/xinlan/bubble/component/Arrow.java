package com.xinlan.bubble.component;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import com.xinlan.bubble.R;
import com.xinlan.utils.VectorUtil;
import com.xinlan.view.MainView;

public class Arrow {
	public boolean isShow = false;

	private MainView context;
	private Bitmap bitmap;
	private Rect srcRect;
	private RectF dstRect;
	private float x, y;
	private float center_x, center_y;
	public float degrees;

	public Arrow(MainView context) {
		this.context = context;
		init();
	}

	public void init() {
		bitmap = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.arrow);
		srcRect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		center_x = x = MainView.screenW / 2;
		y = -Bubble.RADIUS + 5;
		center_y = y + bitmap.getHeight() / 2;
		dstRect = new RectF(x, y, x + bitmap.getWidth(), y + bitmap.getHeight());
	}

	public void draw(Canvas canvas) {
		if (isShow) {
			canvas.save();
			canvas.rotate(degrees, center_x, center_y);
			canvas.drawBitmap(bitmap, srcRect, dstRect, null);
			canvas.restore();
		}
	}

	public void resetDegrees(float pointx, float pointy, float x) {
		float angle = VectorUtil.calCosTwoVectorAngle(pointx, pointy, x, 0);
		degrees = (float) (angle * (180 / Math.PI));
	}

	public void logic() {

	}
}// end class
