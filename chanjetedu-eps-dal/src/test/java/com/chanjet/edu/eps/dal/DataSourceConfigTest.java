package com.chanjet.edu.eps.dal;

import com.google.common.base.Charsets;
import org.junit.Test;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by shuai.w on 2016/5/25.
 */
public class DataSourceConfigTest {

	@Test
	public void base64() throws IOException {

		InputStream is = ClassLoader.getSystemResourceAsStream("com/chanjet/edu/eps/dal/mapper/HelloWorldMapper-selectByKeyVmTest.vm");
		System.out.println(is);


		String x = StreamUtils.copyToString(is, Charsets.UTF_8);
		System.out.println(x);

	}

}