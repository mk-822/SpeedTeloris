package com.mk.SpeedTeloris;

import java.text.DecimalFormat;

public class ETetrisController extends MkObject {
	private static final int BLOCK_TYPE_MAX = 8;
	int Field[][] = new int[14][24];
	TetrisData data = new TetrisData();
	int nextX[] = new int[4];
	int nextY[] = new int[4];
	int centerX = 0;
	int centerY = 0;
	int phase = 20;
	int phaseCount = 0;
	int spinState = 0;
	int blockType = 0;
	boolean visibleCurrent = false;
	boolean currentFlash = false;
	int nextType2 = MkRandom.Gen(7);
	int nextType = MkRandom.Gen(7);
	int blockToStone = 0;
	int score = 0;
	int drop = 0;
	int level = 0;
	int combo = 0;
	int dropCountSpeed = data.dropCountSpeed[level];
	int dropSpeed = data.dropSpeed[level];
	int dropCount = 0;
	boolean useHold = false;
	int holdedType = -1;
	MkWave wave = new MkWave();
	
	public ETetrisController() {
		img.priority = 3;
		
		for(int i=0 ; i<14 ; i++){
			for(int j=0 ; j<24 ; j++){
				Field[i][j] = 0;
			}
		}
		
		for(int i=0 ; i<14 ; i++){
			Field[i][22] = 8;
		}
		
		for(int i=0 ; i<24 ; i++){
			Field[1][i] = 8;
			Field[12][i] = 8;
		}
	}
	public void main() {
		switch(phase){
		case 0:	// ネクスト生成
			GenerateNext();
			wave.play("bl"+(nextType+1));
			if(MkInput.button[0]>0){
				MkInput.button[0]++;
				wave.play("irs");
				doSpin(-1);
			}
			if(MkInput.button[1]>0){
				MkInput.button[1]++;
				wave.play("irs");
				doSpin(1);
			}
			if(!BlockMove(0,0) && !BlockMove(0,-1)){	// げーむおーばーかどうか
				BlockFix();
				phase = 10;
				phaseCount = 180;
				visibleCurrent = true;
				break;
			}
			phase++;
			phaseCount = 0;
			dropCount = 0;
			visibleCurrent = true;
			// あえて break を入れていません
		case 1:	// 自由操作
			if(MkInput.button[2]>0){
				if(!useHold){
					doHold();
				}
			}
			if(dropCount > 0){
				if(MkInput.button[0]==1){
					wave.play("mov");
					BlockSpin(-1);
				}
				if(MkInput.button[1]==1){
					wave.play("mov");
					BlockSpin(1);
				}
				if((MkInput.left==1)||(MkInput.left>=16)){
					BlockMove(-1,0);
				}
				if((MkInput.right==1)||(MkInput.right>=16)){
					BlockMove(1,0);
				}
			}
			dropCount++;

			if(MkInput.up>0){
				score+=40;
				for(int i=0 ; i<20 ; i++){
					BlockMove(0,1);
				}
			}
			if(MkInput.down>0){
				score+=5;
				BlockMove(0,1);
			}
			if(dropCount%dropCountSpeed==0){
				for(int i=0 ; i<data.dropSpeed[level] ; i++){
					BlockMove(0,1);
				}
			}
			if(BlockMove(0,1)){
				BlockMove(0,-1);
			}else{
				wave.play("set");
				phase = 2;
				phaseCount = 0;
			}
			break;
		case 2: // 接地中
			phaseCount++;
			if(MkInput.button[0]==1){
				wave.play("mov");
				BlockSpin(-1);
			}
			if(MkInput.button[1]==1){
				wave.play("mov");
				BlockSpin(1);
			}
			if(MkInput.button[2]==1){
				if(!useHold){
					doHold();
					break;
				}
			}
			if((MkInput.left==1)||(MkInput.left>=16)){
				BlockMove(-1,0);
			}
			if((MkInput.right==1)||(MkInput.right>=16)){
				BlockMove(1,0);
			}
			if(BlockMove(0,1)){
				BlockMove(0,-1);
				phase--;
				phaseCount = 0;
				if(dropCount%dropCountSpeed==0){
					for(int i=0 ; i<data.dropSpeed[level] ; i++){
						BlockMove(0,1);
					}
				}
				break;
			}
			if((MkInput.down>0)||(phaseCount%(data.fixSpeed[level]+dropCountSpeed*2)==0)){
				FixProc();
				phase++;
				phaseCount = 0;
				break;
			}
			break;
		case 3:	// 消去処理
			phase++;
			phaseCount = 20;
			if((common_val[ERASE_LINE] = JudgeErase())>0){
				wave.play("ers");
				phaseCount += 10;
				combo+=1;
				MkObjectController.Generate("EEraseEffectText", 160, 168);
				switch(common_val[ERASE_LINE]){	// スコア加算
				case 1:
					score += 100 + combo*100;
					break;
				case 2:
					score += 250 + combo*150;
					break;
				case 3:
					score += 500 + combo*250;
					break;
				case 4:
					score += 1000 + combo*500;
					break;
				}
				if(combo >= 2){
					if(common_val[ERASE_LINE] == 4){
						wave.play("app");
					}
					common_val[COMBO_COUNT] = combo;
					MkObjectController.Generate("EComboEffectText", 160, 176);
				}
				if(JudgeAllClear()){
					score += 10000;
					MkObjectController.Generate("EAllClearEffectText", 136, 184);
				}
			}else{
				combo = 0;
			}
			break;
		case 4:	// うぇいと
			phaseCount--;
			if(phaseCount<0){
				blockErase();
				phase = 0;
				phaseCount = 0;
			}
			break;
		case 10:	//げーむおーばー
			phaseCount--;
			if(phaseCount%30 == 0){
				visibleCurrent = !visibleCurrent;
			}
			if(phaseCount<0){
				visibleCurrent = false;
				phase++;
				phaseCount = 0;
				blockToStone = 0;
				MkObjectController.Generate("EGameOverString", 32, 108);
			}
			break;
		case 11:	// 下から石化
			phaseCount++;
			if(phaseCount%6 == 0){
				for(int i=2 ; i<12 ; i++){
					if( Field[i][20-blockToStone+2] > 0){
						Field[i][20-blockToStone+2] = 8;
					}
				}
				blockToStone++;
			}
			if(blockToStone>20){
				phase++;
				phaseCount = 0;
			}
			break;
		case 12:	// 待機
			phaseCount++;
			if(phaseCount > 120){
				hp=0;
				MkObjectController.GenerateEvent("ETetrisController");
			}
			break;
		case 20:	// ゲーム開始地点
			phase++;
			phaseCount = 0;
			MkObjectController.Generate("EDifficultySelect", 48, 80);
		case 21:	// 難易度選択
			if(common_val[SELECTED_LEVEL] != 0){
				level = common_val[SELECTED_LEVEL];
				common_val[SELECTED_LEVEL] = 0;
				dropCountSpeed = data.dropCountSpeed[level];
				dropSpeed = data.dropSpeed[level];
				phase++;
				phaseCount = 0;
				MkObjectController.Generate("EGameStartString", 32, 108);
			}
			break;
		case 22:	// ゲームスタート
			phaseCount++;
			if(phaseCount>120){
				phase = 0;
				phaseCount = 0;
			}
			break;
		case 30: // HOLD の後始末用
			if(!BlockMove(0,0) && !BlockMove(0,-1)){	// げーむおーばーかどうか
				BlockFix();
				phase = 10;
				phaseCount = 180;
				visibleCurrent = true;
				break;
			}
			phase = 1;
			phaseCount = 0;
			dropCount = 0;
			break;
		}
	}
	
