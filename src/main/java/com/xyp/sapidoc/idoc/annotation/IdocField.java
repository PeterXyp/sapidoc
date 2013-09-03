package com.xyp.sapidoc.idoc.annotation;

import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *
 * @author Yunpeng_Xu
 */
@Retention(RUNTIME)
public @interface IdocField {

    String name();

    String text() default "";

    Class type() default String.class;

    int length();

    int field_pos();

    int character_first();

    int character_last();
}
