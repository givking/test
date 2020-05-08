package com.example.aliconsumer.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.example.aliconsumer.dao.User;
import com.example.aliconsumer.feign.UserFeignClient;
import com.example.aliconsumer.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guolei
 */
@RestController
public class UserController {
    public static final long COM=5;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private UserFeignClient userFeignClient;
    @Autowired
    private TestService testService;

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable Long id) {
        System.out.println("----------RestTemplate远程调用id："+id);
        List<ServiceInstance> list = discoveryClient.getInstances("ali-provider");
        ServiceInstance instance = list.get(0);
        System.out.println(instance.getHost());
        User result = restTemplate.getForObject("http://ali-provider/findUser/" + id, User.class);
        return result;
    }

    @RequestMapping("/fegin-user/{id}")
    public User findByIdFegin(@PathVariable Long id) {
        System.out.println("----------fegin远程调用id："+id);
        return userFeignClient.findById(id);
    }

    @RequestMapping("/echo/{string}")
    public String echo(@PathVariable String string) {
//        List<ServiceInstance> list = discoveryClient.getInstances("ali-provider");
//        ServiceInstance instance = list.get(0);
//        System.out.println(instance.getHost()+"----"+instance.getPort()+"----"+instance.getUri());
        System.out.println("----------即将传入远程调用id："+string);
        return userFeignClient.echo(string);
    }
    @RequestMapping("/test/{id}")
    public String test(@PathVariable Long id) {
        System.out.println("----------打印id："+id);
        return id.toString();
    }

    @RequestMapping("/sentinel/{id}")
    public String sentinel(@PathVariable Long id) {
        // 资源名
        Entry entry=null;
        try {
            // entry可以理解成入口登记
            entry = SphU.entry("test");
            // 被保护的逻辑, 这里为订单查询接口
            System.out.println("----------打印id："+id);
            return id.toString();
        } catch (BlockException blockException) {
            // 接口被限流的时候, 会进入到这里
            System.out.println("---getOrder1接口被限流了---, exception: ");
            return "接口限流, 返回空";
        } finally {
            // SphU.entry(xxx) 需要与 entry.exit() 成对出现,否则会导致调用链记录异常
            if (entry != null) {
                entry.exit();
            }
        }
    }

    public static void main(String[] args) {
        // 配置规则.
        initFlowRules();

        while (true) {
            // 1.5.0 版本开始可以直接利用 try-with-resources 特性，自动 exit entry
            try (Entry entry = SphU.entry("HelloWorld")) {
                // 被保护的逻辑
                System.out.println("hello world");
            } catch (BlockException ex) {
                // 处理被流控的逻辑
                System.out.println("blocked!");
            }
        }
    }

    @RequestMapping("/hello")
    public String helloWorld() {
        // 资源中的逻辑
        testService.helloWorld();
        System.out.println("hello world");
        return "true";
    }

    private static void initFlowRules(){
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 20.
        rule.setCount(20);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }
}
