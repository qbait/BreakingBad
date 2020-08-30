package io.digitalheart.breakingbad.features.characters

import com.airbnb.mvrx.activityViewModel
import io.digitalheart.breakingbad.R
import io.digitalheart.breakingbad.core.BaseFragment
import io.digitalheart.breakingbad.core.simpleController
import io.digitalheart.breakingbad.views.basicRow
import io.digitalheart.breakingbad.views.imageRow
import io.digitalheart.breakingbad.views.marqueeRow

class CharacterDetailsFragment : BaseFragment() {

    private val viewModel: CharactersViewModel by activityViewModel()

    override fun epoxyController() = simpleController(viewModel) { state ->

        state.selectedCharacter?.let {
            imageRow {
                id("row_image")
                imageUrl(it.imageUrl)
            }
            marqueeRow {
                id("name")
                title(it.name)
            }

            basicRow {
                id("occupation")
                title(it.formattedOccupation(resources))
                subtitle(getString(R.string.occupation))
            }

            basicRow {
                id("status")
                title(it.status)
                subtitle(getString(R.string.status))
            }

            basicRow {
                id("nickname")
                title(it.nickname)
                subtitle(getString(R.string.nickname))
            }

            basicRow {
                id("appearance")
                title(it.formattedAppearance(resources))
                subtitle(getString(R.string.season_appearance))
            }
        }
    }
}