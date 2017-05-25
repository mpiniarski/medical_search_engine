package com.joma.studies.config

import com.joma.studies.EngineConfigProvider
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component


@Component
data class ServerConfigProvider(
        @Value("\${index.directory}")
        val indexDirectoryPath: String
) : EngineConfigProvider {
    override fun getIndexDirectory(): String {
        return this.indexDirectoryPath
    }
}