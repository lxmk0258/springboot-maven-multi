package com.dao;

import com.ServiceApplication;
import com.alibaba.fastjson.JSONObject;
import com.bean.rcd.UserExtra;
import com.dao.rcd.UserExtraMapper;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ServiceApplication.class)
public class ElasticsearchTest {
    private static Logger log = LoggerFactory.getLogger(ElasticsearchTest.class);

    @Autowired
    private UserExtraMapper userExtraMapper;

    @Autowired
    RestHighLevelClient highLevelClient;

    @Test
    public void testEsRestClient() throws IOException {
        SearchRequest request = new SearchRequest("boss");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
        boolBuilder.must(QueryBuilders.termQuery("description.context_ik_max_word", "博士"));
        boolBuilder.must(QueryBuilders.termQuery("description", "java"));
        boolBuilder.must(QueryBuilders.rangeQuery("update_time").from("2019-05-01T00:00:00.000Z").to("2019-06-01T00:00:00.000Z"));

        sourceBuilder.query(boolBuilder);
//        ## 排序start
        sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
//        sourceBuilder.sort(new FieldSortBuilder("_uid").order(SortOrder.ASC));
//        ## 排序end
        sourceBuilder.from(0);
        sourceBuilder.size(20);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        request.source(sourceBuilder);

        SearchResponse searchResponse = highLevelClient.search(request, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();

        SearchHit[] searchHits = hits.getHits();

        System.out.println(hits.totalHits);

        System.out.println("SearchHit:" + searchHits.length);

        for (SearchHit hit : searchHits) {
            Map<String, Object> datas = hit.getSourceAsMap();
            System.out.println(datas.toString());
        }
    }


    /**
     * testIndex:(创建索引).
     *
     * @throws IOException
     * @author xbq Date:2018年3月21日下午4:04:16
     */
    @Test
    public void testCreateIndex() throws IOException {
        List<UserExtra> userExtras = userExtraMapper.selectUserExtraByLimit(10, 5);
        for (UserExtra userExtra : userExtras) {
            log.info(userExtra.getId() + " : " + userExtra.getDescription());
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderNo", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "001");
        jsonObject.put("orderName", "购买元宝");
        jsonObject.put("orderTime", new Date());
        jsonObject.put("price", 1.5);
        jsonObject.put("ip", "192.168.1.111");

        IndexRequest request = new IndexRequest("shop", "goods", "2").source(jsonObject);
        IndexResponse indexResponse = highLevelClient.index(request, RequestOptions.DEFAULT);

        System.out.println("索引名称：" + indexResponse.getIndex());
        System.out.println("类型：" + indexResponse.getType());
        System.out.println("文档ID：" + indexResponse.getId()); // 第一次使用是1
        System.out.println("当前实例状态：" + indexResponse.status());
    }

    /**
     * testIndex:(创建索引).
     *
     * @throws IOException
     * @author xbq Date:2018年3月21日下午4:04:16
     */
    @Test
    public void testCreateIndexByDB() throws IOException {
        int index = 0;
        int page = 1;
//        while (true) {
            BulkRequest bulk = new BulkRequest();

            List<UserExtra> userExtras = userExtraMapper.selectUserExtraByLimit(index, page);
            log.info("search DB ");
            if (userExtras.size() == 0) {
//                break;
            }
            for (UserExtra userExtra : userExtras) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", userExtra.getId());
                jsonObject.put("user_id", userExtra.getUser_id());
                jsonObject.put("description", userExtra.getDescription());
                jsonObject.put("location", userExtra.getLocation());
                jsonObject.put("email", userExtra.getEmail());
                jsonObject.put("update_time", userExtra.getUpdate_time());

                IndexRequest request = new IndexRequest("boss", "geek").source(jsonObject, XContentType.JSON);
                bulk.add(request);
//                log.info(userExtra.getId() + " : " + userExtra.getDescription());
            }

            highLevelClient.bulk(bulk, RequestOptions.DEFAULT);
            index += page;
            log.info("add size={}", index);
//        }

    }


    /**
     * testUpdate:(更新).
     *
     * @author xbq
     * Date:2018年5月3日下午6:07:58
     */
    @Test
    public void testUpdate() throws IOException {
        JSONObject json = new JSONObject();
        json.put("user", "Joe");
        json.put("age", "22");
        json.put("sex", "男");
        json.put("orderTime", "6666666");
        UpdateRequest request = new UpdateRequest("shop", "goods", "1").doc(json);
        UpdateResponse updateResponse = highLevelClient.update(request, RequestOptions.DEFAULT);
        System.out.println("索引名称：" + updateResponse.getIndex());
        System.out.println("类型：" + updateResponse.getType());
        System.out.println("文档ID：" + updateResponse.getId());
        System.out.println("当前实例状态：" + updateResponse.status());
    }

    /**
     * testDelete:(删除).
     *
     * @author xbq
     * Date:2018年5月4日下午5:44:32
     */
    @Test
    public void testDelete() throws IOException {
        DeleteRequest request = new DeleteRequest("shop", "goods", "2");
        request.timeout(TimeValue.timeValueMinutes(2));
        DeleteResponse deleteResponse = highLevelClient.delete(request, RequestOptions.DEFAULT);

        System.out.println("索引名称：" + deleteResponse.getIndex());
        System.out.println("类型：" + deleteResponse.getType());
        System.out.println("文档ID：" + deleteResponse.getId());
        System.out.println("当前实例状态：" + deleteResponse.status());
    }
}
