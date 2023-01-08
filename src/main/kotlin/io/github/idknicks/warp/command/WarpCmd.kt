package io.github.idknicks.warp.command

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class WarpCmd : CommandExecutor {

    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>): Boolean {

        var player: Player = sender as Player

        if (sender is Player) {

            if (args.isEmpty()) {
                player.sendMessage("테스트")
                return true;
            }

            when(args[0]) {

                "생성" -> {

                }


            }
        }
        return false;
    }
}