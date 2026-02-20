package com.sxwzxc.androidglass

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.view.View
import android.view.Window

/**
 * 液态玻璃模糊效果辅助工具类。
 * 在 API 31+ (Android 12+) 设备上使用 RenderEffect 实现真实的模糊效果。
 * 在低版本设备上优雅降级为半透明效果。
 */
object GlassBlurHelper {

    private const val DEFAULT_BLUR_RADIUS = 25f
    private const val LIGHT_BLUR_RADIUS = 15f

    /**
     * 对背景图片应用模糊效果，使其上方的半透明玻璃卡片呈现磨砂玻璃效果。
     */
    fun applyBackgroundBlur(view: View, radius: Float = DEFAULT_BLUR_RADIUS) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            view.setRenderEffect(
                RenderEffect.createBlurEffect(radius, radius, Shader.TileMode.MIRROR)
            )
        }
    }

    /**
     * 对玻璃卡片应用模糊效果。
     * 在 API 31+ 上使用 RenderEffect 将背景模糊与半透明颜色叠加。
     */
    fun applyGlassEffect(view: View, radius: Float = LIGHT_BLUR_RADIUS) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            view.setRenderEffect(
                RenderEffect.createBlurEffect(radius, radius, Shader.TileMode.CLAMP)
            )
        }
    }

    /**
     * 对窗口应用背景模糊效果（适用于对话框和底部弹窗）。
     */
    fun applyWindowBlur(window: Window, blurRadius: Int = 20) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            window.setBackgroundBlurRadius(blurRadius)
            window.setDimAmount(0.3f)
        }
    }

    /**
     * 清除视图上的模糊效果。
     */
    fun clearBlurEffect(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            view.setRenderEffect(null)
        }
    }
}
