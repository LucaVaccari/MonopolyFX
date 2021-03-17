package it.castelli.connection;

import it.castelli.connection.messages.KeepAliveServerMessage;
import it.castelli.serialization.Serializer;

public class KeepAliveSender implements Runnable
{
    ConnectionManager connectionManager = ConnectionManager.getInstance();
    private boolean isRunning;
    private int sendTime;
    String message = Serializer.toJson(new KeepAliveServerMessage());
    String classToBuild = "it.castelli.connection.messages.KeepAliveClientMessage";

    /**
     * KeepAliveReceiver Constructor
     * @param sendTime the time gap between keep alive messages
     */
    public KeepAliveSender(int sendTime)
    {
        this.sendTime = sendTime;
        isRunning = true;

    }

    @Override
    public void run()
    {
        while (isRunning)
        {
            sendKeepAlive();
            try
            {
                Thread.sleep(sendTime * 1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Sends a keep alive message to all the connections in the server
     */
    private void sendKeepAlive()
    {
        //remove inactive players from the waiting room
        for (Connection connection : connectionManager.getWaitingRoom())
        {
            connection.send(classToBuild);
            connection.send(message);
        }

        //remove inactive connections in every game
        for (GameConnectionManager gameConnectionManager : connectionManager.getGames().values())
        {

            //remove inactive connections from joiningPlayers list
            for (Connection connection :gameConnectionManager.getJoiningPlayers())
            {
                connection.send(classToBuild);
                connection.send(message);
            }

            //remove inactive connections from players list
            for (Connection connection :gameConnectionManager.getPlayers())
            {
                connection.send(classToBuild);
                connection.send(message);
            }

        }
    }

    public void interrupt()
    {
        isRunning = false;
    }
}
