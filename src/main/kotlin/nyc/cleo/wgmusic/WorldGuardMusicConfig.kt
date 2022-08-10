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