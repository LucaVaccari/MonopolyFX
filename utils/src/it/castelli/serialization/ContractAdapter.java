package it.castelli.serialization;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import it.castelli.gameLogic.contracts.Contract;

import java.io.IOException;

public class ContractAdapter extends TypeAdapter<Contract>
{
	@Override
	public void write(JsonWriter out, Contract value) throws IOException
	{
		// TODO: implement
	}

	@Override
	public Contract read(JsonReader in) throws IOException
	{
		// TODO: implement
		return null;
	}
}
