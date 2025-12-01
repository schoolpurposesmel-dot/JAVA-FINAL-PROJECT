import java.util.Scanner;

public class Potions {

    
    private static Potion[] commonPotions = {
        new Potion("Lirili Echo Shake", "Restores small mana.", "Brainrot", "Common"),
        new Potion("Ambalabu Doll Cookie", "Heals Brainrot slightly, chance to resist stun.", "Brainrot", "Common"),
        new Potion("Bombini Honey Tea", "Regenerates small HP each turn.", "Brainrot", "Common"),
        new Potion("Tropi Slam Burger", "Restores HP and slightly increases attack.", "Brainrot", "Common"),
        new Potion("Banana Peel Smoothie", "Cures paralysis and restores small HP.", "Brainrot", "Common"),
        new Potion("Frutodrilo Fruit Punch", "Restores HP and slightly boosts defense.", "Brainrot", "Common"),
        new Potion("Mini Mango Pie", "Cures burn effects.", "Brainrot", "Common"),
        new Potion("Chips ni Tropi", "Restores small HP and boosts speed.", "Brainrot", "Common")
    };

    private static Potion[] rarePotions = {
        new Potion("Assassino Espresso Shot", "Restores a large amount of mana but lowers defense.", "Brainrot", "Rare"),
        new Potion("Crocodilo Bomb Wrap", "Adds a strong shield for one hit.", "Brainrot", "Rare"),
        new Potion("Patapim Ice Pop", "Restores mana and slows the enemy for one turn.", "Brainrot", "Rare"),
        new Potion("Ballerina Latte Brew", "Restores HP and increases evasion.", "Brainrot", "Rare"),
        new Potion("Honey Glazed Drumstick", "Restores both HP and mana.", "Brainrot", "Rare"),
        new Potion("Brainrot Bento Meal", "Heals HP and gives a random small buff.", "Brainrot", "Rare")
    };

    private static Potion[] legendaryPotions = {
        new Potion("Tralala Opera Brew", "Fully restores mana and gives a magic buff for three turns.", "Brainrot", "Legendary"),
        new Potion("Sahur Dawn Soup", "Fully restores HP and grants a strong shield.", "Brainrot", "Legendary"),
        new Potion("Golden Graduation Latte", "Revives a fainted Brainrot with full HP.", "Brainrot", "Legendary"),
        new Potion("Canteen Halo-Halo Supreme", "Restores HP and grants a random buff.", "Brainrot", "Legendary"),
        new Potion("Mystery Smoothie", "Gives a random effect: heal, buff, or debuff.", "Brainrot", "Legendary")
    };

    
    private static Potion[] healerPotions = {
        new Potion("Pandesal of Purity", "Cures status ailments.", "Healer", "Support"),
        new Potion("Wintermelon Mana Tea", "Restores small mana.", "Healer", "Support"),
        new Potion("Ice Cream of Inspiration", "Heals all Brainrot slightly.", "Healer", "Support"),
        new Potion("Healing Honey Toast", "Gradually restores HP over time.", "Healer", "Support"),
        new Potion("Salabat of Serenity", "Cures silence and restores mana.", "Healer", "Support")
    };

    private static Potion[] fighterPotions = {
        new Potion("Siomai Rice Power Meal", "Restores medium HP.", "Fighter", "Support"),
        new Potion("TapaSilog Energy Bowl", "Restores HP + strong attack buff.", "Fighter", "Support"),
        new Potion("Nova Crunch", "Small HP heal + mini rage buff.", "Fighter", "Support"),
        new Potion("Spicy Dynamite Roll", "Boosts attack power for 3 turns.", "Fighter", "Support"),
        new Potion("Karaage Power Pack", "Heals Brainrot and gives critical hit chance boost.", "Fighter", "Support")
    };

