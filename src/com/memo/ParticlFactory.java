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
