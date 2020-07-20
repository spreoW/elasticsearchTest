package cn.elasticsearch;

import cn.elasticsearch.config.ESConfig;
import cn.elasticsearch.pojo.Item;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

@SpringBootTest
class CnElasticsearchApplicationTests {
	@Autowired
	private ElasticsearchRestTemplate elasticsearchRestTemplate;

	@Test
	void contextLoads() {
		ESConfig esConfig = new ESConfig();
		//RestHighLevelClient restHighLevelClient = esConfig.elasticsearchClient();
		elasticsearchRestTemplate.createIndex(Item.class);
		elasticsearchRestTemplate.putMapping(Item.class);

	}

}
