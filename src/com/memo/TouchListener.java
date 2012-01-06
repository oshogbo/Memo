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
