import java.util.Scanner;

public class Story {

    public Story(Scanner sc) {
    }

    public void titleScreen() {}

    public void startPrologue(String name) {
        Utils.slowPrint("The adventure begins at the front gate...", 20);
        Utils.slowPrint("(" + name + "): *grabs the bars, shaking it violently* \"Come on!\"", 20);
        Utils.slowPrint("" + name + " then realizes that it was barred by some unknown force. Almost like magic.", 20);
        Utils.slowPrint("(" + name + "): *steps back, breathing hard* \"What the..? It's sealed?\"", 20);
        Utils.slowPrint(name + " panics at the thought of not being able to escape the campus and their possible demise,", 20);
        Utils.slowPrint(name + " hears a faint static on the far right of the entrance. The guard house.", 20);
        Utils.slowPrint("As " + name + " got closer, the sound became less and less static and started to sound like a person's voice.", 20);
        Utils.slowPrint(name + " eventually found the thing that was making the noise: a walkie-talkie.", 20);
        Utils.slowPrint(name + " calls for help, and is answered by someone.", 20);
        Utils.slowPrint("(Voice through walkie - talkie): \"-lo? Hel-? Anyone out there?\"", 20);
        Utils.slowPrint("(" + name + "): *picks up the walkie talkie* \"Yes! I'm here! Who is this?\"", 20);
        Utils.slowPrint("Guard(through static): \"Finally! I'm the guard stationed at the back gate. Don't panic kid, you're safe for now.\"", 20);
        Utils.slowPrint("(" + name + "): \"Safe? The whole campus feels..wrong. What's going on?\"", 20);
        Utils.slowPrint("(Guard): \"It's the storm. It awakened something ancient beneath the school, old energy that's been buried for decades.\"", 20);
         Utils.slowPrint("(Guard): \"Now it's leaking out, twisting everything it touches.\"", 20);
    }

    public void loreIntro() {
        Utils.slowPrint("\nDue to the frequent heavy storms, the campus of CIT-U has been plagued by ancient energy that has long existed", 20);
        Utils.slowPrint("and been buried beneath the campus, but was awakened by the disturbance caused by the floods.", 20);
        Utils.slowPrint("This caused the people present on the campus to be possessed by negative energy and become hostile.", 20);
        Utils.slowPrint("Most of the creatures wander in the large buildings that loom over the campus..", 20);
        Utils.slowPrint("Due to this, CIT-U has been abandoned because of the danger it poses to the students..", 20);
        Utils.slowPrint("However, the powers that rested beneath the school were not all wicked;.", 20);
        Utils.slowPrint("some powers that seeped to other creatures gave them abilities to fight and train with other people if they were lucky enough to catch them. ", 20);
       

    }

    public void continuation(String name) {
        Utils.slowPrint("\n(" + name + "):\"Cursed? Then how do I get out?\"", 20);
        Utils.slowPrint("(Guard): \"You'll need help, and not the kind you're used to.\"", 20);
        Utils.slowPrint("(Guard): \"There should be a chest inside. Open it.\"", 20);
        Utils.slowPrint("*" + name + " opens the chest* ", 20);
        Utils.slowPrint("(Guard): \"This is a Brainrot. A creature born from the same energy that corrupted the campus.\"", 20);
        Utils.slowPrint("(Guard): \"Not all of them turned hostile. Some retained their will, and their strength.\"", 20);
        Utils.slowPrint("(Guard): \"Use this for now to be with you as you escape this hellish campus.”\"", 20);
        Utils.slowPrint("(Guard): \"Don't worry, you can collect more later.\"", 20);

    }

    public void startofbattle(String name) {
        Utils.forceSlowPrint("A corrupted entity blocks your path!", 30);
    }

    public void duringBattle(String name) {}

    public void afterBattle(String name) {}

