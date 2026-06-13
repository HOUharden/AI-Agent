package com.usc.api;

import com.usc.api.dto.DataStatisticsResponseDTO;
import com.usc.api.response.Response;

/**
 * 数据统计
@author luojing

 * 2025/10/4 10:33
 */
public interface IAiAgentDataStatisticsAdminService {

    /**
     * 获取系统数据统计
     * @return 统计数据响应
     */
    Response<DataStatisticsResponseDTO> getDataStatistics();
}
