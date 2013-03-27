package no.runsafe.runsafepexbridge;

import no.runsafe.framework.hook.IPlayerBuildPermission;
import no.runsafe.framework.hook.IPlayerPermissions;
import no.runsafe.framework.server.RunsafeLocation;
import no.runsafe.framework.server.player.RunsafePlayer;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.util.Arrays;
import java.util.List;

public class PermissionsExWrapper implements IPlayerPermissions, IPlayerBuildPermission
{
	@Override
	public boolean blockPlayerBuilding(RunsafePlayer player, RunsafeLocation location)
	{
		return !player.hasPermission("permissions.build");
	}

	@Override
	public List<String> getUserGroups(RunsafePlayer player)
	{
		return Arrays.asList(PermissionsEx.getUser(player.getName()).getGroupsNames());
	}

	@Override
	public List<String> getGroupPermissions(String groupName)
	{
		return Arrays.asList(PermissionsEx.getPermissionManager().getGroup(groupName).getPermissions(null));
	}

	@Override
	public List<String> getPlayerPermissions(RunsafePlayer player)
	{
		return Arrays.asList(PermissionsEx.getUser(player.getName()).getPermissions(null));
	}
}
