package play.easy.markxie.viewfun

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import play.easy.markxie.continuouspicanimview.ContinuousPicAnimView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val resInput = mutableListOf(
                R.mipmap.ic_b1,
                R.mipmap.ic_b2,
                R.mipmap.ic_b3,
                R.mipmap.ic_b4,
                R.mipmap.ic_b5,
                R.mipmap.ic_b6,
                R.mipmap.ic_b7)

        val bitmapInput = resInput.mapNotNull {
            BitmapFactory.decodeResource(resources, it)
        }

        cv.resList = resInput.toMutableList()
        //or
        //cv.resList = bitmapInput.toMutableList()


    }
}
