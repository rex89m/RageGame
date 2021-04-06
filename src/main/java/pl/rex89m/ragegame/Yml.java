package pl.rex89m.ragegame;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class Yml {

    private static File file = new File("plugins/Rage");
    private static File fileyml = new File("plugins/Rage/ArmorStand.yml");

    private static YamlConfiguration yml;

    public static ConfigurationSection section;
    public static ConfigurationSection sectionPlayer;
    public static ConfigurationSection sectionFangs;


    public static void load(){
        try {
            if (!file.exists()){
                file.mkdirs();
            }
            if (!fileyml.exists()){
                fileyml.createNewFile();
                section = yml.createSection("ArmorStand");
                sectionPlayer = yml.createSection("Player");
                sectionFangs = yml.createSection("Player");



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

        section = yml.getConfigurationSection("ArmorStand");
        sectionPlayer = yml.getConfigurationSection("Player");
        sectionFangs = yml.getConfigurationSection("Fangs");

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
        ArrayList list = getFangsString(fangsType);
        list.add(location);
        sectionFangs.set(fangsType.name(),list);
    }
    public static void removeFangs(Location location, FangsType fangsType){
        ArrayList list = getFangsString(fangsType);
        list.remove(location);
        sectionFangs.set(fangsType.name(),list);
    }
    public static ArrayList<Location> getFangsLocation(FangsType fangsType){
        ArrayList<Location> locations = new ArrayList<>();
        for (Object i : sectionFangs.getList(fangsType.name())){
            locations.add(Loc.load(i.toString()));
        }
        return locations;
    }
    public static ArrayList<String> getFangsString(FangsType fangsType){
        return (ArrayList<String>) sectionFangs.getList(fangsType.name());
    }


    public static void setCheckPoint(String key, Location value){
        sectionPlayer.set(key,Loc.save(value));
        save();
    }

    public static String get(String key){
        return section.getString(key);
    }

    public static Location getCheckPoint(String key){
        return Loc.load(sectionPlayer.getString(key));
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
