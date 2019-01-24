package id.lilule.football.mvp.model

data class League(
    val idLeague: String?,
    val strLeague: String?,
    val strSport: String?,
    val strLeagueAlternate: String?
) {
    override fun toString(): String {
        return strLeague.toString()
    }
}
