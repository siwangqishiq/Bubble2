package com.xinlan.bubble.component;

import com.xinlan.bubble.R;
import com.xinlan.utils.Common;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * 播放背景音乐
 * 
 * @author Panyi
 * 
 */
public class BgmPlayer {
	private MediaPlayer mediaPlayer;// 声明一个音乐播放器

	public BgmPlayer(Context context) {
		int rand=Common.genRand(1, 2);
		if (rand == 1) {
			mediaPlayer = MediaPlayer.create(context, R.raw.bg2);
		} else if (rand == 2){
			mediaPlayer = MediaPlayer.create(context, R.raw.bg3);
		}
		mediaPlayer.setVolume(0.6f, 0.6f);
		mediaPlayer.setLooping(true);// 循环播放
	}

	/**
	 * 播放背景音乐
	 */
	public void playBmg() {
		mediaPlayer.start();// 开始播放
	}

	public void pauseBgm() {
		mediaPlayer.pause();
	}

	public void stopBgm() {
		mediaPlayer.stop();
	}
}// end class
