package id.lilule.football.mvp.main.teams

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ProgressBar
import android.widget.Spinner
import com.google.gson.Gson
import id.lilule.football.api.ApiRepository
import id.lilule.football.mvp.item.LeagueArrayAdapter
import id.lilule.football.mvp.item.TeamAdapter
import id.lilule.football.mvp.model.League
import id.lilule.football.mvp.model.Team
import id.lilule.football.mvp.model.TeamResponse
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.find


class TeamsFragment : androidx.fragment.app.Fragment(), TeamsView {

    private val ui = TeamsFragmentUI
    private var teamList: MutableList<Team> = mutableListOf()
    private var leagueList: MutableList<League> = mutableListOf()
    private var presenter: TeamsPresenter? = null
    private var teamAdapter: TeamAdapter? = null
    private var spLeagueAdapter: LeagueArrayAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return TeamsFragmentUI().createView(AnkoContext.create(inflater.context, this, false))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = TeamsPresenter(this, ApiRepository(), Gson())
        setupSpinner()
        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter = null
    }

    private fun setupRecyclerView() {
        teamAdapter = TeamAdapter(teamList)
        val rvTeams: androidx.recyclerview.widget.RecyclerView? = find(ui.rvTeams)
        rvTeams?.adapter = teamAdapter
    }

    private fun setupSpinner() {
        spLeagueAdapter = LeagueArrayAdapter(context!!, leagueList)
        spLeagueAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val spLeague = find<Spinner>(ui.spLeague)
        spLeague.adapter = spLeagueAdapter
        spLeague.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                loadData()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        presenter?.getAllLeagues()
    }

    private fun loadData() {
        val spLeague: Spinner? = find(ui.spLeague)
        if (spLeague != null && spLeagueAdapter != null) {
            val selectedLeague = spLeague.selectedItem as League?
            val selectedIdLeague: String? = selectedLeague?.idLeague
            if (selectedIdLeague != null) {
                presenter?.getAllTeamsByLeague(selectedIdLeague)
            }
        }
    }

    override fun showAllLeagues(data: List<League>) {
        leagueList.clear()
        leagueList.addAll(data)
        spLeagueAdapter?.notifyDataSetChanged()
    }

    override fun showAllTeamsByLeague(data: TeamResponse) {
        teamList.clear()
        if (!data.teams.isNullOrEmpty()) teamList.addAll(data.teams)
        teamAdapter?.notifyDataSetChanged()
    }

    override fun showLoading() {
        val progressBar: ProgressBar? = find(ui.pbTeams)
        progressBar?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        val progressBar: ProgressBar? = find(ui.pbTeams)
        progressBar?.visibility = View.GONE
    }
}