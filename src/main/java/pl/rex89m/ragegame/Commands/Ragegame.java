package pl.rex89m.ragegame.Commands;

import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
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
            Block b = ((Player) sender).getTargetBlock(null, 4);
            if (args.length == 2) {
                if (args[0].equals("armor")) {
                    if (!args[1].isEmpty()) {
                        plugin.listener.playerIntegerHashMap.put(sender.getName(), Integer.valueOf(args[1]));
                        sender.sendMessage("set to: " + args[1]);
                        plugin.listener.playerIntegerHashMap.get(sender.getName());
                    }
                }
                if (args[0].equals("add")) {
                    if (args[1].equals("k1")) {
                        Yml.addFangs(b.getLocation().add(0.5, 1, 0.5), Yml.FangsType.K1);
                    } else {
                        if (args[1].equals("k2")) {
                            Yml.addFangs(b.getLocation().add(0.5, 1, 0.5), Yml.FangsType.K2);
                        }
                    }
                }
            }else{
                if (args.length == 1) {
                    if (args[0].equals("remove")) {
                        Yml.removeFangs(b.getLocation().add(0.5, 1, 0.5));
                    }
                }
            }
        }
        return false;
    }
}
