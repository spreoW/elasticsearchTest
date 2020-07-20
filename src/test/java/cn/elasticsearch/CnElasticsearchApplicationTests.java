package cn.elasticsearch;

import cn.elasticsearch.pojo.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CnElasticsearchApplicationTests {
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Test
	public void contextLoads() {
		elasticsearchTemplate.createIndex(Item.class);
		elasticsearchTemplate.putMapping(Item.class);

	}

}
