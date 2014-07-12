package to.kit.rpg30;

/**
 * 敵情報.
 * @author ponta
 */
public final class Enemy {
	private String tna$;
	private int a;
	private int thp;
	private int tex;
	private int tat;
	private int tsh;
	private int thh;
	private int tgo;

	public Enemy(String name, int val, int hp, int ex, int at, int sh, int hh, int go) {
		this.tna$ = name;
		this.a = val;
		this.thp = hp;
		this.tex = ex;
		this.tat = at;
		this.tsh = sh;
		this.thh = hh;
		this.tgo = go;
	}

	public String getTna$() {
		return this.tna$;
	}
	public void setTna$(String val) {
		this.tna$ = val;
	}
	public int getA() {
		return this.a;
	}
	public void setA(int val) {
		this.a = val;
	}
	public void addA(int val) {
		this.a += val;
	}
	public int getThp() {
		return this.thp;
	}
	public void setThp(int val) {
		this.thp = val;
	}
	public void addThp(int val) {
		this.thp += val;
	}
	public int getTex() {
		return this.tex;
	}
	public void setTex(int val) {
		this.tex = val;
	}
	public int getTat() {
		return this.tat;
	}
	public void setTat(int val) {
		this.tat = val;
	}
	public int getTsh() {
		return this.tsh;
	}
	public void setTsh(int val) {
		this.tsh = val;
	}
	public void addTsh(int val) {
		this.tsh += val;
	}
	public int getThh() {
		return this.thh;
	}
	public void setThh(int val) {
		this.thh = val;
	}
	public int getTgo() {
		return this.tgo;
	}
	public void setTgo(int val) {
		this.tgo = val;
	}
}
