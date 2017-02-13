package com.mk.SpeedTeloris;

public class EDifficultySelect extends MkObject {
	private int cur_pos = 0;
	public EDifficultySelect(){
		img.priority = 4;
	}
	public void main() {
		if(MkInput.down==1 || MkInput.down>20){
			new MkWave().play("mov");
			cur_pos = (cur_pos+3+1)%3;
		}
		if(MkInput.up==1 || MkInput.up>20){
			new MkWave().play("mov");
			cur_pos = (cur_pos+3-1)%3;
		}
		if(MkInput.button[0] == 1){
			new MkWave().play("dec");
			switch(cur_pos){
			case 0:
				common_val[SELECTED_LEVEL] = 1;
				break;
			case 1:
				common_val[SELECTED_LEVEL] = 8;
				break;
			case 2:
				common_val[SELECTED_LEVEL] = 15;
				break;
			}
			hp=0;
		}
	}
	public void draw() {
		draw.DrawImage(0, (int)pos_x+4, (int)pos_y+32+cur_pos*16, 8, 8, 88, 32+count/4%4*8, 8, 8);
		draw.DrawImage(0, (int)pos_x+4, (int)pos_y, 56, 8, 32, 32, 56, 8);
		draw.DrawImage(0, (int)pos_x+20, (int)pos_y+32, 40, 8, 40, 40, 40, 8);
		draw.DrawImage(0, (int)pos_x+20, (int)pos_y+48, 40, 8, 40, 48, 40, 8);
		draw.DrawImage(0, (int)pos_x+20, (int)pos_y+64, 40, 8, 40, 56, 40, 8);
	}
}