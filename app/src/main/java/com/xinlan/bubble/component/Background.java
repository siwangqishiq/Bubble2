package com.xinlan.bubble.component;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.xinlan.bubble.R;
import com.xinlan.view.MainView;

public class Background {
	private MainView context;
	private Bitmap bitmap;
	private Rect srcRect, dstRect;

	public Background(MainView context) {
		this.context = context;
	}

	public void init() {
		bitmap = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.bg);
		srcRect = new Rect(0, 120, bitmap.getWidth(), bitmap.getHeight());
		dstRect = new Rect(0, 0, MainView.screenW, MainView.screenH);
	}

	public void draw(Canvas canvas) {
		canvas.drawBitmap(bitmap, srcRect, dstRect, null);
	}
}// end class
