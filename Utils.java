import java.util.Random;
import java.util.Scanner;

public class Utils {

    private static final Random rnd = new Random();
    public static boolean instantTextMode = false; 

    // =========================================================
    // 💡 NEW METHOD: Handles YES/NO input and sets the mode
    // =========================================================
    public static void setInstantTextMode() {
        // Creates a new Scanner for local use in this method
        Scanner scanner = new Scanner(System.in); 
        String input;
        
        System.out.println("Enable instant text mode? (yes/no)");
        
        // Loop until valid input is received
        while (true) {
            System.out.print("> ");
            // Read input, remove spaces, and convert to uppercase for robust checking
            input = scanner.nextLine().trim().toUpperCase(); 

            if (input.equals("YES") || input.equals("Y")) {
                instantTextMode = true;
                System.out.println("Instant text mode is now ON.");
                break; // Exit the loop
            } 
            else if (input.equals("NO") || input.equals("N")) {
                instantTextMode = false;
                System.out.println("Instant text mode is now OFF (Narrative text will be slow).");
                break; // Exit the loop
            } 
            else {
                // Invalid Input: Print error and loop again
                System.err.println("ERROR: Invalid input. Please type exactly 'YES' or 'NO'.");
            }
        }
      
    }

    public static boolean chance(int percent) {
        return rnd.nextInt(100) < percent;
    }

    public static int randomBetween(int low, int high) {
        return low + rnd.nextInt(Math.max(1, high - low + 1));
    }

    // Respects the "Instant Text" setting (Use for Lore/Intro)
    public static void slowPrint(String text, long delayMillis) {
        long actualDelay = instantTextMode ? 0 : delayMillis;
        for (char c : text.toCharArray()) {
            System.out.print(c);
            try { 
                if (actualDelay > 0) Thread.sleep(actualDelay); 
            }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }
        System.out.println();
    }

    // Ignores settings, ALWAYS slow (Use for Dramatic Battles/Bosses)
    public static void forceSlowPrint(String text, long delayMillis) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            try { 
                Thread.sleep(delayMillis); 
            }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }
        System.out.println();
    }

    public static void pause(long ms) {
        long actualPause = instantTextMode ? Math.min(ms, 100) : ms;
        try { Thread.sleep(actualPause); }
        catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    public static void center(String text) {
        int width = 50;
        int pad = Math.max(0, (width - text.length()) / 2);
        System.out.println(" ".repeat(pad) + text);
    }

    public static void line() {
        System.out.println("==================================================");
    }
}