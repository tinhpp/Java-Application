package org.htm.tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.htm.tools.model.Employee;
import org.htm.tools.service.ElasticSearchService;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

	private static final Logger logger = LogManager.getLogger(Main.class);
	
	public static void main(String[] args) {
		ElasticSearchService service = new ElasticSearchService();
		List<Employee> listEmployee = new ArrayList<Employee>();
		Employee employee = new Employee();
		// Record 1
		employee.setIdEmployee(UUID.randomUUID().toString());
		employee.setEmployeeName("C:/xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4.xls");
		employee.setDayOfBrith("20200213");
		employee.setSex("nu");
		listEmployee.add(employee);
		///
		System.out.println("import data");
		try {
			service.ImportData(listEmployee);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listEmployee = new ArrayList<Employee>();
//			QueryBuilder query = QueryBuilders.matchAllQuery();
//			QueryBuilder query = QueryBuilders.wildcardQuery("employeeName.japanese_field", "*発表▲資料*");
////			QueryBuilder query = QueryBuilders.wildcardQuery("employeeName.japanese_field", "発表▲資料");
		QueryBuilder query = QueryBuilders.boolQuery().should(QueryBuilders.termQuery("employeeName", "C:/xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4xxxx/dddd/yyyy4.xls"));
//			QueryBuilder query = QueryBuilders.multiMatchQuery("*発表▲資料*", "employeeName")
//					.autoGenerateSynonymsPhraseQuery(false)
//					.fuzzyTranspositions(false)
//					.operator(Operator.AND);
//			QueryBuilder query = QueryBuilders.queryStringQuery("発表▲資料")
//					.field("employeeName")
//					.analyzer("kuromoji");
////					.defaultOperator(Operator.AND);
////					.allowLeadingWildcard(true)
////					.autoGenerateSynonymsPhraseQuery(false)
////					.fuzzyMaxExpansions(0)
////					.enablePositionIncrements(false)
////					.analyzeWildcard(true)
////					.escape(false)
////					.fuzzyPrefixLength(0)
////					.lenient(false)
////					.fuzzyTranspositions(false);
		System.out.println("Search data");
//			listEmployee = service.searchData(query);
//			ObjectMapper mapper = new ObjectMapper(); 
//			for (Employee employee2 : listEmployee) {
//				System.out.println(mapper.writeValueAsString(employee2));
//			}
		System.out.println("OK");
	}

}
