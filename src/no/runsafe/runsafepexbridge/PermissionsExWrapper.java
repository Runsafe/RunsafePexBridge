package no.runsafe.runsafepexbridge;

import no.runsafe.framework.api.ILocation;
import no.runsafe.framework.api.hook.IPlayerBuildPermission;
import no.runsafe.framework.api.hook.IPlayerDataProvider;
import no.runsafe.framework.api.hook.IPlayerPermissions;
import no.runsafe.framework.api.log.IDebug;
import no.runsafe.framework.api.player.IPlayer;
import org.apache.commons.lang.StringUtils;
import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.util.*;

public class PermissionsExWrapper implements IPlayerPermissions, IPlayerBuildPermission, IPlayerDataProvider
{
	public PermissionsExWrapper(IDebug console)
	{
		this.debugger = console;
	}

	@Override
	public boolean blockPlayerBuilding(IPlayer player, ILocation location)
	{
		return !player.hasPermission("permissions.build");
	}

	@Override
	public List<String> getUserGroups(IPlayer player)
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
	public boolean setGroup(IPlayer player, String groupName)
	{
		PermissionUser user = PermissionsEx.getUser(player.getName());
		user.setGroups(new String[]{groupName});
		boolean success = user.inGroup(groupName, false);
		if (success)
			new GroupChangeEvent(player, groupName).Fire();
		else
			debugger.debugFine("User group membership appears to have failed - Member of: %s", StringUtils.join(user.getGroupsNames(), ", "));
		return success;
	}

	@Override
	public List<String> getGroupPermissions(String groupName)
	{
		return PermissionsEx.getPermissionManager().getGroup(groupName).getPermissions(null);
	}

	@Override
	public List<String> getPlayerPermissions(IPlayer player)
	{
		return PermissionsEx.getUser(player.getName()).getPermissions(null);
	}

	@Override
	public void addPermission(IPlayer player, String permission, String world)
	{
		PermissionsEx.getPermissionManager().getUser(player.getName()).addPermission(permission, world);
	}

	@Override
	public void addPermission(IPlayer player, String permission)
	{
		PermissionsEx.getPermissionManager().getUser(player.getName()).addPermission(permission);
	}

	@Override
	public void removePermission(IPlayer player, String permission, String world)
	{
		PermissionsEx.getPermissionManager().getUser(player.getName()).removePermission(permission, world);
	}

	@Override
	public void removePermission(IPlayer player, String permission)
	{
		PermissionsEx.getPermissionManager().getUser(player.getName()).removePermission(permission);
	}

	private final IDebug debugger;

	@Override
	public Map<String, String> GetPlayerData(IPlayer player)
	{
		Map<String, String> data = new HashMap<String, String>(1);
		data.put("pex.rank",  StringUtils.join(PermissionsEx.getUser(player.getName()).getGroupsNames(), ", "));
		return data;
	}
}
