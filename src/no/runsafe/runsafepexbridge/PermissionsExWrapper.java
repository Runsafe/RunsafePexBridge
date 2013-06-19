package no.runsafe.runsafepexbridge;

import no.runsafe.framework.api.hook.IPlayerBuildPermission;
import no.runsafe.framework.api.hook.IPlayerPermissions;
import no.runsafe.framework.minecraft.RunsafeLocation;
import no.runsafe.framework.minecraft.player.RunsafePlayer;
import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.util.ArrayList;
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
	public List<String> getGroups()
	{
		PermissionGroup[] groups = PermissionsEx.getPermissionManager().getGroups();
		List<String> groupList = new ArrayList<String>();
		for (PermissionGroup group : groups)
			groupList.add(group.getName());
		return groupList;
	}

	@Override
	public boolean setGroup(RunsafePlayer player, String groupName)
	{
		PermissionGroup group = PermissionsEx.getPermissionManager().getGroup(groupName);
		PermissionsEx.getUser(player.getRawPlayer()).setGroups(new String[]{groupName});
		boolean success = PermissionsEx.getUser(player.getRawPlayer()).inGroup(group);
		if (success)
			new GroupChangeEvent(player, group).Fire();
		return success;
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
