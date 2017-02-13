package com.mk.SpeedTeloris;

public class EComboEffectText extends MkObject {
	int stretch = 0;
	int speed = 1;
	int inc = 1;
	int combo = 0;
	int lines = 0;
	double posXOffset = -128;

	public EComboEffectText(){
		img.priority = 4;
		combo = common_val[COMBO_COUNT];
		lines = common_val[ERASE_LINE]-1;
		if(combo > 10){
			combo = 10;
		}
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
		draw.DrawImage(0, (int)(pos_x+posXOffset), (int)pos_y+moveY, 8, stretch/speed, 40+combo*8, 152+lines*8, 8, 8);
		draw.DrawImage(0, (int)(pos_x+posXOffset+20), (int)pos_y+moveY, 40, stretch/speed, 0, 152+lines*8, 40, 8);
	}
}