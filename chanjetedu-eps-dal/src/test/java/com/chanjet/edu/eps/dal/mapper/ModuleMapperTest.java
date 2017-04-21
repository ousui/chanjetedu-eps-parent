package com.chanjet.edu.eps.dal.mapper;

import com.chanjet.edu.eps.dal.SpringAppConfigTest;
import com.chanjet.edu.eps.dal.domain.Module;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ousui on 16-6-10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringAppConfigTest.class})
public class ModuleMapperTest {
    @Resource
    private ModuleMapper moduleMapper;

    @Test
    public void selectAllWithRoles() throws Exception {

        List<Module> modules = moduleMapper.selectAllWithRoles();
        System.out.println(modules);

        for (int i = 0; i < modules.size(); i++) {
            Module m = modules.get(i);
            System.out.println(m.getName() + " " + m.getPath());
            System.out.println(m.getRoles());
        }
    }

}