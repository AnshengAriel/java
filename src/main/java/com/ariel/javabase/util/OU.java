package com.ariel.javabase.util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 观察工具类，用于关注一个对象的变化，并且打印出来
 *
 * @author ariel
 * @date 2022/10/31
 */
public class OU {

    /**
     * 日志输出路径
     */
    private static final String PATH = "D:\\log";
    /**
     * 类文件存储地址
     */
    private static final String STORE_PATH = "..\\util\\OU.java";
    /**
     * 类文件存储地址
     */
    private static final String THIS_PATH = "src\\main\\java\\" + OU.class.getName().replace('.', '\\') + ".java";
    /**
     * 文件排序开头标识计数
     */
    private static int docIndex;
    /**
     * true=将会输出被过滤掉的类日志
     */
    private static final boolean log = false;
    /**
     * key=追踪目标 value=追踪目标类操作前输出文档，用于符操作后文件做比较
     */
    private static final HashMap<Object, String[]> targets = new HashMap<>(1);
    /**
     * 观察时间
     */
    private static final long START_TIME = System.currentTimeMillis();
    /**
     * 所有可用Field对象缓存，循环引用检查
     */
    private static HashMap<Object, FieldObj> fieldObjMap;
    /**
     * 基础类型的包装类型处理器容器
     */
    private static final Map<Class<?>, Handler> simpleHandlerMap;
    /**
     * 集合类型的包装类型处理器容器
     */
    private static final Map<Class<?>, Handler> setHandlerMap;
    /**
     * 需要包含类的包目录列表
     */
    private static final Set<String> includePackages;
    /**
     * 需要排除类的包目录列表
     */
    private static final Set<String> excludePackages;
    /**
     * 默认类型处理器容器
     */
    private static final Handler DEFAULT_CLASS_HANDLER;

    static {
        SimpleHandler simpleHandler = new SimpleHandler();

        // 处理基元类型、数据结构类型、反射相关类型，直接返回一个简单结果，避免继续递归
        simpleHandlerMap = new LinkedHashMap<>();
        simpleHandlerMap.put(CharSequence.class, simpleHandler);
        simpleHandlerMap.put(Character.class, simpleHandler);
        simpleHandlerMap.put(Number.class, simpleHandler);
        simpleHandlerMap.put(Boolean.class, simpleHandler);
        simpleHandlerMap.put(Enum.class, new EnumHandler());
        simpleHandlerMap.put(LocalDate.class, new DateHandler());
        simpleHandlerMap.put(Class.class, new ClassHandler());
        simpleHandlerMap.put(Method.class, new MethodHandler());
        simpleHandlerMap.put(Constructor.class, new ConstructorHandler());

        // 处理可能发生无限递归的类型
        setHandlerMap = new HashMap<>();
        setHandlerMap.put(Map.class, new MapHandler());
        setHandlerMap.put(Collection.class, new ListHandler());
        setHandlerMap.put(Object[].class, new ListHandler());

		// 处理其他类型
		DEFAULT_CLASS_HANDLER = new DefaultHandler();

        // 保留必须关注的类型
        includePackages = new LinkedHashSet<>();
        includePackages.add("java.nio");
        includePackages.add("java.util");
        includePackages.add("java.lang");
        includePackages.add("java.time");
        includePackages.add("[Ljava.lang");
        includePackages.add("com.ariel");
        includePackages.add("io.netty");

        // 在必须关注的范围内，过滤其中无须关注的类型
        excludePackages = new LinkedHashSet<>();
        excludePackages.add("java.lang.invoke");
        excludePackages.add("java.lang.ref.");
        excludePackages.add("java.lang.Thread");
        excludePackages.add("java.lang.UnsupportedOperationException");
        excludePackages.add("java.util.concurrent.locks");
//        excludePackages.add("java.util.concurrent.Executors");
//        excludePackages.add("java.util.concurrent.ThreadPoolExecutor");
        excludePackages.add("java.util.concurrent.Semaphore");
        excludePackages.add("java.util.Objects");
        excludePackages.add("java.util.Timer");
        excludePackages.add("java.util.ResourceBundle");
        excludePackages.add("io.netty.util.internal.logging");
        excludePackages.add("io.netty.util.concurrent.FastThreadLocalThread");
        excludePackages.add("io.netty.buffer");

    }

    /**
     * 开始观察一个对象，且只关注一个对象
     * @param obj 被观察对象
     */
    public static <T> T obs(T obj) {
        return obs(obj, true);
    }

