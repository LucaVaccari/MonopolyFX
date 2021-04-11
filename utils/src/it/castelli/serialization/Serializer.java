package it.castelli.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.castelli.gameLogic.contracts.Contract;

import java.io.*;
import java.lang.reflect.Type;

/**
 * Class for serializing objects
 */
public class Serializer
{
	/**
	 * Convert an obj to a byte array
	 *
	 * @param obj The object to serialize
	 * @return The serialized object (in form of byte array)
	 */
	public static byte[] serialize(Serializable obj)
	{
		try (ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		     ObjectOutputStream objOut = new ObjectOutputStream(byteOut))
		{
			objOut.writeObject(obj);
			return byteOut.toByteArray();
		}
		catch (IOException e)
		{
			System.out.println(
					"Error serializing object: " + obj.getClass().getName());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Convert a byte array in a serializable object
	 *
	 * @param arr The byte array in which the object is contained
	 * @return The deserialized object
	 */
	public static Serializable deserialize(byte[] arr)
	{
		Serializable object = null;
		try (ByteArrayInputStream byteIn = new ByteArrayInputStream(arr);
		     ObjectInputStream objIn = new ObjectInputStream(byteIn))
		{
			object = (Serializable) objIn.readObject();
		}
		catch (IOException | ClassNotFoundException e)
		{
			System.out.println("Error deserializing an object");
			e.printStackTrace();
		}
		return object;
	}

	/**
	 * Convert a given object to Json string
	 *
	 * @param obj The object to convert
	 * @return The json string representing the given object
	 */
	public static String toJson(Serializable obj)
	{
		return new GsonBuilder()/*.registerTypeAdapter(Contract.class, new ContractAdapter())*/.create().toJson(obj);
	}


	/**
	 * Convert a Json string to an object of type defined by a string
	 *
	 * @param data      The Json string representing the object
	 * @param className The class name
	 * @return The object of the specified type contained in the Json string
	 */
	public static Serializable fromJson(String data, String className)
	{
		Gson json = new GsonBuilder().registerTypeAdapter(Contract.class, new ContractAdapter()).create();
		Serializable obj = null;
		try
		{
			Module module = Class.forName(className).getModule();
			Class.forName(module, "ExampleClassClient");
			obj = json.fromJson(data, (Type) Class.forName(className));
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}

		return obj;
	}

}
