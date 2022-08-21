package com.xenotactic.korge.scenes

import com.soywiz.klogger.Logger
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.SContainer
import com.soywiz.korge.view.Views
import com.soywiz.korge.view.centerOnStage
import com.soywiz.korge.view.text
import com.soywiz.korio.file.std.resourcesVfs

lateinit var VIEWS_INSTANCE: Views

class RootScene(
    override var views: Views,
) : Scene() {
    override suspend fun SContainer.sceneInit() {
        VIEWS_INSTANCE = views

        this.text("Hello world", textSize = 50.0).centerOnStage()

        val test = resourcesVfs["test.txt"]

        logger.info {
            """
                test: $test
                test.path: ${test.path}
                test.absolutePath: ${test.absolutePath}
            """.trimIndent()
        }

        test.writeString("test string")

    }

    override suspend fun sceneAfterDestroy() {
        super.sceneAfterDestroy()
        logger.info {
            "sceneAfterDestroy called"
        }
    }

    override suspend fun sceneDestroy() {
        super.sceneDestroy()
        logger.info {
            "sceneDestroy called"
        }
    }

    companion object {
        val logger = Logger<RootScene>()
    }
}
