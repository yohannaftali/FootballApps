package id.lilule.football.mvp.event

import android.database.sqlite.SQLiteConstraintException
import id.lilule.football.mvp.model.TeamResponse

interface EventView {
    fun showLoading()
    fun hideLoading()
    fun showLookupTeams(dataHome: TeamResponse, dataAway: TeamResponse)
    fun showInsertResult(result: Boolean)
    fun showDeleteResult(result: Boolean)
    fun setFavorite(isFavorite: Boolean)
    fun showErrorDatabase(e: SQLiteConstraintException)
}