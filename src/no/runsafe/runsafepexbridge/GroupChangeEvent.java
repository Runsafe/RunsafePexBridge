package no.runsafe.runsafepexbridge;

import no.runsafe.framework.minecraft.event.player.RunsafeCustomEvent;
import no.runsafe.framework.minecraft.player.RunsafePlayer;
import ru.tehkode.permissions.PermissionGroup;

public class GroupChangeEvent extends RunsafeCustomEvent
{
	public GroupChangeEvent(RunsafePlayer player, PermissionGroup group)
	{
		super(player, "user.group.change");
		this.group = group;
	}

	public PermissionGroup getGroup()
	{
		return group;
	}

	@Override
	public String getData()
	{
		return group.getName();
	}

	private final PermissionGroup group;
}