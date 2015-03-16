# User Stories #

## Potential Stories ##
  * User sees tile in hand shrink to same size as tiles in board when user drags it over the board.
  * User able to hit Esc(already done), followed by Enter(not done) to exit the game
  * User is able to see and/or navigate a minimap
  * User can run some sweet unit tests for networking.

## 7th Build (Apr. 13 - Apr. 23) ##
  * Fix bugs
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' />  User sees the tile adjust to the correct size of the board when zoomed (Adam)
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' />  User sees a splash screen when the program starts (Adam)
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' />  User sees a custom ALT-TAB/Minimized icon (Adam)
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' />  User sees the tile automatically rotate when they place it in a correct position but the tile is not rotated properly (Adam)
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' />  User sees their meeple adjust to the correct size proportional to the board when being drug (Adam)



## 6th Build (Mar. 30 - Apr. 9) ##

Game Team:
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' />  User's meeple are scored at the end of the game (includes partially completed features, but not farms). (Kyle)
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' /> Meeple count is accurately displayed in the lower left, and meeple are given back as features are completed in the game. (Ben)
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' /> User can place farmer meeple and valid placement regions on the tile are highlighted. (Kyle, Ben)
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' />  User can see an end-game screen that shows game statistics (Adam, David)
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' />  User can save a local (not network) game to an XML file. (Ben)
  * User can resume a local game by loading a saved game from an XML file. [integration with the GameController](Pending.md) (Ben)
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' /> User can hear in-game sound effects. SoundManager listens for specific notifications and plays sounds for notifications. [is functional, but only NotificationStartGame is currently registered. We need more sounds!](This.md) (Adam)

Networking Team:


## 5th Build (Mar. 16 - Mar. 26) ##

Game Team:
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' /> User can place meeple on the board during their turn using a small panel. (Ben)
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' /> User sees spots highlighted on the tile where meeple can be placed. Only valid placements are shown. (Ben)
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' /> User's meeple on cities, roads and cloisters are scored as the game progresses and features are finished (Kyle)
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' /> User's current score is displayed in the HUD on the bottom right along with turn information. (Ben)
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' /> User can view game rules from the main menu. (Adam)
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' /> User can choose the color they want to use when they enter their name (David)
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' /> User can see the game not minimize when they try to exit or load a saved game (Adam)


Networking Team:
  * User can interact with the multiplayer game window by joining and hosting game (Hamy)
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' /> User can see join game button (Brian)
  * In addition to looking at users on the local network, user can type in IP address of a game server to join a game. [has implemented this functionality, but interface is not complete.](Hamy.md) (Hamy, Brian)
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' /> Users will be able to chat with players in the global mutiplayer lobby and see what they are doing. (Hamy, Brian).
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' /> User can chat with others playing the same game by typing onto the game canvas. Text appears transparently and pressing enter sends it to the other players. (Hamy, Ben)

## 4th Build (Feb. 24 - Mar. 12) ##

Game Team:
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' /> Prior to starting a game, user sees a "New Game" panel and enters information such as players' names that get stored during gameplay. (David)
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' /> Add HUD Panel to show game stats and more advanced turn information. (Ben)
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' /> User can see possible locations where the tile is allowed to be placed. (Kyle and Ben)
  * User able to navigate the map using the 4 directional keys. (Kyle)
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' /> User see's an end game menu when they run out of tiles. (Adam)
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' /> User will see a Save Game and a Load Game dialog  (Adam)
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' /> User will see a 'Game In Progress' dialog when trying to start a new game  (Adam)
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' /> User sees the number of game "meeple" they have remaining in part of the H.U.D. (Ben)
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' /> User sees an option at the beginning of the game to view instructions. (Ben)

Networking Team:
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' /> Get DNS-SD working, and allow user to see show all network games in the GUI, even if they are ongoing. (Hamy, Brian)
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' /> User can send a notification across the network and receive it. (Hamy, Brian)

## 3rd Build (Feb. 10 - Feb. 19) ##

Game Team:
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' /> User can drag and drop their tile onto the map in the desired position. (Ben, Kyle)
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' /> Add HUD Panel to show the number of each type of remaining tile. (Ben)
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' /> User sees an expanded main menu with many of the options currently in the HUD left sidebar. The main menu can be brought up by pressing esc during gameplay or clicking a small menu icon. (Ben)
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' /> User can place the tile in their hand in a valid location during their turn. (Kyle)
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' /> User sees dialogs in the correct place on the screen that do not cause the game window to minimize. (Adam)
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' /> User sees the correct starting tile when the game begins. (Ben)
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' /> User cannot click draw again unless they place their tile. (Kyle)
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' /> User sees and empty gray box when their tile has been placed. (Ben)

Networking Team:

  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' /> User can search for and see other people (IP addresses) running the Javassonne application on the local network. (user has to run the server / client builds in eclipse )(Hamy, Brian)

## 2nd Build (Jan. 27 - Feb. 5) ##

  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' /> User can see class diagrams representing our model-view-controller architecture and the notifications being sent to different parts of the application via a PubSub mechanism. (Ben)
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' /> User sees an alert window or log window when an exception or error occurs in the app, rather than something printed to the console. (Ben)
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' /> User sees heads up display floating on top of the map, rather than at the bottom of the screen. (Hamy)
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' /> User sees the number of tiles remaining in part of the H.U.D. (Brian, Adam)
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' /> User can rotate the tile in their hand. (Brian, David)
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' /> User sees a single "starting" tile on the board at the beginning of the game, rather than a collection of randomly placed tiles. (David)
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' /> User sees the main menu and see options like "New Game", "Quit", etc... (Kyle, Adam)
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' /> User can press escape to exit the game. (Adam)

## 1st Build (Jan. 13 - Jan. 22) ##

  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' />User sees UI Grid in scrollable inline frame
    * Hamy, David
  * <img src='http://pvalet.com/Yellow%20Check%20Mark.gif' height='15' width='15' />~~User sees UI Grid navigation Buttons in sidebar (Zoom, Pan)~~ <br />Completed Pan by embedding into game window rather than as buttons in sidebar. Zoom remains in sidebar for now
    * Hamy, David
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' />User sees UI control buttons in sidebar (New Game, Load Game, Exit)
    * Brian
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' />User sees UI game status in sidebar (Turn Indicator, Draw Terrain Tile, Drawn Card Display, Card Rotator)
    * Brian, David
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' />User sees saved game tiles loaded
    * Ben, Adam, Kyle, Hamy
  * <img src='http://1sync.ontuet.com/images/layout/check_mark.gif' />User sees terrain\*tile generator utility
    * Ben, Adam, Kyle


---


# Long-Term Objectives #

  * Implement a model-view-controller architecture for the UI, with a pub sub notification system for passing messages around the app. Notification system should be open enough that we can attach a socket observer + publisher to it and run messages across the network transparently. (Ben)

  * A graphical menu system allows to choose gameplay type, join a network game, etc...
  * Users take turns drawing, rotating and placing tiles on a graphical playing field according to game rules.
  * User can place meeple (small morphing people) and claim parts of the board territory.
  * User is awarded points based on scoring rules
  * Game tracks deck of tiles and randomly draws tiles for players
  * Game may be played over a local network or by multiple players on the same machine
  * Game will have different pre\*set terrain sets and will give the user the ability to create their own.
  * Users playing over a network can type to chat amongst each other.