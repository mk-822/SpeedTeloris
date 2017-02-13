package com.mk.SpeedTeloris;

public class MkObject {
	/*
	static final int MYSHIP = 1;			// 自機
	static final int MYBULLET = 2;			// 貫通しない自機弾
	static final int ENEMY = 8;				// 敵機
	static final int ENEMYBULLET = 16;		// 通常の敵弾
	static final int ENEMYBULLET2 = 32;		// 自機弾で破壊できる敵弾
	static final int ITEM = 64;				// アイテム

	static final int MYSHIP_HIT = ENEMY | ENEMYBULLET | ENEMYBULLET2;
	static final int MYBULLET_HIT = ENEMY;
	static final int ENEMY_HIT = MYBULLET;
	static final int ENEMYBULLET_HIT = MYSHIP;
	static final int ENEMYBULLET2_HIT = MYSHIP | MYBULLET;
	static final int ITEM_HIT = MYSHIP;
	*/
	
	static int[] common_val = new int[8];
	static final int SELECTED_LEVEL = 0;
	static final int EFFECT_COLOR = 1;
	static final int ERASE_BLOCK_POS_X = 2;
	static final int ERASE_BLOCK_POS_Y = 3;
	static final int ERASE_LINE = 4;
	static final int COMBO_COUNT = 5;
	static final int LEVEL_COUNT = 6;
	
	static boolean[] common_flag = new boolean[8];
	static final int MYSHIP_DEAD = 0;
	static final int BOSS_DEAD = 1;
		
	// この 6 つはサブクラスで変更してください
	int hp = 1;
	int attack = 0;
	int size_x = 0;
	int size_y = 0;
	int type = 0;
	int hit_type = 0;

	int cnt = 0;
	float speed = 0;
	int angle = 0;
	float pos_x = 0;
	float pos_y = 0;
	int count = 0;
	MkVirtualSprite img = new MkVirtualSprite();
	MkDraw draw = new MkDraw();
	
	boolean hit_flag = false;
	boolean enable = false;
	boolean visible = true;
	boolean can_erase = false;
	
	public MkObject() {
	}
	// 通常処理
	public void main() {
	}
	// 描画処理
	public void draw() {
		int imgno = 0;
		if(hit_flag)
			imgno = 1;
		draw.DrawImage(
			imgno,
			(int)pos_x - img.img_size_x/2,
			(int)pos_y - img.img_size_y/2,
			img.img_size_x,
			img.img_size_y,
			img.img_pos_x,
			img.img_pos_y,
			img.img_size_x,
			img.img_size_y
		);
	}
	// 死亡時の処理
	public void dead() {
	}
	// 即時実行したい時の処理
	public void direct() {
	}
	public void CountUp(){
		count++;
	}
	public void move(int ang, double spd) {
		ang += 256;
		double rad = ang * Math.PI / 512;
		pos_x += Math.cos(rad) * spd;
		pos_y += Math.sin(rad) * spd;
	}
	public boolean isprotrusion() {
		if(
			(pos_x + img.img_size_x <= 0)||
			(pos_x - img.img_size_x >= 240)||
			(pos_y + img.img_size_y <= 0)||
			(pos_y - img.img_size_y >= 240)
		){
			return true;
		}else{
			return false;
		}
	}
	public boolean rolling(int initial_frame, int interval, int times) {
		int tmp = count-initial_frame;
		if(
			(tmp >= 0)&&
			(tmp%interval == 0)&&
			((cnt = tmp/interval) < times)
		){
			return true;
		}
		return false;
	}
	public boolean rolling(int initial_frame, int interval) {
		int tmp = count-initial_frame;
		if(
			(tmp >= 0)&&
			(tmp%interval == 0)
		){
			cnt = tmp/interval;
			return true;
		}
		return false;
	}
	public int myship_angle(int x, int y){
		//double dx = x - common_val[MYSHIP_X];
		//double dy = (y - common_val[MYSHIP_Y]) * -1;
		//int Angle = (int)(Math.atan2(dx, dy) * 512 / Math.PI);
		return 0;//Angle;
	}
	public int grade_angle(int Angle, int target, int Speed){
		if(
			Angle%1024 - target%1024 >
			target%1024 - Angle%1024
		){
			Angle -= Speed;
			if(Angle < target){
				Angle = target;
			}
		}else if(
			Angle%1024 - target%1024 <
			target%1024 - Angle%1024
		){
			Angle += Speed;
			if(Angle > target){
				Angle = target;
			}
		}else{
			Angle = target;
		}
		return Angle;
	}
	// HIT時のコールバックメソッド
	public void hit(MkObject object) {
	}
}
