package cn.az.code;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

    @Test
    void search() throws IOException {
        SearchRequest sr = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // using `must`
        boolQueryBuilder.must(QueryBuilders.matchAllQuery());
        // filter only error message
        boolQueryBuilder.filter(QueryBuilders.matchQuery("message", "ERROR"));

        // under must using range with customized format
        boolQueryBuilder.must(QueryBuilders.rangeQuery("@timestamp").gte("prev").lte("next").format("yyyy-MM-dd HH:mm:ss SSS"));

        searchSourceBuilder.query(boolQueryBuilder);
        searchSourceBuilder.fetchSource(FETCH_FIELDS, Strings.EMPTY_ARRAY);
        // sort by naming sort field
        searchSourceBuilder.sort("@timestamp");
        // using from and size => simulate pagination
        searchSourceBuilder.from(1);
        searchSourceBuilder.size(10);

        sr.indices(INDEX);
        sr.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(sr, RequestOptions.DEFAULT);
        if (searchResponse.getHits().getTotalHits().value > 0) {

            LOGGER.info("total count: {}", searchResponse.getHits().getTotalHits().value);

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
