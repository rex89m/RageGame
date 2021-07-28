package pl.rex89m.ragegame;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

public class Fangs {

    private final RageGame plugin;

    public Fangs(RageGame plugin) {
        this.plugin = plugin;
    }

    public void Every(){
        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                Every();

                for (Location i : Yml.getFangsLocation(Yml.FangsType.K1)){
                    i.getWorld().spawnEntity(i, EntityType.EVOKER_FANGS);
                }
                Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                    @Override
                    public void run() {
                        for (Location i : Yml.getFangsLocation(Yml.FangsType.K2)){
                            i.getWorld().spawnEntity(i, EntityType.EVOKER_FANGS);
                        }
                    }
                },20);


            }
        },40);
    }

}
