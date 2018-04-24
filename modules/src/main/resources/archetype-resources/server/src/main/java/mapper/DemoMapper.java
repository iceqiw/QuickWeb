#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.mapper;

public interface DemoMapper {

    void insert();

    void update();

    void deleteById(Integer id);

    //Demo findById(Integer id);

    //List<Demo> selectByExample();
}
