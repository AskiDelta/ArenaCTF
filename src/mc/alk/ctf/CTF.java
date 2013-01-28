package mc.alk.ctf;

import mc.alk.arena.BattleArena;
import mc.alk.arena.util.Log;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class CTF extends JavaPlugin{
	static CTF plugin;

	@Override
	public void onEnable(){
		plugin = this;

		/// Save our default config.yml
		saveDefaultConfig();

		/// Read in our values from the config
		loadConfig();

		/// Register our competition
		BattleArena.registerCompetition(this, "CaptureTheFlag", "ctf", CTFArena.class, new CTFExecutor());

		Log.info("[" + getName()+ "] v" + getDescription().getVersion()+ " enabled!");
	}


	@Override
	public void onDisable(){
		Log.info("[" + getName() + "] v" + getDescription().getVersion() + " stopping!");
	}

	public static CTF getSelf() {
		return plugin;
	}

	@Override
	public void reloadConfig(){
		super.reloadConfig();
		loadConfig();
	}

	private void loadConfig() {
		FileConfiguration config = getConfig();
		CTFArena.capturesToWin = config.getInt("capturesToWin", 3);
	}
}
