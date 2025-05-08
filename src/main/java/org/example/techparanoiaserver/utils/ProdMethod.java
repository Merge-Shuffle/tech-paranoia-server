package org.example.techparanoiaserver.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * &#064;ProMethod  annotation is used to mark a method
 * that is currently in production and needs to be finished
 */
@Target(ElementType.METHOD)
public @interface ProdMethod {
}
