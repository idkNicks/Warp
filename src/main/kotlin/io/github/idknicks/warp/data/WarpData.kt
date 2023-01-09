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
        Config("warp/" + name, plugin).saveLocation(name, player.location)
    }

    /**
     * 워프를 제거합니다.
     * @param name 제거할 워프의 이름
     */
    fun deleteWarp(name: String) {
        Config("warp/" + name, plugin).deleteFile()
    }

    /**
     * 존재하는 워프를 가져옵니다.
     * @return 존재하는 워프의 이름
     */
    fun listWarp(): List<String> {
        return Config("warp/", plugin).fileListName()
    }

    /**
     * 워프 위치를 가져옵니다.
     * @param name 워프의 이름
     */
    fun getWarpLocation(name: String): Location? {
        return Config("warp/" + name, plugin).getLocation(name)
    }

    /**
     * 스폰을 설정합니다.
     */
    fun setSpawnLocation() {
        Config("spawn", plugin).saveLocation("spawn", player.location)
    }

    /**
     * 스폰 위치를 가져옵니다.
     */
    fun getSpawnLocation(): Location? {
        return Config("spawn", plugin).getLocation("spawn")
    }

    /**
     * 스폰이 존재하는지 확인합니다.
     * @return 스폰이 존재하면 true, 아니면 false
     */
    fun isSpawnExist(): Boolean {
        return Config("spawn", plugin).isFileExist
    }

    /**
     * 워프가 존재하는지 확인합니다.
     * @param name 확인할 워프의 이름
     */
    fun isWarpExist(name: String): Boolean {
        return Config("warp/" + name, plugin).isFileExist
    }
}