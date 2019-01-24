package id.lilule.football.mvp.event

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import id.lilule.football.R
import id.lilule.football.api.ApiRepository
import id.lilule.football.mvp.model.Event
import id.lilule.football.mvp.model.TeamResponse
import id.lilule.football.util.Constants.OBJECT_EVENT
import id.lilule.football.util.Date.dateIsoGMTToLong
import id.lilule.football.util.Date.timeGMTToLocale
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.toast


class EventActivity : AppCompatActivity(), EventView {

    private lateinit var event: Event
    private val ui = EventActivityUI
    private var miFavorite: MenuItem? = null
    private lateinit var presenter: EventPresenter
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = EventPresenter(this, ApiRepository(), Gson())

        setupUI()
        setupData()
    }

    private fun setupUI() {
        EventActivityUI().setContentView(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupData() {
        val intent = intent
        val event: Event = intent.getParcelableExtra(OBJECT_EVENT)
        if (!event.dateEvent.isNullOrEmpty()) {
            this.event = event
            find<TextView>(ui.tvEventDate).text = dateIsoGMTToLong(event.dateEvent, event.strTime!!)
            find<TextView>(ui.tvEventTime).text = timeGMTToLocale(event.strTime)
            find<TextView>(ui.tvHomeTeam).text = event.strHomeTeam
            find<TextView>(ui.tvHomeScore).text = event.intHomeScore
            find<TextView>(ui.tvAwayTeam).text = event.strAwayTeam
            find<TextView>(ui.tvAwayScore).text = event.intAwayScore

            find<TextView>(ui.tvHomeFormation).text = event.strHomeFormation
            find<TextView>(ui.tvHomeGoalDetails).text = event.strHomeGoalDetails
            find<TextView>(ui.tvHomeShots).text = event.intHomeShots
            find<TextView>(ui.tvHomeRedCards).text = event.strHomeRedCards
            find<TextView>(ui.tvHomeYellowCards).text = event.strHomeYellowCards
            find<TextView>(ui.tvHomeLineupGoalKeeper).text = event.strHomeLineupGoalkeeper
            find<TextView>(ui.tvHomeLineupDefense).text = event.strHomeLineupDefense
            find<TextView>(ui.tvHomeLineupMidfield).text = event.strHomeLineupMidfield
            find<TextView>(ui.tvHomeLineupForward).text = event.strHomeLineupForward
            find<TextView>(ui.tvHomeLineupSubstitutes).text = event.strHomeLineupSubstitutes

            find<TextView>(ui.tvAwayFormation).text = event.strAwayFormation
            find<TextView>(ui.tvAwayGoalDetails).text = event.strAwayGoalDetails
            find<TextView>(ui.tvAwayShots).text = event.intAwayShots
            find<TextView>(ui.tvAwayRedCards).text = event.strAwayRedCards
            find<TextView>(ui.tvAwayYellowCards).text = event.strAwayYellowCards
            find<TextView>(ui.tvAwayLineupGoalKeeper).text = event.strAwayLineupGoalkeeper
            find<TextView>(ui.tvAwayLineupDefense).text = event.strAwayLineupDefense
            find<TextView>(ui.tvAwayLineupMidfield).text = event.strAwayLineupMidfield
            find<TextView>(ui.tvAwayLineupForward).text = event.strAwayLineupForward
            find<TextView>(ui.tvAwayLineupSubstitutes).text = event.strAwayLineupSubstitutes

            presenter.getLookupTeam(event.idHomeTeam, event.idAwayTeam)

            supportActionBar?.title = event.strLeague
            supportActionBar?.subtitle = event.strEvent
        } else {
            supportActionBar?.title = getString(R.string.error_no_data)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_event, menu)
        miFavorite = menu.findItem(R.id.mi_favorite_event)
        presenter.queryFavorite(this.applicationContext, event)
        return super.onCreateOptionsMenu(menu)
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
            R.id.mi_favorite_event -> {
                if (isFavorite) {
                    presenter.deleteFavorites(this.applicationContext, event)
                } else {
                    presenter.insertFavorites(this.applicationContext, event)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showLookupTeams(dataHome: TeamResponse, dataAway: TeamResponse) {
        val home = dataHome.teams[0]
        val away = dataAway.teams[0]

        val imgHomeBadge = find<ImageView>(ui.ivHomeBadge)
        Picasso.get()
            .load(home.strTeamBadge)
            .into(imgHomeBadge)

        val imgAwayBadge = find<ImageView>(ui.ivAwayBadge)
        Picasso.get()
            .load(away.strTeamBadge)
            .into(imgAwayBadge)
    }

    override fun showLoading() {
        find<ProgressBar>(ui.pbDetail).visibility = View.VISIBLE
    }

    override fun hideLoading() {
        find<ProgressBar>(ui.pbDetail).visibility = View.GONE
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