/* Name: Jason Wang
File: SplashScreen.java
Date: June 1, 2016
Teacher: Ms. Dyke
Purpose: The purpose of this file is to output the splash screen. */

import java.awt.*;
import hsa.Console;

public class SplashScreen extends Thread
{

    private Console c;
    Image picture;

    private void pic ()
    {
	MediaTracker tracker = new MediaTracker (new Frame ());
	tracker.addImage (picture, 0);
	try
	{
	    tracker.waitForAll ();
	}
	catch (InterruptedException e)
	{
	}
    }


    public void splashScreen ()
    {
	picture = Toolkit.getDefaultToolkit ().getImage ("picture1.png");
	MediaTracker tracker;
	pic ();

	c.setColor (Color.black);
	c.fillRect (0, 0, 640, 621);
	c.drawImage (picture, 120, 80, null);

	//yellow lights
	for (int x = 0 ; x < 100 ; x++)

	    {
		c.setColor (Color.yellow);
		c.drawLine (40 - x, 0 + x, 100 + x, 0 + x);
		c.drawLine (530 - x, 620 - x, 600 + x, 620 - x);
		try
		{

		    Thread.sleep (3);
		}
		catch (Exception e)
		{
		}
	    }

	//stars
	for (int x = 0 ; x < 520 ; x++)
	{
	    c.setColor (Color.black);
	    c.fillStar (30, 129 + x, 100, 100);
	    c.fillStar (530, 401 - x, 100, 100);

	    c.setColor (Color.white);
	    c.fillStar (30, 130 + x, 100, 100);
	    c.fillStar (530, 400 - x, 100, 100);

	    try
	    {

		Thread.sleep (3);
	    }
	    catch (Exception e)
	    {
	    }
	}

	//white erase screen
	for (int x = 0 ; x < 621 ; x++)
	{
	    c.setColor (Color.orange);
	    c.fillRect (320, 0, 5, 0 + x);

	    try
	    {

		Thread.sleep (1);
	    }
	    catch (Exception e)
	    {
	    }
	}

	for (int x = 0 ; x < 322 ; x++)
	{
	    c.fillRect (320 - x, 0, 5 + 2 * x, 621);
	    try
	    {

		Thread.sleep (1);
	    }
	    catch (Exception e)
	    {
	    }
	}

	//text
	c.setFont (new Font ("Britannic Bold", Font.PLAIN, 80));
	for (int x = 0 ; x < 225 ; x++)
	{
	    c.setColor (Color.orange);
	    c.fillRect (-26 + x, 41, 240, 60);
	    c.setColor (Color.blue);
	    c.drawString ("WHEEL", -25 + x, 100);

	    try
	    {

		Thread.sleep (2);
	    }
	    catch (Exception e)
	    {
	    }
	}

	for (int x = 0 ; x < 415 ; x++)
	{
	    c.setColor (Color.orange);
	    c.fillRect (680 - x, 130, 270, 62);
	    c.setColor (Color.blue);
	    c.drawString ("OF", 680 - x, 190);

	    try
	    {

		Thread.sleep (2);
	    }
	    catch (Exception e)
	    {
	    }
	}

	for (int x = 0 ; x < 460 ; x++)
	{
	    c.setColor (Color.orange);
	    c.fillRect (-300 + x, 221, 340, 60);
	    c.setColor (Color.blue);
	    c.drawString ("FORTUNE", -300 + x, 280);

	    try
	    {

		Thread.sleep (2);
	    }
	    catch (Exception e)
	    {
	    }
	}

	for (int x = 0 ; x < 2800 ; x++)
	{
	    c.setColor (Color.orange);
	    c.fillRect (697 - x, 380, 2000, 56);


	    picture = Toolkit.getDefaultToolkit ().getImage ("dollarbill1.png");
	    pic ();
	    c.drawImage (picture, 700 - x, 380, null);


	    picture = Toolkit.getDefaultToolkit ().getImage ("dollarbill2.png");
	    pic ();
	    c.drawImage (picture, 1000 - x, 380, null);


	    picture = Toolkit.getDefaultToolkit ().getImage ("dollarbill3.png");
	    pic ();
	    c.drawImage (picture, 1300 - x, 380, null);

	    picture = Toolkit.getDefaultToolkit ().getImage ("dollarbill4.png");
	    pic ();
	    c.drawImage (picture, 1600 - x, 380, null);


	    picture = Toolkit.getDefaultToolkit ().getImage ("dollarbill5.png");
	    pic ();
	    c.drawImage (picture, 1900 - x, 380, null);


	    picture = Toolkit.getDefaultToolkit ().getImage ("dollarbill6.png");
	    pic ();
	    c.drawImage (picture, 2200 - x, 380, null);


	    picture = Toolkit.getDefaultToolkit ().getImage ("dollarbill7.png");
	    pic ();
	    c.drawImage (picture, 2500 - x, 380, null);

	    try
	    {

		Thread.sleep (2);
	    }
	    catch (Exception e)
	    {
	    }
	}


	for (int x = 0 ; x < 1000 ; x++)
	{
	    c.setColor (Color.black);
	    c.fillArc (300, 500, 50, 50, 360 + x, 90);
	    c.setColor (Color.red);
	    c.fillArc (300, 500, 50, 50, 90 + x, 90);
	    c.setColor (Color.blue);
	    c.fillArc (300, 500, 50, 50, 180 + x, 90);
	    c.setColor (Color.yellow);
	    c.fillArc (300, 500, 50, 50, 270 + x, 90);
	    try
	    {

		Thread.sleep (2);
	    }
	    catch (Exception e)
	    {
	    }
	}

    }


    // The purpose of this method is to create the console window.
    public SplashScreen (Console con)
    {
	c = con;
    }


    // the purpose of this method is to run the methods needed.
    public void run ()
    {
	splashScreen ();
    }
}
