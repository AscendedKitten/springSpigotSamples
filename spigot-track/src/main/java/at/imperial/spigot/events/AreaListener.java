package at.imperial.spigot.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class AreaListener implements Listener {

    @EventHandler
    public void enterArea(EnterAreaEvent e) {
        e.getPlayer().sendMessage("ENTERED " + e.getTerritory().getName());
    }

    @EventHandler
    public void leaveArea(LeaveAreaEvent e) {
        e.getPlayer().sendMessage("LEFT " + e.getTerritory().getName());
    }
}