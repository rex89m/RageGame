package pl.rex89m.ragegame;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import pl.rex89m.ragegame.Commands.Ragegame;

import java.util.HashMap;

public final class RageGame extends JavaPlugin {

    final ArmorStandShot armorStandShot;
    public final Listener listener;
    final Ragegame ragegame;
    final Shot shot;
    final CheckPoint checkPoint;
    public final Dmg_Player Dmg_Player;
    final Fangs fangs;
    final Shulker shulker;

    public RageGame(){
        this.armorStandShot = new ArmorStandShot(this);
        this.listener = new Listener(this);
        this.ragegame = new Ragegame(this);
        this.shot = new Shot(this);
        this.checkPoint = new CheckPoint(this);
        this.Dmg_Player = new Dmg_Player(this);
        this.fangs = new Fangs(this);
        this.shulker = new Shulker(this);
    }

    @Override
    public void onEnable() {
        Yml.load();
        armorStandShot.Every();
        shot.clear_arrow();
        fangs.Every();
        shulker.Every();
        getCommand("Ragegame").setExecutor(new Ragegame(this));
        Bukkit.getPluginManager().registerEvents(listener, this);
        Bukkit.getPluginManager().registerEvents(shulker, this);
        Bukkit.getPluginManager().registerEvents(Dmg_Player, this);
        Bukkit.getPluginManager().registerEvents(checkPoint, this);
        for (Player i: Bukkit.getOnlinePlayers()){
            listener.playerbulletlocation.put(i, new HashMap<>());
        }
    }
    @Override
    public void onDisable() {
    }
}
