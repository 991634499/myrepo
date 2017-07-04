package com.spring.ex;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.spring.pojo.Person;

public class TestRules1 {
	@Test
	public void runRules() throws InterruptedException{
		
		KieServices kss = KieServices.Factory.get();
		ReleaseId releaseId = kss.newReleaseId("com.spring", "gj", "1.0-SNAPSHOT");
		KieContainer kc = kss.newKieContainer(releaseId);
		
		KieScanner scan = kss.newKieScanner(kc);//用于配置扫描间隔
		scan.start(10000L);//单位是毫秒
		
		while (true) {
			
			KieSession ks = kc.newKieSession();
			Person p = new Person("张三", 30);
			
			ks.insert(p);
			ks.fireAllRules();
			
			System.out.println(p.getPersonName());
			Thread.sleep(3000);
			
			ks.dispose();
			System.out.println("ssss");
		}
	}
}
