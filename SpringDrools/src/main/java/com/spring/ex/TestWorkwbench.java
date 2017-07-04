package com.spring.ex;

import java.io.IOException;
import java.io.InputStream;

import org.drools.compiler.kproject.ReleaseIdImpl;
import org.drools.core.io.impl.UrlResource;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.spring.pojo.Person;

public class TestWorkwbench {

	@Test
	public void test() throws IOException, InterruptedException{
		
		
		//创建访问，找到要访问的jar包. http://localhost:8080/kie-drools-wb/maven2/组名/构件名/版本/。。.jar
		String url = "http://localhost:8080/kie-drools-wb/maven2/com/spring/gj/1.0-SNAPSHOT/gj-1.0-20170606.063511-1.jar";
		ReleaseIdImpl releaseId = new ReleaseIdImpl("com.spring", "gj", "1.0-SNAPSHOT");
		KieServices kss = KieServices.Factory.get();
		KieRepository kr = kss.getRepository();
		
		
		//相当于在IE中打开KIE-DROOLS的Web地址，然后输入用户名、密码并点击登录
		UrlResource urlResource = (UrlResource) kss.getResources().newUrlResource(url);
		urlResource.setUsername("drools");
		urlResource.setPassword("drools");
		urlResource.setBasicAuthentication("enabled");
		
		InputStream is = urlResource.getInputStream();
		
		KieModule kmodule = kr.addKieModule(kss.getResources().newInputStreamResource(is));
		
		//访问规则，执行规则
		KieContainer kc = kss.newKieContainer(kmodule.getReleaseId());
		KieSession ks = kc.newKieSession();
		
		Person p = new Person("张三", 30);
		ks.insert(p);
		
		int count = ks.fireAllRules();
		
		System.out.print("共执行了"+count+"条规则"); 
		System.out.print("修改后的结果"+p.getPersonName());
		//Thread.sleep(2000);
		
		
	}
}
