package id.co.ppa_github.infrastructure

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.snackbar.Snackbar
import id.co.ppa_github.R
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onStart

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


fun showAlertSnackbar(view: View, text: String, actionText: String, action: (() -> Unit)?) {
    Snackbar.make(view, text, Snackbar.LENGTH_INDEFINITE)
        .setTextColor(ContextCompat.getColor(view.context, R.color.white))
        .setBackgroundTint(ContextCompat.getColor(view.context, R.color.red_alert)).apply {
            setActionTextColor(ContextCompat.getColor(view.context, R.color.white))
            setAction(actionText) {
                this.dismiss()
                action?.invoke()
            }
        }
        .show()
}

fun showConfirmationSnackbar(
    view: View,
    text: String,
    actionText: String,
    action: (() -> Unit)? = null
) {
    Snackbar.make(view, text, Snackbar.LENGTH_INDEFINITE)
        .setTextColor(ContextCompat.getColor(view.context, R.color.white))
        .setBackgroundTint(ContextCompat.getColor(view.context, R.color.green)).apply {
            setActionTextColor(ContextCompat.getColor(view.context, R.color.white))
            setAction(actionText) {
                this.dismiss()
                action?.invoke()
            }
        }
        .show()
}

@ExperimentalCoroutinesApi
fun SearchView.onTextChanged(): Flow<String?> {
    return callbackFlow<String?> {
        val listener = object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                offer(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                offer(newText)
                return true
            }
        }
        setOnQueryTextListener(listener)
        awaitClose { setOnQueryTextListener(null) }
    }.onStart { emit(query.toString()) }
}

infix fun View.openLink(link: String?) {
    isVisible = !link.isNullOrBlank()
    val uri = Uri.parse(link)
    val intent = Intent(Intent.ACTION_VIEW, uri)
    setOnClickListener { context.startActivity(intent) }
}


