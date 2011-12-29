package com.memo;

import android.graphics.*;
import android.content.*;
import android.view.*;
import android.view.View.*;
import android.app.Activity;

public class SplashScreen extends MemoView implements OnTouchListener
{
  SplashBG bg;
  public SplashScreen(Context context, MemoView mv)
  {
    super(context);
    bg = new SplashBG(context, mv);
    addActor(bg); 
    this.setOnTouchListener(this);
  }

  public boolean onTouch(View v, MotionEvent me)
  {
    if(me.getAction() != MotionEvent.ACTION_DOWN)
      return false;

    if( bg.onTouch(me) == true)
    {
      removeActor(bg);
      bg = null;
    }

    return false;
  }
}
  
class SplashBG extends ActorEvent
{
  private static Bitmap texture = null;
  private static final String name = "splash";
  private Context context;
  private MemoView mv;
  
  public SplashBG(Context context, MemoView mv)
  {
    this.context = context;
    this.mv = mv;
    if(texture == null)
      texture = this.loadGraphic(name)[0];
  }

  public void draw(Canvas canvas)
  {
    canvas.drawBitmap(texture, 0, 0, null);
  }

  public boolean onTouch(MotionEvent me)
  {
    if( me.getAction() != MotionEvent.ACTION_DOWN)
      return false;

    destroy();
    ((Activity)context).setContentView(mv);
    return true;
  }
}
