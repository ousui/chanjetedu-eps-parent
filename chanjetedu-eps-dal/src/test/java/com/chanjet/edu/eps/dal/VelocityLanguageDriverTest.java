package com.chanjet.edu.eps.dal;

import com.google.common.base.Charsets;
import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.apache.velocity.runtime.resource.loader.ResourceLoader;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by shuai.w on 2016/5/26.
 */
public class VelocityLanguageDriverTest {

	String path = "vms/test.vm";

	@Test
	public void test() throws IOException {

		VelocityEngine engine = new VelocityEngine();


		;
//		engine.setProperty();
		Template tpll = engine.getTemplate(path, Charsets.UTF_8.name());
//		Template tpll = new Template();
//		tpll.setName(path);
//		tpll.setEncoding(Charsets.UTF_8.name());

		ResourceLoader loader = new ClasspathResourceLoader();
//		InputStream is = loader.getResourceStream(path);
		tpll.setResourceLoader(loader);
//		System.out.println(loader.resourceExists(path));
//		if (!loader.resourceExists(path)) {
//			System.err.println("不存在/ classpath resource loader");
//			loader = new JarResourceLoader();
//		}

//		RuntimeServices rs = new RuntimeInstance();
//		tpll.setRuntimeServices(rs);
		tpll.process();
//		tpll.initDocument();
		System.err.println(tpll.getData());
	}

}