package cn.lemonnetwork.lemonbedwars.Utils;

import com.google.common.collect.Lists;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ScoreboardManager {

    private Plugin plugin;
    private String[] board;

    public ScoreboardManager(Plugin plugin, String title, String[] content) {
        this.plugin = plugin;

        List<String> b = Lists.newArrayList();
        b.add(title);
        Collections.addAll(b, content);
        board = b.toArray(new String[0]);
    }

    private String[] cut(String[] content) {
        String[] elements = Arrays.copyOf(content, board.length);

        if (elements[0] == null) {
            elements[0] = "Unnamed Board";
        }

        if (elements[0].length() > 32) {
            elements[0] = elements[0].substring(0, 32);
        }

        for (int i = 1; i < elements.length; i++) {
            if (elements[i] != null) {
                if (elements[i].length() > 40) {
                    elements[i] = elements[i].substring(0, 40);
                }
            }
        }

        return elements;
    }

    public void display(Player player) {
        List<String> boardList = Lists.newArrayList();
        boardList.addAll(Arrays.asList(board));
        board = boardList.toArray(new String[0]);

        board = cut(board);

        try {
            if (player.getScoreboard() == null || player.getScoreboard() == plugin.getServer().getScoreboardManager().getMainScoreboard() || player.getScoreboard().getObjectives().size() != 1) {
                player.setScoreboard(player.getServer().getScoreboardManager().getNewScoreboard());
            }

            if (player.getScoreboard().getObjective(player.getUniqueId().toString().substring(0, 16)) == null) {
                player.getScoreboard().registerNewObjective(player.getUniqueId().toString().substring(0, 16), "dummy");
                player.getScoreboard().getObjective(player.getUniqueId().toString().substring(0, 16)).setDisplaySlot(DisplaySlot.SIDEBAR);
            }


            player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(board[0]);

            for (int i = 1; i < board.length; i++) {
                if (board[i] != null) {
                    if (player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(board[i]).getScore() != board.length - i) {
                        player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(board[i]).setScore(board.length - i);
                        for (String string : player.getScoreboard().getEntries()) {
                            if (player.getScoreboard().getObjective(player.getUniqueId().toString().substring(0, 16)).getScore(string).getScore() == board.length - i) {
                                if (!string.equals(board[i])) {
                                    player.getScoreboard().resetScores(string);
                                }
                            }
                        }
                    }
                }
            }

            for (String entry : player.getScoreboard().getEntries()) {
                boolean toErase = true;
                for (String element : board) {
                    if (element != null && element.equals(entry) && player.getScoreboard().getObjective(player.getUniqueId().toString().substring(0, 16))
                            .getScore(entry).getScore() == board.length - Arrays.asList(board).indexOf(element)) {
                        toErase = false;
                        break;
                    }
                }

                if (toErase) {
                    player.getScoreboard().resetScores(entry);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
