package com.sorbor.grit.util;

public class MathUtil {
	
	public static double cosDegrees(double x){
		return Math.toDegrees(Math.cos(Math.toRadians(x)));
	}
	
	public static double sinDegrees(double x){
		return Math.toDegrees(Math.sin(Math.toRadians(x)));
	}

}
