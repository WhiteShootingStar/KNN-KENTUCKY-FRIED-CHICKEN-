package ai;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import point.Point;

public class AI {

	private List<Point> list;

	public AI() {
		list = new ArrayList<>();

	}

	public void learn(String fileName) {
		try {
			BufferedReader read = new BufferedReader(new FileReader(new File(fileName)));
			String line;
			while ((line = read.readLine()) != null) {
				String[] arr = line.split(",");

				double[] point = new double[arr.length - 1];
				for (int i = 0; i < arr.length - 1; i++) {
					point[i] = Double.parseDouble(arr[i]);
				}
				list.add(new Point(arr[arr.length - 1], point));

			}

			read.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void test(String filename) {

		System.out.println(list.size());
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter k");
		int k = scan.nextInt();
		int q = 1;
		int actual = 0;
		int total = 0;
		try {

			BufferedReader read = new BufferedReader(new FileReader(new File(filename)));
			String line;
			while ((line = read.readLine()) != null) {
				String[] arr = line.split(",");
				total++;
				double[] point = new double[arr.length - 1];
				for (int i = 0; i < arr.length - 1; i++) {
					point[i] = Double.parseDouble(arr[i]);
				}
				Point testing = new Point(arr[arr.length - 1], point);
				for (Point training : list) {
					double dist = testing.calculateDist(training);
					if (testing.map.containsKey(dist)) {
						testing.map.get(dist).add(training);
					} else {
						ArrayList<Point> temp = new ArrayList<>();
						temp.add(training);
						testing.map.put(dist, temp);
					}

				}

				List<String> ar = testing.map.keySet().stream().limit(k).map(e -> testing.map.get(e).get(0).type)
						.collect(Collectors.toList());

				actual += mistake(testing.type, ar);

				System.out.println(testing.type + " is real one, and the proposed one is " + proposedName(ar));
			}

			read.close();
			System.out.println(actual + " out of " + total + " \n in percentage it will be "
					+ ((double) actual / total) * 100 + " %");
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (NumberFormatException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (Exception e1) {

			System.out.println("My exception, there are too much/too few values");
			System.exit(-1);
		}

	}

	static int mistake(String what, List<String> from) {
		int occurences = 0;
		Iterator<String> it = from.iterator();
		while (it.hasNext()) {
			if (what.equals(it.next())) {
				occurences++;
			}

		}
		return occurences >= Math.ceil((double) from.size() / 2) ? 1 : 0;

	}

	static String proposedName(List<String> from) {
		String best = null;
		int coun = 0;
		int prevcount = 0;
		for (int i = 0; i < from.size(); i++) {

			for (int b = 0; b < from.size(); b++) {
				if (from.get(b).equals(from.get(i))) {
					coun++;
				}
			}
			if (coun > prevcount) {
				prevcount = coun;
				best = from.get(i);
			}
		}
		return best;
	}
}
