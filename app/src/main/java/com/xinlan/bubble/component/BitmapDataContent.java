package com.xinlan.bubble.component;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import com.xinlan.bubble.R;
import com.xinlan.view.MainView;

/**
 * 存贮位图数据
 * 
 * @author Panyi
 * 
 */
public class BitmapDataContent {

	private MainView mContext;

	private Bitmap blackBubbleBitmap, blueBubbleBitmap, grayBubbleBitmap,
			greenBubbleBitmap, redBubbleBitmap, yellowBitmap;

	public BitmapDataContent(MainView context) {
		mContext = context;
	}

	public void loadImages() {
		blackBubbleBitmap = BitmapFactory.decodeResource(
				mContext.getResources(), R.drawable.black);
		blueBubbleBitmap = BitmapFactory.decodeResource(
				mContext.getResources(), R.drawable.blue);
		grayBubbleBitmap = BitmapFactory.decodeResource(
				mContext.getResources(), R.drawable.gray);
		greenBubbleBitmap = BitmapFactory.decodeResource(
				mContext.getResources(), R.drawable.green);
		redBubbleBitmap = BitmapFactory.decodeResource(mContext.getResources(),
				R.drawable.red);
		yellowBitmap = BitmapFactory.decodeResource(mContext.getResources(),
				R.drawable.yellow);
	}

	public Bitmap getBitmapByColor(int color) {
		Bitmap ret = null;
		switch (color) {
		case Color.BLACK:
			ret = blackBubbleBitmap;
			break;
		case Color.BLUE:
			ret = blueBubbleBitmap;
			break;
		case Color.LTGRAY:
			ret = grayBubbleBitmap;
			break;
		case Color.GREEN:
			ret = greenBubbleBitmap;
			break;
		case Color.RED:
			ret = redBubbleBitmap;
			break;
		case Color.YELLOW:
			ret = yellowBitmap;
			break;
		}// end switch
		return ret;
	}
}// end class
