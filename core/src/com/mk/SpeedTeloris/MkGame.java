package com.mk.SpeedTeloris;

public class MkGame {
	MkObjectController objCtrl = new MkObjectController();
	public MkGame(){
		MkWave wave = new MkWave();
		MkMidi midi = new MkMidi();
		midi.load("main", "bgm/dm.mid");
		wave.load("bl2", R.raw.block1); // I
		wave.load("bl6", R.raw.block2); // L
		wave.load("bl7", R.raw.block3); // O
		wave.load("bl4", R.raw.block4); // Z
		wave.load("bl1", R.raw.block5); // T
		wave.load("bl5", R.raw.block6); // J
		wave.load("bl3", R.raw.block7); // S
		wave.load("app", R.raw.applause);
		wave.load("che", R.raw.cheer);
		wave.load("dec", R.raw.decide);
		wave.load("ers", R.raw.erase);
		wave.load("fal", R.raw.fall);
		wave.load("fix", R.raw.fix);
		wave.load("gmo", R.raw.gameover);
		wave.load("hld", R.raw.hold);
		wave.load("irs", R.raw.irs);
		wave.load("lvl", R.raw.levelup);
		wave.load("mov", R.raw.move);
		wave.load("set", R.raw.set);
		MkObjectController.GenerateEvent("EBackGroundController");
		MkObjectController.GenerateEvent("ETetrisController");
	}
	public void Main(){
		//MkDraw draw = new MkDraw();
		MkInput.IncrementKeyState();
		
		MkObjectController.Run();
		MkObjectController.Hit();
		MkObjectController.Draw();
		MkObjectController.Dead();
		
		/*draw.GetG2D().setColor(Color.BLACK);
		draw.GetG2D().drawString(draw.GetFps()+"FPS / "+MkObjectController.ObjectCount()+"OBJ" , 145 , 25);
		draw.GetG2D().setColor(Color.WHITE);
		draw.GetG2D().drawString(draw.GetFps()+"FPS / "+MkObjectController.ObjectCount()+"OBJ" , 144 , 24);*/
	}
}
