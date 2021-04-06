package pl.rex89m.ragegame;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class ArmorStands {

    private Location location;

    private Double speed;

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

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
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

    public ArmorStands(Location location, Integer speed, UUID uuid){
        this.location=location;
        this.speed= Double.valueOf(speed);
        this.killCount=0;
        this.uuid=uuid;
        if (!save.containsKey(location)){
            Yml.set(uuid+".speed", String.valueOf(speed));
            Yml.set(uuid+".killCount", String.valueOf(killCount));
            Yml.set(uuid+".Location", Loc.save(location));
            save.put(location, this);
            ArmorStandsList.add(this);
        }
    }

    public static ArmorStands get(Location key){
        return save.get(key);
    }

    public static ArrayList<ArmorStands> ArmorStandsList = new ArrayList<>();

}
