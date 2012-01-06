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

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.*;

public class Memo extends Activity
{
    private final Handler handler = new Handler();
    private MemoGame mv;
    private final int refreshRate = 60;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
	
	Display display = getWindowManager().getDefaultDisplay();
	new GameSettings(display.getWidth(), display.getHeight());

	GraphicMaster.resources = this.getResources();

	mv = new MemoGame(this);

	requestWindowFeature(Window.FEATURE_NO_TITLE);
	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//	setContentView(mv);
	setContentView(new SplashScreen(this, mv));
	doTheAutoRefresh();
  }
  
  private void doTheAutoRefresh() {

    handler.postDelayed(new Runnable() {
      public void run()
      {
	ParticlFactory.move(refreshRate);
	mv.refreshSpider(refreshRate);
	mv.invalidate(); 

        doTheAutoRefresh();
      }
     }, refreshRate);
  }

}
