package pl.rex89m.ragegame;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class ArmorStands {

    private Location location;

    private Integer speed;

    private int killCount;

    private UUID uuid;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Location getLocation() {
        return location;
    }


    public void setLocation(Location location) {
        this.location = location;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public int getKillCount() {
        return killCount;
    }
    public void addkill(){
        this.killCount +=1;
       // Yml.set("ArmorStand."+Loc.save(location)+".killCount", String.valueOf(this.killCount));
    }

    public void setKillCount(int killCount) {
        this.killCount = killCount;
    }

    static HashMap<Location, ArmorStands> save = new HashMap<>();

    public ArmorStands(Location location, Integer speed, UUID uuid) {
        ArmorStand armorStand = (ArmorStand) Bukkit.getEntity(uuid);
        if (armorStand != null){
            armorStand.setSmall(true);
            this.location=location;
            this.speed= speed;
            this.killCount=0;
            this.uuid=uuid;
            if (!save.containsKey(location)){
                Yml.set(uuid+".speed", String.valueOf(speed));
                Yml.set(uuid+".killCount", String.valueOf(killCount));
                Yml.set(uuid+".Location", Loc.save(location));
                save.put(location, this);
                ArmorStandsList.add(this);
            }
        }else{
        }
    }

    public static ArmorStands get(Location key){
        return save.get(key);
    }

    public static ArrayList<ArmorStands> ArmorStandsList = new ArrayList<>();

}
