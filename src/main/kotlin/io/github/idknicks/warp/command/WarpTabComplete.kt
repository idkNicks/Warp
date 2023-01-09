package io.github.idknicks.warp.command

import com.github.nicklib.data.Config
import io.github.idknicks.warp.Plugin.Companion.plugin
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.util.StringUtil

class WarpTabComplete : TabCompleter {

    override fun onTabComplete(sender: CommandSender, cmd: Command, label: String, args: Array<out String>): MutableList<String> {
        var list: MutableList<String> = mutableListOf()

        if (args.size == 1) {
            StringUtil.copyPartialMatches(args[0], listOf("이동", "강제이동", "생성", "제거", "목록"), list)
        }

        else if(args.size == 2) {
            if (args[0].equals("이동") || args[0].equals("제거")) {
                val config: Config = Config("warp/", plugin)
                for (warp: String in config.fileListName()) {
                    list.add(warp)
                }
            } else if(args[0].equals("강제이동")) {
                for (player in plugin!!.server.onlinePlayers) {
                    list.add(player.name)
                }
            } else if(args[0].equals("생성")) {
                list.add("<이름>")
            }


        } else if(args.size == 3) {
            if(args[0].equals("강제이동")) {
                val config: Config = Config("warp/", plugin)
                for (warp: String in config.fileListName()) {
                    list.add(warp)
                }
            }
        }
        return list
    }
}