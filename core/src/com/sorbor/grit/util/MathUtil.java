package com.sorbor.grit.util;

public class MathUtil {
	
	public static double cosDegrees(double x){
		return Math.toDegrees(Math.cos(Math.toRadians(x)));
	}
	
	public static double sinDegrees(double x){
		return Math.toDegrees(Math.sin(Math.toRadians(x)));
	}
	
	public static float mapValues(float x, float in_min, float in_max, float out_min, float out_max){
		return  (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}

}
