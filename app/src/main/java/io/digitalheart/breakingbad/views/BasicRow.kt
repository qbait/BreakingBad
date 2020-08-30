package io.digitalheart.breakingbad.views

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import io.digitalheart.breakingbad.R
import io.digitalheart.breakingbad.utils.visible

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class BasicRow @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val headingView: TextView
    private val titleView: TextView
    private val subtitleView: TextView

    init {
        inflate(context, R.layout.row_basic, this)
        headingView = findViewById(R.id.heading)
        titleView = findViewById(R.id.title)
        subtitleView = findViewById(R.id.subtitle)
        orientation = VERTICAL
    }

    @TextProp
    fun setHeading(heading: CharSequence?) {
        headingView.visible = !heading.isNullOrBlank()
        headingView.text = heading
    }

    @TextProp
    fun setTitle(title: CharSequence) {
        titleView.text = title
    }

    @TextProp
    fun setSubtitle(subtitle: CharSequence?) {
        subtitleView.visible = !subtitle.isNullOrBlank()
        subtitleView.text = subtitle
    }

    @CallbackProp
    fun setClickListener(clickListener: OnClickListener?) {
        setOnClickListener(clickListener)
    }
}