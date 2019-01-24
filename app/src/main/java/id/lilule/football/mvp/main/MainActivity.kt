package id.lilule.football.mvp.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import id.lilule.football.R
import id.lilule.football.mvp.main.favorites.FavoritesFragment
import id.lilule.football.mvp.main.matches.MatchesFragment
import id.lilule.football.mvp.main.teams.TeamsFragment
import id.lilule.football.mvp.search.SearchActivity
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView

class MainActivity : AppCompatActivity() {

    private val ui = MainActivityUI
    private var menu: Menu? = null
    private var bottomNavigationView: BottomNavigationView? = null
    private var viewPager: androidx.viewpager.widget.ViewPager? = null
    private var prevMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)
        bottomNavigationView = find(ui.nvMain)
        viewPager = find(ui.vpMain)
    }

    public override fun onResume() {
        super.onResume()
        setupViewPager()
        setupBottomNavigationView()
        setupViewPagerListener()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)

        val miSearch: MenuItem = menu.findItem(R.id.menu_main_search)
        miSearch.setOnMenuItemClickListener {
            val currentItem = find<androidx.viewpager.widget.ViewPager>(ui.vpMain).currentItem
            val intent = Intent(this, SearchActivity::class.java)
            intent.putExtra(getString(R.string.intent_search), currentItem)
            startActivity(intent)
            return@setOnMenuItemClickListener true
        }
        this.menu = menu

        return true
    }

    private fun setupViewPager() {
        if (viewPager?.adapter == null) {
            val adapter = MainFragmentPagerAdapter(supportFragmentManager)
            adapter.addFragment(MatchesFragment(), getString(R.string.matches))
            adapter.addFragment(TeamsFragment(), getString(R.string.teams))
            adapter.addFragment(FavoritesFragment(), getString(R.string.favorite))
            viewPager?.adapter = adapter
        }
    }

    private fun setupBottomNavigationView() {
        val bottomNavigationView: BottomNavigationView? = find(ui.nvMain)
        val vpMain: androidx.viewpager.widget.ViewPager? = find(ui.vpMain)
        if (vpMain != null && bottomNavigationView != null) {
            bottomNavigationView.setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    ui.btMatches -> {
                        vpMain.currentItem = 0
                    }
                    ui.btTeams -> {
                        vpMain.currentItem = 1
                    }
                    ui.btFavorite -> {
                        vpMain.currentItem = 2
                    }
                }
                return@setOnNavigationItemSelectedListener true
            }
        }
    }

    private fun setupViewPagerListener() {
        val vpMain: androidx.viewpager.widget.ViewPager? = find(ui.vpMain)

        vpMain?.addOnPageChangeListener(object : androidx.viewpager.widget.ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                if (prevMenuItem != null) prevMenuItem?.isChecked = false
                else bottomNavigationView?.menu?.getItem(0)?.isChecked = false
                bottomNavigationView?.menu?.getItem(position)?.isChecked = true
                prevMenuItem = bottomNavigationView?.menu?.getItem(position)
                when (position) {
                    0, 1 -> menu?.setGroupVisible(R.id.group_with_favorite, true)
                    2 -> menu?.setGroupVisible(R.id.group_with_favorite, false)
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }
}
