package com.example.lru;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LruApplication {

	public static void main(String[] args) throws IOException {
		LruCache<Integer> lruCache = new LruCache<>(3);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int choice = 1;
		while (choice != 0) {
			System.out.println("1: Put\n2: Get\n0: Exit");
			choice = Integer.parseInt(br.readLine());
			String key;
			int value;

			switch (choice) {
				case 1:
					System.out.println("Enter key");
					key = br.readLine();
					System.out.println("Enter value");
					value = Integer.parseInt(br.readLine());
					lruCache.put(key, value);
					System.out.println("Inserted !");
					break;
				case 2:
					System.out.println("Enter key");
					key = br.readLine();
					System.out.println("Value is : " + lruCache.get(key).toString() + "\n");
					break;
				default:
					System.out.println("See ya !");
			}
		}
	}
}
