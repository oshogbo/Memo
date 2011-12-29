package com.memo;

import android.view.*;
import android.view.View.*;
import java.util.*;
import android.util.Log;

class TouchListener implements OnTouchListener
{
  ArrayList<ActorEvent> eventList = new ArrayList<ActorEvent>();

  public void add(ActorEvent actor)
  {
    eventList.add(actor);
  }

  public void remove(ActorEvent actor)
  {
    int index = eventList.indexOf(actor);

    if(index != -1)
    {
      actor.hidden();
      actor.destroy();
      eventList.remove(index);
    }
  }	

  public boolean onTouch(View v, MotionEvent me)
  {
    if(me.getAction() != MotionEvent.ACTION_DOWN)
      return false;

    if(((MemoGame)v).endGame() == true)
      System.exit(0);
    
    {
      MemoCard[] actor = ((MemoGame)v).getSelected();  
      if(actor[0] != null && actor[1] != null &&
         actor[0].getType() != actor[1].getType()) 
      {
	actor[0].unSelect();
	actor[1].unSelect();
      }
    }

    boolean t = false;
    for(int i = 0; i < eventList.size(); i++)
    {
      if(eventList.get(i).getDestroy() == true)
      {
	remove(eventList.get(i));
      }else if(eventList.get(i).getShow() == true)
      {
	t |= eventList.get(i).onTouch(((MemoGame)v), me);
      }
    }


    if( t == true )
    {
      MemoCard[] actor = ((MemoGame)v).getSelected(true);
      
      if(actor[1] != null)
      {
	if(actor[0].getType() == actor[1].getType())
	{
	  remove(actor[0]);
	  remove(actor[1]);
	  ParticlFactory.createParticles((MemoView)v, actor[0].getX(), actor[0].getY());
	  ParticlFactory.createParticles((MemoView)v, actor[1].getX(), actor[1].getY());
	} 
      }

      v.invalidate();
    }

    return true;
  }
}
