package org.htm.tools.config;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig.Builder;
import org.elasticsearch.action.admin.indices.settings.put.UpdateSettingsRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.htm.tools.util.Utils;

public class ElasticSearchConfiguration {
	
	private static RestHighLevelClient client = null;
	private static final String MAX_RESULT_WINDOW_SETTING = "index.max_result_window";
	private static final String STRUCTURE_OF_INDEX_PATH = "\\resources\\employee.json";
	
	private ElasticSearchConfiguration() throws IOException {	
		Utils utils = new Utils();
		String host = utils.getProperty("elasticsearch.host");
		int port1 = Integer.parseInt(utils.getProperty("elasticsearch.port1"));
		int port2 = Integer.parseInt(utils.getProperty("elasticsearch.port2"));
		String protocol = utils.getProperty("elasticsearch.protocol");
		
		RestClientBuilder builder = RestClient.builder(
			    new HttpHost(host, port1,protocol),
			    new HttpHost(host, port2,protocol))
			    .setRequestConfigCallback(
			        new RestClientBuilder.RequestConfigCallback() {
						@Override
						public Builder customizeRequestConfig(Builder builder) {
							// TODO Auto-generated method stub
							return builder
				                    .setConnectTimeout(5000)
				                    .setSocketTimeout(60000);
						}
			        });
		client = new RestHighLevelClient(builder);
		
		String indexName = utils.getProperty("elasticsearch.indexname");
		
		boolean exists = client.indices().exists(new GetIndexRequest(indexName), RequestOptions.DEFAULT);
		if (exists) {
			UpdateSettingsRequest updateSettingsRequest = new UpdateSettingsRequest(indexName);
			Settings.Builder settingsBuilder = Settings.builder().put(MAX_RESULT_WINDOW_SETTING,
					Integer.MAX_VALUE);
			updateSettingsRequest.settings(settingsBuilder);
			client.indices().putSettings(updateSettingsRequest, RequestOptions.DEFAULT);
		} else {
			CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
			createIndexRequest.settings(Settings.builder().put(MAX_RESULT_WINDOW_SETTING, Integer.MAX_VALUE));
			Map<String, Object> mapping = new HashMap<>();
//			Map<String, Object> properties = new HashMap<>();
//			Map<String, Object> body = new HashMap<>();
//			Map<String, Object> japanese_field = new HashMap<>();
//			Map<String, Object> employeeName = new HashMap<>();
//			Map<String, Object> fields = new HashMap<>();
//			body.put("type", "text");
//			japanese_field.put("analyzer", "kuromoji");
//			japanese_field.put("type", "text");
//			properties.put("body", body);
////			properties.put("english_field", english_field);
////			properties.put("japanese_field", japanese_field);
//			
//			fields.put("japanese_field", japanese_field);
//			employeeName.put("type", "keyword");
//			employeeName.put("fields", fields);
//			properties.put("employeeName", employeeName);
//			mapping.put("properties", properties);
			String xxx="";
			try {
				xxx = readJsonDefn();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mapping.put("properties", xxx);
			createIndexRequest.mapping(mapping);
			client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
		} 
	}
	
    public static String readJsonDefn() throws Exception {
    	String currentDir = System.getProperty("user.dir");
        return readFileInClasspath(currentDir + STRUCTURE_OF_INDEX_PATH);
    }

    public static String readFileInClasspath(String url) throws IOException {
        StringBuffer bufferJSON = new StringBuffer();

        FileInputStream input = new FileInputStream(new File(url).getAbsolutePath());
        DataInputStream inputStream = new DataInputStream(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        String line;

        try {
			while ((line = br.readLine()) != null) {
			    bufferJSON.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        br.close();
		}
        return bufferJSON.toString();
    }
	
	public static RestHighLevelClient getInstance() throws IOException {
		if (client == null) {
			new ElasticSearchConfiguration();
		}
		return client;
	}

	public static void close() throws IOException {
		if (client != null) {
			client.close();
			client = null;
		}
	}
}
