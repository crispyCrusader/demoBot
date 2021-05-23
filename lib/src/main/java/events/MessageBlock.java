package events;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import demoBot.Bot;
import events.util.EventObject;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

// Responsible for blocking anything racist / crude
public class MessageBlock extends EventObject{

	String keyword;
	String user;
	
	public String getName() {
		return "MessageBlock";
	}

	public String getAction() {
		return "Demo Bot blocked message with keyword \"" + keyword + "\" send by \"" + user + "\"";
	}
	
	// When a message is received in any text channel, the message is checked against the regex pattern
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		TextChannel channel = event.getChannel();
		Message message = event.getMessage();
		
		this.user = event.getMember().getUser().getName();
		
		// Starter regex pattern to block the n-word (will be improved upon later)
		String regExPattern = "(?i)(n[^\\s]{4,}r|n[^\\s]{3,}a)";
		
		// RegEx pattern for blocked messages
		Pattern r = Pattern.compile(regExPattern);
		
		// Matcher object to use the find() method
		Matcher matcher = r.matcher(message.getContentRaw());
		
		// If the find() method returns true, delete message, and notify who sent the message in which channel
		if (matcher.find())
		{
			// Returns the matching keyword for use in the dev message
			this.keyword = matcher.group();
			
			// Deletes the message with the matching keyword
			message.delete().complete();
			
			// Replies in bot control channel who sent the message and where it was sent
			Bot.botControlChannel.sendMessage("Deleted message sent by " + user + " in text channel \"" + channel.getName() + "\".").queue();
			
			// Prints the dev message
			super.devMessage(getName(), getAction());
		}
	}
}
