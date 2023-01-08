package io.github.idknicks.warp.data

import com.github.nicklib.data.Config
import org.bukkit.entity.Player

class WarpData {

    var player: Player? = null


    constructor(player: Player) {
        this.player
    }

    fun createWarp(name: String) {
        var config: Config
        player?.sendMessage("${name}의 워프를 생성하였습니다!")

    }

}