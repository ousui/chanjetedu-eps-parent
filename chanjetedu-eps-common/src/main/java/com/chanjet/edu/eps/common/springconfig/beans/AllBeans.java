package com.chanjet.edu.eps.common.springconfig.beans;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by shuai.w on 2016/5/24.
 */
@Configuration
@Import(value = {Caches.class, Converters.class, PropertySources.class})
public class AllBeans {


}
