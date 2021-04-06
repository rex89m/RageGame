package pl.rex89m.ragegame;

import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;

import java.util.HashMap;
import java.util.UUID;

public class ArmorStandShot {

    private final RageGame plugin;

    public ArmorStandShot(RageGame plugin) {
        this.plugin = plugin;
    }
    HashMap<UUID,  Integer> cool = new HashMap<>();
    public void Every(){
        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                Every();
                for (ArmorStands i : ArmorStands.ArmorStandsList){
                    if (!cool.containsKey(i.getUuid())){
                        cool.put(i.getUuid(), 1);
                    }
                    cool.put(i.getUuid(), cool.get(i.getUuid())+1);
                    if (i.getSpeed()<=cool.get(i.getUuid())){
                        cool.put(i.getUuid(), 0);
                        plugin.shot.ShotEffect((ArmorStand) Bukkit.getEntity(i.getUuid()));
                    }
                }
            }
        },5);
    }

}
