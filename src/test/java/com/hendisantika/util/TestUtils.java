package com.hendisantika.util;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/18/22
 * Time: 16:09
 * To change this template use File | Settings | File Templates.
 */
public class TestUtils {
    public static String createStringWithLength(int length) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            sb.append("a");
        }

        return sb.toString();
    }
}
