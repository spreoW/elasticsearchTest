package cn.elasticsearch.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "item",type = "docs",shards = 1,replicas = 0)
public class Item {
    @Id
    Long id;

    /**
     * 标题
     */
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    String title;

    /**
     * 分类
     */
    @Field(type = FieldType.Keyword)
    String category;

    /**
     * 品牌
     */
    @Field(type = FieldType.Keyword)
    String brand;

    /**
     * 价格
     */
    @Field(type = FieldType.Double)
    Double price;

    /**
     * 图片地址
     */
    @Field(type = FieldType.Keyword,index = false)
    String images;
}
