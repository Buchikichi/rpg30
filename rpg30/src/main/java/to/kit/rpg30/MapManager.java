package to.kit.rpg30;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * マップ情報など.
 * @author ponta
 */
public final class MapManager {
	private int gx;
	private int gy;
	private int sx;
	private int sy;
	private int dgx;
	private int dgy;
	private int dsx;
	private int dsy;
	private int kai;
	private int tau;
	private int tgi1;
	private int tgi2;
	private int tgi3;
	private int tgi4;
	private int tgi5;
	private int mati;
	private int dimen;
	private String fe$ = StringUtils.EMPTY;
	private String mapf$ = StringUtils.EMPTY;

	/** DIM MAC(315,67). */
	private byte[] mac;
	/** DIM MAP.!(8,8). */
	private int[] map;
	/** DIM G2(314). */
	private byte[] g2;
	/** DIM G4(314). */
	private byte[] g4;
	/** DIM G6(314). */
	private byte[] g6;
	/** DIM G8(314). */
	private byte[] g8;
	/** DIM TEKI(21243). */
	private byte[] teki;
	/** EG. */
	private final byte[][] eg = new byte[3][];

	public void bload(String filename) throws IOException {
		String name = filename.replace('\\', '/');
		URL url = getClass().getResource(name);

//System.out.println("BLOAD[" + url + "]");
		try (InputStream in = url.openStream()) {
			if (filename.endsWith("ALL")) {
				this.mac = IOUtils.toByteArray(in);
			} else if (filename.endsWith("G2")) {
				this.g2 = IOUtils.toByteArray(in);
			} else if (filename.endsWith("G4")) {
				this.g4 = IOUtils.toByteArray(in);
			} else if (filename.endsWith("G6")) {
				this.g6 = IOUtils.toByteArray(in);
			} else if (filename.endsWith("G8")) {
				this.g8 = IOUtils.toByteArray(in);
			} else if (filename.indexOf("EG") != -1) {
				int len = filename.length();
				int ix = Integer.parseInt(filename.substring(len - 1));
				this.eg[ix] = IOUtils.toByteArray(in);
			} else if (filename.startsWith("TEKI") || filename.startsWith("SYS")) {
				this.teki = IOUtils.toByteArray(in);
			} else {
				int ix = 0;
				this.map = new int[9 * 9];
				for (;;) {
					int remain = in.available();
					if (remain < 4) {
						break;
					}
					byte[] buff = new byte[4];
					in.read(buff);
					float f = N88.toFloat(buff);
					if (f < 0.0f) {
						f = 0.0f;
					}
					this.map[ix] = (int) f;
					ix++;
				}
			}
		}
	}

	/**
	 * MAPF$ の読み込み.
	 * @throws IOException
	 */
	public void bload() throws IOException {
		bload(this.mapf$);
	}

	public void setMapf$dan(String val) {
//		17320  MAPF$="DAN\A"+MID$(STR$(GX),2,2)+MID$(STR$(GY),2,2)+MID$(STR$(KAI),2,2)
		this.mapf$ = val + Math.abs(this.gx) + Math.abs(this.gy) + this.kai;
	}

	public void setMapf$danE() {
//		17380  MAPF$="DAN\E"+FE$
//		17385  MAPF$=MAPF$+MID$(STR$(GX),2,2)+MID$(STR$(GY),2,2)+MID$(STR$(KAI),2,2)
		this.mapf$ = "DAN\\E" + this.fe$ + Math.abs(this.gx) + Math.abs(this.gy) + this.kai;
	}

	public void setMapf$danF() {
//		17420  MAPF$="DAN\F"+FE$+MID$(STR$(GX),2,2)+MID$(STR$(GY),2,2)
		this.mapf$ = "DAN\\F" + this.fe$ + this.gx + this.gy;
	}

	public void setMapf$map() {
//		17530  MAPF$="MAP\MAP"+MID$(STR$(GY),2,2)+"."+MID$(STR$(GX),2,2)
		this.mapf$ = "MAP\\MAP" + this.gy + "." + this.gx;
	}

