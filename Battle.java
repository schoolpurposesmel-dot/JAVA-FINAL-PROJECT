import java.util.*;

public class Battle {
    private final Player player;
    private final Enemy enemy;
    private final Scanner sc;
    private final Inventory inventory; 
    private Brainrot activeBrainrot;

    public Battle(Player p, Enemy e, Scanner sc, Inventory inv) { 
        this.player = p; 
        this.enemy = e; 
        this.sc = sc; 
        this.inventory = inv;
    }

    public int start() {
        activeBrainrot = player.getActiveBrainrot();
        if (activeBrainrot == null || activeBrainrot.isFainted()) {
            activeBrainrot = getNextAlive();
            if (activeBrainrot != null) player.setActiveBrainrot(activeBrainrot);
        }

        if (activeBrainrot == null) {
            ASCII.printBox("You have no conscious Brainrots!");
            return 0;
        }

        Utils.line();
        ASCII.printBox("BATTLE START: " + enemy.getName());
        Utils.pause(500);

        int turn = 1;

        while (!enemy.isFainted() && player.anyAliveInParty()) {
            ASCII.drawBattleUI(enemy, activeBrainrot);
            player.onTurnStart(activeBrainrot);

            boolean playerTurnOver = false; 
            
            while (!playerTurnOver) {
                System.out.println("  Choose action:");
                System.out.println("  [1] " + activeBrainrot.getSkill1().getName() + " (" + activeBrainrot.getSkill1().getManaCost() + " MP)");
                System.out.println("  [2] " + activeBrainrot.getSkill2().getName() + " (" + activeBrainrot.getSkill2().getManaCost() + " MP)");
                
                // 1. ULTIMATE COOLDOWN DISPLAY
                String ultCooldownTxt = activeBrainrot.isUltReady() ? "" : " (CD: " + activeBrainrot.getUltCooldown() + "t)";
                System.out.println("  [3] " + activeBrainrot.getUlt().getName() + " (" + activeBrainrot.getUlt().getManaCost() + " MP)" + ultCooldownTxt);
                
                System.out.println("  [4] Switch Brainrot");
                System.out.println("  [5] Bag");
                System.out.println("  [6] Run");
                
                // ROLE ABILITY OPTION
                String cooldownTxt = player.isAbilityReady() ? "(READY!)" : "(" + player.getCooldown() + " turns)";
                System.out.println("  [7] ROLE: " + player.getAbilityName() + " " + cooldownTxt);
                
                System.out.print("  > ");
                String input = sc.nextLine().trim();
                double dmgMult = player.getDamageMultiplier();

                switch (input) {
                    case "1": if(activeBrainrot.useSkill(1, enemy, dmgMult)) playerTurnOver = true; break;
                    case "2": if(activeBrainrot.useSkill(2, enemy, dmgMult)) playerTurnOver = true; break;
                    case "3": if(activeBrainrot.useSkill(3, enemy, dmgMult)) playerTurnOver = true; break;
                    case "4": switchBrainrot(); ASCII.drawBattleUI(enemy, activeBrainrot); break;
                    case "5": if(openBag()) { System.out.println(">> Item used!"); Utils.pause(800); } break;
                    case "6": System.out.println("You ran away safely!"); return 2; 
                    
                    case "7":
                        if (player.isAbilityReady()) {
                            if (player.useAbility(activeBrainrot)) {
                                player.resetAbilityCooldown();
                                Utils.pause(800);
                            }
                        } else {
                            System.out.println("Ability on cooldown!");
                        }
                        break;
                    
                    default: System.out.println("Invalid command.");
                }
                
                if (enemy.isFainted()) break;
            }

            if (enemy.isFainted()) {
                System.out.println("\n" + enemy.getName() + " was defeated!");
                processVictory();
                return 1;
            }

            System.out.println("\nEnemy's Turn...");
            Utils.pause(500);
            enemy.attack(activeBrainrot, turn);
            
            // DECREASE COOLDOWN AT END OF ROUND
            player.decreaseCooldown();
            // 2. DECREASE ULTIMATE COOLDOWN
            activeBrainrot.decreaseUltCooldown();

            if (activeBrainrot.isFainted()) {
                System.out.println("" + activeBrainrot.getName() + " fainted!");
                activeBrainrot = getNextAlive();
                if (activeBrainrot == null) {
                    ASCII.printBox("DEFEAT...");
                    return 0;
                }
                player.setActiveBrainrot(activeBrainrot);
                System.out.println("Go! " + activeBrainrot.getName() + "!");
            }
            turn++;
        }
        return 0;
    }

