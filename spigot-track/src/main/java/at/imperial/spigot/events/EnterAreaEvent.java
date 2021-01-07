package at.imperial.spigot.events;

import at.imperial.spring.domain.Territory;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class EnterAreaEvent extends Event {

    @Getter
    private final Player player;
    @Getter
    private final Territory territory;

    private static final HandlerList handlers = new HandlerList();

    public EnterAreaEvent(Player player, Territory territory) {
        this.player = player;
        this.territory = territory;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}