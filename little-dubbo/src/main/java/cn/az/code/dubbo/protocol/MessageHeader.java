package cn.az.code.dubbo.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageHeader {

    private Integer version;
    private Integer opCode;
    private Long streamId;

}
