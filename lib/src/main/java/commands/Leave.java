package commands;

import commands.util.CommandObject;
import demoBot.Bot;
import demoBot.Config;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public class Leave extends CommandObject {
	
	boolean isJoined;

	public String getName() {
		return "leave";
	}

	public String extraDetails() {
		if (isJoined == true)
			return "left voice channel \"" + Config.get("VOICE_CHANNEL") + "\";";
		return "was already not connected to any voice channel;";
	}

	public String getArgs() {
		return "";
	}

	public String getType() {
		return "Basic";
	}

	public String getDesc() {
		return "makes bot leave the voice channel";
	}

	public boolean getAdmin() {
		return false;
	}

	public void execute(Guild guild, TextChannel textChannel, User user, String[] arg) {
		
		isJoined = Bot.isJoined;
		
		// Executes leaveVC() method in Bot
		Bot.leaveVC(textChannel);
	}

}
