package com.spring.ex;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.spring.pojo.Person;

public class TestPerson {

	public static void main(String[] args) {
		KieServices kss = KieServices.Factory.get();
		KieContainer kc = kss.getKieClasspathContainer();
		KieSession ks = kc.newKieSession("kie-session");
		
		Person p = new Person("张三", 30);
		ks.insert(p);
		int count = ks.fireAllRules();
		System.out.println("执行了 "+count+" 规则");
		ks.dispose();
	}
}
