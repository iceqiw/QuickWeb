#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient("${rootArtifactId}")
@RequestMapping("/${appName}")
public interface IndexAPI {

    @GetMapping("/index")
    String index();
}
