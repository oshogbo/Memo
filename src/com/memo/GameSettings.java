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
