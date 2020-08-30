package io.digitalheart.breakingbad.features.characters

import io.digitalheart.breakingbad.models.Character
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldNotContain
import org.junit.Test

class CharactersStateTest {

    @Test
    fun `should be no characters at start`() {
        val state = CharactersState()
        state.characters shouldEqual emptyList()
    }

    @Test
    fun `should be correct characters`() {
        val state = CharactersState(characters = characters)

        state.characters.size shouldEqual 5
        state.characters[0] shouldEqual walterWhite
        state.characters[1] shouldEqual jessePinkman
        state.characters[2] shouldEqual skylerWhite
        state.characters[3] shouldEqual mikeEhrmantraut
        state.characters[4] shouldEqual tucoSalamance
    }

    @Test
    fun `should filter by season`() {
        val season1State = CharactersState(characters = characters, searchText = "1")
        val season4State = CharactersState(characters = characters, searchText = "4")

        season1State.filteredCharacters().size shouldEqual 4
        season1State.filteredCharacters() shouldNotContain mikeEhrmantraut

        season4State.filteredCharacters().size shouldEqual 4
        season4State.filteredCharacters() shouldNotContain tucoSalamance
    }

    @Test
    fun `should search intelligently by name even if typos)`() {
        val jasyPinkmannState =
            CharactersState(characters = characters, searchText = "jasy pinkmann")
        val waltWitheState = CharactersState(characters = characters, searchText = "walt  withe")

        jasyPinkmannState.filteredCharacters().size shouldEqual 1
        jasyPinkmannState.filteredCharacters() shouldContain jessePinkman

        waltWitheState.filteredCharacters().size shouldEqual 1
        waltWitheState.filteredCharacters() shouldContain walterWhite
    }

    @Test
    fun `should search by name's initials`() {
        val swState = CharactersState(characters = characters, searchText = "SW")
        val jpState = CharactersState(characters = characters, searchText = "jp")

        swState.filteredCharacters().size shouldEqual 1
        swState.filteredCharacters() shouldContain skylerWhite

        jpState.filteredCharacters().size shouldEqual 1
        jpState.filteredCharacters() shouldContain jessePinkman
    }

    @Test
    fun `should search by part of the name`() {
        val whiState = CharactersState(characters = characters, searchText = "whi")
        val mState = CharactersState(characters = characters, searchText = "m")

        whiState.filteredCharacters().size shouldEqual 2
        whiState.filteredCharacters() shouldContain walterWhite
        whiState.filteredCharacters() shouldContain skylerWhite

        mState.filteredCharacters().size shouldEqual 3
        mState.filteredCharacters() shouldContain jessePinkman
        mState.filteredCharacters() shouldContain mikeEhrmantraut
        mState.filteredCharacters() shouldContain tucoSalamance
    }

    companion object {
        private val walterWhite =
            Character(name = "Walter White", appearance = listOf(1, 2, 3, 4, 5))
        private val jessePinkman =
            Character(name = "Jesse Pinkman", appearance = listOf(1, 2, 3, 4, 5))
        private val skylerWhite =
            Character(name = "Skyler White", appearance = listOf(1, 2, 3, 4, 5))
        private val mikeEhrmantraut =
            Character(name = "Mike Ehrmantraut", appearance = listOf(2, 3, 4, 5))
        private val tucoSalamance = Character(name = "Tuco Salamanca", appearance = listOf(1, 2))

        private val characters =
            listOf(walterWhite, jessePinkman, skylerWhite, mikeEhrmantraut, tucoSalamance)
    }
}