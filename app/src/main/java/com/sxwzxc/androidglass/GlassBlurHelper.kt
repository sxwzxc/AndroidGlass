package com.sxwzxc.androidglass

import android.os.Build
import android.view.Window

/**
 * 液态玻璃模糊效果辅助工具类。
 * 局部模糊通过 GlassBackdropView 实现——仅在组件覆盖的区域显示模糊效果。
 * 在 API 31+ (Android 12+) 设备上使用 RenderEffect 实现真实的模糊效果。
 * 在低版本设备上优雅降级为半透明效果。
 */
object GlassBlurHelper {

    /**
     * 对窗口应用背景模糊效果（适用于对话框和底部弹窗）。
     */
    fun applyWindowBlur(window: Window, blurRadius: Int = 20) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            window.setBackgroundBlurRadius(blurRadius)
            window.setDimAmount(0.3f)
        }
    }
}
