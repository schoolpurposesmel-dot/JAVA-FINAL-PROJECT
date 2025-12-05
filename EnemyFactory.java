public class EnemyFactory {
    // Normal Enemies (Original HP, Retained High Mana/Damage)
    public static Enemy createLostNode() { return new Enemy("Lost Node", 350, 225, new Skill("Pointer Jab", 60, 75, 30), new Skill("Memory Leak", 90, 105, 45), new Skill("Segfault", 150, 180, 135)); }
    public static Enemy createNullBug() { return new Enemy("Null Bug", 400, 225, new Skill("Null Ping", 67, 82, 30), new Skill("Code Lag", 90, 120, 45), new Skill("System Freeze", 165, 195, 105)); }
    public static Enemy createSyntaxGremlin() { return new Enemy("Syntax Gremlin", 450, 225, new Skill("Missing Semicolon", 45, 60, 60), new Skill("Bracket Jab", 105, 135, 37), new Skill("Compiler Panic", 195, 225, 90)); }
    public static Enemy createPacketGhoul() { return new Enemy("Packet Ghoul", 380, 225, new Skill("Ping Strike", 75, 90, 30), new Skill("Packet Loss", 105, 120, 37), new Skill("Network Timeout", 180, 210, 120)); }
    public static Enemy createPingWraith() { return new Enemy("Ping Wraith", 500, 300, new Skill("Echo Slam", 120, 150, 52), new Skill("ICMP Scream", 135, 165, 60), new Skill("Echo Flood", 225, 270, 180)); }
    public static Enemy createMemoryLeech() { return new Enemy("Memory Leech", 550, 300, new Skill("Stack Overflow", 135, 165, 60), new Skill("Memory Drain", 120, 150, 75), new Skill("Blue Screen", 240, 300, 135)); }
    public static Enemy createObjectGhost() { return new Enemy("Object Ghost", 700, 375, new Skill("Instantiate", 75, 90, 75), new Skill("Reference Bite", 150, 180, 60), new Skill("Inheritance Storm", 270, 330, 180)); }
    public static Enemy createStackPhantom() { return new Enemy("Stack Phantom", 420, 225, new Skill("Push Attack", 105, 120, 45), new Skill("Pop Smash", 150, 180, 75), new Skill("Overflow", 210, 240, 150)); }
    public static Enemy createRecursionShade() { return new Enemy("Recursion Shade", 600, 300, new Skill("Loop Strike", 120, 150, 60), new Skill("Echo Attack", 105, 135, 52), new Skill("Infinite Loop", 270, 315, 120)); }

    // BOSSES (Original HP, Retained High Mana/Damage)
    public static Enemy createSirArbs() { return new Enemy("Sir Arbs", 2500, 1200, new Skill("Logic Gate Crash", 225, 270, 75), new Skill("Binary Barrage", 270, 330, 67), new Skill("System Override", 525, 600, 165)); }
    public static Enemy createMaamTulin() { return new Enemy("Ma'am Tulin", 2200, 1050, new Skill("Data Stream", 210, 255, 60), new Skill("Firewall Block", 150, 180, 52), new Skill("Network Blackout", 450, 525, 150)); }
    
    // FINAL BOSS 
    public static Enemy createSirKhai() { 
        return new Enemy("Sir Khai", 5000, 3000,
            new Skill("Terror Quiz", 375, 450, 90),
            new Skill("Deadline Strike", 450, 600, 120),
            new Skill("Final Grade: F", 900, 1200, 300)); 
    }
}