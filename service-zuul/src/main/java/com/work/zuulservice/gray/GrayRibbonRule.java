package com.work.zuulservice.gray;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.Server;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

public class GrayRibbonRule extends AbstractLoadBalancerRule {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {
        logger.info("执行了初始化的方法：initWithNiwsConfig");
    }

    @Override
    public Server choose(Object key) {

        List<Server> serverList = this.getLoadBalancer().getAllServers();
        for (Server server : serverList) {
            logger.info("服务列表："+server.getHostPort());
        }

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String grayMark= request.getHeader("gray_mark");
        logger.info("gray_mark:"+grayMark);


        if ("enable".equals(grayMark)) {
            //进行灰度发布（自定义负载）
            logger.info("进入：进行灰度发布（自定义负载）");
            int index = -1;
            for (int i = 0; i < serverList.size(); i++) {
                Server server = serverList.get(i);
                String hostPort = server.getHostPort();
                if ("LAPTOP-EHHHR2H5:6002".equals(hostPort)) {
                    index = i;
                    logger.info("选择了服务："+hostPort);
                    break;
                }
            }
            return serverList.get(index);
        } else {
            logger.info("进入：随机分发");
            int index = (int)(Math.random() * serverList.size());
            logger.info("选择了服务："+ serverList.get(index).getHostPort());
            return serverList.get(index);
        }

    }
}
