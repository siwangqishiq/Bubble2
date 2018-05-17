package com.xinlan.bubble.component;

import java.util.ArrayList;

import android.graphics.Canvas;

import com.xinlan.view.MainView;

/**
 * 装载要消失的泡泡容器
 * 
 * @author Administrator
 * 
 */
public class DisappearContainer {
	private MainView context;
	private ArrayList<Bubble> list;
	private float dr = 0.1f;

	public DisappearContainer(MainView mainview) {
		context = mainview;
		list = new ArrayList<Bubble>();
	}

	public void logic() {
		for (int i = 0; i < list.size(); i++) {
			Bubble bubble = list.get(i);
			bubble.status=Bubble.STATUS_DEAD;
			bubble.scale -= dr;
			if (bubble.scale <= 0) {
				list.remove(bubble);
				context.groupBubbles.root.remove(bubble);
				System.gc();
			}
		}// end for i
	}

	public void addDisappearBubble(Bubble bubble) {
		list.add(bubble);
	}

	public void draw(Canvas canvas) {
		for (Bubble bubble : list) {
			bubble.draw(canvas);
		}// end for
	}
}// end class
