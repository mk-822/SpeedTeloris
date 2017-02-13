package com.mk.SpeedTeloris;

public class EBackGroundController extends MkObject {
	int oldLevel = 0;
	int oldImage = 2;
	double alpha = 0;
	double alphaSpeed = 0.02;
	int image = MkRandom.Gen(4)+2;
	public EBackGroundController(){
		img.priority = 2;
		MkMidi midi = new MkMidi();
		midi.play("main");
	}
	public void main() {
	}
	public void draw() {
		if(count < 2){
			draw.DrawImage(2, 0, 0, 240, 240, 0, 0, 240, 240);
			draw.DrawImage(3, 0, 0, 240, 240, 0, 0, 240, 240);
			draw.DrawImage(4, 0, 0, 240, 240, 0, 0, 240, 240);
			draw.DrawImage(5, 0, 0, 240, 240, 0, 0, 240, 240);
		}
		
		if(oldLevel != common_val[LEVEL_COUNT]){
			MkWave wave = new MkWave();
			wave.play("lvl");
			alpha = 0;
			oldImage = image;
			image = MkRandom.Gen(4)+2;
		}
		if(alpha != 1){
			alpha += alphaSpeed;
			if(alpha > 1){
				alpha = 1;
			}
		}
		
		draw.DrawImage(oldImage, 0, 0, 240, 240, 0, 0, 240, 240);	// 背景描画
		draw.SetTransparency((float)alpha);
		draw.DrawImage(image, 0, 0, 240, 240, 0, 0, 240, 240);	// 背景描画
		draw.SetTransparency(1);
		
		oldLevel = common_val[LEVEL_COUNT];
	}
}
