import java.util.Scanner;

public class CharacterSelection {
    private Scanner sc;
    public CharacterSelection(Scanner sc) { this.sc = sc; }


    public  String chooseCharacter(){
        String selectedCharacter = "";
        boolean confirmed = false;
        

         while (!confirmed) {
            boolean selectionValid = false; // Reset validity for each round of selection
            
            // Inner loop runs until a valid number (1-4) or a number-format exception is handled
            while (!selectionValid) {
                System.out.println("\n===== CHARACTER SELECTION =====");
                System.out.println("\nChoose your character:");
                System.out.println("1. Carmel (Tank)");
                System.out.println("2. Trixy (Healer)");
                System.out.println("3. Sedric (Fighter)");
                System.out.println("4. Carlo (Collector)");
                System.out.print("Enter the number of your choice: ");

                try {
                    String input = sc.nextLine().trim();
                    // 1. ACTION THAT MAY THROW EXCEPTION: Convert input string to integer
                    int choiceNumber = Integer.parseInt(input);
                    
                    // 2. VALIDATE RANGE: Check if the number is within 1 and 4
                    switch (choiceNumber) {
                        case 1:
                            selectedCharacter = "Carmel";
                            selectionValid = true; 
                            break;
                        case 2:
                            selectedCharacter = "Trixy";
                            selectionValid = true;
                            break;
                        case 3:
                            selectedCharacter = "Sedric";
                            selectionValid = true;
                            break;
                        case 4:
                            selectedCharacter = "Carlo";
                            selectionValid = true;
                            break;
                        default:
                            // If the number is outside 1-4 range (e.g., 5, -1)
                            System.out.println("Invalid number. Please select 1, 2, 3, or 4.");
                    }
                    
                    // 3. EXECUTE LOGIC: If valid, show backstory
                    if (selectionValid) {
                        showBackstory(selectedCharacter);
                    }
                    
                } catch (NumberFormatException e) {
                    // 4. CATCH EXCEPTION: This runs if the input was NOT a number (e.g., "a", "hello").
                    System.err.println("Invalid input. Please enter a numerical choice (1, 2, 3, or 4).");
                    // selectionValid remains false, so the inner loop repeats
                }
            } // End of inner selection loop (selectionValid == true)

        
            // 5. CONFIRMATION STEP (Only runs after a valid selection is made)
            System.out.print("\nYou selected " + selectedCharacter + ". Confirm? (yes/no): ");
            String confirmInput = sc.nextLine().trim().toLowerCase();
            
            if(confirmInput.equals("yes") || confirmInput.equals("y")){
                confirmed = true;
                System.out.println("\n ===== Character locked in! You are now playing as " + selectedCharacter + " ======\n");
            } else if (confirmInput.equals("no") || confirmInput.equals("n")) {
                // If they say no, confirmed remains false, and the outer loop restarts the entire selection process.
                System.out.println("\nLet's pick again!");
            } else {
                // Invalid confirmation response. Treat it as a 'no' and restart the process.
                System.out.println("Invalid confirmation. Returning to selection screen.");
            }
        } // End of outer confirmed loop

        return selectedCharacter;
    }

public void showBackstory(String name){
    System.out.println("\n====== " + name.toUpperCase() + " BACKSTORY ======\n");

    

    switch(name){
        case "Carmel":
            Utils.slowPrint("She had stayed late at the gym for extra tennis practice, headphones in and racket in hand as she hit balls against the wall", 20);
            Utils.slowPrint("balls bouncing against the wall echoed in the quiet gym. Then the power cut out, plunging the entire court into darkness.", 20);    
            Utils.slowPrint("The gym doors slammed shut on their own, and her phone lit up with one single message before dying: \"RUN.\" Her heart was racing,", 20);    
            Utils.slowPrint("as she looked for the exits only to find that the gate was locked. The eerie noises grew louder,", 20);    
            Utils.slowPrint("and even though she couldn't see anything, she felt it. Something was coming for her, and she had to be ready to face it.", 20);    
            break;
         case "Trixy":
            Utils.slowPrint("She had been cramming all night for her Java test, face buried in her laptop practicing problems in the quietest corner of the library.", 20);
            Utils.slowPrint("The sound of the rain outside and the steady hum of the warm lights lulled her to sleep.", 20);
            Utils.slowPrint("When she woke up, the library was quiet... too quiet. Chairs were overturned, books were scattered across the floor,", 20);
            Utils.slowPrint("as if everyone had left in a hurry. She walked around the library, calling out for anyone, but her voice only echoed back at her.", 20);
            Utils.slowPrint("Something was wrong. She could feel eyes on her every time the lights flickered.", 20);
            Utils.slowPrint("With no one around, she grabbed her notes and kept whispering to herself, \"I just have get out of here.\"", 20);
            break;

        case "Sedric":
            Utils.slowPrint("He had been staying late in a classroom, running through his presentation for the tenth time.", 20);
            Utils.slowPrint("The projector kept glitching, playing strange shadows on the wall, and the power flickered more than once.", 20);
            Utils.slowPrint("When he finally stepped out into the hallway, the entire building was silent.", 20);
            Utils.slowPrint("The gate was chained shut, and the announcement speakers suddenly crackled,", 20);
            Utils.slowPrint("playing a warped, slowed-down version of the school hymn on repeat.", 20);
            Utils.slowPrint("He slammed his fist against the locked gate and muttered, \"Alright then. If something wants to keep me here, it's going to regret it.\"", 20);
            Utils.slowPrint("With no one else around, he knew there was only one way forward: fight through the night with whatever he could find and make it out alive.", 20);
            break;

        case "Carlo":
            Utils.slowPrint("He had been exploring the dusty storage rooms again, flashlight in one hand, looking for old papers and answer keys.", 20);
            Utils.slowPrint("It was supposed to be a harmless late-night adventure.", 20);
            Utils.slowPrint("But then he heard it: footsteps behind him.", 20);
            Utils.slowPrint("When he turned around, the hallway was empty.", 20);
            Utils.slowPrint("His flashlight flickered, and for just a second, he saw a reflection in a cracked window:", 20);
            Utils.slowPrint("his own face, smiling at him - even though he wasn't smiling.", 20);
            Utils.slowPrint("Heart pounding, he ran outside but the front gate was locked.", 20);
            Utils.slowPrint("The lights above him buzzed, and the shadow behind him grew longer.", 20);
            break;
        default:
            System.out.println("No backstory available.");
    }
}

}
