package com.mk.SpeedTeloris;

public class MkVirtualSprite {
	int img_size_x = 0;
	int img_size_y = 0;
	int priority = 0;
	int img_pos_x = 0;
	int img_pos_y = 0;
	public MkVirtualSprite(){}
	public void SetImage(int x, int y, int w, int h){
		img_pos_x = x;
		img_pos_y = y;
		img_size_x = w;
		img_size_y = h;
	}
}
