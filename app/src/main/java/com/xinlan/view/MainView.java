package com.xinlan.view;

import com.xinlan.bubble.R;
import com.xinlan.bubble.component.Arrow;
import com.xinlan.bubble.component.Background;
import com.xinlan.bubble.component.BgmPlayer;
import com.xinlan.bubble.component.BitmapDataContent;
import com.xinlan.bubble.component.Bubble;
import com.xinlan.bubble.component.DisappearContainer;
import com.xinlan.bubble.component.GenBubble;
import com.xinlan.bubble.component.GroupBubbles;
import com.xinlan.bubble.component.SoundPlayer;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class MainView extends SurfaceView implements Callback, Runnable {
	private SurfaceHolder sfh;
	private Paint paint;
	private Thread th;
	private boolean flag;
	private Canvas canvas;
	private Context context;
	public static int screenW, screenH;
	private Resources res = this.getResources();

	public static int GAME_STATE = 1;
	public BgmPlayer bgmPlayer;
	public SoundPlayer soundPlayer;
	public BitmapDataContent imageData;
	private Background background;
	public Arrow arrow;
	public GenBubble genBubble;
	public GroupBubbles groupBubbles;
	public DisappearContainer disappear;
	
	public MainView(Context context) {
		super(context);
		this.context = context;
		sfh = this.getHolder();
		sfh.addCallback(this);
		paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setAntiAlias(true);
		setFocusable(true);
		setFocusableInTouchMode(true);
		this.setKeepScreenOn(true);
	}

	public void surfaceCreated(SurfaceHolder holder) {
		screenW = this.getWidth();
		screenH = this.getHeight();
		init();
		flag = true;
		th = new Thread(this);
		th.start();
	}

	/**
	 */
	public void init() {
		GAME_STATE = 1;
		Bubble.RADIUS = screenW / 25;
		imageData = new BitmapDataContent(this);
		soundPlayer = new SoundPlayer(this.getContext());
		soundPlayer.loadSound();//载入声音
		imageData.loadImages();
		background = new Background(this);
		background.init();
		arrow = new Arrow(this);
		genBubble = new GenBubble(this);
		groupBubbles = new GroupBubbles(this);
		groupBubbles.init();
		disappear = new DisappearContainer(this);
		bgmPlayer = new BgmPlayer(this.getContext());
		bgmPlayer.playBmg();
	}

	public void draw() {
		try {
			canvas = sfh.lockCanvas();
			if (canvas != null) {
				switch (GAME_STATE) {
				case 1:
					canvas.drawColor(Color.WHITE);
					background.draw(canvas);
					genBubble.draw(canvas);
					groupBubbles.draw(canvas);
					disappear.draw(canvas);
					arrow.draw(canvas);
					break;
				}
			}// end if
		} catch (Exception e) {
		} finally {
			if (canvas != null) {
				sfh.unlockCanvasAndPost(canvas);
			}
		}
	}

	public void logic() {
		genBubble.logic();
		groupBubbles.logic();
		disappear.logic();
	}

	public void run() {
		while (flag) {
			long start = System.currentTimeMillis();
			logic();
			draw();
			long end = System.currentTimeMillis();
			// System.out.println(end - start);
			try {
				if (end - start < 1) {
					Thread.sleep(1 - (end - start));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}// end while
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		genBubble.onTouchEvent(event);
		return true;
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		if(bgmPlayer!=null){
			bgmPlayer.stopBgm();
		}
		flag = false;
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	public GenBubble getGenBubble() {
		return genBubble;
	}

	public void setGenBubble(GenBubble genBubble) {
		this.genBubble = genBubble;
	}

	public GroupBubbles getGroupBubbles() {
		return groupBubbles;
	}

	public void setGroupBubbles(GroupBubbles groupBubbles) {
		this.groupBubbles = groupBubbles;
	}
}// end class
