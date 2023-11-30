package core.entities.players;

import java.awt.*;
import java.awt.image.BufferedImage;

import core.entities.*;
import core.event.EventArgs.EventArgsDirection;
import core.input.keybinds.Player.PlayerKeybinds;
import core.event.EventArgs.EventArgs;
import core.event.EventListener;
import core.graphics.animation.Animation;
import core.math.Rect;

public abstract class Player extends Entity
{
	protected Animation idleAnimation;
	protected Animation walkAnimation;
	protected Animation punchAnimation;
	protected Animation currentMovementAnimation;
	protected Animation currentAttackAnimation;
	
	private final PlayerKeybinds keybinds;
	
	private float moveDir;
	
	public Player(PlayerKeybinds keybinds, Rect bounds, Rect colliderBounds, float speed, boolean flipped)
	{
		super(bounds, colliderBounds, speed, flipped);
		
		this.keybinds = keybinds;
		
		loadAnimations();
		finalizeAnimations();
		
		keybinds.attackAction.onPerformed.addListener(this::handleAttack);
		keybinds.moveAction.onPerformed.addListener(this::handleWalk);
	}
	
	private Void handleAttack(Object sender, EventListener<EventArgs> listenerInstance, EventArgs e)
	{
		currentAttackAnimation = punchAnimation;
		
		if (currentAttackAnimation.onFinished.getListeners() == null || currentAttackAnimation.onFinished.getListeners().isEmpty())
			currentAttackAnimation.onFinished.addListener(this::handleAttackFinished);
		
		return null;
	}
	
	private Void handleWalk(Object sender, EventListener<EventArgsDirection> listenerInstance, EventArgsDirection e)
	{
		moveDir = e.direction;
		return null;
	}
	
	private Void handleAttackFinished(Object sender, EventListener<EventArgs> listenerInstance, EventArgs e)
	{
		currentAttackAnimation.onFinished.removeListener(listenerInstance);
		currentAttackAnimation.restart();
		currentAttackAnimation = null;
		
		return null;
	}
	
	private void orientCharacter()
	{
		if (moveDir > 0)
			flipped = false;
		else if (moveDir < 0)
			flipped = true;
	}
	
	private void handleMovementAnimations()
	{
		if (moveDir == 0)
			currentMovementAnimation = idleAnimation;
		else
			currentMovementAnimation = walkAnimation;
	}
	
	private void updateAnimations()
	{
		if (currentAttackAnimation == null)
			currentMovementAnimation.update();
		else
			currentAttackAnimation.update();
	}
	
	private void finalizeAnimations()
	{
		currentMovementAnimation = idleAnimation;
	}
	
	protected abstract void loadAnimations();
	
	@Override
	public void update()
	{
		orientCharacter();
		handleMovementAnimations();
		move(moveDir);
		detectCollision();
		
		updateAnimations();
		
		keybinds.update();
	}
	
	@Override
	public void render(Graphics2D g)
	{
		Animation currentAnimation = (currentAttackAnimation != null) ? currentAttackAnimation : currentMovementAnimation;
		BufferedImage texture = (!flipped) ? currentAnimation.getFrame().getTexture() : currentAnimation.getFrame().getXFlippedTexture();
		
		int x = (int) bounds.pos.x;
		int y = (int) bounds.pos.y;
		int width = (int) bounds.size.x;
		int height = (int) bounds.size.y;
		
		g.drawImage(texture, x, y, width, height, null);
	}
}
