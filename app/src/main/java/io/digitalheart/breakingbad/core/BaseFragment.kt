package io.digitalheart.breakingbad.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.airbnb.epoxy.EpoxyRecyclerView
import com.airbnb.mvrx.BaseMvRxFragment
import io.digitalheart.breakingbad.R

abstract class BaseFragment : BaseMvRxFragment() {

    protected lateinit var recyclerView: EpoxyRecyclerView
    protected val epoxyController by lazy { epoxyController() }

    val actionBar by lazy { (activity as AppCompatActivity).supportActionBar }
    val toolbar by lazy { activity?.findViewById<Toolbar>(R.id.toolbar) }

    protected open fun setupToolbar() {}
    protected open fun clearToolbar() {}
    protected open fun setupRecyclerView() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        epoxyController.onRestoreInstanceState(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_base, container, false).apply {
            recyclerView = findViewById(R.id.recyclerView)
            setupRecyclerView()
            recyclerView.setController(epoxyController)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
    }

    override fun invalidate() {
        recyclerView.requestModelBuild()
    }

    abstract fun epoxyController(): MvRxEpoxyController

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        epoxyController.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        epoxyController.cancelPendingModelBuild()
        clearToolbar()
        super.onDestroyView()
    }
}