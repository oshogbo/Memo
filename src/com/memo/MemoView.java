package com.memo;

import java.util.ArrayList;
import android.view.*;
import android.content.*;
import android.graphics.*;

class MemoView extends View 
{
  private ArrayList<Actor> actorList = new ArrayList<Actor>();

  public MemoView(Context context)
  {
    super(context);
  }

  public void addActor(Actor actor)
  {
    actorList.add(actor); 
  }

  protected void onDraw(Canvas canvas)
  {
    //super.onDraw(canvas);

    //some draw things
    for(int i = 0; i < actorList.size(); i++)
    {
      Actor actor = actorList.get(i);

      if(actor.getDestroy() == true) 
      {
	actorList.remove(i);
	i --;
      } else if( actor.getShow() == true ) {
        actor.draw(canvas);
      } 
    }
  }

  public void removeActor(Actor actor)
  {
    int index = actorList.indexOf(actor);
    if(index != -1)
      actorList.remove(index);
  }
}
