package pl.rex89m.ragegame;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.entity.Shulker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;

public class Listener implements org.bukkit.event.Listener {

    private final RageGame plugin;

    public Listener(RageGame plugin) {
        this.plugin = plugin;
    }

    public HashMap<String , Integer> playerIntegerHashMap = new HashMap<>();

    @EventHandler
    public void ArmorStand(EntityDamageByEntityEvent e){
        if (e.getEntity().getType()== EntityType.ARMOR_STAND){
            if (!e.getDamager().isOp()){
                e.setCancelled(true);
            }
            else{
                Player p = (Player) e.getDamager();
                if (p.getInventory().getItemInMainHand().getType()== Material.STICK){
                    if (playerIntegerHashMap.get(p.getName())!=null) {
                        e.setCancelled(true);
                        if (playerIntegerHashMap.get(p.getName()) == 0) {
                            new ArmorStands(e.getEntity().getLocation(),  3, e.getEntity().getUniqueId());
                        } else {
                            new ArmorStands(e.getEntity().getLocation(), playerIntegerHashMap.get(p.getName()) * 4, e.getEntity().getUniqueId());
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void join(PlayerJoinEvent e){
        e.getPlayer().setWalkSpeed((float) 0.2);
    }

    @EventHandler
    public void teleport(PlayerTeleportEvent e){
        if (e.getCause()== PlayerTeleportEvent.TeleportCause.PLUGIN){
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
    }

    @EventHandler
    public void weather(WeatherChangeEvent e){
        e.setCancelled(true);
    }


    @EventHandler
    public void Move(PlayerMoveEvent e){
        if (e.getPlayer().getLocation().add(0,-1,0).getBlock().getType()== Material.CONCRETE) {
            Location location = Yml.getCheckPoint(e.getPlayer().getName());
            if (location.getX()-e.getPlayer().getLocation().getX()>2 || location.getX()-e.getPlayer().getLocation().getX()<-2){
                Yml.setCheckPoint(e.getPlayer().getName(), e.getPlayer().getLocation().add(0, 1.2, 0));
                e.getPlayer().sendMessage("checkpoint");
            }
            if (location.getZ()-e.getPlayer().getLocation().getZ()>2 || location.getZ()-e.getPlayer().getLocation().getZ()<-2){
                Yml.setCheckPoint(e.getPlayer().getName(), e.getPlayer().getLocation().add(0, 1.2, 0));
                e.getPlayer().sendMessage("checkpoint");
            }
        }

        if (e.getPlayer().getLocation().add(0,-2,0).getBlock().getType()== Material.STONE) {
            if (e.getPlayer().getGameMode() == GameMode.ADVENTURE || e.getPlayer().getGameMode() == GameMode.SURVIVAL) {
                e.getPlayer().getLocation().getWorld().spawnEntity(e.getPlayer().getLocation(), EntityType.EVOKER_FANGS);
                Location location1 = e.getPlayer().getLocation();
                location1.setPitch(90);
                e.getPlayer().teleport(location1);
                e.getPlayer().setWalkSpeed(0);
                e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1, 250, false, false));
                Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                    @Override
                    public void run() {
                        e.getPlayer().teleport(Yml.getCheckPoint(e.getPlayer().getName()), PlayerTeleportEvent.TeleportCause.PLUGIN);
                        e.getPlayer().setWalkSpeed((float) 0.2);
                    }
                }, 10);
            }
        }
    }
}
