package io.digitalheart.breakingbad.features.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.mvrx.activityViewModel
import io.digitalheart.breakingbad.R
import io.digitalheart.breakingbad.core.BaseFragment
import io.digitalheart.breakingbad.core.simpleController
import io.digitalheart.breakingbad.utils.snack
import io.digitalheart.breakingbad.views.characterCell
import io.digitalheart.breakingbad.views.loadingRow

class CharacterIndexFragment : BaseFragment() {

    private val viewModel: CharactersViewModel by activityViewModel()

    private val searchEditText by lazy {
        (LayoutInflater.from(context).inflate(R.layout.view_search, null, false) as EditText)
            .apply {
                addTextChangedListener {
                    viewModel.setSearchText(it.toString())
                }
            }
    }

    override fun setupToolbar() {
        super.setupToolbar()
        actionBar?.setDisplayShowTitleEnabled(false)
        toolbar?.addView(searchEditText)
    }

    override fun clearToolbar() {
        super.clearToolbar()
        actionBar?.setDisplayShowTitleEnabled(true)
        toolbar?.removeView(searchEditText)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.asyncSubscribe(CharactersState::request, onFail = { error ->
            error.message?.let { view.snack(it) }
        })

    }

    override fun epoxyController() = simpleController(viewModel) { state ->

        if (state.characters.isEmpty()) {
            loadingRow {
                id("loading")
            }
            viewModel.fetchCharacters()
            return@simpleController
        }

        state.filteredCharacters().forEach { character ->
            characterCell {
                id(character.id)
                name(character.name)
                imageUrl(character.imageUrl)
                clickListener { _ ->
                    viewModel.selectCharacter(character)
                    findNavController().navigate(R.id.action_to_character_details)
                }
            }
        }
    }

    override fun setupRecyclerView() {
        super.setupRecyclerView()
        recyclerView.layoutManager =
            GridLayoutManager(activity, resources.getInteger(R.integer.grid_columns))
    }
}