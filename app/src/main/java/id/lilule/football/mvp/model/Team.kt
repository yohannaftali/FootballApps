package id.lilule.football.mvp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(
    val id: Long?,
    val idTeam: String? = null,
    val strTeam: String? = null,
    val strStadium: String? = null,
    val intFormedYear: String? = null,
    val strDescriptionEN: String? = null,
    val strTeamBadge: String? = null,
    val strLeague: String? = null,
    val strAlternate: String? = null

) : Parcelable {
    companion object {
        const val ID: String = "id"
        const val ID_TEAM: String = "idTeam"
        const val STR_TEAM: String = "strTeam"
        const val STR_STADIUM: String = "strStadium"
        const val INT_FORMED_YEAR: String = "intFormedYear"
        const val STR_DESCRIPTION_EN: String = "strDescriptionEN"
        const val STR_TEAM_BADGE: String = "strTeamBadge"
        const val STR_LEAGUE: String = "strLeague"
        const val STR_ALTERNATE: String = "strAlternate"
    }
}