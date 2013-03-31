package com.tw.pojo;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: Haiyang
 * Date: 3/6/13
 * Time: 5:47 PM
 */
public class TigerTest {

    private Class<Tiger> clazz;
    private Field legsField;
    private Tiger tiger;
    private Method sexMethod;

    @Before
    public void setUp() throws Exception {
        clazz = Tiger.class;
        legsField = clazz.getDeclaredField("legs");

        tiger = new Tiger();
    }

    @Test
    public void shouldGetTheConstructors() {
        Constructor<?>[] constructors = clazz.getConstructors();
        assertThat(constructors.length, is(2));
    }

    @Test
    public void shouldGetTheDeclaredConstructors() {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        assertThat(constructors.length, is(3));
    }

    @Test
    public void shouldGetAllFields() {
        Field[] fields = clazz.getFields();
        assertThat(fields.length, is(3));
    }

    @Test
    public void shouldGetDeclaredFields() {
        Field[] fields = clazz.getDeclaredFields();
        assertThat(fields.length, is(2));
        assertThat(fields[0].getName(), is("legs"));
        assertThat(fields[1].getName(), is("sex"));
    }

    @Test
    public void theLegsOfTigerShouldBe4() throws NoSuchFieldException, IllegalAccessException {
        tiger.legs = 4;
        assertThat(legsField.getInt(tiger), is(4));
    }

    @Test
    public void couldSetLegsTo4() throws IllegalAccessException {
        legsField.setInt(tiger, 4);
        assertThat(tiger.legs, is(4));
    }

    @Test(expected = IllegalAccessException.class)
    public void shouldNotSetValueForPrivateField() throws NoSuchFieldException, IllegalAccessException {
        Field sexField = clazz.getDeclaredField("sex");
        assertThat(sexField.isAccessible(), is(false));
        sexField.setBoolean(tiger, true);
    }

    @Test
    public void shouldSetValueForPrivateFieldAfterSetToAccessible() throws NoSuchFieldException, IllegalAccessException {
        Field sexField = clazz.getDeclaredField("sex");
        sexField.setAccessible(true);
        sexField.setBoolean(tiger, true);
        assertThat(tiger.isSex(), is(true));
    }

    @Test
    public void shouldGetSexThoughtTheGetMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        sexMethod = clazz.getDeclaredMethod("isSex", new Class[0]);
        tiger.setSex(true);
        Boolean sex = (Boolean) sexMethod.invoke(tiger, new Object[0]);
        assertThat(sex, is(true));

        Method setSexMethod = clazz.getDeclaredMethod("setSex", new Class[]{Boolean.class});
        setSexMethod.invoke(tiger, new Object[]{false});
        assertThat(tiger.isSex(), is(false));
    }

    @Test
    public void shouldReturnTigerClassForTigerArray() {
        Tiger[] tigers = new Tiger[2];
        tigers[0] = new Tiger(3);
        tigers[1] = new Tiger(4);
        assertThat(tigers.getClass().getComponentType() == Tiger.class, is(true));
    }

    @Test
    public void shouldCreateNewTigerArray() {
        Tiger[] tigers = new Tiger[2];
        tigers[0] = new Tiger(3);
        tigers[1] = new Tiger(4);

        Tiger[] newTigers = (Tiger[]) Array.newInstance(Tiger.class, 2);
        System.arraycopy(tigers, 0, newTigers, 0, 2);

        assertThat(newTigers.length, is(2));
        assertThat(newTigers[0].legs, is(3));
        assertThat(newTigers[1].legs, is(4));
    }

    @Test
    public void shouldReturnStaticMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method staticMethod = clazz.getDeclaredMethod("thisStaticMethod", new Class[0]);
        String staticMethodResult = (String) staticMethod.invoke(null, null);
        assertThat(staticMethodResult, is("test"));
    }

}
