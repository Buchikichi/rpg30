package to.kit.rpg30.item;


public abstract class Arms extends Item {
	/** 武器装着時の処理. */
	private ArmsFunction arms;

	@FunctionalInterface
	public interface ArmsFunction {
		void equip(int index);
	}

	public Arms(String itemName, int val) {
		super(itemName, val);
	}

	public void equip(int index) {
		if (this.arms != null) {
			this.arms.equip(index);
		}
	}

	public ArmsFunction getArms() {
		return this.arms;
	}
	public void setArms(ArmsFunction func) {
		this.arms = func;
	}
}
