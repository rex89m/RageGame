package pl.rex89m.ragegame;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.Main;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftShulkerBullet;
import org.bukkit.entity.*;
import org.bukkit.entity.Shulker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import pl.rex89m.ragegame.Event.ShulkerTarget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

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
                            new ArmorStands(e.getEntity().getLocation(),  0, e.getEntity().getUniqueId());
                        } else {
                            new ArmorStands(e.getEntity().getLocation(), playerIntegerHashMap.get(p.getName()), e.getEntity().getUniqueId());
                        }
                    }
                }
            }
        }
    }


    @EventHandler
    public void join(PlayerJoinEvent e){
        e.getPlayer().setWalkSpeed((float) 0.2);
        playerbulletlocation.put(e.getPlayer(), new HashMap<>());

    }

    @EventHandler
    public void weather(WeatherChangeEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void Move(PlayerMoveEvent e){
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

    HashMap<UUID, ArrayList<String>> target = new HashMap<>();
    HashMap<ShulkerBullet, Shulker> shulkerBulletShulkerHashMap = new HashMap<>();
    HashMap<ShulkerBullet, Player> shulkerBulletShulkePlayerrHashMap = new HashMap<>();
    HashMap<Player, HashMap<Location, ShulkerBullet>> playerbulletlocation = new HashMap<>();

    @EventHandler
    public void ShulkerTarget(ShulkerTarget e){
        if (target.containsKey(e.getShulker().getUniqueId())){
            ArrayList<String> players = target.get(e.getShulker().getUniqueId());
            if (!players.contains(e.getPlayer().getName())){
                players.add(e.getPlayer().getName());
                target.put(e.getShulker().getUniqueId(), players);
                CraftShulkerBullet shulkerBullet = (CraftShulkerBullet) e.getShulker().getLocation().getWorld().spawnEntity(e.getShulker().getLocation().add(0,1,0), EntityType.SHULKER_BULLET);
                shulkerBullet.setInvulnerable(true);
                shulkerBullet.setTarget(e.getPlayer());
                shulkerBulletShulkerHashMap.put(shulkerBullet, e.getShulker());
                shulkerBulletShulkePlayerrHashMap.put(shulkerBullet, e.getPlayer());
                playerbulletlocation.get(e.getPlayer()).put(e.getShulker().getLocation(), shulkerBullet);
            }
        }else{
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add(e.getPlayer().getName());
            target.put(e.getShulker().getUniqueId(), arrayList);
            CraftShulkerBullet shulkerBullet = (CraftShulkerBullet) e.getShulker().getLocation().getWorld().spawnEntity(e.getShulker().getLocation().add(0,1,0), EntityType.SHULKER_BULLET);
            shulkerBullet.setInvulnerable(true);
            shulkerBullet.setTarget(e.getPlayer());
            shulkerBulletShulkerHashMap.put(shulkerBullet, e.getShulker());
            shulkerBulletShulkePlayerrHashMap.put(shulkerBullet, e.getPlayer());
            playerbulletlocation.get(e.getPlayer()).put(e.getShulker().getLocation(), shulkerBullet);
        }
    }

    @EventHandler
    public void onResp(EntitySpawnEvent e){
        if (e.getEntity().getType()==EntityType.SHULKER){
            Yml.addShulker(e.getLocation());
            Shulker shulker = (Shulker) e.getEntity();
            shulker.setAI(false);
            shulker.setInvulnerable(true);
        }
    }

    @EventHandler
    public void bulletshot(ProjectileHitEvent e){
        if (e.getEntity().getType()==EntityType.SHULKER_BULLET){
            if (shulkerBulletShulkerHashMap.containsKey(e.getEntity())){
                target.get(shulkerBulletShulkerHashMap.get(e.getEntity()).getUniqueId()).remove(shulkerBulletShulkePlayerrHashMap.get(e.getEntity()).getName());
                playerbulletlocation.get(shulkerBulletShulkePlayerrHashMap.get(e.getEntity())).remove(shulkerBulletShulkerHashMap.get(e.getEntity()).getLocation());
                playerbulletlocation.get(Bukkit.getPlayer(shulkerBulletShulkePlayerrHashMap.get(e.getEntity()).getName())).remove(shulkerBulletShulkerHashMap.get(e.getEntity()).getLocation());
                e.getEntity().teleport(Yml.getCheckPoint(e.getEntity().getName()), PlayerTeleportEvent.TeleportCause.PLUGIN);
                e.getEntity().setFireTicks(0);
                e.setCancelled(true);
            }
        }
    }
}
