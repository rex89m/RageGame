package pl.rex89m.ragegame;

import org.bukkit.GameMode;
import org.bukkit.entity.*;
import org.bukkit.entity.Shulker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Dmg_Player implements Listener {

    private final RageGame plugin;

    public Dmg_Player(RageGame plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public static void DMG(EntityDamageByEntityEvent e){
        if (e.getEntity() instanceof Player) {
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
        if (plugin.Dmg_Player.ShulkerBullet.containsKey(e.getPlayer().getUniqueId())) {
            for (Shulker i : plugin.Dmg_Player.ShulkerBullet.get(e.getPlayer().getUniqueId())) {
                plugin.Dmg_Player.shulker.get(i.getUniqueId()).put(e.getPlayer(), false);
            }
            plugin.Dmg_Player.ShulkerBullet.get(e.getPlayer().getUniqueId()).clear();
        }
        if (plugin.Dmg_Player.Bullet.containsKey(e.getPlayer().getUniqueId())) {
            for (Projectile i: plugin.Dmg_Player.Bullet.get(e.getPlayer().getUniqueId())){
                i.remove();
            }
            plugin.Dmg_Player.Bullet.get(e.getPlayer().getUniqueId()).clear();
        }
    }

    public HashMap<UUID, Player> target = new HashMap<>();

    public HashMap<UUID, ArrayList<Shulker>> ShulkerBullet = new HashMap<>();

    public HashMap<UUID, ArrayList<Projectile>> Bullet = new HashMap<>();

    public HashMap<UUID, ArrayList<Projectile>> BullettoShulker = new HashMap<>();

    public HashMap<UUID, HashMap<Player, Boolean>> shulker = new HashMap<>();


    @EventHandler
    public void shulkerShot(ProjectileHitEvent e) {
        if (e.getEntityType()==EntityType.SHULKER_BULLET){
            e.
        }
    }

    @EventHandler
    public void shulkerShot(ProjectileLaunchEvent e){
        Shulker shulkerEntity = (Shulker) e.getEntity().getShooter();

        if (Bullet.containsKey(target.get(shulkerEntity.getUniqueId()))){
            ArrayList<Projectile> lista = Bullet.get(target.get(shulkerEntity.getUniqueId()));
            lista.add(e.getEntity());
            Bullet.put(target.get(shulkerEntity.getUniqueId()).getUniqueId(), lista);
        }else{
            ArrayList<Projectile> lista = new ArrayList<>();
            lista.add(e.getEntity());
            Bullet.put(target.get(shulkerEntity.getUniqueId()).getUniqueId(), lista);
        }

        if (ShulkerBullet.containsKey(target.get(shulkerEntity.getUniqueId()))){
            ArrayList<Shulker> lista = ShulkerBullet.get(target.get(shulkerEntity.getUniqueId()));
            lista.add(shulkerEntity);
            ShulkerBullet.put(target.get(shulkerEntity.getUniqueId()).getUniqueId(), lista);
        }else{
            ArrayList<Shulker> lista = new ArrayList<>();
            lista.add(shulkerEntity);
            ShulkerBullet.put(target.get(shulkerEntity.getUniqueId()).getUniqueId(), lista);
        }

        if (plugin.shulker.firt.containsKey(shulkerEntity.getUniqueId())){
            if (plugin.shulker.firt.get(shulkerEntity.getUniqueId())){
                shulker.get(shulkerEntity.getUniqueId()).put((target.get(shulkerEntity.getUniqueId())).getPlayer(), true);
                plugin.shulker.firt.put(shulkerEntity.getUniqueId(), false);
            }
        }
        if (e.getEntity().getType()==EntityType.SHULKER_BULLET){
            for (Entity i: e.getEntity().getNearbyEntities(15,15,15)){
                if (i instanceof Player) {
                    if (((Player) i).getGameMode() != GameMode.CREATIVE) {
                        if (shulker.get(shulkerEntity.getUniqueId()).containsKey(target.get(shulkerEntity.getUniqueId()))) {
                            if (!shulker.get(shulkerEntity.getUniqueId()).get(target.get(shulkerEntity.getUniqueId()))) {
                                shulker.get(shulkerEntity.getUniqueId()).put(((Player) i).getPlayer(), true);
                                target.put(shulkerEntity.getUniqueId(), (Player) i);
                                shulkerEntity.setTarget((LivingEntity) i);
                            }
                        } else {
                            shulker.get(shulkerEntity.getUniqueId()).put(((Player) i).getPlayer(), true);
                            target.put(shulkerEntity.getUniqueId(), (Player) i);
                            shulkerEntity.setTarget((LivingEntity) i);
                        }
                    }
                }
            }
        }
    }
}
