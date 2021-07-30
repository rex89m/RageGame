package pl.rex89m.ragegame;

import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;

import java.util.ArrayList;


public class ArmorStandShot {

    private final RageGame plugin;

    public ArmorStandShot(RageGame plugin) {
        this.plugin = plugin;
    }
    int cool2 = 0;
    int cool3 = 0;
    int cool4 = 0;
    int cool5 = 0;
    int cool6 = 0;
    int cool7 = 0;
    int cool8 = 0;
    int cool9 = 0;
    int cool10 = 0;


    public void Every(){
        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                Every();
                cool2= cool2+1;
                cool3= cool3+1;
                cool4= cool4+1;
                cool5= cool5+1;
                cool6= cool6+1;
                cool7= cool7+1;
                cool8= cool8+1;
                cool9= cool9+1;
                cool10= cool10+1;
                for (ArmorStands i : ArmorStands.ArmorStandsList){
                    ArmorStand armorStand = (ArmorStand) Bukkit.getEntity(i.getUuid());
                    if (armorStand==null){
                    }else {
                        if (i.getSpeed() == 1) {
                            plugin.shot.ShotEffect((ArmorStand) Bukkit.getEntity(i.getUuid()));
                        }
                        if (cool2 == 2) {
                            if (i.getSpeed() == 2) {
                                plugin.shot.ShotEffect((ArmorStand) Bukkit.getEntity(i.getUuid()));
                            }
                        }
                        if (cool3 == 3) {
                            if (i.getSpeed() == 3) {
                                plugin.shot.ShotEffect((ArmorStand) Bukkit.getEntity(i.getUuid()));
                            }
                        }
                        if (cool4 == 4) {
                            if (i.getSpeed() == 4) {
                                plugin.shot.ShotEffect((ArmorStand) Bukkit.getEntity(i.getUuid()));
                            }
                        }
                        if (cool5 == 5) {
                            if (i.getSpeed() == 5) {
                                plugin.shot.ShotEffect((ArmorStand) Bukkit.getEntity(i.getUuid()));
                            }
                        }
                        if (cool6 == 6) {
                            if (i.getSpeed() == 6) {
                                plugin.shot.ShotEffect((ArmorStand) Bukkit.getEntity(i.getUuid()));
                            }
                        }
                        if (cool7 == 7) {
                            if (i.getSpeed() == 7) {
                                plugin.shot.ShotEffect((ArmorStand) Bukkit.getEntity(i.getUuid()));
                            }
                        }
                        if (cool8 == 8) {
                            if (i.getSpeed() == 8) {
                                plugin.shot.ShotEffect((ArmorStand) Bukkit.getEntity(i.getUuid()));
                            }
                        }
                        if (cool9 == 9) {
                            if (i.getSpeed() == 9) {
                                plugin.shot.ShotEffect((ArmorStand) Bukkit.getEntity(i.getUuid()));
                            }
                        }
                        if (cool10 == 10) {
                            if (i.getSpeed() == 10) {
                                plugin.shot.ShotEffect((ArmorStand) Bukkit.getEntity(i.getUuid()));
                            }
                        }
                    }
                }
                if (cool2>=2){
                    cool2=0;
                }
                if (cool3>=3){
                    cool3=0;
                }
                if (cool4>=4){
                    cool4=0;
                }
                if (cool5>=5){
                    cool5=0;
                }
                if (cool6>=6){
                    cool6=0;
                }
                if (cool7>=7){
                    cool7=0;
                }
                if (cool8>=8){
                    cool8=0;
                }
                if (cool9>=9){
                    cool9=0;
                }
                if (cool10>=10){
                    cool10=0;
                }
            }
        },20);
    }
}
