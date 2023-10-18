package ru.tnkv.barrierteleporter;

import org.bukkit.plugin.java.JavaPlugin;
import ru.tnkv.barrierteleporter.events.PlayerJoin;

import java.util.logging.Level;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getLogger().log(Level.INFO, "Плагин включён.");
    }

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "Плагин выключен.");
    }
}
