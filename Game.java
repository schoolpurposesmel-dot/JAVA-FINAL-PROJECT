import java.security.Guard;
import java.util.*;

public class Game {
    private final Scanner sc = new Scanner(System.in);
    private Story story = new Story(sc);
    private Player player;
    private Inventory inventory = new Inventory();
    private boolean running = true;
    private String characterName;
   
    public Game() {}

    public void launch() {
        while(true) {
            ASCII.printTitle();
            ASCII.printMenuHeader("TRALALERO TEKNOLOGIA");
            System.out.println(" [1] NEW GAME");
            System.out.println(" [2] CONTINUE");
            System.out.println(" [0] EXIT");
            System.out.print(" > ");
            
            String choice = sc.nextLine().trim();
            switch(choice) {
                case "1": startNewGame(); break;
                case "2": 
                    if(loadProgress()) {
                        System.out.println("Save loaded! Welcome back, " + player.getName());
                        mainLoop(); 
                    } else {
                        System.out.println("No save file found.");
                        Utils.pause(1000);
                    }
                    break;
                case "0": System.out.println("Goodbye!"); System.exit(0); break;
                default: System.out.println("Invalid.");
            }
        }
    }

    public void startNewGame() {
        Utils.setInstantTextMode();

        chooseCharacter();
        if (player != null) player.setInstantText(Utils.instantTextMode);

        story.startPrologue(characterName);
        
        System.out.println("\nSkip lore? (yes/no)");
        if (!sc.nextLine().trim().equalsIgnoreCase("yes")) story.loreIntro();

        story.continuation(characterName);
        
    System.out.println("\nA WILD LOST NODE APPEARS!");
    Utils.slowPrint("\n(" + characterName + "):\"Whoa! What was that?!\"", 20);
    Utils.slowPrint("(Guard): That's one of them! A corrupted being. A lost node. Quick! Prepare for Battle!", 20);  

    Utils.slowPrint("\"Use your mana wisely! Every still drains your energy, but landing hits restores some! Don’t let your Brainrot die!\"", 20);  
        // --- START: BATTLE READY LOOP (FIXED) ---
        boolean battleReady = false;
        System.out.println("\n-------------------------------------------");
        System.out.println("Prepare yourself for Battle. Type 'GO' to start the battle...");

        while (!battleReady) {
            System.out.print("> ");
            
            try {
                String input = sc.nextLine().trim();
                
                if (input.equalsIgnoreCase("GO")) {
                    battleReady = true;
                    System.out.println("\nGOODLUCK!");
                } else {
                    System.out.println("Invalid command. You must type 'GO' to proceed. Try again.");
                }
            } catch (Exception e) {
                // If the console input stream fails, log the error and force continuation.
                System.err.println("An unexpected input error occurred. Forcing battle start. Error: " + e.getMessage());
                battleReady = true;
            }
        }
        System.out.println("-------------------------------------------");
        
        if (player.getPartyCount() == 0) {
            player.addToParty(BrainrotFactory.randomBrainrot(player)); 
        }

        Enemy tutorialEnemy = EnemyFactory.createLostNode();
        Battle tutorialBattle = new Battle(player, tutorialEnemy, sc, inventory);
        tutorialBattle.start();

        story.duringBattle(characterName);
        story.afterBattle(characterName);

        mainLoop();
    }
    
    public boolean loadProgress() { 
        Object[] data = SaveManager.loadGame();
        if (data != null) {
            this.player = (Player) data[0];
            this.inventory = (Inventory) data[1];
            Utils.instantTextMode = player.isInstantText();
            return true;
        }
        return false; 
    }

    private void chooseCharacter() {
        CharacterSelection cs = new CharacterSelection(sc);
        characterName = cs.chooseCharacter(); 
        
        switch (characterName) {
            case "Carmel": player = new Tank(characterName); break;
            case "Trixy": player = new Healer(characterName); break;
            case "Sedric": player = new Fighter(characterName); break;
            case "Carlo": player = new Collector(characterName); break;
            default: 
                System.out.println("Defaulting to Fighter class.");
                player = new Fighter(characterName); 
                break;
        }
    }

