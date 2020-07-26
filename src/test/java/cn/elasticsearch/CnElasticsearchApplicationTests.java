package cn.elasticsearch;

import cn.elasticsearch.pojo.User;
import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CnElasticsearchApplicationTests {

	@Autowired
	@Qualifier("restHighLevelClient")
	private RestHighLevelClient client;

	/**
	 * 创建index
	 */
	@Test
	public void createIndex() throws IOException {
		// 创建索引对象
		CreateIndexRequest request = new CreateIndexRequest("jd_goods");
		// 客户端执行请求
		CreateIndexResponse indexResponse = client.indices().create(request, RequestOptions.DEFAULT);
		System.out.println(indexResponse);
	}

	/**
	 * 判断index是否存在
	 */
	@Test
	public void testExitsIndex() throws IOException {
		GetIndexRequest getIndexRequest = new GetIndexRequest("wq4");
		boolean exists = client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
		System.out.println(exists);
	}

	/**
	 * 删除index
	 */
	@Test
	public void testDeleteIndex() throws IOException {
		DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest();
		AcknowledgedResponse acknowledgedResponse = client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
		System.out.println(acknowledgedResponse.isAcknowledged());
	}

	/**
	 * 向index添加一个User
	 */
//	@Test
//	public void addDocument() throws IOException {
//		User user = new User("zs",33);
//		IndexRequest indexRequest = new IndexRequest("wq6_index");
//		indexRequest.timeout(TimeValue.timeValueSeconds(1L));
//		indexRequest.timeout("1s");
//		indexRequest.type("String");
//		//将数据放入请求
//		indexRequest.source(JSON.toJSON(user), XContentType.JSON);
//		IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
//		System.out.println(indexResponse.toString());
//	}

	/**
	 * 向index添加一个User
	 */
//	@Test
//	public void getDocument() throws IOException {
//		GetRequest getRequest = new GetRequest("wq6_index", "1");
//		getRequest.fetchSourceContext(new FetchSourceContext(false));
//		getRequest.storedFields("_none_");
//	}
}
