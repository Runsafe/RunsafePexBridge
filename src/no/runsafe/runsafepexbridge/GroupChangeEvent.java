package no.runsafe.runsafepexbridge;

import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.event.player.RunsafeCustomEvent;

public class GroupChangeEvent extends RunsafeCustomEvent
{
	public GroupChangeEvent(IPlayer player, String group)
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