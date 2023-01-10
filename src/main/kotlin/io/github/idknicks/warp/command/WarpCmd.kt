package io.github.idknicks.warp.command

import io.github.idknicks.warp.Plugin.Companion.config
import io.github.idknicks.warp.Plugin.Companion.prefix
import io.github.idknicks.warp.data.WarpData
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class WarpCmd : CommandExecutor {


    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>): Boolean {

        var player: Player = sender as Player
        var warpData: WarpData = WarpData(player)

        if (sender is Player) {

            if (args.isEmpty()) {
                player.sendMessage("테스트")
                return true;
            }

            when (args[0]) {

                "이동", "teleport" -> {

                    if (!player.hasPermission("warp.teleport")) {
                        player.sendMessage("${prefix}".plus(config!!.getString("errMessages.noPermission")))
                        return true
                    }

                    if (args.size == 1) {
                        player.sendMessage("${prefix}".plus(config!!.getString("errMessages.noTypingWarp")))
                        return true;
                    }

                    if (!warpData.isWarpExist(args[1])) {
                        player.sendMessage("${prefix}".plus(config!!.getString("errMessages.notExistWarp")))
                        return true
                    }

                    var name: String = args[1]
                    warpData.getWarpLocation(name)?.let { player.teleport(it) }
                    player.sendMessage("$prefix".plus(config!!.getString("messages.warp.teleport").replace("{name}", name)))
                }

                "강제이동" -> {

                    if (!player.hasPermission("warp.forcedteleport")) {
                        player.sendMessage("${prefix}".plus(config!!.getString("errMessages.noPermission")))
                        return true
                    }

                    if (args.size == 1) {
                        player.sendMessage("${prefix}".plus(config!!.getString("errMessages.noTypingPlayer")))
                        return true;
                    }

                    if(args.size == 2) {
                        player.sendMessage("${prefix}".plus(config!!.getString("errMessages.noTypingWarp")))
                        return true;
                    }

                    if (!warpData.isWarpExist(args[2])) {
                        player.sendMessage("${prefix}".plus(config!!.getString("errMessages.notExistWarp")))
                        return true
                    }

                    if(Bukkit.getPlayer(args[1]) == null) {
                        player.sendMessage("${prefix}".plus(config!!.getString("errMessages.targetIsOffline")))
                        return true
                    }

                    var target: Player = player.server.getPlayer(args[1])!!
                    var name: String = args[2]

                    warpData.getWarpLocation(name)?.let { target.teleport(it) }
                    player.sendMessage("워프 $name 으로 ${target.name} 님을 이동했습니다.")
                    player.sendMessage("${prefix}".plus(config!!.getString("messages.warp.forcedTeleport")
                    .replace("{name}", name)
                    .replace("{target}", target.name)))
                }


                "생성", "create" -> {

                    if (!player.hasPermission("warp.create")) {
                        player.sendMessage("${prefix}".plus(config!!.getString("errMessages.noPermission")))
                        return true;
                    }

                    if (args.size == 1) {
                        player.sendMessage("${prefix}".plus(config!!.getString("errMessages.noTypingWarp")))
                        return true;
                    }

                    if (warpData.isWarpExist(args[1])) {
                        player.sendMessage("${prefix}".plus(config!!.getString("errMessages.alreadyWarp")))
                        return true;
                    }

                    var name: String = args[1]
                    warpData.createWarp(name)
                    player.sendMessage("${prefix}".plus(config!!.getString("messages.warp.create")
                        .replace("{name}", name)))
                    return true;
                }

                "제거", "삭제", "delete" -> {

                    var name: String = args[1]

                    if (!player.hasPermission("warp.delete")) {
                        player.sendMessage("${prefix}".plus(config!!.getString("errMessages.noPermission")))
                        return true;
                    }

                    if (args.size == 1) {
                        player.sendMessage("${prefix}".plus(config!!.getString("errMessages.noTypingWarp")))
                        return true;
                    }

                    if (!warpData.isWarpExist(name)) {
                        player.sendMessage("${prefix}".plus(config!!.getString("errMessages.notExistWarp")))
                        return true;
                    }

                    warpData.deleteWarp(name)
                    player.sendMessage("${prefix}".plus(config!!.getString("messages.warp.delete")
                        .replace("{name}", name)))
                    return true;

                }

                "목록" -> {

                    if (!player.hasPermission("warp.list")) {
                        player.sendMessage("${prefix}".plus(config!!.getString("errMessages.noPermission")))
                        return true;
                    }

                    var list: List<String> = warpData.listWarp()
                    player.sendMessage("워프 목록: ${list}")
                    return true;

                }

                else -> {
                    player.sendMessage("${prefix}".plus(config!!.getString("errMessages.notExistCommand")))
                }
            }
        }
        return false;
    }
}