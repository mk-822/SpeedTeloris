package com.mk.SpeedTeloris;

import java.util.HashMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

/*
 * Created on 2006/11/05
 */

public class MkWave{
    // 登録できるWAVEファイルの最大数
    static private int maxClips;
    // WAVEファイルデータ（名前->データ本体）
	static private HashMap<String, Integer> clipMap;
    // 登録されたWAVEファイル数
    static private int counter = 0;
    
    static private SoundPool pool;
    static private Context ctx;

    /**
     * コンストラクタ
     */
    public MkWave() {}

    /**
     * コンストラクタ
     * @param context 
     * 
     * @param maxClips
     *            登録できるWAVEファイルの最大数
     * @return 
     */
    public static void Initialize(Context context, int MaxClips) {
        maxClips = MaxClips;
        clipMap = new HashMap<String, Integer>(maxClips);
        pool = new SoundPool(maxClips, AudioManager.STREAM_MUSIC, 0);
        ctx = context;
    }

    /**
     * WAVEファイルをロード
     * @param name 登録名
     * @param filename ファイル名
     */
	public void load(String name, String filename) {
        if (counter == maxClips) {
            System.out.println("エラー: これ以上登録できません");
            return;
        }

        try {
        	// 読み込む
        	int id = pool.load(filename, 1);
        	
            // 登録
            clipMap.put(name, id);
        }finally{
        	
        }
    }

    /**
     * 再生
     * @param name 登録名
     */
    public void play(String name) {
        // 名前に対応するクリップを取得
        Object idObj = clipMap.get(name);
        int id = (Integer) idObj;
        if (idObj != null) {
        	pool.play(id, 1.0f, 1.0f, 0, 0, 1.0f);
        }
    }

	public void load(String name, int asset) {
        if (counter == maxClips) {
            System.out.println("エラー: これ以上登録できません");
            return;
        }

        try {
        	// 読み込む
        	int id = pool.load(ctx, asset, 0);
        	
            // 登録
            clipMap.put(name, id);
        }finally{
        	
        }
	}
}
