package core.entities.players;

import core.input.keybinds.Player.PlayerKeybinds;
import core.math.Rect;
import core.math.Vec2;
import core.utils.AssetLoader;

public class Alisha extends Player 
{
    private static final Rect COLLIDER_BOUNDS = new Rect(128, 120, 150, 180);
    
    public Alisha(PlayerKeybinds keybinds, Rect bounds, float speed, boolean flipped)
    {
        super(keybinds, bounds, COLLIDER_BOUNDS, speed, flipped);
    }
    
    @Override
    protected void loadAnimations()
    {
        idleAnimation = AssetLoader.LoadAnimation("Alisha-Idle_Anim.anim");
        walkAnimation = AssetLoader.LoadAnimation("Alisha-Walk_Anim.anim");
        punchAnimation = AssetLoader.LoadAnimation("Alisha-Punch_Anim.anim");
    }
}
