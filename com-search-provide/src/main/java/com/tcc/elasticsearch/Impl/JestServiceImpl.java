package com.tcc.elasticsearch.Impl;

import com.tcc.facede.JestService;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.*;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import io.searchbox.indices.mapping.GetMapping;
import io.searchbox.indices.mapping.PutMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class JestServiceImpl implements JestService {

    private final Logger logger = LoggerFactory.getLogger(JestServiceImpl.class);

    @Autowired
    private JestClient jestClient;

//    /**
//     * 获取JestClient对象，配置文件中已经指定
//     * @return
//     */
//    public JestClient getJestClient() {
//
//        JestClientFactory factory = new JestClientFactory();
//        factory.setHttpClientConfig(new HttpClientConfig
//                               .Builder("http://localhost:9200")
//                               .gson(new GsonBuilder().setDateFormat("yyyy-MM-dd'T'hh:mm:ss").create())
//                               .connTimeout(1500)
//                               .readTimeout(3000)
//                               .multiThreaded(true)
//                               .build());
//        return factory.getObject();
//    }

    /**  
     * 创建索引  
     * @param indexName
     * @return  
     * @throws Exception
     */
    @Override
    public boolean createIndex(String indexName) throws Exception {
        JestResult jr = jestClient.execute(new CreateIndex.Builder(indexName).build());
        return jr.isSucceeded();  
    }  

    /**  
     * Put映射  
     * @param source
     * @return  
     * @throws Exception  
     */
    @Override
    public boolean createIndexMapping(String source , String indexName , String typeName) throws Exception {
  
        PutMapping putMapping = new PutMapping.Builder(indexName, typeName, source).build();
        JestResult jr = jestClient.execute(putMapping);  
        return jr.isSucceeded();  
    }

    /**  
     * Get映射  
     * @return
     * @throws Exception  
     */
    @Override
    public String getIndexMapping(String indexName , String typeName) throws Exception {
        GetMapping getMapping = new GetMapping.Builder().addIndex(indexName).addType(typeName).build();
        JestResult jr = jestClient.execute(getMapping);  
        return jr.getJsonString();  
    }

    /**  
     * 索引文档  
     * @param objs
     * @return  
     * @throws Exception  
     */
    @Override
    public boolean index(List<Object> objs , String indexName , String typeName) throws Exception {
        Bulk.Builder bulk = new Bulk.Builder().defaultIndex(indexName).defaultType(typeName);
        for (Object obj : objs) {  
          Index index = new Index.Builder(obj).build();
          bulk.addAction(index);  
        }  
        BulkResult br = jestClient.execute(bulk.build());
        return br.isSucceeded();  
    }

    /**  
     * 搜索文档  
     * @param query  理解成查询json字符串
     * @return  
     * @throws Exception  
     */
    @Override
    public String search(String query , String indexName , String typeName) throws Exception {
          
        Search search = new Search.Builder(query)
            .addIndex(indexName)
            .addType(typeName)
            .build();
        SearchResult execute = jestClient.execute(search);
        return execute.getJsonString();
    }

    /**  
     * Count文档  
     * @param query
     * @return  
     * @throws Exception  
     */
    @Override
    public Double count(String query , String indexName , String typeName) throws Exception {
  
        Count count = new Count.Builder()
            .addIndex(indexName)
            .addType(typeName)
            .query(query)  
            .build();  
        CountResult results = jestClient.execute(count);
        return results.getCount();  
    }  

    /**  
     * Get文档  
     * @param id
     * @return  
     * @throws Exception  
     */
    @Override
    public String get(String id , String indexName , String typeName) throws Exception {
        Get get = new Get.Builder(indexName, id).type(typeName).build();
        JestResult execute = jestClient.execute(get);
        return execute.getJsonString();
    }  

    /**  
     * Delete索引  
     * @param indexName
     * @return  
     * @throws Exception  
     */
    @Override
    public boolean deleteIndex(String indexName) throws Exception {
        JestResult jr = jestClient.execute(new DeleteIndex.Builder(indexName).build());
        return jr.isSucceeded();  
    }  
      
    /**  
     * Delete文档  
     * @param id
     * @return  
     * @throws Exception  
     */
    @Override
    public boolean delete(String id , String indexName , String typeName) throws Exception {
        DocumentResult dr = jestClient.execute(new Delete.Builder(id).index(indexName).type(typeName).build());
        return dr.isSucceeded();  
    }  
      
    /**
     * 关闭JestClient客户端
     * @throws Exception
     */
    @Override
    public void closeJestClient() throws Exception {
        if (jestClient != null) {
          jestClient.shutdownClient();
        }
    }

    /**
     * 插入参数
     * @param t
     * @param indexName
     * @param typeName
     * @param uniqueId
     * @param <T>
     */
    @Override
    public <T> Boolean elasticInsert(T t , String indexName , String typeName , String uniqueId) {
        Index index = new Index.Builder(t).id(uniqueId).refresh(true).index(indexName).type(typeName).build();
        try {
            JestResult result = jestClient.execute(index);
            if (result != null && result.isSucceeded()){
                logger.info("新增参数成功" + result);
                return true;
            }else {
                logger.error("新增参数失败==> result "+result);
                return false;
            }
        } catch (IOException e) {
            logger.error("新增参数失败==>"+e.getMessage());
            return false;
        }
//        //是否插入成功标识
//        boolean flag = false;
//        Index.Builder builder = new Index.Builder(t);
//        builder.id(uniqueId);
//        builder.refresh(true);
//        Index indexDoc = builder.index(indexName).type(typeName).build();
//        JestResult result;
//        try {
//            result = jestClient.execute(indexDoc);
//            logger.info("返回参数" + result);
//            if (result != null && result.isSucceeded())
//                flag = true;
//        } catch (IOException e) {
//            logger.error("ESJestClient insertDoc exception", e);
//        }
//        return flag;
    }

}