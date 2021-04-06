package pl.rex89m.ragegame;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CheckPoint {

    private final RageGame plugin;

    public CheckPoint(RageGame plugin) {
        this.plugin = plugin;
    }

    public void Every(){
        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                Every();
            }
        },5);
    }
}
