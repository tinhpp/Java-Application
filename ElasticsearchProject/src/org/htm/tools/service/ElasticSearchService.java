package org.htm.tools.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.htm.tools.config.ElasticSearchConfiguration;
import org.htm.tools.model.Employee;
import org.htm.tools.util.Utils;

public class ElasticSearchService {

	private String indexName;
	private Utils utils;
	
	public ElasticSearchService() {
		utils = new Utils();
		indexName = utils.getProperty("elasticsearch.indexname");
	}
	
	public void ImportData(List<Employee> list) throws Exception {
		BulkRequest request = new BulkRequest(); 
		Map<String, Object> jsonMap;
		for (Employee employee : list) {
			jsonMap = new HashMap<>();
			jsonMap.put("employeeName", employee.getEmployeeName());
			jsonMap.put("dayOfBrith", employee.getDayOfBrith());
			jsonMap.put("sex", employee.getSex());
			request.add(new IndexRequest(indexName).id(employee.getIdEmployee())  
			        .source(jsonMap));
		}
		ElasticSearchConfiguration.getInstance().bulk(request, RequestOptions.DEFAULT);
		ElasticSearchConfiguration.close();
	}
	
	public List<Employee> searchData(QueryBuilder query) throws Exception {
		List<Employee> listEmployee = new ArrayList<Employee>();
		Employee employee;
		SearchRequest request = new SearchRequest(indexName);
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		SearchResponse searchResponse = null;
		SearchHits hits;
		Map<String, Object> map = null;
		int i =0;
		while (searchResponse == null || searchResponse.getHits().getHits().length != 0) {
			request = new SearchRequest(indexName);
			searchSourceBuilder = new SearchSourceBuilder();
			searchSourceBuilder.from(i * 1000);
			searchSourceBuilder.size(1000);
			searchSourceBuilder.query(query);
			request.source(searchSourceBuilder);
			searchResponse = null;
			searchResponse = ElasticSearchConfiguration.getInstance().search(request,RequestOptions.DEFAULT);
			if (searchResponse.getHits().getHits().length != 0) {
				hits = searchResponse.getHits();
				for (SearchHit hit: hits) {
					map = hit.getSourceAsMap();
					employee = new Employee();
					employee.setIdEmployee(hit.getId());
					employee.setEmployeeName(map.get("employeeName").toString());
					employee.setDayOfBrith(map.get("dayOfBrith").toString());
					employee.setSex(map.get("sex").toString());
					listEmployee.add(employee);
				}
			}
			i++;
		}
		ElasticSearchConfiguration.close();
		return listEmployee;
	}
}
