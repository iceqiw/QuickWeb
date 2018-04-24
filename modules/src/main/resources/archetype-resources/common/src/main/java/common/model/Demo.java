#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common.model;

import java.util.Date;
import lombok.Data;

@Data
public class Demo {

  private long user_id;

  private String user_name;

  private String email;

  private String gender;

  private Date create_time;

  private Date last_modify_time;

  private long modify_timestamp;
}
