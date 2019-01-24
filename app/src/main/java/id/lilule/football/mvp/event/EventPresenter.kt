package id.lilule.football.mvp.event

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import com.google.gson.Gson
import id.lilule.football.api.ApiRepository
import id.lilule.football.api.TheSportsDBApi
import id.lilule.football.database.database
import id.lilule.football.mvp.model.Event
import id.lilule.football.mvp.model.TeamResponse
import id.lilule.football.util.Constants.TABLE_FAVORITE_EVENTS
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class EventPresenter(
    private val view: EventView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getLookupTeam(idHomeTeam: String?, idAwayTeam: String?) {
        view.showLoading()
        doAsync {
            val dataHome = gson.fromJson(
                apiRepository.doRequest(TheSportsDBApi.getLookupTeam(idHomeTeam)),
                TeamResponse::class.java
            )

            val dataAway = gson.fromJson(
                apiRepository.doRequest(TheSportsDBApi.getLookupTeam(idAwayTeam)),
                TeamResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showLookupTeams(dataHome, dataAway)
            }
        }
    }

    fun insertFavorites(context: Context, data: Event) {
        view.showLoading()
        try {
            context.database.use {
                insert(
                    TABLE_FAVORITE_EVENTS,
                    Event.ID_EVENT to data.idEvent,
                    Event.STR_EVENT to data.strEvent,
                    Event.STR_SPORT to data.strSport,
                    Event.ID_LEAGUE to data.idLeague,
                    Event.STR_LEAGUE to data.strLeague,
                    Event.DATE_EVENT to data.dateEvent,
                    Event.STR_DATE to data.strDate,
                    Event.STR_TIME to data.strTime,

                    Event.ID_HOME_TEAM to data.idHomeTeam,
                    Event.STR_HOME_TEAM to data.strHomeTeam,
                    Event.INT_HOME_SCORE to data.intHomeScore,
                    Event.STR_HOME_FORMATION to data.strHomeFormation,
                    Event.STR_HOME_GOAL_DETAILS to data.strHomeGoalDetails,
                    Event.INT_HOME_SHOTS to data.intHomeShots,
                    Event.STR_HOME_RED_CARDS to data.strHomeRedCards,
                    Event.STR_HOME_YELLOW_CARDS to data.strHomeYellowCards,

                    Event.STR_HOME_LINEUP_GOALKEEPER to data.strHomeLineupGoalkeeper,
                    Event.STR_HOME_LINEUP_DEFENSE to data.strHomeLineupDefense,
                    Event.STR_HOME_LINEUP_MIDFIELD to data.strHomeLineupMidfield,
                    Event.STR_HOME_LINEUP_FORWARD to data.strHomeLineupForward,
                    Event.STR_HOME_LINEUP_SUBSTITUTES to data.strHomeLineupSubstitutes,

                    Event.ID_AWAY_TEAM to data.idAwayTeam,
                    Event.STR_AWAY_TEAM to data.strAwayTeam,
                    Event.INT_AWAY_SCORE to data.intAwayScore,
                    Event.STR_AWAY_FORMATION to data.strAwayFormation,
                    Event.STR_AWAY_GOAL_DETAILS to data.strAwayGoalDetails,
                    Event.INT_AWAY_SHOTS to data.intAwayShots,
                    Event.STR_AWAY_RED_CARDS to data.strAwayRedCards,
                    Event.STR_AWAY_YELLOW_CARDS to data.strAwayYellowCards,

                    Event.STR_AWAY_LINEUP_GOALKEEPER to data.strAwayLineupGoalkeeper,
                    Event.STR_AWAY_LINEUP_DEFENSE to data.strAwayLineupDefense,
                    Event.STR_AWAY_LINEUP_MIDFIELD to data.strAwayLineupMidfield,
                    Event.STR_AWAY_LINEUP_FORWARD to data.strAwayLineupForward,
                    Event.STR_AWAY_LINEUP_SUBSTITUTES to data.strAwayLineupSubstitutes
                )
            }
            view.hideLoading()
            view.showInsertResult(true)
        } catch (e: SQLiteConstraintException) {
            view.hideLoading()
            view.showErrorDatabase(e)
        }
    }

    fun deleteFavorites(context: Context, data: Event) {
        view.showLoading()
        try {
            context.database.use {
                delete(TABLE_FAVORITE_EVENTS, Event.ID_EVENT + " = {idEvent}", "idEvent" to data.idEvent.toString())
            }
            view.hideLoading()
            view.showDeleteResult(true)
        } catch (e: SQLiteConstraintException) {
            view.hideLoading()
            view.showErrorDatabase(e)
        }
    }

    fun queryFavorite(ctx: Context, data: Event) {
        view.showLoading()
        try {
            ctx.database.use {
                val rowParser = classParser<Event>()
                val queryResult =
                    select(TABLE_FAVORITE_EVENTS).whereArgs(
                        Event.ID_EVENT + " = {idEvent}",
                        "idEvent" to data.idEvent.toString()
                    )
                val queryParsed = queryResult.parseList(rowParser)
                val result = !queryParsed.isEmpty()
                view.hideLoading()
                view.setFavorite(result)
            }
        } catch (e: SQLiteConstraintException) {
            view.hideLoading()
            view.showErrorDatabase(e)
        }
    }
}