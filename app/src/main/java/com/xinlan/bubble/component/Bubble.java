package com.xinlan.bubble.component;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

public class Bubble {
	public Bitmap bitmap;
	public static float RADIUS;
	private BitmapDataContent dataContent;
	public float radius;
	public float x, y;
	public float dx, dy;

	private int color;
	private Paint paint;
	private Rect srcRect;
	private RectF dstRect;
	private float width, height;

	public static final int STATUS_NORMAL = 1;
	public static final int STATUS_DEAD = 2;;
	public int status;
	public float scale = 1.0f;

	public Bubble(BitmapDataContent data, float x, float y, int color,
			float dx, float dy) {
		status = STATUS_NORMAL;
		radius = RADIUS;
		dataContent = data;
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.color = color;
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(color);
		loadBitmap();
		srcRect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		dstRect = new RectF();
		width = 2 * radius;
		height = 2 * radius;
	}

	public Bubble(BitmapDataContent data, float x, float y, int color) {
		this(data, x, y, color, 0, 0);
	}

	public int getColor() {
		return color;
	}

	private void loadBitmap() {
		if (bitmap == null) {
			bitmap = dataContent.getBitmapByColor(color);
		}
	}

	public void draw(Canvas canvas) {
		// canvas.drawCircle(x, y, radius, paint);
		dstRect.left = x - radius;
		dstRect.top = y - radius;
		dstRect.right = dstRect.left + width;
		dstRect.bottom = dstRect.top + height;
		switch (status) {
		case STATUS_NORMAL:
			canvas.drawBitmap(bitmap, srcRect, dstRect, null);
			break;
		case STATUS_DEAD:
			canvas.save();
			canvas.scale(scale, scale, x, y);
			canvas.drawBitmap(bitmap, srcRect, dstRect, null);
			canvas.restore();
			break;
		}
		// canvas.drawBitmap(bitmap, x, y, null);
	}
}// end class
