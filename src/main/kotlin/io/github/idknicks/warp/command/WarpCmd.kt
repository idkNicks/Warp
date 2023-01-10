package io.github.idknicks.warp.command

import io.github.idknicks.warp.data.WarpData
import org.bukkit.Bukkit
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
                        player.sendMessage("§c당신은 이 명령어를 사용할 §c당신은 이 명령어를 사용할 권한이 없습니다.")
                        return true
                    }

                    if (args.size == 1) {
                        player.sendMessage("이동하실 워프 이름을 입력해주세요!")
                        return true;
                    }

                    if (!warpData.isWarpExist(args[1])) {
                        player.sendMessage("워프가 존재하지 않습니다.")
                        return true
                    }

                    var name: String = args[1]
                    warpData.getWarpLocation(name)?.let { player.teleport(it) }
                    player.sendMessage("워프 $name 으로 이동했습니다.")
                }

                "강제이동" -> {

                    if (!player.hasPermission("warp.forcedteleport")) {
                        player.sendMessage("§c당신은 이 명령어를 사용할 §c당신은 이 명령어를 사용할 권한이 없습니다.")
                        return true
                    }

                    if (args.size == 1) {
                        player.sendMessage("강제 이동시킬 플레이어의 닉네임을 입력해주세요!")
                        return true;
                    }

                    if(args.size == 2) {
                        player.sendMessage("강제 이동시킬 워프 이름을 입력해주세요!")
                        return true;
                    }

                    if (!warpData.isWarpExist(args[2])) {
                        player.sendMessage("워프가 존재하지 않습니다.")
                        return true
                    }

                    if(Bukkit.getPlayer(args[1]) == null) {
                        player.sendMessage("플레이어가 온라인이 아닙니다.")
                        return true
                    }

                    var target: Player = player.server.getPlayer(args[1])!!
                    var name: String = args[2]

                    warpData.getWarpLocation(name)?.let { target.teleport(it) }
                    player.sendMessage("워프 $name 으로 ${target.name} 님을 이동했습니다.")
                }


                "생성", "create" -> {

                    if (!player.hasPermission("warp.create")) {
                        player.sendMessage("§c당신은 이 명령어를 사용할 권한이 없습니다.")
                        return true;
                    }

                    if (args.size == 1) {
                        player.sendMessage("생성할 워프 이름을 입력해주세요!")
                        return true;
                    }

                    if (warpData.isWarpExist(args[1])) {
                        player.sendMessage("이미 존재하는 워프입니다!")
                        return true;
                    }

                    var name: String = args[1]
                    warpData.createWarp(name)
                    player.sendMessage("${name}의 워프를 생성하였습니다!")
                    return true;
                }

                "제거", "삭제", "delete" -> {

                    var name: String = args[1]

                    if (!player.hasPermission("warp.delete")) {
                        player.sendMessage("§c당신은 이 명령어를 사용할 권한이 없습니다.")
                        return true;
                    }

                    if (args.size == 1) {
                        player.sendMessage("제거할 워프 이름을 입력해주세요!")
                        return true;
                    }

                    if (!warpData.isWarpExist(name)) {
                        player.sendMessage("존재하지 않는 워프입니다!")
                        return true;
                    }

                    warpData.deleteWarp(name)
                    player.sendMessage("${name}의 워프를 제거하였습니다!")
                    return true;

                }

                "목록" -> {

                    if (!player.hasPermission("warp.list")) {
                        player.sendMessage("§c당신은 이 명령어를 사용할 권한이 없습니다.")
                        return true;
                    }

                    var list: List<String> = warpData.listWarp()
                    player.sendMessage("워프 목록: ${list}")
                    return true;

                }

                else -> {
                    player.sendMessage("잘못된 명령어 입니다!")
                }
            }
        }
        return false;
    }
}