package id.lilule.football.presenter


import com.google.gson.Gson
import id.lilule.football.api.ApiRepository
import id.lilule.football.api.TheSportsDBApi
import id.lilule.football.mvp.model.Team
import id.lilule.football.mvp.event.EventPresenter
import id.lilule.football.mvp.event.EventView
import id.lilule.football.mvp.model.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class EventPresenterTest {

    private lateinit var presenter: EventPresenter

    @Mock
    private lateinit var view: EventView

    @Mock
    lateinit var apiRepository: ApiRepository

    @Mock
    lateinit var sportsDBApi: TheSportsDBApi

    @Mock
    private lateinit var gson: Gson

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = EventPresenter(view, apiRepository, gson)
    }

    @Test
    fun posTestGetLookupTeam() {
        val id = "1336040"
        val data: MutableList<Team> = mutableListOf()
        val response = TeamResponse(data)

        doAsync {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository.doRequest(sportsDBApi.getLookupTeam(id)), TeamResponse::class.java
                )
            ).thenReturn(response)
            uiThread {

                presenter.getLookupTeam(id, id)

                verify(view).showLoading()
                verify(view).showLookupTeams(response, response)
                verify(view).hideLoading()
            }

        }

    }
}