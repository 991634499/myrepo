package com.spring.ex;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieBase;
import org.kie.api.cdi.KSession;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.pojo.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring.xml")
public class TestDrools {
	
	@KSession("kie-session")//此种方法不能在controller中使用mm
	KieSession ksession;
	@Autowired 
	private KieBase kbase;//controller中通过kiebase获取session
	@Test
	public void runRules() throws InterruptedException{
		
		while(true){
			KieSession k = kbase.newKieSession();
			Person p = new Person("张三", 30);
			//int count = ksession.fireAllRules();
			k.insert(p);
			
			int count1 = k.fireAllRules();
			
			System.out.println("执行了 "+count1+" 规则");
			
			ksession.dispose();
			Thread.sleep(2000);
		}
		
	}
	public void runRuleOne(){}
}
