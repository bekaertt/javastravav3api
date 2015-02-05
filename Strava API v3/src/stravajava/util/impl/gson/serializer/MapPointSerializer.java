package stravajava.util.impl.gson.serializer;

import java.lang.reflect.Type;
import java.util.ArrayList;

import stravajava.api.v3.model.StravaMapPoint;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @author dshannon
 *
 */
public class MapPointSerializer implements JsonDeserializer<StravaMapPoint>, JsonSerializer<StravaMapPoint> {

	/**
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type,
	 *      com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize(StravaMapPoint point, Type type, JsonSerializationContext context) {
		if (point == null) { return null; }
		ArrayList<Float> list = new ArrayList<Float>();
		list.add(point.getLatitude());
		list.add(point.getLongitude());
		return context.serialize(list);
	}

	/**
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type,
	 *      com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public StravaMapPoint deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
		if (element == null) { return null; }
		JsonArray array = element.getAsJsonArray();
		return new StravaMapPoint(array.get(0).getAsFloat(), array.get(1).getAsFloat());
	}

}