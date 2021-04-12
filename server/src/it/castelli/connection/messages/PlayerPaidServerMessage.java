package it.castelli.connection.messages;

import it.castelli.connection.Connection;
import it.castelli.gameLogic.Player;

public class PlayerPaidServerMessage implements Message
{
    //String playerName, String ownerName, int Money, String ContractName

    @Override
    public void onReceive(Connection connection, Player player)
    {

    }
}