	private void doHold() {
		wave.play("hld");
		if(holdedType == -1){
			holdedType = blockType;
			phase = 0;
			phaseCount = 0;
		}else{
			int tmp = blockType;
			blockType = holdedType;
			holdedType = tmp;
			centerX = 6;
			centerY = 3;
			spinState = 0;
			for(int i=0 ; i<4 ; i++){
				nextX[i] = data.nextX[blockType][i];
				nextY[i] = data.nextY[blockType][i];
				nextX[i] -= data.adjustX[blockType][spinState];
				nextY[i] -= data.adjustY[blockType][spinState];
			}
			
			if(MkInput.button[0]>0){
				MkInput.button[0]++;
				doSpin(-1);
			}
			if(MkInput.button[1]>0){
				MkInput.button[1]++;
				doSpin(1);
			}
			phase = 30;
		}
		useHold = true;
	}

	private void FixProc() {
		wave.play("fix");
		BlockFix();
		drop++;
		if(drop%25 == 0){
			level++;
			common_val[LEVEL_COUNT]=level;
			if(level > 30){
				level = 30;
			}
			dropCountSpeed = data.dropCountSpeed[level];
			dropSpeed = data.dropSpeed[level];
		}
		currentFlash = true;
		visibleCurrent = false;
		useHold = false;
	}
	private boolean BlockSpin(int spin) {
		doSpin(spin);
		if(!BlockMove(0,0)){
			if(!BlockMove(1,0)){
				if(!BlockMove(-1,0)){
					doSpin(-spin);
					return false;
				}	
			}			
		}
		
		return true;
	}

