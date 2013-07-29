package no.runsafe.runsafepexbridge;

import no.runsafe.framework.api.IOutput;
import no.runsafe.framework.api.hook.IPlayerBuildPermission;
import no.runsafe.framework.api.hook.IPlayerPermissions;
import no.runsafe.framework.minecraft.RunsafeLocation;
import no.runsafe.framework.minecraft.player.RunsafePlayer;
import org.bukkit.craftbukkit.libs.joptsimple.internal.Strings;
import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermissionsExWrapper implements IPlayerPermissions, IPlayerBuildPermission
{
	public PermissionsExWrapper(IOutput console)
	{
		this.console = console;
	}

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
		PermissionUser user = PermissionsEx.getUser(player.getName());
		user.setGroups(new String[]{groupName});
		boolean success = user.inGroup(groupName, false);
		if (success)
			new GroupChangeEvent(player, groupName).Fire();
		else
			console.fine("User group membership appears to have failed - Member of: %s", Strings.join(user.getGroupsNames(), ", "));
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

	private final IOutput console;
}
