package it.castelli.connection;

public class KeepAliveSender implements Runnable
{
    ConnectionManager connectionManager = ConnectionManager.getInstance();
    private boolean isRunning;
    private int sendTime;
    private byte[] keepAliveMessage;

    /**
     * KeepAliveReceiver Constructor
     * @param sendTime the time gap between keep alive messages
     */
    public KeepAliveSender(int sendTime)
    {
        this.sendTime = sendTime;
        isRunning = true;
        // TODO: build keepALiveMessage
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
            connection.send(keepAliveMessage);
        }

        //remove inactive connections in every game
        for (GameConnectionManager gameConnectionManager : connectionManager.getGames().values())
        {
            //remove inactive connections from joiningPlayers list
            for (Connection connection :gameConnectionManager.getJoiningPlayers())
            {
                connection.send(keepAliveMessage);
            }

            //remove inactive connections from players list
            for (Connection connection :gameConnectionManager.getPlayers())
            {
                connection.send(keepAliveMessage);
            }

        }
    }

    public void interrupt()
    {
        isRunning = false;
    }
}
