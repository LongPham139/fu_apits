package com.apits.apitssystembackend.utils;

import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;
@Configuration
public class StringUtils {
    private final char[] SOURCE_CHARACTERS = {'À', 'Á', 'Â', 'Ã', 'È', 'É',
            'Ê', 'Ì', 'Í', 'Ò', 'Ó', 'Ô', 'Õ', 'Ù', 'Ú', 'Ý', 'Ỳ', 'Ỷ', 'Ỹ', 'Ỵ', 'à', 'á', 'â',
            'ã', 'è', 'é', 'ê', 'ì', 'í', 'ò', 'ó', 'ô', 'õ', 'ù', 'ú', 'ý', 'ỳ', 'ỷ', 'ỹ', 'ỵ',
            'Ă', 'ă', 'Đ', 'đ', 'Ĩ', 'ĩ', 'Ũ', 'ũ', 'Ơ', 'ơ', 'Ư', 'ư', 'Ạ',
            'ạ', 'Ả', 'ả', 'Ấ', 'ấ', 'Ầ', 'ầ', 'Ẩ', 'ẩ', 'Ẫ', 'ẫ', 'Ậ', 'ậ',
            'Ắ', 'ắ', 'Ằ', 'ằ', 'Ẳ', 'ẳ', 'Ẵ', 'ẵ', 'Ặ', 'ặ', 'Ẹ', 'ẹ', 'Ẻ',
            'ẻ', 'Ẽ', 'ẽ', 'Ế', 'ế', 'Ề', 'ề', 'Ể', 'ể', 'Ễ', 'ễ', 'Ệ', 'ệ',
            'Ỉ', 'ỉ', 'Ị', 'ị', 'Ọ', 'ọ', 'Ỏ', 'ỏ', 'Ố', 'ố', 'Ồ', 'ồ', 'Ổ',
            'ổ', 'Ỗ', 'ỗ', 'Ộ', 'ộ', 'Ớ', 'ớ', 'Ờ', 'ờ', 'Ở', 'ở', 'Ỡ', 'ỡ',
            'Ợ', 'ợ', 'Ụ', 'ụ', 'Ủ', 'ủ', 'Ứ', 'ứ', 'Ừ', 'ừ', 'Ử', 'ử', 'Ữ',
            'ữ', 'Ự', 'ự',};
    private final char[] DESTINATION_CHARACTERS = {'A', 'A', 'A', 'A', 'E',
            'E', 'E', 'I', 'I', 'O', 'O', 'O', 'O', 'U', 'U', 'Y', 'Y', 'Y', 'Y', 'Y', 'a', 'a',
            'a', 'a', 'e', 'e', 'e', 'i', 'i', 'o', 'o', 'o', 'o', 'u', 'u',
            'y', 'y', 'y', 'y', 'y', 'A', 'a', 'D', 'd', 'I', 'i', 'U', 'u', 'O', 'o', 'U', 'u',
            'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A',
            'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'E', 'e',
            'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E',
            'e', 'I', 'i', 'I', 'i', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o',
            'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O',
            'o', 'O', 'o', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u',
            'U', 'u', 'U', 'u',};
    private final String SOURCE_STR = Stream.of(SOURCE_CHARACTERS).map(String::valueOf).reduce("",
            String::concat);

    public char removeAccent(char ch) {
        char input = ch;
        int index = SOURCE_STR.indexOf(ch);
        if (index >= 0) {
            ch = DESTINATION_CHARACTERS[index];
        }
        // log.info("removeAccent transform: {} => {} ", input, ch);
        System.out.println("removeAccent transform: {" + input + "} => {" + ch + "} ");

        return ch;
    }

    public String removeAccent(String str) {
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < sb.length(); i++) {
            sb.setCharAt(i, removeAccent(sb.charAt(i)));
        }
        return sb.toString();
    }

    public String getShortName(String fullName) {
        String nickName = "";
        try {
            if (fullName != null && fullName.length() > 0) {
                String arr[] = fullName.trim().split("\\s+");
                List<String> list = new ArrayList<>(Arrays.asList(arr));
                String dem = "";
                String ten = "";
                if (arr.length > 2) {
                    ten = arr[arr.length - 1];
                    list.remove(arr.length - 1);
                    nickName = "" + ten;
                    for (String s : list) {
                        if (!s.equals("") && !s.equals(null)) {
                            dem += String.valueOf(s.charAt(0));
                        }
                    }
                    nickName += dem;
                } else {
                    ten = arr[1];
                    list.remove(arr[1]);
                    for (String s : list) {
                        if (!s.equals("") && !s.equals(null)) {
                            dem += String.valueOf(s.charAt(0));
                        }
                    }
                    nickName = ten + dem;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return removeAccent(nickName);
    }

    public String generateNewEmail(String email, String rootEmail) {
        String newEmail = "";
        String[] tu = email.split("@");
        String[] name = email.split("\\d");
        String[] so = tu[0].split("\\D");
        String number = "";
        for (int i = 0; i < so.length; i++) {
            if (!so[i].isEmpty())
                number += so[i];
        }
        if (number == "") {
            number = "1";
            newEmail = tu[0] + number + rootEmail;
            System.out.println(newEmail);
        }else if (number != "") {
            Integer num = Integer.parseInt(number) + 1;
            number = "" + num;
            newEmail = name[0] + number + rootEmail;
            System.out.println(newEmail);
        }
        return newEmail;
    }

    public String getNameInEmail(String email) {
        String name = "";
        try {
            String[] tu = email.split("@");
            String[] so = tu[0].split("\\D");
            name = tu[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }


}
