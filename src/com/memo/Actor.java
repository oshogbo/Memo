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
