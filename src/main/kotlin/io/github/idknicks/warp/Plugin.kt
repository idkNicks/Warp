package io.github.idknicks.warp

import io.github.idknicks.warp.command.WarpCmd
import org.bukkit.plugin.java.JavaPlugin

class Plugin : JavaPlugin() {


    override fun onEnable() {
        init()
    }


    private fun init() {

        /** EVENT */

        /** COMMAND */
        getCommand("warp")?.setExecutor(WarpCmd())

        /** CONFIG */
    }
}