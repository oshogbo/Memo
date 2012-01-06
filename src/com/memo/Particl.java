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
