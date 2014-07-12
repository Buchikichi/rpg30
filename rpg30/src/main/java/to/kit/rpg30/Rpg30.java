package to.kit.rpg30;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * RPG30.
 * @author S.Kanai
 * @author ponta
 */
public final class Rpg30 extends JFrame implements KeyListener {
	/** 仮想スクリーン. */
	private VirtualScreen scr = new VirtualScreen();
	/** マップ. */
	private MapManager map = new MapManager();
	/** 最後に押したキー. */
	private int lastKey;
	/** ステータス. */
	private Status sts = new Status();
	/** 戦闘情報. */
	private Battle btl = new Battle();
	/** 日記. */
	private Niki niki = new Niki();

	private int inkey$() {
		int result;

		repaint();
		for (;;) {
			if (0 < this.lastKey) {
				result = this.lastKey;
				this.lastKey = 0;
				break;
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// 無視
			}
		}
		return result;
	}

	private int inkey$(int ... accepts) {
		int key;

		OUTER:
		for (;;) {
			key = inkey$();
			for (int i : accepts) {
				if (key == i) {
					break OUTER;
				}
			}
		}
		return key;
	}

	/**
	 * 処理開始.
	 * @throws Exception 例外
	 */
	public void execute() throws Exception {
//	10000 'SAVE "B:\SOURCE\BAS\RPG\RPG30",A
//	10005 'CHDIR "\SOURCE\BAS\RPG"
//	10040 *RETRY
//	10045  CLEAR &H0,&H50,&H4FFF,&H0
//	10055 *MAIN
//	10060  GOSUB *WARIKOMI.
		warikomi();
//	10065  GOSUB *SYOKI.
		syoki();
//	10070  GOSUB *TEISUU.
		teisuu();
//	10075  GOSUB *DIM.
		dim();
//	10080  GOSUB *TAITOL.
		taitol();
//	10085  GOSUB *GAMENF.
		gamenf();
//	10090  GOSUB *FANC.
		func();
//	10095  GOSUB *NEDAN.
		nedan();
		for (;;) {
//	10100  GOSUB *HAJIMARI.
			hajimari();
//	10105  GOSUB *GAME.
			game();
		}
//	10110 END
	}

	/**
	 * 10120 *WARIKOMI.
	 */
	private void warikomi() {
//	10135  KEY  OFF
//	10140 'ON ERROR GOTO      *ERROR.Y
//	10150  ON KEY GOSUB *KEY1.Y,*KEY2.Y,*KEY3.Y,*KEY4.Y,*KEY5.Y,*KEY6.Y,*KEY7.Y,*KEY8.Y,*KEY9.Y,*KEY10.Y
//	10155 RETURN
		// 戦う, 逃げる, 魔法, 調べる, 話す
//		// 昇る, 降りる, 入る, 買う, 道具
	}

