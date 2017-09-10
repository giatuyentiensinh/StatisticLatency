package com.hust;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

/**
 * @author tuyenng
 *
 */
public class App {
	public static void main(String[] args) throws IOException {
		System.out.println("Starting ...");

		List<Node> listRoot = new ArrayList<Node>();
		List<Node> listClient = new ArrayList<Node>();
		List<Long> latencies = new ArrayList<Long>();
		// File file = new File(
		// "/home/tuyenng/Experiments/test/without-malicious/data/dataLog.csv");
		// "/home/tuyenng/Experiments/test/with-malicious/data/dataLog.csv");
		File file = new File(args[0]);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String str = null;
		while ((str = br.readLine()) != null) {
			Node node = new Node(str);
			if (0 == node.getId())
				listRoot.add(node);
			else
				listClient.add(node);
		}
		br.close();
		fr.close();

		listRoot.forEach(node -> {
			for (Node client : listClient)
				if (node.compare(client))
					latencies.add(node.getTime() - client.getTime());
		});

		System.out.println("root   Recv: " + listRoot.size());
		System.out.println("client Sent: " + listClient.size());
		System.out.println("PDR (packet delivery rate): "
				+ (listRoot.size() * 100.0 / listClient.size()) + " %");

		OptionalDouble average = latencies.stream().mapToDouble(i -> i)
				.average();
		System.out.println("Latency end 2 end         : "
				+ (average.isPresent() ? average.getAsDouble() : 0) + " us");
	}
}