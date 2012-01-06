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
//


package com.memo;

import android.graphics.Bitmap;
import android.graphics.Canvas;

abstract class Actor 
{
  private boolean showActor = true;
  private boolean destroyActor = false;

  abstract public void draw(Canvas canvas); 

  public Bitmap[] loadGraphic(String src)
  {
    return GraphicMaster.load(src);
  }

  public Bitmap[] loadGraphic(String src, int width, int height)
  {
    return GraphicMaster.load(src, width, height);
  }

  public boolean getShow()
  {
    return showActor;
  }

  public boolean getDestroy()
  {
    return destroyActor;
  }

  protected void hidden()
  {
    showActor = false;
  }

  protected void show()
  {
    showActor = true;
  }

  protected void destroy()
  {
    destroyActor = true;
  }
}
