package com.zxu.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@SpringBootApplication
public class DemoApplication {

	Logger log = LoggerFactory.getLogger(DemoApplication.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@RequestMapping("/")
	public String index(){
		log.warn("we ==++");
		return "Welcome";
	}

	@RequestMapping("/export")
	public String export() {
		// 获取表空间文件存储地址
		List<Map<String, Object>> paths = jdbcTemplate.queryForList("show variables like 'datadir'");
		String dataPath = (String) paths.get(0).get("Value");

		// 需要导入的表的列表
		List<Map<String, Object>> maps = jdbcTemplate.queryForList("show tables");
		List<String> tableList = new ArrayList<>();// 表名数组
		for (Map<String, Object> map : maps) {
			tableList.add(String.valueOf(map.values()).replace("[", "").replace("]", ""));
		}
		// 创建新数据库
		jdbcTemplate.execute("drop database if exists newDB ");
		jdbcTemplate.execute("create database newDB ");
		// 创建新表并删除表空间
		for (String s : tableList) {
			jdbcTemplate.execute("create table newDB." + s + " like old." + s);
			jdbcTemplate.execute("alter table newDB." + s + " discard tablespace");
		}
		// 生成表空间 cfg
		StringBuffer sb = new StringBuffer(" flush tables ");
		for (String s : tableList) {
			sb.append(s).append(",");
		}
		sb.deleteCharAt(sb.length() - 1).append(" for export;");
		jdbcTemplate.execute(sb.toString());
		// 复制并移动表空间文件
		String copyCmd = "cmd.exe /c xcopy \""+dataPath+"old\" \""+dataPath+"newdb\" /c/e/r/y";
		try {
			Runtime.getRuntime().exec(copyCmd);
			System.out.println(copyCmd);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			jdbcTemplate.execute("unlock tables");
			// 恢复表空间
			for (String s : tableList) {
				String importSql = "alter table newDB." + s + " import tablespace";
				System.out.println(importSql);
				jdbcTemplate.execute(importSql);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "Welcome";
	}
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
