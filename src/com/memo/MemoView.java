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
