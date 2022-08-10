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

import net.axay.kspigot.extensions.pluginManager
import net.axay.kspigot.main.KSpigot

class WorldGuardMusic : KSpigot() {
    lateinit var config: Config
        private set
    override fun load() {
        INSTANCE = this
    }
    override fun startup() {
        this.saveDefaultConfig()
        config = Config(this.getConfig())
        pluginManager.registerEvents(WorldGuardMusicEventHandlers(), this)
    }
    override fun shutdown() { }
    companion object {
        lateinit var INSTANCE: WorldGuardMusic
            private set
    }
}
val Manager by lazy { WorldGuardMusic.INSTANCE }