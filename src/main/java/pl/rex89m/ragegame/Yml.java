package pl.rex89m.ragegame;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftShulker;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class Yml {

    private static File file = new File("plugins/Rage");
    private static File fileyml = new File("plugins/Rage/ArmorStand.yml");

    private static YamlConfiguration yml;

    public static ConfigurationSection section;
    public static ConfigurationSection sectionPlayer;
    public static ConfigurationSection sectionFangs;
    public static ConfigurationSection sectionShulker;


    public static void load(){
        try {
            if (!file.exists()){
                file.mkdirs();
            }
            if (!fileyml.exists()){
                fileyml.createNewFile();
                section = yml.createSection("ArmorStand");
                sectionPlayer = yml.createSection("Player");
                sectionFangs = yml.createSection("Fangs");
                sectionShulker = yml.getConfigurationSection("Shulker");
            }
            yml = YamlConfiguration.loadConfiguration(fileyml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!yml.isConfigurationSection("ArmorStand")){
            section = yml.createSection("ArmorStand");
        }
        if (!yml.isConfigurationSection("Player")){
            sectionPlayer = yml.createSection("Player");
        }
        if (!yml.isConfigurationSection("Fangs")){
            sectionPlayer = yml.createSection("Fangs");
        }
        if (!yml.isConfigurationSection("Shulker")){
            sectionShulker= yml.createSection("Shulker");
        }


        section = yml.getConfigurationSection("ArmorStand");
        sectionPlayer = yml.getConfigurationSection("Player");
        sectionFangs = yml.getConfigurationSection("Fangs");
        sectionShulker = yml.getConfigurationSection("Shulker");


        for (String i : section.getKeys(false)) {
            ArmorStands armorStands = new ArmorStands(Loc.load(get(i+".Location")), Integer.valueOf(get(i+".speed")), UUID.fromString(i));
            armorStands.setKillCount(Integer.parseInt(get(i+".killCount")));
        }
    }
    public static void set(String key, String value){
        section.set(key,value);
        save();
    }

    public static void addFangs(Location location, FangsType fangsType){
        ArrayList<String> lista = new ArrayList<>();
        lista.addAll(getFangsString(fangsType));
        lista.add(Loc.save(location));
        sectionFangs.set(fangsType.name(),lista);
        save();
    }
    public static void removeFangs(Location location, FangsType fangsType){
        ArrayList<String> list = getFangsString(fangsType);
        list.remove(Loc.save(location));
        sectionFangs.set(fangsType.name(),Loc.save(location));
        save();
    }

    public static ArrayList<Location> getFangsLocation(FangsType fangsType) {
        ArrayList<Location> lista = new ArrayList<>();
        for (String i : getFangsString(fangsType)){
            lista.add(Loc.load(i));
        }
        return lista;
    }

    public static ArrayList<String> getFangsString(FangsType fangsType){
        ArrayList<String> lista = new ArrayList<>();
        if (sectionFangs.isSet(fangsType.name())) {
            lista.addAll(sectionFangs.getStringList(fangsType.name()));
        }
        return lista;
    }

    public static void setCheckPoint(String key, Location value){
        sectionPlayer.set(key,Loc.save(value));
        save();
    }

    public static String get(String key){
        return section.getString(key);
    }

    public static Location getCheckPoint(String key){
        if (sectionPlayer.isSet(key)) {
            return Loc.load(sectionPlayer.getString(key));
        }
        return Bukkit.getWorld("world").getSpawnLocation();
    }

    public static ArrayList<String> getShulkersLocation(){
        ArrayList<String> lista = new ArrayList<>();
        lista.addAll(sectionShulker.getStringList("Location"));
        return lista;
    }
    public static ArrayList<CraftShulker> getShulkers(){
        ArrayList<CraftShulker> shulkers = new ArrayList<>();
        for (String i: sectionShulker.getStringList("Location")){
            Location location = Loc.load(i);
            ArrayList<Entity> nearbyEntities = (ArrayList<Entity>) location.getWorld().getNearbyEntities(location,0.5, 0.5, 0.5);
            if (nearbyEntities.size()>0) {
                if (nearbyEntities.get(0).getType() == EntityType.SHULKER) {
                    shulkers.add((CraftShulker) nearbyEntities.get(0));
                } else {
                    removeShulker(location);
                }
            }
            else {
                removeShulker(location);
            }
        }
        return shulkers;
    }

    public static void addShulker(Location location){
        ArrayList<String> lista = new ArrayList<>();
        lista.addAll(getShulkersLocation());
        lista.add(Loc.save(location));
        sectionShulker.set("Location",lista);
        save();
    }

    public static void removeShulker(Location location){
        ArrayList<String> list = getShulkersLocation();
        list.remove(Loc.save(location));
        sectionShulker.set("Location",Loc.save(location));
        save();
    }


    public static void save(){
        try {
            yml.save(fileyml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public enum FangsType{
        K1,
        K2;
    }

}
