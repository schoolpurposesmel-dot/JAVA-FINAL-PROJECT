public class EnemyFactory {
    
    private static final double HP_MULTIPLIER = 3.0; // Standard HP x3
    private static final double DMG_MULTIPLIER = 1.5; // Standard DMG x1.5

    private static int hp(int base) { return (int)(base * HP_MULTIPLIER); }
    private static int dmg(int base) { return (int)(base * DMG_MULTIPLIER); }

    // === NORMAL ENEMIES (HP x3, DMG x1.5) ===
    public static Enemy createLostNode() { return new Enemy("Lost Node", hp(350), 225, new Skill("Pointer Jab", dmg(60), dmg(75), 30), new Skill("Memory Leak", dmg(90), dmg(105), 45), new Skill("Segfault", dmg(150), dmg(180), 135)); }
    public static Enemy createNullBug() { return new Enemy("Null Bug", hp(400), 225, new Skill("Null Ping", dmg(67), dmg(82), 30), new Skill("Code Lag", dmg(90), dmg(120), 45), new Skill("System Freeze", dmg(165), dmg(195), 105)); }
    public static Enemy createSyntaxGremlin() { return new Enemy("Syntax Gremlin", hp(450), 225, new Skill("Missing Semicolon", dmg(45), dmg(60), 60), new Skill("Bracket Jab", dmg(105), dmg(135), 37), new Skill("Compiler Panic", dmg(195), dmg(225), 90)); }
    public static Enemy createPacketGhoul() { return new Enemy("Packet Ghoul", hp(380), 225, new Skill("Ping Strike", dmg(75), dmg(90), 30), new Skill("Packet Loss", dmg(105), dmg(120), 37), new Skill("Network Timeout", dmg(180), dmg(210), 120)); }
    public static Enemy createPingWraith() { return new Enemy("Ping Wraith", hp(500), 300, new Skill("Echo Slam", dmg(120), dmg(150), 52), new Skill("ICMP Scream", dmg(135), dmg(165), 60), new Skill("Echo Flood", dmg(225), dmg(270), 180)); }
    public static Enemy createMemoryLeech() { return new Enemy("Memory Leech", hp(550), 300, new Skill("Stack Overflow", dmg(135), dmg(165), 60), new Skill("Memory Drain", dmg(120), dmg(150), 75), new Skill("Blue Screen", dmg(240), dmg(300), 135)); }
    public static Enemy createObjectGhost() { return new Enemy("Object Ghost", hp(700), 375, new Skill("Instantiate", dmg(75), dmg(90), 75), new Skill("Reference Bite", dmg(150), dmg(180), 60), new Skill("Inheritance Storm", dmg(270), dmg(330), 180)); }
    public static Enemy createStackPhantom() { return new Enemy("Stack Phantom", hp(420), 225, new Skill("Push Attack", dmg(105), dmg(120), 45), new Skill("Pop Smash", dmg(150), dmg(180), 75), new Skill("Overflow", dmg(210), dmg(240), 150)); }
    public static Enemy createRecursionShade() { return new Enemy("Recursion Shade", hp(600), 300, new Skill("Loop Strike", dmg(120), dmg(150), 60), new Skill("Echo Attack", dmg(105), dmg(135), 52), new Skill("Infinite Loop", dmg(270), dmg(315), 120)); }

    // === BOSSES (HP/DMG Buffed to retain challenge) ===
    public static Enemy createSirArbs() { return new Enemy("Sir Arbs", hp(2500), 1200, new Skill("Logic Gate Crash", dmg(225), dmg(270), 75), new Skill("Binary Barrage", dmg(270), dmg(330), 67), new Skill("System Override", dmg(525), dmg(600), 165)); }
    public static Enemy createMaamTulin() { return new Enemy("Ma'am Tulin", hp(2200), 1050, new Skill("Data Stream", dmg(210), dmg(255), 60), new Skill("Firewall Block", dmg(150), dmg(180), 52), new Skill("Network Blackout", dmg(450), dmg(525), 150)); }
    public static Enemy createSirKhai() { 
        // Sir Khai is already extremely tough, adding a slight multiplier.
        return new Enemy("Sir Khai", (int)(5000 * 1.5), 3000,
            new Skill("Terror Quiz", dmg(375), dmg(450), 90),
            new Skill("Deadline Strike", dmg(450), dmg(600), 120),
            new Skill("Final Grade: F", dmg(900), dmg(1200), 300)); 
    }
}