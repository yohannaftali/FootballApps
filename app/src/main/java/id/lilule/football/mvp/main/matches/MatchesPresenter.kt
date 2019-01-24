package id.lilule.football.mvp.main.matches

import com.google.gson.Gson
import id.lilule.football.api.ApiRepository
import id.lilule.football.api.TheSportsDBApi
import id.lilule.football.mvp.model.EventResponse
import id.lilule.football.mvp.model.LeagueResponse
import id.lilule.football.util.Constants
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchesPresenter(
    private val view: MatchesView,
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

    fun getEventsPastLeague(idLeague: String?) {
        view?.showLoading()
        doAsync {
            val data =
                gson.fromJson(
                    apiRepository.doRequest(TheSportsDBApi.getEventsPastLeague(idLeague)),
                    EventResponse::class.java
                )

            uiThread {
                view?.hideLoading()
                view?.showEventsPastLeague(data)
            }
        }
    }

    fun getEventsNextLeague(idLeague: String?) {
        view?.showLoading()
        doAsync {
            val data =
                gson.fromJson(
                    apiRepository.doRequest(TheSportsDBApi.getEventsNextLeague(idLeague)),
                    EventResponse::class.java
                )

            uiThread {
                view?.hideLoading()
                view?.showEventsNextLeague(data)
            }
        }
    }
}