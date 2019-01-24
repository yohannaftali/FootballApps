package id.lilule.football.mvp.search

import com.google.gson.Gson
import id.lilule.football.api.ApiRepository
import id.lilule.football.api.TheSportsDBApi
import id.lilule.football.mvp.model.SearchEventsResponse
import id.lilule.football.mvp.model.TeamResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class SearchPresenter(
    private val activityView: SearchActivityView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getSearchEvents(e: String?) {
        activityView.showLoading()
        doAsync {
            val json = apiRepository.doRequest(TheSportsDBApi.getSearchEvents(e))

            uiThread {
                val data = gson.fromJson(json, SearchEventsResponse::class.java)
                activityView.hideLoading()
                if (!data.event.isNullOrEmpty()) {
                    activityView.showSearchEventResult(data.event)
                }
            }
        }
    }

    fun getSearchTeams(e: String?) {
        activityView.showLoading()
        doAsync {
            val json = apiRepository.doRequest(TheSportsDBApi.getSearchTeams(e))

            uiThread {
                val data = gson.fromJson(json, TeamResponse::class.java)
                activityView.hideLoading()
                if (!data.teams.isNullOrEmpty()) {
                    activityView.showSearchTeamsResult(data.teams)
                }
            }
        }
    }
}