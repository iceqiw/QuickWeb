#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import org.springframework.web.bind.annotation.RestController;
import ${package}.api.IndexAPI;
import ${package}.mapper.DemoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * ${artifactId} constroller
 *
 * @author qiwei
 * @create 2018/01/27
 **/
@RestController
@Slf4j
public class IndexController implements IndexAPI {


    @Autowired
    DemoMapper demoMapper;

    @Override
    public String index(){
        return "This is a demo for ${rootArtifactId}.";
    }
}
