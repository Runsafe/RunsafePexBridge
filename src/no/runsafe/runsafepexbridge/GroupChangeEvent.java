package no.runsafe.runsafepexbridge;

import no.runsafe.framework.minecraft.event.player.RunsafeCustomEvent;
import no.runsafe.framework.minecraft.player.RunsafePlayer;

public class GroupChangeEvent extends RunsafeCustomEvent
{
	public GroupChangeEvent(RunsafePlayer player, String group)
	{
		super(player, "user.group.change");
		this.group = group;
	}

	@Override
	public String getData()
	{
		return group;
	}

	private final String group;
}