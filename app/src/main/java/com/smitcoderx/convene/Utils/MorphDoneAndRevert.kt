package com.smitcoderx.convene.Utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.os.Handler
import android.os.Looper
import com.github.leandroborgesferreira.loadingbutton.customViews.ProgressButton
import com.smitcoderx.convene.R

@SuppressLint("UseCompatLoadingForDrawables")
fun ProgressButton.morphDoneAndRevert(
    context: Context,
    fillColor: Int,
    bitmap: Bitmap,
    doneTime: Long = 1000,
    revertTime: Long = 2000,
    callback: () -> Unit
) {
    progressType = com.github.leandroborgesferreira.loadingbutton.animatedDrawables.ProgressType.INDETERMINATE
    startAnimation()
    val handler = Handler(Looper.getMainLooper())
    val runnable = Runnable {
        handler.postDelayed({ doneLoadingAnimation(fillColor, bitmap) }, doneTime)
        handler.postDelayed(::revertAnimation, revertTime)
        handler.postDelayed(callback, revertTime)
    }

    handler.postDelayed(runnable, revertTime)
    context.getDrawable(R.drawable.bg_rounded_tab_look)?.let { setBackground(it) }
}