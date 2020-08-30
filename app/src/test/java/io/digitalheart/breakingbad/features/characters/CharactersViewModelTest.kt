package io.digitalheart.breakingbad.features.characters

import android.content.res.Resources
import android.os.Build
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.withState
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.digitalheart.breakingbad.models.Character
import io.digitalheart.breakingbad.network.BreakingBadService
import io.digitalheart.breakingbad.utils.RxSchedulersOverrideRule
import io.reactivex.Single
import io.reactivex.subjects.SingleSubject
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldEqual
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.standalone.inject
import org.koin.test.AutoCloseKoinTest
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.P])
@RunWith(RobolectricTestRunner::class)
class CharactersViewModelTest : AutoCloseKoinTest() {

    @get:Rule
    val rxSchedulersOverrideRule = RxSchedulersOverrideRule()

    private val service: BreakingBadService = mock()
    private val resources: Resources by inject()

    private lateinit var viewModel: CharactersViewModel
    private val characters: List<Character> = emptyList()

    @Test
    fun `should fetch characters successfully`() {
        val charactersSubject = SingleSubject.create<List<Character>>()
        whenever(service.characters()).thenReturn(charactersSubject)

        viewModel = CharactersViewModel(CharactersState(), service)
        viewModel.fetchCharacters()

        verify(service).characters()

        withState(viewModel) {
            (it.request is Loading) shouldBe true
        }

        charactersSubject.onSuccess(characters)

        withState(viewModel) {
            (it.request is Success) shouldBe true
            it.request() shouldEqual characters
        }
    }

    @Test
    fun `should fail on fetching characters`() {
        whenever(service.characters()).thenReturn(Single.error(Exception("Server not found")))

        viewModel = CharactersViewModel(CharactersState(), service)
        viewModel.fetchCharacters()

        verify(service).characters()

        withState(viewModel) {
            (it.request is Fail) shouldBe true
            it.request() shouldEqual null
        }
    }

    @Test
    fun `should format occupation`() {
        val noOccupationCharacter = Character(occupation = emptyList())
        val singleOccupationCharacter = Character(occupation = listOf("Developer"))
        val multipleOccupationCharacter = Character(occupation = listOf("Developer", "Drug Dealer"))

        noOccupationCharacter.formattedOccupation(resources) shouldEqual ""
        singleOccupationCharacter.formattedOccupation(resources) shouldEqual "Developer"
        multipleOccupationCharacter.formattedOccupation(resources) shouldEqual "Developer, Drug Dealer"
    }

    @Test
    fun `should format season appearance`() {
        val noAppearanceCharacter = Character(appearance = emptyList())
        val singleAppearanceCharacter = Character(appearance = listOf(2))
        val multipleAppearanceCharacter = Character(appearance = listOf(1, 2, 4))

        noAppearanceCharacter.formattedAppearance(resources) shouldEqual ""
        singleAppearanceCharacter.formattedAppearance(resources) shouldEqual "S2"
        multipleAppearanceCharacter.formattedAppearance(resources) shouldEqual "S1, S2, S4"
    }

}