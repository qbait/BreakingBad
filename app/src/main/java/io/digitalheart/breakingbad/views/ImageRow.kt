package io.digitalheart.breakingbad.views

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ImageView
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import io.digitalheart.breakingbad.R
import io.digitalheart.breakingbad.utils.loadFromUrl

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class ImageRow @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val imageView: ImageView

    init {
        inflate(context, R.layout.row_image, this)
        imageView = findViewById(R.id.image)
    }

    @ModelProp
    fun setImageUrl(url: String) {
        imageView.loadFromUrl(url)
    }
}