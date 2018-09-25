package com.sample.app;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@EnableWebMvc
@Configuration
//@ComponentScan
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void extendMessageConverters(java.util.List<HttpMessageConverter<?>> converters) {
    	MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converters.add(converter);
    }

	  /*
    @Override
    public void configureMessageConverters(
      List<HttpMessageConverter<?>> converters) {
     
    	//converters.add(createXmlHttpMessageConverter());
    	converters.add(new MappingJackson2HttpMessageConverter());
 
        //super.configureMessageConverters(converters);
    }
    */

}
