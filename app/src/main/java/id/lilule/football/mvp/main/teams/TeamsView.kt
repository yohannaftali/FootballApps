package id.lilule.football.mvp.main.teams

import id.lilule.football.mvp.model.League
import id.lilule.football.mvp.model.TeamResponse

interface TeamsView {
    fun showLoading()
    fun hideLoading()

    fun showAllLeagues(data: List<League>)
    fun showAllTeamsByLeague(data: TeamResponse)
}