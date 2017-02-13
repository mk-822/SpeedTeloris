package com.mk.SpeedTeloris;

public class TetrisData {
	final int TYPE_MAX = 7;
	final int CENTER_X = 6;
	final int CENTER_Y = 3;
	int nextX[][] = new int[TYPE_MAX][4];
	int nextY[][] = new int[TYPE_MAX][4];
	int adjustX[][] = new int[TYPE_MAX][4];
	int adjustY[][] = new int[TYPE_MAX][4];
	int dropCountSpeed[] = {200, 120, 80, 60, 40, 50, 30, 20, 10, 5, 15, 3, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
	int dropSpeed[] = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 4, 1, 4, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};
	int fixSpeed[] = {30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 28, 28, 28, 26, 26, 26, 22, 22, 15};
	
	public TetrisData() {
		//******************ネクストデータの初期化********************
		//		 T
		nextX[0][0] = 5; nextY[0][0] = 2;
		nextX[0][1] = 6; nextY[0][1] = 2;
		nextX[0][2] = 7; nextY[0][2] = 2;
		nextX[0][3] = 6; nextY[0][3] = 3;
		//		 I
		nextX[1][0] = 5; nextY[1][0] = 3;
		nextX[1][1] = 6; nextY[1][1] = 3;
		nextX[1][2] = 7; nextY[1][2] = 3;
		nextX[1][3] = 8; nextY[1][3] = 3;
		//		 S
		nextX[2][0] = 5; nextY[2][0] = 3;
		nextX[2][1] = 6; nextY[2][1] = 3;
		nextX[2][2] = 6; nextY[2][2] = 2;
		nextX[2][3] = 7; nextY[2][3] = 2;
		//		 Z
		nextX[3][0] = 5; nextY[3][0] = 2;
		nextX[3][1] = 6; nextY[3][1] = 2;
		nextX[3][2] = 6; nextY[3][2] = 3;
		nextX[3][3] = 7; nextY[3][3] = 3;
		//		 J
		nextX[4][0] = 5; nextY[4][0] = 2;
		nextX[4][1] = 6; nextY[4][1] = 2;
		nextX[4][2] = 7; nextY[4][2] = 2;
		nextX[4][3] = 7; nextY[4][3] = 3;
		//		 L
		nextX[5][0] = 5; nextY[5][0] = 2;
		nextX[5][1] = 6; nextY[5][1] = 2;
		nextX[5][2] = 7; nextY[5][2] = 2;
		nextX[5][3] = 5; nextY[5][3] = 3;
		//		 O
		nextX[6][0] = 6; nextY[6][0] = 3;
		nextX[6][1] = 7; nextY[6][1] = 3;
		nextX[6][2] = 6; nextY[6][2] = 2;
		nextX[6][3] = 7; nextY[6][3] = 2;
		//******************補正値の初期化********************
		//		 T
		adjustX[0][0] = 0; adjustY[0][0] = 0;
		adjustX[0][1] = -1; adjustY[0][1] = -1;
		adjustX[0][2] = 0; adjustY[0][2] = -1;
		adjustX[0][3] = 1; adjustY[0][3] = -1;
		//		 I
		adjustX[1][0] = 0; adjustY[1][0] = 0;
		adjustX[1][1] = 1; adjustY[1][1] = 0;
		adjustX[1][2] = 1; adjustY[1][2] = 0;
		adjustX[1][3] = 1; adjustY[1][3] = 1;
		//		 S
		adjustX[2][0] = 0; adjustY[2][0] = 0;
		adjustX[2][1] = -1; adjustY[2][1] = -1;
		adjustX[2][2] = 0; adjustY[2][2] = -1;
		adjustX[2][3] = 0; adjustY[2][3] = -1;
		//		 Z
		adjustX[3][0] = 0; adjustY[3][0] = 0;
		adjustX[3][1] = 0; adjustY[3][1] = -1;
		adjustX[3][2] = 0; adjustY[3][2] = -1;
		adjustX[3][3] = 1; adjustY[3][3] = -1;
		//		 J
		adjustX[4][0] = 0; adjustY[4][0] = 0;
		adjustX[4][1] = -1; adjustY[4][1] = -1;
		adjustX[4][2] = 0; adjustY[4][2] = -1;
		adjustX[4][3] = 1; adjustY[4][3] = -1;
		//		 L
		adjustX[5][0] = 0; adjustY[5][0] = 0;
		adjustX[5][1] = -1; adjustY[5][1] = -1;
		adjustX[5][2] = 0; adjustY[5][2] = -1;
		adjustX[5][3] = 1; adjustY[5][3] = -1;
		//		 O
		adjustX[6][0] = 0; adjustY[6][0] = 0;
		adjustX[6][1] = 0; adjustY[6][1] = -1;
		adjustX[6][2] = 1; adjustY[6][2] = -1;
		adjustX[6][3] = 1; adjustY[6][3] = 0;
	}
}