	private void syoki() {
//	10165 *SYOKI.
//	10170  CLOSE
//	10175  CLS 3
//	10180  WIDTH   80,25
//	10185  SCREEN   3, 0,0,1
	/*
	 * screen 画面モード,画面スイッチ,アクティブページ,ディスプレイページ
	 * 3:高分解能カラーモード(640x400)
	 * 0:表示
	 * 0:ページ1
	 * 1:ページ1のみ表示
	 */
//	10190  CONSOLE 20,24,0,1
	/*
	 * CONSOLE スクロール開始行, スクロール行数, ファンクションキー表示スイッチ, カラー/白黒スイッチ
	 */
		this.scr.console(20, 24);
//	10195  COLOR    7, 0,1,7,2
	/*
	 * COLOR ファンクションコード,バックグラウンドカラー,ボーダーカラー,フォアグラウンドカラー,パレットモード
	 * パレットモード=2:4096色中16色モード
	 */
//	10200 RETURN
		addKeyListener(this);
		setFocusable(true);
		setSize(640, 480);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		Graphics g = getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	private void teisuu() {
//	10210 *TEISUU.
//	10215  DEFINT A-Z
//	10220  NM=VAL(MID$(TIME$,4,2))
//	10225  NS=VAL(MID$(TIME$,7,2))
//	10230  NR=NM*60+NS
//	10235  RANDOMIZE NR
//	10240  VSYC =0
//	10245  OX=0
//	10250  OY=0
//	10255  EX=0
//	10260  EY=0
//	10265  WC=0
//	10270 RETURN
	}

	private void dim() {
//	10280 *DIM.
//	10285  OPTION BASE 0
//	10290  DIM MAC(315,67),MAP.!(8,8)
//	10295  DIM SHA$(5),HEA$(5),ARA$(5),TAA$(5)        ,NEDAN(2,5)
//	10300  DIM G2(314),G4(314),G6(314),G8(314),ITM$(1),TEKI(21243)
//	10305 RETURN
	}

//	10315 *ERROR.Y
//	10320  IF ERR=62 THEN GOTO *DISK.IF
//	10325  IF ERR=7  THEN GOTO *MEMORY.IF
//	10330  LOCATE 4,24
//	10335  PRINT "ＥＲＲＯＲが発生しました。"
//	10340  PRINT "ＤＡＴＡをＳＡＶＥします。"
//	10345  PRINT "ＨＩＴ　ＡＮＹ　ＫＥＹ　！"
//	10350  CLOSE
//	10355  GOSUB *ANY.M
//	10360  GOSUB *PRINT.M
//	10365  GOSUB *SAVE.M
//	10370 RESUME *RETRY
//	10380 *DISK.IF
//	10385  LOCATE 4,24
//	10390  PRINT "ＤＩＳＫを入れて下さい。"
//	10395  PRINT "ＨＩＴ　ＡＮＹ　ＫＥＹ　！"
//	10400  GOSUB *ANY.M
//	10405  GOSUB *PRINT.M
//	10410 RESUME
//	10420 *MEMORY.IF
//	10425  LOCATE 4,24
//	10430  PRINT "ＭＥＭＯＲＹが足りません。"
//	10435 STOP

	/**
	 * 戦う.
	 * 10465 *KEY1.Y.
	 * @throws IOException 入出力例外
	 */
	private void key1y(Enemy enemy) throws IOException {
//	10470  KEY OFF
//	10475  IF C=1 THEN GOSUB *JIATA.M:GOTO *KEY1.IF
		if (this.btl.getC() == 1) {
			jiataM(enemy);
			return;
		}
		int rnd = (int) (Math.random() * 10);
//	10480  IF INT(RND(1)*10)<5 THEN GOSUB *JIATA.M ELSE GOSUB *TEKIATA.M
		if (rnd < 5) {
			jiataM(enemy);
		} else {
			tekiataM(enemy);
		}
//	10485 *KEY1.IF
//	10490  IF TDA=1 THEN GOSUB *CFKEY.M ELSE GOSUB *KEYON.M
//	10495 RETURN
	}

	/**
	 * 逃げる.
	 * 10505 *KEY2.Y.
	 * @throws IOException 入出力例外
	 */
	private void key2y(Enemy enemy) throws IOException {
//	10510  KEY OFF
//	10515  A=4
		int a = 4;
//	10520  IF HP>THP THEN A=7
		if (this.sts.getHp() > enemy.getThp()) {
			a = 7;
		}
//	10525  IF TGI1=1 OR TGI3=1 OR TGI4=1 OR TGI5=1 THEN GOTO *KEY2.IF
		int tgi1 = this.map.getTgi1();
		int tgi3 = this.map.getTgi3();
		int tgi4 = this.map.getTgi4();
		int tgi5 = this.map.getTgi5();
		if (tgi1 == 1 || tgi3 == 1 || tgi4 == 1 || tgi5 == 1) {
			key2if(enemy);
			return;
		}
//	10530  IF C=1                                  THEN GOTO *ATAEND.4
		if (this.btl.getC() == 1) {
			ataend4();
		}
//	10535  IF INT(RND(1)*10)<A                     THEN GOTO *ATAEND.4
		int rnd = (int) Math.random() * 10;
		if (rnd < a) {
			ataend4();
		}
//	10540  IF NIGE=1                               THEN GOTO *ATAEND.4
		if (this.btl.isNige()) {
			ataend4();
		}
	}

	/**
	 * 10545 *KEY2.IF.
	 * @param enemy 敵情報
	 * @throws IOException 入出力例外
	 */
	private void key2if(Enemy enemy) throws IOException {
//	10550  LOCATE 4,24
		this.scr.locate(4, 24);
//	10555  PRINT "だめだ！敵に追い付かれてしまつた。"
		this.scr.print("だめだ！敵に追い付かれてしまつた。");
//	10560     C=2
		this.btl.setC(2);
//	10565  NIGE=0
		this.btl.setNige(false);
//	10570  GOSUB *TEKIATA.M
		tekiataM(enemy);
//	10575  GOSUB *CFKEY.M
//	10580 RETURN
	}

	/**
	 * 魔法.
	 * 10590 *KEY3.Y.
	 * @param enemy 敵情報
	 * @throws IOException 入出力例外
	 */
	private void key3y(Enemy enemy) throws IOException {
//	10595  IF NOT MGK=1 THEN RETURN
		if (this.sts.getMgk() != 1) {
			return;
		}
//	10600  KEY OFF
//	10605  RESTORE *KEY3.D
		List<Integer> keyList = new ArrayList<>();
		int qmp = this.sts.getQmp();
//	10610  FOR L=1 TO QMP
		for (int l = 1; l <= qmp; l++) {
//	10615   READ A$
//	10620   LOCATE 4,24
			this.scr.locate(4, 24);
//	10625   PRINT A$
			this.scr.print(KEY3_D[l - 1]);
//	10630  NEXT L
			keyList.add(Integer.valueOf(KeyEvent.VK_0 + l));
			keyList.add(Integer.valueOf(KeyEvent.VK_NUMPAD0 + l));
		}
		keyList.add(Integer.valueOf(KeyEvent.VK_0));
		keyList.add(Integer.valueOf(KeyEvent.VK_NUMPAD0));
//	10631  WHILE NOT B$=""
//	10632   B$=INKEY$
//	10633  WEND
//	10640  WHILE QMP<VAL(B$) OR VAL(B$)<1
//	10645   B$=INKEY$
//	10650  WEND
		Integer[] keys = keyList.toArray(new Integer[keyList.size()]);
		int b$ = inkey$(ArrayUtils.toPrimitive(keys));
//	10655  ON INT(VAL(B$)) GOSUB *KEY31.3,*KEY32.3,*KEY33.3,*KEY34.3
		if (b$ == KeyEvent.VK_1 || b$ == KeyEvent.VK_NUMPAD1) {
			key313(enemy);
		} else if (b$ == KeyEvent.VK_2 || b$ == KeyEvent.VK_NUMPAD2) {
			key323(enemy);
		} else if (b$ == KeyEvent.VK_3 || b$ == KeyEvent.VK_NUMPAD3) {
			key333(enemy);
		} else if (b$ == KeyEvent.VK_4 || b$ == KeyEvent.VK_NUMPAD4) {
			key343(enemy);
		} else {
			printM();
		}
//	10660  GOSUB *PRINT.M
//	10670  IF TDA=1 THEN GOSUB *CFKEY.M ELSE GOSUB *KEYON.M
//	10675 RETURN
	}
	private static final String[] KEY3_D = {
//	10685 *KEY3.D
//	10690  DATA 元気になぁーれ！（　５）．．．Ｐｕｓｈ［１］
//	10695  DATA ドドンガドン　！（１０）．．．Ｐｕｓｈ［２］
//	10700  DATA 元気いっぱい　！（１６）．．．Ｐｕｓｈ［３］
//	10705  DATA ピカピカドン　！（２１）．．．Ｐｕｓｈ［４］
//	10710 RETURN
		"元気になぁーれ！（　５）．．．Ｐｕｓｈ［１］",
		"ドドンガドン　！（１０）．．．Ｐｕｓｈ［２］",
		"元気いっぱい　！（１６）．．．Ｐｕｓｈ［３］",
		"ピカピカドン　！（２１）．．．Ｐｕｓｈ［４］",
	};

	/**
	 * 10720 *KEY31.3.
	 * @param enemy 敵情報
	 * @throws IOException 入出力例外
	 */
	private void key313(Enemy enemy) throws IOException {
//	10725  IF  MP<5 THEN GOTO *KEY3.G
		if (this.sts.getMp() < 5) {
			key3g();
			return;
		}
//	10730  MP=MP-5
		this.sts.addMp(-5);
//	10735  LOCATE 4,24
		this.scr.locate(4, 24);
//	10740  PRINT "元気になぁーれ！"
		this.scr.print("元気になぁーれ！");
//	10745  KEN$="    GOOD"
		this.sts.setKen$("    GOOD");
		int mhp = this.sts.getMhp();
		int hp = this.sts.getHp() + 30 + (int) (Math.random() * 10);
//	10755  IF HP+30+INT(RND(1)*10)<MHP THEN HP=HP+30+INT(RND(0)*10) ELSE HP=MHP
		if (hp < mhp) {
			this.sts.setHp(hp);
		} else {
			this.sts.setHp(mhp);
		}
//	10760  GOSUB *HYOUZI.M
		hyouziM();
//	10763  IF TDA=1 THEN GOTO *KEY31.G
		if (this.btl.isTda()) {
			key31g(enemy);
		}
//	10765 RETURN
	}

	/**
	 * 10775 *KEY31.G.
	 * @param enemy 敵情報
	 * @throws IOException 入出力例外
	 */
	private void key31g(Enemy enemy) throws IOException {
//	10785  C=2
		this.btl.setC(2);
//	10790  GOSUB *TEKIATA.M
		tekiataM(enemy);
//	10795  GOSUB *HYOUZI.M
		hyouziM();
//	10800 RETURN
	}

	/**
	 * 10810 *KEY32.3.
	 * @param enemy 敵情報
	 * @throws IOException 入出力例外
	 */
	private void key323(Enemy enemy) throws IOException {
//	10815  IF  MP<10 THEN GOTO *KEY3.G
		if (this.sts.getMp() < 10) {
			key3g();
			return;
		}
//	10820  IF TDA=0  THEN RETURN
		if (!this.btl.isTda()) {
			return;
		}
//	10825  MP=MP-10
		this.sts.addMp(-10);
//	10830  LOCATE 4,24
		this.scr.locate(4, 24);
//	10835  PRINT "ドドンガドン！"
		this.scr.print("ドドンガドン！");
//	10840    D=INT(RND(1)*30)+40
		int d = ((int) (Math.random() * 30)) + 40;
//	10845  THP=THP-D
		enemy.addThp(-d);
//	10850  FOR J=0 TO 3
//	10851   GOSUB *BEEPS.M
//	10852   VSYC=8
//	10853   GOSUB *VSYC.M
//	10854  NEXT
//	10855  GOSUB *JIATA2.M
		jiata2M(enemy, d);
//	10860 RETURN
	}

	/**
	 * 10865 *KEY33.3.
	 * @param enemy
	 * @throws IOException
	 */
	private void key333(Enemy enemy) throws IOException {
//	10870  IF  MP<16 THEN GOTO *KEY3.G
		if (this.sts.getMp() < 16) {
			key3g();
			return;
		}
//	10875  MP=MP-11
		this.sts.addMp(-11);
//	10880  LOCATE 4,24
		this.scr.locate(4, 24);
//	10885  PRINT "元気いつぱい！"
		this.scr.print("元気いつぱい！");
//	10890  HP=MHP
		this.sts.setHp(this.sts.getMhp());
//	10895  KEN$="    GOOD"
		this.sts.setKen$("    GOOD");
//	10900  GOSUB *HYOUZI.M
		hyouziM();
//	10901  IF TDA=1 THEN GOTO *KEY33.G
		if (this.btl.isTda()) {
			key33g(enemy);
		}
//	10902 RETURN
	}

	/**
	 * 10903 *KEY33.G.
	 * @throws IOException 入出力例外
	 */
	private void key33g(Enemy enemy) throws IOException {
//	10904  C=2
		this.btl.setC(2);
//	10905  GOSUB *TEKIATA.M
		tekiataM(enemy);
//	10906  GOSUB *HYOUZI.M
		hyouziM();
//	10907 RETURN
	}

	/**
	 * 10915 *KEY34.3.
	 * @param enemy
	 * @throws IOException 入出力例外
	 */
	private void key343(Enemy enemy) throws IOException {
//	10920  IF MP<21 THEN GOTO *KEY3.G
		if (this.sts.getMp() < 21) {
			key3g();
			return;
		}
//	10925  IF TDA=0 THEN RETURN
		if (!this.btl.isTda()) {
			return;
		}
//	10930  MP=MP-21
		this.sts.addMp(-21);
//	10935  LOCATE 4,24
		this.scr.locate(4, 24);
//	10940  PRINT "ピカピカドン！"
		this.scr.print("ピカピカドン！");
//	10945  D=INT(RND(1)*50)+100
		int d = ((int) (Math.random() * 50)) + 100;
//	10950  THP=THP-D
		enemy.addThp(-d);
//	10956  FOR J=0 TO 7
//	10957   GOSUB *BEEPS.M
//	10958   VSYC=8
//	10959   GOSUB *VSYC.M
//	10960  NEXT
//	10961  GOSUB *JIATA2.M
		jiata2M(enemy, d);
//	10962 RETURN
	}

	/**
	 * 10970 *KEY3.G.
	 */
	private void key3g() {
//	10975  LOCATE 4,24
		this.scr.locate(4, 24);
//	10980  PRINT "Ｍ．Ｐが足りない。"
		this.scr.print("Ｍ．Ｐが足りない。");
//	10985 RETURN
	}

	/**
	 * 調べる.
	 * 10995 *KEY4.Y.
	 */
	private void key4y() {
//	11000  KEY OFF
		int val = this.map.getMap();
//	11005  IF    MAP.!(SX,SY)=8                     THEN GOTO *KEY41.G
		if (val == 8) {
			key41g();
			return;
		}
//	11010  IF    MAP.!(SX,SY)=25 OR MAP.!(SX,SY)=26 THEN GOTO *KEY42.G
		if (val == 25 || val == 26) {
			key42g();
			return;
		}
//	11015  LOCATE 4,24
		this.scr.locate(4, 24);
//	11020  PRINT NA$;"は、よく自分の足元を調べた。"
		this.scr.print(this.sts.getNa$() + "は、よく自分の足元を調べた。");
//	11025  IF INT(RND(1)*100)=50                    THEN GOTO *KEY43.G
		int rnd = (int) (Math.random() * 100);
		if (rnd == 50) {
			key43g();
			return;
		}
//	11030  GOSUB *NANIMONAI.M
		nanimonaiM();
//	11035  GOSUB *KEYON.M
//	11040 RETURN
	}

	/**
	 * 11050 *KEY41.G.
	 */
	private void key41g() {
//	11055  LOCATE 4,24
		this.scr.locate(4, 24);
//	11060  PRINT NA$;"は、よく宝箱の中を調べた。"
		this.scr.print(this.sts.getNa$() + "は、よく宝箱の中を調べた。");
//	11065  IF FE$="2" AND TGI2=0 THEN GOTO *KEY41.G2
		int tgi2 = this.map.getTgi2();
		if ("2".equals(this.map.getFe$()) && tgi2 == 0) {
			key41g2();
			return;
		}
//	11070  GOSUB *NANIMONAI.M
		nanimonaiM();
//	11075  GOSUB *KEYON.M
//	11080 RETURN
	}

	/**
	 * 11090 *KEY41.G2.
	 */
	private void key41g2() {
//	11095  LOCATE 4,24
		this.scr.locate(4, 24);
//	11100  PRINT "なんと、２０００ＧＯＬＤをみつけた "
		this.scr.print("なんと、２０００ＧＯＬＤをみつけた ");
//	11105  GOLD!=GOLD!+2000
		this.sts.addGold(2000);
//	11110  TGI2=1
		this.map.setTgi2(1);
//	11115  GOSUB *HYOUZI.M
		hyouziM();
//	11120  GOSUB *BEEPL.M
		beeplM();
//	11125  GOSUB *KEYON.M
//	11130 RETURN
	}

	/**
	 * 11140 *KEY42.G.
	 */
	private void key42g() {
//	11145  FOR  J =1 TO 2
//	11150   FOR  I=1 TO 3
//	11155    GOSUB *BEEPS.M
//	11160    VSYC=10
//	11165    GOSUB *VSYC.M
//	11170   NEXT I
//	11175    VSYC=30
//	11180    GOSUB *VSYC.M
//	11185  NEXT J
//	11190  LOCATE 4,24
		this.scr.locate(4, 24);
//	11195  PRINT "トントントン、トントントン ．．．．"
		this.scr.print("トントントン、トントントン ．．．．");
//	11200  LOCATE 4,24
//	11205  PRINT "留守みたいだ 　　！！"
		this.scr.print("留守みたいだ 　　！！");
//	11210  GOSUB *KEYON.M
//	11215 RETURN
	}

	/**
	 * 11225 *KEY43.G.
	 */
	private void key43g() {
//	11230  LOCATE 4,24
		this.scr.locate(4, 24);
//	11235  PRINT "なんと、１０ＧＯＬＤをみつけた ！"
		this.scr.print("なんと、１０ＧＯＬＤをみつけた ！");
//	11240  GOLD!=GOLD!+10
		this.sts.addGold(10);
//	11245  GOSUB *HYOUZI.M
		hyouziM();
//	11250  GOSUB *BEEPL.M
		beeplM();
//	11255  GOSUB *KEYON.M
//	11260 RETURN
	}

	/**
	 * 11270 *NANIMONAI.M.
	 */
	private void nanimonaiM() {
//	11275  LOCATE 4,24
		this.scr.locate(4, 24);
//	11280  PRINT "しかし、何も見つからなかつた。"
		this.scr.print("しかし、何も見つからなかつた。");
//	11285 RETURN
	}

//	11295  *OUSAMA.D
//	11300   DATA わしは、ミスバ国の王ダカオ、お前に頼みがある。                            　　　暗黒龍が、この国を滅ぼそうとしている。　　　　　　　　　　　　　　　  　　　　　わしの娘も暗黒龍にさらわれた、この国を救ってくれ。！
	private static final String OUSAMA_D = "わしは、ミスバ国の王ダカオ、お前に頼みがある。\n暗黒龍が、この国を滅ぼそうとしている。\nわしの娘も暗黒龍にさらわれた、この国を救ってくれ。！";
//	11305  *ONI1.D
//	11310   DATA 私は、昔、コパの村で木こりのアルバイトをしたことがあるんだが、                  そこの親方は、すごい力持ちなんだけど　　                                        殺されたと言う話は、本当かなぁ？
	private static final String ONI1_D = "私は、昔、コパの村で木こりのアルバイトをしたことがあるんだが、\nそこの親方は、すごい力持ちなんだけど\n殺されたと言う話は、本当かなぁ？";
//	11315  *ONE1.D
//	11320   DATA この城から南へ行った所にセレーヌの森があるの、　　　　　　　　　　　  　　　　　その森の中には、コパの村があるて話ょ。
	private static final String ONE1_D = "この城から南へ行った所にセレーヌの森があるの、\nその森の中には、コパの村があるて話ょ。";
//	11325  *OTO1.D
//	11330   DATA ぼく、大きくなつて、カーピスのおじちゃんみたいに力持ちの木こりになる            んだ
	private static final String OTO1_D = "ぼく、大きくなつて、カーピスのおじちゃんみたいに\n力持ちの木こりになるんだ";
//	11335  *ONN1.D
//	11340   DATA 私の、お姉ちゃんの友達の従姉妹の友達の隣の家のお姉ちゃんは、　　　　            アイドルの　Ｋｙｏｎ２  なのよ。                                                レコード買つてね。??
	private static final String ONN1_D = "私の、お姉ちゃんの友達の従姉妹の友達の隣の家のお姉ちゃんは、\nアイドルの　Ｋｙｏｎ２  なのよ。\nレコード買つてね。♪♪";
//	11345  *ONI6.D
//	11350   DATA ちくしょー、おら悔しいべ。                                                      カーピス親方が殺されるなんて、                                                  親方が、かなわないなんて、一体どんな奴だべ。
	private static final String ONI6_D = "ちくしょー、おら悔しいべ。\nカーピス親方が殺されるなんて、\n親方が、かなわないなんて、一体どんな奴だべ。";
//	11355  *ONE6.D
//	11360   DATA この村の人は、ミスバ城の東の湖の町に住む商人に木を売って暮らして                いるの。                                                                        でも・・・カーピスが殺されて以来、怖がって商人がこないの。
	private static final String ONE6_D = "この村の人は、ミスバ城の東の湖の町に住む商人に木を売って暮らして\nいるの。\nでも・・・カーピスが殺されて以来、怖がって商人がこないの。";
//	11365  *OTO6.D
//	11370   DATA ぐすん、ぐすん。                                                                お父ちゃんの仇を打ってくれよ！
	private static final String OTO6_D = "ぐすん、ぐすん。\nお父ちゃんの仇を打ってくれよ！";
//	11375  *ONN6.D
//	11380   DATA 私 見たの・・・                                                                 カーピスおじちゃんを 殺した魔物が森の一本松の方へ行くのを・・・                 でも 怖くて今まで黙っていたの。
	private static final String ONN6_D = "私 見たの・・・\nカーピスおじちゃんを 殺した魔物が森の一本松の方へ行くのを・・・\nでも 怖くて今まで黙っていたの。";
//	11385  *ONI61.D
//	11390   DATA ありがとう。
	private static final String ONI61_D = "ありがとう。";
//	11395  *KAIBUTU1.D
//	11400   DATA ぐはははぁー                                                                    俺様は、魔王ホーバリ様だ。                                                      ちょうど腹がへっていることだし食べてくれるわ。
	private static final String KAIBUTU1_D = "ぐはははぁー\n俺様は、魔王ホーバリ様だ。\nちょうど腹がへっていることだし食べてくれるわ。";
//	11405  *ONI2.D
//	11410   DATA コパの村に木材を買いに行けないから、木材が不足して 困った困った。
	private static final String ONI2_D = "コパの村に木材を買いに行けないから、木材が不足して 困った困った。";
//	11415  *ONE2.D
//	11420   DATA 家を建てたいけど・・・                                                          木材が高くてとてもとても。
	private static final String ONE2_D = "家を建てたいけど・・・\n木材が高くてとてもとても。";
//	11425  *OTO2.D
//	11430   DATA 外は魔物がいっぱいで出たらいけないってママが言っていたけど・・・                お兄ちゃんだいじょうぶ？
	private static final String OTO2_D = "外は魔物がいっぱいで出たらいけないってママが言っていたけど・・・\nお兄ちゃんだいじょうぶ？";
//	11435  *ONN2.D
//	11440   DATA コパの村は日曜学校のピクニックに行ったけど大きな森の中にあって                  とってもきれいな所だったなぁ。
	private static final String ONN2_D = "コパの村は日曜学校のピクニックに行ったけど大きな森の中にあって\nとってもきれいな所だったなぁ。";
//	11445  *ONI21.D
//	11450   DATA ありがとう。                                                                    さっき、早速、木材を買って来たよ。
	private static final String ONI21_D = "ありがとう。\nさっき、早速、木材を買って来たよ。";
//	11455  *ONE21.D
//	11460   DATA これでやっと Ｍｙ ｈｏｍｅを 建てられるわ。??
	private static final String ONE21_D = "これでやっと Ｍｙ ｈｏｍｅを 建てられるわ。♪♪";
//	11465  *OTO21.D
//	11470   DATA 本当にありがとう。
	private static final String OTO21_D = "本当にありがとう。";
//	11475  *ONN21.D
//	11480   DATA これでやっと南の鍾乳洞や東のリルビーの町へ行けるょー。
	private static final String ONN21_D = "これでやっと南の鍾乳洞や東のリルビーの町へ行けるょー。";
//	11485  *ONI3.D
//	11490   DATA 山のどこかにお金持ちの王子が道楽で建てた塔があるんだ。                          その王子はその塔に宝物を隠して、初めにとってきた者にそれをやる         　       って言ってたんだけど・・・
	private static final String ONI3_D = "山のどこかにお金持ちの王子が道楽で建てた塔があるんだ。\nその王子はその塔に宝物を隠して、初めにとってきた者にそれをやる\nって言ってたんだけど・・・";
//	11495  *ONE3.D
//	11500   DATA リルビーの町にようこそ！！
	private static final String ONE3_D = "リルビーの町にようこそ！！";
//	11505  *OTO3.D
//	11510   DATA 塔の中には、王子が仕掛けた罠がいっぱいあるって話だよ。                          それから、入り口は洞窟らしいよ。
	private static final String OTO3_D = "塔の中には、王子が仕掛けた罠がいっぱいあるって話だよ。\nそれから、入り口は洞窟らしいよ。";
//	11515  *ONN3.D
//	11520   DATA 私はクララ、おおきくなったら砂漠の町リーバンに住むペーターと                    結婚するの。??
	private static final String ONN3_D = "私はクララ、おおきくなったら砂漠の町リーバンに住むペーターと\n結婚するの。??";
//	11525  *ONI31.D
//	11530   DATA えーっ！ 本当？ 宝物を・・・
	private static final String ONI31_D = "えーっ！ 本当？ 宝物を・・・";
//	11535  *ONI4.D
//	11540   DATA 西の湖の洞窟に魔物が住みついてから、井戸の水位が下がって、                      このままだとあと何日持つか・・・
	private static final String ONI4_D = "";
//	11545  *ONE4.D
//	11550   DATA 水がいっぱいある南の町ルドイヤに引っ越そうかなぁ。
	private static final String ONE4_D = "水がいっぱいある南の町ルドイヤに引っ越そうかなぁ。";
//	11555  *OTO4.D
//	11560   DATA えーっ！クララがそんなことを・・・                                              ハイジのほうが好きなのに・・・
	private static final String OTO4_D = "えーっ！クララがそんなことを・・・\nハイジのほうが好きなのに・・・";
//	11565  *ONN4.D
//	11570   DATA 私、ハイジ。コパの村から引っ越して来たの。                                      でも、コパの村のほうがいいわ。??
	private static final String ONN4_D = "私、ハイジ。コパの村から引っ越して来たの。\nでも、コパの村のほうがいいわ。??";
//	11575  *ONI41.D
//	11580   DATA どうも ありがとう。
	private static final String ONI41_D = "どうも ありがとう。";
//	11585  *KAIBUTU3.D
//	11590   DATA ぐはははぁー                                                                    俺様は、キング　大妙王様だ。                                                    ちょうど腹がへっていることだし食べてくれるわ。
	private static final String KAIBUTU3_D = "ぐはははぁー \n俺様は、キング　大妙王様だ。\nちょうど腹がへっていることだし食べてくれるわ。";
//	11595  *ONI5.D
//	11600   DATA 西のレドルのツインタワーは、とても危険じゃ・・・                                気をつけていくがよい。
	private static final String ONI5_D = "西のレドルのツインタワーは、とても危険じゃ・・・\n気をつけていくがよい。";
//	11605  *ONE5.D
//	11610   DATA ??～・・・なんてったって アイドル・・・ ??～
	private static final String ONE5_D = "♪♪～・・・なんてったって アイドル・・・ ♪♪～";
//	11615  *OTO5.D
//	11620   DATA Ｋｙｏｎ２ を見なかった？
	private static final String OTO5_D = "Ｋｙｏｎ２ を見なかった？";
//	11625  *ONN5.D
//	11630   DATA ようこそルドイヤへ！！
	private static final String ONN5_D = "ようこそルドイヤへ！！";
//	11635  *KAIBUTU4.D
//	11640   DATA このレドルのツインタワーの最上階に登ってくる人間がいるとは・・・                バカ者めが、殺してくれるわ！！！
	private static final String KAIBUTU4_D = "このレドルのツインタワーの最上階に登ってくる人間がいるとは・・・\nバカ者めが、殺してくれるわ！！！";
//	11645  *ONI7.D
//	11650   DATA 魔物？！・・・                                                                  なんのこと？
	private static final String ONI7_D = "魔物？！・・・\nなんのこと？";
//	11655  *ONE7.D
//	11660   DATA どこからきたの？
	private static final String ONE7_D = "どこからきたの？";
//	11665  *OTO7.D
//	11670   DATA この村に旅人とは・・・珍しーぃ。
	private static final String OTO7_D = "この村に旅人とは・・・珍しーぃ。";
//	11675  *ONN7.D
//	11680   DATA お船に乗って来たの？
	private static final String ONN7_D = "お船に乗って来たの？";
//	11685  *ONI8.D
//	11690   DATA わしはシーペ城の門番じゃった。                                                  しかし暗黒龍ギルドンによって城は・・・
	private static final String ONI8_D = "わしはシーペ城の門番じゃった。\nしかし暗黒龍ギルドンによって城は・・・";
//	11695  *ONE8.D
//	11700   DATA みんな、命からがら、城から逃げてきたの。
	private static final String ONE8_D = "みんな、命からがら、城から逃げてきたの。";
//	11705  *OTO8.D
//	11710   DATA ギルドンなんか、大きくなったらやっつけてやるぅ！
	private static final String OTO8_D = "ギルドンなんか、大きくなったらやっつけてやるぅ！";
//	11715  *ONN8.D
//	11720   DATA ぐすん、ぐすん、 怖いよーっ。
	private static final String ONN8_D = "ぐすん、ぐすん、 怖いよーっ。";
//	11725  *KAIBUTUX.D
//	11730   DATA ぐはははぁー                                                                    俺様は暗黒龍ギルドン様だ。                                                      よくここまで来たな。しかし、これで貴様も終わりだ！！
	private static final String KAIBUTUX_D = "ぐはははぁー\n俺様は暗黒龍ギルドン様だ。\nよくここまで来たな。しかし、これで貴様も終わりだ！！";

	/**
	 * 話す.
	 * 11740 *KEY5.Y.
	 * @throws IOException 入出力例外
	 */
	private void key5y() throws IOException {
//	11745  TA=0
		String ta = "";
		int sy = this.map.getSy();
//	11750  IF SY=0 THEN RETURN
		if (sy == 0) {
			return;
		}
		int val = this.map.getMap(this.map.getSx(), sy - 1);
//	11755  IF MAP.!(SX,SY-1)=64 THEN TA=1:GOSUB *KAIBUTU.3
//	11760  IF MAP.!(SX,SY-1)=61 THEN TA=1:GOSUB *ONIISAN.3
//	11765  IF MAP.!(SX,SY-1)=62 THEN TA=1:GOSUB *ONEESAN.3
//	11770  IF MAP.!(SX,SY-1)=65 THEN TA=1:GOSUB *OTOKO.3
//	11775  IF MAP.!(SX,SY-1)=66 THEN TA=1:GOSUB *ONNA.3
//	11780  IF MAP.!(SX,SY-1)=60 THEN TA=1:GOSUB *OUSAMA.3
		if (val == 64) {
			ta = kaibutu3();
		} else if (val == 61) {
			ta = oniisan3();
		} else if (val == 62) {
			ta = oneesan3();
		} else if (val == 65) {
			ta = otoko3();
		} else if (val == 66) {
			ta = onna3();
		} else if (val == 60) {
			ta = OUSAMA_D;
		}
//	11785  IF TA=0 THEN RETURN
		if (StringUtils.isBlank(ta)) {
			return;
		}
//	11790  READ D$
//	11795  LOCATE 4,24
		this.scr.locate(4, 24);
//	11800  PRINT D$
		this.scr.print(ta);
		Enemy enemy = null;
//	11805  IF TGI1=1 THEN GOSUB *BOSS1
//	11810  IF TGI3=1 THEN GOSUB *BOSS2
//	11815  IF TGI4=1 THEN GOSUB *BOSS3
//	11820  IF TGI5=1 THEN GOSUB *BOSS4
		if (this.map.getTgi1() == 1) {
			enemy = new Enemy("魔王　ホーバリ", 1, 500, 300, 30, 30, 0, 500);
			this.btl.setTda(true);
		} else if (this.map.getTgi3() == 1) {
			enemy = new Enemy("キング　大妙王", 0, 1000, 1000, 30, 35, 0, 2000);
			this.btl.setTda(true);
		} else if (this.map.getTgi4() == 1) {
			enemy = new Enemy("キング　ゴースト", 2, 4000, 2000, 140, 40, 0, 3000);
			this.btl.setTda(true);
		} else if (this.map.getTgi5() == 1) {
			enemy = new Enemy("暗黒龍　ギルドン", 4, 8000, 5000, 170, 55, 0, 5000);
			this.btl.setTda(true);
		}
//	11825  GOSUB *ANY.M
		anyM();
//	11830  IF TDA=0 THEN RETURN
		if (!this.btl.isTda() || enemy == null) {
			return;
		}
//	11835  LINE (16,16)-(304,304),0,BF
		wakuM(12, 12, 303, 303, 0);
//	11840  GOSUB *CFKEY.M
//	11845  GOSUB *BOSS.F
		bossF("\\MB", enemy);
//	11850  C=0
		this.btl.setC(0);
//	11855  GOSUB *ABOSS.F
		abossF(enemy);
//	11860 RETURN
//	11870 *BOSS1
//	11875   THP=500
//	11880   TEX=300
//	11885   TAT=30
//	11890   TSH=30
//	11895   TGO=500
//	11900    A$="\MB"
//	11905   TDA=1
//	11910  TNA$="魔王　ホーバリ　　"
//	11915     A=1
//	11920 RETURN
//	11925 *BOSS2
//	11930  TNA$="キング　大妙王　　"
//	11935  THP=1000
//	11940  TEX=1000
//	11945  TAT=60
//	11950  TSH=35
//	11955  TGO=2000
//	11960   A=0
//	11965   A$="\MB"
//	11970   TDA=1
//	11975 RETURN
//	11980 *BOSS3
//	11985  TNA$="キング　ゴースト　"
//	11990  THP=4000
//	11995  TEX=2000
//	12000  TAT=140
//	12005  TSH=40
//	12010  TGO=3000
//	12015   A=2
//	12020   A$="\MB"
//	12025   TDA=1
//	12030 RETURN
//	12035 *BOSS4
//	12040   TNA$="暗黒龍　ギルドン　"
//	12045   THP=8000
//	12050   TEX=5000
//	12055   TAT=170
//	12060   TSH=55
//	12065   TGO=5000
//	12070     A=4
//	12075   TDA=1
//	12080  DEF SEG=VARPTR(TEKI(0),1)
//	12085  BLOAD "TEKI\BOS",VARPTR(TEKI(0),0)
//	12090  GOSUB *FADEOUT.M
//	12095  PUT@ (16,16),TEKI,PSET
//	12100  GOSUB *FADEIN.M
//	12105  GOSUB *CFKEY.M
//	12110  C=0
//	12115  GOSUB *ABOSS.F
//	12120 RETURN
	}

	/**
	 * 12130 *KAIBUTU.3.
	 * @return メッセージ
	 */
	private String kaibutu3() {
		String msg = "";
		int dgx = this.map.getDgx();
		int tgi1 = this.map.getTgi1();
		int tgi3 = this.map.getTgi3();
		int tgi4 = this.map.getTgi4();
//	12135  IF DGX=0 AND TGI1=0 THEN RESTORE *KAIBUTU1.D:TGI1=1
		if (dgx == 0 && tgi1 == 0) {
			msg = KAIBUTU1_D;
			this.map.setTgi1(1);
		}
//	12140  IF DGX=0 AND TGI1=2 THEN GOSUB *SIBOU.M
		if (dgx == 0 && tgi1 == 2) {
			msg = SIBOU_M;
		}
//	12145  IF DGX=7 AND TGI3=0 THEN RESTORE *KAIBUTU3.D:TGI3=1
		if (dgx == 7 && tgi3 == 0) {
			msg = KAIBUTU3_D;
			this.map.setTgi3(1);
		}
//	12150  IF DGX=7 AND TGI3=2 THEN GOSUB *SIBOU.M
		if (dgx == 7 && tgi3 == 2) {
			msg = SIBOU_M;
		}
//	12155  IF DGX=6 AND TGI4=0 THEN RESTORE *KAIBUTU4.D:TGI4=1
		if (dgx == 6 && tgi4 == 0) {
			msg = KAIBUTU4_D;
			this.map.setTgi4(1);
		}
//	12160  IF DGX=6 AND TGI4=2 THEN GOSUB *SIBOU.M
		if (dgx == 6 && tgi4 == 2) {
			msg = SIBOU_M;
		}
//	12165  IF DGX=4            THEN RESTORE *KAIBUTUX.D:TGI5=1
		if (dgx == 4) {
			msg = KAIBUTUX_D;
			this.map.setTgi5(1);
		}
//	12170 RETURN
		return msg;
	}
//	12180 *SIBOU.M
//	12185  DATA ．．．．．．　　　　　　　　　　　　　　　　　                            　　　死んでいる。
//	12190  RESTORE *SIBOU.M
//	12195 RETURN
	private static final String SIBOU_M = "．．．．．．\n死んでいる。";

	/**
	 * 12205 *ONIISAN.3.
	 * @return メッセージ
	 */
	private String oniisan3() {
		String msg = "";
//	12210  ON MATI GOSUB *ONI1,*ONI2,*ONI3,*ONI4,*ONI5,*ONI6,*ONI7,*ONI8
		int mati = this.map.getMati();
		int tgi1 = this.map.getTgi1();
		int tgi2 = this.map.getTgi2();
		int tgi3 = this.map.getTgi3();

		if (mati == 1) {
			msg = ONI1_D;
		} else if (mati == 2) {
			if (tgi1 == 0) {
				msg = ONI2_D;
			} else {
				msg = ONI21_D;
			}
		} else if (mati == 3) {
			if (tgi2 == 0) {
				msg = ONI3_D;
			} else {
				msg = ONI31_D;
			}
		} else if (mati == 4) {
			if (tgi3 == 0) {
				msg = ONI4_D;
			} else {
				msg = ONI41_D;
			}
		} else if (mati == 5) {
			msg = ONI5_D;
		} else if (mati == 6) {
			if (tgi1 == 0) {
				msg = ONI6_D;
			} else {
				msg = ONI61_D;
			}
		} else if (mati == 7) {
			msg = ONI7_D;
		} else if (mati == 8) {
			msg = ONI8_D;
		}
//	12215 RETURN
		return msg;
	}
//	12225  *ONI1
//	12230   RESTORE *ONI1.D
//	12235  RETURN
//	12240  *ONI2
//	12245   IF TGI1=0 THEN RESTORE *ONI2.D:RETURN
//	12250   RESTORE *ONI21.D
//	12255  RETURN
//	12260  *ONI3
//	12265   IF TGI2=0 THEN RESTORE *ONI3.D:RETURN
//	12270   RESTORE *ONI31.D
//	12275  RETURN
//	12280  *ONI4
//	12285   IF TGI3=0 THEN RESTORE *ONI4.D:RETURN
//	12290   RESTORE *ONI41.D
//	12295  RETURN
//	12300  *ONI5
//	12305   RESTORE *ONI5.D
//	12310  RETURN
//	12315  *ONI6
//	12320   IF TGI1=0 THEN RESTORE *ONI6.D:RETURN
//	12325   RESTORE *ONI61.D
//	12330  RETURN
//	12335  *ONI7
//	12340   RESTORE *ONI7.D
//	12345  RETURN
//	12350  *ONI8
//	12355   RESTORE *ONI8.D
//	12360 RETURN
	/**
	 * 12370 *ONEESAN.3.
	 * @return メッセージ
	 */
	private String oneesan3() {
		String msg = "";
//	12375  ON MATI GOSUB *ONE1,*ONE2,*ONE3,*ONE4,*ONE5,*ONE6,*ONE7,*ONE8
		int mati = this.map.getMati();
		int tgi1 = this.map.getTgi1();
		int tgi2 = this.map.getTgi2();
		int tgi3 = this.map.getTgi3();

		if (mati == 1) {
			msg = ONE1_D;
		} else if (mati == 2) {
			if (tgi1 == 0) {
				msg = ONE2_D;
			} else {
				msg = ONE21_D;
			}
		} else if (mati == 3) {
			if (tgi2 == 0) {
				msg = ONE3_D;
			} else {
				msg = ONI31_D;
			}
		} else if (mati == 4) {
			if (tgi3 == 0) {
				msg = ONE4_D;
			} else {
				msg = ONI31_D;
			}
		} else if (mati == 5) {
			msg = ONE5_D;
		} else if (mati == 6) {
			if (tgi1 == 0) {
				msg = ONE6_D;
			} else {
				msg = ONI61_D;
			}
		} else if (mati == 7) {
			msg = ONE7_D;
		} else if (mati == 8) {
			msg = ONE8_D;
		}
//	12380 RETURN
		return msg;
	}
//	12390  *ONE1
//	12395   RESTORE *ONE1.D
//	12400  RETURN
//	12405  *ONE2
//	12410   IF TGI1=0 THEN RESTORE *ONE2.D:RETURN
//	12415   RESTORE *ONE21.D
//	12420  RETURN
//	12425  *ONE3
//	12430   IF TGI2=0 THEN RESTORE *ONE3.D:RETURN
//	12435   RESTORE *ONI31.D
//	12440  RETURN
//	12445  *ONE4
//	12450   IF TGI3=0 THEN RESTORE *ONE4.D:RETURN
//	12455   RESTORE *ONI31.D
//	12460  RETURN
//	12465  *ONE5
//	12470   RESTORE *ONE5.D
//	12475  RETURN
//	12480  *ONE6
//	12485   IF TGI1=0 THEN RESTORE *ONE6.D:RETURN
//	12490   RESTORE *ONI61.D
//	12495  RETURN
//	12500  *ONE7
//	12505   RESTORE *ONE7.D
//	12510  RETURN
//	12515  *ONE8
//	12520   RESTORE *ONE8.D
//	12525 RETURN
	/**
	 * 12535 *OTOKO.3.
	 * @return
	 */
	private String otoko3() {
		String msg = "";
//	12540  ON MATI GOSUB *OTO1,*OTO2,*OTO3,*OTO4,*OTO5,*OTO6,*OTO7,*OTO8
		int mati = this.map.getMati();
		int tgi1 = this.map.getTgi1();
		int tgi2 = this.map.getTgi2();
		int tgi3 = this.map.getTgi3();

		if (mati == 1) {
			msg = OTO1_D;
		} else if (mati == 2) {
			if (tgi1 == 0) {
				msg = OTO2_D;
			} else {
				msg = OTO21_D;
			}
		} else if (mati == 3) {
			if (tgi2 == 0) {
				msg = OTO3_D;
			} else {
				msg = ONI31_D;
			}
		} else if (mati == 4) {
			if (tgi3 == 0) {
				msg = OTO4_D;
			} else {
				msg = ONI41_D;
			}
		} else if (mati == 5) {
			msg = OTO5_D;
		} else if (mati == 6) {
			if (tgi1 == 0) {
				msg = OTO6_D;
			} else {
				msg = ONI61_D;
			}
		} else if (mati == 7) {
			msg = OTO7_D;
		} else if (mati == 8) {
			msg = OTO8_D;
		}
//	12545 RETURN
		return msg;
	}
//	12555  *OTO1
//	12560   RESTORE *OTO1.D
//	12565  RETURN
//	12570  *OTO2
//	12575   IF TGI1=0 THEN RESTORE *OTO2.D:RETURN
//	12580   RESTORE *OTO21.D
//	12585  RETURN
//	12590  *OTO3
//	12595   IF TGI2=0 THEN RESTORE *OTO3.D:RETURN
//	12600   RESTORE *ONI31.D
//	12605  RETURN
//	12610  *OTO4
//	12615   IF TGI3=0 THEN RESTORE *OTO4.D:RETURN
//	12620   RESTORE *ONI41.D
//	12625  RETURN
//	12630  *OTO5
//	12635   RESTORE *OTO5.D
//	12640  RETURN
//	12645  *OTO6
//	12650   IF TGI1=0 THEN RESTORE *OTO6.D:RETURN
//	12655   RESTORE *ONI61.D
//	12660  RETURN
//	12665  *OTO7
//	12670   RESTORE *OTO7.D
//	12675  RETURN
//	12680  *OTO8
//	12685   RESTORE *OTO8.D
//	12690  RETURN

	/**
	 * 12700 *ONNA.3.
	 * @return メッセージ
	 */
	private String onna3() {
		String msg = "";
//	12705  ON MATI GOSUB *ONN1,*ONN2,*ONN3,*ONN4,*ONN5,*ONN6,*ONN7,*ONN8
		int mati = this.map.getMati();
		int tgi1 = this.map.getTgi1();
		int tgi2 = this.map.getTgi2();
		int tgi3 = this.map.getTgi3();

		if (mati == 1) {
			msg = ONN1_D;
		} else if (mati == 2) {
			if (tgi1 == 0) {
				msg = ONN2_D;
			} else {
				msg = ONN21_D;
			}
		} else if (mati == 3) {
			if (tgi2 == 0) {
				msg = ONN3_D;
			} else {
				msg = ONI31_D;
			}
		} else if (mati == 4) {
			if (tgi3 == 0) {
				msg = ONN4_D;
			} else {
				msg = ONI41_D;
			}
		} else if (mati == 5) {
			msg = ONN5_D;
		} else if (mati == 6) {
			if (tgi1 == 0) {
				msg = ONN6_D;
			} else {
				msg = ONI61_D;
			}
		} else if (mati == 7) {
			msg = ONN7_D;
		} else if (mati == 8) {
			msg = ONN8_D;
		}
//	12710  RETURN
		return msg;
	}
//	12720  *ONN1
//	12725   RESTORE *ONN1.D
//	12730  RETURN
//	12735  *ONN2
//	12740   IF TGI1=0 THEN RESTORE *ONN2.D:RETURN
//	12745   RESTORE *ONN21.D
//	12750  RETURN
//	12755  *ONN3
//	12760   IF TGI2=0 THEN RESTORE *ONN3.D:RETURN
//	12765   RESTORE *ONI31.D
//	12770  RETURN
//	12775  *ONN4
//	12780   IF TGI3=0 THEN RESTORE *ONN4.D:RETURN
//	12785   RESTORE *ONI41.D
//	12790  RETURN
//	12795  *ONN5
//	12800   RESTORE *ONN5.D
//	12805  RETURN
//	12810  *ONN6
//	12815   IF TGI1=0 THEN RESTORE *ONN6.D:RETURN
//	12820   RESTORE *ONI61.D
//	12825  RETURN
//	12830  *ONN7
//	12835   RESTORE *ONN7.D
//	12840  RETURN
//	12845  *ONN8
//	12850   RESTORE *ONN8.D
//	12855  RETURN
//	12865 *OUSAMA.3
//	12870  RESTORE *OUSAMA.D
//	12875 RETURN

	/**
	 * 12885 *KEY6.Y.
	 * @throws IOException 入出力例外
	 */
	private void key6y() throws IOException {
//	12890  KEY OFF
//	12895  IF   MAP.!(SX,SY)=29 THEN GOSUB *KEY61.3
		if (this.map.getMap() == 29) {
			key613();
		}
//	12900  GOSUB *KEYON.M
//	12905 RETURN
	}

	/**
	 * 12915 *KEY61.3.
	 * @throws IOException 入出力例外
	 */
	private void key613() throws IOException {
		int tgi3 = this.map.getTgi3();
		int gx = this.map.getGx();
		int gy = this.map.getGy();
//	12920  IF TGI3=2 AND GX=7 AND GY=6 THEN GOTO *KEY61.G
		if (tgi3 == 2 && gx == 7 && gy == 6) {
			dame23();
			return;
		}
//	12925  FOR J=1 TO 8
//	12930   GOSUB *BEEPS.M
//	12935  NEXT
		int dimen = this.map.getDimen();
//	12940  IF DIMEN=6 THEN GOTO *KAIDAN.M
		if (dimen == 6) {
			kaidanM();
			return;
		}
//	12945   KAI=KAI+1
		this.map.addKai(1);
//	12950  GOSUB *FDANE.M
		fdaneM();
//	12955  GOSUB *MAPLOAD.M
		maploadM();
//	12960  GOSUB *MAPHYOUZI.M
		maphyouziM();
//	12965  GOSUB *UEKAKU.M
		uekakuM();
//	12970 RETURN
	}

//	12980 *KEY61.G
//	12985  GOSUB *DAME2.3
//	12990 RETURN

	/**
	 * 13000 *KEY7.Y.
	 * @throws IOException 入出力例外
	 */
	private void key7y() throws IOException {
//	13005  KEY OFF
//	13010  IF MAP.!(SX,SY)=30 THEN GOSUB *KEY71.3
		if (this.map.getMap() == 30) {
			key713();
		}
//	13015  GOSUB *KEYON.M
//	13020 RETURN
	}

	/**
	 * 13030 *KEY71.3.
	 * @throws IOException 入出力例外
	 */
	private void key713() throws IOException {
//	13035  FOR  J=1 TO 8
//	13040   GOSUB *BEEPS.M
//	13045  NEXT J
//	13050  KAI=KAI-1
		this.map.addKai(-1);
//	13055  GOSUB *FDANE.M
		fdaneM();
//	13060  GOSUB *MAPLOAD.M
		maploadM();
//	13065  GOSUB *MAPHYOUZI.M
		maphyouziM();
//	13070  GOSUB *UEKAKU.M
		uekakuM();
//	13075 RETURN
	}
	/**
	 * 13085 *KAIDAN.M.
	 * @throws IOException 入出力例外
	 */
	private void kaidanM() throws IOException {
//	13090  IF  GX=0 AND GY=0 THEN GOTO  *KAIDAN0.G
		if (this.map.getGx() == 0 && this.map.getGy() == 0) {
			deru23();
			return;
		}
		String fe$ = this.map.getFe$();
//	13095  IF FE$="2"        THEN GOSUB *KAIDAN2.3
//	13100  IF FE$="3"        THEN GOSUB *KAIDAN3.3
//	13105  IF FE$="5"        THEN GOSUB *KAIDAN5.3
//	13110  IF FE$="6"        THEN GOSUB *KAIDAN6.3
//	13115  IF FE$="7"        THEN GOSUB *KAIDAN7.3
//	13120  IF FE$="8"        THEN GOSUB *KAIDAN8.3
		if ("2".equals(fe$)) {
			this.map.setKaidan(8, 3, 4, 3);
		} else if ("3".equals(fe$)) {
			this.map.setKaidan(8, 2, 5, 3);
		} else if ("5".equals(fe$)) {
			this.map.setKaidan(9, 9, 8, 7);
		} else if ("6".equals(fe$)) {
			this.map.setKaidan(9, 10, 6, 6);
		} else if ("7".equals(fe$)) {
			this.map.setKaidan(5, 7, 1, 1);
		} else if ("8".equals(fe$)) {
			this.map.setKaidan(4, 7, 6, 1);
		}
//	13125  GOSUB *DERU2.3
		deru23();
//	13130 RETURN
//	13140 *KAIDAN0.G
//	13145  GOSUB *DERU2.3
//	13150 RETURN
//	13160 *KAIDAN2.3
//	13165  DGX=8
//	13170  DGY=3
//	13175  DSX=4
//	13180  DSY=3
//	13185 RETURN
//	13195 *KAIDAN3.3
//	13200  DGX=8
//	13205  DGY=2
//	13210  DSX=5
//	13215  DSY=3
//	13220 RETURN
//	13230 *KAIDAN5.3
//	13235  DGX=9
//	13240  DGY=9
//	13245  DSX=8
//	13250  DSY=7
//	13255 RETURN
//	13265 *KAIDAN6.3
//	13270  DGX=9
//	13275  DGY=10
//	13280  DSX=6
//	13285  DSY=6
//	13290 RETURN
//	13300 *KAIDAN7.3
//	13305  DGX=5
//	13310  DGY=7
//	13315  DSX=1
//	13320  DSY=1
//	13325 RETURN
//	13335 *KAIDAN8.3
//	13340  DGX=4
//	13345  DGY=7
//	13350  DSX=6
//	13355  DSY=1
	}

	/**
	 * 入る.
	 * 13365 *KEY8.Y.
	 * @throws IOException 入出力例外
	 */
	private void key8y() throws IOException {
//	13370  KEY OFF
		int val = this.map.getMap();
//	13375  IF MAP.!(SX,SY)=28                    THEN GOSUB *YADO.2
		if (val == 28) {
			yado2();
		}
//	13380  IF MAP.!(SX,SY)=23 OR MAP.!(SX,SY)=24 THEN GOSUB *KYOUKAI.2
		if (val == 23 || val == 24) {
			kyoukai2();
		}
//	13385  IF MAP.!(SX,SY)=27                    THEN GOSUB *MISE.2
		if (val == 27) {
			mise2();
		}
//	13390  GOSUB *KEYON.M
//	13395 RETURN
	}

	/**
	 * 13405 *YADO.2.
	 */
	private void yado2() {
//	13410  D$=""
//	13415  LOCATE 4,24
		this.scr.locate(4, 24);
//	13420  PRINT "いらっしゃいませ 。"
		this.scr.print("いらっしゃいませ 。");
//	13425  LOCATE 4,24
//	13430  PRINT "一泊";AKCNV$(STR$(LEV*5));"  ＧＯＬＤだよ"
		int charge = this.sts.getLev() * 5;
		this.scr.print("一泊" + charge + "  ＧＯＬＤだよ");
//	13435  LOCATE 4,24
//	13440  PRINT "泊まる．．［１］　ｏｒ　泊まらない．．［３］”
		this.scr.print("泊まる．．［１］　ｏｒ　泊まらない．．［３］");
//	13445   D$=INKEY$
		int d$ = inkey$('1', '3', KeyEvent.VK_NUMPAD1, KeyEvent.VK_NUMPAD3);
//	13450  WHILE NOT D$="1" AND NOT D$="3"
//	13455   D$=INKEY$
//	13460  WEND
//	13465  IF D$="1" THEN GOSUB *YADO1.3
		if (d$ == '1' || d$ == KeyEvent.VK_NUMPAD1) {
			yado13();
		}
//	13470  GOSUB *OKAERI.M
		okaeriM();
//	13475 RETURN
	}

	/**
	 * 13485 *YADO1.3.
	 */
	private void yado13() {
		int charge = this.sts.getLev() * 5;
//	13490  IF LEV*5>GOLD! THEN GOTO *NOMONEY.M
		if (this.sts.getGold() < charge) {
			nomoneyM();
			return;
		}
//	13495  GOLD!=GOLD!-LEV*5
		this.sts.addGold(-charge);
//	13500    HP=MHP
		this.sts.setHp(this.sts.getMhp());
//	13505    MP=MMP
		this.sts.setMp(this.sts.getMmp());
//	13510  KEN$="    GOOD"
		this.sts.setKen$("    GOOD");
//	13515  GOSUB *BEEPS.M
		beepsM();
//	13520  GOSUB *HYOUZI.M
		hyouziM();
//	13525 RETURN
	}

	/**
	 * 13535 *KYOUKAI.2.
	 * @throws IOException 入出力例外
	 */
	private void kyoukai2() throws IOException {
//	13540  D$=""
//	13545  LOCATE 4,24
		this.scr.locate(4, 24);
//	13550  PRINT "いらっしゃいませ 。"
		this.scr.print("いらっしゃいませ 。");
//	13555  LOCATE 4,24
//	13560  PRINT "一回";AKCNV$(STR$(LEV*5));"  ＧＯＬＤだよ"
		int charge = this.sts.getLev() * 5;
		this.scr.print("一回" + charge + "  ＧＯＬＤだよ");
//	13565  LOCATE 4,24
//	13570  PRINT "日記を書く．．［１］　ｏｒ　日記を書かない．．［３］"
		this.scr.print("日記を書く．．［１］　ｏｒ　日記を書かない．．［３］");
//	13575   D$=INKEY$
		int d$ = inkey$('1', '3', KeyEvent.VK_NUMPAD1, KeyEvent.VK_NUMPAD3);
//	13580  WHILE NOT D$="1" AND NOT D$="3"
//	13585   D$=INKEY$
//	13590  WEND
//	13595  IF D$="1" THEN GOSUB *KYOUKAI1.3
		if (d$ == '1' || d$ == KeyEvent.VK_NUMPAD1) {
			kyoukai13();
		}
//	13600  GOSUB *OKAERI.M
		okaeriM();
//	13605 RETURN
	}

	/**
	 * 13615 *KYOUKAI1.3.
	 * @throws IOException 入出力例外
	 */
	private void kyoukai13() throws IOException {
		int charge = this.sts.getLev() * 5;
//	13620  IF LEV*5>GOLD! THEN GOTO *NOMONEY.M
		if (this.sts.getGold() < charge) {
			nomoneyM();
			return;
		}
//	13625  GOLD!=GOLD!-LEV*5
		this.sts.addGold(-charge);
//	13630  GOSUB *BEEPS.M
		beepsM();
//	13635  GOSUB *SAVE.M
		saveM();
//	13640  RETURN
	}

	private Map<String, Item> getItemMap() {
		Map<String, Item> result = new LinkedHashMap<>();
		int sou = this.sts.getSou();
		Item[] itemList = {
			new Item("薬草", 50),
			new Item("毒消し草", 20),
			new Item("人形", 30 + 1 * sou),
			new Item("日記帳 ", 40 + 2 * sou),
			new Item("風船", 80 + 3 * sou),
			new Item("ＢＯＭＢ", 100 + 4 * sou),
		};
		for (Item item : itemList) {
			result.put(item.getName(), item);
		}
		return result;
	}

	/**
	 * 13650 *MISE.2.
	 */
	private void mise2() {
//	13653  GOSUB *PRINTS.M
		printsM();
//	13655  LOCATE 4,24
		this.scr.locate(4, 24);
//	13660  PRINT "いらっしゃいませ。"
		this.scr.print("いらっしゃいませ。");
//	13665  LOCATE 4,24
//	13670  PRINT "どれにしますか  ？"
		this.scr.print("どれにしますか  ？");
//	13675  GOSUB *PRINTS.M
//	13680  LOCATE 42,2
		this.scr.locate(42, 2);
//	13685  PRINT "薬草      ．．．．．．．．  ５０"
		this.scr.print("薬草      ．．．．．．．．  ５０");
//	13690  LOCATE 42,3
		this.scr.locate(42, 3);
//	13695  PRINT "毒消し草  ．．．．．．．．  ２０"
		this.scr.print("毒消し草  ．．．．．．．．  ２０");
//	13700  LOCATE 42,4
		this.scr.locate(42, 4);
//	13705  PRINT "人形      ．．．．．．．．";AKCNV$(STR$( 30+1*SOU))
		int sou = this.sts.getSou();
		this.scr.print("人形      ．．．．．．．．" + (30 + 1 * sou));
//	13710  LOCATE 42,5
		this.scr.locate(42, 5);
//	13715  PRINT "日記帳    ．．．．．．．．";AKCNV$(STR$( 40+2*SOU))
		this.scr.print("日記帳    ．．．．．．．．" + (40 + 2 * sou));
//	13720  LOCATE 42,6
		this.scr.locate(42, 6);
//	13725  PRINT "風船　　  ．．．．．．．．";AKCNV$(STR$( 80+3*SOU))
		this.scr.print("風船　　  ．．．．．．．．" + (80 + 3 * sou));
//	13730  LOCATE 42,7
		this.scr.locate(42, 7);
//	13735  PRINT "ＢＯＭＢ  ．．．．．．．．";AKCNV$(STR$(100+4*SOU))
		this.scr.print("ＢＯＭＢ  ．．．．．．．．" + (100 + 4 * sou));
//	13740  LOCATE 42,9
		this.scr.locate(42, 9);
//	13745  PRINT SHA$(MATI)
//	13750  LOCATE 60,10
		this.scr.locate(60, 10);
//	13755  PRINT "．．．．";AKCNV$(STR$(NEDAN(0,MATI)))
//	13760  LOCATE 42,11
		this.scr.locate(42, 11);
//	13765  PRINT HEA$(MATI)
//	13770  LOCATE 60,12
		this.scr.locate(60, 12);
//	13775  PRINT "．．．．";AKCNV$(STR$(NEDAN(0,MATI)))
//	13780  LOCATE 42,13
		this.scr.locate(42, 13);
//	13785  PRINT ARA$(MATI)
//	13790  LOCATE 60,14
		this.scr.locate(60, 14);
//	13795  PRINT "．．．．";AKCNV$(STR$(NEDAN(1,MATI)))
//	13800  LOCATE 42,15
		this.scr.locate(42, 15);
//	13805  PRINT TAA$(MATI)
//	13810  LOCATE 60,16
		this.scr.locate(60, 16);
//	13815  PRINT "．．．．";AKCNV$(STR$(NEDAN(2,MATI)))
//	13820  LOCATE 42,17
		this.scr.locate(42, 17);
//	13825  PRINT "店をでる"
		this.scr.print("店をでる");
//	13830  A=2
		int a = 2;
//	13835  B=0
		int b = 0;
//	13840  WHILE NOT B=1
		while (b != 1) {
//	13845   KEY (9) ON
//	13850   LOCATE 40,A
			this.scr.locate(40, a);
//	13855   PRINT "＊"
			this.scr.print("＊");
//	13860   A$=INKEY$
			int a$ = inkey$();
//	13865   IF A$="8" THEN GOSUB *MISE8.3
			if (a$ == KeyEvent.VK_UP || a$ == KeyEvent.VK_NUMPAD8) {
				a = mise83(a);
			}
//	13870   IF A$="2" THEN GOSUB *MISE2.3
			if (a$ == KeyEvent.VK_DOWN || a$ == KeyEvent.VK_NUMPAD2) {
				a = mise23(a);
			}
			if (a$ == KeyEvent.VK_B || a$ == KeyEvent.VK_ENTER
					|| a$ == KeyEvent.VK_SPACE) {
				b = key9y(a, b);
			}
//	13875  WEND
		}
//	13880  GOSUB *PRINTS.M
		printsM();
//	13885  GOSUB *HYOUZI.M
		hyouziM();
//	13890  GOSUB *OKAERI.M
		okaeriM();
//	13895 RETURN
	}

	/**
	 * 13905 *MISE8.3.
	 * @param pa A
	 * @return A
	 */
	private int mise83(int pa) {
		int a = pa;
//	13910  LOCATE 40,A
//	13915  PRINT "  "
		this.scr.clearRect(319, 16, 16, 287);
//	13920  IF A>2 AND A<8 THEN A=A-1
		if (a > 2 && a < 8) {
			a--;
		}
//	13925  IF A>8         THEN A=A-2
		if (a > 8) {
			a -= 2;
		}
//	13930 RETURN
		return a;
	}

	/**
	 * 13940 *MISE2.3.
	 * @param pa A
	 * @return A
	 */
	private int mise23(int pa) {
		int a = pa;
//	13945  LOCATE 40,A
//	13950  PRINT "  "
		this.scr.clearRect(319, 16, 16, 287);
//	13955  IF A>6 AND A<17 THEN A=A+2
		if (a > 6 && a < 17) {
			a += 2;
		}
//	13960  IF         A<7  THEN A=A+1
		if (a < 7) {
			a++;
		}
//	13965 RETURN
		return a;
	}

	/**
	 * 買う.
	 * 13975 *KEY9.Y.
	 * @param a A
	 * @param pb B
	 * @return B
	 */
	private int key9y(final int a, final int pb) {
		int b = pb;
//	13980  KEY OFF
//	13985  ON A GOTO *R,*A2.G ,*A3.G ,*A4.G ,*A5.G ,*A6.G ,*A7.G,*R    ,*A9.G ,*R,*A11.G,*R    ,*A13.G,*R    ,*A15.G,*R   ,*A17.G
		if (a == 2) {
			buy("薬草");
		} else if (a == 3) {
			buy("毒消し草");
		} else if (a == 4) {
			buy("人形");
		} else if (a == 5) {
			buy("日記帳 ");
		} else if (a == 6) {
			buy("風船");
		} else if (a == 7) {
			buy("ＢＯＭＢ");
		} else if (a == 15) {
			a15g();
		} else if (a == 17) {
			b = 1; // *A17.G
		}
//	13990 RETURN
		return b;
	}

	private void buy(String itemName) {
		Map<String, Item> itemMap = getItemMap();
		Item item = itemMap.get(itemName);
		int amount = item.getAmount();

		if (this.sts.getGold() < amount) {
			nomoneyM();
			return;
		}
		String[] itm = this.sts.getItm$();
		if (StringUtils.isBlank(itm[0])) {
			this.sts.addGold(-amount);
			itm[0] = itemName;
			arigatouM();
			return;
		}
		if (StringUtils.isBlank(itm[1])) {
			this.sts.addGold(-amount);
			itm[1] = itemName;
			arigatouM();
			return;
		}
		motenaiM();
	}
//  14000 *A2.G
//	14005  IF    GOLD!<50         THEN GOTO *NOMONEY.M
//	14010  IF ITM$(0)="　　　　" THEN GOTO *A20.G
//	14015  IF ITM$(1)="　　　　" THEN GOTO *A21.G
//	14020  GOSUB                           *MOTENAI.M
//	14025 RETURN
//  14035 *A20.G
//	14040     GOLD!=GOLD!-50
//	14045  ITM$(0)="薬草"
//	14050  GOSUB *ARIGATOU.M
//	14055 RETURN
//  14065 *A21.G.
//	14070     GOLD!=GOLD!-50
//	14075  ITM$(1)="薬草"
//	14080  GOSUB *ARIGATOU.M
//	14085 RETURN
//	14095 *A3.G
//	14100  IF   GOLD!<20          THEN GOTO *NOMONEY.M
//	14105  IF ITM$(0)="　　　　" THEN GOTO *A30.G
//	14110  IF ITM$(1)="　　　　" THEN GOTO *A31.G
//	14115  GOSUB                           *MOTENAI.M
//	14120 RETURN
//	14130 *A30.G
//	14135     GOLD!=GOLD!-20
//	14140  ITM$(0)="毒消し草"
//	14145  GOSUB *ARIGATOU.M
//	14150 RETURN
//	14160 *A31.G
//	14165     GOLD!=GOLD!-20
//	14170  ITM$(1)="毒消し草"
//	14175  GOSUB *ARIGATOU.M
//	14180 RETURN
//	14190 *A4.G
//	14195  IF    GOLD!<30+1*SOU   THEN GOTO *NOMONEY.M
//	14200  IF ITM$(0)="　　　　" THEN GOTO *A40.G
//	14205  IF ITM$(1)="　　　　" THEN GOTO *A41.G
//	14210  GOSUB                           *MOTENAI.M
//	14215 RETURN
//	14225 *A40.G
//	14230     GOLD!=GOLD!-30-1*SOU
//	14235  ITM$(0)="人形"
//	14240      SOU=SOU+1
//	14245  GOSUB *ARIGATOU.M
//	14250 RETURN
//	14260 *A41.G
//	14265     GOLD!=GOLD!-30-1*SOU
//	14270  ITM$(1)="人形"
//	14275      SOU=SOU+1
//	14280  GOSUB *ARIGATOU.M
//	14285 RETURN
//	14295 *A5.G
//	14300  IF   GOLD!<40+2*SOU   THEN GOTO *NOMONEY.M
//	14305  IF ITM$(0)="　　　　" THEN GOTO *A50.G
//	14310  IF ITM$(1)="　　　　" THEN GOTO *A51.G
//	14315  GOSUB                           *MOTENAI.M
//	14320 RETURN
//	14330 *A50.G
//	14335     GOLD!=GOLD!-40-2*SOU
//	14340  ITM$(0)="日記帳"
//	14345      SOU=SOU+1
//	14350  GOSUB *ARIGATOU.M
//	14355 RETURN
//	14365 *A51.G
//	14370     GOLD!=GOLD!-40-2*SOU
//	14375  ITM$(1)="日記帳"
//	14380      SOU=SOU+1
//	14385  GOSUB *ARIGATOU.M
//	14390 RETURN
//	14400 *A6.G
//	14405  IF    GOLD!<80+3*SOU  THEN GOTO *NOMONEY.M
//	14410  IF ITM$(0)="　　　　" THEN GOTO *A60.G
//	14415  IF ITM$(1)="　　　　" THEN GOTO *A61.G
//	14420  GOSUB                           *MOTENAI.M
//	14425 RETURN
//	14435 *A60.G
//	14440     GOLD!=GOLD!-80-3*SOU
//	14445  ITM$(0)="風船"
//	14450      SOU=SOU+1
//	14455  GOSUB *ARIGATOU.M
//	14460 RETURN
//	14470 *A61.G
//	14475     GOLD!=GOLD!-80-3*SOU
//	14480  ITM$(1)="風船"
//	14485      SOU=SOU+1
//	14490  GOSUB *ARIGATOU.M
//	14495 RETURN
//	14505 *A7.G
//	14510  IF    GOLD!<100+4*SOU  THEN GOTO *NOMONEY.M
//	14515  IF ITM$(0)="　　　　" THEN GOTO *A70.G
//	14520  IF ITM$(1)="　　　　" THEN GOTO *A71.G
//	14525  GOSUB                           *MOTENAI.M
//	14530 RETURN
//	14540 *A70.G
//	14545     GOLD!=GOLD!-100-4*SOU
//	14550  ITM$(0)="ＢＯＭＢ"
//	14555      SOU=SOU+1
//	14560  GOSUB *ARIGATOU.M
//	14565 RETURN
//	14575 *A71.G
//	14580     GOLD!=GOLD!-100-4*SOU
//	14585  ITM$(1)="ＢＯＭＢ"
//	14590      SOU=SOU+1
//	14595  GOSUB *ARIGATOU.M
//	14600 RETURN
//	14610 *A9.G
//	14615  IF     GOLD!<NEDAN(0,MATI) THEN GOTO  *NOMONEY.M
//	14620  IF NOT  ASH=0             THEN GOSUB *A90.5
//	14625      ASH=MATI
//	14630     GOLD!=GOLD!-NEDAN(0,ASH)
//	14635  ON ASH GOSUB *ASH1.F,*ASH2.F,*ASH3.F,*ASH4.F,*ASH5.F
//	14640  GOSUB *ARIGATOU.M
//	14645 RETURN
//	14655 *A90.5
//	14660  LOCATE 4,24
//	14665  PRINT "今持つてる";SHA$(ASH);"を";
//	14670  PRINT AKCNV$(STR$(INT(NEDAN(0,ASH)/2)));"ＧＯＬＤで引きとるよ。"
//	14675  GOLD!=GOLD!+INT(NEDAN(0,ASH)/2)
//	14680 RETURN
//	14690 *A11.G
//	14695  IF    GOLD!<NEDAN(0,MATI) THEN  GOTO *NOMONEY.M
//	14700  IF NOT AHE=0             THEN GOSUB *A110.5
//	14705     AHE=MATI
//	14710    GOLD!=GOLD!-NEDAN(0,AHE)
//	14715  ON AHE GOSUB *AHE1.F,*AHE2.F,*AHE3.F,*AHE4.F,*AHE5.F
//	14720  GOSUB *ARIGATOU.M
//	14725 RETURN
//	14735 *A110.5
//	14740  LOCATE 4,24
//	14745  PRINT "今持ってる";HEA$(AHE);"を";
//	14750  PRINT AKCNV$(STR$(INT(NEDAN(0,AHE)/2)));"ＧＯＬＤで引きとるよ。"
//	14755  GOLD!=GOLD!+INT(NEDAN(0,AHE)/2)
//	14760 RETURN
//	14770 *A13.G
//	14775  IF    GOLD!<NEDAN(1,MATI) THEN GOTO  *NOMONEY.M
//	14780  IF NOT AAR=0             THEN GOSUB *A130.5
//	14785     AAR=MATI
//	14790    GOLD!=GOLD!-NEDAN(1,AAR)
//	14795  ON AAR GOSUB *AAR1.F,*AAR2.F,*AAR3.F,*AAR4.F,*AAR5.F
//	14800  GOSUB *ARIGATOU.M
//	14805 RETURN
//	14815 *A130.5
//	14820  LOCATE 4,24
//	14825  PRINT "今持ってる";ARA$(AAR);"を";
//	14830  PRINT AKCNV$(STR$(INT(NEDAN(1,AAR)/2)));"ＧＯＬＤで引きとるよ。"
//	14835  GOLD!=GOLD!+INT(NEDAN(1,AAR)/2)
//	14840 RETURN
	private void a15g() {
//	14850 *A15.G
//	14855  IF    GOLD!<NEDAN(2,MATI) THEN GOTO  *NOMONEY.M
//	14860  IF NOT ATA=0             THEN GOSUB *A150.5
//	14865     ATA=MATI
//	14870    GOLD!=GOLD!-NEDAN(2,ATA)
//	14875  ON ATA GOSUB *ATA1.F,*ATA2.F,*ATA3.F,*ATA4.F,*ATA5.F
//	14880  GOSUB *ARIGATOU.M
//	14885 RETURN
	}
//	14895 *A150.5
//	14900  LOCATE 4,24
//	14905  PRINT "今持ってる";TAA$(ATA);"を";
//	14910  PRINT AKCNV$(STR$(INT(NEDAN(2,ATA)/2)));"ＧＯＬＤで引きとるよ。"
//	14915  GOLD!=GOLD!+INT(NEDAN(2,ATA)/2)
//	14920 RETURN
//	14930 *A17.G
//	14935  B=1
//	14940 RETURN
//	14950 *ATA1.F
//	14955  ATAP=1
//	14960  RETURN
//	14965 *ATA2.F
//	14970  ATAP=2
//	14975  RETURN
//	14980 *ATA3.F
//	14985  ATAP=3
//	14990  RETURN
//	14995 *ATA4.F
//	15000  ATAP=5
//	15005  RETURN
//	15010 *ATA5.F
//	15015  ATAP=7
//	15020 RETURN
//	15030 *ASH1.F
//	15035  ASHP=3
//	15040  RETURN
//	15045 *ASH2.F
//	15050  ASHP=6
//	15055  RETURN
//	15060 *ASH3.F
//	15065  ASHP=10
//	15070  RETURN
//	15075 *ASH4.F
//	15080  ASHP=15
//	15085  RETURN
//	15090 *ASH5.F
//	15095  ASHP=20
//	15100 RETURN
//	15110 *AHE1.F
//	15115  AHEP=2
//	15120  RETURN
//	15125 *AHE2.F
//	15130  AHEP=3
//	15135  RETURN
//	15140 *AHE3.F
//	15145  AHEP=5
//	15150  RETURN
//	15155 *AHE4.F
//	15160  AHEP=8
//	15165  RETURN
//	15170 *AHE5.F
//	15175  AHEP=14
//	15180 RETURN
//	15190 *AAR1.F
//	15195  AARP=10
//	15200  RETURN
//	15205 *AAR2.F
//	15210  AARP=15
//	15215  RETURN
//	15220 *AAR3.F
//	15225  AARP=20
//	15230  RETURN
//	15235 *AAR4.F
//	15240  AARP=25
//	15245  RETURN
//	15250 *AAR5.F
//	15255  AARP=32
//	15260 RETURN

	/**
	 * 道具.
	 * 15270 *KEY10.Y.
	 * @param enemy 敵情報
	 * @throws IOException 入出力例外
	 */
	private void key10y(Enemy enemy) throws IOException {
		String[] itm$ = this.sts.getItm$();
//	15275  IF ITM$(0)="　　　　" AND ITM$(1)="　　　　" THEN RETURN
		if (StringUtils.isBlank(itm$[0]) && StringUtils.isBlank(itm$[1])) {
			return;
		}
//	15280  KEY OFF
//	15285  LOCATE 4,24
		this.scr.locate(4, 24);
//	15290  PRINT "どれを使う。"
		this.scr.print("どれを使う。");
//	15295  GOSUB *BEEPS.M
		beepsM();
//	15300  LOCATE 4,24
//	15305  PRINT ITM$(0);"．．．ＰＵＳＨ　［１］ "
		this.scr.print(itm$[0] + "．．．ＰＵＳＨ　［１］ ");
//	15310  LOCATE 4,24
//	15315  PRINT ITM$(1);"．．．ＰＵＳＨ　［３］ "
		this.scr.print(itm$[1] + "．．．ＰＵＳＨ　［３］ ");
//	15317  LOCATE 4,24
//	15318  PRINT "やめる　．．．ＰＵＳＨ　［７］ "
		this.scr.print("やめる　．．．ＰＵＳＨ　［７］ ");
//	15320   A$=INKEY$
		int a$ = inkey$(KeyEvent.VK_1, KeyEvent.VK_3, KeyEvent.VK_7);
//	15325  WHILE A$<>"1" AND A$<>"3" AND A$<>"7"
//	15330   A$=INKEY$
		int ix = 0;
//	15335   IF A$="1" THEN IX=0
//	15343   IF A$="3" THEN IX=1
		if (a$ == KeyEvent.VK_3) {
			ix = 1;
		}
//	15345  WEND
//	15350  GOSUB *BEEPS.M
		beepsM();
//	15353  IF        A$="7"        THEN *KEY10.IF
		if (a$ == KeyEvent.VK_7) {
			key10if();
			return;
		}
//	15355  IF  ITM$(IX)="　　　　" THEN *KEY10.IF
		if (StringUtils.isBlank(itm$[ix])) {
			key10if();
			return;
		}
//	15360  LOCATE 4,24
//	15365  PRINT NA$+"は、"+ITM$(IX)+"を取り出した。"
		this.scr.print(this.sts.getNa$() + "は、" + itm$[ix] + "を取り出した。");
//	15370  IF  ITM$(IX)="薬草"     THEN GOSUB *KEY101.4
		if ("薬草".equals(itm$[ix])) {
			key1014(enemy);
		}
//	15375  IF  ITM$(IX)="毒消し草" THEN GOSUB *KEY102.4
		if ("毒消し草".equals(itm$[ix])) {
			key1024(enemy);
		}
//	15380  IF  ITM$(IX)="人形"     THEN GOSUB *KEY103.4
		if ("人形".equals(itm$[ix])) {
			key1034(enemy);
		}
//	15385  IF  ITM$(IX)="日記帳"   THEN GOSUB *KEY104.4
		if ("日記帳".equals(itm$[ix])) {
			key1044(enemy);
		}
//	15390  IF  ITM$(IX)="風船"     THEN GOSUB *KEY105.4
		if ("風船".equals(itm$[ix])) {
			key1054(enemy);
		}
//	15395  IF  ITM$(IX)="ＢＯＭＢ" THEN GOSUB *KEY106.4
		if ("ＢＯＭＢ".equals(itm$[ix])) {
			key1064(enemy);
		}
//	15400  ITM$(IX)="　　　　"
		itm$[ix] = "　　　　";
		key10if();
	}

	/**
	 * 15405 *KEY10.IF;
	 */
	private void key10if() {
//	15410  GOSUB *PRINTS.M
		printsM();
//	15415  GOSUB *HYOUZI.M
		hyouziM();
//	15417  GOSUB *PRINT.M
		printM();
//	15425  IF TDA=1 THEN GOSUB *CFKEY.M ELSE GOSUB *KEYON.M
//	15430 RETURN
	}

	/**
	 * 15440 *KEY101.4.
	 * @param enemy 敵情報
	 * @throws IOException 入出力例外
	 */
	private void key1014(Enemy enemy) throws IOException {
		int rnd = (int) (Math.random() * 10);
		int hp = this.sts.getHp();
		int mhp = this.sts.getMhp();
//	15450  IF HP+45+INT(RND(1)*10)<MHP THEN HP=HP+45+INT(RND(0)*10):ELSE HP=MHP
		if (hp + 45 + rnd < mhp) {
			this.sts.setHp(hp + 45 + rnd);
		} else {
			this.sts.setHp(mhp);
		}
//	15453  IF TDA=1 THEN GOTO *KEY101.G
		if (this.btl.isTda()) {
			key101g(enemy);
		}
//	15455 RETURN
	}

	/**
	 * 15465 *KEY101.G.
	 * @param enemy 敵情報
	 * @throws IOException 入出力例外
	 */
	private void key101g(Enemy enemy) throws IOException {
//	15475  C=2
		this.btl.setC(2);
//	15480  GOSUB *TEKIATA.M
		tekiataM(enemy);
//	15485 RETURN
	}

	/**
	 * 15495 *KEY102.4.
	 * @param enemy 敵情報
	 * @throws IOException 入出力例外
	 */
	private void key1024(Enemy enemy) throws IOException {
//	15500  KEN$="    GOOD"
		this.sts.setKen$("    GOOD");
//	15505  IF TDA=0 THEN RETURN
		if (!this.btl.isTda()) {
			return;
		}
//	15510  C=2
		this.btl.setC(2);
//	15515  GOSUB *TEKIATA.M
		tekiataM(enemy);
//	15520 RETURN
	}
	/**
	 * 15530 *KEY103.4.
	 * @throws IOException 入出力例外
	 */
	private void key1034(Enemy enemy) throws IOException {
//	15535  IF TDA=0 THEN RETURN *KEY10.IF
		if (!this.btl.isTda()) {
			key10if();
			return;
		}
//	15540  IF TGI1=1 OR TGI3=1 OR TGI4=1 OR TGI5=1 THEN GOSUB *KEY2.IF ELSE NIGE=1
		int tgi1 = this.map.getTgi1();
		int tgi3 = this.map.getTgi3();
		int tgi4 = this.map.getTgi4();
		int tgi5 = this.map.getTgi5();
		if (tgi1 == 1 || tgi3 == 1 || tgi4 == 1 || tgi5 == 1) {
			key2if(enemy);
		} else {
			this.btl.setNige(true);
		}
//	15545 RETURN
	}

	/**
	 * 15555 *KEY104.4.
	 * @param enemy 敵情報
	 * @throws IOException 入出力例外
	 */
	private void key1044(Enemy enemy) throws IOException {
//	15560  IF TDA=1 THEN GOTO *KEY1041.IF
		if (this.btl.isTda()) {
			key1041if(enemy);
			return;
		}
		String mapf$ = this.map.getMapf$();
//	15565  IF LEFT$(MAPF$,5)="DAN\E" OR LEFT$(MAPF$,5)="DAN\F" THEN GOTO *KEY1042.IF
		if (mapf$.startsWith("DAN\\E") || mapf$.startsWith("DAN\\F")) {
			key1042if();
			return;
		}
//	15570  GOSUB *SAVE.M
		saveM();
//	15575 RETURN
	}
	private void key1041if(Enemy enemy) throws IOException {
//	15585 *KEY1041.IF
//	15590  DATA うわー敵に取られた。
//	15595  RESTORE *KEY1041.IF
//	15600  GOSUB *TOOK
		took("うわー敵に取られた。");
//	15605  C=2
		this.btl.setC(2);
//	15610  GOSUB *TEKIATA.M
		tekiataM(enemy);
//	15615 RETURN
	}

	/**
	 * 15625 *KEY1042.IF.
	 */
	private void key1042if() {
//	15630  DATA 暗くて何も書けない。
//	15635  RESTORE *KEY1042.IF
//	15640  GOSUB *TOOK
		took("暗くて何も書けない。");
//	15643  GOSUB *ANY.M
		anyM();
//	15645 RETURN *KEY10.IF
	}

	private void key1054(Enemy enemy) throws IOException {
//	15655 *KEY105.4
//	15660  IF TDA=1 THEN GOTO *KEY1051.IF
		if (this.btl.isTda()) {
			key1051if(enemy);
			return;
		}
		String mapf$ = this.map.getMapf$();
//	15665  IF LEFT$(MAPF$,5)="DAN\E" OR LEFT$(MAPF$,5)="DAN\F" THEN GOTO *KEY1052.IF
		if (mapf$.startsWith("DAN\\E") || mapf$.startsWith("DAN\\F")) {
			key1052if();
			return;
		}
//	15670    GX=1
		this.map.setGx(1);
//	15675    GY=1
		this.map.setGy(1);
//	15680    SX=4
		this.map.setSx(4);
//	15681    SY=7
		this.map.setSy(7);
//	15682   KAI=1
		this.map.setKai(1);
//	15683   TAU=2
		this.map.setTau(2);
//	15684  MATI=0
		this.map.setMati(0);
//	15685  DIMEN=1
		this.map.setDimen(1);
//	15689  GOSUB *FMAP.M
		this.map.setMapf$map();
//	15690  GOSUB *MAPLOAD.M
		maploadM();
//	15695  GOSUB *MAPHYOUZI.M
		maphyouziM();
//	15700  GOSUB *SHITAKAKU.M
		shitakakuM();
//	15705 RETURN
	}

	/**
	 * 15715 *KEY1051.IF.
	 * @param enemy 敵情報
	 * @throws IOException 入出力例外
	 */
	private void key1051if(Enemy enemy) throws IOException {
//	15720  DATA うわー敵に当たって、われちゃった。
//	15725  RESTORE *KEY1051.IF
//	15730  GOSUB *TOOK
		took("うわー敵に当たって、われちゃった。");
//	15735  C=2
		this.btl.setC(2);
//	15740  GOSUB *TEKIATA.M
		tekiataM(enemy);
//	15745 RETURN
	}

	/**
	 * 15755 *KEY1052.IF.
	 */
	private void key1052if() {
//	15760  DATA うわー天井に当たって、われちゃった。
//	15765  RESTORE *KEY1052.IF
//	15770  GOSUB *TOOK
		took("うわー天井に当たって、われちゃった。");
//	15773  GOSUB *ANY.M
		anyM();
//	15775 RETURN
	}

	/**
	 * 15785 *KEY106.4.
	 * @param enemy 敵情報
	 * @throws IOException 入出力例外
	 */
	private void key1064(Enemy enemy) throws IOException {
//	15790  IF TDA=1 THEN GOTO *KEY106.IF
		if (this.btl.isTda()) {
			key106if(enemy);
			return;
		}
//	15795  LOCATE 4,24
		this.scr.locate(4, 24);
//	15800  PRINT "けど、危ないのでやめた。"
		this.scr.print("けど、危ないのでやめた。");
//	15803  GOSUB *ANY.M
		anyM();
//	15805 RETURN *KEY10.IF
		key10if();
	}

	/**
	 * 15815 *KEY106.IF.
	 * @param enemy 敵情報
	 * @throws IOException 入出力例外
	 */
	private void key106if(Enemy enemy) throws IOException {
//	15820  D=INT(RND(1)*10)+70
		int d = ((int) (Math.random() * 10)) + 70;
//	15825  THP=THP-D
		enemy.addThp(-d);
//	15830  GOSUB *BEEPL.M
//	15835  GOSUB *ANY.M
		anyM();
//	15840  GOSUB *PRINT.M
		printM();
//	15845  GOSUB *JIATA2.M
		jiata2M(enemy, d);
//	15850 RETURN
	}

	/**
	 * 15860 *TOOK.
	 * @param d$
	 */
	private void took(String d$) {
//	15865  READ D$
//	15870  LOCATE 4,24
		this.scr.locate(4, 24);
//	15875  PRINT D$
		this.scr.print(d$);
//	15880 RETURN
	}

	/**
	 * 15890 *VSYC.M
	 */
	private void vsycM(int vsyc) {
//	15895  LA=0
//	15900  WHILE LA<VSYC
//	15905   WAIT &H60,&H20,&HDF
//	15910    LA=LA+1
//	15915  WEND
//	15920 RETURN
		try {
			Thread.sleep(vsyc);
		} catch (InterruptedException e) {
			// 無視
		}
		repaint();
	}

	/**
	 * 15930 *PRINT.M.
	 */
	private void printM() {
//		15935  LOCATE 4,24
		this.scr.locate(4, 24);
//		15940  FOR  I=1 TO 4
		for (int i = 1; i <= 4; i++) {
//		15945   PRINT
			this.scr.print("");
//		15950  NEXT I
		}
//		15955 RETURN
	}

	/**
	 * 15965 *PRINTS.M.
	 */
	private void printsM() {
//	15970  FOR I=1 TO 18
//	15975   LOCATE 40,I
//	15980   PRINT SPC(39)
//	15985  NEXT
		this.scr.clearRect(319, 16, 305, 287);
//	15990  RETURN
	}

	/**
	 * 16000 *OKAERI.M.
	 */
	private void okaeriM() {
//	16005  LOCATE 4,24
		this.scr.locate(4, 24);
//	16010  PRINT "またどうぞ。"
		this.scr.print("またどうぞ。");
//	16015 RETURN
	}

	/**
	 * 16025 *NOMONEY.M.
	 */
	private void nomoneyM() {
//	16030  LOCATE 4,24
		this.scr.locate(4, 24);
//	16035  PRINT "お金が足りないよ！"
		this.scr.print("お金が足りないよ！");
//	16040  GOSUB *BEEPL.M
		beeplM();
//	16045 RETURN
	}

	/**
	 * 16055 *MOTENAI.M.
	 */
	private void motenaiM() {
//	16060  LOCATE 4,24
		this.scr.locate(4, 24);
//	16065  PRINT "もう、持てないよう！"
		this.scr.print("もう、持てないよう！");
//	16070  GOSUB *BEEPL.M
		beeplM();
//	16075 RETURN
	}

	/**
	 * 16085 *ARIGATOU.M.
	 */
	private void arigatouM() {
//	16090  LOCATE 4,24
		this.scr.locate(4, 24);
//	16095  PRINT "有難うごさいます。"
		this.scr.print("有難うごさいます。");
//	16100  GOSUB *BEEPS.M
//	16105 RETURN
	}
//	16115 *R
//	16120 RETURN

	private void anyM() {
//	16130 *ANY.M
//	16135  WHILE NOT INKEY$=""
//	16140  WEND
//	16145  WHILE     INKEY$=""
//	16150  WEND
		inkey$();
//	16155 RETURN
	}

	/**
	 * 16165 *WAKU.M.
	 */
	private void wakuM(int ox, int oy, int ex, int ey, int wc) {
//	16170  ZA=EX-OX
		int za = ex - ox;
//	16175  ZB=EY-OY
		int zb = ey - oy;
//	16180  ZC=ZA/2
		int zc = za / 2;
//	16185  ZD=ZB/2
		int zd = zb / 2;
//	16190  FOR I!=2 TO 1 STEP -1*ZA*.00001
		double i = 2;
		Point start;
		Point end;
		boolean isSleep = false;

		for (;;) {
			if (i < 1d) {
				break;
			}
//	16195   ZE=(ZC-(ZA/I!))
			int ze = zc - (int) (za / i);
//	16200   ZF=(ZD-(ZB/I!))
			int zf = zd - (int) (zb / i);
//	16205   LINE (ZC-ZE+OX,ZD-ZF+OY)-(ZC+ZE+OX,ZD+ZF+OY),WC,B
			start = new Point(zc - ze + ox, zd - zf + oy);
			end = new Point(zc + ze + ox, zd + zf + oy);
			this.scr.line(start, end, N88.PAL[wc]);
			repaint();
			if (isSleep) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// 無視
				}
			}
			isSleep = !isSleep;
//	16210   LINE (ZC-ZE+OX,ZD-ZF+OY)-(ZC+ZE+OX,ZD+ZF+OY), 0,B
			this.scr.line(start, end, Color.BLACK);
//	16215  NEXT I!
			i -= za * .00001;
		}
//	16220  LINE (OX,OY)-(ZA+OX,ZB+OY),WC,B
		start = new Point(ox, oy);
		end = new Point(za + ox, zb + oy);
		this.scr.line(start, end, Color.WHITE);
//	16225 RETURN
	}

	/**
	 * 16235 *TAITOL.
	 */
	private void taitol() {
//	16240  COLOR  =(8,&HDEE)
//	16245  OX=0
//	16250  OY=0
//	16255  EX=639
//	16260  EY=399
//	16265  WC=10
//	16270  GOSUB *WAKU.M
		wakuM(0, 0, 639, 399, 10);
//	16275  RESTORE *TAITOL.D
		int[][] taitolD = {
			{188,0x2352, 9},{204,0x236F, 0},{220,0x236C, 1},{236,0x2365, 2},
			{313,0x2350,10},{329,0x236C, 3},{345,0x2361, 4},{361,0x2379, 5},
			{377,0x2369, 0},{393,0x236E, 1},{409,0x2367, 2},{446,0x2347,11},
			{462,0x2361, 3},{478,0x236D, 4},{494,0x2365, 5}
		};
//	16280  FOR  I=1 TO 15
//	16285   READ A,B,C
//	16290   PUT@ (A,208),KANJI(B),,C,7
//	16295  NEXT I
		for (int[] words : taitolD) {
			Point p = new Point(words[0], 208);
			this.scr.put(p, words[1]);
		}
//	16300  VSYC=100
//	16305  GOSUB *VSYC.M
		vsycM(100);
		anyM();
//	16310  CLS 3
		this.scr.cls();
//	16315 RETURN
//	16325 *TAITOL.D
//	16330  DATA 188,&H2352, 9,204,&H236F, 0,220,&H236C, 1,236,&H2365, 2
//	16335  DATA 313,&H2350,10,329,&H236C, 3,345,&H2361, 4,361,&H2379, 5
//	16340  DATA 377,&H2369, 0,393,&H236E, 1,409,&H2367, 2,446,&H2347,11
//	16345  DATA 462,&H2361, 3,478,&H236D, 4,494,&H2365, 5
	}

	/**
	 * 16355 *SAVE.M.
	 * @throws IOException 入出力例外
	 */
	private void saveM() throws IOException {
//	16360  OPEN "SYS\NIKI" FOR OUTPUT AS #1
//	16365  WRITE #1,ATAP,ASHP,AHEP,AARP,EXPP!,MEXP!,TGI1,TGI2,TGI3,TGI4,GOLD!,MATI
//	16370  WRITE #1, VIT, LEV, SRN, MHP, MMP, ATA, ASH, AHE, AAR, KAI, MGK, SOU
//	16375  WRITE #1, DGX, DGY, DSX, DSY, TAU, QMP, LEV,  IQ,  HP,  MP,  GX,  GY
//	16380  WRITE #1,  SX,  SY, NA$, FE$,KEN$,    MAPF$,  ITM$(0),  ITM$(1),DIMEN
//	16385  CLOSE
//	16390 RETURN
		this.niki.save(this.map, this.sts);
	}

	/**
	 * 16400 *FANC.
	 */
	private void func() {
//	16405  RESTORE *FANC.D
//	16410  E=0
		int e = 0;
		int i = 1;
//	16415  FOR I=1 TO 10
		for (String d : FUNC_D) {
//	16420   IF I=6 THEN E=25
			if (i == 6) {
				e = 25;
			}
//	16425   READ A$
//	16430   FOR   J=1 TO 3
//	16435    B$=KMID$(A$,J,1)
//	16440     D=VAL("&H"+JIS$(B$))
//	16445    PUT (I*56+J*16+E-42,383),KANJI(D),PSET,0,8
			this.scr.put(new Point(i * 56 + 16 + e - 42, 385), d);
			i++;
//	16450   NEXT  J
//	16455  NEXT   I
		}
//	16465 RETURN
	}
