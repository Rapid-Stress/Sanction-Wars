package core.staticobjects;

public class Backgrounds
{
	public static final Background MAJESTIC_MOUNTAINS = new Background("Backgrounds/MajesticMountains-BG.png");
	
	private static Background CurrentBackground = MAJESTIC_MOUNTAINS;
	
	public static void SetCurrentBackground(Background newBackground)
	{
		CurrentBackground = newBackground;
	}
	
	public static Background GetBackground()
	{
		return CurrentBackground;
	}
}
