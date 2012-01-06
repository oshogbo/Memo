Where is Memo?
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

import android.graphics.*;
import android.view.*;
import android.util.Log;

class MemoCard extends ActorEvent
{
  private int width = 0;
  private int height = 0;
  private static Bitmap[] texture = null;
  private Bitmap selectTexture = null;
  private boolean isSelected = false;
  private static final String name = "MemoCard";
  private int x, y;
  private int type; 

  public MemoCard(int x, int y, int type)
  {
    this.x = x;
    this.y = y;
    this.type = type;

    if(texture == null)
      texture = this.loadGraphic(name);
    
    selectTexture = texture[0];
    width = selectTexture.getWidth();
    height = selectTexture.getHeight();
    //selectTexture = texture[type];
  }

  public int getType()
  {
    return type;
  }

  public boolean getStatusSelected()
  {
    return isSelected;
  }

  public void unSelect()
  {
    selectTexture = texture[0];
    isSelected = false;
  }

  public void draw(Canvas canvas)
  {
    if(getShow() == true)
      canvas.drawBitmap(selectTexture, x, y, null);
  }

  public boolean onTouch(MemoView v, MotionEvent me)
  {
    if(me.getAction() != MotionEvent.ACTION_DOWN)
      return false;

    float x = me.getX();
    float y = me.getY();

    if( this.x < x &&
       	this.x + this.width > x &&
       	this.y < y &&
       	this.y + this.height > y )
        {
	  selectTexture = texture[type];
	  isSelected = true;
	  return true;
	}

    return false;
  }

  public int getX()
  {
    return x;
  }

  public int getY()
  {
    return y;
  }

  public int getWidth()
  {
    return width;
  }

  public int getHeight()
  {
    return height;
  }
}
