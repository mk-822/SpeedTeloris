package com.mk.SpeedTeloris;

public class EAllClearEffectText extends MkObject {
	int stretch = 0;
	int speed = 1;
	int inc = 1;
	double posXOffset = -128;

	public EAllClearEffectText(){
		new MkWave().play("che");
		img.priority = 4;
	}
	public void main() {
		posXOffset *= 0.75;
		if(posXOffset > -0.5){
			posXOffset = 0;
		}
		stretch += inc;
		if(stretch > 16*speed){
			stretch = 16*speed;
		}
		if(stretch < 0){
			hp = 0;
		}
		if(count == 240){
			inc *= -1;
		}
	}
	public void draw() {
		int moveY = 0;
		if(inc < 0){
			moveY = 16-stretch/speed;
		}
		draw.DrawImage(0, (int)(pos_x+posXOffset), (int)pos_y+moveY, 96, stretch/speed, 0, 88+count/2%2*16, 96, 16);
	}
}