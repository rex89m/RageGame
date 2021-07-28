package pl.rex89m.ragegame;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftShulker;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import pl.rex89m.ragegame.Event.ShulkerTarget;


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
                    System.out.println(i.getLocation());
                    for (Entity i2 : i.getNearbyEntities(15,4,15)){
                        if (i2.getType() == EntityType.PLAYER){
                            plugin.getServer().getPluginManager().callEvent(new ShulkerTarget(i, (Player) i2));
                        }
                    }
                }
            }
        },20);
    }
}
