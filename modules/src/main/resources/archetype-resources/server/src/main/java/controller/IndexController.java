#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import ${package}.api.IndexAPI;
/**
 * home constroller
 *
 * @author chris.zhai
 * @create 2018/01/27
 **/
@RestController
@Slf4j
public class IndexController implements IndexAPI {


    @Override
    public String index(){
        return "This is a demo for ${rootArtifactId}.";
    }
}