//	16475 *FANC.D
//	16480  DATA 戦う　,逃げる,魔法　,調べる,話す
//	16485  DATA 昇る　,降りる,入る　,買う　,道具
	private static final String[] FUNC_D = { "戦う　", "逃げる", "魔法　", "調べる", "話す　",
			"昇る　", "降りる", "入る　", "買う　", "道具　" };

	/**
	 * 16495 *GAMENF.
	 */
	private void gamenf() {
//	16500  OX=0
//	16505  OY=0
//	16510  EX=639
//	16515  EY=399
//	16520  WC=8
//	16525  GOSUB *WAKU.M
		wakuM(0, 0, 639, 400, 8);
//	16530  OX=15
//	16535  OY=15
//	16540  EX=303
//	16545  EY=303
//	16550  WC=8
//	16555  GOSUB *WAKU.M
		wakuM(15, 15, 303, 303, 8);
//	16565  OX=318
//	16570  OY=15
//	16575  EX=624
//	16580  EY=303
//	16585  WC=8
//	16590  GOSUB *WAKU.M
		wakuM(318, 15, 624, 303, 8);
//	16600  OX=15
//	16605  OY=318
//	16610  EX=624
//	16615  EY=382
//	16620  WC=8
//	16625  GOSUB *WAKU.M
		wakuM(15, 318, 624, 384, 8);
//	16630  PAINT (319,1), 8,8
//	16635 RETURN
	}

	private void nedan() {
//	16645 *NEDAN.
//	16650  RESTORE *NEDAN.D
//	16655  FOR I=0 TO 5
//	16660   READ A$,B$,C$,D$,A,B,C
//	16665    SHA$(I)  =A$
//	16670    HEA$(I)  =B$
//	16675    ARA$(I)  =C$
//	16680    TAA$(I)  =D$
//	16685   NEDAN(0,I)=A
//	16690   NEDAN(1,I)=B
//	16695   NEDAN(2,I)=C
//	16700  NEXT I
//	16705 RETURN
//	16715 *NEDAN.D
//	16720  DATA "　　　　　　　　　　　　　　　　"
//	16725  DATA "　　　　　　　　　　　　　　　　"
//	16730  DATA "　　　　　　　　　　　　　　　　"
//	16735  DATA "　　　　　　　　　　　　　　　　"
//	16740  DATA    0,   0,   0
//	16745  DATA "ＷＯＯＤ　ＳＨＩＥＬＤ"
//	16750  DATA "ＰＬＡＳＴＩＣ　ＨＥＬＭ "
//	16755  DATA "ＬＥＡＴＨＥＲ　ＡＲＭＯＲ"
//	16760  DATA "ＳＨＯＲＴ　ＳＷＯＲＤ"
//	16765  DATA   30, 50, 50
//	16770  DATA "ＣＯＰＰＥＲ　ＳＨＩＥＬＤ"
//	16775  DATA "ＣＯＰＰＥＲ　ＨＥＬＭ"
//	16780  DATA "ＣＨＡＩＮ　ＭＡＩＬ"
//	16785  DATA "ＬＯＮＧ　ＳＷＯＲＤ"
//	16790  DATA  80, 100, 150
//	16795  DATA "ＩＲＯＮ　ＳＨＩＥＬＤ"
//	16800  DATA "ＩＲＯＮ　ＨＥＬＭ"
//	16805  DATA "ＤＵＲＡＬＵＭＩＮ　ＭＡＩＬ"
//	16810  DATA "ＳＡＢＥＲ"
//	16815  DATA  200,350,300
//	16820  DATA "ＤＵＲＡＬＵＭＩＮ　ＳＨＩＥＬＤ"
//	16825  DATA "ＤＵＲＡＬＵＭＩＮ　ＨＥＬＭ"
//	16830  DATA "ＴＩＴＡＮ　ＭＡＩＬ"
//	16835  DATA "ＳＬＡＹＥＹ　ｏｆ　ＤＲＡＧＯＮ"
//	16840  DATA  800,1200,1000
//	16845  DATA "ＴＩＴＡＮ　ＳＨＩＥＬＤ"
//	16850  DATA "ＴＩＴＡＮ　ＨＥＬＭ"
//	16855  DATA "１ＳＴ　ＣＬＡＳＳ　ＰＬＡＴＥ"
//	16860  DATA "ＭＵＲＡＭＡＳＡ　ＢＬＡＤＥ"
//	16865  DATA 2100,2800,4500
	}

	/**
	 * 16875 *HAJIMARI.
	 */
	private void hajimari() throws IOException {
		this.niki.reset(this.map, this.sts);
//	16880  LOCATE 18,20
		this.scr.locate(18, 20);
//	16885  PRINT "初めからする。　 Ｐｕｓｈ［１］ｋｅｙ";
		this.scr.print("初めからする。　 Ｐｕｓｈ［１］ｋｅｙ");
//	16890  LOCATE 18,21
		this.scr.locate(18, 21);
//	16895  PRINT "つづきをする。　 Ｐｕｓｈ［２］ｋｅｙ";
		this.scr.print("つづきをする。　 Ｐｕｓｈ［２］ｋｅｙ");
		while (StringUtils.isBlank(this.sts.getNa$())) {
//	16900   A$=INKEY$
//	16905  WHILE NOT A$="1" AND NOT A$="2"
//	16910   A$=INKEY$
//	16915  WEND
			int a$ = inkey$('1', '2', KeyEvent.VK_NUMPAD1, KeyEvent.VK_NUMPAD2);
//	16920  GOSUB *BEEPS.M
			beepsM();
//	16925  GOSUB *PRINT.M
			//printM();
//	16930  IF A$="1" THEN GOSUB *HAJIMETE.2
//	16935  IF A$="2" THEN GOSUB *TUZUKI.2
			if (a$ == '1' || a$ == KeyEvent.VK_NUMPAD1) {
				hajimete2();
			} else {
				// *TUZUKI.2
				if (!loadM()) {
					hajimete2();
				}
			}
		}
		printM();
//	16940  GOSUB *HYOUZI.M
		hyouziM();
//	16945  GOSUB *LOADMAPC.2
		loadmapc2();
//	16950  GOSUB *MAPLOAD.M
		maploadM();
//	16955  GOSUB *MAPHYOUZI.M
		maphyouziM();
//	16960  GOSUB *UEKAKU.M
		uekakuM();
//	16963  GOSUB *KEYON.M
//	16965 RETURN
	}

	private void beepsM() {
//	16975 *BEEPS.M
//	16980  BEEP 1
//	16985  VSYC=3
//	16990  GOSUB *VSYC.M
//	16995  BEEP 0
//	17000 RETURN
	}
	private void beeplM() {
//	17010 *BEEPL.M
//	17015  BEEP 1
//	17020  VSYC=12
//	17025  GOSUB *VSYC.M
//	17030  BEEP 0
//	17035 RETURN
	}

	/**
	 * 17045 *HAJIMETE.2.
	 */
	private void hajimete2() {
//	17050  ITM$(0)="　　　　"
//	17055  ITM$(1)="　　　　"
//	17060     KEN$="    GOOD"
		this.sts.setKen$("    GOOD");
//	17065    DIMEN=2
		this.map.setDimen(2);
//	17070     MEXP!=20
		this.sts.setMexp(20);
//	17075     MATI=1
		this.map.setMati(1);
//	17080     EXPP!=0
		this.sts.setExpp(0);
//	17085     GOLD!=270
		this.sts.setGold(270);
//	17090     ATAP=1
		this.sts.setAtap(1);
//	17095     ASHP=1
		this.sts.setAshp(1);
//	17100     AHEP=1
		this.sts.setAhep(1);
//	17105     AARP=1
		this.sts.setAarp(1);
//	17110      LEV=1
		this.sts.setLev(1);
//	17115      MGK=0
		this.sts.setMgk(0);
//	17120      SOU=1
		this.sts.setSou(1);
//	17125      TAU=0
		this.map.setTau(0);
//	17130      KAI=2
		this.map.setKai(2);
//	17135      DGX=1
		this.map.setDgx(1);
//	17140      DGY=1
		this.map.setDgy(1);
//	17145      DSX=4
		this.map.setDsx(4);
//	17150      DSY=5
		this.map.setDsy(5);
//	17155      QMP=0
		this.sts.setQmp(0);
//	17160       GX=0
		this.map.setGx(0);
//	17165       GY=0
		this.map.setGy(0);
//	17170       SX=4
//	17175       SY=3
		this.map.setPos(4, 3);
//	17180  LOCATE  2,20
//	17185  PRINT "名前を入れて下さい。（８文字まで ）．．．？"
//	17190  LOCATE 46,20
//	17195  INPUT "",NA$
		String name = JOptionPane.showInputDialog(this, "名前を入れて下さい。（８文字まで ）．．．？");
//	17200    NA$=AKCNV$(LEFT$(NA$,8))
		this.sts.setNa$(name);
//	17220  GOSUB *PRINT.M
		//printM();
//	17225  GOSUB *SUTEITASU.M
		suteitasuM();
//	17230       HP=(LEV+VIT)*2
		int lev = this.sts.getLev();
		int vit = this.sts.getVit();
		int hp = (lev + vit) * 2;
		this.sts.setHp(hp);
//	17235       MP=INT(MGK*(LEV+IQ))
		int mgk = this.sts.getMgk();
		int iq = this.sts.getIq();
		int mp = mgk * (lev + iq);
		this.sts.setMp(mp);
//	17240       MHP=HP
		this.sts.setMhp(hp);
//	17245       MMP=MP
		this.sts.setMmp(mp);
//	17250  GOSUB *FDANA.M
		fdanaM();
//	17255 RETURN
		this.btl.setTda(false);
	}

