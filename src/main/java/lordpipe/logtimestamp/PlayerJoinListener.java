package lordpipe.logtimestamp;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    private LogTimestamp plugin;

    public PlayerJoinListener(LogTimestamp pl) {
        plugin = pl;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("logtimestamp.login")) {
            player.sendMessage(plugin.mm.deserialize("[LogTimestamp] Current server time: " + plugin.timeString()));
        }
    }
}
