package pl.rex89m.ragegame;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class CheckPoint implements Listener {

    private final RageGame plugin;

    public CheckPoint(RageGame plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void Move(PlayerMoveEvent e) {
        if (e.getPlayer().getLocation().add(0, -1, 0).getBlock().getType() == Material.BLACK_CONCRETE) {
            Location location = Yml.getCheckPoint(e.getPlayer().getName());
            if (location.getX() - e.getPlayer().getLocation().getX() > 2 || location.getX() - e.getPlayer().getLocation().getX() < -2) {
                Yml.setCheckPoint(e.getPlayer().getName(), e.getPlayer().getLocation().add(0, 1.2, 0));
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1,0);
                e.getPlayer().setWalkSpeed((float) 0.2);
            }
            else if (location.getZ() - e.getPlayer().getLocation().getZ() > 2 || location.getZ() - e.getPlayer().getLocation().getZ() < -2) {
                Yml.setCheckPoint(e.getPlayer().getName(), e.getPlayer().getLocation().add(0, 1.2, 0));
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1,0);
                e.getPlayer().setWalkSpeed((float) 0.2);
            }
        }
    }
}
