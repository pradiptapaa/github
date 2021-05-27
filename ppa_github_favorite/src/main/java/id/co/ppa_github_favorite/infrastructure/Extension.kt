package id.co.ppa_github_favorite.infrastructure

import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import id.co.ppa_github_favorite.R

infix fun ImageView.loadPhotoFrom(url: String?) {
    if (!url.isNullOrEmpty())
        Glide.with(this.context.applicationContext)
            .load(url)
            .override(500, 300)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.drawable.placeholder_square)
            .error(R.drawable.placeholder_square)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(this)
    else
        Glide.with(this.context.applicationContext)
            .load(R.drawable.placeholder_square)
            .into(this)
}

infix fun TextView.setTextFrom(text: String?) {
    if (!text.isNullOrBlank()) {
        this.isVisible = true
        this.text = text
    } else
        this.isVisible = false
}