package point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Point {
	public double[] values;
	public String type = null;;
	public Map<Double, ArrayList<Point>> map;

	public Point(String name, double... a) {
		type = name;
		values = a;
		map = new TreeMap<>();
	}

	@Override
	public String toString() {
		return "Point [values=" + Arrays.toString(values) + ", type=" + type + "]\n ";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((map == null) ? 0 : map.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + Arrays.hashCode(values);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (map == null) {
			if (other.map != null)
				return false;
		} else if (!map.equals(other.map))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (!Arrays.equals(values, other.values))
			return false;
		return true;
	}

	public double calculateDist(Point to_where) throws Exception {
		double temp = 0;
		if (values.length != to_where.values.length) {
			throw new Exception("Cant calculate the given distance, something has too much paraeters");

		}
		for (int i = 0; i < values.length; i++) {
			temp += Math.pow(values[i] - to_where.values[i], 2);
		}
		return Math.sqrt(temp);
	}
}
