package com.xinlan.bubble.component;

import java.util.HashMap;

import com.xinlan.bubble.R;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

/**
 * 音效播放器
 * 
 * @author Panyi
 * 
 */
public class SoundPlayer {
	private Context context;
	private SoundPool soundPool;
	private HashMap<Integer, Integer> map;

	public SoundPlayer(Context context) {
		this.context = context;
		map = new HashMap<Integer, Integer>();
		soundPool = new SoundPool(6, AudioManager.STREAM_MUSIC, 100);
	}
	
	public void loadSound(){
		addSound(R.raw.click1);
		addSound(R.raw.click2);
		addSound(R.raw.fire_bubble);
		addSound(R.raw.load_bubble);
		addSound(R.raw.kill_bubble);
	}

	public void addSound(int resId) {
		int index = soundPool.load(context, resId, 0);
		map.put(resId, index);
	}

	public void playSound(int resId) {
		soundPool.play(map.get(resId), 1, 1, 0, 0, 1);
	}
}// end class
