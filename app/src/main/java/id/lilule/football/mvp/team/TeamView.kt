package id.lilule.football.mvp.team

import android.database.sqlite.SQLiteConstraintException
import id.lilule.football.mvp.model.PlayerResponse

interface TeamView {
    fun showLoading()
    fun hideLoading()
    fun showPlayer(data: PlayerResponse)
    fun showInsertResult(result: Boolean)
    fun showDeleteResult(result: Boolean)
    fun setFavorite(isFavorite: Boolean)
    fun showErrorDatabase(e: SQLiteConstraintException)
}