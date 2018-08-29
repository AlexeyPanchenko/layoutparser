package ru.alexeyp.layoutparser

import android.os.Bundle
import android.support.v7.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    //setContentView(RActivityMainInflater(this).rootInflaterView)
    setContentView(R.layout.r_activity_main)

  }
}
