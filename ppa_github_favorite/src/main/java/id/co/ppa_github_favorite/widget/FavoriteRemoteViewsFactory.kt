package id.co.ppa_github_favorite.widget

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import androidx.core.os.bundleOf
import com.bumptech.glide.Glide
import com.squareup.moshi.Moshi
import id.co.ppa_github_favorite.R
import id.co.ppa_github_favorite.core.domain.UserDetails
import id.co.ppa_github_favorite.infrastructure.DatabaseContract
import id.co.ppa_github_favorite.infrastructure.ObjectConverter
import id.co.ppa_github_favorite.infrastructure.ProviderMapper

class FavoriteRemoteViewsFactory(private val context: Context) :
    RemoteViewsService.RemoteViewsFactory {

    private var list: List<UserDetails>? = null
    private var converter: ObjectConverter? = null

    override fun onCreate() {
        Log.d("StackWidget", "onCreate: ")
        list = listOf()
        converter = ObjectConverter(Moshi.Builder().build())
    }

    override fun onDestroy() {
        list = null
        converter = null
    }

    override fun onDataSetChanged() {
        val cursor = context.contentResolver.query(
            DatabaseContract.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        list = ProviderMapper.mapCursorToList(cursor, converter!!)
        Log.d("StackWidget", "onDataSetChanged: $list")
    }

    override fun getCount(): Int {
        return list!!.size
    }

    override fun getViewAt(position: Int): RemoteViews {
        val rv = RemoteViews(context.packageName, R.layout.item_favorite_widget)
        Log.d("StackWidget", "getViewAt: $list")
        val bitmap = Glide
            .with(context.applicationContext) // safer!
            .asBitmap()
            .load(list?.get(position)?.userDetailsResponse?.avatarUrl)
            .submit(512, 512).get()
        rv.setImageViewBitmap(R.id.imageView, bitmap)

        val extras = bundleOf(
            GitPWidget.EXTRA_ITEM to position
        )
        val fillInIntent = Intent()
        fillInIntent.putExtras(extras)

        rv.setOnClickFillInIntent(R.id.imageView, fillInIntent)
        return rv
    }

    override fun getLoadingView(): RemoteViews? {
        return null
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun hasStableIds(): Boolean {
        return false
    }
}