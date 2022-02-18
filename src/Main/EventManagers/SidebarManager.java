package Main.EventManagers;

import Main.Main;
import Main.Util.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class SidebarManager {

    private final Main main;

    public SidebarManager(Main main) {
        this.main = main;
    }

    public static void updateSidebar(Player player, Main main) {

        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective obj = board.registerNewObjective("TritonSideBar", "dummy");

        obj.setDisplayName(ChatColor.RED.toString() + ChatColor.BOLD + "Triton MC");

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        PlayerData playerData = main.playerData.get(player);

        obj.getScore(ChatColor.AQUA.toString() + ChatColor.BOLD + player.getName()).setScore(10);
        obj.getScore(ChatColor.RED + "XP : " + ChatColor.BLUE + playerData.xp).setScore(9);
        obj.getScore(ChatColor.RED + "Kills : " + playerData.kills).setScore(8);
        obj.getScore(ChatColor.RED + "Deaths : " + playerData.deaths).setScore(7);
        if (playerData.kills == 0 || playerData.deaths == 0){
            if (playerData.deaths == 0)
                obj.getScore(ChatColor.RED + "K/D Ratio : " + playerData.kills).setScore(6);
            if (playerData.kills == 0)
                obj.getScore(ChatColor.RED + "K/D Ratio : " + -1 * playerData.kills).setScore(6);
            if (playerData.kills == 0 && playerData.deaths == 0)
                obj.getScore(ChatColor.RED + "K/D Ratio : " + '0').setScore(6);
        }else{
            obj.getScore(ChatColor.RED + "K/D Ratio : " + String.format("%.2f", (double)playerData.kills / playerData.deaths)).setScore(6);
        }
        obj.getScore(ChatColor.RED + "Level : " + ChatColor.BLUE + playerData.levelSet.name).setScore(5);
        obj.getScore(ChatColor.RED + "Longest Kill Streak : " + playerData.longestKillStreak).setScore(4);
        obj.getScore(ChatColor.RED + "Current Kill Streak : " + playerData.killStreak).setScore(3);
        obj.getScore(ChatColor.RED + "Current XP Multiplier : " + playerData.xpMultiplier).setScore(2);
        obj.getScore(ChatColor.RED + "Current Bounty : " + getBounty(playerData.killStreak)).setScore(1);

        player.setScoreboard(board);

    }

    public static int getBounty(int val) {
        int bounty = 0;
        if (val >= 5){
            if (val >= 10) {
                if (val >= 25){
                    if (val >= 60){
                        bounty = 115;
                    }else{ bounty = 50; }
                }else{ bounty = 20; }
            } else{ bounty = 10; }
        }
        return bounty;
    }

}