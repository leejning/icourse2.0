package com.online.icourse.business.feign;

import com.online.icourse.common.server.IcServiceList;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName ProfileFeign
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/9/28 0028
 * @Version V1.0
 **/
@FeignClient(IcServiceList.IC_BUSINESS_PROFILE)
public interface ProfileFeign {

}
