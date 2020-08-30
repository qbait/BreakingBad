package io.digitalheart.breakingbad.views

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import io.digitalheart.breakingbad.R
import io.digitalheart.breakingbad.utils.loadFromUrl
import io.digitalheart.breakingbad.utils.setSelectableBackground

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT, fullSpan = false)
class CharacterCell @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val nameView: TextView
    private val imageView: ImageView

    init {
        inflate(context, R.layout.cell_character, this)
        nameView = findViewById(R.id.name)
        imageView = findViewById(R.id.image)
        setSelectableBackground()
    }

    @TextProp
    fun setName(name: CharSequence?) {
        nameView.text = name
    }

    @ModelProp
    fun setImageUrl(url: String) {
        imageView.loadFromUrl(url)
    }

    @CallbackProp
    fun setClickListener(clickListener: OnClickListener?) {
        setOnClickListener(clickListener)
    }
}