package at.imperial.spigot;

import at.imperial.Application;
import at.imperial.spigot.events.AreaListener;
import at.imperial.spigot.events.AreaManager;
import at.imperial.spigot.tasks.TrackTask;
import dev.alangomes.springspigot.SpringSpigotBootstrapper;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.util.Properties;

public class ShowcasePlugin extends JavaPlugin {

    private ClassLoader defaultClassLoader;
    private ConfigurableApplicationContext context;

    @SneakyThrows
    @Override
    public void onEnable() {
        defaultClassLoader = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(getClassLoader());
        context = SpringSpigotBootstrapper.initialize(this, Application.class);

        init();
    }

    private void init() throws IOException {
        Properties props = new Properties();
        props.load(getClassLoader().getResourceAsStream("application.properties"));

        SpringApplication application = new SpringApplication(Application.class);
        application.setDefaultProperties(props);

        AreaManager.INSTANCE.initialize();

        TrackTask tr = context.getBean(TrackTask.class);
        tr.updatePlayerLocations(10);

        Bukkit.getPluginManager().registerEvents(new AreaListener(), this);
    }

    @Override
    public void onDisable() {
        Thread.currentThread().setContextClassLoader(defaultClassLoader);
        context.close();
        context = null;
    }
}