    private void mainLoop() {
        running = true;
        while (running) {
            ASCII.printMenuHeader("MAIN MENU");
            System.out.println(" Player: " + player.getName() + " (Lvl " + player.getLevel() + ")");
            System.out.println(" Coins:  " + player.getCoins() + "  | Keys: " + player.keysString());
            System.out.println(".---------------------------------.");
            System.out.println("| [1] Farm Brainrots (Espacio)    |");
            System.out.println("| [2] View Backpack               |");
            System.out.println("| [3] Equip Brainrots             |");
            System.out.println("| [4] Enter Building              |");
            System.out.println("| [5] Canteen (Shop)              |");
            System.out.println("| [6] Clinic (Revive)             |");
            System.out.println("| [7] Show Party                  |");
            System.out.println("| [8] Upgrade Brainrot            |");
            System.out.println("| [9] Sell Brainrots              |"); 
            if (player.hasAllKeys()) {
                System.out.println("| [!] UNLOCK BACKGATE             |");
            }
            System.out.println("| [0] Save & Exit to Title        |");
            System.out.println("'---------------------------------'");
            System.out.print(" Choice > ");
            
            String c = sc.nextLine().trim();

            switch (c) {
                case "1": farmLoop(); break;
                case "2": inventory.printBackpack(); inventory.printItems(); break;
                case "3": equipMenu(); break;
                case "4": enterBuildingMenu(); break;
                case "5": visitCanteen(); break;
                case "6": visitClinic(); break;
                case "7": player.printParty(); break;
                case "8": upgradeMenu(); break;
                case "9": sellMenu(); break;
                case "!": 
                    if (player.hasAllKeys()) unlockBackgateSequence(); 
                    else System.out.println("Locked.");
                    break;
                case "0": 
                    SaveManager.saveGame(player, inventory);
                    running = false; 
                    break;
                default: System.out.println("Invalid command.");
            }
        }
    }

    private void enterBuildingMenu() {
        if (player.getPartyCount() == 0) {
            ASCII.printBox("LOCKED! You must equip Brainrots first.");
            System.out.println(" (Go to [3] Equip Brainrots in the Main Menu)");
            Utils.pause(1000);
            return;
        }

        ASCII.printMenuHeader("SELECT BUILDING");
        System.out.println(" [NGE]  [GLE]  [RTL]");
        System.out.print(" > ");
        String b = sc.nextLine().trim().toUpperCase();
        
        if (b.equals("GLE") && !player.hasKey('C')) {
            ASCII.printBox("LOCKED! You need the Key [C] from NGE first.");
            return;
        }
        if (b.equals("RTL") && (!player.hasKey('C') || !player.hasKey('I'))) {
            ASCII.printBox("LOCKED! You need Keys [C] and [I] first.");
            return;
        }

        Building building = null;
        switch(b) {
            case "NGE": building = BuildingFactory.createNGE(); break;
            case "GLE": building = BuildingFactory.createGLE(); break;
            case "RTL": building = BuildingFactory.createRTL(); break;
        }

        if (building != null) {
            story.bossEncounter(building.getBossName());
            int res = building.explore(player, sc, inventory);
            
            if (res == 1) { 
                if (b.equals("RTL")) {
                    handleSirKhaiDefeat();
                } else {
                    story.victory(building.getBossName());
                    if(building.getKey() != '\0' && !player.hasKey(building.getKey())) {
                        player.obtainKey(building.getKey());
                        System.out.println("You obtained Key [" + building.getKey() + "]!");
                    }
                }
            } else if (res == 0) { 
                story.defeat();
            }
        } else {
            System.out.println("Invalid building.");
        }
    }

    private void handleSirKhaiDefeat() {
        boolean originalInstantMode = Utils.instantTextMode;
        Utils.instantTextMode = false; 

        Utils.line();
        Utils.slowPrint("Sir Khai drops to one knee, the red code surrounding him flickering violently.", 30);
        Utils.pause(800);
        Utils.slowPrint("'Impossible... My calculations... were absolute...'", 40);
        Utils.pause(800);
        Utils.slowPrint("He looks up, his eyes turning from chaotic red to a fading white.", 40);
        Utils.slowPrint("'You have... passed the final evaluation. Do not... waste this freedom.'", 40);
        Utils.pause(1000);
        
        System.out.println("\n[SYSTEM] CRITICAL ERROR. BOSS DELETED.");
        Utils.pause(1000);
        ASCII.printBox("SIR KHAI DEFEATED");
        
        if (!player.hasKey('T')) {
            player.obtainKey('T');
            Utils.slowPrint("\nYou pick up the final Key: [T]. It hums with immense power.", 30);
        }
        
        Utils.slowPrint("Your pocket feels heavy. All three keys are reacting to each other.", 30);
        Utils.pause(1000);

        Utils.instantTextMode = originalInstantMode; 
        
        System.out.println("\n------------------------------------------------");
        System.out.println(" You just gathered all the keys.");
        System.out.println(" Want to unlock the backgate to escape?");
        System.out.println("------------------------------------------------");
        System.out.print(" (yes/no) > ");
        
        String choice = sc.nextLine().trim();
        if (choice.equalsIgnoreCase("yes")) {
            unlockBackgateSequence();
        } else {
            System.out.println("You decided to wait. Returning to main menu.");
        }
    }

