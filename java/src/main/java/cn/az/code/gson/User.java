package cn.az.code.gson;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author azusachino
 * @version 12/20/2019
 */
@Data
public class User {
    @SerializedName("ID")
    private String id;
    private String name;
}
