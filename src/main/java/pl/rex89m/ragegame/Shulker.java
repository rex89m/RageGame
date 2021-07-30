package pl.rex89m.ragegame;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftShulker;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import pl.rex89m.ragegame.Event.ShulkerTarget;

import java.util.ArrayList;


public class Shulker implements Listener {

    private final RageGame plugin;

    public Shulker(RageGame plugin) {
        this.plugin = plugin;
    }

    public void Every(){
        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                Every();
                for (CraftShulker i : Yml.getShulkers()){
                    for (Entity i2 : i.getNearbyEntities(15,4,15)) {
                        if (i2.getType() == EntityType.PLAYER) {
                            if (((Player) i2).getGameMode() == GameMode.SURVIVAL || ((Player) i2).getGameMode() == GameMode.ADVENTURE) {
                                plugin.getServer().getPluginManager().callEvent(new ShulkerTarget(i, (Player) i2));
                            }
                        }
                    }
                }
                for (Player i: Bukkit.getOnlinePlayers()){
                    if (plugin.listener.playerbulletlocation.containsKey(i)){
                        ArrayList<Location> removelist = new ArrayList<>();
                        for (Location i2: plugin.listener.playerbulletlocation.get(i).keySet()) {
                            if (i2.distance(i.getLocation()) > 15) {
                                plugin.listener.playerbulletlocation.get(i).get(i2).remove();
                                plugin.listener.target.get(plugin.listener.shulkerBulletShulkerHashMap.get(plugin.listener.playerbulletlocation.get(i).get(i2)).getUniqueId()).remove(i.getName());
                                removelist.add(i2);
                            }
                        }
                        for (Location i2 : removelist){
                            plugin.listener.playerbulletlocation.get(i).remove(i2);
                        }
                    }
                }
            }
        },20);
    }
}
