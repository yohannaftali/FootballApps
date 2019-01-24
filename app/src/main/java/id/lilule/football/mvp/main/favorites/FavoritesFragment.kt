package id.lilule.football.mvp.main.favorites

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.google.gson.Gson
import id.lilule.football.R
import id.lilule.football.api.ApiRepository
import id.lilule.football.mvp.item.EventAdapter
import id.lilule.football.mvp.item.TeamAdapter
import id.lilule.football.mvp.model.Event
import id.lilule.football.mvp.model.Team
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.toast


class FavoritesFragment : androidx.fragment.app.Fragment(), FavoritesView {

    private val ui = FavoritesFragmentUI
    private var eventList: MutableList<Event> = mutableListOf()
    private var teamList: MutableList<Team> = mutableListOf()
    private var presenter: FavoritesPresenter? = null
    private var eventAdapter: EventAdapter? = null
    private var teamAdapter: TeamAdapter? = null
    private var tlFavorites: TabLayout? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FavoritesFragmentUI().createView(AnkoContext.create(inflater.context, this, false))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = FavoritesPresenter(this, ApiRepository(), Gson())
        tlFavorites = find(ui.tlFavorites)
        setupRecyclerView()
        setupTabLayout()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter = null
    }

    private fun setupTabLayout() {
        if (tlFavorites?.tabCount == 0) {
            tlFavorites?.newTab()?.setText(getString(R.string.favorite_events))?.let { tlFavorites?.addTab(it) }
            tlFavorites?.newTab()?.setText(getString(R.string.favorite_teams))?.let { tlFavorites?.addTab(it) }
            tlFavorites?.addOnTabSelectedListener(object :
                TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    loadData()
                }
                override fun onTabUnselected(tab: TabLayout.Tab) {}
                override fun onTabReselected(tab: TabLayout.Tab) {}
            })
        }
    }

    private fun loadData() {
        when (tlFavorites?.selectedTabPosition) {
            0 -> presenter?.getFavoriteEvents(activity?.applicationContext!!)
            1 -> presenter?.getFavoriteTeams(activity?.applicationContext!!)
        }
    }

    private fun setupRecyclerView() {
        eventAdapter = EventAdapter(eventList)
        val rvFavoriteEvent: androidx.recyclerview.widget.RecyclerView? = find(ui.rvFavoriteEvent)
        rvFavoriteEvent?.adapter = eventAdapter
        teamAdapter = TeamAdapter(teamList)
        val rvFavoriteTeam: androidx.recyclerview.widget.RecyclerView? = find(ui.rvFavoriteTeam)
        rvFavoriteTeam?.adapter = teamAdapter
    }

    override fun showFavoriteEvents(data: List<Event>) {
        eventList.clear()
        if(!data.isNullOrEmpty()) eventList.addAll(data)
        eventAdapter?.notifyDataSetChanged()
        showRecyclerViewEvent()
    }

    override fun showFavoriteTeams(data: List<Team>) {
        teamList.clear()
        if(!data.isNullOrEmpty()) teamList.addAll(data)
        teamAdapter?.notifyDataSetChanged()
        showRecyclerViewTeam()
    }

    private fun showRecyclerViewEvent() {
        val rvFavoriteTeam: androidx.recyclerview.widget.RecyclerView? = find(ui.rvFavoriteTeam)
        val rvFavoriteEvent: androidx.recyclerview.widget.RecyclerView? = find(ui.rvFavoriteEvent)
        rvFavoriteTeam?.visibility = View.GONE
        rvFavoriteEvent?.visibility = View.VISIBLE
    }

    private fun showRecyclerViewTeam() {
        val rvFavoriteTeam: androidx.recyclerview.widget.RecyclerView? = find(ui.rvFavoriteTeam)
        val rvFavoriteEvent: androidx.recyclerview.widget.RecyclerView? = find(ui.rvFavoriteEvent)
        rvFavoriteTeam?.visibility = View.VISIBLE
        rvFavoriteEvent?.visibility = View.GONE
    }

    override fun showLoading() {
        val progressBar:ProgressBar? = find(ui.pbFavorites)
        progressBar?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        val progressBar:ProgressBar? = find(ui.pbFavorites)
        progressBar?.visibility = View.GONE
    }

    override fun showErrorDatabase(e: SQLiteConstraintException) {
        toast(e.toString())
    }
}