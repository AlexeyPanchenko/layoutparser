package ru.alexeyp.layoutparser.q;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import ru.alexeyp.layoutparser.R;

public class Inflater {

  public final Button btn_first;
  public final View v;
  public final ConstraintLayout constt;

  public Inflater(Context context) {
    View view = LayoutInflater.from(context).inflate(R.layout.i_file, null);
    btn_first = view.findViewById(R.id.btn_first);
    v = view.findViewById(R.id.all);
    constt = view.findViewById(R.id.constt);
  }
}
