import com.alibaba.fastjson.JSON;
import com.tcc.constants.IndexNameAndTypeConstant;
import com.tcc.elasticsearch.SearchProviderApplication;
import com.tcc.entity.User;
import com.tcc.facede.JestService;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SearchProviderApplication.class)
public class ElasticSearchTest {

    private final Logger logger = LoggerFactory.getLogger(ElasticSearchTest.class);
    @Resource
    private JestService jestService;

    @Test
    public void testElasticSearch() throws Exception {
        //使用 SearchSourceBuilder 拼接查询语句 , 支持链式调用。
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.queryStringQuery("云山"));
        logger.info("searchSourceBuilder===>"+searchSourceBuilder.toString());
        String search = jestService.search(searchSourceBuilder.toString(), IndexNameAndTypeConstant.INDEXNAME_MYINDEX, IndexNameAndTypeConstant.TYPENAME_USER);
        logger.info(search);
    }

    /**
     * 获取对应索引的mapping
     *
     * @throws Exception
     */
    @Test
    public void testElasticGetMapping() throws Exception {
        String indexMapping = jestService.getIndexMapping(IndexNameAndTypeConstant.INDEXNAME_MYINDEX, IndexNameAndTypeConstant.TYPENAME_USER);
        logger.info("indexMapping= {}" + indexMapping);
    }


    @Test
    public void testElasticInsert() throws Exception {
        String insertString = "新华网北京9月7日电 中共中央党校7日上午举行2015年秋季学期开学典礼。中共中央政治局常委、中央党校校长刘云山出席并讲话，就深入学习贯彻习近平总书记系列重要讲话精神、坚持党校姓党提出要求。\" +\n" +
                "                \"刘云山指出，党校姓党是党校工作的根本原则。坚持党校姓党，重要的是坚持坚定正确的政治方向、贯彻实事求是的思想路线、落实从严治校的基本方针。要把党的基本理论教育和党性党风教育作为主课，深化中国特色社会主义理论体系学习教育，深化对习近平总书记系列重要讲话精神的学习教育，深化党章和党纪党规的学习教育。要坚持实事求是的思想方法和工作方法，弘扬理论联系实际的学风，提高教学和科研工作的针对性实效性。要严明制度、严肃纪律，把从严治校要求体现到党校工作和学员管理各方面，使党校成为不正之风的“净化器”。\" +\n" +
                "                \"刘云山指出，坚持党校姓党，既是对党校教职工的要求，也是对党校学员的要求。每一位学员都要强化党的意识，保持对党忠诚的政治品格，忠诚于党的信仰，坚定道路自信、理论自信、制度自信；忠诚于党的宗旨，牢记为了人民是天职、服务人民是本职，自觉践行党的群众路线；忠诚于党的事业，勤政敬业、先之劳之、敢于担当，保持干事创业的进取心和精气神。要强化党的纪律规矩意识，经常看一看党的政治准则、组织原则执行得怎么样，看一看党的路线方针政策落实得怎么样，看一看重大事项请示报告制度贯彻得怎么样，找差距、明不足，做政治上的明白人、遵规守纪的老实人。\" +\n" +
                "                \"刘云山强调，领导干部来党校学习，就要自觉接受党的优良作风的洗礼，修好作风建设这门大课。要重温党的光荣传统，学习革命先辈、英雄模范和优秀典型的先进事迹和崇高风范，自觉践行社会主义核心价值观，永葆共产党人的先进性纯洁性，以人格力量传递作风建设正能量。要认真落实党中央关于从严治党、改进作风的一系列要求，贯彻从严精神、突出问题导向，把自己摆进去、把职责摆进去，推动思想问题和实际问题一起解决，履行好党和人民赋予的职责使命。\" +\n" +
                "                \"赵乐际出席开学典礼。";
        User user = new User("userName_3", "password_3", "nickName_3", "13027383692", 1, new Date(),
                "userType_3", "openId_3", "userSex_3",
                insertString, "userArea_3", "userProvice_3", "unionid_3", 30);
        jestService.elasticInsert(JSON.toJSONString(user), IndexNameAndTypeConstant.INDEXNAME_MYINDEX, IndexNameAndTypeConstant.TYPENAME_USER, "5");
    }


}