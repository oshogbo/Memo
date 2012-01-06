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

class ParticlFactory
{
  private static final float sizeOne = 12;
  private static ArrayList<Particl> listParticl = new ArrayList<Particl>();

  static public void createParticles(MemoView v, int x, int y)
  {
    createParticles(v, x, y, null);
  }

  static public void createParticles(MemoView v, int x, int y, Integer color)
  {
    for(int i = 0; i < sizeOne; i++)
    {
      Particl p = new Particl(x, y, color);
      listParticl.add(p);
      v.addActor(p);
    }
  }

  static public boolean move(long dt)
  {
    int size = listParticl.size();
    for(int i = 0; i < listParticl.size(); i++)
    {
      listParticl.get(i).move(dt);
      if(listParticl.get(i).getDestroy() == true)
      {
	listParticl.remove(i);
	i--;
      }
    }

    if(size > 0)
      return true;
    else
      return false;
  }


}
