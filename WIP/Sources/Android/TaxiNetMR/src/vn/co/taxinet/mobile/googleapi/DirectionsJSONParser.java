package vn.co.taxinet.mobile.googleapi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import vn.co.taxinet.mobile.app.AppController;
import vn.co.taxinet.mobile.newactivity.MapActivity;

import android.content.Context;
import android.location.Location;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

public class DirectionsJSONParser {

	/**
	 * Receives a JSONObject and returns a list of lists containing latitude and
	 * longitude
	 */
	public List<List<HashMap<String, String>>> parse(JSONObject jObject) {

		List<List<HashMap<String, String>>> routes = new ArrayList<List<HashMap<String, String>>>();
		JSONArray jRoutes = null;
		JSONArray jLegs = null;
		JSONArray jSteps = null;

		try {

			jRoutes = jObject.getJSONArray("routes");

			/** Traversing all routes */
			for (int i = 0; i < jRoutes.length(); i++) {
				jLegs = ((JSONObject) jRoutes.get(i)).getJSONArray("legs");
				if (i == 0) {
					String dis = (String) ((JSONObject) ((JSONObject) jLegs
							.get(0)).get("distance")).get("text");
					AppController.setDistance(parseDistance(dis));			
				}

				List path = new ArrayList<HashMap<String, String>>();

				/** Traversing all legs */
				for (int j = 0; j < jLegs.length(); j++) {
					jSteps = ((JSONObject) jLegs.get(j)).getJSONArray("steps");

					/** Traversing all steps */
					for (int k = 0; k < jSteps.length(); k++) {
						String polyline = "";
						polyline = (String) ((JSONObject) ((JSONObject) jSteps
								.get(k)).get("polyline")).get("points");
						List<LatLng> list = decodePoly(polyline);

						/** Traversing all points */
						for (int l = 0; l < list.size(); l++) {
							HashMap<String, String> hm = new HashMap<String, String>();
							hm.put("lat",
									Double.toString(((LatLng) list.get(l)).latitude));
							hm.put("lng",
									Double.toString(((LatLng) list.get(l)).longitude));
							path.add(hm);
						}
					}
					routes.add(path);
				}
			}

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
		}
		return routes;
	}

	/**
	 * Method to decode polyline points Courtesy :
	 * jeffreysambells.com/2010/05/27
	 * /decoding-polylines-from-google-maps-direction-api-with-java
	 * */
	private Double parseDistance(String stringDistance){
		Double distance = 0.0;
		Boolean isDot = false;
		for (int i = 0; i < stringDistance.length(); i++) {
			char character = stringDistance.charAt(i);
			if (Character.isDigit(character) && !isDot) {
				String h = "" + character;
				distance = distance * 10 + Integer.parseInt(h);
			} else if (Character.isDigit(character) && isDot) {
				String h = "" + character;					
				distance = distance + (Double)(Integer.parseInt(h) / 10.0);
			} else if (character == ',' || character == '.') {
				isDot = true;
			}
		}
		return distance;
	}
	
	private List<LatLng> decodePoly(String encoded) {

		List<LatLng> poly = new ArrayList<LatLng>();
		int index = 0, len = encoded.length();
		int lat = 0, lng = 0;

		while (index < len) {
			int b, shift = 0, result = 0;
			do {
				b = encoded.charAt(index++) - 63;
				result |= (b & 0x1f) << shift;
				shift += 5;
			} while (b >= 0x20);
			int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
			lat += dlat;

			shift = 0;
			result = 0;
			do {
				b = encoded.charAt(index++) - 63;
				result |= (b & 0x1f) << shift;
				shift += 5;
			} while (b >= 0x20);
			int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
			lng += dlng;

			LatLng p = new LatLng((((double) lat / 1E5)),
					(((double) lng / 1E5)));
			poly.add(p);
		}
		return poly;
	}
}

// public class DirectionsJSONParser {
//
// /**
// * Receives a JSONObject and returns a list of lists containing latitude and
// * longitude
// */
// public List<List<HashMap<String, String>>> parse(JSONObject jObject) {
//
// List<List<HashMap<String, String>>> routes = new
// ArrayList<List<HashMap<String, String>>>();
// JSONArray jRoutes = null;
// JSONArray jLegs = null;
// JSONArray jSteps = null;
// String distance = null;
//
// try {
// jRoutes = jObject.getJSONArray("routes");
// /** Traversing all routes */
// for (int i = 0; i < jRoutes.length(); i++) {
// jLegs = ((JSONObject) jRoutes.get(i)).getJSONArray("legs");
// // if (i == 0) {
// // JSONArray jo = ((JSONObject) jLegs.get(0))
// // .getJSONArray("distance");
// // distance = jo.getJSONObject(0).getString("text");
// // }
// List path = new ArrayList<HashMap<String, String>>();
//
// /** Traversing all legs */
// for (int j = 0; j < jLegs.length(); j++) {
// jSteps = ((JSONObject) jLegs.get(j)).getJSONArray("steps");
//
// /** Traversing all steps */
// for (int k = 0; k < jSteps.length(); k++) {
// String polyline = "";
// polyline = (String) ((JSONObject) ((JSONObject) jSteps
// .get(k)).get("polyline")).get("points");
// List<LatLng> list = decodePoly(polyline);
//
// /** Traversing all points */
// for (int l = 0; l < list.size(); l++) {
// HashMap<String, String> hm = new HashMap<String, String>();
// hm.put("lat",
// Double.toString(((LatLng) list.get(l)).latitude));
// hm.put("lng",
// Double.toString(((LatLng) list.get(l)).longitude));
// path.add(hm);
// }
// HashMap<String, String> hm = new HashMap<String, String>();
// hm.put("distance", distance);
// path.add(hm);
// }
// routes.add(path);
// }
// }
//
// } catch (JSONException e) {
// e.printStackTrace();
// } catch (Exception e) {
// }
// return routes;
// }
//
// /**
// * Method to decode polyline points Courtesy :
// * jeffreysambells.com/2010/05/27
// * /decoding-polylines-from-google-maps-direction-api-with-java
// * */
// private List<LatLng> decodePoly(String encoded) {
//
// List<LatLng> poly = new ArrayList<LatLng>();
// int index = 0, len = encoded.length();
// int lat = 0, lng = 0;
//
// while (index < len) {
// int b, shift = 0, result = 0;
// do {
// b = encoded.charAt(index++) - 63;
// result |= (b & 0x1f) << shift;
// shift += 5;
// } while (b >= 0x20);
// int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
// lat += dlat;
//
// shift = 0;
// result = 0;
// do {
// b = encoded.charAt(index++) - 63;
// result |= (b & 0x1f) << shift;
// shift += 5;
// } while (b >= 0x20);
// int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
// lng += dlng;
//
// LatLng p = new LatLng((((double) lat / 1E5)),
// (((double) lng / 1E5)));
// poly.add(p);
// }
// return poly;
// }
// }
