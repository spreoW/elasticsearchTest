package cn.elasticsearch.service;

import cn.elasticsearch.pojo.Goods;
import cn.elasticsearch.utils.HTMLParseUtil;
import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    public Boolean parseContent(String keyword) throws Exception {
        List<Goods> goodsList = new HTMLParseUtil().parse(keyword);
        BulkRequest request = new BulkRequest();
        request.timeout("2m");

        for (int i = 0; i < goodsList.size(); i++) {
            request.add(new IndexRequest("jd_goods").source(JSON.toJSON(goodsList.get(i)), XContentType.JSON));
        }
        BulkResponse bulk = restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
        return !bulk.hasFailures();
    }
}
