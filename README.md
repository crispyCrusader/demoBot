# **_STILL IN DEVELOPMENT_**

## PROJECT TITLE: "Demo Bot"

I have been developing several Discord bots over the past 6 months or so, and learned so much about software development in the process. This demobot is a way for me to continually show the skills I have learned and continue to learn.
  
This project has been written entirely in Java, and is the first project I have done using the Gradle Wrapper, rather than Maven.

This Discord bot will have some basic functionalities, such as admin commands (ban users, kick, channel management, etc.), a music player, and whatever other fun things I want to add later on.
  
This project takes advantage of the JDA API wrapper, which enables the use of Discord's API in Java. The source code of this project can be found here at https://github.com/DV8FromTheWorld/JDA.
  
This project also takes advantage of the Lavaplayer project to be used in the Audio and Player managers, to play audio in the voice channels. The project can be found here at https://github.com/sedmelluq/lavaplayer.
  
Both of the above projects take advantage of the Apache License 2.0.

Source Java files are located in the demoBot/src/test/java directory.

### IMPORTANT INFORMATION:

In order to run this project, a "private.json" file is needed with the required information. This file is not included in the commits for maximum privacy, as well as to increase customization for multiple people to use.

As of right now, the "private.json" file needs to be in this format:

```
{
"TOKEN" : <Discord Application API Token>
"ADMIN_ROLE" : <Name of the role you want to have admin privileges>
"COMMAND_PREFIX" : <Custom command prefix>
"PRESENCE" : <Presence description>
"VOICE CHANNEL" : <Voice channel name>
"BOT_CHANNEL" : <Text channel name>
}
```

The token is given to you when you do the following:
- Make a developer account with Discord.
- Create an application in the developer portal, and retrieve the token. 

The admin role is created if it does not exist in the server, and this will allow you to select moderators you trust in the server.

The command prefix is what starts the command. For my server, this prefix is "!d". Example command is "!d presence //custom presence//".
  
The presence is a custom description that appears below the bot's profile in the member list. It appears in the form of "Playing / Watching / Streaming / Listening 'custom desc.' "
  
The voice channel is the primary voice channel that the bot will connect to. For simplicity's sake, right now this will be the only vc the bot can join, but functionality will be added to let the bot join any vc.
  
The bot channel is the primary text channel the bot will send messages in. Just like the voice channel, this is the only channel it'll use, but will be able to use other tc's later in development.
  
Other attributes will be added in the "private.json" as they are needed.
    
###HOW IT WORKS / PROGRAM FLOW:

The program starts in the Main.java file, where the main method is. It calls the static object of Bot, transferring control to Bot.launch() method.

Once in the launch() method, it initializes the Config.java file, which is what will privately store all the important and necessary information to run the program successfully.

The init() function transfers all the information from the "private.json" into a new static JSON object, which can be accessed from anywhere within the program.

Once initialization is finished, the program checks to make sure the token was stored correctly, since without the token the bot cannot run. Once verified, Bot.start() is ran.
 
After verifying that the token is initialized again, bot is built and started. Variable "jda" uses the JDA datatype, which represents the API wrapper used for this project. It sets the token and all the gateway intents (allows developers to choose which events the bot can receive and react to) are enabled. The program then pauses until the bot is fully initialized.

Presence is then set, and then all the necessary static variables are set and created as well:
- Guild: the "server" in which the bot resides in.
- Admin role: the role with the admin privileges. Automatically created if it does not exist already.
- Text channel: the text channel which the bot will primarily send messages to. Automatically created if it does not exist already.
- Voice channel: the voice channel which the bot will primarily connect to. Automatically created if it does not exist already.

After these steps are done, the bot is fully initialized. Event and command initialization takes place after the text and voice channel initializations.

###VERSION HISTORY:

####Initial commit, 05/16/2021:
- Formatted the "private.json" file with the initial information:
  - Token
  - Admin role
  - Command prefix
  - Presence
  - Voice channel
  - Bot channel
- Bot can fully initialize with all the Gateway intents in place
- Bot creates the required roles and channels upon initialization (if they do not exist already)

####Major commit 2, 05/17/2021:
- Created both event and command initializations:
  - Command abstract information:
    - getName(): stores the name of the command. Used for the command handler to execute the command, as well as the dev message
    - extraDetails(): any extra unique details I want displayed in the command's dev message
    - getArgs(): any additional arguments for the command are stored here
    - getType(): stores the type of command it is (e.g. "Basic", "Admin", etc.)
    - getDesc(): stores the description of the command
    - getAdmin(): stores whether or not the command requires the user to have admin privileges to run
    - execute(): method that is called to execute the process of a given command
  - Event abstract information:
    - getName(): stores the name of the event. Used for the dev message
    - getAction(): stores the action that a specific event will do
- Dev messages: upon execution of either a command or an event, a dev message will appear in the terminal, providing feedback on whether execution was successful or not, and the timestamp of when execution took place
- Commands: added commands in the form of "'command_name' 'arguments'":
  - help 'command': retrieves information on a specific command. Leave the command field blank for information on every command
  - join: joins the voice channel that is stored in the "private.json" file.
  - leave: leaves the voice channel that the bot was previously connected to
- Events: no event objects have been added. However, the event method "onGuildMessageReceived()" was added to Bot.java to enable command detection
- Handler: responsible for parsing the command and delegating said command's execution. Also prints the dev message for a command

###FUTURE FEATURES:
- [Add events (triggers that the bot reacts to)]
	- [Say hello back]
	- [Automatically join / leave voice channel when someone else joins] 
	- [Block crude / racist messages]
- [Add commands]
	- [Music player (play, stop, skip, back, etc.)]
	- [Set custom presence activity (does not replace the presence in "private.json")]
	- [File counter, and total lines of code counter]
	- [Shutdown]
