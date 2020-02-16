package com.elastic.web.util;

import java.net.InetAddress;
import java.net.InetSocketAddress;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class ElasticSearchConnection {

	public static Settings settings = Settings.builder()
            .put("network.host", "172.16.110.115")
            .build();
    
	public static TransportClient client = new PreBuiltTransportClient(settings)
    		.addTransportAddress(new TransportAddress(new InetSocketAddress("172.16.100.128", 9300)));
	
}