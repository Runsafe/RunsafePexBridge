package no.runsafe.runsafepexbridge;

import no.runsafe.framework.RunsafePlugin;

public class Plugin extends RunsafePlugin
{
	@Override
	protected void PluginSetup()
	{
		addComponent(PermissionsExWrapper.class);
	}
}