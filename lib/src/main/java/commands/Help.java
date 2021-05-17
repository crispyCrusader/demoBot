package commands;

import java.awt.Color;

import commands.util.CommandObject;
import demoBot.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

// Retrieves and prints out the information for commands
public class Help extends CommandObject {
	
	public boolean wasSingle = false;
	public String singleCommand;

	public String getName() {
		return "help";
	}

	public String extraDetails() {
		if (wasSingle == false)
			return "";
		wasSingle = false;
		return "user requested command \"" + singleCommand + "\";";
	}

	public String getArgs() {
		return " <command>";
	}
	
	public String getType() {
		return "Basic";
	}

	public String getDesc() {
		return "gets info on commands";
	}

	public boolean getAdmin() {
		return false;
	}

	// Execution method: Determines if there is a specific command request or not
	public void execute(Guild guild, TextChannel textChannel, User user, String[] arg) {
		
		// If the user requests help for a specific command, execute singleEmbedBuild to return specific command information
		if (arg.length >= 3)
		{
			singleEmbedBuild(arg[2], textChannel);
			wasSingle = true;
		}
		// Otherwise, execute allEmbedBuild to return information on all commands
		else
			allEmbedBuild(textChannel);
	}
	
	// Help for a specific command
	public void singleEmbedBuild(String cmd, TextChannel channel) {
		
		// Create the embed builder, set title and color
		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle("Commands: " + Config.get("COMMAND_PREFIX") + " <command>");
		eb.setColor(Color.orange);
		
		// Determines if the command has been found
		boolean commandFound = false;
		
		// Index used for retrieving the command name
		int i = 0;
		
		singleCommand = cmd;
		
		// Looks for the requested command
		for (CommandObject command : CommandObject.commands) 
		{
			if (command.compare(cmd)) 
			{
				commandFound = true;
				
				break;
			}
			
			i++;
		}
		
		// If the command is found, add a field with the proper information
		if (commandFound == true)
		{
			CommandObject command = CommandObject.commands.get(i);
			
			eb.addField(command.getType(), command.getName() + command.getArgs() + ": " + command.getDesc(), false);
		}
		// Otherwise, tell user the command was not found
		else
		{
			eb.addField("Command not found", cmd + " has not been found. Be sure to use " + Config.get("COMMAND_PREFIX") + " for more information.", false);
		}
		
		// Build and send the embed
		channel.sendMessage(eb.build()).queue();
	}
	
	// Help for all commands
	public void allEmbedBuild(TextChannel channel) {
		
		// Creates the embed builder, set the title and color
		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle("Commands: " + Config.get("COMMAND_PREFIX") + " <commands>");
		eb.setColor(Color.orange);
		
		// Index to use for retrieving command types from the static array "types"
		int i = 0;
		
		// String builder to create the body of the embed
		StringBuilder sb = new StringBuilder();
		
		// Traverses through all the commands and adds their information to sb
		for (CommandObject command : CommandObject.commands)
		{
			// If command type is equal to the currently set type, append the string to add command info
			if (CommandObject.types.get(i).compareTo(command.getType()) == 0)
			{
				sb.append(command.getName() + command.getArgs() + ": " + command.getDesc() + "\n");
			}
			// Otherwise, add the embed field with the current command type and string, and move on to the next command type
			else
			{
				// Adds embed field
				eb.addField(CommandObject.types.get(i), sb.toString(), false);
				
				// Deletes the string in the string builder
				sb.delete(0, sb.length());
				
				// Adds the current command info to the now blank string builder
				sb.append(command.getName() + command.getArgs() + ": " + command.getDesc() + "\n");
				
				// Iterates to the new command type
				i++;
			}
		}
		
		// Adds the final command to the field
		eb.addField(CommandObject.types.get(i), sb.toString(), false);
		
		// Prints the embed
		channel.sendMessage(eb.build()).queue();
	}
}
