package org.kitteh.tnt.abuser.plus.plus;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Plus extends JavaPlugin implements Listener {

    private int tntcount = 0;

    @Override
    public void onDisable() {
        this.getLogger().info("v" + this.getDescription().getVersion() + " disabled.");
    }

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
        this.getLogger().info("v" + this.getDescription().getVersion() + " enabled.");
    }

    @EventHandler
    public void move(PlayerMoveEvent event) {
        if (event.getPlayer().getName().equalsIgnoreCase("tntbass")) {
            if (tntcount == 0) {
                event.getPlayer().getWorld().createExplosion(event.getPlayer().getLocation(), 2f, true);
            }
            tntcount++;
            if (tntcount > 10) {
                tntcount = 0;
            }
        }
    }

    @EventHandler
    public void ouch(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player && ((Player) event.getEntity()).getName().equalsIgnoreCase("tntbass")) {
            event.setCancelled(true);
        }
    }

}