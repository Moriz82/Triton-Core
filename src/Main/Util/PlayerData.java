package Main.Util;

public class PlayerData {
	public int xp, kills, deaths, killStreak, longestKillStreak, xpMultiplier;
	public LevelSet levelSet;

	public PlayerData(int xp, int kills, int deaths, int killStreak, int longestKillStreak, int xpMultiplier, LevelSet levelSet) {
		this.xp = xp;
		this.kills = kills;
		this.deaths = deaths;
		this.killStreak = killStreak;
		this.longestKillStreak = longestKillStreak;
		this.xpMultiplier = xpMultiplier;
		this.levelSet = levelSet;
	}
}
