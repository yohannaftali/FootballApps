package id.lilule.football.mvp.search

import id.lilule.football.mvp.model.Event
import id.lilule.football.mvp.model.Team

interface SearchActivityView {
    fun showLoading()
    fun hideLoading()

    fun showSearchEventResult(data: List<Event>)
    fun showSearchTeamsResult(data: List<Team>)
}