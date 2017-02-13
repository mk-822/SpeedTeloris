package com.mk.SpeedTeloris;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class MkDraw {
	private static int fps, fpscount;
	private static long nowtime_old = 0;
	static Bitmap[] spImage = new Bitmap[8];
	static int offset_x = 0;
	static int width, height;
	Rect paramRect = new Rect();
	Rect paramRect2 = new Rect();
	static int screenX = 240;
	static int screenY = 240;
	//static float magnification = 320.0f / 240.0f;
	static Paint paint = new Paint();
	
	static final int CMDTYPE_DRAW = 0;
	static final int CMDTYPE_SETALPHA = 1;
	
	public static final int MAX_DISPCOMMAND = 512;
	class DispCommand{
		int type = 0;
		int value = 0;
		Rect src = new Rect();
		Rect dest = new Rect();
	}
	static DispCommand[] dispList = new DispCommand[MAX_DISPCOMMAND];
	static int dispNum = 0;

	public MkDraw(){}
	public MkDraw(int Width, int Height, Context ctx){
		for(int i=0; i<MAX_DISPCOMMAND; i++){
			dispList[i] = new DispCommand();
		}
		width = Width;
		height = Height;
		spImage[0] = loadImage(ctx, R.drawable.parts);
		spImage[1] = loadImage(ctx, R.drawable.shadow);
		spImage[2] = loadImage(ctx, R.drawable.bg1);
		spImage[3] = loadImage(ctx, R.drawable.bg2);
		spImage[4] = loadImage(ctx, R.drawable.bg3);
		spImage[5] = loadImage(ctx, R.drawable.bg4);
		paint.setAntiAlias(true);
	}
	public Bitmap loadImage(Context ctx, int resourceID){
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inScaled = false;
		return BitmapFactory.decodeResource(ctx.getResources(), resourceID, opt);
	}
	public void DrawImage(int imgno, int x, int y, int w, int h, int x2, int y2, int w2, int h2){
		x += offset_x;
		paramRect.set(x, y, x+w, y+h);
		paramRect2.set(x2, y2, x2+w2, y2+h2);
		PushDisplayList(imgno, paramRect2, paramRect);
	}
	public void DrawImageNormal(int imgno, int x, int y, int w, int h, int x2, int y2, int w2, int h2){
		paramRect.set(x, y, x+w, y+h);
		paramRect2.set(x2, y2, x2+w2, y2+h2);
		PushDisplayList(imgno, paramRect2, paramRect);
	}
	public static void PushDisplayList(int spriteNo, Rect src, Rect dest){
		if(dispNum >= MAX_DISPCOMMAND)
			return;
		DispCommand command = dispList[dispNum];
		command.type = CMDTYPE_DRAW;
		command.value = spriteNo;
		command.src.set(src);
		command.dest.set(dest);
		dispNum++;
	}
	public static void ClearDisplayList(){
		dispNum = 0;
	}
	public void SetTransparency(float alpha){
		if(dispNum >= MAX_DISPCOMMAND)
			return;
		DispCommand command = dispList[dispNum];
		command.type = CMDTYPE_SETALPHA;
		command.value = (int)(alpha * 255);
		dispNum++;
	}
	public static void FpsCount(long Nowtime){
		if(Nowtime >= nowtime_old+1000){
			fps = fpscount;
			fpscount = 0;
			nowtime_old = 0;
		}
		if(nowtime_old == 0){
			nowtime_old = Nowtime;
		}
		fpscount++;
	}
	public long GetFps(){
		return fps;
	}
	// ディスプレイリストを吐き出し
	public static void SyncRedraw(Canvas canvas){
		float magnification = (float)canvas.getWidth() / screenX;
		float magnification2 = (float)canvas.getHeight() / screenY;
		if(magnification > magnification2)
			magnification = magnification2;
		canvas.save();
		canvas.clipRect(0, 0, screenX * magnification, screenY * magnification);
		canvas.scale(magnification, magnification);
		int count = 0;
		for(DispCommand command : dispList){
			if(count >= dispNum)
				break;
			switch(command.type){
			case CMDTYPE_DRAW:
				canvas.drawBitmap(spImage[ command.value ], command.src, command.dest, paint);
				break;
			case CMDTYPE_SETALPHA:
				paint.setAlpha( command.value );
				break;
			}
			count++;
		}
		canvas.restore();
		ClearDisplayList();
	}
}
