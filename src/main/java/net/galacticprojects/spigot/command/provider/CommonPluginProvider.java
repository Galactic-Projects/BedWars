package net.galacticprojects.spigot.command.provider;

import me.lauriichan.laylib.command.Actor;
import me.lauriichan.laylib.command.IProviderArgumentType;
import net.galacticprojects.spigot.CommonPlugin;

public final class CommonPluginProvider implements IProviderArgumentType<CommonPlugin> {
	
	private final CommonPlugin plugin;
	
	public CommonPluginProvider(final CommonPlugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public CommonPlugin provide(Actor<?> actor) {
		return plugin;
	}

}
