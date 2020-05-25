package com.recycler.adapter.base.animation

import android.animation.Animator
import android.view.View


interface BaseAnimation {
    fun animators(view: View): Array<Animator>
}