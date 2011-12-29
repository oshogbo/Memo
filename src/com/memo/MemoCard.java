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
