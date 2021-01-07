package at.imperial.spigot.scheduler;

import at.imperial.spigot.events.AreaManager;
import at.imperial.spigot.events.EnterAreaEvent;
import at.imperial.spigot.events.LeaveAreaEvent;
import at.imperial.spigot.utility.Utility;
import at.imperial.spring.service.LocationService;
import dev.alangomes.springspigot.util.scheduler.SchedulerService;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class TrackTask {

    private final LocationService locationService;
    private final SchedulerService schedulerService;

    @Autowired
    public TrackTask(LocationService locationService, SchedulerService schedulerService) {
        this.locationService = locationService;
        this.schedulerService = schedulerService;
    }

    public void updatePlayerLocations(long delay) {
        schedulerService.scheduleSyncRepeatingTask(() -> Bukkit.getOnlinePlayers().forEach((p) -> {
            Location loc = p.getLocation();

            int x = loc.getBlockX();
            int z = loc.getBlockZ();

            locationService.save(p.getName(), x, z);

            AreaManager.INSTANCE.getTerritories().forEach((t) -> {
                if (t != null) {
                    UUID pId = p.getUniqueId();
                    if (Utility.isWithin(x, z, t)) {
                        if (t.addMember(pId))
                            Bukkit.getPluginManager().callEvent(new EnterAreaEvent(p, t));
                    } else if (t.removeMember(pId))
                        Bukkit.getPluginManager().callEvent(new LeaveAreaEvent(p, t));
                }
            });
        }), delay, delay);
    }
}