//  17265 *TUZUKI.2.
//	17270  GOSUB *LOAD.M
//	17275 RETURN

	/**
	 * 17285 *SUTEITASU.M.
	 */
	private void suteitasuM() {
		int iq = this.sts.getIq();
		int lev = this.sts.getLev();
		int vit = this.sts.getVit();
		int srn = this.sts.getSrn();
		double rnd = Math.random() + Math.random() + Math.random();
//	17290   IQ=2+INT((RND(2)+RND(2)+RND(2))/3*10)+ IQ+LEV
		iq = 2 + ((int) ((rnd / 3) * 10)) + iq + lev;
		this.sts.setIq(iq);
//	17295  VIT=2+INT((RND(1)+RND(1)+RND(1))/3*10)+VIT+LEV
		rnd = Math.random() + Math.random() + Math.random();
		vit = 2 + ((int) ((rnd / 3) * 10)) + vit + lev;
		this.sts.setVit(vit);
//	17300  SRN=2+INT((RND(3)+RND(3)+RND(3))/3*10)+SRN+LEV
		rnd = Math.random() + Math.random() + Math.random();
		srn = 2 + ((int) ((rnd / 3) * 10)) + srn + lev;
		this.sts.setSrn(srn);
//	17305 RETURN
	}

	/**
	 * 17315 *FDANA.M.
	 */
	private void fdanaM() {
//	17320  MAPF$="DAN\A"+MID$(STR$(GX),2,2)+MID$(STR$(GY),2,2)+MID$(STR$(KAI),2,2)
		this.map.setMapf$dan("DAN\\A");
//	17325 RETURN
	}
	/**
	 * 17335 *FDANC.M.
	 */
	private void fdancM() {
//	17340  MAPF$="DAN\C"+MID$(STR$(GX),2,2)+MID$(STR$(GY),2,2)+MID$(STR$(KAI),2,2)
		this.map.setMapf$dan("DAN\\C");
//	17345 RETURN
	}
	/**
	 * 17355 *FDAND.M.
	 */
	private void fdandM() {
//	17360  MAPF$="DAN\D"+MID$(STR$(GX),2,2)+MID$(STR$(GY),2,2)+MID$(STR$(KAI),2,2)
		this.map.setMapf$dan("DAN\\D");
//	17365 RETURN
	}
	/**
	 * 17375 *FDANE.M.
	 */
	private void fdaneM() {
//	17380  MAPF$="DAN\E"+FE$
//	17385  MAPF$=MAPF$+MID$(STR$(GX),2,2)+MID$(STR$(GY),2,2)+MID$(STR$(KAI),2,2)
		this.map.setMapf$danE();
//	17390 RETURN
	}

	/**
	 * 17400 *FDANF.M.
	 */
	private void fdanfM() {
//	17405  IF GX<0 AND GY<0 THEN GOTO *FDANF1.G
//	17410  IF GX<0          THEN GOTO *FDANF2.G
//	17415  IF GY<0          THEN GOTO *FDANF3.G
//	17420  MAPF$="DAN\F"+FE$+MID$(STR$(GX),2,2)+MID$(STR$(GY),2,2)
		this.map.setMapf$danF();
//	17425 RETURN
//	17435 *FDANF1.G
//	17440     A$=MID$(STR$(GX),1,2)
//	17445     B$=MID$(STR$(GY),1,2)
//	17450  MAPF$="DAN\F"+FE$+A$+B$
//	17455 RETURN
//	17465 *FDANF2.G
//	17470     A$=MID$(STR$(GX),1,2)
//	17475     B$=MID$(STR$(GY),2,1)
//	17480  MAPF$="DAN\F"+FE$+A$+B$
//	17485 RETURN
//	17495 *FDANF3.G
//	17500     B$=MID$(STR$(GY),1,2)
//	17505     A$=MID$(STR$(GX),2,1)
//	17510  MAPF$="DAN\F"+FE$+A$+B$
//	17515 RETURN
//	17525 *FMAP.M
//	17530  MAPF$="MAP\MAP"+MID$(STR$(GY),2,2)+"."+MID$(STR$(GX),2,2)
//	17535 RETURN
	}

	/**
	 * 17545 *LOAD.M.
	 * @return 復元出来ているっぽい場合はtrue、ファイルが存在しない場合はfalse
	 * @throws IOException 入出力例外
	 */
	private boolean loadM() throws IOException {
//	17550  OPEN "SYS\NIKI" FOR INPUT  AS #1
//	17555  INPUT #1,ATAP,ASHP,AHEP,AARP,EXPP!,MEXP!,TGI1,TGI2,TGI3,TGI4,GOLD!,MATI
//	17560  INPUT #1, VIT, LEV, SRN, MHP, MMP, ATA, ASH, AHE, AAR, KAI, MGK, SOU
//	17565  INPUT #1, DGX, DGY, DSX, DSY, TAU, QMP, LEV,  IQ,  HP,  MP,  GX,  GY
//	17570  INPUT #1,  SX,  SY, NA$, FE$,KEN$,    MAPF$,  ITM$(0),  ITM$(1),DIMEN
//	17575  CLOSE
//	17580  GOSUB *PRINT.M
//	17585 RETURN
		return this.niki.load(this.map, this.sts);
	}

	/**
	 * 17595 *MAPLOAD.M.
	 */
	private void maploadM() throws IOException {
//	17600  DEF SEG=VARPTR(MAP.!(0,0),1)
//	17605  BLOAD MAPF$,VARPTR(MAP.!(0,0),0)
		this.map.bload();
//	17610 RETURN
	}

	/**
	 * ステータス表示.
	 * 17620 *HYOUZI.M.
	 */
	private void hyouziM() {
		printsM();
//	17625  LOCATE 42,1
		this.scr.locate(42, 1);
//	17630  PRINT NA$
		this.scr.print(this.sts.getNa$());
//	17635  A=78-LEN(KEN$)*2
//	17640  LOCATE  A,1
		this.scr.locate(78, 1);
//	17645  PRINT AKCNV$(KEN$)
		this.scr.print(this.sts.getKen$(), Align.RIGHT);
//	17650  LOCATE 42,3
		this.scr.locate(42, 3);
//	17655  PRINT "ＳＴＲＥＮＧＴＨ"
		this.scr.print("ＳＴＲＥＮＧＴＨ");
//	17660  A=78-LEN(STR$(SRN))*2
//	17665  LOCATE  A,3
		this.scr.locate(78, 3);
//	17670  PRINT AKCNV$(STR$(SRN))
		this.scr.print(this.sts.getSrn(), Align.RIGHT);
//	17675  LOCATE 42,4
		this.scr.locate(42, 4);
//	17680  PRINT "Ｉ．Ｑ"
		this.scr.print("Ｉ．Ｑ");
//	17685  A=78-LEN(STR$(IQ))*2
//	17690  LOCATE  A,4
		this.scr.locate(78, 4);
//	17695  PRINT AKCNV$(STR$(IQ))
		this.scr.print(this.sts.getIq(), Align.RIGHT);
//	17700  LOCATE 42,5
		this.scr.locate(42, 5);
//	17705  PRINT "ＶＩＴＡＬＩＴＹ"
		this.scr.print("ＶＩＴＡＬＩＴＹ");
//	17710  A=78-LEN(STR$(VIT))*2
//	17715  LOCATE  A,5
		this.scr.locate(78, 5);
//	17720  PRINT AKCNV$(STR$(VIT))
		this.scr.print(this.sts.getVit(), Align.RIGHT);
//	17725  LOCATE 42,7
		this.scr.locate(42, 7);
//	17730  PRINT "ＬＥＶＥＬ"
		this.scr.print("ＬＥＶＥＬ");
//	17735  A=78-LEN(STR$(LEV))*2
//	17740  LOCATE  A,7
		this.scr.locate(78, 7);
//	17745  PRINT AKCNV$(STR$(LEV))
		this.scr.print(this.sts.getLev(), Align.RIGHT);
//	17750  LOCATE 42,8
		this.scr.locate(42, 8);
//	17755  PRINT "Ｈ．Ｐ．"
		this.scr.print("Ｈ．Ｐ．");
//	17760  A=78-LEN(STR$(HP)+STR$(MHP))*2
//	17765  LOCATE  A,8
		this.scr.locate(78, 8);
//	17770  PRINT AKCNV$(STR$(HP));"／";MID$(AKCNV$(STR$(MHP)),3,78-A)
		String hp = this.sts.getHp() + "／" + this.sts.getMhp();
		this.scr.print(hp, Align.RIGHT);
//	17775  LOCATE 42,9
		this.scr.locate(42, 9);
//	17780  PRINT "Ｍ．Ｐ．"
		this.scr.print("Ｍ．Ｐ．");
//	17785  A=78-LEN(STR$(MP)+STR$(MMP))*2
//	17790  LOCATE  A,9
		this.scr.locate(78, 9);
//	17795  PRINT AKCNV$(STR$(MP));"／";MID$(AKCNV$(STR$(MMP)),3,78-A)
		String mp = this.sts.getMp() + "／" + this.sts.getMmp();
		this.scr.print(mp, Align.RIGHT);
//	17800  LOCATE 42,10
		this.scr.locate(42, 10);
//	17805  PRINT "ＥＸＰ．"
		this.scr.print("ＥＸＰ．");
//	17810  A=78-LEN(STR$(EXPP!)+STR$(MEXP!))*2
//	17815  LOCATE  A,10
		this.scr.locate(78, 10);
//	17820  PRINT AKCNV$(STR$(EXPP!));"／";MID$(AKCNV$(STR$(MEXP!)),3,78-A)
		String exp = this.sts.getExpp() + "／" + this.sts.getMexp();
		this.scr.print(exp, Align.RIGHT);
//	17825  LOCATE 42,11
		this.scr.locate(42, 11);
//	17830  PRINT "ＧＯＬＤ"
		this.scr.print("ＧＯＬＤ");
//	17835  A=78-LEN(STR$(GOLD!))*2
//	17840  LOCATE  A,11
		this.scr.locate(78, 11);
//	17845  PRINT AKCNV$(STR$(GOLD!))
		this.scr.print(this.sts.getGold(), Align.RIGHT);
//	17850  LOCATE 42,13
		this.scr.locate(42, 13);
//	17855  PRINT SHA$(ASH)
//	17860  LOCATE 42,14
		this.scr.locate(42, 14);
//	17865  PRINT HEA$(AHE)
//	17870  LOCATE 42,15
		this.scr.locate(42, 15);
//	17875  PRINT ARA$(AAR)
//	17880  LOCATE 42,16
		this.scr.locate(42, 16);
//	17885  PRINT TAA$(ATA)
//	17890  LOCATE 44,17
		this.scr.locate(44, 17);
//	17895  PRINT ITM$(0)
		String itm$[] = this.sts.getItm$();
		this.scr.print(itm$[0]);
//	17900  LOCATE 44,18
		this.scr.locate(44, 18);
//	17905  PRINT ITM$(1)
		this.scr.print(itm$[1]);
//	17910 RETURN
	}

	/**
	 * 17920 *LOADMAPC.2.
	 * @throws IOException 入出力例外
	 */
	private void loadmapc2() throws IOException {
//	17925  DEF SEG=SEGPTR(3)
//	17930  DEF SEG=VARPTR(MAC(0,0),1)
//	17935  BLOAD "MAPC\MAC.ALL",VARPTR(MAC(0,0),0 )
		this.map.bload("MAPC\\MAC.ALL");
//	17940  DEF SEG=VARPTR(G2(0),1)
//	17945  BLOAD "MAPC\G2",VARPTR(G2(0),0 )
		this.map.bload("MAPC\\G2");
//	17950  DEF SEG=VARPTR(G4(0),1)
//	17955  BLOAD "MAPC\G4",VARPTR(G4(0),0 )
		this.map.bload("MAPC\\G4");
//	17960  DEF SEG=VARPTR(G6(0),1)
//	17965  BLOAD "MAPC\G6",VARPTR(G6(0),0 )
		this.map.bload("MAPC\\G6");
//	17970  DEF SEG=VARPTR(G8(0),1)
//	17975  BLOAD "MAPC\G8",VARPTR(G8(0),0 )
		this.map.bload("MAPC\\G8");
//	17980 RETURN
	}

	/**
	 * 17990 *MAPHYOUZI.M.
	 */
	private void maphyouziM() {
//	17995  FOR   I=0 TO 8
		for (int i = 0; i <= 8; i++) {
//	18000   FOR  J=0 TO 8
			for (int j = 0; j <= 8; j++) {
//	18005    A=VAL(RIGHT$(STR$(MAP.!(I,J)),2))
				int a = this.map.getMap(i, j);
				byte[] mac = this.map.getMac(a);
//	18010    PUT@ (16+32*I,16+32*J),MAC(315,A),PSET
				this.scr.put(16 + 32 * i, 16 + 32 * j, mac);
//	18015   NEXT J
//this.scr.locate(2 + i * 4, 1 + j * 2);
//this.scr.print("" + a);
			}
//	18020  NEXT  I
		}
//	18025 RETURN
	}

	/**
	 * 18035 *MIGIKESHI.M.
	 */
	private void migikeshiM() {
		int sx = this.map.getSx();
		int sy = this.map.getSy();
		int a = this.map.getMap();
//	18040  SX=SX+1
//	18045   A=VAL(RIGHT$(STR$(MAP.!(SX-1,SY)),2))
//	18050  PUT@ (16+32*(SX-1),16+32*SY),MAC(315,A),PSET
		this.scr.put(16 + 32 * sx, 16 + 32 * sy, this.map.getMac(a));
		this.map.addSx(1);
//	18055 RETURN
	}

	/**
	 * 18065 *MIGIKAKU.M.
	 */
	private void migikakuM() {
//	18070  PUT@ (16+32*SX,16+32*SY)    ,G6        ,PSET
		int x = 16 + 32 * this.map.getSx();
		int y = 16 + 32 * this.map.getSy();
		this.scr.put(x, y, this.map.getG6());
//	18075 RETURN
	}

	/**
	 * 18085 *SHITAKESHI.M
	 */
	private void shitakeshiM() {
		int sx = this.map.getSx();
		int sy = this.map.getSy();
		int a = this.map.getMap();
//	18090  SY=SY+1
//	18095   A=VAL(RIGHT$(STR$(MAP.!(SX,SY-1)),2))
//	18100  PUT@ (16+32*SX,16+32*(SY-1)),MAC(315,A),PSET
		this.scr.put(16 + 32 * sx, 16 + 32 * sy, this.map.getMac(a));
		this.map.addSy(1);
//	18105 RETURN
	}

	/**
	 * 18115 *SHITAKAKU.M.
	 */
	private void shitakakuM() {
//	18120  PUT@ (16+32*SX,16+32*SY)    ,G2        ,PSET
		int x = 16 + 32 * this.map.getSx();
		int y = 16 + 32 * this.map.getSy();
		this.scr.put(x, y, this.map.getG2());
//	18125 RETURN
	}

	/**
	 * 18135 *HIDARIKESHI.M.
	 */
	private void hidarikeshiM() {
		int sx = this.map.getSx();
		int sy = this.map.getSy();
		int a = this.map.getMap();
//	18140  SX=SX-1
//	18145   A=VAL(RIGHT$(STR$(MAP.!(SX+1,SY)),2))
//	18150  PUT@ (16+32*(SX+1),16+32*SY),MAC(315,A),PSET
		this.scr.put(16 + 32 * sx, 16 + 32 * sy, this.map.getMac(a));
		this.map.addSx(-1);
//	18155 RETURN
	}

	/**
	 * 18165 *HIDARIKAKU.M.
	 */
	private void hidarikakuM() {
//	18170  PUT@ (16+32*SX,16+32*SY)    ,G4        ,PSET
		int x = 16 + 32 * this.map.getSx();
		int y = 16 + 32 * this.map.getSy();
		this.scr.put(x, y, this.map.getG4());
//	18175 RETURN
	}

	/**
	 * 18185 *UEKESHI.M.
	 */
	private void uekeshiM() {
		int sx = this.map.getSx();
		int sy = this.map.getSy();
		int a = this.map.getMap();
//	18190  SY=SY-1
//	18195   A=VAL(RIGHT$(STR$(MAP.!(SX,SY+1)),2))
//	18200  PUT@ (16+32*SX,16+32*(SY+1)),MAC(315,A),PSET
		this.scr.put(16 + 32 * sx, 16 + 32 * sy, this.map.getMac(a));
		this.map.addSy(-1);
//	18205 RETURN
	}

	/**
	 * 18215 *UEKAKU.M.
	 */
	private void uekakuM() {
//	18220  PUT@ (16+32*SX,16+32*SY)    ,G8        ,PSET
		int x = 16 + 32 * this.map.getSx();
		int y = 16 + 32 * this.map.getSy();
		this.scr.put(x, y, this.map.getG8());
//	18225 RETURN
	}