	private void doSpin(int spin){
		for(int i=0 ; i<4 ; i++){
			nextX[i] -= data.adjustX[blockType][spinState];
			nextY[i] -= data.adjustY[blockType][spinState];
		}
		spinState = (spinState + 4 + spin)%4;
		
		switch(spin){
		case -1:
			for(int i=0 ; i<4 ; i++){
				int x = nextY[i] - centerY + centerX + data.adjustX[blockType][spinState];
				int y = ((nextX[i] - centerX) * -1) + centerY + data.adjustY[blockType][spinState];
				nextX[i] = x;
				nextY[i] = y;
			}
			break;
		case 1:
			for(int i=0 ; i<4 ; i++){
				int x = ((nextY[i] - centerY) * -1 ) + centerX + data.adjustX[blockType][spinState];
				int y = nextX[i] - centerX + centerY + data.adjustY[blockType][spinState];
				nextX[i] = x;
				nextY[i] = y;
			}
			break;
		}
	}
	
	private boolean JudgeAllClear() {
		int count = 0;
		for(int j=2 ; j<22 ; j++){
			for(int i=2 ; i<12 ; i++){
				if(Field[i][j] > 0 && Field[i][j] <= BLOCK_TYPE_MAX){
					count++;
				}
			}
		}
		if(count == 0){
			return true;
		}else{
			return false;
		}
	}
	
	private int JudgeErase() {
		int eraseCount=0;
		for(int j=2 ; j<22 ; j++){
			int ok=0;
			for(int i=2 ; i<12 ; i++){
				if(Field[i][j]>0)
					ok++;
			}
			if(ok==10){
				eraseCount++;
				for(int i=2 ; i<12 ; i++){
					common_val[EFFECT_COLOR] = Field[i][j]-1;
					common_val[ERASE_BLOCK_POS_X] = i-2;
					common_val[ERASE_BLOCK_POS_Y] = j-2;
					Field[i][j] += BLOCK_TYPE_MAX;
					MkObjectController.Generate("EBlockBreakEffect", 40+(i-2)*8, 52+(j-2)*8);
				}
			}
		}
		return eraseCount;
	}

	private void blockErase() {
		int okCount = 0;
		for(int j=0 ; j<22 ; j++){
			int ok=0;
			for(int i=2 ; i<12 ; i++){
				if(Field[i][j]>BLOCK_TYPE_MAX || j==0)
					ok++;
			}
			if(ok==10){
				okCount++;
				for( ; j>1 ; j--){
					for(int i=2 ; i<12 ; i++){
						Field[i][j] = Field[i][j-1];
					}
				}
			}
		}
		if(okCount >= 2){
			wave.play("fal");
		}
	}

	private void BlockFix() {
		for(int i=0 ; i<4 ; i++){
			Field[ nextX[i] ][ nextY[i] ] = blockType+1;
		}			
	}
	
	private boolean BlockMove(int x, int y) {
		for(int i=0 ; i<4 ; i++){
			if(nextX[i]+x < 0 || nextX[i]+x >= 14
					|| nextY[i]+y >= 24){
				return false;
			}
			if(Field[ nextX[i]+x ][ nextY[i]+y ]>0){
				return false;
			}
		}
		for(int i=0 ; i<4 ; i++){
			 nextX[i]+=x;
			 nextY[i]+=y;
		}
		centerX +=x;
		centerY +=y;
		return true;
	}

