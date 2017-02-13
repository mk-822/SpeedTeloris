package com.mk.SpeedTeloris;

import java.util.Timer;
import java.util.TimerTask;

public class MkTimer {
	Timer timer;
	TimerTask task;
	int intv;
	
	public MkTimer(){	}
	public void SetTimer(TimerTask tt, int interval){
		task  = tt;
		intv = interval;
	}
	public void Start(){
		if(task != null){
			timer = new Timer();
			timer.scheduleAtFixedRate(task, 0, intv);
		}
	}
	public void Stop(){
		if(task != null){
			timer.cancel();
			timer = null;
		}
	}
}
