package session2.exercise3_4.utils;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

import java.io.*;

public class CookieUtils {

    //  ADD COOKIE
    public static void addCookie(WebDriver driver, String name, String value) {

        Cookie cookie = new Cookie(name, value);

        driver.manage().addCookie(cookie);

        System.out.println("Added cookie: " + name);

    }

    //  GET COOKIE
    public static Cookie getCookie(WebDriver driver, String name) {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(d -> d.manage().getCookies().size() > 0);

        Cookie cookie =
                driver.manage().getCookieNamed(name);

        if (cookie != null)
            System.out.println("Found cookie: " + cookie.getName());

        return cookie;

    }

    //  GET ALL COOKIES
    public static Set<Cookie> getAllCookies(WebDriver driver) {

        Set<Cookie> cookies =
                driver.manage().getCookies();

        System.out.println("All cookies:");

        for (Cookie c : cookies)
            System.out.println(c.getName() + "=" + c.getValue());

        return cookies;

    }

    //  DELETE COOKIE
    public static void deleteCookie(WebDriver driver, String name) {

        driver.manage().deleteCookieNamed(name);

        System.out.println("Deleted cookie: " + name);

    }

    //  DELETE ALL COOKIES
    public static void deleteAllCookies(WebDriver driver) {

        driver.manage().deleteAllCookies();

        System.out.println("Deleted all cookies");

    }

    //  SAVE COOKIES using ObjectOutputStream
    public static void saveCookies(WebDriver driver, String filePath) {

        try {

            FileOutputStream fileOut =
                    new FileOutputStream(filePath);

            ObjectOutputStream out =
                    new ObjectOutputStream(fileOut);

            out.writeObject(
                    new HashSet<>(driver.manage().getCookies())
            );

            out.close();
            fileOut.close();

            System.out.println("Cookies saved to file");

        }

        catch (IOException e) {

            e.printStackTrace();

        }

    }

    //  LOAD COOKIES using ObjectInputStream
    public static void loadCookies(WebDriver driver, String filePath) {

        try {

            FileInputStream fileIn =
                    new FileInputStream(filePath);

            ObjectInputStream in =
                    new ObjectInputStream(fileIn);

            Set<Cookie> cookies =
                    (Set<Cookie>) in.readObject();

            for (Cookie cookie : cookies)
                driver.manage().addCookie(cookie);

            in.close();
            fileIn.close();

            System.out.println("Cookies loaded");

        }

        catch (IOException | ClassNotFoundException e) {

            e.printStackTrace();

        }

    }

    // ADVANCED: PRINT AUTH COOKIES
    public static void printAuthCookies(WebDriver driver) {

        System.out.println("Auth cookies:");

        for (Cookie c : driver.manage().getCookies()) {

            if (c.getName().contains("token")
                    || c.getName().contains("user")
                    || c.getName().contains("expires"))

                System.out.println(
                        c.getName() + "=" + c.getValue()
                );

        }

    }

}