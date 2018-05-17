package com.xinlan.utils;

/**
 * 向量操作
 * 
 * @author Panyi
 * 
 */
public class VectorUtil {
	/**
	 * 计算两向量夹角余弦值
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public static float calCosTwoVector(float x1, float y1, float x2, float y2) {
		return (float) ((x1 * x2 + y1 * y2)
				/ (Math.sqrt(x1 * x1 + y1 * y1) * Math.sqrt(x2 * x2 + y2 * y2)));
	}
	public static double calCosTwoVectorDouble(float x1, float y1, float x2, float y2) {
		return ((x1 * x2 + y1 * y2)
				/ (Math.sqrt(x1 * x1 + y1 * y1) * Math.sqrt(x2 * x2 + y2 * y2)));
	}
	
	public static float calCosTwoVectorAngle(float x1, float y1, float x2, float y2){
		double cos=calCosTwoVectorDouble(x1,y1,x2,y2);
		return (float) Math.acos(cos);
	}
}
