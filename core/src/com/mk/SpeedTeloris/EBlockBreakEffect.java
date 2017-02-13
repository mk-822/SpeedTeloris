package com.mk.SpeedTeloris;

public class EBlockBreakEffect extends MkObject {
	int stretch = 0;
	int speed = 1;
	int inc = 1;
	int color = 0;
	double moveX = (common_val[ERASE_BLOCK_POS_X]-5)*0.2;
	int spinSpeed = MkRandom.Gen(8)+2;
	boolean spinDirection = false;
	double moveY = -1 -(20-common_val[ERASE_BLOCK_POS_Y])*0.08;//-(MkRandom.Gen(1.0)+1);
	double moveYmove = 0.1;
	double moveYmoveGravity = 1.03;

	public EBlockBreakEffect(){
		if(Math.random()>0.5){
			spinDirection = true;
		}
		img.priority = 4;
		color = common_val[EFFECT_COLOR];
	}
	public void main() {
		pos_x += moveX;
		pos_y += moveY;
		moveY += moveYmove;
		moveYmove *= moveYmoveGravity;
		if(count>64){
			hp=0;
		}
	}
	public void draw() {
		if(spinDirection){
			draw.DrawImage(0, (int)pos_x, (int)pos_y, 8, 8, 96+color*8, 32+count/spinSpeed%5*8, 8, 8);
		}else{
			draw.DrawImage(0, (int)pos_x, (int)pos_y, 8, 8, 96+color*8, 32+(4-count/spinSpeed%5)*8, 8, 8);
		}
	}
}