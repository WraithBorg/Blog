package com.zxu.demo;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Arrays;

public class RoleTest extends BaseTest {
    @Test
    public void testHasRole(){
        login("classpath:shiro-role.ini","zhang","123");

        Assert.assertTrue(subject().hasRole("role1"));

        Assert.assertTrue(subject().hasAllRoles(Arrays.asList("role1", "role2")));

        boolean[] result = subject().hasRoles(Arrays.asList("role1", "role2", "role3"));

        Assert.assertEquals(true, result[0]);
        Assert.assertEquals(true, result[1]);
        Assert.assertEquals(false, result[2]);

    }

}
