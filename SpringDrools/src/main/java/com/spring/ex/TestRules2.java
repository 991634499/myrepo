package com.spring.ex;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.builder.KieScanner;
import org.kie.api.cdi.KSession;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.pojo.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class TestRules2 {

	@KSession("per-session") 
	private KieSession kSession;
	
	@Test
	public void runRule() throws InterruptedException{
		
		while (true) {
			
			Person p = new Person("张三", 30);
			
			kSession.insert(p);
			int i = kSession.fireAllRules();
			
			System.out.println("============================");
			System.out.println("name is :" + p.getPersonName() + " age is :" + p.getPersonAge());
			System.out.println("总共执行了【"+ i +"】规则");
			System.out.println("============================");
			
		
			//kSession.dispose();
			Thread.sleep(2000);
		}
	}
}
