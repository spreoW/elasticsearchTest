package cn.elasticsearch.service;

import cn.elasticsearch.pojo.Goods;
import cn.elasticsearch.utils.HTMLParseUtil;
import com.alibaba.fastjson.JSON;
import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class ContentService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    public Boolean parseContent(String keyword) throws Exception {
        List<Goods> goodsList = new HTMLParseUtil().parse(keyword);
        BulkRequest request = new BulkRequest();
        request.timeout("2m");

        for (int i = 0; i < goodsList.size(); i++) {
            request.add(new IndexRequest("jd_goods").type("goods").source(JSON.toJSON(goodsList.get(i)), XContentType.JSON));
        }
        BulkResponse bulk = restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
        return !bulk.hasFailures();
    }

    public List<Map<String,Object>> searchPage(String keyword,int pageNo,int pageSize) throws IOException {
        if(pageNo<=1){
            pageNo = 1;
        }
        // 条件搜索
        SearchRequest searchRequest = new SearchRequest("jd_goods");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //分页
        searchSourceBuilder.from(pageNo);
        searchSourceBuilder.size(pageSize);
        // 精准查询
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title",keyword);
        searchSourceBuilder.query(termQueryBuilder);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        //执行搜索
        SearchRequest source = searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(source, RequestOptions.DEFAULT);
        SearchHit[] hits = searchResponse.getHits().getHits();
        // 取出数据
        List<Map<String,Object>> list = new ArrayList<>();
        for (SearchHit documentFields : hits){
            list.add(documentFields.getSourceAsMap());
        }
        return list;
    }
}
