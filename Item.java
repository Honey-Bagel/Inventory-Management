public class Item {
	private String name;
	private float priceForEach;
	private int count;

	public Item(String newName, int newCount, float newPrice) {
		name = newName;
		count = newCount;
		priceForEach = newPrice;
	}

	/** Returns item name */
	public String getName() {
		return name;
	}

	/** Returns the price for 1 of this item */
	public float getPriceForEach() {
		return priceForEach;
	}

	/** Returns the total price for the current stock of this item */
	public float getTotalPrice() {
		return priceForEach * count;
	}

	/** Returns the current stock of this item */
	public int getCount() {
		return count;
	}

	/**
	 * Removes a specified amount from the current stock if possible
	 * 
	 * @param amount Specified amount to remove from stock
	 * @return Whether or not the amount was successfully removed
	 */
	public boolean remove(int amount) {
		if ((count - amount) >= 0) {
			count -= amount;
			return true;
		} else {
			return false; // Not enough stock
		}
	}

	/**
	 * Adds a specified amount to the current stock
	 * 
	 * @param amount Ammount to add to the current stock
	 * @return Whether or not the amount was successfully added
	 */
	public boolean add(int amount) {
		if ((count + amount) < Integer.MAX_VALUE) {
			count += amount;
			return true;
		} else {
			return false; // Count will be bigger than the max int value
		}
	}

	/**
	 * Checks whether or not this item is equal to another item
	 * 
	 * @param otherItem Item to check against this one
	 * @return If the two items are equal or not
	 */
	public boolean equals(Item otherItem) {
		return name.equals(otherItem.getName()) && priceForEach == otherItem.getPriceForEach()
				&& count == otherItem.getCount();

	}
}