	private void GenerateNext() {
		centerX = 6;
		centerY = 3;
		spinState = 0;
		blockType = nextType;
		nextType = nextType2;
		nextType2 = MkRandom.Gen(7);
		for(int i=0 ; i<4 ; i++){
			nextX[i] = data.nextX[blockType][i];
			nextY[i] = data.nextY[blockType][i];
			nextX[i] -= data.adjustX[blockType][spinState];
			nextY[i] -= data.adjustY[blockType][spinState];
		}
	}
	public void draw() {
		draw.DrawImage(1, 0, 0, 240, 240, 80, 0, 240, 240);	// 影描画
		
		// 特殊パーツ描画
		draw.DrawImage(0, 96, 8, 32, 8, 0, 48, 32, 8);	// NEXT
		draw.DrawImage(0, 32, 8, 32, 8, 0, 56, 32, 8);	// HOLD
		
		// ネクスト描画
		if(holdedType != -1){
			draw.DrawImage(0, 32, 24, 24, 16, holdedType*24, 216, 24, 16);	// HOLD
		}
		draw.DrawImage(0, 64, 16, 32, 24, nextType*32, 232, 32, 24);	// NEXT
		draw.DrawImage(0, 100, 24, 24, 16, nextType2*24, 216, 24, 16);	// NEXT2
		
		// 数値描画
		draw.DrawImage(0, 140, 56, 40, 8, 0, 24, 40, 8);	// SCORE_TEXT
		DecimalFormat df = new DecimalFormat();
		df.applyLocalizedPattern("0000000");
		for(int i=0 ; i<7 ; i++){
			int num = Integer.parseInt( df.format(score).substring(i,i+1) );
			draw.DrawImage(0, 156+i*8, 68, 8, 8, num*8, 0, 8, 8);	// SCORE
		}

		draw.DrawImage(0, 140, 96, 32, 8, 0, 32, 32, 8);	// DROP_TEXT
		df.applyLocalizedPattern("0000000");
		for(int i=0 ; i<7 ; i++){
			int num = Integer.parseInt( df.format(drop).substring(i,i+1) );
			draw.DrawImage(0, 156+i*8, 108, 8, 8, num*8, 8, 8, 8);	// DROP
		}
		
		draw.DrawImage(0, 140, 136, 40, 8, 0, 40, 40, 8);	// LEVEL_TEXT
		df.applyLocalizedPattern("000");
		for(int i=0 ; i<3 ; i++){
			int num = Integer.parseInt( df.format(level).substring(i,i+1) );
			draw.DrawImage(0, 188+i*8, 148, 8, 8, num*8, 16, 8, 8);	// LEVEL
		}
		// **処理落ち防止
		/*for(int i=0 ; i<10 ; i++){	// フィールド枠描画
			for(int j=0 ; j<20 ; j++){
				if(Field[i+2][j+2]>0 && Field[i+2][j+2]<=BLOCK_TYPE_MAX){
					draw.DrawImage(0, 36 + i*8, 48 + j*8, 16, 16, 80, 16, 16, 16);
				}
			}
		}*/
		for(int i=0 ; i<10 ; i++){	// フィールド描画
			for(int j=0 ; j<20 ; j++){
				if(Field[i+2][j+2]>0 && Field[i+2][j+2]<=BLOCK_TYPE_MAX){
					draw.DrawImage(0, 40 + i*8, 52 + j*8, 8, 8, 96+(Field[i+2][j+2]-1)*8, 16, 8, 8);
				}
			}
		}
		
		draw.DrawImage(0, 32, 44, 96, 176, 160, 0, 96, 176);// 枠描画

		if(visibleCurrent){
			if(count/2 % 2 == 0){
				for(int i=0 ; i<4 ; i++){	// カレント点滅
					if((nextX[i]-2 >= 0)&&
						(nextX[i]-2 < 10)&&
						(nextY[i]-2 >= 0)&&
						(nextY[i]-2 < 20)
					){
						draw.DrawImage(0, 36 + (nextX[i]-2)*8, 48 + (nextY[i]-2)*8, 16, 16, 80, 0, 16, 16);
					}
				}
			}
			for(int i=0 ; i<4 ; i++){	// カレント描画
				if((nextX[i]-2 >= 0)&&
					(nextX[i]-2 < 10)&&
					(nextY[i]-2 >= 0)&&
					(nextY[i]-2 < 20)
				){
					draw.DrawImage(0, 40 + (nextX[i]-2)*8, 52 + (nextY[i]-2)*8, 8, 8, 96+blockType*8, 0, 8, 8);
				}
			}
			{	// ゴースト描画
				int j=0;
				for(; j<20 ; j++){
					if(!BlockMove(0,1)){
						break;
					}
				}
				draw.SetTransparency(0.5f);
				for(int i=0 ; i<4 ; i++){	// カレント描画
					if((nextX[i]-2 >= 0)&&
						(nextX[i]-2 < 10)&&
						(nextY[i]-2 >= 0)&&
						(nextY[i]-2 < 20)
					){
						draw.DrawImage(0, 40 + (nextX[i]-2)*8, 52 + (nextY[i]-2)*8, 8, 8, 96+blockType*8, 0, 8, 8);
					}
				}
				draw.SetTransparency(1f);
				for(; j>0 ; j--){
					BlockMove(0,-1);
				}
			}
		}
		if(currentFlash){	//カレントフラッシュ
			for(int i=0 ; i<4 ; i++){	// カレント描画
				if((nextX[i]-2 >= 0)&&
					(nextX[i]-2 < 10)&&
					(nextY[i]-2 >= 0)&&
					(nextY[i]-2 < 20)
				){
					draw.DrawImage(0, 40 + (nextX[i]-2)*8, 52 + (nextY[i]-2)*8, 8, 8, 96+blockType*8, 8, 8, 8);
				}
			}
			currentFlash = false;
		}
	}
}
