/*
    Allows playing sounds when entering worldguard regions
    Copyright (C) 2022 cleonyc

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/
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