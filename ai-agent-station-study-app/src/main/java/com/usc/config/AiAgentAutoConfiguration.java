package com.usc.config;

import com.alibaba.fastjson.JSON;
import com.usc.domain.agent.model.entity.ArmoryCommandEntity;
import com.usc.domain.agent.model.valobj.AiAgentVO;
import com.usc.domain.agent.model.valobj.enums.AiAgentEnumVO;
import com.usc.domain.agent.service.IArmoryService;
import com.usc.domain.agent.service.armory.node.factory.DefaultArmoryStrategyFactory;
import com.usc.types.common.Constants;
import cn.bugstack.wrench.design.framework.tree.StrategyHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * AI Agent 自动装配配置类
 * 在Spring Boot应用启动完成后，根据配置自动装配AI客户端
 * @author luojing
 * 2025/1/15 10:00
 */
@Slf4j
@Configuration  // 标识为配置类
@EnableConfigurationProperties(AiAgentAutoConfigProperties.class)   // 启用配置属性
@ConditionalOnProperty(prefix = "spring.ai.agent.auto-config", name = "enabled", havingValue = "true")
// 监听 ApplicationReadyEvent，确保在Spring容器完全启动后执行。应用启动时的自动初始化
public class AiAgentAutoConfiguration implements ApplicationListener<ApplicationReadyEvent> {

    @Resource
    private AiAgentAutoConfigProperties aiAgentAutoConfigProperties;

    @Resource
    private DefaultArmoryStrategyFactory defaultArmoryStrategyFactory;

    @Resource
    private IArmoryService armoryService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            log.info("AI Agent 自动装配开始，配置: {}", aiAgentAutoConfigProperties);

            // 检查配置是否有效
            if (!aiAgentAutoConfigProperties.isEnabled()) {
                log.info("AI Agent 自动装配未启用");
                return;
            }

            List<AiAgentVO> aiAgentVOS = armoryService.acceptArmoryAllAvailableAgents();

            log.info("AI Agent 自动装配完成 {}", JSON.toJSONString(aiAgentVOS));
        } catch (Exception e) {
            log.error("AI Agent 自动装配失败", e);
        }
    }

}