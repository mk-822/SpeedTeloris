package com.mk.SpeedTeloris;

import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.SurfaceView;
import android.view.Window;

public class TeloMain extends Activity {
	MySurfaceView view;
	Thread mainLoop;
	
	class MySurfaceView extends SurfaceView implements Runnable {
		Bitmap bitmap;
		boolean u, d, l, r;
		int left, top;
		long time;
		int tx, ty;
		MkGame main;
		MkInput key;

		public MySurfaceView(Context context) {
			super(context);
			setFocusable(true); // キーイベントを使うために必須
			requestFocus(); // フォーカスを当てないとキーイベントを拾わない
			@SuppressWarnings("unused")
			MkDraw draw = new MkDraw(240, 240, context);
			MkWave.Initialize(context, 256);
			MkMidi.Initialize(context, 256);
			main = new MkGame();
			key = new MkInput();
			mainLoop = new Thread(this);
			mainLoop.start();
		}

		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			key.keyPressed(keyCode);
			return true;
		}
		
		@Override
		public boolean onKeyUp(int keyCode, KeyEvent event) {
			key.keyReleased(keyCode);
			return true;
		}
		
		void doDraw() {
			Canvas canvas = getHolder().lockCanvas();
			if (canvas != null) {
				MkDraw.SyncRedraw(canvas);
				getHolder().unlockCanvasAndPost(canvas);
			}
		}

		@Override
		public void run() {
			while (true) {
				Date d = new Date();
				MkDraw.FpsCount(d.getTime());
				main.Main();
				
				doDraw();
			}
		}
	}
	
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// タイトルバーを消す
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        // ステータスバーを消す
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        // ビューを載せる
		view = new MySurfaceView(this);
		setContentView(view);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Thread.interrupted();
	}
}
