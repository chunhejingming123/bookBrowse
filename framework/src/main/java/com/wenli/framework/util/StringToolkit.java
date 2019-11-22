package com.wenli.framework.util;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 字符串工具箱
 *
 * @author changmingzhe
 */
public final class StringToolkit {
    /**
     * 将一个字符串的首字母改为大写或者小写
     *
     * @param srcString 源字符串
     * @param flag      大小写标识，ture小写，false大些
     * @return 改写后的新字符串
     */
    public static String toLowerCaseInitial(String srcString, boolean flag) {
        StringBuilder sb = new StringBuilder();
        if (flag) {
            sb.append(Character.toLowerCase(srcString.charAt(0)));
        } else {
            sb.append(Character.toUpperCase(srcString.charAt(0)));
        }
        sb.append(srcString.substring(1));
        return sb.toString();
    }

    /**
     * 将一个字符串按照句点（.）分隔，返回最后一段
     *
     * @param clazzName 源字符串
     * @return 句点（.）分隔后的最后一段字符串
     */
    public static String getLastName(String clazzName) {
        String[] ls = clazzName.split("\\.");
        return ls[ls.length - 1];
    }

    /**
     * 格式化文件路径，将其中不规范的分隔转换为标准的分隔符,并且去掉末尾的"/"符号。
     *
     * @param path 文件路径
     * @return 格式化后的文件路径
     */
    public static String formatPath(String path) {
        String reg0 = "\\\\＋";
        String reg = "\\\\＋|/＋";
        String temp = path.trim().replaceAll(reg0, "/");
        temp = temp.replaceAll(reg, "/");
        if (temp.endsWith("/")) {
            temp = temp.substring(0, temp.length() - 1);
        }
        if (System.getProperty("file.separator").equals("\\")) {
            temp = temp.replace('/', '\\');
        }
        return temp;
    }

    /**
     * 格式化文件路径，将其中不规范的分隔转换为标准的分隔符,并且去掉末尾的"/"符号(适用于FTP远程文件路径或者Web资源的相对路径)。
     *
     * @param path 文件路径
     * @return 格式化后的文件路径
     */
    public static String formatPath4Ftp(String path) {
        String reg0 = "\\\\＋";
        String reg = "\\\\＋|/＋";
        String temp = path.trim().replaceAll(reg0, "/");
        temp = temp.replaceAll(reg, "/");
        if (temp.endsWith("/")) {
            temp = temp.substring(0, temp.length() - 1);
        }
        return temp;
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("file.separator"));
        @SuppressWarnings("unused")
        Properties p = System.getProperties();
        System.out.println(formatPath("C:///\\xxxx\\\\\\\\\\///\\\\R5555555.txt"));

