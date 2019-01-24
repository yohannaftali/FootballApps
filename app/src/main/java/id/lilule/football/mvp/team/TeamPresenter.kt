package id.lilule.football.mvp.team

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import com.google.gson.Gson
import id.lilule.football.api.ApiRepository
import id.lilule.football.api.TheSportsDBApi
import id.lilule.football.database.database
import id.lilule.football.mvp.model.PlayerResponse
import id.lilule.football.mvp.model.Team
import id.lilule.football.util.Constants.TABLE_FAVORITE_TEAMS
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamPresenter(
    private val view: TeamView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getPlayers(idTeam: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportsDBApi.getLookupAllPlayersByTeamId(idTeam)),
                PlayerResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showPlayer(data)
            }
        }
    }

    fun insertFavorites(context: Context, data: Team) {
        view.showLoading()
        try {
            context.database.use {
                insert(
                    TABLE_FAVORITE_TEAMS,
                    Team.ID_TEAM to data.idTeam,
                    Team.STR_TEAM to data.strTeam,
                    Team.STR_STADIUM to data.strStadium,
                    Team.INT_FORMED_YEAR to data.intFormedYear,
                    Team.STR_DESCRIPTION_EN to data.strDescriptionEN,
                    Team.STR_TEAM_BADGE to data.strTeamBadge,
                    Team.STR_LEAGUE to data.strLeague,
                    Team.STR_ALTERNATE to data.strAlternate
                )
            }
            view.hideLoading()
            view.showInsertResult(true)
        } catch (e: SQLiteConstraintException) {
            view.hideLoading()
            view.showErrorDatabase(e)
        }
    }

    fun deleteFavorites(context: Context, data: Team) {
        view.showLoading()
        try {
            context.database.use {
                delete(TABLE_FAVORITE_TEAMS, Team.ID_TEAM + " = {idTeam}", "idTeam" to data.idTeam.toString())
            }
            view.hideLoading()
            view.showDeleteResult(true)
        } catch (e: SQLiteConstraintException) {
            view.hideLoading()
            view.showErrorDatabase(e)
        }
    }

    fun queryFavorite(ctx: Context, data: Team) {
        view.showLoading()
        try {
            ctx.database.use {
                val rowParser = classParser<Team>()
                val queryResult =
                    select(TABLE_FAVORITE_TEAMS).whereArgs(
                        Team.ID_TEAM + " = {idTeam}",
                        "idTeam" to data.idTeam.toString()
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