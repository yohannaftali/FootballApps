package id.lilule.football.mvp.main.favorites

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import com.google.gson.Gson
import id.lilule.football.api.ApiRepository
import id.lilule.football.database.database
import id.lilule.football.mvp.model.Event
import id.lilule.football.mvp.model.Team
import id.lilule.football.util.Constants.TABLE_FAVORITE_EVENTS
import id.lilule.football.util.Constants.TABLE_FAVORITE_TEAMS
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoritesPresenter(
    private val view: FavoritesView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getFavoriteEvents(context: Context) {
        view?.showLoading()
        val data: MutableList<Event> = mutableListOf()
        try {
            context.database.use {
                val rowParser = classParser<Event>()
                val queryResult = select(TABLE_FAVORITE_EVENTS)
                val queryParsed = queryResult.parseList(rowParser)
                data.addAll(queryParsed)
            }
            view?.hideLoading()
            view?.showFavoriteEvents(data)
        } catch (e: SQLiteConstraintException) {
            view?.hideLoading()
            view?.showErrorDatabase(e)
        }
    }

    fun getFavoriteTeams(context: Context) {
        view?.showLoading()
        val data: MutableList<Team> = mutableListOf()
        try {
            context.database.use {
                val rowParser = classParser<Team>()
                val queryResult = select(TABLE_FAVORITE_TEAMS)
                val queryParsed = queryResult.parseList(rowParser)
                data.addAll(queryParsed)
            }
            view?.hideLoading()
            view?.showFavoriteTeams(data)
        } catch (e: SQLiteConstraintException) {
            view?.hideLoading()
            view?.showErrorDatabase(e)
        }
    }
}