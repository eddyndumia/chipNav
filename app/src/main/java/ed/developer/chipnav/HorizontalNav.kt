package ed.developer.chipnav

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.TextView
import ed.developer.chipnav.util.colorAnimation
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

import com.ismaeldivita.chipnavigation.ChipNavigationBar

class HorizontalNav : AppCompatActivity() {

    private val container by lazy { findViewById<View>(R.id.container) }
    private val title by lazy { findViewById<TextView>(R.id.title) }
    private val menu by lazy { findViewById<ChipNavigationBar>(R.id.bottom_menu) }

    private var lastColor: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horizontal)

        lastColor = (container.background as ColorDrawable).color

        menu.setOnItemSelectedListener { id ->
            val option = when (id) {
                R.id.home -> R.color.home to "Home"
                R.id.activity -> R.color.activity to "Activity"
                R.id.favorites -> R.color.favorites to "Favorites"
                R.id.settings -> R.color.settings to "Settings"
                else -> R.color.white to ""
            }
            val color = ContextCompat.getColor(this@HorizontalNav, option.first)
            container.colorAnimation(lastColor, color)
            lastColor = color

            title.text = option.second
        }


        if (savedInstanceState == null) {
            menu.showBadge(R.id.home)
            menu.showBadge(R.id.settings, 32)
        }
    }
}
