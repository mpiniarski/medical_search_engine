package com.joma.studies.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component


@Component
data class ConfigProvider(
        @Value("\${index.directory}")
        val indexDirectoryPath : String
)