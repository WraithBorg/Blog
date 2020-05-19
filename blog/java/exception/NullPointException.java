package com.example.share.exception;

import com.example.demo.User;

public class NullPointException {
    public static void main(String[] args) {
        try {
            User u = null;
            u.getName();
        } catch (Exception e) {
            System.out.println(e.getMessage() == null);
            System.out.println(e.getMessage());
        }
    }
}
