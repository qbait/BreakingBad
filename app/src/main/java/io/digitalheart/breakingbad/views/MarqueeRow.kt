package io.digitalheart.breakingbad.views

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.TextView
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import io.digitalheart.breakingbad.R

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class MarqueeRow @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val titleView: TextView

    init {
        inflate(context, R.layout.row_marquee, this)
        titleView = findViewById(R.id.title)
    }

    @TextProp
    fun setTitle(title: CharSequence) {
        titleView.text = title
    }
}