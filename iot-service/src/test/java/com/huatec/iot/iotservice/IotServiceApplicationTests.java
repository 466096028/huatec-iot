package com.huatec.iot.iotservice;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huatec.iot.common.entity.CategoryAbilitys;
import com.huatec.iot.common.entity.Products;
import com.huatec.iot.common.utils.DateUtils;
import com.huatec.iot.common.utils.UserUtils;
import com.huatec.iot.iotservice.mapper.CategoryAbilitysMapper;
import com.huatec.iot.iotservice.mapper.DevicesMapper;
import com.huatec.iot.iotservice.mapper.ProductsMapper;
import com.huatec.iot.iotservice.service.DeviceService;
import lombok.SneakyThrows;
import org.apache.ibatis.reflection.MetaObject;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.util.MetaObjectUtil;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class IotServiceApplicationTests {
    @Autowired
    DeviceService deviceService;

    @Autowired
    CategoryAbilitysMapper categoryAbilitysMapper;
    @Autowired
    ProductsMapper productsMapper;
    @Autowired
    DevicesMapper devicesMapper;
    //
    // @Autowired
    // CategoryAbilitysMapper categoryAbilitysMapper;
    //
    // @Autowired
    // private MqttProviderConfig providerClient;

    @Test
    void providerTest() {

        String str = "token=123456789；SSFSMS=24hjsdi2342";
        Pattern pattern = Pattern.compile("token=(\\d+)");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            String token = matcher.group(1);
            System.out.println(token);
        }

    }

    @Test
    void test1() {

        String s = "{\"schema\":\"https://iotx-tsl.oss-ap-southeast-1.aliyuncs.com/schema.json\",\"profile\":{\"version\":\"1.0\",\"productKey\":\"gwu118x6Iea\"},\"properties\":[{\"identifier\":\"CableTemperature_A\",\"name\":\"线缆温度_A相\",\"accessMode\":\"r\",\"required\":false,\"dataType\":{\"type\":\"float\",\"specs\":{\"min\":\"-10\",\"max\":\"60\",\"unit\":\"°C\",\"unitName\":\"摄氏度\",\"step\":\"0.1\"}}},{\"identifier\":\"CableTemperature_B\",\"name\":\"线缆温度_B相\",\"accessMode\":\"r\",\"required\":false,\"dataType\":{\"type\":\"float\",\"specs\":{\"min\":\"-10\",\"max\":\"60\",\"unit\":\"°C\",\"unitName\":\"摄氏度\",\"step\":\"0.1\"}}},{\"identifier\":\"CableTemperature_C\",\"name\":\"线缆温度_C相\",\"accessMode\":\"r\",\"required\":false,\"dataType\":{\"type\":\"float\",\"specs\":{\"min\":\"-10\",\"max\":\"60\",\"unit\":\"°C\",\"unitName\":\"摄氏度\",\"step\":\"0.1\"}}},{\"identifier\":\"SmokeSensorState\",\"name\":\"烟雾传感器检测状态\",\"accessMode\":\"r\",\"required\":false,\"dataType\":{\"type\":\"enum\",\"specs\":{\"0\":\"正常\",\"1\":\"检测到烟雾\"}}},{\"identifier\":\"SF6Content\",\"name\":\"六氟化硫浓度\",\"accessMode\":\"r\",\"required\":false,\"dataType\":{\"type\":\"int\",\"specs\":{\"min\":\"0\",\"max\":\"500\",\"unit\":\"ppm\",\"unitName\":\"百万分率\",\"step\":\"1\"}}},{\"identifier\":\"O2Content\",\"name\":\"氧气浓度\",\"accessMode\":\"r\",\"required\":false,\"dataType\":{\"type\":\"float\",\"specs\":{\"min\":\"0\",\"max\":\"30\",\"unit\":\"%\",\"unitName\":\"百分比\",\"step\":\"0.1\"}}},{\"identifier\":\"WaterSensorState\",\"name\":\"水浸检测状态\",\"accessMode\":\"r\",\"required\":false,\"dataType\":{\"type\":\"bool\",\"specs\":{\"0\":\"正常\",\"1\":\"有水浸入\"}}},{\"identifier\":\"AlarmControl\",\"name\":\"声光报警器控制\",\"accessMode\":\"rw\",\"required\":false,\"dataType\":{\"type\":\"bool\",\"specs\":{\"0\":\"关\",\"1\":\"开\"}}},{\"identifier\":\"AlarmState\",\"name\":\"声光报警器状态\",\"accessMode\":\"r\",\"required\":false,\"dataType\":{\"type\":\"enum\",\"specs\":{\"0\":\"正常\",\"1\":\"报警\"}}},{\"identifier\":\"ElectricalFireMonitoringSystemState\",\"name\":\"电气火灾监控探测器状态反馈\",\"accessMode\":\"r\",\"required\":false,\"dataType\":{\"type\":\"enum\",\"specs\":{\"0\":\"运行中\",\"1\":\"报警\",\"2\":\"故障\",\"3\":\"离线\",\"4\":\"脱扣\"}}},{\"identifier\":\"ElectricEnergyMeterState\",\"name\":\"三相智能电能表状态反馈\",\"accessMode\":\"r\",\"required\":false,\"dataType\":{\"type\":\"enum\",\"specs\":{\"0\":\"正常\",\"1\":\"故障\",\"2\":\"离线\"}}},{\"identifier\":\"TotalActiveEnergy\",\"name\":\"总有功电量\",\"accessMode\":\"r\",\"required\":false,\"dataType\":{\"type\":\"double\",\"specs\":{\"min\":\"0\",\"max\":\"99999\",\"unit\":\"kW·h\",\"unitName\":\"千瓦时\",\"step\":\"0.01\"}}},{\"identifier\":\"PositiveActiveEnergy\",\"name\":\"正向有功电量\",\"accessMode\":\"r\",\"required\":false,\"dataType\":{\"type\":\"double\",\"specs\":{\"min\":\"0\",\"max\":\"99999\",\"unit\":\"kW·h\",\"unitName\":\"千瓦时\",\"step\":\"0.01\"}}},{\"identifier\":\"ReverseActiveEnergy\",\"name\":\"反向有功电量\",\"accessMode\":\"r\",\"required\":false,\"dataType\":{\"type\":\"double\",\"specs\":{\"min\":\"0\",\"max\":\"99999\",\"unit\":\"kW·h\",\"unitName\":\"千瓦时\",\"step\":\"0.01\"}}},{\"identifier\":\"Ia\",\"name\":\"A相电流\",\"accessMode\":\"r\",\"required\":false,\"dataType\":{\"type\":\"double\",\"specs\":{\"min\":\"0\",\"max\":\"10000000000\",\"unit\":\"A\",\"unitName\":\"安培\",\"step\":\"0.01\"}}},{\"identifier\":\"Ib\",\"name\":\"B相电流\",\"accessMode\":\"r\",\"required\":false,\"dataType\":{\"type\":\"double\",\"specs\":{\"min\":\"0\",\"max\":\"10000000000\",\"unit\":\"A\",\"unitName\":\"安培\",\"step\":\"0.01\"}}},{\"identifier\":\"Ic\",\"name\":\"C相电流\",\"accessMode\":\"r\",\"required\":false,\"dataType\":{\"type\":\"double\",\"specs\":{\"min\":\"0\",\"max\":\"10000000000\",\"unit\":\"A\",\"unitName\":\"安培\",\"step\":\"0.01\"}}},{\"identifier\":\"nUa\",\"name\":\"A相电压\",\"accessMode\":\"r\",\"required\":false,\"dataType\":{\"type\":\"double\",\"specs\":{\"min\":\"0\",\"max\":\"1000\",\"unit\":\"V\",\"unitName\":\"伏特\",\"step\":\"0.01\"}}},{\"identifier\":\"nUb\",\"name\":\"B相电压\",\"accessMode\":\"r\",\"required\":false,\"dataType\":{\"type\":\"double\",\"specs\":{\"min\":\"0\",\"max\":\"1000\",\"unit\":\"V\",\"unitName\":\"伏特\",\"step\":\"0.01\"}}},{\"identifier\":\"nUc\",\"name\":\"C相电压\",\"accessMode\":\"r\",\"required\":false,\"dataType\":{\"type\":\"float\",\"specs\":{\"min\":\"0\",\"max\":\"1000\",\"unit\":\"V\",\"unitName\":\"伏特\",\"step\":\"0.1\"}}},{\"identifier\":\"electric_fra\",\"name\":\"A相有功功率\",\"accessMode\":\"r\",\"required\":false,\"dataType\":{\"type\":\"double\",\"specs\":{\"min\":\"0\",\"max\":\"10000000\",\"unit\":\"kW\",\"unitName\":\"千瓦特\",\"step\":\"0.01\"}}},{\"identifier\":\"electric_frb\",\"name\":\"B相有功功率\",\"accessMode\":\"r\",\"required\":false,\"dataType\":{\"type\":\"double\",\"specs\":{\"min\":\"0\",\"max\":\"1000000\",\"unit\":\"kW\",\"unitName\":\"千瓦特\",\"step\":\"0.01\"}}},{\"identifier\":\"electric_frc\",\"name\":\"C相有功功率\",\"accessMode\":\"r\",\"required\":false,\"dataType\":{\"type\":\"double\",\"specs\":{\"min\":\"0\",\"max\":\"100000000\",\"unit\":\"kW\",\"unitName\":\"千瓦特\",\"step\":\"0.01\"}}},{\"identifier\":\"electric_fr\",\"name\":\"频率\",\"accessMode\":\"r\",\"required\":false,\"dataType\":{\"type\":\"double\",\"specs\":{\"min\":\"0\",\"max\":\"80\",\"unit\":\"Hz\",\"unitName\":\"赫兹\",\"step\":\"0.01\"}}}],\"events\":[{\"identifier\":\"post\",\"name\":\"post\",\"type\":\"info\",\"required\":true,\"desc\":\"属性上报\",\"method\":\"thing.event.property.post\",\"outputData\":[{\"identifier\":\"CableTemperature_A\",\"name\":\"线缆温度_A相\",\"dataType\":{\"type\":\"float\",\"specs\":{\"min\":\"-10\",\"max\":\"60\",\"unit\":\"°C\",\"unitName\":\"摄氏度\",\"step\":\"0.1\"}}},{\"identifier\":\"CableTemperature_B\",\"name\":\"线缆温度_B相\",\"dataType\":{\"type\":\"float\",\"specs\":{\"min\":\"-10\",\"max\":\"60\",\"unit\":\"°C\",\"unitName\":\"摄氏度\",\"step\":\"0.1\"}}},{\"identifier\":\"CableTemperature_C\",\"name\":\"线缆温度_C相\",\"dataType\":{\"type\":\"float\",\"specs\":{\"min\":\"-10\",\"max\":\"60\",\"unit\":\"°C\",\"unitName\":\"摄氏度\",\"step\":\"0.1\"}}},{\"identifier\":\"SmokeSensorState\",\"name\":\"烟雾传感器检测状态\",\"dataType\":{\"type\":\"enum\",\"specs\":{\"0\":\"正常\",\"1\":\"检测到烟雾\"}}},{\"identifier\":\"SF6Content\",\"name\":\"六氟化硫浓度\",\"dataType\":{\"type\":\"int\",\"specs\":{\"min\":\"0\",\"max\":\"500\",\"unit\":\"ppm\",\"unitName\":\"百万分率\",\"step\":\"1\"}}},{\"identifier\":\"O2Content\",\"name\":\"氧气浓度\",\"dataType\":{\"type\":\"float\",\"specs\":{\"min\":\"0\",\"max\":\"30\",\"unit\":\"%\",\"unitName\":\"百分比\",\"step\":\"0.1\"}}},{\"identifier\":\"WaterSensorState\",\"name\":\"水浸检测状态\",\"dataType\":{\"type\":\"bool\",\"specs\":{\"0\":\"正常\",\"1\":\"有水浸入\"}}},{\"identifier\":\"AlarmControl\",\"name\":\"声光报警器控制\",\"dataType\":{\"type\":\"bool\",\"specs\":{\"0\":\"关\",\"1\":\"开\"}}},{\"identifier\":\"AlarmState\",\"name\":\"声光报警器状态\",\"dataType\":{\"type\":\"enum\",\"specs\":{\"0\":\"正常\",\"1\":\"报警\"}}},{\"identifier\":\"ElectricalFireMonitoringSystemState\",\"name\":\"电气火灾监控探测器状态反馈\",\"dataType\":{\"type\":\"enum\",\"specs\":{\"0\":\"运行中\",\"1\":\"报警\",\"2\":\"故障\",\"3\":\"离线\",\"4\":\"脱扣\"}}},{\"identifier\":\"ElectricEnergyMeterState\",\"name\":\"三相智能电能表状态反馈\",\"dataType\":{\"type\":\"enum\",\"specs\":{\"0\":\"正常\",\"1\":\"故障\",\"2\":\"离线\"}}},{\"identifier\":\"TotalActiveEnergy\",\"name\":\"总有功电量\",\"dataType\":{\"type\":\"double\",\"specs\":{\"min\":\"0\",\"max\":\"99999\",\"unit\":\"kW·h\",\"unitName\":\"千瓦时\",\"step\":\"0.01\"}}},{\"identifier\":\"PositiveActiveEnergy\",\"name\":\"正向有功电量\",\"dataType\":{\"type\":\"double\",\"specs\":{\"min\":\"0\",\"max\":\"99999\",\"unit\":\"kW·h\",\"unitName\":\"千瓦时\",\"step\":\"0.01\"}}},{\"identifier\":\"ReverseActiveEnergy\",\"name\":\"反向有功电量\",\"dataType\":{\"type\":\"double\",\"specs\":{\"min\":\"0\",\"max\":\"99999\",\"unit\":\"kW·h\",\"unitName\":\"千瓦时\",\"step\":\"0.01\"}}},{\"identifier\":\"Ia\",\"name\":\"A相电流\",\"dataType\":{\"type\":\"double\",\"specs\":{\"min\":\"0\",\"max\":\"10000000000\",\"unit\":\"A\",\"unitName\":\"安培\",\"step\":\"0.01\"}}},{\"identifier\":\"Ib\",\"name\":\"B相电流\",\"dataType\":{\"type\":\"double\",\"specs\":{\"min\":\"0\",\"max\":\"10000000000\",\"unit\":\"A\",\"unitName\":\"安培\",\"step\":\"0.01\"}}},{\"identifier\":\"Ic\",\"name\":\"C相电流\",\"dataType\":{\"type\":\"double\",\"specs\":{\"min\":\"0\",\"max\":\"10000000000\",\"unit\":\"A\",\"unitName\":\"安培\",\"step\":\"0.01\"}}},{\"identifier\":\"nUa\",\"name\":\"A相电压\",\"dataType\":{\"type\":\"double\",\"specs\":{\"min\":\"0\",\"max\":\"1000\",\"unit\":\"V\",\"unitName\":\"伏特\",\"step\":\"0.01\"}}},{\"identifier\":\"nUb\",\"name\":\"B相电压\",\"dataType\":{\"type\":\"double\",\"specs\":{\"min\":\"0\",\"max\":\"1000\",\"unit\":\"V\",\"unitName\":\"伏特\",\"step\":\"0.01\"}}},{\"identifier\":\"nUc\",\"name\":\"C相电压\",\"dataType\":{\"type\":\"float\",\"specs\":{\"min\":\"0\",\"max\":\"1000\",\"unit\":\"V\",\"unitName\":\"伏特\",\"step\":\"0.1\"}}},{\"identifier\":\"electric_fra\",\"name\":\"A相有功功率\",\"dataType\":{\"type\":\"double\",\"specs\":{\"min\":\"0\",\"max\":\"10000000\",\"unit\":\"kW\",\"unitName\":\"千瓦特\",\"step\":\"0.01\"}}},{\"identifier\":\"electric_frb\",\"name\":\"B相有功功率\",\"dataType\":{\"type\":\"double\",\"specs\":{\"min\":\"0\",\"max\":\"1000000\",\"unit\":\"kW\",\"unitName\":\"千瓦特\",\"step\":\"0.01\"}}},{\"identifier\":\"electric_frc\",\"name\":\"C相有功功率\",\"dataType\":{\"type\":\"double\",\"specs\":{\"min\":\"0\",\"max\":\"100000000\",\"unit\":\"kW\",\"unitName\":\"千瓦特\",\"step\":\"0.01\"}}},{\"identifier\":\"electric_fr\",\"name\":\"频率\",\"dataType\":{\"type\":\"double\",\"specs\":{\"min\":\"0\",\"max\":\"80\",\"unit\":\"Hz\",\"unitName\":\"赫兹\",\"step\":\"0.01\"}}}]}],\"services\":[{\"identifier\":\"set\",\"name\":\"set\",\"required\":true,\"callType\":\"async\",\"desc\":\"属性设置\",\"method\":\"thing.service.property.set\",\"inputData\":[{\"identifier\":\"AlarmControl\",\"name\":\"声光报警器控制\",\"dataType\":{\"type\":\"bool\",\"specs\":{\"0\":\"关\",\"1\":\"开\"}}}],\"outputData\":[]},{\"identifier\":\"get\",\"name\":\"get\",\"required\":true,\"callType\":\"async\",\"desc\":\"属性获取\",\"method\":\"thing.service.property.get\",\"inputData\":[\"CableTemperature_A\",\"CableTemperature_B\",\"CableTemperature_C\",\"SmokeSensorState\",\"SF6Content\",\"O2Content\",\"WaterSensorState\",\"AlarmControl\",\"AlarmState\",\"ElectricalFireMonitoringSystemState\",\"ElectricEnergyMeterState\",\"TotalActiveEnergy\",\"PositiveActiveEnergy\",\"ReverseActiveEnergy\",\"Ia\",\"Ib\",\"Ic\",\"nUa\",\"nUb\",\"nUc\",\"electric_fra\",\"electric_frb\",\"electric_frc\",\"electric_fr\"],\"outputData\":[{\"identifier\":\"CableTemperature_A\",\"name\":\"线缆温度_A相\",\"dataType\":{\"type\":\"float\",\"specs\":{\"min\":\"-10\",\"max\":\"60\",\"unit\":\"°C\",\"unitName\":\"摄氏度\",\"step\":\"0.1\"}}},{\"identifier\":\"CableTemperature_B\",\"name\":\"线缆温度_B相\",\"dataType\":{\"type\":\"float\",\"specs\":{\"min\":\"-10\",\"max\":\"60\",\"unit\":\"°C\",\"unitName\":\"摄氏度\",\"step\":\"0.1\"}}},{\"identifier\":\"CableTemperature_C\",\"name\":\"线缆温度_C相\",\"dataType\":{\"type\":\"float\",\"specs\":{\"min\":\"-10\",\"max\":\"60\",\"unit\":\"°C\",\"unitName\":\"摄氏度\",\"step\":\"0.1\"}}},{\"identifier\":\"SmokeSensorState\",\"name\":\"烟雾传感器检测状态\",\"dataType\":{\"type\":\"enum\",\"specs\":{\"0\":\"正常\",\"1\":\"检测到烟雾\"}}},{\"identifier\":\"SF6Content\",\"name\":\"六氟化硫浓度\",\"dataType\":{\"type\":\"int\",\"specs\":{\"min\":\"0\",\"max\":\"500\",\"unit\":\"ppm\",\"unitName\":\"百万分率\",\"step\":\"1\"}}},{\"identifier\":\"O2Content\",\"name\":\"氧气浓度\",\"dataType\":{\"type\":\"float\",\"specs\":{\"min\":\"0\",\"max\":\"30\",\"unit\":\"%\",\"unitName\":\"百分比\",\"step\":\"0.1\"}}},{\"identifier\":\"WaterSensorState\",\"name\":\"水浸检测状态\",\"dataType\":{\"type\":\"bool\",\"specs\":{\"0\":\"正常\",\"1\":\"有水浸入\"}}},{\"identifier\":\"AlarmControl\",\"name\":\"声光报警器控制\",\"dataType\":{\"type\":\"bool\",\"specs\":{\"0\":\"关\",\"1\":\"开\"}}},{\"identifier\":\"AlarmState\",\"name\":\"声光报警器状态\",\"dataType\":{\"type\":\"enum\",\"specs\":{\"0\":\"正常\",\"1\":\"报警\"}}},{\"identifier\":\"ElectricalFireMonitoringSystemState\",\"name\":\"电气火灾监控探测器状态反馈\",\"dataType\":{\"type\":\"enum\",\"specs\":{\"0\":\"运行中\",\"1\":\"报警\",\"2\":\"故障\",\"3\":\"离线\",\"4\":\"脱扣\"}}},{\"identifier\":\"ElectricEnergyMeterState\",\"name\":\"三相智能电能表状态反馈\",\"dataType\":{\"type\":\"enum\",\"specs\":{\"0\":\"正常\",\"1\":\"故障\",\"2\":\"离线\"}}},{\"identifier\":\"TotalActiveEnergy\",\"name\":\"总有功电量\",\"dataType\":{\"type\":\"double\",\"specs\":{\"min\":\"0\",\"max\":\"99999\",\"unit\":\"kW·h\",\"unitName\":\"千瓦时\",\"step\":\"0.01\"}}},{\"identifier\":\"PositiveActiveEnergy\",\"name\":\"正向有功电量\",\"dataType\":{\"type\":\"double\",\"specs\":{\"min\":\"0\",\"max\":\"99999\",\"unit\":\"kW·h\",\"unitName\":\"千瓦时\",\"step\":\"0.01\"}}},{\"identifier\":\"ReverseActiveEnergy\",\"name\":\"反向有功电量\",\"dataType\":{\"type\":\"double\",\"specs\":{\"min\":\"0\",\"max\":\"99999\",\"unit\":\"kW·h\",\"unitName\":\"千瓦时\",\"step\":\"0.01\"}}},{\"identifier\":\"Ia\",\"name\":\"A相电流\",\"dataType\":{\"type\":\"double\",\"specs\":{\"min\":\"0\",\"max\":\"10000000000\",\"unit\":\"A\",\"unitName\":\"安培\",\"step\":\"0.01\"}}},{\"identifier\":\"Ib\",\"name\":\"B相电流\",\"dataType\":{\"type\":\"double\",\"specs\":{\"min\":\"0\",\"max\":\"10000000000\",\"unit\":\"A\",\"unitName\":\"安培\",\"step\":\"0.01\"}}},{\"identifier\":\"Ic\",\"name\":\"C相电流\",\"dataType\":{\"type\":\"double\",\"specs\":{\"min\":\"0\",\"max\":\"10000000000\",\"unit\":\"A\",\"unitName\":\"安培\",\"step\":\"0.01\"}}},{\"identifier\":\"nUa\",\"name\":\"A相电压\",\"dataType\":{\"type\":\"double\",\"specs\":{\"min\":\"0\",\"max\":\"1000\",\"unit\":\"V\",\"unitName\":\"伏特\",\"step\":\"0.01\"}}},{\"identifier\":\"nUb\",\"name\":\"B相电压\",\"dataType\":{\"type\":\"double\",\"specs\":{\"min\":\"0\",\"max\":\"1000\",\"unit\":\"V\",\"unitName\":\"伏特\",\"step\":\"0.01\"}}},{\"identifier\":\"nUc\",\"name\":\"C相电压\",\"dataType\":{\"type\":\"float\",\"specs\":{\"min\":\"0\",\"max\":\"1000\",\"unit\":\"V\",\"unitName\":\"伏特\",\"step\":\"0.1\"}}},{\"identifier\":\"electric_fra\",\"name\":\"A相有功功率\",\"dataType\":{\"type\":\"double\",\"specs\":{\"min\":\"0\",\"max\":\"10000000\",\"unit\":\"kW\",\"unitName\":\"千瓦特\",\"step\":\"0.01\"}}},{\"identifier\":\"electric_frb\",\"name\":\"B相有功功率\",\"dataType\":{\"type\":\"double\",\"specs\":{\"min\":\"0\",\"max\":\"1000000\",\"unit\":\"kW\",\"unitName\":\"千瓦特\",\"step\":\"0.01\"}}},{\"identifier\":\"electric_frc\",\"name\":\"C相有功功率\",\"dataType\":{\"type\":\"double\",\"specs\":{\"min\":\"0\",\"max\":\"100000000\",\"unit\":\"kW\",\"unitName\":\"千瓦特\",\"step\":\"0.01\"}}},{\"identifier\":\"electric_fr\",\"name\":\"频率\",\"dataType\":{\"type\":\"double\",\"specs\":{\"min\":\"0\",\"max\":\"80\",\"unit\":\"Hz\",\"unitName\":\"赫兹\",\"step\":\"0.01\"}}}]}]}";
        JSONObject jsonObject = JSONObject.parseObject(s);
        JSONArray properties = jsonObject.getJSONArray("properties");
        for (int i = 0; i < properties.size(); i++) {
            CategoryAbilitys categoryAbilitys = new CategoryAbilitys();
            JSONObject jsonObject1 = properties.getJSONObject(i);
            categoryAbilitys.setAbilityIdentifier(jsonObject1.getString("identifier"));
            categoryAbilitys.setAbilityName(jsonObject1.getString("name"));
            categoryAbilitys.setAbilityType((byte) 1);
            categoryAbilitys.setCategoryId(3);
            categoryAbilitys.setCategoryName("电力监控");
            categoryAbilitys.setCategoryKey("Electric_Monitor");
            categoryAbilitys.setIsRequired((byte) (jsonObject1.getString("required") == "true" ? 1 : 0));
            JSONObject dataType = jsonObject1.getJSONObject("dataType");
            categoryAbilitys.setDataType(dataType.getString("type"));
            categoryAbilitys.setIsStandard((byte) 1);
            categoryAbilitys.setDescription("");
            categoryAbilitys.setIsDel(0);
            switch (dataType.getString("type")) {
                case "int":
                case "double":
                case "float":
                    categoryAbilitys.setDataSpecs(dataType.getString("specs"));
                    break;
                case "bool":
                case "enum":
                    JSONObject specs = dataType.getJSONObject("specs");
                    ArrayList<Object> objects = new ArrayList<>();
                    for (int j = 0; j < specs.size(); j++) {
                        if (specs.containsKey(String.valueOf(j))) {
                            HashMap<String, String> stringStringHashMap = new HashMap<>();
                            stringStringHashMap.put("name", String.valueOf(j));
                            stringStringHashMap.put("value", specs.getString(String.valueOf(j)));
                            objects.add(stringStringHashMap);
                        }
                    }
                    categoryAbilitys.setDataSpecs(JSONObject.toJSONString(objects));
                    break;

            }

            int insert = categoryAbilitysMapper.insertSelective(categoryAbilitys);
            System.out.println("inserStatus:" + insert);
        }
    }

    @Test
    void test() {
        String s = "{\"schema\":\"https://iotx-tsl.oss-ap-southeast-1.aliyuncs.com/schema.json\",\"profile\":{\"version\":\"1.0\",\"productKey\":\"gwu1ipsWW0l\"},\"properties\":[{\"identifier\":\"MainLineRunningState\",\"name\":\"总线运行状态\",\"accessMode\":\"r\",\"required\":false,\"dataType\":{\"type\":\"enum\",\"specs\":{\"0\":\"停机\",\"1\":\"运行中\",\"2\":\"报警\",\"3\":\"检修\"}}},{\"identifier\":\"MainLineControl\",\"name\":\"总线自动控制\",\"accessMode\":\"rw\",\"required\":false,\"dataType\":{\"type\":\"bool\",\"specs\":{\"0\":\"停止\",\"1\":\"启动\"}}},{\"identifier\":\"SubLine_01State\",\"name\":\"仪表台安装线运行状态\",\"accessMode\":\"r\",\"required\":false,\"dataType\":{\"type\":\"enum\",\"specs\":{\"0\":\"停机\",\"1\":\"运行中\",\"2\":\"报警\",\"3\":\"检修\"}}},{\"identifier\":\"SubLine_02State\",\"name\":\"前后玻璃安装线运行状态\",\"accessMode\":\"r\",\"required\":false,\"dataType\":{\"type\":\"enum\",\"specs\":{\"0\":\"停机\",\"1\":\"运行中\",\"2\":\"报警\",\"3\":\"检修\"}}},{\"identifier\":\"SubLine_03State\",\"name\":\"动力总成及底盘与车身合装线运行状态\",\"accessMode\":\"r\",\"required\":false,\"dataType\":{\"type\":\"enum\",\"specs\":{\"0\":\"停机\",\"1\":\"运行中\",\"2\":\"报警\",\"3\":\"检修\"}}},{\"identifier\":\"SubLine_04State\",\"name\":\"座椅车门安装线运行状态\",\"accessMode\":\"r\",\"required\":false,\"dataType\":{\"type\":\"enum\",\"specs\":{\"0\":\"停机\",\"1\":\"运行中\",\"2\":\"报警\",\"3\":\"检修\"}}},{\"identifier\":\"SubLine_05State\",\"name\":\"轮胎安装线运行状态\",\"accessMode\":\"r\",\"required\":false,\"dataType\":{\"type\":\"enum\",\"specs\":{\"0\":\"停机\",\"1\":\"运行中\",\"2\":\"报警\",\"3\":\"检修\"}}},{\"identifier\":\"SubLine_06State\",\"name\":\"前后仓盖安装线运行状态\",\"accessMode\":\"r\",\"required\":false,\"dataType\":{\"type\":\"enum\",\"specs\":{\"0\":\"停机\",\"1\":\"运行中\",\"2\":\"报警\",\"3\":\"检修\"}}},{\"identifier\":\"DiagnosticTestStationState\",\"name\":\"诊断测试站运行状态\",\"accessMode\":\"r\",\"required\":false,\"dataType\":{\"type\":\"enum\",\"specs\":{\"0\":\"停机\",\"1\":\"运行中\",\"2\":\"报警\",\"3\":\"检修\"}}},{\"identifier\":\"RainTestInspectionState\",\"name\":\"淋雨检测站运行状态\",\"accessMode\":\"r\",\"required\":false,\"dataType\":{\"type\":\"enum\",\"specs\":{\"0\":\"停机\",\"1\":\"运行中\",\"2\":\"报警\",\"3\":\"检修\"}}}],\"events\":[{\"identifier\":\"post\",\"name\":\"post\",\"type\":\"info\",\"required\":true,\"desc\":\"属性上报\",\"method\":\"thing.event.property.post\",\"outputData\":[{\"identifier\":\"MainLineRunningState\",\"name\":\"总线运行状态\",\"dataType\":{\"type\":\"enum\",\"specs\":{\"0\":\"停机\",\"1\":\"运行中\",\"2\":\"报警\",\"3\":\"检修\"}}},{\"identifier\":\"MainLineControl\",\"name\":\"总线自动控制\",\"dataType\":{\"type\":\"bool\",\"specs\":{\"0\":\"停止\",\"1\":\"启动\"}}},{\"identifier\":\"SubLine_01State\",\"name\":\"仪表台安装线运行状态\",\"dataType\":{\"type\":\"enum\",\"specs\":{\"0\":\"停机\",\"1\":\"运行中\",\"2\":\"报警\",\"3\":\"检修\"}}},{\"identifier\":\"SubLine_02State\",\"name\":\"前后玻璃安装线运行状态\",\"dataType\":{\"type\":\"enum\",\"specs\":{\"0\":\"停机\",\"1\":\"运行中\",\"2\":\"报警\",\"3\":\"检修\"}}},{\"identifier\":\"SubLine_03State\",\"name\":\"动力总成及底盘与车身合装线运行状态\",\"dataType\":{\"type\":\"enum\",\"specs\":{\"0\":\"停机\",\"1\":\"运行中\",\"2\":\"报警\",\"3\":\"检修\"}}},{\"identifier\":\"SubLine_04State\",\"name\":\"座椅车门安装线运行状态\",\"dataType\":{\"type\":\"enum\",\"specs\":{\"0\":\"停机\",\"1\":\"运行中\",\"2\":\"报警\",\"3\":\"检修\"}}},{\"identifier\":\"SubLine_05State\",\"name\":\"轮胎安装线运行状态\",\"dataType\":{\"type\":\"enum\",\"specs\":{\"0\":\"停机\",\"1\":\"运行中\",\"2\":\"报警\",\"3\":\"检修\"}}},{\"identifier\":\"SubLine_06State\",\"name\":\"前后仓盖安装线运行状态\",\"dataType\":{\"type\":\"enum\",\"specs\":{\"0\":\"停机\",\"1\":\"运行中\",\"2\":\"报警\",\"3\":\"检修\"}}},{\"identifier\":\"DiagnosticTestStationState\",\"name\":\"诊断测试站运行状态\",\"dataType\":{\"type\":\"enum\",\"specs\":{\"0\":\"停机\",\"1\":\"运行中\",\"2\":\"报警\",\"3\":\"检修\"}}},{\"identifier\":\"RainTestInspectionState\",\"name\":\"淋雨检测站运行状态\",\"dataType\":{\"type\":\"enum\",\"specs\":{\"0\":\"停机\",\"1\":\"运行中\",\"2\":\"报警\",\"3\":\"检修\"}}}]}],\"services\":[{\"identifier\":\"set\",\"name\":\"set\",\"required\":true,\"callType\":\"async\",\"desc\":\"属性设置\",\"method\":\"thing.service.property.set\",\"inputData\":[{\"identifier\":\"MainLineControl\",\"name\":\"总线自动控制\",\"dataType\":{\"type\":\"bool\",\"specs\":{\"0\":\"停止\",\"1\":\"启动\"}}}],\"outputData\":[]},{\"identifier\":\"get\",\"name\":\"get\",\"required\":true,\"callType\":\"async\",\"desc\":\"属性获取\",\"method\":\"thing.service.property.get\",\"inputData\":[\"MainLineRunningState\",\"MainLineControl\",\"SubLine_01State\",\"SubLine_02State\",\"SubLine_03State\",\"SubLine_04State\",\"SubLine_05State\",\"SubLine_06State\",\"DiagnosticTestStationState\",\"RainTestInspectionState\"],\"outputData\":[{\"identifier\":\"MainLineRunningState\",\"name\":\"总线运行状态\",\"dataType\":{\"type\":\"enum\",\"specs\":{\"0\":\"停机\",\"1\":\"运行中\",\"2\":\"报警\",\"3\":\"检修\"}}},{\"identifier\":\"MainLineControl\",\"name\":\"总线自动控制\",\"dataType\":{\"type\":\"bool\",\"specs\":{\"0\":\"停止\",\"1\":\"启动\"}}},{\"identifier\":\"SubLine_01State\",\"name\":\"仪表台安装线运行状态\",\"dataType\":{\"type\":\"enum\",\"specs\":{\"0\":\"停机\",\"1\":\"运行中\",\"2\":\"报警\",\"3\":\"检修\"}}},{\"identifier\":\"SubLine_02State\",\"name\":\"前后玻璃安装线运行状态\",\"dataType\":{\"type\":\"enum\",\"specs\":{\"0\":\"停机\",\"1\":\"运行中\",\"2\":\"报警\",\"3\":\"检修\"}}},{\"identifier\":\"SubLine_03State\",\"name\":\"动力总成及底盘与车身合装线运行状态\",\"dataType\":{\"type\":\"enum\",\"specs\":{\"0\":\"停机\",\"1\":\"运行中\",\"2\":\"报警\",\"3\":\"检修\"}}},{\"identifier\":\"SubLine_04State\",\"name\":\"座椅车门安装线运行状态\",\"dataType\":{\"type\":\"enum\",\"specs\":{\"0\":\"停机\",\"1\":\"运行中\",\"2\":\"报警\",\"3\":\"检修\"}}},{\"identifier\":\"SubLine_05State\",\"name\":\"轮胎安装线运行状态\",\"dataType\":{\"type\":\"enum\",\"specs\":{\"0\":\"停机\",\"1\":\"运行中\",\"2\":\"报警\",\"3\":\"检修\"}}},{\"identifier\":\"SubLine_06State\",\"name\":\"前后仓盖安装线运行状态\",\"dataType\":{\"type\":\"enum\",\"specs\":{\"0\":\"停机\",\"1\":\"运行中\",\"2\":\"报警\",\"3\":\"检修\"}}},{\"identifier\":\"DiagnosticTestStationState\",\"name\":\"诊断测试站运行状态\",\"dataType\":{\"type\":\"enum\",\"specs\":{\"0\":\"停机\",\"1\":\"运行中\",\"2\":\"报警\",\"3\":\"检修\"}}},{\"identifier\":\"RainTestInspectionState\",\"name\":\"淋雨检测站运行状态\",\"dataType\":{\"type\":\"enum\",\"specs\":{\"0\":\"停机\",\"1\":\"运行中\",\"2\":\"报警\",\"3\":\"检修\"}}}]}]}";
        JSONObject jsonObject = JSONObject.parseObject(s);
        JSONArray properties = jsonObject.getJSONArray("properties");
        for (int i = 0; i < properties.size(); i++) {
            CategoryAbilitys categoryAbilitys = new CategoryAbilitys();
            JSONObject jsonObject1 = properties.getJSONObject(i);
            categoryAbilitys.setAbilityIdentifier(jsonObject1.getString("identifier"));
            categoryAbilitys.setAbilityName(jsonObject1.getString("name"));
            categoryAbilitys.setAbilityType((byte) 1);
            categoryAbilitys.setCategoryId(6);
            categoryAbilitys.setCategoryName("主线自动化控制");
            categoryAbilitys.setCategoryKey("Main_Line_Auto_Controller");
            categoryAbilitys.setIsRequired((byte) (jsonObject1.getString("required") == "true" ? 1 : 0));
            JSONObject dataType = jsonObject1.getJSONObject("dataType");
            categoryAbilitys.setDataType(dataType.getString("type"));
            categoryAbilitys.setIsStandard((byte) 1);
            categoryAbilitys.setDescription("");
            categoryAbilitys.setIsDel(0);
            switch (dataType.getString("type")) {
                case "int":
                case "double":
                case "float":
                    categoryAbilitys.setDataSpecs(dataType.getString("specs"));
                    break;
                case "bool":
                case "enum":
                    JSONObject specs = dataType.getJSONObject("specs");
                    ArrayList<Object> objects = new ArrayList<>();
                    for (int j = 0; j < specs.size(); j++) {
                        if (specs.containsKey(String.valueOf(j))) {
                            HashMap<String, String> stringStringHashMap = new HashMap<>();
                            stringStringHashMap.put("name", String.valueOf(j));
                            stringStringHashMap.put("value", specs.getString(String.valueOf(j)));
                            objects.add(stringStringHashMap);
                        }
                    }
                    categoryAbilitys.setDataSpecs(JSONObject.toJSONString(objects));
                    break;

            }

            int insert = categoryAbilitysMapper.insertSelective(categoryAbilitys);
            System.out.println("inserStatus:" + insert);


        }

    }

    @Test
    void test2() {
        String str = "[{\"Symbol\":\"mg/kg\",\"Name\":\"毫克每千克\"},{\"Symbol\":\"NTU\",\"Name\":\"浊度\"},{\"Symbol\":\"pH\",\"Name\":\"PH值\"},{\"Symbol\":\"dS/m\",\"Name\":\"土壤EC值\"},{\"Symbol\":\"W/㎡\",\"Name\":\"太阳总辐射\"},{\"Symbol\":\"mm/hour\",\"Name\":\"降雨量\"},{\"Symbol\":\"var\",\"Name\":\"乏\"},{\"Symbol\":\"cP\",\"Name\":\"厘泊\"},{\"Symbol\":\"aw\",\"Name\":\"饱和度\"},{\"Symbol\":\"pcs\",\"Name\":\"个\"},{\"Symbol\":\"cst\",\"Name\":\"厘斯\"},{\"Symbol\":\"bar\",\"Name\":\"巴\"},{\"Symbol\":\"ppt\",\"Name\":\"纳克每升\"},{\"Symbol\":\"ppb\",\"Name\":\"微克每升\"},{\"Symbol\":\"uS/cm\",\"Name\":\"微西每厘米\"},{\"Symbol\":\"N/C\",\"Name\":\"牛顿每库仑\"},{\"Symbol\":\"V/m\",\"Name\":\"伏特每米\"},{\"Symbol\":\"ml/min\",\"Name\":\"滴速\"},{\"Symbol\":\"mmHg\",\"Name\":\"血压\"},{\"Symbol\":\"mmol/L\",\"Name\":\"血糖\"},{\"Symbol\":\"mm/s\",\"Name\":\"毫米每秒\"},{\"Symbol\":\"turn/m\",\"Name\":\"转每分钟\"},{\"Symbol\":\"count\",\"Name\":\"次\"},{\"Symbol\":\"gear\",\"Name\":\"档\"},{\"Symbol\":\"stepCount\",\"Name\":\"步\"},{\"Symbol\":\"Nm3/h\",\"Name\":\"标准立方米每小时\"},{\"Symbol\":\"kV\",\"Name\":\"千伏\"},{\"Symbol\":\"kVA\",\"Name\":\"千伏安\"},{\"Symbol\":\"kVar\",\"Name\":\"千乏\"},{\"Symbol\":\"uw/cm2\",\"Name\":\"微瓦每平方厘米\"},{\"Symbol\":\"只\",\"Name\":\"只\"},{\"Symbol\":\"%RH\",\"Name\":\"相对湿度\"},{\"Symbol\":\"m³/s\",\"Name\":\"立方米每秒\"},{\"Symbol\":\"kg/s\",\"Name\":\"公斤每秒\"},{\"Symbol\":\"r/min\",\"Name\":\"转每分钟\"},{\"Symbol\":\"t/h\",\"Name\":\"吨每小时\"},{\"Symbol\":\"KCL/h\",\"Name\":\"千卡每小时\"},{\"Symbol\":\"L/s\",\"Name\":\"升每秒\"},{\"Symbol\":\"Mpa\",\"Name\":\"兆帕\"},{\"Symbol\":\"m³/h\",\"Name\":\"立方米每小时\"},{\"Symbol\":\"kvarh\",\"Name\":\"千乏时\"},{\"Symbol\":\"μg/L\",\"Name\":\"微克每升\"},{\"Symbol\":\"kcal\",\"Name\":\"千卡路里\"},{\"Symbol\":\"GB\",\"Name\":\"吉字节\"},{\"Symbol\":\"MB\",\"Name\":\"兆字节\"},{\"Symbol\":\"KB\",\"Name\":\"千字节\"},{\"Symbol\":\"B\",\"Name\":\"字节\"},{\"Symbol\":\"μg/(d㎡·d)\",\"Name\":\"微克每平方分米每天\"},{\"Symbol\":\"\",\"Name\":\"无\"},{\"Symbol\":\"ppm\",\"Name\":\"百万分率\"},{\"Symbol\":\"pixel\",\"Name\":\"像素\"},{\"Symbol\":\"Lux\",\"Name\":\"照度\"},{\"Symbol\":\"grav\",\"Name\":\"重力加速度\"},{\"Symbol\":\"dB\",\"Name\":\"分贝\"},{\"Symbol\":\"%\",\"Name\":\"百分比\"},{\"Symbol\":\"lm\",\"Name\":\"流明\"},{\"Symbol\":\"bit\",\"Name\":\"比特\"},{\"Symbol\":\"g/mL\",\"Name\":\"克每毫升\"},{\"Symbol\":\"g/L\",\"Name\":\"克每升\"},{\"Symbol\":\"mg/L\",\"Name\":\"毫克每升\"},{\"Symbol\":\"μg/m³\",\"Name\":\"微克每立方米\"},{\"Symbol\":\"mg/m³\",\"Name\":\"毫克每立方米\"},{\"Symbol\":\"g/m³\",\"Name\":\"克每立方米\"},{\"Symbol\":\"kg/m³\",\"Name\":\"千克每立方米\"},{\"Symbol\":\"nF\",\"Name\":\"纳法\"},{\"Symbol\":\"pF\",\"Name\":\"皮法\"},{\"Symbol\":\"μF\",\"Name\":\"微法\"},{\"Symbol\":\"F\",\"Name\":\"法拉\"},{\"Symbol\":\"Ω\",\"Name\":\"欧姆\"},{\"Symbol\":\"μA\",\"Name\":\"微安\"},{\"Symbol\":\"mA\",\"Name\":\"毫安\"},{\"Symbol\":\"kA\",\"Name\":\"千安\"},{\"Symbol\":\"A\",\"Name\":\"安培\"},{\"Symbol\":\"mV\",\"Name\":\"毫伏\"},{\"Symbol\":\"V\",\"Name\":\"伏特\"},{\"Symbol\":\"ms\",\"Name\":\"毫秒\"},{\"Symbol\":\"s\",\"Name\":\"秒\"},{\"Symbol\":\"min\",\"Name\":\"分钟\"},{\"Symbol\":\"h\",\"Name\":\"小时\"},{\"Symbol\":\"day\",\"Name\":\"日\"},{\"Symbol\":\"week\",\"Name\":\"周\"},{\"Symbol\":\"month\",\"Name\":\"月\"},{\"Symbol\":\"year\",\"Name\":\"年\"},{\"Symbol\":\"kn\",\"Name\":\"节\"},{\"Symbol\":\"km/h\",\"Name\":\"千米每小时\"},{\"Symbol\":\"m/s\",\"Name\":\"米每秒\"},{\"Symbol\":\"″\",\"Name\":\"秒\"},{\"Symbol\":\"′\",\"Name\":\"分\"},{\"Symbol\":\"°\",\"Name\":\"度\"},{\"Symbol\":\"rad\",\"Name\":\"弧度\"},{\"Symbol\":\"Hz\",\"Name\":\"赫兹\"},{\"Symbol\":\"μW\",\"Name\":\"微瓦\"},{\"Symbol\":\"mW\",\"Name\":\"毫瓦\"},{\"Symbol\":\"kW\",\"Name\":\"千瓦特\"},{\"Symbol\":\"W\",\"Name\":\"瓦特\"},{\"Symbol\":\"cal\",\"Name\":\"卡路里\"},{\"Symbol\":\"kW·h\",\"Name\":\"千瓦时\"},{\"Symbol\":\"Wh\",\"Name\":\"瓦时\"},{\"Symbol\":\"eV\",\"Name\":\"电子伏\"},{\"Symbol\":\"kJ\",\"Name\":\"千焦\"},{\"Symbol\":\"J\",\"Name\":\"焦耳\"},{\"Symbol\":\"℉\",\"Name\":\"华氏度\"},{\"Symbol\":\"K\",\"Name\":\"开尔文\"},{\"Symbol\":\"t\",\"Name\":\"吨\"},{\"Symbol\":\"°C\",\"Name\":\"摄氏度\"},{\"Symbol\":\"mPa\",\"Name\":\"毫帕\"},{\"Symbol\":\"hPa\",\"Name\":\"百帕\"},{\"Symbol\":\"kPa\",\"Name\":\"千帕\"},{\"Symbol\":\"Pa\",\"Name\":\"帕斯卡\"},{\"Symbol\":\"mg\",\"Name\":\"毫克\"},{\"Symbol\":\"g\",\"Name\":\"克\"},{\"Symbol\":\"kg\",\"Name\":\"千克\"},{\"Symbol\":\"N\",\"Name\":\"牛\"},{\"Symbol\":\"mL\",\"Name\":\"毫升\"},{\"Symbol\":\"L\",\"Name\":\"升\"},{\"Symbol\":\"mm³\",\"Name\":\"立方毫米\"},{\"Symbol\":\"cm³\",\"Name\":\"立方厘米\"},{\"Symbol\":\"km³\",\"Name\":\"立方千米\"},{\"Symbol\":\"m³\",\"Name\":\"立方米\"},{\"Symbol\":\"h㎡\",\"Name\":\"公顷\"},{\"Symbol\":\"c㎡\",\"Name\":\"平方厘米\"},{\"Symbol\":\"m㎡\",\"Name\":\"平方毫米\"},{\"Symbol\":\"k㎡\",\"Name\":\"平方千米\"},{\"Symbol\":\"㎡\",\"Name\":\"平方米\"},{\"Symbol\":\"nm\",\"Name\":\"纳米\"},{\"Symbol\":\"μm\",\"Name\":\"微米\"},{\"Symbol\":\"mm\",\"Name\":\"毫米\"},{\"Symbol\":\"cm\",\"Name\":\"厘米\"},{\"Symbol\":\"dm\",\"Name\":\"分米\"},{\"Symbol\":\"km\",\"Name\":\"千米\"},{\"Symbol\":\"m\",\"Name\":\"米\"}]";
        JSONArray objects = JSONObject.parseArray(str);

        for (int i = 0; i < objects.size(); i++) {
            JSONObject jsonObject = objects.getJSONObject(i);

            System.out.print(jsonObject.get("Symbol").toString().replace("/", "_").toUpperCase() + "_TYPE(\"" + jsonObject.get("Symbol").toString() + "\", \"整数型" + jsonObject.get("Name").toString() + "\");");
        }
    }

    @Test
    void tet3() {
        HashMap<String, String> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("μg/(d㎡·d)", "123123");
        System.out.println(objectObjectHashMap.get("μg/(d㎡·d)"));
    }

    @Test
    void test4() {
        Products products = new Products();
        products.setCreateUserId(58);

        List<Products> select = productsMapper.select(products);
        System.out.println(JSONObject.toJSONString(select));
    }

    @Test
    void test5() {
        // System.out.println("starttime:"+DateUtils.getCurrentTime());
        // System.out.println("endtime:"+UserUtils.EXPIRATION_TIME);
        // if (DateUtils.getCurrentTime() > UserUtils.EXPIRATION_TIME) {
        //     System.out.println(1111111);
        // }
        // System.out.println(222222222);

        String param = "{\"a\":\"1\",\"b\":\"2\"}";
        MetaObject metaObject = MetaObjectUtil.forObject(param);
        String[] properties = metaObject.getGetterNames();
        // for (String property : properties) {
        //     //属性和列对应Map中有此属性
        //     if (propertyMap.get(property) != null) {
        //         Object value = metaObject.getValue(property);
        //         //属性值不为空
        //         if (value != null) {
        //             orEqualTo(property, value);
        //         } else {
        //             orIsNull(property);
        //         }
        //     }
        // }
        System.out.println(properties);
    }

    @Test
    void test6() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("AAA", "QQQQ");
        System.out.println(map);
    }

    class Task implements Runnable {
        @SneakyThrows
        @Override
        public void run() {
            System.out.println("线程池："+Thread.currentThread().getName());
            //创建MQTT客户端对象
            MqttClient client = new MqttClient("tcp://10.10.34.14:1883", "test_"+UUID.randomUUID(), new MemoryPersistence());
            //连接设置
            MqttConnectOptions options = new MqttConnectOptions();
            //是否清空session，设置false表示服务器会保留客户端的连接记录（订阅主题，qos）,客户端重连之后能获取到服务器在客户端断开连接期间推送的消息
            //设置为true表示每次连接服务器都是以新的身份
            options.setCleanSession(true);
            //设置连接用户名
            options.setUserName("huatec");
            //设置连接密码
            options.setPassword("huatec".toCharArray());
            //设置超时时间，单位为秒
            options.setConnectionTimeout(100);
            //设置心跳时间 单位为秒，表示服务器每隔 1.5*20秒的时间向客户端发送心跳判断客户端是否在线
            options.setKeepAliveInterval(100);

            //设置回调
            // client.setCallback(mqttCallBack);
            client.connect(options);
        }
    }

    @Test
    void test7() {
        try {
            ExecutorService es = Executors.newFixedThreadPool(10);
            for (int i = 0; i < 100; i++) {
                es.submit(new Task());
            }

            System.out.println("我睡一会");
            while (true) {
                Thread.sleep(100000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void test8() {
        Double d = 10000000.1d;
        System.out.println(d);
    }
}
