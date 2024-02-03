package kr.board.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration //ȯ�漳������ ���
@MapperScan(basePackages = {"kr.board.mapper"}) 
@PropertySource({"classpath:my_sql.properties"}) //property������ ������ٸ� �װ� �����´�.
public class RootConfig {

	@Autowired
	private Environment env;////springFramwork.core.env
	//Environment -> PropertySource ������ ������
	
	@Bean
	public DataSource dataSource() { 
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(env.getProperty("driver")); //������ propertysorce�� key:value ����
		hikariConfig.setJdbcUrl(env.getProperty("url"));
		hikariConfig.setUsername(env.getProperty("user"));
		hikariConfig.setPassword(env.getProperty("password"));
		//env -> propertysorce�� ����&��ȸ -> hikariconfig�� set -> 
		/*
		 PropertySource�� �����ʴ´ٸ�? 
		 hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
		hikariConfig.setJdbcUrl(env.getProperty("jdbc:mysql://localhost:3306/Spring_MVC01?serverTimezone=UTC"));
		hikariConfig.setUsername(env.getProperty("root"));
		hikariConfig.setPassword(env.getProperty("root"));
		�̷������� �ϸ�Ǵ°Ű��� ����..
		 * */
		HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
		return hikariDataSource;
	}
	
	@Bean //this = sqlsessionfactoryBean 
	public SqlSessionFactory sessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean(); 
		sessionFactoryBean.setDataSource(dataSource()); //���� hikariDataSource�� �޾Ƽ� sessionFactoryBean�� SET
		return (SqlSessionFactory)sessionFactoryBean.getObject(); //getObject�� �޾Ƽ� sqlSessionFactory�� ����ȯ�ؼ� ���
	}
	
}
