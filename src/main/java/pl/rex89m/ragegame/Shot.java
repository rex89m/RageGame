package pl.rex89m.ragegame;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;

import java.util.ArrayList;

public class Shot {

    private final RageGame plugin;

    public Shot(RageGame plugin) {
        this.plugin = plugin;
    }

    ArrayList<Arrow> arrows = new ArrayList<>();

    public void ShotEffect(ArmorStand entity){
        if (!entity.isSmall()) {
            entity.setSmall(true);
        }
        Location location = entity.getLocation();
        Arrow arrow = entity.getWorld().spawnArrow(location.add(0,1.3,0), entity.getLocation().getDirection(), 3,0);
        arrow.setGravity(false);
        arrows.add(arrow);
    }

    public void clear_arrow(){
        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                clear_arrow();
                for (Arrow i : arrows){
                    if (i.isInBlock()){
                        i.remove();
                    }else{
                        if (i.getTicksLived()>=800) {
                            i.remove();
                        }
                    }
                }
            }
        },40);
    }

}
