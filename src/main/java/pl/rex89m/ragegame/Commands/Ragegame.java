package pl.rex89m.ragegame.Commands;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;
import pl.rex89m.ragegame.RageGame;

public class Ragegame implements CommandExecutor {

    public final RageGame plugin;

    public Ragegame(RageGame plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp()){

            Location location = new Location(sender.getServer().getWorld("world"), 100, 100, 100);
            for (Entity i : location.getWorld().getNearbyEntities(location, 1,1,1)){
                if (i.getType()== EntityType.SHULKER){
                    Player p = (Player)sender;
                    Shulker shulker = (Shulker) i;
                    p.sendMessage(String.valueOf(shulker.hasAI()));

                }
            }

            if (args.length==2){
                if (args[0].equals("armor")) {
                    if (!args[1].isEmpty()) {
                        plugin.listener.playerIntegerHashMap.put(sender.getName(), Integer.valueOf(args[1]));
                        sender.sendMessage("set to: "+args[1]);
                        plugin.listener.playerIntegerHashMap.get(sender.getName());
                        System.out.println(sender.getName());
                    }
                }
            }else{
                if (args.length==1){
                    Block b = ((Player)sender).getTargetBlock(null, 4);
                    sender.sendMessage(b.getType().toString());
                    if (args[0].equals("k1")){
                     //   Yml.addFangs();
                    }
                    else{
                        if (args[0].equals("k2")) {
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean getLookingAt(Player player, ArmorStand entity)
    {
        Location eye = player.getEyeLocation();
        Vector toEntity = entity.getEyeLocation().toVector().subtract(eye.toVector());
        double dot = toEntity.normalize().dot(eye.getDirection());

        return dot > 0.99D;
    }

}
