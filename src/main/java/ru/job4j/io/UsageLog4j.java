package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte b = 3;
        short s = 20;
        int i = 378;
        boolean bool = true;
        double d = Math.PI;
        float f = 3.14F;
        long l = 123412L;
        char c = 'C';

        LOG.debug("1.byte : {}, 2.short : {}, 3.int : {},  4.boolean : {}, "
                + "5.double : {}, 6.float : {}, 7.long : {}, 8.char : {}", b, s, i, bool, d, f, l, c);
    }
}