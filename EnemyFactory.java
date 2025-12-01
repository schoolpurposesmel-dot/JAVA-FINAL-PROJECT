public class EnemyFactory {
    public static Enemy createLostNode() { return new Enemy("Lost Node", 350, 150, new Skill("Pointer Jab", 40, 50, 20), new Skill("Memory Leak", 60, 70, 30), new Skill("Segfault", 100, 120, 90)); }
    public static Enemy createNullBug() { return new Enemy("Null Bug", 400, 150, new Skill("Null Ping", 45, 55, 20), new Skill("Code Lag", 60, 80, 30), new Skill("System Freeze", 110, 130, 70)); }
    public static Enemy createSyntaxGremlin() { return new Enemy("Syntax Gremlin", 450, 150, new Skill("Missing Semicolon", 30, 40, 40), new Skill("Bracket Jab", 70, 90, 25), new Skill("Compiler Panic", 130, 150, 60)); }
    public static Enemy createPacketGhoul() { return new Enemy("Packet Ghoul", 380, 150, new Skill("Ping Strike", 50, 60, 20), new Skill("Packet Loss", 70, 80, 25), new Skill("Network Timeout", 120, 140, 80)); }
    public static Enemy createPingWraith() { return new Enemy("Ping Wraith", 500, 200, new Skill("Echo Slam", 80, 100, 35), new Skill("ICMP Scream", 90, 110, 40), new Skill("Echo Flood", 150, 180, 120)); }
    public static Enemy createMemoryLeech() { return new Enemy("Memory Leech", 550, 200, new Skill("Stack Overflow", 90, 110, 40), new Skill("Memory Drain", 80, 100, 50), new Skill("Blue Screen", 160, 200, 90)); }
    public static Enemy createObjectGhost() { return new Enemy("Object Ghost", 700, 250, new Skill("Instantiate", 50, 60, 50), new Skill("Reference Bite", 100, 120, 40), new Skill("Inheritance Storm", 180, 220, 120)); }
    public static Enemy createStackPhantom() { return new Enemy("Stack Phantom", 420, 150, new Skill("Push Attack", 70, 80, 30), new Skill("Pop Smash", 100, 120, 50), new Skill("Overflow", 140, 160, 100)); }
    public static Enemy createRecursionShade() { return new Enemy("Recursion Shade", 600, 200, new Skill("Loop Strike", 80, 100, 40), new Skill("Echo Attack", 70, 90, 35), new Skill("Infinite Loop", 180, 210, 80)); }

    // BOSSES
    public static Enemy createSirArbs() { return new Enemy("Sir Arbs", 2500, 800, new Skill("Logic Gate Crash", 150, 180, 50), new Skill("Binary Barrage", 180, 220, 45), new Skill("System Override", 350, 400, 110)); }
    public static Enemy createMaamTulin() { return new Enemy("Ma'am Tulin", 2200, 700, new Skill("Data Stream", 140, 170, 40), new Skill("Firewall Block", 100, 120, 35), new Skill("Network Blackout", 300, 350, 100)); }
    
    // FINAL BOSS - HARD
    public static Enemy createSirKhai() { 
        return new Enemy("Sir Khai", 5000, 2000,
            new Skill("Terror Quiz", 250, 300, 60),
            new Skill("Deadline Strike", 300, 400, 80),
            new Skill("Final Grade: F", 600, 800, 200)); 
    }
}