        //     List<String> result = series2List("asdf | sdf|siii|sapp|aaat| ", "\\|");
        //     System.out.println(result.size());
        //     for (String s : result) {
        //         System.out.println(s);
        //     }
    }

    /**
     * 获取文件父路径
     *
     * @param path 文件路径
     * @return 文件父路径
     */
    public static String getParentPath(String path) {
        return new File(path).getParent();
    }

    /**
     * 获取相对路径
     *
     * @param fullPath 全路径
     * @param rootPath 根路径
     * @return 相对根路径的相对路径
     */
    public static String getRelativeRootPath(String fullPath, String rootPath) {
        String relativeRootPath = null;
        String _fullPath = formatPath(fullPath);
        String _rootPath = formatPath(rootPath);

        if (_fullPath.startsWith(_rootPath)) {
            relativeRootPath = fullPath.substring(_rootPath.length());
        } else {
            throw new RuntimeException("要处理的两个字符串没有包含关系，处理失败！");
        }
        if (relativeRootPath == null) return null;
        else
            return formatPath(relativeRootPath);
    }

    /**
     * 获取当前系统换行符
     *
     * @return 系统换行符
     */
    public static String getSystemLineSeparator() {
        return System.getProperty("line.separator");
    }

    /**
     * 将用“|”分隔的字符串转换为字符串集合列表，剔除分隔后各个字符串前后的空格
     *
     * @param series 将用“|”分隔的字符串
     * @return 字符串集合列表
     */
    public static List<String> series2List(String series) {
        return series2List(series, "\\|");
    }

    /**
     * 将用正则表达式regex分隔的字符串转换为字符串集合列表，剔除分隔后各个字符串前后的空格
     *
     * @param series 用正则表达式分隔的字符串
     * @param regex  分隔串联串的正则表达式
     * @return 字符串集合列表
     */
    private static List<String> series2List(String series, String regex) {
        List<String> result = new ArrayList<String>();
        if (series != null && regex != null) {
            for (String s : series.split(regex)) {
                if (s.trim() != null && !s.trim().equals("")) result.add(s.trim());
            }
        }
        return result;
    }

    /**
     * @param strList 字符串集合列表
     * @return 通过“|”串联为一个字符串
     */
    public static String list2series(List<String> strList) {
        StringBuffer series = new StringBuffer();
        for (String s : strList) {
            series.append(s).append("|");
        }
        return series.toString();
    }

    /**
     * 将字符串的首字母转为小写
     *
     * @param resStr 源字符串
     * @return 首字母转为小写后的字符串
     */
    public static String firstToLowerCase(String resStr) {
        if (resStr == null) {
            return null;
        } else if ("".equals(resStr.trim())) {
            return "";
        } else {
            StringBuffer sb = new StringBuffer();
            Character c = resStr.charAt(0);
            if (Character.isLetter(c)) {
                if (Character.isUpperCase(c))
                    c = Character.toLowerCase(c);
                sb.append(resStr);
                sb.setCharAt(0, c);
                return sb.toString();
            }
        }
        return resStr;
    }

    /**
     * 将字符串的首字母转为大写
     *
     * @param resStr 源字符串
     * @return 首字母转为大写后的字符串
     */
    public static String firstToUpperCase(String resStr) {
        if (resStr == null) {
            return null;
        } else if ("".equals(resStr.trim())) {
            return "";
        } else {
            StringBuffer sb = new StringBuffer();
            Character c = resStr.charAt(0);
            if (Character.isLetter(c)) {
                if (Character.isLowerCase(c))
                    c = Character.toUpperCase(c);
                sb.append(resStr);
                sb.setCharAt(0, c);
                return sb.toString();
            }
        }
        return resStr;
    }

    /**
     * 判断给定的字符串是否null或空字符串
     *
     * @param s
     * @return
     */
    public static boolean isEmpty(String s) {
        if (s == null || s.length() <= 0) return true;
        else return false;
    }

    /**
     * 检测给定字符串，若字符串为null则返回""，否则返回字符串
     *
     * @param text
     * @return
     */
    public static String nullToEmpty(String text) {
        return text == null ? "" : text;
    }

    /**
     * 字符串定长分割成数组
     *
     * @param msg
     * @param num
     * @return
     */
    public static String[] split(String msg, int num) {
        int len = msg.length();
        if (len <= num)
            return new String[]{msg};

        int count = len / (num - 1);
        count += len > (num - 1) * count ? 1 : 0;   //这里应该值得注意

        String[] result = new String[count];

        int pos = 0;
        int splitLen = num - 1;
        for (int i = 0; i < count; i++) {
            if (i == count - 1)
                splitLen = len - pos;

            result[i] = msg.substring(pos, pos + splitLen);
            pos += splitLen;

        }

        return result;
    }

    /**
     * 将InputStream中的文本读出到字符串中
     * @param inputStream 欲读取的流
     * @param charsetName 字符集，若为空则使用“gbk”
     * @return
     */
    public static String inputStreamToString(InputStream inputStream, String charsetName) {
        InputStreamReader inputStreamReader = null;
        String _charsetName = "gbk";
        if (!isEmpty(charsetName)){
            _charsetName = charsetName;
        }
        try {
            inputStreamReader = new InputStreamReader(inputStream, _charsetName);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuffer sb = new StringBuffer("");
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}