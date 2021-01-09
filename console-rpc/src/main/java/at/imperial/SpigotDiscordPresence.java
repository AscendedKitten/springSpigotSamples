package at.imperial;


import at.imperial.pojos.LocationData;
import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class SpigotDiscordPresence {

    static final WebClient client = WebClient.create("http://localhost:8080");

    public static void main(String[] args) {
        SpringApplication.run(SpigotDiscordPresence.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(DiscordRPC::discordShutdown));
        initDiscord();

        DiscordRichPresence.Builder presence = new DiscordRichPresence.Builder("");

        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                DiscordRPC.discordRunCallbacks();
                try {

                    client.get().uri("/players/{name}", "cathKitten")
                            .retrieve().bodyToMono(LocationData.class)
                            .subscribe(p -> presence.setDetails(String.format("%d | %d", p.getLocX(), p.getLocZ())));

                    DiscordRPC.discordUpdatePresence(presence.build());
                    Thread.sleep(1000);

                } catch (InterruptedException ignored) {
                }
            }
        }).start();

    }

    private static void initDiscord() {
        DiscordEventHandlers handlers = new DiscordEventHandlers.Builder()
                .setReadyEventHandler((user) -> System.out.printf("Found user %s\n", String.join("#", user.username, user.discriminator))).build();

        DiscordRPC.discordInitialize("796721248171851807", handlers, false);
        DiscordRPC.discordRegister("796721248171851807", "");
    }

}
