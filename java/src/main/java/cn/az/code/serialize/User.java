package cn.az.code.serialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author az
 * @date 2020/3/7
 */
@Data
@ToString
@AllArgsConstructor
public class User implements Serializable {

    private String username;
    private String desc;
}
