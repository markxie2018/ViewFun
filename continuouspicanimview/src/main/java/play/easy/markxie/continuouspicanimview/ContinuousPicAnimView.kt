package play.easy.markxie.continuouspicanimview

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import android.animation.ValueAnimator
import android.graphics.Matrix
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.core.content.ContextCompat


class ContinuousPicAnimView(context: Context, attrs: AttributeSet? = null)
    : View(context, attrs) {

    private val TAG = ContinuousPicAnimView::class.java.simpleName

    var resList = listOf<Any>()
        set(value) {
            field = value
            max = value.size - 1
            anim?.removeAllUpdateListeners()
            anim = null
            initAnim()
        }


    var repeatMode = ValueAnimator.RESTART
    var repeatCount = ValueAnimator.INFINITE
    var duration = 300L
    var startDelay = 0L

    private var imageBitmap: Bitmap? = null
    private var imageDrawable: Drawable? = null
    private var anim: ValueAnimator? = null

    private var mWidth: Int = 100
    private var mHeight: Int = 100
    private var max = 0


    init {
        attrs?.let {
            val ta = context.obtainStyledAttributes(attrs, R.styleable.ContinuousPicAnimView)
            duration = ta.getInt(R.styleable.ContinuousPicAnimView_duration, 300).toLong()
            repeatMode = ta.getInt(R.styleable.ContinuousPicAnimView_repeatMode, ValueAnimator.RESTART)
            repeatCount = ta.getInt(R.styleable.ContinuousPicAnimView_repeatCount, ValueAnimator.INFINITE)
            startDelay = ta.getInt(R.styleable.ContinuousPicAnimView_startDelay, 0).toLong()
            ta.recycle()
        }
    }

    private fun initAnim() {
        anim = ValueAnimator.ofInt(0, max).apply {

            duration = this@ContinuousPicAnimView.duration
            startDelay = this@ContinuousPicAnimView.startDelay
            repeatMode = this@ContinuousPicAnimView.repeatMode
            repeatCount = this@ContinuousPicAnimView.repeatCount
            addUpdateListener {
                val index = it.animatedValue as Int
                if (resList.isNotEmpty()) {

                    when (resList[index]) {
                        is Int -> {

                            imageDrawable = ContextCompat.getDrawable(context, resList[index] as Int)

                            imageDrawable?.let { d ->

                                val w = d.minimumWidth
                                val h = d.minimumHeight

                                val scale = mWidth.toFloat() / w

                                imageDrawable?.setBounds(
                                        0,
                                        0,
                                        w * scale.toInt(),
                                        h * scale.toInt())
                            }

                        }
                        is Bitmap -> {

                            imageBitmap = resList[index] as Bitmap

                        }

                        else -> {
                            anim?.removeAllUpdateListeners()
                            Log.e(TAG, "inputType is not support!!!")
                        }
                    }

                    invalidate()
                }

            }

            setEvaluator { fraction, startValue, endValue ->
                Math.round(((endValue as Int) - (startValue as Int)) * fraction)
            }
            start()
        }

    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = MeasureSpec.getSize(widthMeasureSpec)
        mHeight = MeasureSpec.getSize(heightMeasureSpec)

    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (resList[0] is Bitmap) {
            resList = resList.map {
                getResizedBitmap(it as Bitmap, mWidth, mHeight)
            }
        }
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        imageBitmap?.let {
            canvas.drawBitmap(it, 0f, 0f, null)
        }

        imageDrawable?.draw(canvas)


    }

    fun removeAllUpdateListeners() {
        anim?.removeAllUpdateListeners()
    }

    private fun getResizedBitmap(bm: Bitmap, newWidth: Int, newHeight: Int): Bitmap {
        val width = bm.width
        val height = bm.height

        val scaleWidth = newWidth.toFloat() / width
//        val scaleHeight = newHeight.toFloat() / height
        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleWidth)
        val resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false)
        bm.recycle()
        return resizedBitmap
    }

}