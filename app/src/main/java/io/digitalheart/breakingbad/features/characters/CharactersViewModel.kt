package io.digitalheart.breakingbad.features.characters

import android.content.res.Resources
import com.airbnb.mvrx.*
import io.digitalheart.breakingbad.R
import io.digitalheart.breakingbad.core.MvRxViewModel
import io.digitalheart.breakingbad.models.Character
import io.digitalheart.breakingbad.network.BreakingBadService
import io.digitalheart.breakingbad.utils.isMatching
import org.koin.android.ext.android.inject

data class CharactersState(
    val characters: List<Character> = emptyList(),
    val request: Async<List<Character>> = Uninitialized,
    val searchText: String = "",
    val selectedCharacter: Character? = null
) : MvRxState {
    fun filteredCharacters() = characters
        .filter { it.name.isMatching(searchText) || it.appearance.toString().contains(searchText) }
}

class CharactersViewModel(
    initialState: CharactersState,
    private val breakingBadService: BreakingBadService
) : MvRxViewModel<CharactersState>(initialState) {

    fun fetchCharacters() = withState { state ->
        if (state.request is Loading) return@withState

        breakingBadService
            .characters()
            .execute { copy(request = it, characters = it() ?: emptyList()) }
    }

    fun setSearchText(text: String) {
        setState {
            copy(searchText = text)
        }
    }

    fun selectCharacter(character: Character) {
        setState {
            copy(selectedCharacter = character)
        }
    }

    companion object : MvRxViewModelFactory<CharactersViewModel, CharactersState> {
        @JvmStatic
        override fun create(
            viewModelContext: ViewModelContext,
            state: CharactersState
        ): CharactersViewModel {
            val service: BreakingBadService by viewModelContext.activity.inject()
            return CharactersViewModel(state, service)
        }
    }
}

fun Character.formattedOccupation(resources: Resources): String =
    this.occupation.joinToString(separator = resources.getString(R.string.separator))

fun Character.formattedAppearance(resources: Resources): String =
    this.appearance.joinToString(separator = resources.getString(R.string.separator)) {
        resources.getString(
            R.string.season_prefix,
            it
        )
    }
