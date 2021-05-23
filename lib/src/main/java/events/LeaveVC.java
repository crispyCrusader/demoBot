package events;

import java.util.List;

import demoBot.Bot;
import events.util.EventObject;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;

// Disconnects Demo Bot from the voice channel if all users leave the voice channel
public class LeaveVC extends EventObject{

	public String getName() {
		return "LeaveVC";
	}

	public String getAction() {
		return "Demo Bot left the voice channel because everyone else has left";
	}

	// Executes if a user disconnects from a voice channel
	public void onGuildVoiceLeave(GuildVoiceLeaveEvent event) {
		
		if (!(event.getMember().getUser().isBot()))
		{
			// Retrieves the voice channel the user left from
			VoiceChannel channel = event.getChannelLeft();
			
			// Boolean variable used to determine if event should be completely executed
			boolean isTrue = false;
			
			// Creates a list of all members currently in the voice channel
			List<Member> members = channel.getMembers();
			
			// Determines if each user is a bot or if Demo is in the voice chat
			for (int i = 0; i < members.size(); i++)
			{
				// If there are stil users in the vocie chat, break from the for loop and do nothing
				if (!(members.get(i).getUser().isBot()))
				{
					isTrue = false;
					break;
				}
				
				// If Demo Bot is in the voice chat, set the boolean variable to true
				if (members.get(i).getUser().getName().compareTo("Demo Bot") == 0)
					isTrue = true;
			}
			
			// If no human users are in the voice channel and Demo Bot is still present in the VC, disconnect
			if (isTrue)
			{
				// Executes leaveVC() method in the Bot class
				Bot.leaveVC(Bot.botControlChannel);
				
				// Prints the dev message
				super.devMessage(getName(), getAction());
			}
		}
	}
}
