package pl.rex89m.ragegame;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Dmg_Player implements Listener {

    private final RageGame plugin;

    public Dmg_Player(RageGame plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public static void DMG(EntityDamageByEntityEvent e){
        if (e.getEntity() instanceof Player) {
            System.out.println(e.getDamager().getType());
            if (e.getDamager().getType() == EntityType.ARROW) {
                e.getEntity().teleport(Yml.getCheckPoint(e.getEntity().getName()), PlayerTeleportEvent.TeleportCause.PLUGIN);
                e.getEntity().setFireTicks(0);
                e.setCancelled(true);
                e.getDamager().remove();
            } else if (e.getDamager().getType() == EntityType.EVOKER_FANGS) {
                e.getEntity().teleport(Yml.getCheckPoint(e.getEntity().getName()), PlayerTeleportEvent.TeleportCause.PLUGIN);
                e.getEntity().setFireTicks(0);
                e.setCancelled(true);
            } else if (e.getDamager().getType() == EntityType.SHULKER_BULLET) {
                e.getEntity().teleport(Yml.getCheckPoint(e.getEntity().getName()));
                e.getEntity().setFireTicks(0);
                e.setCancelled(true);
                e.getDamager().remove();
            }
        }
    }

    @EventHandler
    public static void DMG(EntityDamageEvent e) {
        if (e.getEntity() instanceof ShulkerBullet){
            e.setCancelled(true);
        }
        if (e.getEntity() instanceof Player) {
            if (e.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK) {
                e.getEntity().teleport(Yml.getCheckPoint(e.getEntity().getName()), PlayerTeleportEvent.TeleportCause.PLUGIN);
                e.getEntity().setFireTicks(1);
                e.setCancelled(true);
                Player p = (Player) e.getEntity();
                p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 1, 1, false, false));
            }
            if (e.getCause() == EntityDamageEvent.DamageCause.LAVA) {
                e.getEntity().teleport(Yml.getCheckPoint(e.getEntity().getName()), PlayerTeleportEvent.TeleportCause.PLUGIN);
                e.getEntity().setFireTicks(1);
                e.setCancelled(true);
                Player p = (Player) e.getEntity();
                p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 1, 1, false, false));
            }
            if (e.getCause() == EntityDamageEvent.DamageCause.FIRE) {
                e.getEntity().teleport(Yml.getCheckPoint(e.getEntity().getName()), PlayerTeleportEvent.TeleportCause.PLUGIN);
                e.getEntity().setFireTicks(1);
                e.setCancelled(true);
                Player p = (Player) e.getEntity();
                p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 1, 1, false, false));
            }
        }
    }

    @EventHandler
    public void onDeath(PlayerRespawnEvent e){
        e.setRespawnLocation(Yml.getCheckPoint(e.getPlayer().getName()));
    }
}
