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

import android.graphics.*;
import android.content.res.*;
import java.io.*;
import android.util.Log;
import android.view.*;

class GraphicMaster
{
  public static Resources resources;

  static public Bitmap[] load(String src)
  {
    return GraphicMaster.load(src, 0, 0);
  }

  static public Bitmap[] load(String src, int width, int height)
  {
    Bitmap[] texture = null;
    InputStream is = null;
    AssetManager am = resources.getAssets();
    String[] nameOfBoxs = null;

    try
    {
      nameOfBoxs = am.list(src);
    }catch(IOException e)
    {
      Log.d("GraphicMaster", "Culd not find folder " + src);
    }

    Log.d("GraphicMaster", "NameOfBoxs.length = " + nameOfBoxs.length );
    texture = new Bitmap[ nameOfBoxs.length ];

    for(int i = 0; i < nameOfBoxs.length; i++)
    {
      try
      {
	is = resources.getAssets().open(src + "/" + nameOfBoxs[i]);
    
	texture[i] = BitmapFactory.decodeStream(is);

/*	if(width > 0 && height > 0)
          texture[i] = Bitmap.createScaledBitmap(texture[i], width, height, false);*/

	Log.d("GraphicMaster", "Scaled from " +
	       new Integer((int)(texture[i].getWidth())).toString() + " " +
	       new Integer((int)(texture[i].getHeight())).toString() + " to " +
	       new Integer((int)(texture[i].getWidth() * GameSettings.widthM)).toString() + " " +
	       new Integer((int)(GameSettings.heightM * texture[i].getWidth())).toString());

	Log.d("GraphicMaster", "Scale" + new Double(GameSettings.widthM).toString() + " " + new Double(GameSettings.heightM).toString());

	if( GameSettings.widthM != 1 && GameSettings.heightM != 1)
          texture[i] = Bitmap.createScaledBitmap(texture[i], (int)(texture[i].getWidth() * GameSettings.widthM), (int)(texture[i].getHeight() * GameSettings.heightM), false);

      }catch(IOException e)
      {
	Log.d("GraphicMaster", "Culd not read: " + src + nameOfBoxs[i]);
      }
    }

    return texture;
  }
}
