# Preview
# Gradle：
```
Add it in your root build.gradle at the end of repositories:

allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
Add the dependency

dependencies {
       implementation 'com.github.markxie2018:ViewFun:v1.1'
}
```
# Usage

in xml
```
 <play.easy.markxie.continuouspicanimview.ContinuousPicAnimView
        android:id="@+id/cv"
        android:layout_width="200dp"
        android:layout_height="match_parent"
        app:duration="5000"
        app:repeatCount="-1"
        app:repeatMode="reverse"
        app:startDelay="500" />
```

in acivity/fragment
```
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
```

