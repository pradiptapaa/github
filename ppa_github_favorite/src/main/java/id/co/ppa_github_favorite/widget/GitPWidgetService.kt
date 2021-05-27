package id.co.ppa_github_favorite.widget

import android.content.Intent
import android.widget.RemoteViewsService

class GitPWidgetService : RemoteViewsService() {

    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {
        return FavoriteRemoteViewsFactory(this.applicationContext)
    }
}