	public void doukutu(String fe, int psx, int psy) {
		this.fe$ = fe;
		this.sx = psx;
		this.sy = psy;
	}

	public int getMap(int x, int y) {
		int ix = x + y * 9;
		return this.map[ix];
	}

	public int getMap() {
		return getMap(this.sx, this.sy);
	}

	public byte[] getMac(int ix) {
		int from = ix * 632 + 630;
		int to = from + 632 - 1;
		return Arrays.copyOfRange(this.mac, from, to);
	}

	public byte[] getG2() {
		return this.g2;
	}

	public byte[] getG4() {
		return this.g4;
	}

	public byte[] getG6() {
		return this.g6;
	}

	public byte[] getG8() {
		return this.g8;
	}

	public byte[] getTeki() {
		return this.teki;
	}

	public void setPos(int px, int py) {
		this.sx = px;
		this.sy = py;
	}

	public void setKaidan(int pgx, int pgy, int psx, int psy) {
		this.dgx = pgx;
		this.dgy = pgy;
		this.dsx = psx;
		this.dsy = psy;
	}

	public void pushD() {
		setKaidan(this.gx, this.gy, this.sx, this.sy);
	}

	public void popD() {
		this.gx = this.dgx;
		this.gy = this.dgy;
		this.sx = this.dsx;
		this.sy = this.dsy;
	}
	//-------------------------------------------------------------------------
	public int getGx() {
		return this.gx;
	}
	public void setGx(int val) {
		this.gx = val;
	}
	public void addGx(int val) {
		this.gx += val;
	}
	public int getGy() {
		return this.gy;
	}
	public void setGy(int val) {
		this.gy = val;
	}
	public void addGy(int val) {
		this.gy += val;
	}
	public int getSx() {
		return this.sx;
	}
	public void setSx(int val) {
		this.sx = val;
	}
	public void addSx(int val) {
		this.sx += val;
	}
	public int getSy() {
		return this.sy;
	}
	public void setSy(int val) {
		this.sy = val;
	}
	public void addSy(int val) {
		this.sy += val;
	}
	public int getDgx() {
		return this.dgx;
	}
	public void setDgx(int val) {
		this.dgx = val;
	}
	public int getDgy() {
		return this.dgy;
	}
	public void setDgy(int val) {
		this.dgy = val;
	}
	public int getDsx() {
		return this.dsx;
	}
	public void setDsx(int val) {
		this.dsx = val;
	}
	public int getDsy() {
		return this.dsy;
	}
	public void setDsy(int val) {
		this.dsy = val;
	}
	public int getKai() {
		return this.kai;
	}
	public void setKai(int val) {
		this.kai = val;
	}
	public void addKai(int val) {
		this.kai += val;
	}
	public int getTau() {
		return this.tau;
	}
	public void setTau(int val) {
		this.tau = val;
	}
	public int getTgi1() {
		return this.tgi1;
	}
	public void setTgi1(int val) {
		this.tgi1 = val;
	}
	public int getTgi2() {
		return this.tgi2;
	}
	public void setTgi2(int val) {
		this.tgi2 = val;
	}
	public int getTgi3() {
		return this.tgi3;
	}
	public void setTgi3(int val) {
		this.tgi3 = val;
	}
	public int getTgi4() {
		return this.tgi4;
	}
	public void setTgi4(int val) {
		this.tgi4 = val;
	}
	public int getTgi5() {
		return this.tgi5;
	}
	public void setTgi5(int val) {
		this.tgi5 = val;
	}
	public int getMati() {
		return this.mati;
	}
	public void setMati(int val) {
		this.mati = val;
	}
	public int getDimen() {
		return this.dimen;
	}
	public void setDimen(int val) {
		this.dimen = val;
	}
	public String getFe$() {
		return this.fe$;
	}
	public void setFe$(String val) {
		this.fe$ = val;
	}
	public void setFe$(String val, int px, int py) {
		this.fe$ = val;
		this.sx = px;
		this.sy = py;
	}
	public String getMapf$() {
		return this.mapf$;
	}
	public void setMapf$(String val) {
		this.mapf$ = val;
	}
	public byte[][] getEg() {
		return this.eg;
	}
}
