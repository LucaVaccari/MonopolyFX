package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

public class SellContractServerMessage implements Message
{
    //gameCode (int), contract Contract

    @Override
    public void onReceive(Connection connection, Player player)
    {

    }
}
