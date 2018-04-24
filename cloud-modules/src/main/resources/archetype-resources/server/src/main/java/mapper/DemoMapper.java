#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.mapper;

import ${package}.common.model.Demo;
import java.util.List;
import org.apache.ibatis.annotations.Select;

public interface DemoMapper {

    @Select("select * from t_user_demo_info")
    List<Demo> selectAll();
}
