package id.lilule.football.mvp.main.matches

import android.database.sqlite.SQLiteConstraintException
import id.lilule.football.mvp.model.EventResponse
import id.lilule.football.mvp.model.League

interface MatchesView {
    fun showLoading()
    fun hideLoading()

    fun showAllLeagues(data: List<League>)
    fun showEventsPastLeague(data: EventResponse)
    fun showEventsNextLeague(data: EventResponse)

    fun showErrorDatabase(e: SQLiteConstraintException)
}