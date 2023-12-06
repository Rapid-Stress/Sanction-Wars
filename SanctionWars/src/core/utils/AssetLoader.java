package core.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import javax.imageio.ImageIO;

import core.graphics.rendering.animation.Animation;
import core.graphics.rendering.animation.AnimationArgs;
import core.graphics.rendering.animation.FrameArgs;

public class AssetLoader
{
	public static final String TEXTURE_DIR = "/textures/";
	public static final String ANIMATION_DIR = "res/animations/";
	
	public static BufferedImage LoadTexture(String path)
	{
		CompletableFuture<BufferedImage> loader = CompletableFuture.supplyAsync(() ->
																				{
																					try
																					{
																						return (ImageIO.read(Objects.requireNonNull(AssetLoader.class.getResource(TEXTURE_DIR + path))));
																					} catch (IOException e)
																					{
																						e.printStackTrace();
																						System.exit(1);
																					}
																					
																					return null;
																				});
		
		try
		{
			return loader.get();
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static BufferedImage CropTexture(BufferedImage img, int x, int y, int width, int height)
	{
		return img.getSubimage(x, y, width, height);
	}
	
	public static Animation LoadAnimation(String path)
	{
		CompletableFuture<Animation> loader = CompletableFuture.supplyAsync(() ->
																			{
																				String loadedFile = LoadFileAsString(ANIMATION_DIR + path);
																				String[] tokens = loadedFile.split("\\s+");
																				
																				BufferedImage sheet = null;
																				FrameArgs frameArgs = null;
																				int spriteSize = 0;
																				boolean looping = false;
																				
																				for (int i = 0; i < tokens.length; i++)
																				{
																					switch (tokens[i])
																					{
																						case "SHEET_PATH:":
																							sheet = LoadTexture(tokens[i + 1]);
																							break;
																						
																						case "FRAME_ARGS_UNIFORM:":
																							frameArgs = new FrameArgs(ParseInt(tokens[i + 1]), ParseInt(tokens[i + 2]));
																							break;
																						
																						case "FRAME_ARGS_FRAMES:":
																							ArrayList<Integer> frameDurations = new ArrayList<>();
																							
																							int currentIndex = i + 1;
																							String currentNumStr = tokens[currentIndex];
																							do
																							{
																								currentNumStr = tokens[currentIndex];
																								frameDurations.add(ParseInt(currentNumStr));
																								currentIndex++;
																							} while (!tokens[currentIndex].equals("/$"));
																							
																							frameArgs = new FrameArgs(Arrays.stream(frameDurations.toArray(new Integer[0])).mapToInt(Integer::intValue).toArray());
																							break;
																						
																						case "SPRITE_SIZE:":
																							spriteSize = ParseInt(tokens[i + 1]);
																							break;
																						
																						case "LOOPING:":
																							looping = ParseBool(tokens[i + 1]);
																							break;
																					}
																				}
																				
																				return new Animation(new AnimationArgs(sheet, frameArgs, spriteSize, looping));
																			});
		
		try
		{
			return loader.get();
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static String LoadFileAsString(String path)
	{
		CompletableFuture<String> loader = CompletableFuture.supplyAsync(() ->
																		 {
																			 StringBuilder builder = new StringBuilder();
																			 
																			 try
																			 {
																				 BufferedReader reader = new BufferedReader(new FileReader(path));
																				 
																				 String line;
																				 while ((line = reader.readLine()) != null)
																					 builder.append(line).append('\n');
																				 
																				 reader.close();
																			 } catch (IOException e)
																			 {
																				 e.printStackTrace();
																			 }
																			 
																			 return builder.toString();
																		 });
		
		try
		{
			return loader.get();
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static int ParseInt(String numStr)
	{
		try
		{
			return Integer.parseInt(numStr);
		} catch (NumberFormatException e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public static boolean ParseBool(String boolStr)
	{
		if (boolStr.equals("true"))
		{
			return true;
		}
		else if (boolStr.equals("false"))
		{
			return false;
		}
		else
		{
			System.err.println("Expected true or false");
			System.exit(1);
			return false;
		}
	}
}
