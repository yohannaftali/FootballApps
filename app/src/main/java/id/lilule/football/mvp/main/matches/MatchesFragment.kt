package id.lilule.football.mvp.main.matches

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ProgressBar
import android.widget.Spinner
import com.google.gson.Gson
import id.lilule.football.R
import id.lilule.football.api.ApiRepository
import id.lilule.football.mvp.item.EventAdapter
import id.lilule.football.mvp.item.LeagueArrayAdapter
import id.lilule.football.mvp.model.Event
import id.lilule.football.mvp.model.EventResponse
import id.lilule.football.mvp.model.League
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.toast


class MatchesFragment : androidx.fragment.app.Fragment(), MatchesView {

    private val ui = MatchesFragmentUI
    private var eventList: MutableList<Event> = mutableListOf()
    private var leagueList: MutableList<League> = mutableListOf()
    private var presenter: MatchesPresenter? = null
    private var eventAdapter: EventAdapter? = null
    private var spLeagueAdapter: LeagueArrayAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return MatchesFragmentUI().createView(AnkoContext.create(inflater.context, this, false))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = MatchesPresenter(this, ApiRepository(), Gson())
        setupUI()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter = null
    }

    private fun setupUI() {
        setupSpinner()
        setupRecyclerView()
        setupTabLayout()
    }

    private fun setupTabLayout() {
        val tlMatches = find<TabLayout>(ui.tlMatches)
        if (tlMatches.tabCount == 0) {
            tlMatches.addTab(tlMatches.newTab().setText(getString(R.string.next_match)))
            tlMatches.addTab(tlMatches.newTab().setText(getString(R.string.past_match)))

            tlMatches.addOnTabSelectedListener(object :
                TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    loadData()
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {}

                override fun onTabReselected(tab: TabLayout.Tab) {}
            })
        }
    }

    private fun setupRecyclerView() {
        eventAdapter = EventAdapter(eventList)
        find<androidx.recyclerview.widget.RecyclerView>(ui.rvEvents).adapter = eventAdapter
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
        val tlMatches = find<TabLayout>(ui.tlMatches)
        if (tlMatches.tabCount > 0) {
            val spLeague = find<Spinner>(ui.spLeague)
            val selectedLeague = spLeague.selectedItem as League?
            val selectedIdLeague: String? = selectedLeague?.idLeague
            if (selectedIdLeague != null) {
                when (tlMatches.selectedTabPosition) {
                    0 -> presenter?.getEventsNextLeague(selectedIdLeague)
                    1 -> presenter?.getEventsPastLeague(selectedIdLeague)
                }
            }
        }
    }

    override fun showAllLeagues(data: List<League>) {
        leagueList.clear()
        leagueList.addAll(data)
        spLeagueAdapter?.notifyDataSetChanged()
    }

    override fun showEventsPastLeague(data: EventResponse) {
        eventList.clear()
        if (!data.events.isNullOrEmpty()) {
            eventList.addAll(data.events)
        }
        eventAdapter?.notifyDataSetChanged()
    }

    override fun showEventsNextLeague(data: EventResponse) {
        eventList.clear()
        if (!data.events.isNullOrEmpty()) {
            eventList.addAll(data.events)
        }
        eventAdapter?.notifyDataSetChanged()
    }

    override fun showLoading() {
        val progressBar:ProgressBar? = find(ui.pbMatches)
        progressBar?.visibility = View.VISIBLE

    }

    override fun hideLoading() {
      //  if(presenter != null) {
            val progressBar: ProgressBar? = find(ui.pbMatches)
            progressBar?.visibility = View.GONE
      //  }
    }

    override fun showErrorDatabase(e: SQLiteConstraintException) {
        toast(e.toString())
    }
}