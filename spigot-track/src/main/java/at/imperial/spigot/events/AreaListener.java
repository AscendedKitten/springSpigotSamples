package at.imperial.spigot.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class AreaListener implements Listener {

    @EventHandler
    public void enterArea(EnterAreaEvent e) {
        e.getPlayer().sendTitle(ChatColor.GREEN + e.getTerritory().getName(), "Welcome!", 10, 20, 10);
    }

    @EventHandler
    public void leaveArea(LeaveAreaEvent e) {
        e.getPlayer().sendTitle(ChatColor.RED + e.getTerritory().getName(), "Goodbye!", 10, 20, 10);
    }
}