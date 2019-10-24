package com.pingo.moviebook.shared.ext

import android.app.Activity
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.bumptech.glide.Glide
import com.pingo.moviebook.R
import com.pingo.moviebook.shared.utils.HorizontalItemDecoration


/**
 * Extension methods
 */


/**
 * Make translucent screen
 * @receiver Activity
 */
fun Activity.setFullscreen() = with(window) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
}


/**
 * Set toolbar in fragment
 * @receiver Activity
 * @param toolbar Toolbar?
 */
fun Activity.setToolbar(toolbar: Toolbar?, showTitle: Boolean = false, title: String? = null) {
    (this as AppCompatActivity).setSupportActionBar(toolbar)
    supportActionBar?.setDisplayShowTitleEnabled(showTitle)
    setTitle(title)
}


/**
 * Widgets
 */
fun RecyclerView.setup(fixed: Boolean = true, nestedScroll: Boolean = false): RecyclerView {
    setHasFixedSize(fixed)
    setItemViewCacheSize(20)
    isNestedScrollingEnabled = nestedScroll
    addItemDecoration(HorizontalItemDecoration.create(this.context, R.color.divider, 0))
    (itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false

    return this
}


/**
 * Download and set image to the target regardless of what source is
 * @receiver ImageView
 * @param url String?
 * @param placeholder Int
 */
fun ImageView.showImage(url: String?, @DrawableRes placeholder: Int = R.drawable.avatar_placeholder) {
//    Picasso.get()
//        .load(url)
//        .placeholder(placeholder)
////        .transform(CropCircleTransformation())
//        .into(this, object : Callback {
//            override fun onSuccess() {
//                Log.e("Picasso", "Success")
//            }
//
//            override fun onError(e: Exception) {
//                Log.e("Picasso", "Failed: " + e.printStackTrace())
//            }
//        })

    Glide
        .with(this)
        .load(url)
        .into(this)
}


/**
 * Set tint color to the compound drawables to the text views
 * @receiver TextView
 * @param color String
 */
fun TextView.tintDrawable(color: String) {
    for (drawable in this.compoundDrawablesRelative) {
        if (drawable != null) {

            val tint = try {
                Color.parseColor(color)
            } catch (exp: Exception) {
                ContextCompat.getColor(this.context, R.color.colorAccent)
            }

            drawable.colorFilter = PorterDuffColorFilter(tint, PorterDuff.Mode.SRC_ATOP)
        }
    }
}
