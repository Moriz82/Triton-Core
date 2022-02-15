package Main.Util;

public class PlayerData {
	public int xp, killStreak, longestKillStreak, xpMultiplier;
	public LevelSet levelSet;

	public PlayerData(int xp, int killStreak, int longestKillStreak, int xpMultiplier, LevelSet levelSet) {
		this.xp = xp;
		this.killStreak = killStreak;
		this.longestKillStreak = longestKillStreak;
		this.xpMultiplier = xpMultiplier;
		this.levelSet = levelSet;
	}
}
