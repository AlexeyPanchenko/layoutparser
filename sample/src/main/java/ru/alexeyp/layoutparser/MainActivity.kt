package ru.alexeyp.layoutparser

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ru.alexeyp.layoutparser.layoutparser.ActivityMainInflater


class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(ActivityMainInflater(this).rootInflaterView)
  }
}
