package com.mk.SpeedTeloris;

public class MkObjectController {
	static final int OBJMAX = 1024;
	static final int EVOBJ = 960;
	static int count = 0;
	static int current_obj = 0;
	static MkObject[] obj = new MkObject[OBJMAX];
	static int[] damage = new int[OBJMAX];
	static String namespace = "com.mk.SpeedTeloris.";
	public MkObjectController(){
		for(int i=0 ; i<EVOBJ ; i++){
			damage[i] = 0;
		}
	}
	public static MkObject Generate(String name, int x, int y){
		int empty = SearchEmpty();
		if(empty == -1) {
			return null;
		}
		try {
			obj[empty] = (MkObject)Class.forName(namespace+name).newInstance();
			obj[empty].pos_x = x;
			obj[empty].pos_y = y;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return obj[empty];
	}
	public static MkObject Generate(String name, int x, int y, int Angle, double Speed){
		int empty = SearchEmpty();
		if(empty == -1){
			return null;
		}
		try {
			obj[empty] = (MkObject)Class.forName(namespace+name).newInstance();
			obj[empty].pos_x = x;
			obj[empty].pos_y = y;
			obj[empty].angle = Angle;
			obj[empty].speed = (float)Speed;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return obj[empty];
	}
	public static MkObject GenerateEvent(String name){
		int empty = SearchEmptyEvent();
		if(empty == -1){
			return null;
		}
		try {
			obj[empty] = (MkObject)Class.forName(namespace+name).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return obj[empty];
	}
	private static int SearchEmpty(){
		for(int i=0 ; i<EVOBJ ; i++){
			if(obj[i] == null){
				return i;
			}
		}
		return -1;
	}
	private static int SearchEmptyEvent(){
		for(int i=EVOBJ ; i<OBJMAX ; i++){
			if(obj[i] == null){
				return i;
			}
		}
		return -1;
	}
	public static void Run() {
		for(current_obj=0 ; current_obj<OBJMAX ; current_obj++){
			if(obj[current_obj] != null){
				if(obj[current_obj].enable){
					obj[current_obj].main();
					obj[current_obj].CountUp();
				}
				obj[current_obj].enable = true;
			}
		}
		count++;
	}
	public static void Hit() {
		for(current_obj=0 ; current_obj<OBJMAX ; current_obj++){	// このオブジェクトが
			if(obj[current_obj] != null){
				for(int j=0 ; j<OBJMAX ; j++){ // このオブジェクトから
					if((obj[j] != null)&&(current_obj!=j)){
						if(	// ダメージを受けるかどうか
							((obj[current_obj].size_x + obj[j].size_x)/2 >= Math.abs(obj[j].pos_x - obj[current_obj].pos_x))
							&&((obj[current_obj].size_y + obj[j].size_y)/2 >= Math.abs(obj[j].pos_y - obj[current_obj].pos_y))
							&&((obj[current_obj].hit_type & obj[j].type) != 0)
						){
							if(damage[current_obj] < obj[j].attack){
								damage[current_obj] = obj[j].attack;
								obj[current_obj].hit(obj[j]);
							}
							obj[current_obj].hit_flag = true;
						}
					}
				}
			}
		}
		for(int i=0 ; i<OBJMAX ; i++){
			if(obj[i] != null){
				obj[i].hp -= damage[i];
			}
			damage[i] = 0;
		}
	}
	public static void Draw() {
		for(int j=0 ; j<8 ; j++){
			for(current_obj=0 ; current_obj<OBJMAX ; current_obj++){
				if((obj[current_obj] != null)&&(obj[current_obj].visible)){
					if(obj[current_obj].img.priority == j){
						obj[current_obj].draw();
						if(obj[current_obj] != null)
							obj[current_obj].hit_flag = false;
					}
				}
			}
		}
	}
	public static void Dead() {
		for(current_obj=0 ; current_obj<OBJMAX ; current_obj++){
			if(obj[current_obj] != null){
				if(obj[current_obj].hp <= 0){
					obj[current_obj].dead();
					obj[current_obj] = null;
				}
			}
		}
	}
	public static void Suicide() {
		obj[current_obj] = null;
	}
	public static void AllKill() {
		for(current_obj=0 ; current_obj<OBJMAX ; current_obj++){
			obj[current_obj] = null;
		}
	}
	public static void WipeOut() {
		for(current_obj=0 ; current_obj<OBJMAX ; current_obj++){
			if(obj[current_obj] != null){
				if(!obj[current_obj].can_erase){
					obj[current_obj] = null;
				}
			}
		}
	}
	public static int ObjectCount() {
		int objcnt=0;
		for(current_obj=0 ; current_obj<OBJMAX ; current_obj++){
			if(obj[current_obj] != null){
				objcnt++;
			}
		}
		return objcnt;
	}
}
