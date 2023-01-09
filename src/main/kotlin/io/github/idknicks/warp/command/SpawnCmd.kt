package io.github.idknicks.warp.command

import io.github.idknicks.warp.data.WarpData
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SpawnCmd: CommandExecutor {

    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>): Boolean {

        var player: Player = sender as Player
        var warpData: WarpData = WarpData(player)

        if(sender is Player) {

            if(args.isEmpty()) {

                if(!player.hasPermission("warp.spawn")) {
                    player.sendMessage("§c당신은 이 명령어를 사용할 권한이 없습니다.")
                    return true
                }

                if(!warpData.isSpawnExist()) {
                    player.sendMessage("스폰이 설정되어있지 않습니다!")
                    return true
                }

                warpData.getSpawnLocation()?.let { player.teleport(it) }
                sender.sendMessage("스폰으로 이동했습니다.")
            }
        }
        return false;
    }
}

class SetSpawnCmd : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>): Boolean {

        var player: Player = sender as Player
        var warpData: WarpData = WarpData(player)

        if(sender is Player) {

            if(args.isEmpty()) {

                if(!player.hasPermission("warp.setspawn")) {
                    player.sendMessage("§c당신은 이 명령어를 사용할 권한이 없습니다.")
                    return true
                }

                warpData.setSpawnLocation()
                player.sendMessage("스폰이 설정되었습니다.")
            }
        }
        return false;
    }
}