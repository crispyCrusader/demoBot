package commands;

import commands.util.CommandObject;
import demoBot.Bot;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

// Allows an admin to change the activity that the bot has in his profile
public class Activity extends CommandObject {
	
	String user;
	String activity;

	public String getName() {
		return "activity";
	}

	public String extraDetails() {
		return (user + " changed the bot's presence to \"" + activity + "\";");
	}

	public String getArgs() {
		return " <custom presence>";
	}

	public String getType() {
		return "Basic";
	}

	public String getDesc() {
		return "sets the activity of bot";
	}

	public boolean getAdmin() {
		return true;
	}

	public void execute(Guild guild, TextChannel textChannel, User user, String[] arg) {
		this.user = user.getName();
		
		StringBuilder sb = new StringBuilder();
		for (int i = 2; i < arg.length; i++) 
		{
			sb.append(arg[i] + " ");
		}
		
		this.activity = sb.toString();
		
		Bot.setActivity(activity);
	}

}
