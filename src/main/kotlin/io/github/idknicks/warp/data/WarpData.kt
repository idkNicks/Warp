package io.github.idknicks.warp.data

import com.github.nicklib.data.Config
import io.github.idknicks.warp.Plugin.Companion.plugin
import org.bukkit.Location
import org.bukkit.entity.Player


class WarpData(player: Player) {


    var player: Player = player


    /**
     * 워프를 생성합니다.
     * @param name 생성할 워프의 이름
     */
    fun createWarp(name: String) {
        var config: Config = Config("warp/" + name, plugin)
        config.saveLocation(name, player.location)
    }


    /**
     * 워프를 제거합니다.
     * @param name 제거할 워프의 이름
     */
    fun deleteWarp(name: String) {
        var config: Config = Config("warp/" + name, plugin)
        config.deleteFile()
    }


    /**
     * 존재하는 워프를 가져옵니다.
     * @return 존재하는 워프의 이름
     */
    fun listWarp(): List<String> {
        var config: Config = Config("warp/", plugin)
        return config.fileListName()
    }


    fun getWarpLocation(name: String): Location? {
        var config: Config = Config("warp/" + name, plugin)
        return config.getLocation(name)
    }


    /**
     * 워프가 존재하는지 확인합니다.
     * @param name 확인할 워프의 이름
     */
    fun isWarpExist(name: String): Boolean {
        var config: Config = Config("warp/" + name, plugin)
        return config.isFileExist
    }
}