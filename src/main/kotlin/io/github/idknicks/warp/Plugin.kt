package io.github.idknicks.warp

import io.github.idknicks.warp.command.WarpCmd
import io.github.idknicks.warp.command.WarpTabComplete
import org.bukkit.plugin.java.JavaPlugin

class Plugin : JavaPlugin() {

    companion object {
        var plugin: JavaPlugin? = null
    }



    override fun onEnable() {
        plugin = this
        init()
    }


    private fun init() {

        /** EVENT */

        /** COMMAND */
        getCommand("warp")?.setExecutor(WarpCmd())
        getCommand("warp")?.tabCompleter = WarpTabComplete()

        /** CONFIG */
        saveConfig()
    }
}