    private static Potion[] collectorPotions = {
        new Potion("Lucky Cookie", "Increases drop rate in next fight.", "Collector", "Support"),
        new Potion("Choco Chips Delight", "Restores HP + mana.", "Collector", "Support"),
        new Potion("Collector’s Soda Pop", "Increases chance of finding Brainrot.", "Collector", "Support"),
        new Potion("Treasure Bun", "Doubles coins earned in next fight.", "Collector", "Support"),
        new Potion("Espacio Special Fries", "Boosts encounter rate of rare Brainrot.", "Collector", "Support")
    };

    private static Potion[] tankPotions = {
        new Potion("Fita Shield Biscuit", "Adds small shield.", "Tank / Strategist", "Support"),
        new Potion("Chicken Ramen Warmth", "Restores HP + buffs defense.", "Tank / Strategist", "Support"),
        new Potion("Strategist’s Black Coffee", "Reveals enemy weakness in battle.", "Tank / Strategist", "Support"),
        new Potion("Lumpia Wall Wrap", "Gives shield to absorb multiple small attacks.", "Tank / Strategist", "Support"),
        new Potion("Brainy Iced Tea", "Restores mana and reduces enemy accuracy.", "Tank / Strategist", "Support")
    };

    
    public static void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean viewing = true;

        while (viewing) {
            System.out.println("\n===== BRAINROT CANTEEN POTIONS MENU =====");
            System.out.println("1. Common Brainrot Potions");
            System.out.println("2. Rare Brainrot Potions");
            System.out.println("3. Legendary Brainrot Potions");
            System.out.println("4. Character Support Potions");
            System.out.println("5. Exit Potion Menu");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    selectPotion(scanner, commonPotions, "Common Brainrot Potions");
                    break;
                case "2":
                    selectPotion(scanner, rarePotions, "Rare Brainrot Potions");
                    break;
                case "3":
                    selectPotion(scanner, legendaryPotions, "Legendary Brainrot Potions");
                    break;
                case "4":
                    displaySupportMenu(scanner);
                    break;
                case "5":
                    viewing = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1–5.");
            }
        }
    }

    
    private static void displaySupportMenu(Scanner scanner) {
        boolean viewingSupport = true;

        while (viewingSupport) {
            System.out.println("\n=== CHARACTER SUPPORT POTIONS ===");
            System.out.println("1. Healer Potions");
            System.out.println("2. Fighter Potions");
            System.out.println("3. Collector Potions");
            System.out.println("4. Tank / Strategist Potions");
            System.out.println("5. Go Back");
            System.out.print("Choose a category: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    selectPotion(scanner, healerPotions, "Healer Potions");
                    break;
                case "2":
                    selectPotion(scanner, fighterPotions, "Fighter Potions");
                    break;
                case "3":
                    selectPotion(scanner, collectorPotions, "Collector Potions");
                    break;
                case "4":
                    selectPotion(scanner, tankPotions, "Tank / Strategist Potions");
                    break;
                case "5":
                    viewingSupport = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1–5.");
            }
        }
    }

    
    private static void selectPotion(Scanner scanner, Potion[] potions, String title) {
        System.out.println("\n-- " + title + " --");
        for (int i = 0; i < potions.length; i++) {
            System.out.println((i + 1) + ". " + potions[i].getName());
        }
        System.out.println((potions.length + 1) + ". Go back");
        System.out.print("Choose an potion: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice >= 1 && choice <= potions.length) {
                Potion selected = potions[choice - 1];
                System.out.println("\nYou chose: " + selected.getName());
                System.out.println("Rarity: " + selected.getRarity());
                System.out.println("Type: " + selected.getType());
                System.out.println("Effect: " + selected.getEffect());

                System.out.print("\nWould you like to use this potion? (y/n): ");
                String use = scanner.nextLine();
                if (use.equalsIgnoreCase("y")) {
                    System.out.println("You used " + selected.getName() + "! " + selected.getEffect());
                } else {
                    System.out.println("Potion saved for later.");
                }
            } else if (choice == potions.length + 1) {
                return;
            } else {
                System.out.println("Invalid selection.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }
}




