package com.xyp.sapidoc.idoc.annotation;

import com.xyp.sapidoc.idoc.enumeration.StatusEnum;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *
 * @author Yunpeng_Xu
 */
@Retention(RUNTIME)
public @interface IdocSegment {

    String name();

    Class type();
    
    String qualified() default "";

    int level();

    StatusEnum status() default StatusEnum.DEFAULT;

    int loopMin();

    int loopMax();
}