    // === UPDATED: DRAMATIC SIR KHAI ENTRANCE ===
    public void bossEncounter(String bossName) {
        Utils.line();
        ASCII.printBox("WARNING: HIGH LEVEL THREAT DETECTED");
        Utils.pause(1000);

        if (bossName.contains("Sir Khai")) {
            // === SIR KHAI EXCLUSIVE: ULTRA DRAMATIC ===
            Utils.forceSlowPrint("\n......", 150); 
            Utils.pause(1000);
            
            Utils.forceSlowPrint("The temperature in the room drops instantly.", 40);
            Utils.pause(1000);
            
            Utils.forceSlowPrint("Tick... tock... tick... tock...", 80);
            Utils.pause(1000);
            
            Utils.forceSlowPrint("Heavy footsteps echo from the darkness.", 50);
            Utils.pause(1500);
            
            Utils.forceSlowPrint("Sir Khai emerges. He checks his stopwatch with a cold stare.", 50);
            Utils.pause(1000);
            
            Utils.forceSlowPrint("\nSIR KHAI: 'You are late.'", 100); 
            Utils.pause(1000);
            
            Utils.forceSlowPrint("SIR KHAI: 'I gave you the syllabus... I gave you time...'", 60);
            Utils.pause(800);
            
            Utils.forceSlowPrint("SIR KHAI: 'But now? The deadline has passed.'", 60);
            Utils.pause(1000);
            
            Utils.forceSlowPrint("SIR KHAI: 'Submit your final breath... OR PERISH.'", 90);
        } 
        else if (bossName.contains("Sir Arbs")) {
            // Standard Cinematic (Faster)
            Utils.forceSlowPrint("\nThe computer screens in the room flicker uncontrollably.", 30);
            Utils.forceSlowPrint("SIR ARBS: 'You think your logic is sound? Let's compile your demise.'", 40);
        } 
        else if (bossName.contains("Ma'am Tulin")) {
            // Standard Cinematic (Faster)
            Utils.forceSlowPrint("\nThe network cables on the ceiling begin to writhe like snakes.", 30);
            Utils.forceSlowPrint("MA'AM TULIN: 'I will sever your packet flow permanently.'", 40);
        } 
        else {
            Utils.forceSlowPrint("A powerful enemy, " + bossName + ", blocks your path!", 30);
        }
        
        Utils.pause(1000);
        System.out.println("\n[!] BOSS BATTLE INITIATED");
        Utils.pause(1000);
    }

    public void victory(String bossName) {
        Utils.line();
        if (bossName.contains("Sir Arbs")) {
            Utils.forceSlowPrint("Sir Arbs collapses, his body glitching into static.", 40);
            Utils.pause(500);
            Utils.forceSlowPrint("SIR ARBS: 'Runtime Error... System Halted...'", 50);
            Utils.forceSlowPrint("SIR ARBS: 'You have debugged the system... Accept this Key [C]...'", 50);
        }
        else if (bossName.contains("Ma'am Tulin")) {
            Utils.forceSlowPrint("Ma'am Tulin's energy shield shatters into glass-like shards.", 40);
            Utils.pause(500);
            Utils.forceSlowPrint("MA'AM TULIN: 'My connection... is lost...'", 50);
            Utils.forceSlowPrint("MA'AM TULIN: 'Take this Key [I]... ensure the network stays alive...'", 50);
        }
        else if (bossName.contains("Sir Khai")) {
            Utils.forceSlowPrint("Sir Khai falls to one knee, looking at his stopwatch.", 60); // Slower for drama
            Utils.pause(800);
            Utils.forceSlowPrint("SIR KHAI: 'Time... is up...'", 80);
            Utils.pause(800);
            Utils.forceSlowPrint("SIR KHAI: 'You have passed the final exam... Accept this Key [T]...'", 60);
        }
        else {
            Utils.forceSlowPrint("\n" + bossName + " falls to the ground, the corruption fading.", 30);
        }
        Utils.pause(1000);
    }

    public void defeat() {
        Utils.forceSlowPrint("\nYour vision fades... you were not strong enough.", 40);
    }
}