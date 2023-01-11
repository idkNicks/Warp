package io.github.idknicks.warp

import com.github.nicklib.data.Config
import io.github.idknicks.warp.command.SetSpawnCmd
import io.github.idknicks.warp.command.SpawnCmd
import io.github.idknicks.warp.command.WarpCmd
import io.github.idknicks.warp.command.tabcomplete.SpawnTabComplete
import io.github.idknicks.warp.command.tabcomplete.WarpTabComplete
import org.bukkit.plugin.java.JavaPlugin

class Plugin : JavaPlugin() {

    companion object {
        var instance: Plugin? = null
        var config: Config? = null
        var prefix: String? = null
    }

    override fun onEnable() {
        init()
    }

    /** 모듈을 관리합니다. */
    private fun init() {

        /** COMMAND */
        getCommand("warp")?.setExecutor(WarpCmd())
        getCommand("warp")?.tabCompleter = WarpTabComplete()
        getCommand("spawn")?.setExecutor(SpawnCmd())
        getCommand("spawn")?.tabCompleter = SpawnTabComplete()
        getCommand("setspawn")?.setExecutor(SetSpawnCmd())
        getCommand("setspawn")?.tabCompleter = SpawnTabComplete()

        /** CONFIG */
        instance = this
        Plugin.config = Config("config", this)
        Plugin.config!!.loadDefaultConfig()
        prefix = Config("config", this).config.getString("prefix")
    }
}