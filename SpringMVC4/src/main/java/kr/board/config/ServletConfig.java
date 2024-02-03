package kr.board.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration //this : 환경설정파일 annotation
@EnableWebMvc //this : enable annotation
@ComponentScan(basePackages = {"kr.board.controller"}) // 컴포넌트 스캔 
public class ServletConfig implements WebMvcConfigurer{

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) { //this: viewResolver 설정(bean으로 만들어줌)
		InternalResourceViewResolver bean = new InternalResourceViewResolver("/WEB-INF/views/", ".jsp");
		/* this equals =>
		 InternalResourceViewResolver bean = new InternalResourceViewResolver();
		 bean.setPrefix("/WEB-INF/views");
		 bean.setSurffix(".jsp");
		 접두사 : WEB-INF/views 접미사 : .jsp;
		 ex) return board; -> WEB-INF/views + /board + .jsp
		 
		 ModelAndView 객체를 view영역으로 전달하기위한 알맞는 View 정보를 설정하는역할.  
		 * */
		registry.viewResolver(bean);
	}

	@Override //경로 앞뒤로 / 붙이는 거 진짜 주의할것
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		//this.resources => src/main/webapp/resources -> ex)css/image/upload...;
		//경로아래 모든것을 찾아서 등록
	}
	
}
