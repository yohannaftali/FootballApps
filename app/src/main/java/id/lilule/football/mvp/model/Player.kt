package id.lilule.football.mvp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Player(
    val idPlayer: String? = null,
    val idTeam: String? = null,
    val strNationality: String? = null,
    val strPlayer: String? = null,
    val strTeam: String? = null,
    val strSport: String? = null,
    val strHeight: String? = null,
    val strWeight: String? = null,
    val strPosition: String? = null,
    val strDescriptionEN: String? = null,
    val strThumb: String? = null,
    val strCutout: String? = null,
    val strFanart1: String? = null

) : Parcelable