package com.usc.domain.agent.service.armory.business.data;

import com.usc.domain.agent.model.entity.ArmoryCommandEntity;
import com.usc.domain.agent.service.armory.node.factory.DefaultArmoryStrategyFactory;

/**
 * 数据加载策略
 *
 * @author
 * 2025/6/27 17:16
 */
public interface ILoadDataStrategy {

    void loadData(ArmoryCommandEntity armoryCommandEntity, DefaultArmoryStrategyFactory.DynamicContext dynamicContext);

}
