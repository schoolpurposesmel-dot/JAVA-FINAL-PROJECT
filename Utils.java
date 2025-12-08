import java.util.Random;
import java.util.Scanner;

public class Utils {

    private static final Random rnd = new Random();
    public static boolean instantTextMode = false; 

   
    public static void setInstantTextMode() {
        
        Scanner scanner = new Scanner(System.in); 
        String input;
        
        System.out.println("Enable instant text mode? (yes/no)");
        
        
        while (true) {
            System.out.print("> ");
            
            input = scanner.nextLine().trim().toUpperCase(); 

            if (input.equals("YES") || input.equals("Y")) {
                instantTextMode = true;
                System.out.println("Instant text mode is now ON.");
                break; 
            } 
            else if (input.equals("NO") || input.equals("N")) {
                instantTextMode = false;
                System.out.println("Instant text mode is now OFF (Narrative text will be slow).");
                break; 
            } 
            else {
                
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