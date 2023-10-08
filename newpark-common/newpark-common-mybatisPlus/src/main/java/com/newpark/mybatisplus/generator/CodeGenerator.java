package com.newpark.mybatisplus.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author jack
 * @date 2023/4/28
 */
public class CodeGenerator {

    public static void main(String[] args) {

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        // 获取项目路径
        String projectPath = System.getProperty("user.dir");
        projectPath = "D:\\code";
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.zh.mustang.ehr.sys");
        mpg.setPackageInfo(pc);
        // 生成文件的输出目录
        gc.setOutputDir(projectPath + "/src/main/java");
        // 作者
        gc.setAuthor("jack");
        // 开启 swagger2 模式
        gc.setSwagger2(true);
        // 是否打开输出目录
        gc.setOpen(true);
        // 指定生成的主键的ID类型
        gc.setIdType(IdType.ASSIGN_ID);
        // 如果有同名文件，是否覆盖
        gc.setFileOverride(true);
        // 设置给到代码生成器
        gc.setDateType(DateType.ONLY_DATE);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        // 选择使用 MySQL
        dsc.setDbType(DbType.MYSQL);
        dsc.setUrl("jdbc:mysql://112.74.161.63:3306/zh_ehr_dev?useUnicode=true&useSSL=false&characterEncoding=utf8");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("dev");
        dsc.setPassword("dev123");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 表名（驼峰命名）AbstractTemplateEngine
        strategy.setInclude("sys_sequence");
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 设置表前缀
        strategy.setTablePrefix(pc.getModuleName() + "_");
        // 字段命名
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 【实体】是否为lombok模型（默认 false）
        strategy.setEntityLombokModel(true);
        // 生成 <code>@RestController</code> 控制器
        strategy.setRestControllerStyle(true);
        mpg.setStrategy(strategy);
        mpg.execute();
    }
}
