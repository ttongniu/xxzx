package workflow;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.boco.xxzx.model.LeaveBill;
import com.boco.xxzx.model.User;
import com.boco.xxzx.service.LeaveBillService;

import common.BaseTest;

public class TestLeaveBill  extends BaseTest {
      @Autowired
      private  LeaveBillService leaveBillService;
      @Test
      public void  test01(){
    	  LeaveBill leaveBill=new LeaveBill();
    	  leaveBill.setDays(3);
    	  leaveBill.setContent("dsagdsja");
    	  leaveBill.setRemark("aaaaa");
    	  User user=new User();
    	  user.setId(3);
    	  leaveBill.setUser(user);
    	  System.err.println("*******"+leaveBill.getId());
    	  leaveBillService.insertLeaveBill(leaveBill);
    	  System.err.println("======="+leaveBill.getId());
      }
	

}
