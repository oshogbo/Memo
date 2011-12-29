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
