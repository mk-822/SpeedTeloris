package com.mk.SpeedTeloris;

import android.view.KeyEvent;

public class MkInput{
	static int up = 0;
	static int down = 0;
	static int left  = 0;
	static int right = 0;
	static int button[] = { 0, 0, 0, 0 };

	static boolean fUp = false;
	static boolean fDown = false;
	static boolean fLeft  = false;
	static boolean fRight = false;
	static boolean fButton[] = { false, false, false, false };
	
	static int mouse = 0;
	static int mouser = 0;
	static boolean fMouse = false;
	static boolean fMouser = false;
	static boolean fDragged = false;
	static int mX = 0 , mY = 0;
	
	public MkInput(){}
	public void keyPressed(int keyCode) {
		switch (keyCode) {
			case KeyEvent.KEYCODE_DPAD_UP:
				fUp = true;
				break;
			case KeyEvent.KEYCODE_DPAD_DOWN:
				fDown = true;
				break;
			case KeyEvent.KEYCODE_DPAD_LEFT:
				fLeft = true;
				break;
			case KeyEvent.KEYCODE_DPAD_RIGHT:
				fRight = true;
				break;
			case KeyEvent.KEYCODE_DPAD_CENTER:
			case KeyEvent.KEYCODE_BUTTON_A:
			case KeyEvent.KEYCODE_Z:
			case KeyEvent.KEYCODE_MENU:
			case KeyEvent.KEYCODE_7:
				fButton[0] = true;
				break;
			case KeyEvent.KEYCODE_BUTTON_B:
			case KeyEvent.KEYCODE_X:
			case KeyEvent.KEYCODE_8:
				fButton[1] = true;
				break;
			case KeyEvent.KEYCODE_SEARCH:
			case KeyEvent.KEYCODE_BUTTON_X:
			case KeyEvent.KEYCODE_C:
			case KeyEvent.KEYCODE_4:
				fButton[2] = true;
				break;
			case KeyEvent.KEYCODE_BUTTON_Y:
			case KeyEvent.KEYCODE_V:
			case KeyEvent.KEYCODE_5:
				fButton[3] = true;
				break;
			default:
				break;
		}
	}
	public void keyReleased(int keyCode) {
		switch (keyCode) {
			case KeyEvent.KEYCODE_DPAD_UP:
				fUp = false;
				break;
			case KeyEvent.KEYCODE_DPAD_DOWN:
				fDown = false;
				break;
			case KeyEvent.KEYCODE_DPAD_LEFT:
				fLeft = false;
				break;
			case KeyEvent.KEYCODE_DPAD_RIGHT:
				fRight = false;
				break;
			case KeyEvent.KEYCODE_DPAD_CENTER:
			case KeyEvent.KEYCODE_BUTTON_A:
			case KeyEvent.KEYCODE_Z:
			case KeyEvent.KEYCODE_MENU:
			case KeyEvent.KEYCODE_7:
				fButton[0] = false;
				break;
			case KeyEvent.KEYCODE_BUTTON_B:
			case KeyEvent.KEYCODE_X:
			case KeyEvent.KEYCODE_8:
				fButton[1] = false;
				break;
			case KeyEvent.KEYCODE_SEARCH:
			case KeyEvent.KEYCODE_BUTTON_X:
			case KeyEvent.KEYCODE_C:
			case KeyEvent.KEYCODE_4:
				fButton[2] = false;
				break;
			case KeyEvent.KEYCODE_BUTTON_Y:
			case KeyEvent.KEYCODE_V:
			case KeyEvent.KEYCODE_5:
				fButton[3] = false;
				break;
			default:
				break;
		}
	}
	public void keyTyped(int keyCode) {	};
	public static void IncrementKeyState(){
		if(fUp){
			up++;
		}else{
			up=0;
		}
		if(fDown){
			down++;
		}else{
			down=0;
		}
		if(fLeft){
			left++;
		}else{
			left=0;
		}
		if(fRight){
			right++;
		}else{
			right=0;
		}
		if(fButton[0]){
			button[0]++;
		}else{
			button[0]=0;
		}
		if(fButton[1]){
			button[1]++;
		}else{
			button[1]=0;
		}
		if(fButton[2]){
			button[2]++;
		}else{
			button[2]=0;
		}
		if(fButton[3]){
			button[3]++;
		}else{
			button[3]=0;
		}
		if( fMouse ){
			mouse++;
		}else{
			mouse = 0;
		}
		if( fMouser ){
			mouser++;
		}else{
			mouser = 0;
		}
	}
}
