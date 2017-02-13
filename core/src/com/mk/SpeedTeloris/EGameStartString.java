package com.mk.SpeedTeloris;

public class EGameStartString extends MkObject {
	int stretch = 0;
	int speed = 1;
	int inc = 1;

	public EGameStartString(){
		img.priority = 4;
	}
	public void main() {
		stretch += inc;
		if(stretch > 12*speed){
			stretch = 12*speed;
		}
		if(stretch < 0){
			hp = 0;
		}
		if(count == 80){
			inc *= -1;
		}
	}
	public void draw() {
		int moveY = 0;
		if(inc < 0){
			moveY = 12-stretch/speed;
		}
		draw.DrawImage(0, (int)pos_x, (int)pos_y+moveY, 96, stretch/speed, 0, 64, 96, 12);
	}
}