package com.memo;

import android.view.*;

abstract class ActorEvent extends Actor 
{
  //function for event
  public boolean onTouch(MemoView v, MotionEvent me) {return false;}
}
