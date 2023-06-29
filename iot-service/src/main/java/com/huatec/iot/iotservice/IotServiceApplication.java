package com.huatec.iot.iotservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = "com.huatec.iot")
@MapperScan(basePackages = {"com.huatec.iot.iotservice.mapper"})
public class IotServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotServiceApplication.class, args);
    }

}
