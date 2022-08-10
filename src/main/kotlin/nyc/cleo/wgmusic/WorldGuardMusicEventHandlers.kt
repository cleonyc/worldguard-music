package nyc.cleo.wgmusic

import net.kyori.adventure.key.Key
import net.kyori.adventure.sound.Sound
import net.raidstone.wgevents.events.RegionEnteredEvent
import net.raidstone.wgevents.events.RegionLeftEvent
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class WorldGuardMusicEventHandlers : Listener {
    @EventHandler
    fun onRegionEntered(event: RegionEnteredEvent) {
        val player = Bukkit.getPlayer(event.uuid)!!
        val rSound = Manager.config.regionSounds.find { it.region == event.regionName } ?: return
        handle(player, rSound.onEnter)
    }
    @EventHandler
    fun onRegionLeft(event: RegionLeftEvent) {
        val player = Bukkit.getPlayer(event.uuid)!!
        val rSound = Manager.config.regionSounds.find { it.region == event.regionName } ?: return
        handle(player, rSound.onLeave)
    }
    private fun handle(player: Player, event: Event) {
        if (event.play == "") {
            return
        }
        if (event.send != "") {
            player.sendMessage(event.send)
        }

        player.stopAllSounds()
        player.playSound(Sound.sound(Key.key(event.play), Sound.Source.RECORD, 1f, 1f), Sound.Emitter.self())
    }
}