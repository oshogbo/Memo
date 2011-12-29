package com.memo;

import java.util.Random;
import java.lang.Math;
import android.graphics.*;
import android.util.Log;

class Particl extends Actor
{
  private static final int lifeLongAndProsperMAX = 2500;
  private static final int lifeLongAndProsperMIN = 750;
  private static final String name="Particle";
  private static Random random = new Random();
  private static Bitmap[] texture = null;
  private double x, y;
  private double ax, ay;
  private int life;
  private Paint paint = null;
  private int type = 0;

  public Particl(int x, int y, Integer type)
  {
    if( texture == null )
      texture = loadGraphic(name);

    paint = new Paint();
    if(type != null)
      this.type = type;
    
    this.x = x;
    this.y = y;
    this.life = random.nextInt( lifeLongAndProsperMAX - lifeLongAndProsperMIN) + lifeLongAndProsperMIN;
    this.ax = Math.cos( random.nextDouble() * Math.PI * 1 / 2 + Math.PI / 4 );
    this.ay = Math.sin( random.nextDouble() * Math.PI * 1 / 2 + Math.PI / 4 );
  }

 
  public void move(long dt)
  {
    x += ax * (dt / 12);
    y += ay * (dt / 12);
    life -= dt;
    if(life <= 0)
      destroy();      
  }

  public void draw(Canvas canvas)
  {
    paint.setAlpha( life * 255 / lifeLongAndProsperMAX);
    canvas.drawBitmap(texture[type], (int)x, (int)y, paint);
  }
}
