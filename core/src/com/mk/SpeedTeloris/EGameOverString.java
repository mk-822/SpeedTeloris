package com.mk.SpeedTeloris;

public class EGameOverString extends MkObject {
	int stretch = 0;
	int speed = 3;
	int inc = 1;

	public EGameOverString(){
		new MkWave().play("gmo");
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
		if(count == 180){
			inc *= -1;
		}
	}
	public void draw() {
		int moveY = 0;
		if(inc < 0){
			moveY = 12-stretch/speed;
		}
		draw.DrawImage(0, (int)pos_x, (int)pos_y+moveY, 96, stretch/speed, 0, 76, 96, 12);
	}
}
