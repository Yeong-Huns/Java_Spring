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

@Configuration //환경설정변수 등록
@MapperScan(basePackages = {"kr.board.mapper"}) 
@PropertySource({"classpath:my_sql.properties"}) //property파일을 만들었다면 그걸 가져온다.
public class RootConfig {

	@Autowired
	private Environment env;////springFramwork.core.env
	//Environment -> PropertySource 내용을 가져옴
	
	@Bean
	public DataSource dataSource() { 
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(env.getProperty("driver")); //가져온 propertysorce를 key:value 접근
		hikariConfig.setJdbcUrl(env.getProperty("url"));
		hikariConfig.setUsername(env.getProperty("user"));
		hikariConfig.setPassword(env.getProperty("password"));
		//env -> propertysorce에 접근&조회 -> hikariconfig에 set -> 
		/*
		 PropertySource를 쓰지않는다면? 
		 hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
		hikariConfig.setJdbcUrl(env.getProperty("jdbc:mysql://localhost:3306/Spring_MVC01?serverTimezone=UTC"));
		hikariConfig.setUsername(env.getProperty("root"));
		hikariConfig.setPassword(env.getProperty("root"));
		이런식으로 하면되는거같음 ㅇㅇ..
		 * */
		HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
		return hikariDataSource;
	}
	
	@Bean //this = sqlsessionfactoryBean 
	public SqlSessionFactory sessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean(); 
		sessionFactoryBean.setDataSource(dataSource()); //위의 hikariDataSource를 받아서 sessionFactoryBean에 SET
		return (SqlSessionFactory)sessionFactoryBean.getObject(); //getObject로 받아서 sqlSessionFactory로 형변환해서 사용
	}
	
}
