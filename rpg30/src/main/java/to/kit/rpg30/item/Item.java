package to.kit.rpg30.item;

/**
 * アイテム.
 * @author ponta
 */
public class Item {
	/** 商品名. */
	private final String name;
	/** 金額. */
	private final int amount;

	public Item(String itemName, int val) {
		this.name = itemName;
		this.amount = val;
	}

	public String getName() {
		return this.name;
	}
	public int getAmount() {
		return this.amount;
	}
}
