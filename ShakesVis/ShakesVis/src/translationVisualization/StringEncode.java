package translationVisualization;

import java.io.UnsupportedEncodingException;

/**  
 * 字符串转换编码  
 * @author Administrator  
 *  
 */  
public class StringEncode {  
  
    /**  
     * 将ISO-8859-1编码字符串转换UTF-8编码  
     * @param param  
     * @return  
     */  
    public static String toUTF8(String param) {  
        if (param == null) {  
            return null;  
        } else {  
            try {  
                param = new String(param.getBytes("ISO-8859-1"), "UTF-8");  
            } catch (UnsupportedEncodingException e) {  
                e.printStackTrace();  
                return param;  
            }  
        }  
        return param;  
    }  
  
}  