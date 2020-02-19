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

        val resInput = mutableListOf(R.mipmap.ic_b1, R.mipmap.ic_b2, R.mipmap.ic_b3,
                R.mipmap.ic_b4, R.mipmap.ic_b5, R.mipmap.ic_b6, R.mipmap.ic_b7)

        val bitmapInput = resInput.mapNotNull {
            BitmapFactory.decodeResource(resources, it)
        }

        val resInput2 = mutableListOf(R.mipmap.ic_a1, R.mipmap.ic_a2, R.mipmap.ic_a3)

        val errorInput = mutableListOf("1", "2", "3")


        val custom = ContinuousPicAnimView(this)
        custom.resList = bitmapInput.toMutableList()
//        linear.addView(custom)
        cv.resList = bitmapInput.toMutableList()

        tv_hello.setOnClickListener {
            cv.resList = resInput2
        }

    }
}
