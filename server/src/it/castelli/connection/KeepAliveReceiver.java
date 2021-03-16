package it.castelli.connection;

public class KeepAliveReceiver implements Runnable
{
    ConnectionManager connectionManager = ConnectionManager.getInstance();
    private boolean isRunning;
    private int checkTime;

    /**
     * KeepAliveReceiver Constructor
     * @param checkTime the time gap between connection inactivity check in seconds
     */
    public KeepAliveReceiver(int checkTime)
    {
        this.checkTime = checkTime;
        isRunning = true;
    }

    @Override
    public void run()
    {
        while (isRunning)
        {
            try
            {
                Thread.sleep(checkTime * 1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            removeInactiveConnections();
        }
    }

    /**
     * Removes the inactive connections from the server
     */
    private void removeInactiveConnections()
    {
        //remove inactive players from the waiting room
        for (Connection connection : connectionManager.getWaitingRoom())
        {
            if (!connection.getKeepAliveFlag())
            {
                connection.interrupt();
                connectionManager.getWaitingRoom().remove(connection);
            }
        }

        //remove inactive connections in every game
        for (GameConnectionManager gameConnectionManager : connectionManager.getGames().values())
        {
            //remove inactive connections from joiningPlayers list
            for (Connection connection :gameConnectionManager.getJoiningPlayers())
            {
                if (!connection.getKeepAliveFlag())
                {
                    connection.interrupt();
                    gameConnectionManager.getJoiningPlayers().remove(connection);
                }
            }

            //remove inactive connections from players list
            for (Connection connection :gameConnectionManager.getPlayers())
            {
                if (!connection.getKeepAliveFlag())
                {
                    connection.interrupt();
                    gameConnectionManager.getPlayers().remove(connection);
                }
            }

        }
    }

    public void interrupt()
    {
        isRunning = false;
    }
}
