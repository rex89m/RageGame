package pl.rex89m.ragegame;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Loc {

    public static Location load(String value){
        String[] lockST = value.split("#");
        return new Location(Bukkit.getWorld(lockST[0]),Double.parseDouble(lockST[1]) ,Double.parseDouble(lockST[2]) ,Double.parseDouble(lockST[3]));
    }
    public static String save(Location value){
        return value.getWorld().getName()+"#"+value.getX()+"#"+value.getY()+"#"+value.getZ();

    }

}
