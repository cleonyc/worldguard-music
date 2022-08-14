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

import org.bukkit.ChatColor
import org.bukkit.configuration.file.FileConfiguration

data class Config(var regionSounds: List<RegionSound>) {
    constructor(spigotConfig: FileConfiguration) : this(
        spigotConfig.getConfigurationSection("regions")?.getKeys(false)?.map {
            RegionSound(
                it,
                Event(
                    spigotConfig.getString("regions.${it}.onEnter.play")?: "",
                    ChatColor.translateAlternateColorCodes('&', spigotConfig.getString("regions.${it}.onEnter.send")?: "")
                ),
                Event(
                    spigotConfig.getString("regions.${it}.onLeave.play")?: "",
                    ChatColor.translateAlternateColorCodes('&', spigotConfig.getString("regions.${it}.onLeave.send")?: "")
                ),
            )

        }?.toList() ?: listOf()
    )
    fun reload(spigotConfig: FileConfiguration) {
        this.regionSounds = Config(spigotConfig).regionSounds
    }
}
data class RegionSound(
    val region: String,
    val onEnter: Event,
    val onLeave: Event,
)
data class Event(
    val play: String,
    val send: String
)