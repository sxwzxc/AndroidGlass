package com.sxwzxc.androidglass

import android.content.Context
import android.graphics.Matrix
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.util.AttributeSet
import android.view.ViewTreeObserver
import androidx.appcompat.widget.AppCompatImageView

/**
 * 液态玻璃局部模糊背景视图。
 * 仅对该视图覆盖的屏幕区域显示背景图片的模糊效果，实现真正的局部磨砂玻璃效果。
 * 应作为玻璃卡片容器（FrameLayout）的第一个子视图，置于内容层之下。
 */
class GlassBackdropView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : AppCompatImageView(context, attrs) {

    private val scrollListener = ViewTreeObserver.OnScrollChangedListener { updateMatrix() }

    init {
        scaleType = ScaleType.MATRIX
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            setRenderEffect(
                RenderEffect.createBlurEffect(25f, 25f, Shader.TileMode.MIRROR)
            )
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        viewTreeObserver.addOnScrollChangedListener(scrollListener)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        viewTreeObserver.removeOnScrollChangedListener(scrollListener)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        updateMatrix()
    }

    private fun updateMatrix() {
        val d = drawable ?: return
        val drawableW = d.intrinsicWidth.toFloat()
        val drawableH = d.intrinsicHeight.toFloat()
        if (drawableW <= 0f || drawableH <= 0f || width <= 0 || height <= 0) return

        val root = rootView
        val rootW = root.width.toFloat()
        val rootH = root.height.toFloat()
        if (rootW <= 0f || rootH <= 0f) return

        // 计算与 bgScene centerCrop 一致的缩放比例
        val scale = maxOf(rootW / drawableW, rootH / drawableH)
        val scaledLeft = (rootW - drawableW * scale) / 2f
        val scaledTop = (rootH - drawableH * scale) / 2f

        // 获取本视图相对根视图的屏幕位置
        val rootLoc = IntArray(2)
        val myLoc = IntArray(2)
        root.getLocationOnScreen(rootLoc)
        getLocationOnScreen(myLoc)

        val relX = (myLoc[0] - rootLoc[0]).toFloat()
        val relY = (myLoc[1] - rootLoc[1]).toFloat()

        // 调整矩阵以显示该视图对应的背景图片区域
        val m = Matrix()
        m.setScale(scale, scale)
        m.postTranslate(scaledLeft - relX, scaledTop - relY)
        imageMatrix = m
    }
}
