package org.example.utils;


import com.mysql.cj.util.TimeUtil;

import java.util.Random;

public class NoGenerate {

    public String acctNoGenerate() {
        Long prefix = TimeUtil.getCurrentTimeNanosOrMillis();
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        Random random = new Random();
        for (int i = 0; i < 16 - sb.length(); i++) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }

    public String credCardNoGenerate() {
        Long prefix = TimeUtil.getCurrentTimeNanosOrMillis();
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        Random random = new Random();
        for (int i = 0; i < 18 - sb.length(); i++) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }

    public String onlineNoGenerate() {
        Long prefix = TimeUtil.getCurrentTimeNanosOrMillis();
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        Random random = new Random();
        for (int i = 0; i < 17 - sb.length(); i++) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }
}
