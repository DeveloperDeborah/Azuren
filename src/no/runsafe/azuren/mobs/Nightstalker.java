package no.runsafe.azuren.mobs;

import net.minecraft.server.v1_7_R1.*;
import no.runsafe.azuren.Plugin;
import no.runsafe.framework.api.ILocation;
import no.runsafe.framework.api.IWorld;
import no.runsafe.framework.internal.wrapper.ObjectUnwrapper;
import no.runsafe.framework.minecraft.Sound;
import no.runsafe.framework.minecraft.item.meta.RunsafeSkull;

public class Nightstalker extends EntityBat
{
	public Nightstalker(World world)
	{
		super(world);
		rworld = Plugin.server.getWorld(world.worldData.getName());
		effect = new MobEffect(MobEffectList.INVISIBILITY.id, 86400 * 20, 1);
	}

	public void spawn(ILocation location)
	{
		setPosition(location.getX(), location.getY(), location.getZ());
		world.addEntity(this);
	}

	private boolean hasWorld()
	{
		return world != null;
	}

	private ILocation getLocation()
	{
		if (!hasWorld())
			return null;

		return rworld.getLocation(locX, locY, locZ);
	}

	@Override
	protected String t()
	{
		return null;
	}

	@Override
	protected String aT()
	{
		return null;
	}

	@Override
	protected String aU()
	{
		return null;
	}

	@Override
	public boolean damageEntity(DamageSource damageSource, float v)
	{
		getLocation().playSound(Sound.Creature.PigZombie.Hurt, 2, 2);
		return super.damageEntity(damageSource, v);
	}

	@Override
	public void die(DamageSource damagesource)
	{
		getLocation().playSound(Sound.Creature.Zombie.Death, 2, 2);
		super.die(damagesource);
	}

	@Override
	protected void dropDeathLoot(boolean flag, int i)
	{
		a(Items.COOKIE, 1); // Always drop a cookie.
	}

	@Override
	protected void bn()
	{
		super.bn();

		if (!hasSetup)
		{
			hasSetup = true;
			addEffect(effect);

			RunsafeSkull skull = (RunsafeSkull) no.runsafe.framework.minecraft.Item.Decoration.Head.Human.getItem();
			skull.setOwner("JettKuso");
			setEquipment(4, ObjectUnwrapper.getMinecraft(skull));
			dropChances[4] = 0.0F; // Prevent head dropping
		}
	}



	private final IWorld rworld;
	private final MobEffect effect;
	private boolean hasSetup;
}
