package com.hawaste.junit;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.web.hawaste.service.IWorkOrderService;
import com.web.hawaste.utils.PageInfo;
import com.web.hawaste.domain.WorkOrderDo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
public class TestWorkOrderDo {

    @Autowired
    private IWorkOrderService workOrderService;

    @Test
    public void test1() {

        HashMap<String, Object> params = new HashMap<>();

        params.put("status", 0);
        params.put("startDate", "2016-01-01");
        params.put("endDate", "2016-12-31");
        params.put("officeId", 56);

        PageInfo<WorkOrderDo> page = new PageInfo<>(1, 5);
        IPage<WorkOrderDo> pageInfo = workOrderService.selectByCondition(page, params);
        pageInfo.getRecords().forEach(workOrderDo -> {
            System.out.println(workOrderDo.getCode() + "\t" + workOrderDo.getCreateBy());
        });
    }
}
