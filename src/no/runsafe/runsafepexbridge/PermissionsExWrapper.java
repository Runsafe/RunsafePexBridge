package no.runsafe.runsafepexbridge;

import no.runsafe.framework.api.hook.IPlayerBuildPermission;
import no.runsafe.framework.api.hook.IPlayerPermissions;
import no.runsafe.framework.minecraft.RunsafeLocation;
import no.runsafe.framework.minecraft.player.RunsafePlayer;
import ru.tehkode.permissions.PermissionUser;
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
	public boolean setUserGroup(RunsafePlayer player, String group)
	{
		PermissionUser user = PermissionsEx.getUser(player.getName());
		if (user == null)
			return false;
		user.setGroups(new String[]{group});
		return user.inGroup(group);
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
