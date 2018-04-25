package common;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 * 
 * @ClassName:  BaseTest   
 * @Description:TODO(单元测试的基类)   
 * @author: niutongtong  
 * @date:   2017年9月28日 下午2:22:55   
 *
 */
// 注解让测试运行于Spring测试环境;
@RunWith(SpringJUnit4ClassRunner.class)
//注解加载的是Spring的配置文件
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})        
public class BaseTest {


}