//	18235 *KEYON.M
//	18240  KEY(1)OFF
//	18245  KEY(2)OFF
//	18250  KEY(3)ON
//	18255  KEY(4)ON
//	18260  KEY(5)ON
//	18265  KEY(6)ON
//	18270  KEY(7)ON
//	18275  KEY(8)ON
//	18280  KEY(9)OFF
//	18285  KEY(10)ON
//	18290 RETURN

	/**
	 * 18300 *GAME.
	 * @throws IOException 入出力例外
	 */
	private void game() throws IOException {
		for (;;) {
			int ink = inkey$();
			if (ink == KeyEvent.VK_F3 || ink == KeyEvent.VK_M) {
				// 魔法
				key3y(null);
				continue;
			} else if (ink == KeyEvent.VK_F4 || ink == KeyEvent.VK_S) {
				// 調べる
				key4y();
				continue;
			} else if (ink == KeyEvent.VK_F5 || ink == KeyEvent.VK_T || ink == KeyEvent.VK_SPACE) {
				// 話す
				key5y();
				continue;
			} else if (ink == KeyEvent.VK_F6 || ink == KeyEvent.VK_F) {
				// 昇る
				key6y();
				continue;
			} else if (ink == KeyEvent.VK_F7 || ink == KeyEvent.VK_D) {
				key7y(); // 降りる
				continue;
			} else if (ink == KeyEvent.VK_F8 || ink == KeyEvent.VK_E || ink == KeyEvent.VK_ENTER) {
				key8y(); // 入る
				continue;
			} else if (ink == KeyEvent.VK_F10 || ink == KeyEvent.VK_I) {
				key10y(null); // 道具
				continue;
			}
//	18305  WHILE  INK<1 OR INK>9
//	18310    INK=INP(&H41)-66
//	18315   VSYC=7
//	18320   GOSUB *VSYC.M
			vsycM(7);
//	18325  WEND
//	18330  GOSUB *PRINT.M
			printM();
//	18335  GOSUB *IDOU.2
			idou2(ink);
			if (this.sts.isGameover()) {
				break;
			}
			int val = this.map.getMap();
//	18340  IF MAP.!(SX,SY)=19 OR MAP.!(SX,SY)=20            THEN GOSUB *SHIRO.2
			if (val == 19 || val == 20) {
				shiro2();
			}
//	18345  IF MAP.!(SX,SY)=17 OR MAP.!(SX,SY)=18            THEN GOSUB *MACHI.2
			if (val == 17 || val == 18) {
				machi2();
			}
//	18350  IF MAP.!(SX,SY)=15 OR MAP.!(SX,SY)=16            THEN GOSUB *MURA.2
			if (val == 15 || val == 16) {
				mura2();
			}
//	18355  IF MAP.!(SX,SY)=21                               THEN GOSUB *TOU.2
			if (val == 21) {
				tou2();
			}
//	18360  IF MAP.!(SX,SY)=22                               THEN GOSUB *DOUKUTU.2
			if (val == 22) {
				doukutu2();
			}
			int dimen = this.map.getDimen();
			int gx = this.map.getGx();
			int gy = this.map.getGy();
			int sx = this.map.getSx();
			int sy = this.map.getSy();
//	18365  IF DIMEN>1 AND GX=0 AND GY=0 AND SY=8 AND SX=2   THEN GOSUB *DERU.2
			if (1 < dimen && gx == 0 && gy == 0 && sy == 8 && sx == 2) {
				deru2();
			}
			double tau = this.map.getTau() * 2.3;
			int rnd = (int) (((Math.random() + Math.random() + Math.random()) / 3.0d) * 25d);
//	18370  IF INT((RND(1)+RND(1)+RND(1))/3*25)<TAU*2.3      THEN GOSUB *SENTOU.2
			if (rnd < tau) {
				sentou2();
				if (this.sts.isGameover()) {
					break;
				}
			}
//	18375 GOTO *GAME.
		}
		this.sts.setGameover(false);
	}

	/**
	 * 18385 *IDOU.2.
	 * @param c
	 * @throws IOException 入出力例外
	 */
	private void idou2(int c) throws IOException {
//	18390  ON INK GOSUB *INK8.3,*R,*R,*INK4.3,*R,*INK6.3,*R,*R,*INK2.3
		switch (c) {
		case KeyEvent.VK_UP:
		case KeyEvent.VK_NUMPAD8:
			ink83();
			break;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_NUMPAD4:
			ink43();
			break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_NUMPAD6:
			ink63();
			break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_NUMPAD2:
			ink23();
			break;
		default:
			break;
		}
//	18395      INK=0
//	18400  IF NOT KEN$="    GOOD" THEN GOSUB *IDOU.3
		if (!"    GOOD".equals(this.sts.getKen$())) {
			this.sts.addHp(-1);
		}
//	18405  IF      HP<=0          THEN GOTO  *GAMEOVER.M
		if (this.sts.getHp() < 0) {
			gameoverM();
			return;
		}
//	18410  GOSUB *HYOUZI.M
		hyouziM();
//	18415 RETURN
	}

