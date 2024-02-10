package cn.az.code.dubbo.protocol;

public record MessageHeader(Integer version, Integer opCode, Long streamId) {
}
