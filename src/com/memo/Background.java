package com.memo;

import android.graphics.*;

class Background extends Actor
{
  private static Bitmap texture = null;
  private static final String name = "Background";

  public Background()
  {
    if(texture == null)
      texture = (this.loadGraphic(name))[0];
  }

  public void draw(Canvas canvas)
  {
    canvas.drawBitmap(texture, 0, 0, null);
  }
}