//	18425 *IDOU.3
//	18430  HP=HP-1
//	18435 RETURN

	/**
	 * 18445 *INK8.3.
	 * @throws IOException 入出力例外
	 */
	private void ink83() throws IOException {
		int sx = this.map.getSx();
		int sy = this.map.getSy();
//	18450  IF SY=0              THEN GOTO  *INK8.G
		if (sy == 0) {
			ink8g();
			return;
		}
//	18455  IF MAP.!(SX,SY-1)<31 THEN GOSUB *UEKESHI.M
		if (this.map.getMap(sx, sy - 1) < 31) {
			uekeshiM();
		}
//	18460  GOSUB *UEKAKU.M
		uekakuM();
//	18465  RETURN
	}

	/**
	 * 18475 *INK8.G.
	 * @throws IOException 入出力例外
	 */
	private void ink8g() throws IOException {
//	18480  GY=GY-1
		this.map.addGy(-1);
//	18485  SY=8
		this.map.setSy(8);
//	18490  ON DIMEN GOSUB *FMAP.M,*FDANA.M,*FDANC.M,*FDAND.M,*FDANE.M,*FDANF.M
		int dimen = this.map.getDimen();
		if (dimen == 1) {
			this.map.setMapf$map();
		} else if (dimen == 2) {
			fdanaM();
		} else if (dimen == 3) {
			fdancM();
		} else if (dimen == 4) {
			fdandM();
		} else if (dimen == 5) {
			fdaneM();
		} else if (dimen == 6) {
			fdanfM();
		}
//	18495  GOSUB *MAPLOAD.M
		maploadM();
//	18500  GOSUB *MAPHYOUZI.M
		maphyouziM();
//	18505  GOSUB *UEKAKU.M
		uekakuM();
//	18510 RETURN
	}

	/**
	 * 18520 *INK4.3.
	 * @throws IOException 入出力例外
	 */
	private void ink43() throws IOException {
		int sx = this.map.getSx();
		int sy = this.map.getSy();
//	18525  IF SX=0              THEN GOTO  *INK4.G
		if (sx == 0) {
			ink4g();
			return;
		}
//	18530  IF MAP.!(SX-1,SY)<31 THEN GOSUB *HIDARIKESHI.M
		if (this.map.getMap(sx - 1, sy) < 31) {
			hidarikeshiM();
		}
//	18535  GOSUB *HIDARIKAKU.M
		hidarikakuM();
//	18540 RETURN
	}

	/**
	 * 18550 *INK4.G.
	 * @throws IOException
	 */
	private void ink4g() throws IOException {
//	18555  GX=GX-1
		this.map.addGx(-1);
//	18560  SX=8
		this.map.setSx(8);
//	18565  ON DIMEN GOSUB *FMAP.M,*FDANA.M,*FDANC.M,*FDAND.M,*FDANE.M,*FDANF.M
		int dimen = this.map.getDimen();
		if (dimen == 1) {
			this.map.setMapf$map();
		} else if (dimen == 2) {
			fdanaM();
		} else if (dimen == 3) {
			fdancM();
		} else if (dimen == 4) {
			fdandM();
		} else if (dimen == 5) {
			fdaneM();
		} else if (dimen == 6) {
			fdanfM();
		}
//	18570  GOSUB *MAPLOAD.M
		maploadM();
//	18575  GOSUB *MAPHYOUZI.M
		maphyouziM();
//	18580  GOSUB *HIDARIKAKU.M
		hidarikakuM();
//	18585 RETURN
	}

	/**
	 * 18595 *INK2.3.
	 * @throws IOException 入出力例外
	 */
	private void ink23() throws IOException {
		int sx = this.map.getSx();
		int sy = this.map.getSy();
//	18600  IF SY=8              THEN GOTO  *INK2.G
		if (sy == 8) {
			ink2g();
			return;
		}
//	18605  IF MAP.!(SX,SY+1)<31 THEN GOSUB *SHITAKESHI.M
		if (this.map.getMap(sx, sy + 1) < 31) {
			shitakeshiM();
		}
//	18610  GOSUB *SHITAKAKU.M
		shitakakuM();
//	18615  RETURN
	}

	/**
	 * 18625 *INK2.G.
	 * @throws IOException
	 */
	private void ink2g() throws IOException {
//	18630  GY=GY+1
		this.map.addGy(1);
//	18635  SY=0
		this.map.setSy(0);
//	18640  ON DIMEN GOSUB *FMAP.M,*FDANA.M,*FDANC.M,*FDAND.M,*FDANE.M,*FDANF.M
		int dimen = this.map.getDimen();
		if (dimen == 1) {
			this.map.setMapf$map();
		} else if (dimen == 2) {
			fdanaM();
		} else if (dimen == 3) {
			fdancM();
		} else if (dimen == 4) {
			fdandM();
		} else if (dimen == 5) {
			fdaneM();
		} else if (dimen == 6) {
			fdanfM();
		}
//	18645  GOSUB *MAPLOAD.M
		maploadM();
//	18650  GOSUB *MAPHYOUZI.M
		maphyouziM();
//	18655  GOSUB *SHITAKAKU.M
		shitakakuM();
//	18660 RETURN
	}

	/**
	 * 18670 *INK6.3.
	 * @throws IOException 入出力例外
	 */
	private void ink63() throws IOException {
		int sx = this.map.getSx();
		int sy = this.map.getSy();
//	18675  IF SX=8              THEN GOTO  *INK6.G
		if (sx == 8) {
			ink6g();
			return;
		}
//	18680  IF MAP.!(SX+1,SY)<31 THEN GOSUB *MIGIKESHI.M
		if (this.map.getMap(sx + 1, sy) < 31) {
			migikeshiM();
		}
//	18685  GOSUB *MIGIKAKU.M
		migikakuM();
//	18690 RETURN
	}

	/**
	 * 18700 *INK6.G.
	 * @throws IOException 入出力例外
	 */
	private void ink6g() throws IOException {
//	18705  GX=GX+1
		this.map.addGx(1);
//	18710  SX=0
		this.map.setSx(0);
//	18715  ON DIMEN GOSUB *FMAP.M,*FDANA.M,*FDANC.M,*FDAND.M,*FDANE.M,*FDANF.M
		int dimen = this.map.getDimen();
		if (dimen == 1) {
			this.map.setMapf$map();
		} else if (dimen == 2) {
			fdanaM();
		} else if (dimen == 3) {
			fdancM();
		} else if (dimen == 4) {
			fdandM();
		} else if (dimen == 5) {
			fdaneM();
		} else if (dimen == 6) {
			fdanfM();
		}
//	18720  GOSUB *MAPLOAD.M
		maploadM();
//	18725  GOSUB *MAPHYOUZI.M
		maphyouziM();
//	18730  GOSUB *MIGIKAKU.M
		migikakuM();
//	18735 RETURN
	}

	/**
	 * 18745 *GAMEOVER.M.
	 * @throws IOException 入出力例外
	 */
	private void gameoverM() throws IOException {
//	18750  COLOR=(8,&HF0)
//	18755  DEF SEG=VARPTR(TEKI(0),1)
//	18760  BLOAD "SYS\OVER",VARPTR(TEKI(0),0)
		this.map.bload("SYS\\OVER");
//	18765  GOSUB *FADEOUT.M
		fadeoutM();
//	18770  PUT@ (60,16),TEKI,PSET
		this.scr.put(60, 15, this.map.getTeki());
//	18775  GOSUB *FADEIN.M
		fadeinM();
//	18780  LOCATE 30,24
		this.scr.locate(30, 24);
//	18785  PRINT "ＧＡＭＥ　ＯＶＥＲ"
		this.scr.print("ＧＡＭＥ　ＯＶＥＲ");
//	18790  GOSUB *ANY.M
		anyM();
//	18795  GOTO  *RETRY
		printM();
		this.sts.setGameover(true);
	}

	private void fadeoutM() {
//	18805 *FADEOUT.M
//	18810  FOR I=15 TO 0 STEP -1
//	18815   VSYC=6
//	18820   GOSUB *VSYC.M
		vsycM(6);
//	18825   COLOR=(1,I)
//	18830   COLOR=(2,I*16)
//	18835   COLOR=(3,I*16+I)
//	18840   COLOR=(4,I*256)
//	18845   COLOR=(5,I*256+I)
//	18850   COLOR=(6,I*256+I*16)
//	18855   COLOR=(7,I*256+I*16+I)
//	18860  NEXT
//	18865  FOR I=10 TO 0 STEP -1
//	18870   VSYC=8
//	18875   GOSUB *VSYC.M
//	18880   COLOR=(9,I)
//	18885   COLOR=(10,I*16)
//	18890   COLOR=(11,I*16+I)
//	18895   COLOR=(12,I*256)
//	18900   COLOR=(13,I*256+I)
//	18905   COLOR=(14,I*256+I*16)
//	18910   COLOR=(15,I*256+I*16+I)
//	18915  NEXT
//	18920  LINE (16,16)-(304,304),0,BF
//	18925 RETURN
	}

	private void fadeinM() {
//	18935 *FADEIN.M
//	18940  FOR I=0 TO 15
//	18945   VSYC=6
//	18950   GOSUB *VSYC.M
//	18955   COLOR=(1,I)
//	18960   COLOR=(2,I*16)
//	18965   COLOR=(3,I*16+I)
//	18970   COLOR=(4,I*256)
//	18975   COLOR=(5,I*256+I)
//	18980   COLOR=(6,I*256+I*16)
//	18985   COLOR=(7,I*256+I*16+I)
//	18990  NEXT
//	18995  FOR I=0 TO 10
//	19000   VSYC=8
//	19005   GOSUB *VSYC.M
		vsycM(8);
//	19010   COLOR=(9,I)
//	19015   COLOR=(10,I*16)
//	19020   COLOR=(11,I*16+I)
//	19025   COLOR=(12,I*256)
//	19030   COLOR=(13,I*256+I)
//	19035   COLOR=(14,I*256+I*16)
//	19040   COLOR=(15,I*256+I*16+I)
//	19045  NEXT
//	19050 RETURN
	}

	/**
	 * 19060 *SHIRO.2.
	 * @throws IOException 入出力例外
	 */
	private void shiro2() throws IOException {
//	19065  IF   MATI=1      THEN GOTO  *SHIRO3.G
		if (this.map.getMati() == 1) {
			shiro3g();
			return;
		}
//	19070    KAI=0
		this.map.setKai(0);
//	19075    TAU=5
		this.map.setTau(5);
//	19080  DIMEN=2
		this.map.setDimen(2);
//	19085    DGX=GX
//	19090    DGY=GY
//	19095    DSX=SX
//	19100    DSY=SY
		this.map.pushD();
//	19105     SY=7
//	19110     SX=2
		this.map.setPos(2, 7);
//	19115     GY=0
//	19120     GX=0
		this.map.setGx(0);
		this.map.setGy(0);
//	19125  IF DGX=1 AND DGY=1 THEN GOSUB *SHIRO2.3
		if (this.map.getDgx() == 1 && this.map.getDgy() == 1) {
			shiro23();
		}
//	19130  GOSUB *FDANA.M
		fdanaM();
//	19135  GOSUB *MAPLOAD.M
		maploadM();
//	19140  GOSUB *MAPHYOUZI.M
		maphyouziM();
//	19145  GOSUB *UEKAKU.M
		uekakuM();
//	19150 RETURN
	}

	/**
	 * 19160 *SHIRO2.3.
	 */
	private void shiro23() {
//	19165   TAU=0
		this.map.setTau(0);
//	19170   KAI=1
		this.map.setKai(1);
//	19175  MATI=1
		this.map.setMati(1);
//	19180 RETURN
	}

	/**
	 * 19190 *SHIRO3.G.
	 * @throws IOException 入出力例外
	 */
	private void shiro3g() throws IOException {
//	19195  KAI=2
		this.map.setKai(2);
//	19200   SY=7
//	19205   SX=2
		this.map.setPos(2, 7);
//	19210   GX=0
		this.map.setGx(0);
//	19215   GY=0
		this.map.setGy(0);
//	19220  GOSUB *FDANA.M
		fdanaM();
//	19225  GOSUB *MAPLOAD.M
		maploadM();
//	19230  GOSUB *MAPHYOUZI.M
		maphyouziM();
//	19235  GOSUB *UEKAKU.M
		uekakuM();
//	19240 RETURN
	}

	/**
	 * 19250 *MACHI.2.
	 * @throws IOException 入出力例外
	 */
	private void machi2() throws IOException {
		int gx = this.map.getGx();
		int gy = this.map.getGy();
//	19255  IF     GX=5  THEN MATI=2
		if (gx == 5) {
			this.map.setMati(2);
		}
//	19260  IF     GX=10 THEN MATI=3
		if (gx == 10) {
			this.map.setMati(3);
		}
//	19265  IF     GX=9  THEN MATI=4
		if (gx == 9) {
			this.map.setMati(4);
		}
//	19270  IF     GY=9  THEN MATI=5
		if (gy == 9) {
			this.map.setMati(5);
		}
//	19275  DIMEN=3
		this.map.setDimen(3);
//	19280    TAU=0
		this.map.setTau(0);
//	19285    DGX=GX
//	19290    DGY=GY
//	19295    DSX=SX
//	19300    DSY=SY
		this.map.pushD();
//	19305     SY=7
//	19310     SX=2
		this.map.setPos(2, 7);
//	19315     GX=0
//	19320     GY=0
		this.map.setGx(0);
		this.map.setGy(0);
//	19325  GOSUB *FDANC.M
		fdancM();
//	19330  GOSUB *MAPLOAD.M
		maploadM();
//	19335  GOSUB *MAPHYOUZI.M
		maphyouziM();
//	19340  GOSUB *UEKAKU.M
		uekakuM();
//	19345 RETURN
	}

	/**
	 * 19355 *MURA.2.
	 * @throws IOException 入出力例外
	 */
	private void mura2() throws IOException {
		int gx = this.map.getGx();
//	19360  IF     GX=2  THEN MATI=6
		if (gx == 2) {
			this.map.setMati(6);
		}
//	19365  IF     GX=4  THEN MATI=8
		if (gx == 4) {
			this.map.setMati(8);
		}
//	19370  IF     GX=10 THEN MATI=7
		if (gx == 10) {
			this.map.setMati(8);
		}
//	19375  DIMEN=4
		this.map.setDimen(4);
//	19380    TAU=0
		this.map.setTau(0);
//	19385    DGX=GX
//	19390    DGY=GY
//	19395    DSX=SX
//	19400    DSY=SY
		this.map.pushD();
//	19405     SY=7
//	19410     SX=2
		this.map.setPos(2, 7);
//	19415     GX=0
		this.map.setGx(0);
//	19420     GY=0
		this.map.setGy(0);
//	19425  GOSUB *FDAND.M
		fdandM();
//	19430  GOSUB *MAPLOAD.M
		maploadM();
//	19435  GOSUB *MAPHYOUZI.M
		maphyouziM();
//	19440  GOSUB *UEKAKU.M
		uekakuM();
//	19445 RETURN
	}

	/**
	 * 19455 *TOU.2.
	 * @throws IOException 入出力例外
	 */
	private void tou2() throws IOException {
		int gx = this.map.getGx();
		int sx = this.map.getSx();
		int tgi1 = this.map.getTgi1();
		int tgi4 = this.map.getTgi4();
//	19460  IF     GX=0 AND          TGI1=0 THEN   FE$="1"
		if (gx == 0 && tgi1 == 0) {
			this.map.setFe$("1");
		}
//	19465  IF     GX=0 AND          TGI1=2 THEN GOTO *DAME.G
		if (gx == 0 && tgi1 == 2) {
			dameG();
			return;
		}
//	19470  IF     GX=7                     THEN   FE$="2"
		if (gx == 7) {
			this.map.setFe$("2");
		}
//	19475  IF     GX=6 AND SX=2            THEN   FE$="3"
		if (gx == 6 && sx == 2) {
			this.map.setFe$("3");
		}
//	19480  IF     GX=6 AND SX=6 AND TGI4=0 THEN   FE$="4"
		if (gx == 6 && sx == 6 && tgi4 == 0) {
			this.map.setFe$("4");
		}
//	19485  IF                       TGI4=2 THEN GOTO *DAME.G
		if (tgi4 == 2) {
			dameG();
			return;
		}
//	19490  DIMEN=5
		this.map.setDimen(5);
//	19495    DGX=GX
//	19500    DGY=GY
//	19505    DSX=SX
//	19510    DSY=SY
		this.map.pushD();
//	19515    TAU=3
		this.map.setTau(3);
//	19520     SY=7
//	19525     SX=2
		this.map.setPos(2, 7);
//	19530     GY=0
		this.map.setGy(0);
//	19535     GX=0
		this.map.setGx(0);
//	19540  GOSUB *FDANE.M
		fdaneM();
//	19545  GOSUB *MAPLOAD.M
		maploadM();
//	19550  GOSUB *MAPHYOUZI.M
		maphyouziM();
//	19555  GOSUB *UEKAKU.M
		uekakuM();
//	19560 RETURN
	}

	/**
	 * 19570 *DAME.G.
	 */
	private void dameG() {
//	19575  LOCATE 4,24
		this.scr.locate(4, 24);
//	19580  PRINT "入り口が崩れて入れない。"
		this.scr.print("入り口が崩れて入れない。");
//	19590  GOSUB *MIGIKESHI.M
		migikeshiM();
//	19595  GOSUB *MIGIKAKU.M
		migikakuM();
//	19600  GOSUB *BEEPL.M
//	19605  TAU=2
		this.map.setTau(2);
//	19610 RETURN
	}

	/**
	 * 19620 *DOUKUTU.2.
	 * @throws IOException 入出力例外
	 */
	private void doukutu2() throws IOException {
		int gx = this.map.getGx();
		int gy = this.map.getGy();
		int tgi3 = this.map.getTgi3();
//	19625    DGX=GX
//	19630    DGY=GY
//	19635    DSX=SX
//	19640    DSY=SY
		this.map.pushD();
//	19645  IF     GX=3            THEN GOSUB *DOUKUTU1.3
//	19650  IF     GX=8 AND GY=2   THEN GOSUB *DOUKUTU2.3
//	19655  IF     GX=8 AND GY=3   THEN GOSUB *DOUKUTU3.3
//	19660  IF     GX=7 AND TGI3=0 THEN GOSUB *DOUKUTU4.3
//	19665  IF     GX=7 AND TGI3=2 THEN GOTO  *DAME.G
//	19670  IF     GX=9 AND GY=10  THEN GOSUB *DOUKUTU5.3
//	19675  IF     GX=9 AND GY=9   THEN GOSUB *DOUKUTU6.3
//	19680  IF     GX=4            THEN GOSUB *DOUKUTU7.3
//	19685  IF     GX=5            THEN GOSUB *DOUKUTU8.3
		if (gx == 3) {
			this.map.setFe$("1", 6, 4);
		} else if (gx == 8 && gy == 2) {
			this.map.setFe$("2", 4, 2);
		} else if (gx == 8 && gy == 3) {
			this.map.setFe$("3", 4, 6);
		} else if (gx == 7 && tgi3 == 0) {
			this.map.setFe$("4", 6, 4);
		} else if (gx == 7 && tgi3 == 2) {
			dameG();
		} else if (gx == 9 && gy == 10) {
			this.map.setFe$("5", 4, 6);
		} else if (gx == 9 && gy == 9) {
			this.map.setFe$("6", 5, 4);
		} else if (gx == 4) {
			this.map.setFe$("7", 2, 4);
		} else if (gx == 5) {
			this.map.setFe$("8", 6, 4);
		}
//	19690  DIMEN=6
		this.map.setDimen(6);
//	19695    TAU=3
		this.map.setTau(3);
//	19700     GX=0
//	19705     GY=0
		this.map.setGx(0);
		this.map.setGy(0);
//	19710  GOSUB *FDANF.M
		fdanfM();
//	19715  GOSUB *MAPLOAD.M
		maploadM();
//	19720  GOSUB *MAPHYOUZI.M
		maphyouziM();
//	19725  GOSUB *UEKAKU.M
		uekakuM();
//	19730 RETURN
	}

