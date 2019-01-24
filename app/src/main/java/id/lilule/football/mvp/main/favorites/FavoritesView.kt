package id.lilule.football.mvp.main.favorites

import android.database.sqlite.SQLiteConstraintException
import id.lilule.football.mvp.model.Event
import id.lilule.football.mvp.model.Team

interface FavoritesView {
    fun showLoading()
    fun hideLoading()
    fun showFavoriteEvents(data: List<Event>)
    fun showFavoriteTeams(data: List<Team>)
    fun showErrorDatabase(e: SQLiteConstraintException)
}