    public static <T> T obs(T obj, boolean on) {
        if (on && obj != null) {
            targets.putIfAbsent(obj, null);
            initFile(PATH + "/" + obj.getClass().getSimpleName());
        }
        return obj;
    }

    public static void apd(String logger) {
        apd(null, logger, true);
    }

    public static <T> T apd(T t, String logger) {
        return apd(t, logger, true);
    }

    /**
     * 观察对象变化并打印日志
     * @param logger 日志
     */
    public static <T> T apd(T t, String logger, boolean on) {
        if (on) {
            targets.keySet().forEach(k -> {
                // 重置缓存
                fieldObjMap = new HashMap<>();

                FieldObj root = new FieldObj();
                root.fValue = k;
                root.parent = new JsonObj();
                root.setSelf();
                root.handle();

                String fileName = String.format("%s/%s/%s_%s.json", PATH, k.getClass().getSimpleName(), docIndex++, logger);
                save(logger, compare(k, root.self.toStringPretty()), fileName);
            });
        }
        return t;
    }

    public static void listen(long refreshTimeSec) {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(refreshTimeSec * 1000);
                    apd("AutoRefresh#" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH-mm-ss")));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    private static String compare(Object k, String currentStr) {
        final String sign = "\n";
        String[] oldText = targets.get(k);
        String[] currText = currentStr.split(sign);
        targets.put(k, currText);

        // 旧值为空，没有比较的必要
        if (oldText == null || oldText.length == 0) {
            return currentStr;
        }

        // 逐行比较对象的变化
        StringBuilder result = new StringBuilder();
        for (int b = 0, a = 0; b < oldText.length && a < currText.length;) {
            if (Objects.equals(oldText[b], currText[a])) {
                result.append(currText[a]).append(sign);
                b++;a++;
                if (b == oldText.length || a == oldText.length) {
                    // 文件无变化
                    return null;
                }
            }else {
                for (int i = a; i < currText.length; i++) {
                    result.append(currText[i]);
                    boolean f = false;
                    for (int j = b; j < oldText.length; j++) {
                        if (Objects.equals(oldText[j], currText[i])) {
                            result.append(sign);
                            f = true;
                            break;
                        }
                    }
                    if (!f) {
                        result.append(" // +").append(sign);
                    }
                }
                break;
            }
        }
        return result.toString();
    }

    private static void initFile(String filePath) {
        try {
            Path path = Paths.get(filePath);
            if (Files.exists(path)) {
                Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        Files.delete(file);
                        return FileVisitResult.CONTINUE;
                    }
                    @Override
                    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                        Files.delete(dir);
                        return FileVisitResult.TERMINATE;
                    }
                });
            }
            Files.createDirectories(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void save(String logger, String result, String fileName) {
        if (result != null) {
            System.out.println((System.currentTimeMillis() - START_TIME) + ": " + logger);
            try (FileWriter fileWriter = new FileWriter(fileName)) {
                fileWriter.write(result);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static boolean effectPackage(String className) {
        boolean ok = false;
        // 只关注包含在includeClassSet中的类对象
        for (String includeStr : includePackages) {
            if (className.startsWith(includeStr)) {
                ok = true;
                break;
            }
        }
        // 不关注包含在excludeClassSet中的类对象
        for (String excludeStr : excludePackages) {
            if (className.startsWith(excludeStr)) {
                ok = false;
                break;
            }
        }
        return ok;
    }

    private static class FieldObj {
        public Field field;
        public Object fValue;
        public int classLevel;
        public int hashLevel;
        public JsonObj parent;
        public JsonObj self;
        public String key;
        public boolean ignored;

        public String getKeyName() {
            if (key != null) return key;
            if (field != null) return classLevel + "-" + field.getDeclaringClass().getSimpleName() + "-" + field.getName();
            return getValueName();
        }

        public String getValueName() {
            return fValue.getClass().getName() + "@" + fValue.hashCode();
        }

        public void handle() {
            if (fValue == null) {
                return;
            }
            JsonObj parentJson = parent;
            Class<?> fClass = fValue.getClass();

            // 基元类型
            if (fClass.isPrimitive()) {
                parentJson.put(getKeyName(), fValue);
                return;
            }
            // 直接返回一个简单结果的类型，可选
            for (Map.Entry<Class<?>, Handler> entry : simpleHandlerMap.entrySet()) {
                if (entry.getKey().isAssignableFrom(fClass)) {
                    entry.getValue().handle(this);
                    return;
                }
            }

            // 无效包目录直接返回简单的结果
            if (!effectPackage(fClass.getName())) {
                parentJson.put(getKeyName(), "*" + getValueName());
                if (log) {
                    System.out.println(fClass.getName());
                }
                return;
            }

            // 避免自循环
            if (fieldObjMap.containsKey(fValue)) {
                if (ignored) {
                    parentJson.put(getKeyName(), getValueName());
                }else {
                    // 看是否需要迁移对象，避免复杂对象太大
                    FieldObj oldFieldObj = fieldObjMap.get(fValue);
                    if (hashLevel < oldFieldObj.hashLevel) {
                        self = oldFieldObj.self;
                        parentJson.put(getKeyName(), self);
                        oldFieldObj.parent.put(oldFieldObj.getKeyName(), oldFieldObj.getValueName());
                        fieldObjMap.put(fValue, this);
                    }else {
                        parentJson.put(getKeyName(), getValueName());
                    }
                }
                return;
            }
            fieldObjMap.put(fValue, this);

            // 递归遍历复杂的对象
            for (Map.Entry<Class<?>, Handler> entry : setHandlerMap.entrySet()) {
                if (entry.getKey().isAssignableFrom(fClass)) {
                    entry.getValue().handle(this);
                    ignored = true;
                    return;
                }
            }

            // 最后处理真正需要研究的对象
            DEFAULT_CLASS_HANDLER.handle(this);
        }

        public int getSonHashLevel() {
            return hashLevel + 1;
        }

        public void setHashCode() {
            self.put("*HASHCODE", getValueName());
        }

        public void setSelf() {
            self = new JsonObj();
            setHashCode();
            parent.put(getKeyName(), self);
        }

    }

    interface Handler {
        void handle(FieldObj fieldObj);
    }

    /**
     * 默认类处理器
     */
    private static class DefaultHandler implements Handler {

        @Override
        public void handle(FieldObj fieldObj) {
            Class<?> aClass = fieldObj.fValue.getClass();
            List<Class<?>> classes = new LinkedList<>();
            while (aClass != null && aClass.getDeclaredFields().length > 0) {
                classes.add(0, aClass);
                aClass = aClass.getSuperclass();
            }
            for (int classLevel = 0; classLevel < classes.size(); classLevel++) {
                Field[] fields = classes.get(classLevel).getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    try {
                        Object fValue = field.get(fieldObj.fValue);
                        if (fValue != null) {
                            FieldObj son = new FieldObj();
                            son.parent = fieldObj.self;
                            son.field = field;
                            son.fValue = fValue;
                            son.classLevel = classLevel;
                            son.hashLevel = fieldObj.getSonHashLevel();
                            son.setSelf();
                            son.handle();
                        }
                    } catch (IllegalAccessException e) {
                        System.out.println("反射获取变量失败: " + field.getType().getName());
                    }
                }
            }

        }
	}

    /**
     * 简单类处理器，简单类直接输出
     */
    private static class SimpleHandler implements Handler {
        @Override
        public void handle(FieldObj fieldObj) {
            fieldObj.parent.put(fieldObj.getKeyName(), fieldObj.fValue);
        }
    }

    /**
     * Map处理器
     */
    private static class MapHandler implements Handler {
        @Override
        public void handle(FieldObj fieldObj) {
            Map<?, ?> map = (Map<?, ?>) fieldObj.fValue;
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                Object k = entry.getKey();
                Object v = entry.getValue();
                if (k != null && v != null) {
                    String keyStr;
                    if (k instanceof Class) {
                        keyStr = ((Class<?>) k).getSimpleName() + ".class";
                    }else if (k instanceof Method){
                        Method method = (Method) k;
                        keyStr = method.getDeclaringClass().getSimpleName() + "#" + method.getName();
                    }else {
                        keyStr = k.toString();
                    }

                    FieldObj son = new FieldObj();
                    son.fValue = v;
                    son.parent = fieldObj.self;
                    son.hashLevel = fieldObj.getSonHashLevel();
                    son.key = keyStr;
                    son.setSelf();
                    son.handle();
                }
            }
        }
    }

    /**
     * Collection处理器，简单类直接输出
     */
    private static class ListHandler implements Handler {
        @Override
        public void handle(FieldObj fieldObj) {
            // 将数组类型转换成Map
            Object[] array;
            if (fieldObj.fValue instanceof Collection) {
                array = ((Collection<?>) fieldObj.fValue).toArray();
            }else if (fieldObj.fValue instanceof Object[]) {
                array = (Object[]) fieldObj.fValue;
            }else {
                return;
            }

            for (int i = 0; i < array.length && array[i] != null; i++) {
                FieldObj son = new FieldObj();
                son.fValue = array[i];
                son.parent = fieldObj.self;
                son.hashLevel = fieldObj.getSonHashLevel();
                son.key = int2Str(i);
                son.setSelf();
                son.handle();
            }
        }

        private String int2Str(int i) {
            if (i < 10) return "00" + i;
            if (i < 100) return "0" + i;
            return Integer.toString(i);
        }
    }

    /**
     * 日期处理器，简单类直接输出
     */
    private static class DateHandler implements Handler {
        @Override
        public void handle(FieldObj fieldObj) {
            LocalDate localDate = (LocalDate) fieldObj.fValue;
            fieldObj.parent.put(fieldObj.getKeyName(), "LocalDate" + localDate.toString());
        }
    }

    /**
     * 枚举处理器，简单类直接输出
     */
    private static class EnumHandler implements Handler {
        @Override
        public void handle(FieldObj fieldObj) {
            Enum<?> anEnum = (Enum<?>) fieldObj.fValue;
            fieldObj.parent.put(fieldObj.getKeyName(), "Enum " + anEnum.getClass().getSimpleName() + "#" + anEnum.name());
        }
    }

    /**
     * Class处理器，简单类直接输出
     */
    private static class ClassHandler implements Handler {
        @Override
        public void handle(FieldObj fieldObj) {
            Class<?> aClass = (Class<?>) fieldObj.fValue;
            fieldObj.parent.put(fieldObj.getKeyName(), "Class " + aClass.getSimpleName() + ".class");
        }
    }

    /**
     * Method处理器，简单类直接输出
     */
    private static class MethodHandler implements Handler {
        @Override
        public void handle(FieldObj fieldObj) {
            Method method = (Method) fieldObj.fValue;
            fieldObj.parent.put(fieldObj.getKeyName(), "Method " + method.getDeclaringClass().getSimpleName() + "#" + method.getName());
        }
    }

    /**
     * Constructor处理器，简单类直接输出
     */
    private static class ConstructorHandler implements Handler {
        @Override
        public void handle(FieldObj fieldObj) {
            Constructor<?> constructor = (Constructor<?>) fieldObj.fValue;
            StringJoiner joiner = new StringJoiner(",");
            for (Class<?> aClass : constructor.getParameterTypes()) {
                joiner.add(aClass.getSimpleName());
            }
            fieldObj.parent.put(fieldObj.getKeyName(), "Constructor " + constructor.getDeclaringClass().getSimpleName() + "(" + joiner + ")");
        }
    }

    private static class JsonObj extends TreeMap<String, Object> {
		private static final long serialVersionUID = 1L;
		public JsonObj() {
            // 自定义比较器以下划线为先
			super((o1, o2) -> {
                // 0涉及到查询，最先处理
                int i = o1.compareTo(o2);
                if (i != 0) {
                    // 先比较静态变量
                    boolean o1Static = o1.equals(o1.toUpperCase());
                    boolean o2Static = o2.equals(o2.toUpperCase());
                    if (o1Static ^ o2Static) {
                        return o1Static ? -1 : 1;
                    }
                }
                return i;
            });
		}

        public String toStringPretty() {
            StringBuilder builder = toStr(new StringBuilder(), this, 0);
            return builder.deleteCharAt(builder.lastIndexOf(",")).toString();
        }

        public static StringBuilder toStr(StringBuilder builder, JsonObj o, int level) {
            builder.append("{\n");
            o.forEach((k, v) -> {
                builder.append(getSpace(level + 1))
                        .append("\"").append(k).append("\": ");
                if (v instanceof JsonObj) {
                    builder.append(toStr(new StringBuilder(), ((JsonObj) v), level + 1));
                }else {
                    builder.append(v instanceof Number ? (v + ",\n") : "\"" + v + "\",\n");
                }
            });
            if (!o.isEmpty()) {
                builder.deleteCharAt(builder.lastIndexOf(","));
            }
            builder.append(getSpace(level)).append("},\n");
            return builder;
        }

        public static StringBuilder getSpace(int level) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < level; i++) {
                builder.append("    ");
            }
            return builder;
        }

    }

    private static void push() {
        copy(THIS_PATH, STORE_PATH);
        System.out.println("文件上传成功");
    }

    private static void pull() {
        copy(STORE_PATH, THIS_PATH);
        System.out.println("文件更新成功");
    }

    private static void copy(String source, String target) {
        try (FileReader reader = new FileReader(source);
             FileWriter writer = new FileWriter(target)) {
            char[] buffer = new char[1024];
            int length;
            while ((length = reader.read(buffer)) > 0) {
                writer.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        push();
//        pull();
    }

}
