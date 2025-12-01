import java.util.*;

public class Building {
    private String name;
    private List<Enemy> enemies;
    private Enemy boss;
    private char key; 

    public Building(String name) {
        this.name = name;
        this.enemies = new ArrayList<>();
    }

    public void addEnemy(Enemy e) { enemies.add(e); }
    public void setBoss(Enemy boss) { this.boss = boss; }
    public void setKey(char key) { this.key = key; }
    public char getKey() { return key; }
    public String getBossName() { return boss != null ? boss.getName() : "Unknown"; }

    public int explore(Player player, Scanner sc, Inventory inv) {
        ASCII.printBox("ENTERING " + name);
        Utils.pause(500);

        int floor = 1;
        for (Enemy e : enemies) {
            ASCII.printMenuHeader("FLOOR " + floor);
            // FORCED CINEMATIC ENTRY
            Utils.forceSlowPrint("You hear footsteps echoing in the hall...", 30);
            Utils.pause(300);
            Utils.forceSlowPrint("You encounter " + e.getName() + "!", 30);
            Utils.pause(800);

            Battle battle = new Battle(player, e, sc, inv);
            int result = battle.start();

            if (result == 2) {
                System.out.println("🏃 You fled the building!");
                return 2; 
            }
            if (result == 0) {
                System.out.println("❌ Defeated on Floor " + floor);
                return 0; 
            }
            
            System.out.println("Floor cleared!");
            floor++;
            Utils.pause(500);
        }

        if (boss != null) {
            ASCII.printBox("⚠️ BOSS ROOM: " + boss.getName() + " ⚠️");
            Utils.pause(1000);
            
            Battle bossBattle = new Battle(player, boss, sc, inv);
            int bossResult = bossBattle.start();

            if (bossResult == 1) {
                System.out.println("🎉 Boss Defeated!");
                return 1;
            } else if (bossResult == 2) {
                System.out.println("🏃 Escaped!");
                return 2;
            } else {
                return 0;
            }
        }
        return 1;
    }
}