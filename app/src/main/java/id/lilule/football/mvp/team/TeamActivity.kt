package id.lilule.football.mvp.team

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import id.lilule.football.R
import id.lilule.football.api.ApiRepository
import id.lilule.football.mvp.model.Player
import id.lilule.football.mvp.model.PlayerResponse
import id.lilule.football.mvp.model.Team
import id.lilule.football.util.Constants.OBJECT_TEAM
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.toast


class TeamActivity : AppCompatActivity(), TeamView {

    private lateinit var team: Team
    private val ui = TeamActivityUI
    private var playerList: MutableList<Player> = mutableListOf()
    private var miFavorite: MenuItem? = null
    private lateinit var presenter: TeamPresenter
    private var isFavorite = false
    private lateinit var playerAdapter: PlayerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = TeamPresenter(this, ApiRepository(), Gson())

        setupUI()
        setupData()
    }

    private fun setupUI() {
        TeamActivityUI().setContentView(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupRecyclerView()
        setupTabLayout()
    }

    private fun setupData() {
        val intent = intent
        val team: Team = intent.getParcelableExtra(OBJECT_TEAM)
        println(team.idTeam)
        if (!team.idTeam.isNullOrEmpty()) {
            this.team = team
            find<TextView>(ui.tvTeam).text = team.strTeam
            find<TextView>(ui.tvStadium).text = team.strStadium
            find<TextView>(ui.tvFormedYear).text = team.intFormedYear
            find<TextView>(ui.tvDescriptionEN).text = team.strDescriptionEN

            val ivTeamBadgeDetail = find<ImageView>(ui.ivTeamBadgeDetail)
            Picasso.get()
                .load(team.strTeamBadge)
                .into(ivTeamBadgeDetail)

            presenter.getPlayers(team.idTeam)

            supportActionBar?.title = team.strLeague
            supportActionBar?.subtitle = team.strAlternate
        } else {
            supportActionBar?.title = getString(R.string.error_no_data)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_team, menu)
        miFavorite = menu.findItem(R.id.mi_favorite_team)
        presenter.queryFavorite(this.applicationContext, team)
        return super.onCreateOptionsMenu(menu)
    }

    private fun setupTabLayout() {
        val tlTeam = find<TabLayout>(ui.tlTeam)
        tlTeam.addTab(tlTeam.newTab().setText(getString(R.string.overview)))
        tlTeam.addTab(tlTeam.newTab().setText(getString(R.string.players)))

        tlTeam.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tlTeam.selectedTabPosition) {
                    0 -> showOverview()
                    1 -> showRecyclerViewPlayer()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }

        })
    }

    private fun setupRecyclerView() {
        playerAdapter = PlayerAdapter(playerList)
        find<androidx.recyclerview.widget.RecyclerView>(ui.rvPlayers).adapter = playerAdapter
    }

    private fun showOverview() {
        val svTeamOverview = find<ScrollView>(ui.svTeamOverview)
        val svTeamPlayers = find<ScrollView>(ui.svTeamPlayers)
        svTeamOverview.visibility = View.VISIBLE
        svTeamPlayers.visibility = View.GONE
    }

    private fun showRecyclerViewPlayer() {
        if (!team.idTeam.isNullOrEmpty()) {
            val svTeamOverview = find<ScrollView>(ui.svTeamOverview)
            val svTeamPlayers = find<ScrollView>(ui.svTeamPlayers)
            svTeamOverview.visibility = View.GONE
            svTeamPlayers.visibility = View.VISIBLE
        }
    }

    override fun setFavorite(isFavorite: Boolean) {
        this.isFavorite = isFavorite
        if (this.isFavorite) {
            miFavorite?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star)
        } else {
            miFavorite?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_border)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.mi_favorite_team -> {
                if (isFavorite) {
                    presenter.deleteFavorites(this.applicationContext, team)
                } else {
                    presenter.insertFavorites(this.applicationContext, team)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showPlayer(data: PlayerResponse) {
        playerList.clear()
        if (!data.player.isNullOrEmpty()) {
            playerList.addAll(data.player)
        }
        playerAdapter.notifyDataSetChanged()
    }

    override fun showLoading() {
        find<ProgressBar>(ui.pbTeam).visibility = View.VISIBLE
    }

    override fun hideLoading() {
        find<ProgressBar>(ui.pbTeam).visibility = View.GONE
    }

    override fun showErrorDatabase(e: SQLiteConstraintException) {
        toast(e.toString())
    }

    override fun showInsertResult(result: Boolean) {
        if (result) {
            setFavorite(true)
            toast(getString(R.string.add_favorite))
        }
    }

    override fun showDeleteResult(result: Boolean) {
        if (result) {
            setFavorite(false)
            toast(getString(R.string.delete_favorite))
        }
    }
}