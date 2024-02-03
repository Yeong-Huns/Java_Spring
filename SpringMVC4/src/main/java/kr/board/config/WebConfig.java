package kr.board.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//web.xml
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
	//한글 인코딩설정 (=servlet filter)
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		encodingFilter.setForceEncoding(true);
		return new Filter[] {encodingFilter}; 
	}
	//this = root-context.xml
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new class[] {}; //RootConfig.class
	}
	
	//this = servlet-context.xml
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new class[] {}; // ServletConfig.class
	}
	
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

}
