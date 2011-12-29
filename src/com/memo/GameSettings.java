package com.memo;

class GameSettings
{
  public final static int width = 480;
  public final static int height = 640;
  public static int windowsWidth = 0;
  public static int windowsHeight = 0;
  public static double widthM = 0;
  public static double heightM = 0;

  public GameSettings(int width, int height)
  {
    windowsWidth = width;
    windowsHeight = height;
    widthM = windowsWidth / (double)this.width;
    heightM = windowsHeight / (double)this.height;
  }
}
