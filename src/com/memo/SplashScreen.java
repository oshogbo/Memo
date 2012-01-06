// Where is Memo?
// code by oshogbo//vx
// http://oshogbo.vexillium.org
// http://vexillium.org
//
// Version: ALPHA 2011-01-06
//
// LICENSE
// Permission is hereby granted to use, copy, modify, and distribute this
// source code, or portions hereof, for any purpose, without fee, subject
// to the following restrictions:
// 
// 1. The origin of this source code must not be misrepresented.
// 
// 2. Altered versions must be plainly marked as such and must not
//    be misrepresented as being the original source.
// 
// 3. This Copyright notice may not be removed or altered from any
//    source or altered source distribution. 
// 
// 4. Code or part of code can't by sold.
//
// This software is provided AS IS. The author does not guarantee that 
// this program works, is bugfree, etc. The author does not take any
// responsibility for eventual damage caused by this program.
// Use at own risk.
//

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
