package to.kit.rpg30;

/**
 * アイテム.
 * @author ponta
 */
public final class Item {
	/** 商品名. */
	private String name;
	/** 金額. */
	private int amount;

	public Item(String itemName, int val) {
		this.name = itemName;
		this.amount = val;
	}

	public String getName() {
		return this.name;
	}
	public void setName(String val) {
		this.name = val;
	}
	public int getAmount() {
		return this.amount;
	}
	public void setAmount(int val) {
		this.amount = val;
	}
}
