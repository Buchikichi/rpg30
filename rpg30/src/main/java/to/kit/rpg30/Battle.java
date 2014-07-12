package to.kit.rpg30;

/**
 * 戦闘情報.
 * @author ponta
 */
public final class Battle {
	/** 戦闘中. */
	private boolean tda;
	/** 逃げ. */
	private boolean nige;
	/** C. */
	private int c;
	/** 最後に戦った敵. */
	private Enemy lastEnemy;

	public boolean isTda() {
		return this.tda;
	}
	public void setTda(boolean b) {
		this.tda = b;
	}
	public boolean isNige() {
		return this.nige;
	}
	public void setNige(boolean b) {
		this.nige = b;
	}
	public int getC() {
		return this.c;
	}
	public void setC(int val) {
		this.c = val;
	}
	public Enemy getLastEnemy() {
		return this.lastEnemy;
	}
	public void setLastEnemy(Enemy val) {
		this.lastEnemy = val;
	}
}
