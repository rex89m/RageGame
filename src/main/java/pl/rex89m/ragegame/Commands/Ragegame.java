package pl.rex89m.ragegame.Commands;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftShulkerBullet;
import org.bukkit.entity.*;
import pl.rex89m.ragegame.RageGame;
import pl.rex89m.ragegame.Yml;

public class Ragegame implements CommandExecutor {

    public final RageGame plugin;

    public Ragegame(RageGame plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp()) {
            Location location = new Location(sender.getServer().getWorld("world"), 120.5, 104, 167.5);
            CraftShulkerBullet bullet = (CraftShulkerBullet) location.getWorld().spawnEntity(location.add(0, 0, 0), EntityType.SHULKER_BULLET);
            bullet.setTarget((Entity) sender);
            bullet.setInvulnerable(true);
            if (args.length == 2) {
                if (args[0].equals("armor")) {
                    if (!args[1].isEmpty()) {
                        plugin.listener.playerIntegerHashMap.put(sender.getName(), Integer.valueOf(args[1]));
                        sender.sendMessage("set to: " + args[1]);
                        plugin.listener.playerIntegerHashMap.get(sender.getName());
                    }
                }
                Block b = ((Player) sender).getTargetBlock(null, 4);
                if (args[0].equals("add")) {
                    if (args[1].equals("k1")) {
                        Yml.addFangs(b.getLocation().add(0.5, 1, 0.5), Yml.FangsType.K1);
                    } else {
                        if (args[1].equals("k2")) {
                            Yml.addFangs(b.getLocation().add(0.5, 1, 0.5), Yml.FangsType.K2);
                        }
                    }
                } else {
                    if (args[0].equals("remove")) {
                        if (args[1].equals("k1")) {
                            Yml.removeFangs(b.getLocation().add(0.5, 1, 0.5), Yml.FangsType.K1);
                        } else {
                            if (args[1].equals("k2")) {
                                Yml.removeFangs(b.getLocation().add(0.5, 1, 0.5), Yml.FangsType.K2);
                            }
                        }
                    }


                }
            }
        }
        return false;
    }
}
