package com.google.api.services.discovery;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import afluentes.core.api.IEvaluation;

public class ApisTest {
	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		String uri = "https://www.googleapis.com/discovery/v1/apis";
		IApisResource calculator = new ApisResourceImpl(client, uri);

		DirectoryList list = calculator.list(null, true);
		for (DirectoryList.Items item : list.items) {
			System.out.println(item.name + " " + item.version);
		}

		IApisAsyncResource calculatorAsync = new ApisAsyncResourceImpl(client,
				uri);
		IEvaluation<DirectoryList> listEvaluation = calculatorAsync.list(null,
				true);
		list = listEvaluation.y();
		for (DirectoryList.Items item : list.items) {
			System.out.println(item.name + " " + item.version);
		}
	}
}