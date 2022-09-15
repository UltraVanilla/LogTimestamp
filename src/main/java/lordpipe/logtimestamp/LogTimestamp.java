package lordpipe.logtimestamp;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import net.kyori.adventure.text.minimessage.MiniMessage;

public class LogTimestamp extends JavaPlugin {
    public MiniMessage mm = MiniMessage.miniMessage();

    private Logger log;
    @Override
    public void onEnable() {
        log = getLogger();

        log.info("Current server time: " + timeString());

        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleAtFixedRate(() -> {
            log.info("Current server time: " + timeString());
        }, 0, 1, TimeUnit.HOURS);

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
    }

    public String timeString() {
        ZoneId tz = ZoneId.systemDefault();
        ZonedDateTime local = ZonedDateTime.now(ZoneId.systemDefault());
        ZonedDateTime utc = local.withZoneSameInstant(ZoneId.of("UTC"));

        return utc.format(DateTimeFormatter.ISO_INSTANT)
            + " " + local.format(DateTimeFormatter.ISO_DATE_TIME)
            + " " + tz.getDisplayName(TextStyle.SHORT_STANDALONE, Locale.ROOT);
    }
}
