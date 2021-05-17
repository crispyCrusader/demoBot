package commands;

import commands.util.CommandObject;
import demoBot.Bot;
import demoBot.Config;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public class Join extends CommandObject {
	
	boolean isJoined;
	
	public String getName() {
		return "join";
	}

	public String extraDetails() {
		if (isJoined == false)
			return "joined voice channel \"" + Config.get("VOICE_CHANNEL") + "\";";
		return "was already connected to channel \"" + Config.get("VOICE_CHANNEL") + "\";";
	}

	public String getArgs() {
		return "";
	}

	public String getType() {
		return "Basic";
	}

	public String getDesc() {
		return "makes bot join the voice channel";
	}

	public boolean getAdmin() {
		return false;
	}

	public void execute(Guild guild, TextChannel textChannel, User user, String[] arg) {
		
		isJoined = Bot.isJoined;
		
		// Executes joinVC() method in Bot
		Bot.joinVC(textChannel);
	}

}
