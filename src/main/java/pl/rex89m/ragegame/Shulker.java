package pl.rex89m.ragegame;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;

import java.util.HashMap;
import java.util.UUID;

public class Shulker implements Listener {

    private final RageGame plugin;

    public Shulker(RageGame plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void targe(EntityTargetEvent e){
        System.out.println(e.getEntity().getType());
        if (e.getEntity().getType()== EntityType.SHULKER){
            System.out.println(plugin.Dmg_Player.shulker.get(e.getEntity().getUniqueId()).get((Player)e.getTarget()));
            if (plugin.Dmg_Player.shulker.get(e.getEntity().getUniqueId()).containsKey((Player)e.getTarget())){
                if (plugin.Dmg_Player.shulker.get(e.getEntity().getUniqueId()).get((Player)e.getTarget())){
                    e.setCancelled(true);
                }
            }
        }
    }

    HashMap<UUID, Boolean> firt = new HashMap<>();

    public void Every(){
        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                Every();
                for (Entity i: Bukkit.getWorld("world").getEntities()){
                    if (i.getType()==EntityType.SHULKER){
                        org.bukkit.entity.Shulker shulker = (org.bukkit.entity.Shulker) i;
                        if (!plugin.Dmg_Player.shulker.containsKey(shulker.getUniqueId())){
                            plugin.Dmg_Player.shulker.put(shulker.getUniqueId(), new HashMap<>());
                        }
                        boolean rezult = true;
                        for (Entity i2: i.getNearbyEntities(15,15,15)){
                            if (i2 instanceof Player){
                                if (((Player) i2).getGameMode() != GameMode.CREATIVE) {
                                    if (plugin.Dmg_Player.shulker.get(i.getUniqueId()).containsKey(i2)) {
                                        if (!plugin.Dmg_Player.shulker.get(i.getUniqueId()).get(i2)) {
                                            rezult = false;
                                        }
                                    } else {
                                        rezult = false;
                                    }
                                }
                            }
                        }
                        if (!rezult){
                            shulker.setAI(true);
                            for (Entity i2: i.getNearbyEntities(15,15,15)) {
                                if (i2 instanceof Player) {
                                    if (((Player) i2).getGameMode() != GameMode.CREATIVE) {
                                        plugin.Dmg_Player.target.put(shulker.getUniqueId(), (Player) i2);
                                        shulker.setTarget((LivingEntity) i2);
                                        firt.put(shulker.getUniqueId(), true);

                                    }
                                }
                            }
                        }else{
                            if (firt.containsKey(shulker.getUniqueId())){
                                if (!firt.get(shulker.getUniqueId())){
                                    shulker.setAI(false);
                                }
                            }
                        }
                    }
                }
            }
        },20);
    }

}
