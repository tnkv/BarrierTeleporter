package ru.tnkv.barrierteleporter.events;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    public final String COLOR_MAIN = "#D77BBA";
    public final String COLOR_WORLD = "#C5E1AC";
    public final String COLOR_WORLD_NETHER = "#F5A5AD";
    public final String COLOR_WORLD_THE_END = "#F5B5E9";

    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Location playerLocation = player.getLocation();
        Location spawnLocation = player.getWorld().getSpawnLocation();
        double worldBorder = player.getWorld().getWorldBorder().getSize() / 2;

        if (spawnLocation.getBlockX() + worldBorder >= Math.abs(playerLocation.getBlockX())
                && spawnLocation.getBlockZ() + worldBorder >= Math.abs(playerLocation.getBlockZ())) return;

        String PLAYER_WORLD_COLOR = getWorldColor(player.getWorld().getName());

        player.teleport(spawnLocation);
        player.sendMessage(Component.text("Во время входа на сервер вы оказались за границей мира, поэтому нам пришлось переместить вас на спавн. \n\nВаши старые координаты: ")
                .color(TextColor.fromHexString(COLOR_MAIN))
                .append(Component.text(String.format("%d %d %d", playerLocation.getBlockX(), playerLocation.getBlockY(), playerLocation.getBlockZ()))
                        .color(TextColor.fromHexString(PLAYER_WORLD_COLOR)))
                .append(Component.text(". При необходимости, отправьте эти координаты администратору.")
                        .color(TextColor.fromHexString(COLOR_MAIN))));
    }

    public String getWorldColor(String playerWorld) {
        return switch (playerWorld) {
            case "world" -> COLOR_WORLD;
            case "world_nether" -> COLOR_WORLD_NETHER;
            case "world_the_end" -> COLOR_WORLD_THE_END;
            default -> COLOR_MAIN;
        };
    }
}