    private void unlockBackgateSequence() {
        Utils.line();
        ASCII.printBox("THE BACKGATE");
        Utils.line();
        Utils.slowPrint("You stand before the massive digital gate at the back of the campus.", 30);
        
        if (!player.hasAllKeys()) {
            System.out.println("You don't have all the keys yet!");
            return;
        }

        System.out.println("\n[SYSTEM] INITIALIZING UNLOCK PROTOCOL...");
        Utils.pause(1000);

        System.out.print(" > Inserting Key [C]");
        for(int i=0; i<3; i++) { Utils.pause(400); System.out.print("."); }
        System.out.println(" [ACCEPTED]");
        
        System.out.print(" > Inserting Key [I]");
        for(int i=0; i<3; i++) { Utils.pause(400); System.out.print("."); }
        System.out.println(" [ACCEPTED]");

        System.out.print(" > Inserting Key [T]");
        for(int i=0; i<3; i++) { Utils.pause(400); System.out.print("."); }
        System.out.println(" [ACCEPTED]");

        Utils.pause(1000);
        System.out.println("\n[SYSTEM] AUTHENTICATION COMPLETE.");
        Utils.pause(800);
        System.out.println("[SYSTEM] DROPPING BARRIER...");
        Utils.pause(2000);

        ASCII.printBox("GATE OPENED");
        
        ASCII.printSirKhai(); 
        
        Utils.slowPrint("\nThe digital wall flickers and dissolves into millions of pixels.", 40);
        Utils.slowPrint("The cool, real air of the outside world hits your face.", 40);
        Utils.slowPrint("You have finally escaped CIT University.", 50);
        Utils.pause(3000);
        
        ASCII.printTitle();
        Utils.center("THANK YOU FOR PLAYING!");
        running = false;
    }

   private void farmLoop() {
        ASCII.printMenuHeader("ESPACIO FARMING");
        boolean farming = true;
        while(farming) {
            System.out.println(" [1] Search   [0] Back");
            System.out.print(" > ");
            String cmd = sc.nextLine().trim();

            try {
                // 1. Attempt to convert the string input to an integer
                int selection = Integer.parseInt(cmd);

                if (selection == 1) {
                    // --- Original Logic for [1] Search ---
                    if (Math.random() < 0.65) {
                        Brainrot found = BrainrotFactory.randomBrainrot(player);
                        ASCII.printBox("WILD " + found.getName().toUpperCase() + " APPEARED!");
                        System.out.println(found.describeShort());
                        if(inventory.addToBackpack(found)) System.out.println("Captured!");
                        else {
                            System.out.println("Backpack full! Sell some Brainrots.");
                            farming = false; // Exit loop if backpack is full
                        }
                    } else {
                        System.out.println("...nothing found...");
                    }
                } else if(selection == 0) {
                    // --- Original Logic for [0] Back ---
                    farming = false;
                } else {
                    // 3. Catch valid numbers that are not 1 or 0
                    System.out.println("Invalid command: Input must be 1 or 0.");
                }

            } catch (NumberFormatException e) {
                // 4. Catch input that is not a valid number (e.g., "a", "back")
                System.out.println("Invalid input format. Please enter a number (1 or 0).");
            }
        }
    }
    
    private void equipMenu() {
        ASCII.printMenuHeader("EQUIPMENT");
        inventory.printBackpack();
        System.out.println("Enter indices (e.g. 1,2) or 0 to exit:");
        String line = sc.nextLine().trim();
        if (line.equals("0")) return;
        String[] parts = line.split(",");
        player.clearParty();
        for (String p : parts) {
            try {
                int idx = Integer.parseInt(p.trim());
                Brainrot b = inventory.getFromBackpack(idx - 1);
                if (b != null) player.addToParty(b);
            } catch (Exception e) {}
        }
        System.out.println("Party updated.");
    }

