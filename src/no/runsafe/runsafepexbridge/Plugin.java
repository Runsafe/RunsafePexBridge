package no.runsafe.runsafepexbridge;

import no.runsafe.framework.RunsafePlugin;
import no.runsafe.framework.features.Events;
import no.runsafe.framework.features.FrameworkHooks;

public class Plugin extends RunsafePlugin
{
	@Override
	protected void pluginSetup()
	{
		// Framework features
		addComponent(FrameworkHooks.class);

		// Plugin components
		addComponent(PermissionsExWrapper.class);
	}
}
