package com.hawaste.junit;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.web.hawaste.domain.ExamineDo;
import com.web.hawaste.service.IExamineService;
import com.web.hawaste.utils.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
public class TestExamineDo {

    @Autowired
    private IExamineService examineService;

    @Test
    public void test1() {
        HashMap<String, Object> map = new HashMap<>();

        map.put("officeId", 56);
        map.put("type", 1);
        map.put("name", "人员");
        PageInfo<ExamineDo> page = new PageInfo<>(1, 5);
        IPage<ExamineDo> pageInfo = examineService.selectByCondition(page, map);
        pageInfo.getRecords().forEach(examineDo -> {
            System.out.println(examineDo);
        });
    }
}
