package com.mk.SpeedTeloris;

public class MkRandom {
	public MkRandom(){}
	public static int Gen(int max) {
		return (int)(Math.random()*max);
	}
	public static double Gen(double max) {
		return Math.random()*max;
	}
	public static int GenAllowance(int center, int allowance) {
		return (int)(center - allowance/2 + Math.random()*allowance);
	}
}
