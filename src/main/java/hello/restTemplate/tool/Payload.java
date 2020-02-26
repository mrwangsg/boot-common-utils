package hello.restTemplate.tool;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

/**
 * @创建人 sgwang
 * @name Result
 * @user 91119
 * @创建时间 2019/6/14
 * @描述
 */
public class Payload<T> implements Serializable {
    private static final long serialVersionUID = -1549643581827130116L;
    private T payload;
    // 错误码
    private String code = "200";
    // 错误信息
    private String msg = "ok";
    private ObjectMapper objectMapper = new ObjectMapper();

    public Payload() {
    }

    public Payload(T payload) {
        this.payload = payload;
    }

    public Payload(T payload, String code, String msg) {
        this.payload = payload;
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getPayload(){
        return this.payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public String toString() {
        System.out.println("payload:" + this.payload);
        try {
            String json = this.payload != null ? this.objectMapper.writeValueAsString(this) : "{payload=" + null
                    + ",code=" + this.code + ",msg=" + this.msg + "}";
            return this.payload != null ? (this.objectMapper.readValue(json, Map.class)).toString() : json;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return this.getClass().getName() + "@" + Integer.toHexString(this.hashCode());
        }
    }

}
