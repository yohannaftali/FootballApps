package id.lilule.football.mvp.main.teams

import com.google.gson.Gson
import id.lilule.football.api.ApiRepository
import id.lilule.football.api.TheSportsDBApi
import id.lilule.football.mvp.model.LeagueResponse
import id.lilule.football.mvp.model.TeamResponse
import id.lilule.football.util.Constants
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamsPresenter(
    private val view: TeamsView?,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getAllLeagues() {
        view?.showLoading()
        doAsync {
            val data =
                gson.fromJson(
                    apiRepository.doRequest(TheSportsDBApi.getAllLeagues()),
                    LeagueResponse::class.java
                )

            uiThread {
                view?.hideLoading()
                if (!data.leagues.isNullOrEmpty()) {
                    val soccerLeague = data.leagues.filter { a -> a.strSport == Constants.STR_SPORT_SOCCER }
                    val cleanLeague = soccerLeague.filter { a -> a.strLeague != Constants.STR_LEAGUE_NO_LEAGUE }
                    view?.showAllLeagues(cleanLeague)
                }
            }
        }
    }

    fun getAllTeamsByLeague(id: String?) {
        view?.showLoading()
        doAsync {
            val data =
                gson.fromJson(
                    apiRepository.doRequest(TheSportsDBApi.getLookupAllTeamsByLeagueId(id)),
                    TeamResponse::class.java
                )

            uiThread {
                view?.hideLoading()
                view?.showAllTeamsByLeague(data)
            }
        }
    }
}