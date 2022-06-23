package app.dealux.view_animations_library

import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils

fun View.fadeIn(duration: Long, endAction: () -> Unit = {}) = animate()
    .also { clearAnimation() }
    .setDuration(duration)
    .alpha(1f)
    .withStartAction {
        visibility = View.INVISIBLE
        alpha = 0f
    }
    .withEndAction {
        visibility = View.VISIBLE
        endAction()
    }
    .start()

fun View.fadeOut(duration: Long = 0, endAction: () -> Unit = {}) = animate()
    .also { clearAnimation() }
    .setDuration(duration)
    .alphaBy(-1f)
    .withStartAction { alpha = 1f }
    .withEndAction {
        visibility = View.INVISIBLE
        endAction()
    }
    .start()

fun View.explode(endAction: () -> Unit = {}) {
    val explodeAnim = AnimationUtils.loadAnimation(this.context, R.anim.explode)

    this.visibility = View.VISIBLE
    this.startAnimation(explodeAnim)

    Handler(Looper.getMainLooper()).postDelayed({
        endAction()
    }, 500)
}

fun View.shake(view: View) {
    val startX = 0f
    val translationX = 35f
    val duration = 100L

    repeat(2) {
        view.animate()
            .translationX(translationX)
            .setDuration(duration)
            .withEndAction {
                view.animate()
                    .translationX(startX)
                    .duration = duration
            }
            .start()
    }
}