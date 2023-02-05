package com.yuk.miuiXXL.hooks.modules.mediaeditor

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.yuk.miuiXXL.hooks.modules.BaseHook
import com.yuk.miuiXXL.utils.findClassOrNull
import com.yuk.miuiXXL.utils.getBoolean

object RemoveCropRestriction : BaseHook() {
    override fun init() {

        if (!getBoolean("mediaeditor_remove_crop_restriction", false)) return
        val resizeDetectorClass = "com.miui.gallery.editor.photo.core.imports.obsoletes.Crop\$ResizeDetector".findClassOrNull()
        if (resizeDetectorClass != null) {
            findMethod("com.miui.gallery.editor.photo.core.imports.obsoletes.Crop\$ResizeDetector") {
                name == "calculateMinSize"
            }.hookBefore {
                it.result = 0
            }
        } else {
            var resizeDetector = 'a'
            for (i in 0..25) {
                val classIfExists = ("com.miui.gallery.editor.photo.screen.crop.ScreenCropView\$${resizeDetector}").findClassOrNull()
                if (classIfExists != null) try {
                    findMethod(classIfExists) {
                        returnType == Int::class.javaPrimitiveType && parameterCount == 0
                    }.hookBefore {
                        it.result = 0
                    }
                } catch (t: Throwable) {
                    resizeDetector++
                }
            }
        }
        val resizeDetectorClass1 = "com.miui.gallery.editor.photo.screen.crop.ScreenCropView\$ResizeDetector".findClassOrNull()
        if (resizeDetectorClass1 != null) {
            findMethod("com.miui.gallery.editor.photo.screen.crop.ScreenCropView\$ResizeDetector") {
                name == "calculateMinSize"
            }.hookBefore {
                it.result = 0
            }
        } else {
            var resizeDetector1 = 'a'
            for (i in 0..25) {
                try {
                    findMethod("com.miui.gallery.editor.photo.core.imports.obsoletes.Crop\$${resizeDetector1}") {
                        returnType == Int::class.javaPrimitiveType && parameterCount == 0
                    }.hookBefore {
                        it.result = 0
                    }
                } catch (_: Throwable) {
                    resizeDetector1++
                }
            }
        }
    }

}
