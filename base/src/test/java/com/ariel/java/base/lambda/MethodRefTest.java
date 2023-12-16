package com.ariel.java.base.lambda;

import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor
public class MethodRefTest {

    /**
     * 所谓方法引用，是指如果某个方法签名和接口恰好一致，就可以直接传入方法引用。
     * 注意：在这里，方法签名只看参数类型和返回类型，不看方法名称，也不看类的继承关系。
     */
    @Test
    public void methodRef() {
        Integer[] ints = {1,6,3,2,5,};
        Arrays.sort(ints, (o1, o2) -> o1.compareTo(o2)); // 方法签名：(ii)i
        Arrays.sort(ints, Integer::compareTo); // 方法签名：(i)i为什么也可以呢？因为实例函数自带this变量，可以充当方法形参；
        Arrays.sort(ints, Integer::compare); // 同时从实例方法变更为static方法，因此通过Integer::引用
        Arrays.sort(ints, this::compareTo1);
        Arrays.sort(ints, MethodRefTest::compareTo2);
        System.out.println(Arrays.toString(ints));
    }

    /**
     * 把泛型对应上就是方法签名Person apply(String)，即传入参数String，返回类型Person。
     * 而Person类的构造方法恰好满足这个条件，因为构造方法的参数是String，而构造方法虽然没有return语句，
     * 但它会隐式地返回this实例，类型就是Person，
     * 因此，此处可以引用构造方法。构造方法的引用写法是类名::new，因此，此处传入Person::new。
     */
    @Test
    public void constructorRef() {
        Integer[] ints = {1,6,3,2,5,};
        List<MethodRefTest> list = Stream.of(ints)
                .map(MethodRefTest::new).collect(Collectors.toList());
    }

    private int compareTo1(Integer o1, Integer o2) {
        return o1 - o2;
    }

    private static int compareTo2(Integer o1, Integer o2) {
        return o1 - o2;
    }

    MethodRefTest(Integer i) {

    }

}
