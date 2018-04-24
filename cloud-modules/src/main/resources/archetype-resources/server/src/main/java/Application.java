#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * ${artifactId} Application
 *
 * @author qiwei
 * @create 2018/04/27
 **/
@SpringBootApplication
@MapperScan("${package}.mapper")
@EnableFeignClients(basePackages = {
		"com.gofine.demo" //需要修改包名
})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
