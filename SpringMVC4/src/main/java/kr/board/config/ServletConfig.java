package kr.board.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration //this : ȯ�漳������ annotation
@EnableWebMvc //this : enable annotation
@ComponentScan(basePackages = {"kr.board.controller"}) // ������Ʈ ��ĵ 
public class ServletConfig implements WebMvcConfigurer{

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) { //this: viewResolver ����(bean���� �������)
		InternalResourceViewResolver bean = new InternalResourceViewResolver("/WEB-INF/views/", ".jsp");
		/* this equals =>
		 InternalResourceViewResolver bean = new InternalResourceViewResolver();
		 bean.setPrefix("/WEB-INF/views");
		 bean.setSurffix(".jsp");
		 ���λ� : WEB-INF/views ���̻� : .jsp;
		 ex) return board; -> WEB-INF/views + /board + .jsp
		 
		 ModelAndView ��ü�� view�������� �����ϱ����� �˸´� View ������ �����ϴ¿���.  
		 * */
		registry.viewResolver(bean);
	}

	@Override //��� �յڷ� / ���̴� �� ��¥ �����Ұ�
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		//this.resources => src/main/webapp/resources -> ex)css/image/upload...;
		//��ξƷ� ������ ã�Ƽ� ���
	}
	
}
