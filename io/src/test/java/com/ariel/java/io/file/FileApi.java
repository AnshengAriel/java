package com.ariel.java.io.file;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * [创建文件的几种方式](jetbrains://idea/navigate/reference?project=java-io&fqn=com.ariel.io.io.FileApi)
 */
public class FileApi {

    public static final String FILE_NAME = UUID.randomUUID() + ".txt";

    @Test
    public void absolutePath() throws IOException {
        // 绝对路径，以盘符作为根路径
        String path = "D:\\ariel\\project\\java-io\\src\\test\\resources\\io\\" + FILE_NAME;
        createFile(path);
    }

    @Test
    public void relativePath() throws IOException {
        // 相对路径，main包中代码以当前项目作为根路经，test包以所在模块作为根路径，有区别
        String path = FILE_NAME;
        createFile(path);
    }

    private void createFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.printf("创建文件[%s]成功%n", file.getName());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 路径分隔符和系统有关：
     * windows和DOS系统默认使用"\"来表示
     * UNIX和URL使用"/"来表示
     * https://blog.csdn.net/weixin_52533007/article/details/123274351?ops_request_misc=&request_id=&biz_id=102&utm_term=Java%20File&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduweb~default-1-123274351.nonecase&spm=1018.2226.3001.4187
     */
    @Test
    public void separator() {
        // 根据操作系统，动态的提供分隔符。
        System.out.println("File.separator = " + File.separator);
    }



}