    private boolean switchBrainrot() {
        System.out.println("\n=== SELECT BRAINROT ===");
        for (int i = 0; i < player.party.size(); i++) {
            Brainrot b = player.party.get(i);
            String status = "";
            if (b.isFainted()) status = " [FAINTED]";
            else if (b == activeBrainrot) status = " [ACTIVE]";
            
            // MODIFIED LINE: Mana is now displayed as Current/Max
            String manaDisplay = b.getCurrentMana() + "/" + b.getMaxMana() + " MP";

            System.out.println(" [" + (i + 1) + "] " + b.getName() + status + 
                               " | HP: " + b.getCurrentHp() + "/" + b.getMaxHp() +
                               " | Mana: " + manaDisplay); // Updated display
        }
        System.out.println(" [0] Cancel");
        System.out.print(" Enter slot number > ");
        try {
            String input = sc.nextLine().trim();
            int index = Integer.parseInt(input) - 1; 
            if (input.equals("0")) return false;
            if (index >= 0 && index < player.party.size()) {
                Brainrot target = player.party.get(index);
                if (target.isFainted()) { System.out.println("Cannot switch to fainted."); return false; }
                if (target == activeBrainrot) { System.out.println("Already active."); return false; }
                activeBrainrot = target;
                player.setActiveBrainrot(target);
                System.out.println("Switched to " + activeBrainrot.getName() + "!");
                return true;
            } else System.out.println("Invalid slot.");
        } catch (NumberFormatException e) { System.out.println("Enter a number."); }
        return false;
    }

    private boolean openBag() {
        // (Use the numbered bag logic from previous response)
        Map<String, Integer> items = inventory.getItems();
        if (items.isEmpty()) { System.out.println("Bag is empty."); return false; }
        List<String> itemList = new ArrayList<>(items.keySet());
        System.out.println("\n=== BAG CONTENT ===");
        for (int i = 0; i < itemList.size(); i++) {
            System.out.println(" [" + (i+1) + "] " + itemList.get(i) + " (x" + items.get(itemList.get(i)) + ")");
        }
        System.out.println(" [0] Cancel");
        System.out.print(" Choose item > ");
        String input = sc.nextLine().trim();
        if (input.equals("0")) return false;
        try {
            int index = Integer.parseInt(input) - 1;
            if (index >= 0 && index < itemList.size()) {
                String choice = itemList.get(index);
                if (inventory.useItem(choice)) {
                    if (choice.contains("Shake") || choice.contains("Tea")) activeBrainrot.restoreMana(80);
                    else if (choice.contains("Cookie") || choice.contains("Burger")) activeBrainrot.heal(200);
                    else if (choice.contains("Espresso")) activeBrainrot.applyTempBuff(1.5);
                    else if (choice.contains("Halo-Halo")) { activeBrainrot.healFull(); activeBrainrot.restoreManaFull(); }
                    else { activeBrainrot.heal(100); }
                    return true;
                }
            }
        } catch (Exception e) { System.out.println("Error."); }
        return false;
    }

    private Brainrot getNextAlive() { return player.firstAlive(); }

    private void processVictory() {
        double rewardMult = player.getRewardMultiplier();
        int xpGain = (int)((40 + (player.getLevel() * 10)) * rewardMult);
        int coinGain = (int)((25 + Utils.randomBetween(0, 15)) * rewardMult);
        ASCII.printBox("VICTORY! +" + xpGain + "XP  +" + coinGain + " Coins");
        player.addXp(xpGain);
        player.addCoins(coinGain);
        player.restoreManaPartial(10); 
        Utils.pause(1000);
    }
}