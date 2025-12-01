import java.util.*;
import java.io.Serializable;

public class Inventory implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private final List<Brainrot> backpack = new ArrayList<>();
    private final Map<String, Integer> items = new HashMap<>(); 
    private final int MAX_BRAINROTS = 10;

    public boolean addToBackpack(Brainrot b) {
        if (backpack.size() >= MAX_BRAINROTS) return false;
        backpack.add(b);
        return true;
    }

    public void printBackpack() {
        System.out.println("\n=== Brainrots (" + backpack.size() + "/10) ===");
        for (int i = 0; i < backpack.size(); i++) {
            Brainrot b = backpack.get(i);
            System.out.println((i + 1) + ") " + b.getName() + " [" + b.getRarity() + "] HP:" + b.getCurrentHp() + "/" + b.getMaxHp());
        }
    }

    public Brainrot getFromBackpack(int idx) {
        if (idx < 0 || idx >= backpack.size()) return null;
        return backpack.get(idx);
    }

    public Brainrot removeAtIndex(int idx) {
        if (idx < 0 || idx >= backpack.size()) return null;
        return backpack.remove(idx);
    }

    public int size() { return backpack.size(); }

    public void addItem(String itemName) {
        items.put(itemName, items.getOrDefault(itemName, 0) + 1);
    }

    public boolean useItem(String itemName) {
        if (!items.containsKey(itemName) || items.get(itemName) <= 0) {
            return false;
        }
        int count = items.get(itemName);
        if (count == 1) {
            items.remove(itemName);
        } else {
            items.put(itemName, count - 1);
        }
        return true;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public void printItems() {
        System.out.println("\n=== Item Bag ===");
        if (items.isEmpty()) {
            System.out.println("(Empty)");
        } else {
            for (Map.Entry<String, Integer> entry : items.entrySet()) {
                System.out.println("- " + entry.getKey() + " x" + entry.getValue());
            }
        }
    }
}