//	19740 *DOUKUTU1.3
//	19745  FE$="1"
//	19750   SX=6
//	19755   SY=4
//	19760 RETURN
//	19770 *DOUKUTU2.3
//	19775  FE$="2"
//	19780   SX=4
//	19785   SY=2
//	19790 RETURN
//	19800 *DOUKUTU3.3
//	19805  FE$="3"
//	19810   SX=4
//	19815   SY=6
//	19820 RETURN
//	19830 *DOUKUTU4.3
//	19835  FE$="4"
//	19840   SX=6
//	19845   SY=4
//	19850 RETURN
//	19860 *DOUKUTU5.3
//	19865  FE$="5"
//	19870   SX=4
//	19875   SY=6
//	19880 RETURN
//	19890 *DOUKUTU6.3
//	19895  FE$="6"
//	19900   SX=5
//	19905   SY=4
//	19910 RETURN
//	19920 *DOUKUTU7.3
//	19925  FE$="7"
//	19930   SX=2
//	19935   SY=4
//	19940 RETURN
//	19950 *DOUKUTU8.3
//	19955  FE$="8"
//	19960   SX=6
//	19965   SY=4
//	19970 RETURN

	/**
	 * 19980 *DERU.2.
	 * @throws IOException 入出力例外
	 */
	private void deru2() throws IOException {
//	19985  IF DIMEN>1                   THEN GOSUB *DERU2.3
		if (1 < this.map.getDimen()) {
			deru23();
		}
		int tgi1 = this.map.getTgi1();
		int tgi4 = this.map.getTgi4();
		int gx = this.map.getGx();
		int gy = this.map.getGy();
//	19990  IF  TGI1=2 AND GX=0 AND GY=6 THEN GOSUB *DAME2.3
		if (tgi1 == 2 && gx == 0 && gy == 6) {
			dame23();
		}
//	19995  IF  TGI4=2 AND GX=6 AND GY=8 THEN GOSUB *DAME2.3
		if (tgi4 == 2 && gx == 6 && gy == 8) {
			dame23();
		}
//	20000 RETURN
	}

	/**
	 * 20010 *DERU2.3.
	 * @throws IOException 入出力例外
	 */
	private void deru23() throws IOException {
//	20015  IF  DIMEN=2 AND  KAI=2       THEN GOTO  *DERU2.G
		if (this.map.getDimen() == 2 && this.map.getKai() == 2) {
			deru2g();
			return;
		}
//	20020  DIMEN=1
		this.map.setDimen(1);
//	20025   MATI=0
		this.map.setMati(0);
//	20030    KAI=1
		this.map.setKai(1);
//	20035    TAU=2
		this.map.setTau(2);
//	20040     GY=DGY
//	20045     SX=DSX
//	20050     SY=DSY
//	20055     GX=DGX
		this.map.popD();
//	20060  GOSUB *FMAP.M
		this.map.setMapf$map();
//	20065  GOSUB *MAPLOAD.M
		maploadM();
//	20070  GOSUB *MAPHYOUZI.M
		maphyouziM();
//	20075  GOSUB *SHITAKAKU.M
		shitakakuM();
//	20080 RETURN
	}

	/**
	 * 20090 *DERU2.G.
	 * @throws IOException 入出力例外
	 */
	private void deru2g() throws IOException {
//	20095  KAI=1
		this.map.setKai(1);
//	20100   SY=6
//	20105   SX=4
		this.map.setPos(4, 6);
//	20110   GX=0
//	20115   GY=-1
		this.map.setGx(0);
		this.map.setGy(-1);
//	20120  GOSUB *FDANA.M
		fdanaM();
//	20125  GOSUB *MAPLOAD.M
		maploadM();
//	20130  GOSUB *MAPHYOUZI.M
		maphyouziM();
//	20135  GOSUB *SHITAKAKU.M
		shitakakuM();
//	20140 RETURN
	}

	/**
	 * 20150 *DAME2.3.
	 */
	private void dame23() {
//	20155  LOCATE 4,24
		this.scr.locate(4, 24);
//	20160  PRINT "ドドドドドドシャンガラガラ                                                      塔が崩れていく・・・"
		this.scr.print("ドドドドドドシャンガラガラ \n塔が崩れていく・・・");
//	20165  GOSUB *BEEPL.M
		beeplM();
//	20170     VSYC=80
//	20175  GOSUB *VSYC.M
		vsycM(80);
//	20180  GOSUB *BEEPL.M
		beeplM();
//	20185 RETURN
	}

	/**
	 * 20195 *SENTOU.2.
	 * @throws IOException 入出力例外
	 */
	private void sentou2() throws IOException {
//	20198  KEY OFF
//	20200  GOSUB *TEKI.3
		Enemy enemy = teki3();
//	20205  GOSUB *SENKOU.3
		senkou3(enemy);
//	20207  GOSUB *CFKEY.M
		abossF(enemy);
	}

	/**
	 * 20210 *ABOSS.F.
	 * @param enemy 敵情報
	 * @throws IOException 入出力例外
	 */
	private void abossF(Enemy enemy) throws IOException {
//	20215  WHILE TDA<>0
		while (this.btl.isTda()) {
			int ink = inkey$();

			if (ink == KeyEvent.VK_F1 || ink == KeyEvent.VK_A) {
				key1y(enemy); // 戦う
			} else if (ink == KeyEvent.VK_F2 || ink == KeyEvent.VK_E) {
				key2y(enemy); // 逃げる
			} else if (ink == KeyEvent.VK_F3 || ink == KeyEvent.VK_M) {
				key3y(enemy); // 魔法
			} else if (ink == KeyEvent.VK_F10 || ink == KeyEvent.VK_I) {
				key10y(enemy); // 道具
			}
			if (this.sts.isGameover()) {
				break;
			}
//	20225   IF NIGE=1 THEN GOSUB *KEY2.Y
			if (this.btl.isNige()) {
				key2y(enemy);
			}
//	20230  WEND
		}
//	20235 RETURN
	}

//	20245 *CFKEY.M
//	20250  KEY(1)ON
//	20255  KEY(2)ON
//	20260  KEY(3)ON
//	20265  KEY(4)OFF
//	20270  KEY(5)OFF
//	20275  KEY(6)OFF
//	20280  KEY(7)OFF
//	20285  KEY(8)OFF
//	20290  KEY(9)OFF
//	20295  KEY(10)ON
//	20300 RETURN

	/**
	 * 20310 *SENKOU.3.
	 * @param enemy 敵情報
	 * @throws IOException 入出力例外
	 */
	private void senkou3(Enemy enemy) throws IOException {
//	20315  C=INT(RND(1)*10)
		int c = (int) (Math.random() * 10.0d);
//	20320  IF TGI1=1 OR TGI3=1 OR TGI4=1 OR TGI5=1 THEN C=0
		int tgi1 = this.map.getTgi1();
		int tgi3 = this.map.getTgi3();
		int tgi4 = this.map.getTgi4();
		int tgi5 = this.map.getTgi5();
		if (tgi1 == 1 || tgi3 == 1 || tgi4 == 1 || tgi5 == 1) {
			c = 0;
		}
		this.btl.setC(c);
//	20325  IF            C<4                       THEN GOSUB *SEN.4
		if (c < 4) {
			sen4(enemy);
		}
//	20330  IF    C>3 AND C<7                       THEN GOSUB *DOU.4
		if (c > 3 && c < 7) {
			dou4(enemy);
		}
//	20335  IF    C>6                               THEN GOSUB *ATO.4
		if (c > 6) {
			ato4(enemy);
		}
//	20340 RETURN
	}

	/**
	 * 20350 *ATO.4.
	 * @param enemy 敵情報
	 * @throws IOException 入出力例外
	 */
	private void ato4(Enemy enemy) throws IOException {
//	20355  LOCATE 4,24
		this.scr.locate(4, 24);
//	20360  PRINT "わぁ！いきなり不意を突かれた。"
		this.scr.print("わぁ！いきなり不意を突かれた。");
//	20365  C=2
		this.btl.setC(2);
//	20370  GOSUB *TEKIATA.M
		tekiataM(enemy);
//	20375 RETURN
	}

	/**
	 * 20385 *DOU.4.
	 * @param enemy 敵情報
	 */
	private void dou4(Enemy enemy) {
//	20390  C=0
		this.btl.setC(0);
//	20395  LOCATE 4,24
		this.scr.locate(4, 24);
//	20400  PRINT "あっ！"+TNA$+"　だ！"
		this.scr.print("あっ！" + enemy.getTna$() + "　だ！");
//	20405 RETURN
	}

	/**
	 * 20415 *SEN.4.
	 * @param enemy 敵情報
	 */
	private void sen4(Enemy enemy) {
//	20420  LOCATE 4,24
		this.scr.locate(4, 24);
//	20425  PRINT "あっ！"+TNA$+"　だ！"
		this.scr.print("あっ！" + enemy.getTna$() + "　だ！");
//	20430  LOCATE 4,24
//	20435  PRINT "しかし、敵は、まだ気づいていない。"
		this.scr.print("しかし、敵は、まだ気づいていない。");
//	20440  C=1
		this.btl.setC(1);
//	20445 RETURN
	}

	/**
	 * 20455 *JIATA.M.
	 * @param enemy 敵情報
	 * @throws IOException 入出力例外
	 */
	private void jiataM(Enemy enemy) throws IOException {
//	20460  LOCATE 4,24
		this.scr.locate(4, 24);
//	20465  PRINT NA$+"の攻撃！"
		this.scr.print(this.sts.getNa$() + "の攻撃！");
//	20470  FOR J=0 TO 2
//	20475   GOSUB *BEEPS.M
//	20476   VSYC=7
//	20477   GOSUB *VSYC.M
		vsycM(7);
//	20480  NEXT J
//	20490    D=INT(((SRN*ATAP)+RND(1)*SRN/3)*(100-TSH)/100)+1
		int srn = this.sts.getSrn();
		int atap = this.sts.getAtap();
		int tsh = enemy.getTsh();
		int d = (int) ((srn * atap + Math.random() * srn / 3) * (100 - tsh) / 100.0d) + 1;
//	20495  THP=THP-D
		enemy.addThp(-d);
		jiata2M(enemy, d);
	}

	/**
	 * 20500 *JIATA2.M.
	 * @param enemy 敵情報
	 * @throws IOException 入出力例外
	 */
	private void jiata2M(Enemy enemy, int d) throws IOException {
//	20505  LOCATE 4,24
		this.scr.locate(4, 24);
//	20510  PRINT NA$+"は、"+AKCNV$(STR$(D))+"のダメージを与えた。"
		this.scr.print(this.sts.getNa$() + "は、" + d + "のダメージを与えた。");
//	20515  GOSUB *YATUKEIRO.M
		yatukeiroM();
//	20520  IF THP=<0 THEN GOTO *WIN.G
		if (enemy.getThp() <= 0) {
			winG(enemy);
			return;
		}
//	20525  IF   C=1  THEN C=0:RETURN
		if (this.btl.getC() == 1) {
			this.btl.setC(0);
			return;
		}
//	20530   C=2
		this.btl.setC(2);
//	20535  GOSUB *TEKIATA.M
//	20540 RETURN
		tekiataM(enemy);
	}

	/**
	 * 20550 *TEKIATA.M.
	 * @param enemy 敵情報
	 * @throws IOException 入出力例外
	 */
	private void tekiataM(Enemy enemy) throws IOException {
//	20555  LOCATE 4,24
		this.scr.locate(4, 24);
//	20560  PRINT TNA$+"の攻撃！"
		this.scr.print(enemy.getTna$() + "の攻撃！");
//	20565   D=INT((TAT+RND(1)*TAT/3)*((100-(SHPP+AHEP+AARP))/100))+1
		int tat = enemy.getTat();
		int shpp = 0; // おそらく既存バグ
		int ahep = this.sts.getAhep();
		int aarp = this.sts.getAarp();
		int d = ((int) ((tat + Math.random() * tat / 3) * ((100 - shpp + ahep + aarp) / 100))) + 1;
//	20570  VSYC=40
//	20575  GOSUB *VSYC.M
		vsycM(40);
		int thh = enemy.getThh();
//	20580  IF THH=2 AND RND(1)>.8 THEN GOSUB *DOKU.4
		if (thh == 2 && Math.random() > .8d) {
			d = doku4(d);
		}
//	20585  IF THH=1 AND RND(1)>.4 THEN GOSUB *HONOO.4
		if (thh == 1 && Math.random() > .4d) {
			d = honoo4(enemy, d);
		}
//	20600  VSYC=40
//	20605  GOSUB *VSYC.M
		vsycM(40);
//	20610  HP=HP-D
		this.sts.addHp(-d);
//	20615  LOCATE 4,24
		this.scr.locate(4, 24);
//	20620  PRINT NA$+"は、"+AKCNV$(STR$(D))+"のダメージを受けた。"
		this.scr.print(this.sts.getNa$() + "は、" + d + "のダメージを受けた。");
//	20625  IF HP=<0 THEN *GAMEOVER.M
		if (this.sts.getHp() <= 0) {
			gameoverM();
			return;
		}
//	20630  GOSUB *YARAREIRO.M
		yarareiroM();
//	20635  GOSUB *HYOUZI.M
		hyouziM();
//	20640  IF  C=2 THEN C=0:RETURN
		if (this.btl.getC() == 2) {
			this.btl.setC(0);
			return;
		}
//	20645  C=1
		this.btl.setC(1);
//	20650  GOSUB *JIATA.M
		jiataM(enemy);
//	20655 RETURN
	}

	/**
	 * 20665 *DOKU.4.
	 * @param d ダメージ
	 * @return ダメージ
	 */
	private int doku4(int d) {
//	20670  LOCATE 4,24
		this.scr.locate(4, 24);
//	20675  PRINT NA$+"は、毒をくらつた。"
		this.scr.print(this.sts.getNa$() + "は、毒をくらつた。");
//	20680   KEN$="  POISON"
		this.sts.setKen$("  POISON");
//	20685  GOSUB *BEEPL.M
		beeplM();
//	20690  D=INT(D*1.2)
//	20695 RETURN
		return (int) (d * 1.2d);
	}
	private int honoo4(Enemy enemy, int d) {
//	20705 *HONOO.4
//	20710  LOCATE 4,24
		this.scr.locate(4, 24);
//	20715  PRINT TNA$+"は、炎を吐いた。"
		this.scr.print(enemy.getTna$() + "は、炎を吐いた。");
//	20720  GOSUB *BEEPL.M
		beeplM();
//	20725  GOSUB *BEEPL.M
		beeplM();
//	20730  D=INT(D*1.6)
//	20735 RETURN
		return (int) (d * 1.6d);
	}

	private void yarareiroM() {
//	20745 *YARAREIRO.M
//	20750  FOR I=0 TO 3
//	20755   COLOR=(8,&HF0)
//	20758   GOSUB *BEEPL.M
//	20760   VSYC=12
//	20765   GOSUB *VSYC.M
//	20770   COLOR=(8,&HDEE)
//	20785  NEXT I
//	20790 RETURN
	}

	private void yatukeiroM() {
//	20800 *YATUKEIRO.M
//	20805  FOR I=0 TO 3
//	20810   COLOR=(6,&HFFF)
//	20815   COLOR=(1,&H444)
//	20820   VSYC=12
//	20825   GOSUB *VSYC.M
//	20830   COLOR=(6,OPL)
//	20835   COLOR=(1,&HF)
//	20840   VSYC=12
//	20845   GOSUB *VSYC.M
//	20850  NEXT I
//	20855 RETURN
	}

	/**
	 * 20865 *TEKI.3.
	 * @throws IOException 入出力例外
	 */
	private Enemy teki3() throws IOException {
//	20870    OX=15
//	20875    OY=15
//	20880    EX=303
//	20885    EY=303
//	20890    WC=0
//	20895  GOSUB *WAKU.M
		wakuM(12, 12, 303, 303, 0);
//	20900    TDA=1
		this.btl.setTda(true);
//	20905    THH=0
		int thh = 0;
//	20910     A$="\ML"
		String a$ = "\\ML";
		int gx = this.map.getGx();
		int gy = this.map.getGy();
		int tau = this.map.getTau();
		String mapf$ = this.map.getMapf$();
		Enemy enemy = this.btl.getLastEnemy();
//	20915  IF    GX<3 AND GY<4                      THEN GOSUB *TEKI0.4
//	20920  IF    GY>3 AND GX<3  AND GY<7            THEN GOSUB *TEKI1.4
//	20925  IF    GX>2 AND GX<6  AND GY<4            THEN GOSUB *TEKI2.4
//	20930  IF    GY>3 AND GX>2  AND GX<6  AND GY<6  THEN GOSUB *TEKI3.4
//	20935  IF    GX>5 AND GX<12 AND GY<3            THEN GOSUB *TEKI4.4
//	20940  IF    GY>2 AND GX>5  AND GX<12 AND GY<6  THEN GOSUB *TEKI5.4
//	20945  IF    GY>7 AND GX>5  AND GX<12 AND GY<8  THEN GOSUB *TEKI6.4
//	20950  IF    GY>7 AND GX>7  AND GX<12 AND GY<12 THEN GOSUB *TEKI7.4
//	20955  IF    GY>5 AND GX>4  AND GX<7  AND GY<8  THEN GOSUB *TEKI8.4
//	20960  IF    GY>7 AND GX>4  AND GX<8  AND GY<12 THEN GOSUB *TEKI9.4
//	20965  IF    GY=6 AND GX>2  AND GX<5            THEN GOSUB *TEKI10.4
//	20970  IF    GY>6 AND GY<12 AND GX<5            THEN GOSUB *TEKI10.4
//	20975  IF  TAU<>2 AND LEFT$(MAPF$,4)="DAN\E"    THEN GOSUB *TEKID.4
//	20980  IF  TAU<>2 AND LEFT$(MAPF$,4)="DAN\F"    THEN GOSUB *TEKIF.4
//	20985  IF  TAU =5                               THEN GOSUB *TEKIB.4
		if (gx < 3 && gy < 4) {
			enemy = new Enemy("ハクション大魔道師", 0, 7, 4, 2, 10, thh, 4); // *TEKI0.4
		}
		if (gy > 3 && gx < 3 && gy < 7) {
			enemy = new Enemy("カットゴーフ", 1, 15, 10, 5, 15, thh, 10); // *TEKI1.4
		}
		if (gx > 2 && gx < 6 && gy < 4) {
			enemy = new Enemy("スライム", 2, 30, 18, 15, 20, 2, 20);// *TEKI2.4
		}
		if (gy > 3 && gx > 2 && gx < 6 && gy < 6) {
			enemy = new Enemy("ワルクロス", 3, 70, 30, 22, 25, thh, 30);// *TEKI3.4
		}
		if (gx > 5 && gx < 12 && gy < 3) {
			enemy = new Enemy("ワーム・モール", 4, 90, 56, 25, 30, thh, 35);// *TEKI4.4
		}
		if (gy > 2 && gx > 5 && gx < 12 && gy < 6) {
			enemy = new Enemy("ヒメサンダー", 5, 120, 73, 25, 35, 2, 40);// *TEKI5.4
		}
		if (gy > 7 && gx > 5 && gx < 12 && gy < 8) {
			enemy = new Enemy("ファイター", 6, 180, 112, 30, 40, thh, 50);// *TEKI6.4
		}
		if (gy > 7 && gx > 7 && gx < 12 && gy < 12) {
			enemy = new Enemy("エロガッパ", 7, 220, 160, 35, 45, 1, 60);// *TEKI7.4
		}
		if (gy > 5 && gx > 4 && gx < 7 && gy < 8) {
			enemy = new Enemy("ラリーパッパ", 8, 250, 215, 40, 50, 1, 70);// *TEKI8.4
		}
		if (gy > 7 && gx > 4 && gx < 8 && gy < 12) {
			enemy = new Enemy("ラリーパッパ", 9, 300, 215, 40, 50, 1, 70);// *TEKI9.4
		}
		if (gy == 6 && gx > 2 && gx < 5) {
			enemy = new Enemy("ジャイアント", 10, 420, 280, 45, 55, thh, 85);// *TEKI10.4
		}
		if (gy > 6 && gy < 12 && gx < 5) {
			enemy = new Enemy("ジャイアント", 10, 420, 280, 45, 55, thh, 85);// *TEKI10.4
		}
		if (tau != 2 && mapf$.startsWith("DAN\\E")) {
			enemy = tekid4();
		} else if (tau != 2 && mapf$.startsWith("DAN\\F")) {
			enemy = tekif4();
		}
		if (tau == 5) {
			a$ = "\\MB";
			enemy = tekib4();
		}
//	20990  ON  INT(RND(1)*10)+1 GOSUB *TEKIG.4,*TEKIG.4,*TEKIG.4,*TEKIG.4,*TEKIR.4,*TEKIR.4,*TEKIR.4,*TEKIY.4,*TEKIY.4,*TEKIM.4
		int rnd = ((int) (Math.random() * 10)) + 1;
		if (1 <= rnd && rnd <= 4) {
			tekig4(enemy);
		} else if (5 <= rnd && rnd <= 7) {
			tekir4(enemy);
		} else if (8 <= rnd && rnd <= 9) {
			tekiy4(enemy);
		} else if (rnd == 10) {
			tekim4(enemy);
		}
//	20995  IF  INT((RND(1)*10+RND(1)*10)/2)=5       THEN GOSUB *TEKIX.4
		rnd = ((int) (Math.random() * 10 + Math.random() * 10) / 2);
		if (rnd == 5) {
			tekix4(enemy);
		}
//	21000  IF  INT( RND(1)*10+RND(1)*10)   <4       THEN GOSUB *TEKIB.4
		rnd = (int) (Math.random() * 10 + Math.random() * 10);
		if (rnd < 4) {
			a$ = "\\MB";
			enemy = tekib4();
		}
		bossF(a$, enemy);
		this.btl.setLastEnemy(enemy);
		return enemy;
	}

	/**
	 * 21005 *BOSS.F.
	 * @param a$
	 * @throws IOException 入出力例外
	 */
	private void bossF(String a$, Enemy enemy) throws IOException {
//	21010  DEF SEG=VARPTR(TEKI(0),1)
		int a = enemy.getA();
//	21015  BLOAD "TEKI"+A$+"."+MID$(STR$(A),2,1),VARPTR(TEKI(0),0)
		if (9 < a) {
			a = 1;
		}
		this.map.bload("TEKI" + a$ + "." + a);
//	21020  IF     A$="\MB" THEN B=90            ELSE B=123
		int b;
		if ("\\MB".equals(a$)) {
			b = 90;
		} else {
			b = 123;
		}
//	21025  PUT@ (B,B),TEKI,PSET
		byte[] teki = this.map.getTeki();
		this.scr.put(b, b, teki);
//	21030 RETURN
	}

	/**
	 * 21040 *TEKIG.4.
	 * @param enemy 敵情報
	 */
	private void tekig4(Enemy enemy) {
//	21045  COLOR=( 6,&HF80)
		N88.color(6, 0xf80);
//	21050  COLOR=(14,&HF08)
		N88.color(14, 0xf08);
//	21055  TNA$="グリーン　"+TNA$
		enemy.setTna$("グリーン　" + enemy.getTna$());
//	21060  OPL =VAL("&HF80")
//	21065  THH =0
		enemy.setThh(0);
//	21070 RETURN
	}

	/**
	 * 21080 *TEKIR.4.
	 * @param enemy 敵情報
	 */
	private void tekir4(Enemy enemy) {
//	21085  COLOR=( 6,&H8F0)
		N88.color(6, 0x8f0);
//	21090  COLOR=(14,&HF8)
		N88.color(14, 0xf8);
//	21095  TNA$="レッド　　"+TNA$
		enemy.setTna$("レッド　　" + enemy.getTna$());
//	21100  OPL =VAL("&H8F0")
//	21105  TAT =INT(TAT*1.2)
		double tat = enemy.getTat() * 1.2;
		enemy.setTat((int) tat);
//	21110  TEX =INT(TEX*1.2)
		double tex = enemy.getTex() * 1.2;
		enemy.setTex((int) tex);
//	21115  IF   THH=1 THEN THH=1
//	21120 RETURN
	}

	/**
	 * 21130 *TEKIY.4.
	 * @param enemy 敵情報
	 */
	private void tekiy4(Enemy enemy) {
//	21135  COLOR=( 6,&HFF0)
		N88.color(6, 0xff0);
//	21140  COLOR=(14,&HAA0)
		N88.color(14, 0xaa0);
//	21145  TNA$="イエロー　"+TNA$
		enemy.setTna$("イエロー　" + enemy.getTna$());
//	21150   OPL=VAL("&HFF0")
//	21155   THP=INT(THP*1.2)
		double thp = enemy.getThp() * 1.2;
		enemy.setThp((int) thp);
//	21160   TGO=INT(TGO*1.2)
		double tgo = enemy.getTgo() * 1.2;
		enemy.setTgo((int) tgo);
//	21165  IF   THH=2 THEN THH=2
//	21170 RETURN
	}

	/**
	 * 21180 *TEKIM.4.
	 * @param enemy 敵情報
	 */
	private void tekim4(Enemy enemy) {
//	21185  COLOR=( 6,&H666)
		N88.color(6, 0x666);
//	21190  COLOR=(14,&HCCC)
		N88.color(14, 0xccc);
//	21195  TNA$="メタル　　"+TNA$
		enemy.setTna$("メタル　　" + enemy.getTna$());
//	21200  OPL =VAL("&H666")
//	21205  TSH =TSH+20
		enemy.addTsh(20);
//	21210  TEX =INT(TEX*1.2)
		double tex = enemy.getTex() * 1.2;
		enemy.setTex((int) tex);
//	21215  THH =0
		enemy.setThh(0);
//	21220 RETURN
	}

	/**
	 * 21230 *TEKIX.4.
	 * @param enemy 敵情報
	 */
	private void tekix4(Enemy enemy) {
//	21235  IF  A<10 THEN A=A+1
		if (enemy.getA() < 10) {
			enemy.addA(1);
		}
//	21240  TNA$="正体不明の怪物　"
		enemy.setTna$("正体不明の怪物　");
//	21245  THP =INT(THP*1.3)
		double thp = enemy.getThp() * 1.3;
		enemy.setThp((int) thp);
//	21250  TEX =INT(TEX*1.3)
		double tex = enemy.getTex() * 1.3;
		enemy.setTex((int) tex);
//	21255  TAT =INT(TAT*1.3)
		double tat = enemy.getTat() * 1.3;
		enemy.setTat((int) tat);
//	21260  TSH =INT(TSH*1.3)
		double tsh = enemy.getTsh() * 1.3;
		enemy.setTsh((int) tsh);
//	21265  TGO =INT(TGO*1.3)
		double tgo = enemy.getTgo() * 1.3;
		enemy.setTgo((int) tgo);
//	21270 RETURN
	}

	/**
	 * 21280 *TEKID.4.
	 * @return 敵情報
	 */
	private Enemy tekid4() {
		Enemy enemy;
//	21285  ON VAL(FE$) GOSUB *TEKID1.5,*TEKID2.5,*TEKID3.5,*TEKID3.5
		String fe$ = this.map.getFe$();
		if ("1".equals(fe$)) {
			enemy = new Enemy("カツトゴーフ　　　", 1, 40, 12, 5, 17, 2, 25); // *TEKID1.5
		} else if ("2".equals(fe$)) {
			enemy = new Enemy("ヒメサンダー　　　", 5, 150, 85, 30, 35, 2, 45); // *TEKID2.5
		} else {
			enemy = new Enemy("ジャイアント　　　", 9, 300, 302, 45, 50, 0, 90); // *TEKID3.5
		}
//	21290 RETURN
		return enemy;
//	21300 *TEKID1.5
//	21305  TNA$="カツトゴーフ　　　"
//	21310  THP =40
//	21315  TEX =12
//	21320  TAT =5
//	21325  TSH =17
//	21330  THH =2
//	21335  TGO =25
//	21340    A =1
//	21345 RETURN
//	21355 *TEKID2.5
//	21360  TNA$="ヒメサンダー　　　"
//	21365  THP =150
//	21370  TEX =85
//	21375  TAT =30
//	21380  TSH =35
//	21385  THH =2
//	21390  TGO =45
//	21395    A =5
//	21400 RETURN
//	21410 *TEKID3.5
//	21415  TNA$="ジャイアント　　　"
//	21420  THP =300
//	21425  TEX =302
//	21430  TAT =45
//	21435  TSH =50
//	21440  TGO =90
//	21445    A =9
//	21450 RETURN
	}

	/**
	 * 21460 *TEKIF.4
	 * @return
	 */
	private Enemy tekif4() {
		Enemy enemy;
//	21465  ON VAL(FE$) GOSUB *TEKIF1.5,*TEKIF2.5,*TEKIF2.5,*TEKIF3.5,*TEKIF4.5,*TEKIF4.5,*TEKIF5.5,*TEKIF5.5
		String fe$ = this.map.getFe$();
		if ("1".equals(fe$)) {
			enemy = new Enemy("ワルクロス　　　　", 3, 55, 35, 15, 25, 0, 12); // *TEKIF1.5
		} else if ("2".equals(fe$) || "3".equals(fe$)) {
			enemy = new Enemy("ワーム・モール　　", 4, 80, 60, 20, 30, 0, 41); // *TEKIF2.5
		} else if ("4".equals(fe$)) {
			enemy = new Enemy("ファイター　　　　", 6, 150, 120, 30, 40, 0, 61); // *TEKIF3.5
		} else if ("5".equals(fe$) || "6".equals(fe$)) {
			enemy = new Enemy("ハクション大魔道師", 0, 8, 6, 3, 10, 0, 4); // *TEKIF4.5
		} else {
			enemy = new Enemy("ラリーパッパ　　　", 8, 200, 250, 40, 50, 1, 81); // *TEKIF5.5
		}
//	21470 RETURN
			return enemy;
//	21480 *TEKIF1.5
//	21485  TNA$="ワルクロス　　　　"
//	21490  THP =55
//	21495  TEX =35
//	21500  TAT =15
//	21505  TSH =25
//	21510  TGO =12
//	21515    A =3
//	21520 RETURN
//	21530 *TEKIF2.5
//	21535  TNA$="ワーム・モール　　"
//	21540  THP =80
//	21545  TEX =60
//	21550  TAT =20
//	21555  TSH =30
//	21560  TGO =41
//	21565    A =4
//	21570 RETURN
//	21580 *TEKIF3.5
//	21585  TNA$="ファイター　　　　"
//	21590  THP =150
//	21595  TEX =120
//	21600  TAT =30
//	21605  TSH =40
//	21610  TGO =61
//	21615    A =6
//	21620 RETURN
//	21630 *TEKIF4.5
//	21635  TNA$="ハクション大魔道師"
//	21640  THP =8
//	21645  TEX =6
//	21650  TAT =3
//	21655  TSH =10
//	21660  TGO =4
//	21665    A =0
//	21670 RETURN
//	21680 *TEKIF5.5
//	21685  TNA$="ラリーパッパ　　　"
//	21690  THP =200
//	21695  TEX =250
//	21700  TAT =40
//	21705  TSH =50
//	21710  THH =1
//	21715  TGO =81
//	21720    A =8
//	21725 RETURN
	}

	/**
	 * 21735 *TEKIB.4.
	 * @return
	 */
	private Enemy tekib4() {
		Enemy enemy;
//	21740  A$="\MB"
		int mhp = this.sts.getMhp();
//	21745  IF             MHP<100 THEN GOSUB *TEKIB1.5
//	21750  IF MHP>101 AND MHP<200 THEN GOSUB *TEKIB2.5
//	21755  IF MHP>201 AND MHP<400 THEN GOSUB *TEKIB3.5
//	21760  IF MHP>401             THEN GOSUB *TEKIB4.5
//	21765 RETURN
		if (mhp < 100) {
			enemy = new Enemy("グルグル　", 3, 20, 48, 10, 15, 0, 50); // *TEKIB1.5
		} else if (mhp > 101 && mhp < 200) {
			enemy = new Enemy("ホーバリ", 1, 200, 130, 30, 30, 0, 71); // *TEKIB2.5
		} else if (mhp > 201 && mhp < 200) {
			enemy = new Enemy("大妙王", 0, 1400, 150, 25, 40, 0, 121); // *TEKIB3.5
		} else {
			enemy = new Enemy("ゴースト", 2, 2500, 180, 35, 55, 0, 153); // *TEKIB4.5
		}
		return enemy;
//	21775 *TEKIB1.5
//	21780  TNA$="グルグル　"
//	21785  THP =20
//	21790  TEX =48
//	21795  TAT =10
//	21800  TSH =15
//	21805  TGO =50
//	21810    A =3
//	21815 RETURN
//	21825 *TEKIB2.5
//	21830  TNA$="ホーバリ　"
//	21835  THP=200
//	21840  TEX=130
//	21845  TAT=30
//	21850  TSH=30
//	21855  TGO=71
//	21860   A =1
//	21865 RETURN
//	21875 *TEKIB3.5
//	21880  TNA$="大妙王　　"
//	21885  THP=1400
//	21890  TEX=150
//	21895  TAT=25
//	21900  TSH=40
//	21905  TGO=121
//	21910   A =0
//	21915 RETURN
//	21925 *TEKIB4.5
//	21930  TNA$="ゴースト　"
//	21935  THP =2500
//	21940  TEX =180
//	21945  TAT =35
//	21950  TSH =55
//	21955  TGO =153
//	21960    A =2
//	21965 RETURN
	}

