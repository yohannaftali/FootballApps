package id.lilule.football.mvp.search

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.SearchView
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import com.google.gson.Gson
import id.lilule.football.R
import id.lilule.football.api.ApiRepository
import id.lilule.football.mvp.item.EventAdapter
import id.lilule.football.mvp.item.TeamAdapter
import id.lilule.football.mvp.model.Event
import id.lilule.football.mvp.model.Team
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView


class SearchActivity : AppCompatActivity(), SearchActivityView {

    private val ui = SearchActivityUI
    private var type: Int = 0
    private var eventList: MutableList<Event> = mutableListOf()
    private var teamList: MutableList<Team> = mutableListOf()
    private var presenter: SearchPresenter? = null
    private var eventAdapter: EventAdapter? = null
    private var teamAdapter: TeamAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        type = intent.getIntExtra(getString(R.string.intent_search), 0)

        presenter = SearchPresenter(this, ApiRepository(), Gson())

        setupUI()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_search, menu)

        val miSearch: MenuItem = menu.findItem(R.id.menu_search_action)
        setSearchManager(miSearch)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupUI() {
        SearchActivityUI().setContentView(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val rvSearch: androidx.recyclerview.widget.RecyclerView? = find(ui.rvSearch)
        when (type) {
            0 -> {
                eventAdapter = EventAdapter(eventList)
                rvSearch?.adapter = eventAdapter
            }
            1 -> {
                teamAdapter = TeamAdapter(teamList)
                rvSearch?.adapter = teamAdapter
            }
        }
    }

    private fun setSearchManager(menuItem: MenuItem) {
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager?

        if (searchManager != null) {
            val searchView = menuItem.actionView as SearchView

            var queryHint: String = getString(R.string.search_hint)
            when (type) {
                0 -> queryHint = getString(R.string.search_match)
                1 -> queryHint = getString(R.string.search_team)
            }

            searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
            searchView.queryHint = queryHint
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    if (!TextUtils.isEmpty(query)) {
                        println(type)
                        when (type) {
                            0 -> presenter?.getSearchEvents(query)
                            1 -> presenter?.getSearchTeams(query)
                        }
                        return true
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    return false
                }
            })

            searchView.setIconifiedByDefault(true)
            menuItem.expandActionView()
            menuItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
                override fun onMenuItemActionExpand(menuItem: MenuItem): Boolean {
                    return true
                }

                override fun onMenuItemActionCollapse(menuItem: MenuItem): Boolean {
                    finish()
                    return true
                }
            })
        }
    }

    override fun showSearchEventResult(data: List<Event>) {
        println("show search")
        eventList.clear()
        if (!data.isNullOrEmpty()) {
            eventList.addAll(data)
        }
        eventAdapter?.notifyDataSetChanged()
    }

    override fun showSearchTeamsResult(data: List<Team>) {
        println("show search team")
        teamList.clear()
        if (!data.isNullOrEmpty()) {
            teamList.addAll(data)
        }
        teamAdapter?.notifyDataSetChanged()
    }

    override fun showLoading() {
        find<ProgressBar>(ui.pbSearch).visibility = View.VISIBLE
    }

    override fun hideLoading() {
        find<ProgressBar>(ui.pbSearch).visibility = View.GONE
    }
}