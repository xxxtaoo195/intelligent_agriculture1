package com.topwulian.service;

import com.topwulian.dto.DeviceGatherCharts;
import com.topwulian.model.Device;
import com.topwulian.model.DeviceCon;
import com.topwulian.model.DeviceGather;
import com.topwulian.page.table.PageTableRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: szz
 * @Date: 2018/12/26 下午2:01
 * @Version 1.0
 */
public interface DeviceService {
    List<DeviceGather> getRealTimeDataByFarmId(Long farmId);
    List<DeviceGather> getHistoryDataByFarmId(PageTableRequest pageTableRequest,Long farmId);

    Map<Long,List<DeviceGatherCharts> > echartsShow(Long userId, PageTableRequest pageTableRequest);

    Map devicesState(PageTableRequest request);

    List<DeviceGather> getGatherInfo(String monpointsid);

    List<DeviceCon> listDeviceCon(@Param("monpointsid") String monpointsid);

    List<DeviceGather> getGatherInfoList(@Param("mid") String mid,@Param("monpointsid") String monpointsid);


    Map<Long,List<DeviceGatherCharts> > echartsDeviceShow(PageTableRequest pageTableRequest);

    List<Device> getByMidDeviceInfo(String mid);



}
