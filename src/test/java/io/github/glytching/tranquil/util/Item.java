package io.github.glytching.tranquil.util;

public class Item {
  private String name;
  private int quantity;

  public Item() {}

  public Item(String name, int quantity) {
    this.name = name;
    this.quantity = quantity;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    final Item item = (Item) o;

    if (quantity != item.quantity) return false;
    return name != null ? name.equals(item.name) : item.name == null;
  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + quantity;
    return result;
  }

  @Override
  public String toString() {
    return "Item{" + "name='" + name + '\'' + ", quantity=" + quantity + '}';
  }
}
