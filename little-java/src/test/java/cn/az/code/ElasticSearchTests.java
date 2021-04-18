package cn.az.code;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import java.io.IOException;

/**
 * @author az
 * @since 2021-04-18 12:51
 */
@SpringBootTest(classes = LittleJavaApplication.class)
public class ElasticSearchTests {

    public static final String INDEX = "filebeat-7.12.0-2021.04.18";

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchTests.class);
    final private static String[] FETCH_FIELDS = {"@timestamp", "message"};
    @Autowired
    RestHighLevelClient restHighLevelClient;
    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Test
    void search() throws IOException {
        IndexRequest indexRequest = new IndexRequest(INDEX);
        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        LOGGER.info(String.valueOf(indexResponse.getResult()));
    }

    @Test
    void search2() throws IOException {
        SearchRequest sr = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        IndexQueryBuilder indexQueryBuilder = new IndexQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.matchAllQuery());
        searchSourceBuilder.query(boolQueryBuilder).fetchSource(FETCH_FIELDS, null);
        sr.indices(INDEX);
        sr.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(sr, RequestOptions.DEFAULT);
        if (searchResponse.getHits().getTotalHits().value > 0) {

            System.out.println(searchResponse.getHits().getTotalHits());

            for (SearchHit hit : searchResponse.getHits()) {
                System.out.println("Match: ");
                for (String fetchField : FETCH_FIELDS) {
                    System.out.println(" - " + fetchField + " " + hit.getSourceAsMap().get(fetchField));
                }
            }
        } else {
            System.out.println("No results matching the criteria.");
        }

        restHighLevelClient.close();
    }
}
