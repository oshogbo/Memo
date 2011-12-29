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
