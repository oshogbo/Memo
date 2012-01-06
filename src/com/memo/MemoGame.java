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

import android.content.*;
import java.util.*;
import android.graphics.*;

public class MemoGame extends MemoView 
{
  private final int numberOfCards = 28;
  private final int numberOfTypes = 14;
  private final int numberOfCardsInRow = 4;
  private MemoCard[] memoCards;
  private Spider spider;
  private TouchListener touchListener = new TouchListener();
  private BackgroundPoints bpoints;

  public MemoGame(Context context)
  {
    super(context);
    this.setOnTouchListener(touchListener);

    addActor(new Background());

    memoCards = new MemoCard[numberOfCards];

    ArrayList<Integer> types = new ArrayList<Integer>();

    for(int i = 0; i < numberOfTypes; i++)
      types.add( (i + 1) * 2); 

    Random random = new Random();
    MemoCard mc = new MemoCard(0, 0, 1);
    int startX = (int)(20 * GameSettings.widthM);
    int startY = (int)(10 * GameSettings.heightM);

    for(int i = 0; i < numberOfCards; i++)
    {
      int r = random.nextInt(types.size());

      int type = types.get(r);

      if(type % 2 == 0)
      {
	types.set(r, type - 1);
	type /= 2;
      } else {
	type = (type + 1) / 2;
	types.remove(r);
      }

      memoCards[i] = new MemoCard(startX + (int)(mc.getWidth() + 10 * GameSettings.widthM) * (i % numberOfCardsInRow), 
	  startY + (int)(mc.getHeight() + 10 * GameSettings.heightM) * (i / numberOfCardsInRow), 
	  type);

      this.addActor(memoCards[i]);
      touchListener.add(memoCards[i]);

    }
    spider = new Spider((int)((GameSettings.width - 100) * GameSettings.widthM), 10);
    this.addActor(spider);
    touchListener.add(spider);

    bpoints = new BackgroundPoints((int)((GameSettings.width - 105) * GameSettings.widthM), (int)((GameSettings.height - 60) * GameSettings.heightM));
    this.addActor(bpoints);
  }

  public void refreshSpider(int dt)
  {
    spider.move(dt);
  }

  public MemoCard[] getSelected() 
  {
    return getSelected(false);
  }

  public MemoCard[] getSelected(boolean points) 
  {
    MemoCard[] actor = { null, null };

    for(int i = 0, j = 0; i < memoCards.length && j < actor.length; i++)
      if(memoCards[i].getStatusSelected() == true && memoCards[i].getShow() == true)
	actor[j++] = memoCards[i];

    if(points == true && actor[1] != null)
      if(actor[0].getType() == actor[1].getType())
	bpoints.addPoints(25);
      else
	bpoints.addPoints(-5);

    return actor;
  }

  public boolean endGame()
  {
    for(int i = 0; i < memoCards.length; i++)
      if(memoCards[i].getShow() == true)
	return false;

    return true;
  }
}

class BackgroundPoints extends Actor
{
  private static Bitmap texture = null;
  private static final String name = "points";
  private int x;
  private int y;
  private Paint paint;
  private int points = 0;
  private int lastDrawPoints = -1;
  private float textFloat = 0;

  public BackgroundPoints(int x, int y)
  {
    this.x = x;
    this.y = y;
    if(texture == null)
      texture = loadGraphic(name)[0];
    
    paint = new Paint();
    paint.setColor(Color.WHITE);
    paint.setTextSize((float)(texture.getHeight()));
  }

  public void addPoints(int p)
  {
    if(points + p < 0)
      points = 0;
    else 
      points += p;
  }

  public void draw(Canvas canvas)
  {
    String text = new Integer(points).toString();
    
    if(lastDrawPoints != points)
    {
      float sth[] = new float[text.length()];
      paint.getTextWidths(text, sth);

      float res = 0;
      for(int i = 0; i < sth.length; i++)
	res += sth[i];

      textFloat = res;
      lastDrawPoints = points;
    }

    int fontX = this.x + texture.getWidth() * 9/10;
    fontX -= (int)(textFloat);

    canvas.drawBitmap(texture, x, y, null);
    canvas.drawText(text,
		    fontX, 
		    y + texture.getHeight() * 9 / 10,
		    paint);
  }
}

