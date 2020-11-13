package com.zxu.demo.mysql;

import com.zxu.demo.DemoApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CopyDatabase {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Value("${oldDB}")
    private String oldDB;
    @Value("${newDB}")
    private String newDB;
    /**
     *  java 表空间传输实例
     */
    @RequestMapping("/export")
    public String export() {

        long begin = System.currentTimeMillis();
        System.out.println("开始转储数据" + String.valueOf(begin));
        // 获取表空间文件存储地址
        List<Map<String, Object>> paths = jdbcTemplate.queryForList("show variables like 'datadir'");
        String dataPath = (String) paths.get(0).get("Value");
        System.out.println("获取表空间文件存储地址:"+dataPath);
        // 需要导入的表的列表
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("show tables");
        List<String> tableList = new ArrayList<>();// 表名数组
        for (Map<String, Object> map : maps) {
            tableList.add(String.valueOf(map.values()).replace("[", "").replace("]", ""));
        }
        System.out.println("有个"+tableList.size()+"表需要导入");
        // 创建新数据库
        jdbcTemplate.execute("drop database if exists "+newDB+" ");
        jdbcTemplate.execute("create database "+newDB+" ");
        // 创建新表并删除表空间
        for (String s : tableList) {
            jdbcTemplate.execute("create table "+newDB+"." + s + " like "+oldDB+"." + s);
            jdbcTemplate.execute("alter table "+newDB+"." + s + " discard tablespace");
        }
        System.out.println("创建新数据库 创建新表并删除表空间");
        // 生成表空间 cfg
        StringBuffer sb = new StringBuffer(" flush tables ");
        for (String s : tableList) {
            sb.append(s).append(",");
        }
        sb.deleteCharAt(sb.length() - 1).append(" for export;");
        jdbcTemplate.execute(sb.toString());
        System.out.println("生成表空间");
        // 复制并移动表空间文件
        String copyCmd = "cmd.exe /c xcopy \""+dataPath+""+oldDB+"\\*\" \""+dataPath+""+newDB+"\" /c/e/r/y";
        try {
            System.out.println(copyCmd);
            System.out.println("开始移动表空间" + System.currentTimeMillis());
            Process process = Runtime.getRuntime().exec(copyCmd);

            InputStream is = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while((line = reader.readLine())!= null){
                System.out.println(line);
            }
            is.close();
            reader.close();
            process.destroy();
            process.waitFor();
            System.out.println("移动表空间结束" + System.currentTimeMillis());
            jdbcTemplate.execute("unlock tables");
            // 恢复表空间
            for (String s : tableList) {
                String importSql = "alter table "+newDB+"." + s + " import tablespace";
                System.out.println(importSql);
                jdbcTemplate.execute(importSql);
            }
            System.out.println("转储结束：" + (begin - System.currentTimeMillis()));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "Welcome";
    }
}
