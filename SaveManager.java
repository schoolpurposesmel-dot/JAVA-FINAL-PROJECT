import java.io.*;

public class SaveManager {
    private static final String SAVE_FILE = "savegame.dat";
    public static void saveGame(Player player, Inventory inventory) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
            oos.writeObject(player);
            oos.writeObject(inventory);
            System.out.println("\n[!] Game Saved Successfully.");
        } catch (IOException e) {
            System.out.println("\n[X] Error saving game: " + e.getMessage());
        }
    }
    public static Object[] loadGame() {
        File f = new File(SAVE_FILE);
        if (!f.exists()) return null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_FILE))) {
            Player p = (Player) ois.readObject();
            Inventory i = (Inventory) ois.readObject();
            return new Object[]{p, i};
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("\n[X] Error loading game.");
            return null;
        }
    }
}