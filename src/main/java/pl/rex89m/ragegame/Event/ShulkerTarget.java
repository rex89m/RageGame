package pl.rex89m.ragegame.Event;

import org.bukkit.craftbukkit.v1_17_R1.entity.CraftShulker;
import org.bukkit.entity.Player;
import org.bukkit.entity.Shulker;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ShulkerTarget extends Event{

    private static final HandlerList handlerList = new HandlerList();

    private Shulker shulker;
    private CraftShulker craftShulker;
    private Player player;

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList(){
        return handlerList;
    }

    public ShulkerTarget(Shulker shulker, Player player) {
        this.shulker=shulker;
        this.craftShulker=(CraftShulker) shulker;
        this.player=player;
    }

    public Shulker getShulker() {
        return shulker;
    }

    public CraftShulker getCraftShulker() {
        return craftShulker;
    }

    public Player getPlayer(){
        return player;
    }

}