    private void sellMenu() {
        boolean selling = true;
        while(selling) {
            ASCII.printMenuHeader("SELL SHOP");
            System.out.println(" Wallet: " + player.getCoins() + " coins");
            inventory.printBackpack();
            System.out.println(" Enter slot number to sell (or 0 to back):");
            System.out.print(" > ");
            String input = sc.nextLine().trim();
            if (input.equals("0")) { selling = false; continue; }
            try {
                int idx = Integer.parseInt(input) - 1;
                if (idx >= 0 && idx < inventory.size()) {
                    Brainrot b = inventory.getFromBackpack(idx);
                    if (player.party.contains(b)) { System.out.println("Cannot sell equipped brainrot!"); continue; }
                    int price = (b.getRarity() == Brainrot.Rarity.LEGENDARY) ? 300 : (b.getRarity() == Brainrot.Rarity.RARE ? 150 : 50);
                    System.out.println("Sell for " + price + "? (y/n)");
                    if (sc.nextLine().trim().equalsIgnoreCase("y")) {
                        inventory.removeAtIndex(idx);
                        player.addCoins(price);
                        System.out.println("Sold!");
                    }
                }
            } catch (Exception e) { System.out.println("Invalid input."); }
        }
    }

    private void visitCanteen() {
        boolean shopping = true;
        while(shopping) {
            ASCII.printMenuHeader("CANTEEN POTIONS");
            System.out.println(" Coins: " + player.getCoins());
            System.out.println(" [1] Lirili Echo Shake (Mana)  - 15c  | Effect: +80 MP");
            System.out.println(" [2] Ambalabu Cookie (Heal)    - 15c  | Effect: +120 HP");
            System.out.println(" [3] Tropi Slam Burger (Heal)  - 20c  | Effect: +200 HP");
            System.out.println(" [4] Assassino Espresso (Buff) - 40c  | Effect: 1.5x Dmg Next Turn");
            System.out.println(" [5] Halo-Halo Supreme (Full)  - 100c | Effect: Full HP & Mana");
            System.out.println(" [0] Exit");
            System.out.print(" > ");
            String c = sc.nextLine().trim();
            if (c.equals("0")) { shopping = false; continue; }
            int cost = 0; String item = "";
            switch(c) {
                case "1": cost=15; item="Lirili Echo Shake"; break;
                case "2": cost=15; item="Ambalabu Doll Cookie"; break;
                case "3": cost=20; item="Tropi Slam Burger"; break;
                case "4": cost=40; item="Assassino Espresso Shot"; break;
                case "5": cost=100; item="Canteen Halo-Halo Supreme"; break;
            }
            if (!item.isEmpty()) {
                if (player.getCoins() >= cost) {
                    player.spendCoins(cost); inventory.addItem(item);
                    System.out.println("Bought " + item + "!");
                } else System.out.println("Not enough coins.");
            }
        }
    }
    
    // === UPDATED: BUDGET CLINIC LOGIC ===
    private void visitClinic() {
        ASCII.printMenuHeader("CLINIC");
        System.out.println(" [1] Full Treatment (500c) - Revive & Full Heal");
        System.out.println(" [2] Budget Aid (250c)     - Add +50% HP/Mana to all");
        System.out.println(" [0] Exit");
        System.out.print(" > ");
        
        String c = sc.nextLine().trim();
        if (c.equals("1")) {
            if (player.getCoins() >= 500) {
                player.spendCoins(500); player.reviveAll(); player.healAll(); player.restoreManaAll();
                System.out.println("Fully Healed!");
            } else System.out.println("Not enough coins.");
        } else if (c.equals("2")) {
            if (player.getCoins() >= 250) {
                player.spendCoins(250); 
                player.reviveAll(); // Ensures fainted ones are back up first
                
                // ADD 50% HP/Mana to current stats (Instead of setting cap)
                for(int i=0; i<player.getPartyCount(); i++) {
                    Brainrot b = player.party.get(i);
                    
                    int healAmt = b.getMaxHp() / 2;
                    b.heal(healAmt); 
                    
                    int manaAmt = b.getMaxMana() / 2;
                    b.restoreMana(manaAmt);
                }
                System.out.println("Applied Budget Aid (+50% stats)!");
            } else System.out.println("Not enough coins.");
        }
    }
    
    private void upgradeMenu() {
        ASCII.printMenuHeader("UPGRADE");
        for (int i=0; i<player.getPartyCount(); i++) {
            System.out.println(" [" + (i+1) + "] " + player.party.get(i).describeShort());
        }
        System.out.println(" [0] Cancel");
        System.out.print(" Enter slot number to upgrade (50c) > ");
        
        try {
            int idx = Integer.parseInt(sc.nextLine().trim()) - 1;
            if (idx == -1) return;
            
            if (idx >= 0 && idx < player.getPartyCount()) {
                if (player.getCoins() >= 50) {
                    player.spendCoins(50);
                    player.party.get(idx).upgrade();
                } else System.out.println("Not enough coins.");
            } else System.out.println("Invalid slot.");
        } catch (Exception e) { System.out.println("Invalid input."); }
    }
}