//	21975 *TEKI0.4
//	21980  TNA$="ハクション大魔道師"
//	21985    A =0
//	21990  THP =7
//	21995  TEX =4
//	22000  TAT =2
//	22005  TSH =10
//	22010  TGO =4
//	22015 RETURN
//	22025 *TEKI1.4
//	22030  TNA$="カットゴーフ　　"
//	22035    A =1
//	22040  THP =15
//	22045  TEX =10
//	22050  TAT =5
//	22055  TSH =15
//	22060  TGO =10
//	22065 RETURN
//	22075 *TEKI2.4
//	22080  TNA$="スライム　　　　"
//	22085    A =2
//	22090  THP =30
//	22095  TEX =18
//	22100  TAT =15
//	22105  TSH =20
//	22110  THH =2
//	22115  TGO =20
//	22120 RETURN
//	22130 *TEKI3.4
//	22135  TNA$="ワルクロス　　　"
//	22140    A =3
//	22145  THP =70
//	22150  TEX =30
//	22155  TAT =22
//	22160  TSH =25
//	22165  TGO =30
//	22170 RETURN
//	22180 *TEKI4.4
//	22185  TNA$="ワーム・モール　"
//	22190    A =4
//	22195  THP =90
//	22200  TEX =56
//	22205  TAT =25
//	22210  TSH =30
//	22215  TGO =35
//	22220 RETURN
//	22230 *TEKI5.4
//	22235  TNA$="ヒメサンダー　　"
//	22240    A =5
//	22245  THP =120
//	22250  TEX =73
//	22255  TAT =25
//	22260  TSH =35
//	22265  THH =2
//	22270  TGO =40
//	22275 RETURN
//	22285 *TEKI6.4
//	22290  TNA$="ファイター　　　"
//	22295    A =6
//	22300  THP =180
//	22305  TEX =112
//	22310  TAT =30
//	22315  TSH =40
//	22320  TGO =50
//	22325 RETURN
//	22335 *TEKI7.4
//	22340  TNA$="エロガッパ　　　"
//	22345    A =7
//	22350  THP =220
//	22355  TEX =160
//	22360  TAT =35
//	22365  TSH =45
//	22370  THH =1
//	22375  TGO =60
//	22380 RETURN
//	22390 *TEKI8.4
//	22395  TNA$="ラリーパッパ"
//	22400    A =8
//	22405  THP =250
//	22410  TEX =215
//	22415  TAT =40
//	22420  TSH =50
//	22425  THH =1
//	22430  TGO =70
//	22435 RETURN
//	22445 *TEKI9.4
//	22450  TNA$="ラリーパッパ"
//	22455    A =9
//	22460  THP =300
//	22465  TEX =215
//	22470  TAT =40
//	22475  TSH =50
//	22480  THH =1
//	22485  TGO =70
//	22490 RETURN
//	22500 *TEKI10.4
//	22505  TNA$="ジャイアント"
//	22510    A =10
//	22515  THP =420
//	22520  TEX =280
//	22525  TAT =45
//	22530  TSH =55
//	22535  TGO =85
//	22540 RETURN

	/**
	 * 22550 *WIN.G.
	 * @param enemy 敵情報
	 * @throws IOException 入出力例外
	 */
	private void winG(Enemy enemy) throws IOException {
//	22555  EXPP!=EXPP!+TEX
		this.sts.addExpp(enemy.getTex());
//	22560  GOLD!=GOLD!+TGO
		this.sts.addGold(enemy.getTgo());
//	22565  LOCATE 4,24
		this.scr.locate(4, 24);
//	22570  PRINT NA$+"は、"+TNA$+"を倒した。"
		String na$ = this.sts.getNa$();
		this.scr.print(na$ + "は、" + enemy.getTna$() + "を倒した。");
//	22575  GOSUB *BEEPS.M
		beepsM();
//	22580  VSYC=80
//	22585  GOSUB *VSYC.M
//	22590  LOCATE 4,24
		this.scr.locate(4, 24);
//	22595  PRINT NA$+"は、"+AKCNV$(STR$(TGO))+"ＧＯＬＤを手に入れた。"
		this.scr.print(na$ + "は、" + enemy.getTgo() + "ＧＯＬＤを手に入れた。");
//	22600  LOCATE 4,24
		this.scr.locate(4, 24);
//	22605  PRINT NA$+"は、経験値"+AKCNV$(STR$(TEX))+"Ｐｏｉｎｔを手に入れた。"
		this.scr.print(na$ + "は、経験値" + enemy.getTex() + "Ｐｏｉｎｔを手に入れた。");
//	22610  IF TGI1=1                 THEN TGI1=2:TAU=0
		if (this.map.getTgi1() == 1) {
			this.map.setTgi1(2);
			this.map.setTau(0);
		}
//	22615  IF TGI3=1                 THEN TGI3=2:TAU=0
		if (this.map.getTgi3() == 1) {
			this.map.setTgi3(2);
			this.map.setTau(0);
		}
//	22620  IF TGI4=1                 THEN TGI4=2:TAU=0
		if (this.map.getTgi4() == 1) {
			this.map.setTgi4(2);
			this.map.setTau(0);
		}
//	22625  IF TGI5=1                 THEN TGI5=2:TAU=0:GOTO *ENDING.G
		if (this.map.getTgi5() == 1) {
			this.map.setTgi5(2);
			this.map.setTau(0);
			endingG();
			return;
		}
//	22630  IF EXPP!=>MEXP! AND LEV<20  THEN GOSUB *LEVUP.4
		if (this.sts.getMexp() <= this.sts.getExpp() && this.sts.getLev() < 20) {
			levup4();
		}
//	22635  GOSUB *ATAEND.4
		ataend4();
//	22640 RETURN
	}

	/**
	 * 22650 *ATAEND.4.
	 */
	private void ataend4() {
//	22655   TDA=0
		this.btl.setTda(false);
//	22660  NIGE=0
		this.btl.setNige(false);
//	22665  FOR I=90 TO 250 STEP 2
//	22670   LINE (90,I)-(218,I+2),0,BF
//	22675  NEXT
//	22680  COLOR=(14,&HAA0)
//	22685  COLOR=(6,&HFF0)
//	22690  GOSUB *KEYON.M
//	22695  GOSUB *MAPHYOUZI.M
		maphyouziM();
//	22700  GOSUB *HYOUZI.M
		hyouziM();
//	22705  GOSUB *SHITAKAKU.M
		shitakakuM();
//	22710  GOSUB *ANY.M
		anyM();
//	22715  GOSUB *PRINT.M
		printM();
//	22720 RETURN
	}

	/**
	 * 22730 *LEVUP.4.
	 */
	private void levup4() {
//	22735  GOSUB *SUTEITASU.M
		suteitasuM();
//	22740  LOCATE 4,24
		this.scr.locate(4, 24);
//	22745  PRINT NA$+"は、レベルが上がった。"
		String na$ = this.sts.getNa$();
		this.scr.print(na$ + "は、レベルが上がった。");
//	22750  GOSUB *BEEPL.M
		beeplM();
//	22755  VSYC=80
//	22760  GOSUB *VSYC.M
		vsycM(80);
//	22765  ON LEV GOSUB *MEXP1.5,*MEXP2.5,*MEXP3.5,*MEXP4.5,*MEXP5.5,*MEXP6.5,*MEXP7.5,*MEXP8.5,*MEXP9.5,*MEXP10.5,*MEXP11.5,*MEXP12.5,*MEXP13.5,*MEXP14.5,*MEXP15.5,*MEXP16.5,*MEXP17.5,*MEXP18.5,*MEXP19.5
		int lev = this.sts.getLev();
		if (lev == 1) {
			this.sts.setMexp(60);
		} else if (lev == 2) {
			this.sts.setMexp(150);
		} else if (lev == 3) {
			this.sts.setMexp(300);
		} else if (lev == 4) {
			this.sts.setMexp(500);
		} else if (lev == 5) {
			this.sts.setMexp(800);
		} else if (lev == 6) {
			this.sts.setMexp(1200);
		} else if (lev == 7) {
			this.sts.setMexp(1800);
		} else if (lev == 8) {
			this.sts.setMexp(2600);
		} else if (lev == 9) {
			this.sts.setMexp(3600);
		} else if (lev == 10) {
			this.sts.setMexp(4100);
		} else if (lev == 11) {
			this.sts.setMexp(6000);
		} else if (lev == 12) {
			this.sts.setMexp(8500);
		} else if (lev == 13) {
			this.sts.setMexp(11000);
		} else if (lev == 14) {
			this.sts.setMexp(15000);
		} else if (lev == 15) {
			this.sts.setMexp(20000);
		} else if (lev == 16) {
			this.sts.setMexp(26000);
		} else if (lev == 17) {
			this.sts.setMexp(40000);
		} else if (lev == 18) {
			this.sts.setMexp(65535);
		} else if (lev == 19) {
			this.sts.setMexp(0);
		}
//	22770  LEV=LEV+1
		lev++;
		this.sts.setLev(lev);
//	22775  MHP=(LEV+VIT)*2
		this.sts.setMhp((lev + this.sts.getVit()) * 2);
//	22785    A=QMP
		int a = this.sts.getQmp();
		int iq = this.sts.getIq();
		if (iq < 26) {
//	22790  IF IQ<26 THEN QMP=0:MGK=0
			this.sts.setQmp(0);
			this.sts.setMgk(0);
		}
		if (iq > 25) {
//	22795  IF IQ>25 THEN QMP=1:MGK=1
			this.sts.setQmp(1);
			this.sts.setMgk(1);
		}
		if (iq > 45) {
//	22800  IF IQ>45 THEN QMP=2:MGK=1
			this.sts.setQmp(2);
			this.sts.setMgk(1);
		}
		if (iq > 76) {
//	22805  IF IQ>76 THEN QMP=3:MGK=1
			this.sts.setQmp(3);
			this.sts.setMgk(1);
		}
		if (iq > 95) {
//	22810  IF IQ>95 THEN QMP=4:MGK=1
			this.sts.setQmp(4);
			this.sts.setMgk(1);
		}
//	22813  MMP=(LEV+IQ)*MGK
		this.sts.setMmp((lev + iq) * this.sts.getMgk());
//	22815  IF A=QMP THEN RETURN
		if (a == this.sts.getQmp()) {
			return;
		}
//	22820  LOCATE 4,24
		this.scr.locate(4, 24);
//	22825  PRINT NA$+"は、新しい魔法を覚えた。"
		this.scr.print(na$ + "は、新しい魔法を覚えた。");
//	22830 RETURN
	}

//	22840 *MEXP1.5
//	22845  MEXP!=   60
//	22850 RETURN
//	22860 *MEXP2.5
//	22865  MEXP!=  150
//	22870 RETURN
//	22880 *MEXP3.5
//	22885  MEXP!=  300
//	22890 RETURN
//	22900 *MEXP4.5
//	22905  MEXP!=  500
//	22910 RETURN
//	22920 *MEXP5.5
//	22925  MEXP!=  800
//	22930 RETURN
//	22940 *MEXP6.5
//	22945  MEXP!= 1200
//	22950 RETURN
//	22960 *MEXP7.5
//	22965  MEXP!= 1800
//	22970 RETURN
//	22980 *MEXP8.5
//	22985  MEXP!= 2600
//	22990 RETURN
//	23000 *MEXP9.5
//	23005  MEXP!= 3600
//	23010 RETURN
//	23020 *MEXP10.5
//	23025  MEXP!= 4100
//	23030 RETURN
//	23040 *MEXP11.5
//	23045  MEXP!= 6000
//	23050 RETURN
//	23060 *MEXP12.5
//	23065  MEXP!= 8500
//	23070 RETURN
//	23080 *MEXP13.5
//	23085  MEXP!=11000
//	23090 RETURN
//	23100 *MEXP14.5
//	23105  MEXP!=15000
//	23110 RETURN
//	23120 *MEXP15.5
//	23125  MEXP!=20000
//	23130 RETURN
//	23140 *MEXP16.5
//	23145  MEXP!=26000
//	23150 RETURN
//	23160 *MEXP17.5
//	23165  MEXP!=40000
//	23170 RETURN
//	23180 *MEXP18.5
//	23185  MEXP!=65535
//	23190 RETURN
//	23200 *MEXP19.5
//	23205  MEXP!=    0
//	23210 RETURN

	/**
	 * 23220 *ENDING.G.
	 * @throws IOException 入出力例外
	 */
	private void endingG() throws IOException {
//	23225  CLEAR
//	23230  GOSUB *FADEOUT.M
		fadeoutM();
//	23235  CONSOLE 0,25
//	23240  CLS 3
//	23245  FOR I=0 TO 2
		for (int i = 0; i <= 2; i++) {
//	23250   DEF SEG=&HA800+&H800*I
//	23255   BLOAD "SYS\EG1"+CHR$(&H30+I)
			this.map.bload("SYS\\EG1" + i);
//	23260  NEXT I
		}
		this.scr.put(8, 0, this.map.getEg());
		repaint();
		inkey$();
//	23265  GOSUB *FADEIN.M
		fadeinM();
//	23270  VSYC=500
//	23275  GOSUB *VSYC.M
		vsycM(500);
//	23280  GOSUB *FADEOUT.M
		fadeoutM();
//	23285  FOR I=0 TO 2
		for (int i = 0; i <= 2; i++) {
//	23290   DEF SEG=&HA800+&H800*I
//	23295   BLOAD "SYS\EG2"+CHR$(&H30+I)
			this.map.bload("SYS\\EG2" + i);
//	23300  NEXT I
		}
		this.scr.put(8, 0, this.map.getEg());
		repaint();
		inkey$();
//	23305  GOSUB *FADEIN.M
		fadeinM();
//	23310  VSYC=10000
//	23315  GOSUB *VSYC.M
		vsycM(10000);
//	23320 END
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// 処理なし
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		if (keyCode == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		if (keyCode == KeyEvent.VK_ALT
				|| keyCode == KeyEvent.VK_CONTROL) {
			return;
		}
		this.lastKey = keyCode;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// 処理なし
	}

	@Override
	public void paint(Graphics g) {
		BufferedImage img = this.scr.getImage();

		g.drawImage(img, 0, 32, this);
	}

	public static void main(String[] args) throws Exception {
		Rpg30 rpg30 = new Rpg30();

		rpg30.execute();
	}
}
