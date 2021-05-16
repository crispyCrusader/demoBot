package demoBot;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Bot extends ListenerAdapter{
	
	public static JDA jda;
	public static Guild guild;
	public static Role adminRole;
	public static TextChannel botControlChannel;
	public static boolean isJoined;
	
	public static boolean launch() throws LoginException, InterruptedException {
		// Ensures the config file is initialized
		if (!Config.isInitialized())
		{
			Config.init();
		}

		// Starts the bot
		return start();
	}
	
	public static boolean start() throws InterruptedException, LoginException {
		
		// Ensures that the bot's token has been initialized in the config file
		if (Config.get("TOKEN").isEmpty())
			return false;
		
		// Initializes the bot and sets the jda variable
		jda = JDABuilder
				.createDefault(Config.get("TOKEN").toString(), EnumSet.allOf(GatewayIntent.class))
				.build();
		jda.awaitReady();
		
		// Sets the default presence of the bot
		setActivity(Config.get("PRESENCE").toString());

		// Sets a static guild variable to be used throughout the program
		guild = jda.getGuilds().get(0);
		
		// Creates admin's role if it doesn't exist
		if (guild.getRolesByName(Config.get("ADMIN_ROLE"), true).isEmpty())
		{
			guild.createRole()
				.setName(Config.get("ADMIN_ROLE"))
				.setColor(Color.yellow)
				.setMentionable(true)
				.setPermissions(getAdminPermissions())
				.complete();
			
			guild.modifyRolePositions()
				.selectPosition(guild.getRolesByName(Config.get("ADMIN_ROLE"), true).get(0))
				.moveTo(guild.getBotRole().getPosition() - 1)
				.complete();
		}
		
		// Sets the admin's role to the static variable
		adminRole = guild.getRolesByName(Config.get("ADMIN_ROLE"), true).get(0);
		
		// Creates channels if they don't exist
		if (guild.getTextChannelsByName(Config.get("BOT_CONTROL_CHANNEL"), true).isEmpty())
			guild.createTextChannel(Config.get("BOT_CONTROL_CHANNEL"))
			.setTopic("Don't worry 'bout it yet.")
			.complete();
		if (guild.getVoiceChannelsByName(Config.get("VOICE_CHANNEL"), true).isEmpty())
			guild.createVoiceChannel(Config.get("VOICE_CHANNEL"))
			.complete();

		// Initializes the control channel variable
		botControlChannel = guild.getTextChannelsByName(Config.get("BOT_CONTROL_CHANNEL"), true).get(0);
		
		// Initializes the commands
		//TODO
		// Initializes the events
		//TODO
		return true;
	}
	
	// Shuts down the program
	public static void shutdown() {
		System.exit(0);
	}
	
	// Joins the voice channel
	public static void joinVC() {
		if (isJoined)
			return;
		
		String channelId = Config.get("VOICE_CHANNEL").toString();
		VoiceChannel channel = guild.getVoiceChannelsByName(channelId, true).get(0);
		AudioManager manager = guild.getAudioManager();
		
		try 
		{
			manager.openAudioConnection(channel);
			isJoined = true;
		} catch(Exception e) {
			botControlChannel.sendMessage("Could not join");
		}
	}
	
	// Leaves the voice channel
	public static void leaveVC() {
		guild.getAudioManager().closeAudioConnection();
		isJoined = false;
	}
	
	// Sets a custom presence activity
	public static void setActivity(String activity) {
		jda.getPresence().setActivity(Activity.playing(activity));
	}
	
	// Adds every role needed for the admin role
	private static Collection<Permission> getAdminPermissions() {
		Collection<Permission> permissions = new ArrayList<>();
		
		permissions.add(Permission.CREATE_INSTANT_INVITE);
		permissions.add(Permission.KICK_MEMBERS);
		permissions.add(Permission.BAN_MEMBERS);
		permissions.add(Permission.MANAGE_CHANNEL);
		permissions.add(Permission.MESSAGE_ADD_REACTION);
		permissions.add(Permission.VIEW_AUDIT_LOGS);
		permissions.add(Permission.PRIORITY_SPEAKER);
		permissions.add(Permission.VOICE_STREAM);
		permissions.add(Permission.VIEW_CHANNEL);
		permissions.add(Permission.MESSAGE_READ);
		permissions.add(Permission.MESSAGE_WRITE);
		permissions.add(Permission.MESSAGE_TTS);
		permissions.add(Permission.MESSAGE_EMBED_LINKS);
		permissions.add(Permission.MESSAGE_ATTACH_FILES);
		permissions.add(Permission.MESSAGE_HISTORY);
		permissions.add(Permission.MESSAGE_MENTION_EVERYONE);
		permissions.add(Permission.MESSAGE_EXT_EMOJI);
		permissions.add(Permission.VIEW_GUILD_INSIGHTS);
		permissions.add(Permission.VOICE_CONNECT);
		permissions.add(Permission.VOICE_MUTE_OTHERS);
		permissions.add(Permission.VOICE_DEAF_OTHERS);
		permissions.add(Permission.VOICE_MOVE_OTHERS);
		permissions.add(Permission.VOICE_USE_VAD);
		permissions.add(Permission.NICKNAME_CHANGE);
		permissions.add(Permission.NICKNAME_MANAGE);
		permissions.add(Permission.MANAGE_ROLES);
		permissions.add(Permission.MANAGE_WEBHOOKS);
		permissions.add(Permission.MANAGE_EMOTES);
		permissions.add(Permission.VOICE_SPEAK);
		permissions.add(Permission.MESSAGE_MANAGE);
		permissions.add(Permission.MANAGE_SERVER);
		permissions.add(Permission.USE_SLASH_COMMANDS);
		
		return permissions;
	}
}
