package com.sorbor.grit.util;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;

/**
 * Incomplete class! WARNING DO NOT USE CARELESSLY
 * 
 * TODO IMPLEMENT FUNCTIONS
 */
public class Vector2Double implements Vector<Vector2Double>{


	double x,y;
	public Vector2Double() {
		x = 0;
		y = 0;
	}
	
	public Vector2Double(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public Vector2Double(Vector2Double vec){
		this(vec.x, vec.y);
	}
	
	public Vector2Double(Vector2 vec){
		this(vec.x, vec.y);
	}

	@Override
	public Vector2Double cpy() {
		return new Vector2Double(this);
	}
	
	@Override
	public float len() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float len2() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Vector2Double limit(float limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector2Double limit2(float limit2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector2Double setLength(float len) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector2Double setLength2(float len2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector2Double clamp(float min, float max) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector2Double set(Vector2Double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector2Double sub(Vector2Double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector2Double nor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector2Double add(Vector2Double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float dot(Vector2Double v) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Vector2Double scl(float scalar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector2Double scl(Vector2Double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float dst(Vector2Double v) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float dst2(Vector2Double v) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Vector2Double lerp(Vector2Double target, float alpha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector2Double interpolate(Vector2Double target, float alpha, Interpolation interpolator) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUnit() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isUnit(float margin) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isZero() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isZero(float margin) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isOnLine(Vector2Double other, float epsilon) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isOnLine(Vector2Double other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCollinear(Vector2Double other, float epsilon) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCollinear(Vector2Double other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCollinearOpposite(Vector2Double other, float epsilon) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCollinearOpposite(Vector2Double other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPerpendicular(Vector2Double other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPerpendicular(Vector2Double other, float epsilon) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasSameDirection(Vector2Double other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasOppositeDirection(Vector2Double other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean epsilonEquals(Vector2Double other, float epsilon) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Vector2Double mulAdd(Vector2Double v, float scalar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector2Double mulAdd(Vector2Double v, Vector2Double mulVec) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector2Double setZero() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String toString(){
		return "Vector2Double(" + x + ":" + y + ")";
	}


}
