package Main.Util;

import java.io.Serializable;

public class PlayerDataBuilder implements Serializable {
	public int xp, killStreak, longestKillStreak, xpMultiplier;
	public String levelSet;

	public PlayerDataBuilder(int xp, int killStreak, int longestKillStreak, int xpMultiplier, String levelSet) {
		this.xp = xp;
		this.killStreak = killStreak;
		this.longestKillStreak = longestKillStreak;
		this.xpMultiplier = xpMultiplier;
		this.levelSet = levelSet;
	}
}
