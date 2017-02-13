package com.mk.SpeedTeloris;

import android.content.Context;

/*
 * Created on 2006/11/05
 */

public class MkMidi{
/*
    // 登録できるMIDIファイルの最大数
    static private int maxSequences;
    // 登録されているMIDIファイル数
    @SuppressWarnings("unused")
	static private int counter = 0;
*/
    // 現在再生中のMIDIシーケンス名
    static String currentSequenceName = "";

    /**
     * コンストラクタ
     */
    public MkMidi() {}

    /**
     * コンストラクタ
     * @param context 
     * 
     * @param maxSequences
     *            登録できるMIDIファイルの最大数
     * @return 
     */
    public static void Initialize(Context context, int MaxSequences) {
        // シーケンサーとシンセサイザーを初期化
        initSequencer();
    }

    private static void initSequencer() {
    }

    /**
     * MIDIファイルをロード
     * 
     * @param name
     *            登録名
     * @param filename
     *            ファイル名
     */
	public void load(String name, String filename) {
    }

    /**
     * 再生
     * 
     * @param name
     *            登録名
     */
    public void play(String name) {
    }

    /**
     * 停止
     */
    public void stop() {
    }

    /**
     * 終了処理
     */
    public void close() {
    }
}
