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


    /**
     * 워프 위치를 가져옵니다.
     * @param name 워프의 이름
     */
    fun getWarpLocation(name: String): Location? {
        var config: Config = Config("warp/" + name, plugin)
        return config.getLocation(name)
    }


    /**
     * 스폰을 설정합니다.
     */
    fun setSpawnLocation() {
        var config: Config = Config("spawn", plugin)
        config.saveLocation("spawn", player.location)
    }


    /**
     * 스폰 위치를 가져옵니다.
     */
    fun getSpawnLocation(): Location? {
        var config: Config = Config("spawn", plugin)
        return config.getLocation("spawn")
    }


    /**
     * 스폰이 존재하는지 확인합니다.
     * @return 스폰이 존재하면 true, 아니면 false
     */
    fun isSpawnExist(): Boolean {
        var config: Config = Config("spawn", plugin)
        return config.isFileExist
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