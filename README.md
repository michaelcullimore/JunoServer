# JUNO

Classes
- Server - (Instructor)
• Game Logic
- turn - who's turn it is
- deck - the cards
- discard pile - the played cards, last played card is displayed
- ServerHandler (Instructor)
• Handles input/output from clients
- Protocol - (Group)
• OutputStream
• InputStream
- Client - (Individual)
• GUI
- MessageFactory
• Types - login, ack, deny, startGame, quitGame, chat, drawCard, playCard, turn,
dealCard
Use Cases
1. Login
Client logs into server by connecting to it via ip address and submitting a
username. If the username is valid (unique) the server will respond
acknowledging or denying the login.
2. Chat
"
1
Speciﬁcations

The client sends a chat message using the chat window in the UI. The user types
a message and sends it to the server with the send button or by pressing
ctrl+enter. The server receives the message and attaches a username to it then
echoes it out to all other clients currently connected.
3. Start Game
When a user wants to start a game they click the “Start game” button. The
server then receives the start message and if there is not a game currently
running the server it will respond to all clients currently connected and start the
game by dealing the initial cards and setting the player who started the game as
the current turn.
4. Play Card
User plays a card on the discard pile in some way and a message is sent to the
server that contains the card played. The server checks if the card is a valid
move and either accepts it and sends the move to all other clients and sets the
current turn to the appropriate next player or rejects it.
5. Draw Card (can’t play)
User cannot play a valid card so draws a card by clicking the draw button. The
client sends a drawCard message to the server and the server responds with a
dealCard that will be added to the clients hand.
6. Draw Card (someone played a draw 2 or draw 4)
User plays a draw 2 or draw 4. Next player who’s turn it is is dealt the appropriate
number of cards from the server.
7. Reverse Card (someone plays a reverse)
User plays a reverse card. Server receives the card message and sets the turn to
the correct player.
8. Skip Card (someone plays a reverse)
User plays a skip card. Server receives the card message and sets the turn to
the correct player.
9. Call Uno (nice to have)
"
2
Speciﬁcations

User has only one card and may call uno by sending an “uno message” to the
server. Other users may attempt to call uno at the appropriate time before they
do and force them to draw a card.
10.End game
A user may end the game by clicking the quit game button or closing the window.
The server will receive the quit message and end the game logic. If the user has
quit the application the client will close the socket and streams with the server.
Group Written Class Speciﬁcations
Server Class
Fields
Set - sockets - connected Sockets
Set - users - connected users
Behavior
broadcastMessage() - sends a message type to all clients
sendMessage() - sends a message to a client
runGame() - starts and handles game logic
Protocol Class
Fields
InputStream
OutputStream
Behavior
sendMessage() - sends message to server
handleMessage() - depending on the message type it will: display it in the client
chat, display the card on the gui, notify the client as to who’s turn it is etc.
