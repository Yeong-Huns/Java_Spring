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
		return new Class[] {RootConfig.class}; //RootConfig.class //여기 대문자 조심
	}
	
	//this = servlet-context.xml
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {ServletConfig.class}; // ServletConfig.class
	}
	
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

}
