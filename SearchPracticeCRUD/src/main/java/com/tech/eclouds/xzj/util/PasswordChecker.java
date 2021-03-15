package com.tech.eclouds.xzj.util;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * []
 *
 * @author zhangdi
 * @date 2020-01-13 16:48
 */
public class PasswordChecker {
    // 包含大写字母
    private boolean upperCase = true;
    // 包含小写字母
    private boolean lowerCase = true;
    // 包含字母
    private boolean letter = true;
    // 包含数字
    private boolean digit = true;
    // 包含特殊字符
    private boolean special = true;
    // 特殊字符集合
    private Set<Character> specialCharSet = defaultSpecialCharSet();
    // 最小长度
    private int minLength = 8;
    // 最大长度
    private int maxLength = 20;

    public boolean isUpperCase() {
        return upperCase;
    }

    public void setUpperCase(boolean upperCase) {
        this.upperCase = upperCase;
    }

    public boolean isLowerCase() {
        return lowerCase;
    }

    public void setLowerCase(boolean lowerCase) {
        this.lowerCase = lowerCase;
    }

    public boolean isLetter() {
        return letter;
    }

    public void setLetter(boolean letter) {
        this.letter = letter;
    }

    public boolean isDigit() {
        return digit;
    }

    public void setDigit(boolean digit) {
        this.digit = digit;
    }

    public boolean isSpecial() {
        return special;
    }

    public void setSpecial(boolean special) {
        this.special = special;
    }

    public Set<Character> getSpecialCharSet() {
        return specialCharSet;
    }

    public void setSpecialCharSet(Set<Character> specialCharSet) {
        this.specialCharSet = specialCharSet;
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    /**
     * 密码符合规则，返回true
     */
    public boolean check(String password) {
        if (password == null || password.length() < this.minLength || password.length() > this.maxLength) {
            // 长度不符合
            return false;
        }

        boolean containUpperCase = false;
        boolean containLowerCase = false;
        boolean containLetter = false;
        boolean containDigit = false;
        boolean containSpecial = false;

        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                containUpperCase = true;
                containLetter = true;
            } else if (Character.isLowerCase(ch)) {
                containLowerCase = true;
                containLetter = true;
            } else if (Character.isDigit(ch)) {
                containDigit = true;
            } else if (this.specialCharSet.contains(ch)) {
                containSpecial = true;
            } else {
                // 非法字符
                return false;
            }
        }

        if (this.upperCase && !containUpperCase) {
            return false;
        }
        if (this.lowerCase && !containLowerCase) {
            return false;
        }
        if (this.letter && !containLetter) {
            return false;
        }
        if (this.digit && !containDigit) {
            return false;
        }
        if (this.special && !containSpecial) {
            return false;
        }
        return true;
    }

    public static Set<Character> defaultSpecialCharSet() {
        Set<Character> specialChars = new LinkedHashSet<>();
        // 键盘上能找到的符号
        specialChars.add(Character.valueOf('~'));
//        specialChars.add(Character.valueOf('`'));
        specialChars.add(Character.valueOf('!'));
        specialChars.add(Character.valueOf('@'));
        specialChars.add(Character.valueOf('#'));
        specialChars.add(Character.valueOf('$'));
        specialChars.add(Character.valueOf('%'));
        specialChars.add(Character.valueOf('^'));
        specialChars.add(Character.valueOf('&'));
        specialChars.add(Character.valueOf('*'));
        specialChars.add(Character.valueOf('('));
        specialChars.add(Character.valueOf(')'));
//        specialChars.add(Character.valueOf('-'));
//        specialChars.add(Character.valueOf('_'));
        specialChars.add(Character.valueOf('+'));
//        specialChars.add(Character.valueOf('='));
        specialChars.add(Character.valueOf('{'));
        specialChars.add(Character.valueOf('['));
        specialChars.add(Character.valueOf('}'));
        specialChars.add(Character.valueOf(']'));
//        specialChars.add(Character.valueOf('|'));
        specialChars.add(Character.valueOf('\\'));
//        specialChars.add(Character.valueOf(':'));
//        specialChars.add(Character.valueOf(';'));
//        specialChars.add(Character.valueOf('"'));
//        specialChars.add(Character.valueOf('\''));
        specialChars.add(Character.valueOf('<'));
//        specialChars.add(Character.valueOf(','));
        specialChars.add(Character.valueOf('>'));
//        specialChars.add(Character.valueOf('.'));
        specialChars.add(Character.valueOf('?'));
//        specialChars.add(Character.valueOf('/'));
        return specialChars;
    }
}
