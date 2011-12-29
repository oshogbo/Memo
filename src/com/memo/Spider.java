package com.memo;

import android.graphics.*;
import android.os.Handler;
import android.view.*;

class Spider extends ActorEvent
{
  private double x;
  private int y;
  private int startX;
  private int steps = 0;
  private int steps2 = 0;
  private int steps2Start = 10;
  private int steps2Limit = steps2Start;
  private double ax = 0.5;
  private double ay = 1;
  private static Bitmap[] texture = null;
  private int modeTexture = 0;
  private static final String name = "spider";
  private int width = 0;
  private int height = 0;
  private boolean die = false;
  private int alpha = 255;

  public Spider(int x, int y)
  {
    this.startX = x;
    this.x = x;
    this.y = y;

    if(texture == null)
      texture = this.loadGraphic(name);

    width = texture[0].getWidth();
    height = texture[0].getHeight();
  }

  public void move(int dt)
  {
    if(die == true)
      return;
    
    x += ax;
    y += ay;
    steps ++;
    steps2 ++;

    if(steps2 > steps2Limit)
    {
      ax *= -1;
      steps2 = 0;
      if(steps2Limit == steps2Start)
	steps2Limit *= 2;
    }
    
    if(steps >= 5)
    {
      steps = 0;
      modeTexture ++;
      if(modeTexture >= texture.length - 1)
	modeTexture = 0;
    }

    if( y < 0 || y > GameSettings.windowsWidth * 0.60)
      ay *= -1;
  }

  public void draw(Canvas canvas)
  {
    Paint p = new Paint();
    if(die == false)
    {
      Paint paint = new Paint();
      paint.setColor(Color.WHITE);

      canvas.drawLine(startX + width / 2, 0, (int)(x) + width / 2, y + height / 2, paint);

    }else{
      p.setAlpha(alpha);
    }

    canvas.drawBitmap(texture[modeTexture], (int)(x), y, p);
  }

  public boolean onTouch(MemoView v, MotionEvent me)
  {
    if(me.getAction() != MotionEvent.ACTION_DOWN)
      return false;

    int x = (int)(me.getX());
    int y = (int)(me.getY());

    if( this.x < x &&
       	this.x + this.width > x &&
       	this.y < y &&
       	this.y + this.height > y )
	{
	  if(die == true)
	  {
	    alpha /= 1.2;
	    if(alpha < 40)
	      destroy();
	  }else {
	    die = true;
	    modeTexture = texture.length - 1;
	  }
	  ParticlFactory.createParticles(v, x, y, 1);
	}
 

    return false;
  }
}
