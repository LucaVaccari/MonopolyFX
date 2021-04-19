package it.castelli.serialization;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import it.castelli.gameLogic.OwnerPlayer;
import it.castelli.gameLogic.Pawn;
import it.castelli.gameLogic.contracts.*;

import java.io.IOException;

public class ContractAdapter extends TypeAdapter<Contract>
{
	@Override
	public void write(JsonWriter out, Contract contract) throws IOException
	{
		out.beginObject();
		out.name("Type");
		if (contract instanceof PropertyContract)
		{
			out.value("Property");
			PropertyContract propertyContract = (PropertyContract) contract;

			out.name("revenues");
			out.beginArray();
			for (int revenue : propertyContract.getRevenues())
				out.value(revenue);
			out.endArray();

			out.name("color");
			out.value(String.valueOf(propertyContract.getColor()));

			out.name("houseCost");
			out.value(propertyContract.getHouseCost());

			out.name("numberOfHouses");
			out.value(propertyContract.getNumberOfHouses());

			out.name("colorSetContractNumber");
			out.value(propertyContract.getColorSetContractNumber());
		}
		else if (contract instanceof StationContract)
			out.value("Station");
		else if (contract instanceof CompanyContract)
		{
			out.value("Company");
			out.name("company");
			out.value(String.valueOf(((CompanyContract) contract).getCompany()));
		}
		else
			throw new IOException("ContractAdapter - " + contract.getName() +
					": Cannot convert a pure Contract into JSON. Should be a subclass of it");

		out.name("name");
		out.value(contract.getName());

		out.name("value");
		out.value(contract.getValue());

		out.name("revenue");
		out.value(contract.getRevenue());

		out.name("mortgageValue");
		out.value(contract.getMortgageValue());

		out.name("owner");
		out.beginObject();
		{
			if (contract.getOwner() != null)
			{
				out.name("money");
				out.value(contract.getOwner().getMoney());
				out.name("name");
				out.value(contract.getOwner().getName());
				out.name("inPrison");
				out.value(contract.getOwner().isInPrison());
				out.name("position");
				out.value(contract.getOwner().getPosition());
				out.name("previousPosition");
				out.value(contract.getOwner().getPreviousPosition());
				out.name("pawn");
				out.value(String.valueOf(contract.getOwner().getPawn()));
				out.name("randomEventDescription");
				out.value(contract.getOwner().getRandomEventDescription());
				out.name("randomEventType");
				out.value(contract.getOwner().getRandomEventType());
			}
		}
		out.endObject();

		out.name("mortgaged");
		out.value(contract.isMortgaged());

		out.endObject();
	}

	@Override
	public Contract read(JsonReader in) throws IOException
	{
		JsonToken token;

		in.beginObject();
		Contract contract = null;

		String contractType;

		// PROPERTY ONLY
		int[] revenues = new int[6];
		PropertyColor color = null;
		int houseCost = 0, numberOfHouses = 0, colorSetContractNumber = 0;

		// COMPANY ONLY
		CompanyContract.Company company = null;

		String name;
		int value, revenue, mortgageValue;
		OwnerPlayer owner = null;
		int money;
		String ownerName;
		int position, previousPosition;
		Pawn pawn = null;
		boolean inPrison;
		String randomEventType = "", randomEventDescription = "";
		boolean mortgaged;

		in.nextName();
		contractType = in.nextString();

		if (contractType.equals("Property"))
		{
			in.nextName();
			in.beginArray();
			for (int i = 0; i < 6; i++)
			{
				revenues[i] = in.nextInt();
			}
			in.endArray();

			in.nextName();
			color = PropertyColor.valueOf(in.nextString());

			in.nextName();
			houseCost = in.nextInt();

			in.nextName();
			numberOfHouses = in.nextInt();

			in.nextName();
			colorSetContractNumber = in.nextInt();
		}
		else if (contractType.equals("Company"))
		{
			in.nextName();
			company = CompanyContract.Company.valueOf(in.nextString());
		}

		in.nextName();
		name = in.nextString();

		in.nextName();
		value = in.nextInt();

		in.nextName();
		revenue = in.nextInt();

		in.nextName();
		mortgageValue = in.nextInt();

		// OWNER
		in.nextName();
		in.beginObject();
		token = in.peek();
		if (!token.equals(JsonToken.END_OBJECT))
		{
			in.nextName();
			money = in.nextInt();

			in.nextName();
			ownerName = in.nextString();

			in.nextName();
			inPrison = in.nextBoolean();

			in.nextName();
			position = in.nextInt();

			in.nextName();
			previousPosition = in.nextInt();

			in.nextName();
			String pawnString = in.nextString();
			if (!pawnString.equals("null"))
				pawn = Pawn.valueOf(pawnString);

			in.nextName();
			token = in.peek();
			if (!token.equals(JsonToken.NULL))
				randomEventDescription = in.nextString();
			else
				in.nextNull();

			in.nextName();
			token = in.peek();
			if (!token.equals(JsonToken.NULL))
				randomEventType = in.nextString();
			else
				in.nextNull();

			owner = new OwnerPlayer(ownerName, money, position, inPrison, pawn, randomEventType,
					randomEventDescription,
					previousPosition);
		}
		in.endObject();

		in.nextName();
		mortgaged = in.nextBoolean();

		in.endObject();

		switch (contractType)
		{
			case "Property" -> {
				contract = new PropertyContract(name, value, revenues[0], revenues[1], revenues[2],
						revenues[3], revenues[4], revenues[5], houseCost, color, colorSetContractNumber);
				((PropertyContract) contract).addHouses(numberOfHouses);
			}
			case "Station" -> {
				contract = new StationContract(name, value, revenue);
			}
			case "Company" -> {
				contract = new CompanyContract(name, company, value);
			}
			default -> throw new IllegalStateException("Unexpected value: " + contractType);
		}
		;

		if (owner != null)
			contract.setOwner(owner.toPlayer());

		contract.setMortgaged(mortgaged);

		return contract;
	}
}
