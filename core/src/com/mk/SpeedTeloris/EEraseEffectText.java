package com.mk.SpeedTeloris;

public class EEraseEffectText extends MkObject {
	int stretch = 0;
	int speed = 1;
	int inc = 1;
	int lines = 0;
	double posXOffset = -128;

	public EEraseEffectText(){
		img.priority = 4;
		lines = common_val[ERASE_LINE]-1;
	}
	public void main() {
		posXOffset *= 0.75;
		stretch += inc;
		if(stretch > 8*speed){
			stretch = 8*speed;
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
			moveY = 8-stretch/speed;
		}
		draw.DrawImage(0, (int)(pos_x+posXOffset), (int)pos_y+moveY, 48, stretch/speed, 0, 120+lines*8, 48, 8);
	}
}